package com.hfad.betup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.hfad.betup.BetToday;
import com.hfad.betup.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class HistoryAdapter  extends  RecyclerView.Adapter<HistoryAdapter.CustomViewHolder> {
    DatabaseReference db;
    private LayoutInflater mInflater;
    private List<BetToday> predictions = new ArrayList<>();
    // private List<BetToday> predictionsAll = new ArrayList<>();
    final String TAG = "BonusAdapter";
    TreeMap<String, Integer> flags = new TreeMap<>();
    private ChildEventListener mChildEventListener;
    String currendate="";


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView country;
        public ImageView flag;
        public TextView teams;
        public TextView timeMatch;
        public TextView predictionToday;
        public TextView keffGame;


        public CustomViewHolder(View view) {
            super(view);
            country = (TextView) view.findViewById(R.id.country);
            flag = (ImageView) view.findViewById(R.id.flag);
            timeMatch = (TextView) view.findViewById(R.id.time);
            teams = (TextView) view.findViewById(R.id.bothTeams);
            predictionToday = (TextView) view.findViewById((R.id.prediction));
            keffGame = (TextView) view.findViewById(R.id.teamKeff);

        }
    }


    public HistoryAdapter(Context ctx, DatabaseReference dbPrediction) {
        createFlag();

        this.mInflater = LayoutInflater.from(ctx);
        this.db = dbPrediction;
        Date tempDate=new Date();
        SimpleDateFormat formatItem = new SimpleDateFormat("dd.MM.yyyy");
        this.currendate=formatItem.format(tempDate);
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot ds, @Nullable String s) {

                String m_country = ds.child("country").getValue(String.class);
                String m_time = ds.child("time").getValue(String.class);
                String m_teamOwner = ds.child("teamOwner").getValue(String.class);
                String m_teamGuest = ds.child("teamGuest").getValue(String.class);
                String m_resultMatchOwner = ds.child("resultMatchOwner").getValue(String.class);
                String m_resultMatchGuest = ds.child("resultMatchGuest").getValue(String.class);
                String m_betPrediction = ds.child("betPrediction").getValue(String.class);
                String m_keff = ds.child("keff").getValue(String.class);
                String m_state = ds.child("state").getValue(String.class);
                String m_flag = ds.child("flag").getValue(String.class);
                String m_flagBonus = ds.child("flagBonus").getValue(String.class);

                BetToday predict = new BetToday(m_country, m_time, m_teamOwner, m_teamGuest,
                        m_resultMatchOwner, m_resultMatchGuest, m_betPrediction, m_keff, m_state, m_flag, m_flagBonus);
                addCollection(predict);
                notifyItemInserted(predictions.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        this.db.addChildEventListener(childEventListener);
        mChildEventListener = childEventListener;
    }

    private void addCollection( BetToday predict) {
        String m_flagBonus=predict.getFlagBonus();
        String m_resultMatchOwner=predict.getResultMatchOwner();

        if( m_flagBonus.equals("false")&& m_resultMatchOwner.equals("-1")==false){
            predictions.add(predict);
        }
    }

    private void createFlag() {
        flags.put("england.jpg", R.drawable.england);
        flags.put("germany.jpg", R.drawable.germany);
        flags.put("ukraine.jpg", R.drawable.ukraine);
        flags.put("austriya.jpg", R.drawable.austriya);
        flags.put("belarus.jpg", R.drawable.belarus);
        flags.put("belgium.jpg", R.drawable.belgium);
        flags.put("championsleague.jpg", R.drawable.championsleague);
        flags.put("croatia.jpg", R.drawable.croatia);
        flags.put("czech.jpg", R.drawable.czech);
        flags.put("denmark.jpg", R.drawable.denmark);
        flags.put("europaleague.jpg", R.drawable.europaleague);
        flags.put("finland.jpg", R.drawable.finland);
        flags.put("france.jpg", R.drawable.france);
        flags.put("iceland.jpg", R.drawable.iceland);
        flags.put("irland.jpg", R.drawable.irland);
        flags.put("italy.jpg", R.drawable.italy);
        flags.put("nitherlands.jpg", R.drawable.nitherlands);
        flags.put("norway.jpg", R.drawable.norway);
        flags.put("polsky.jpg", R.drawable.polsky);
        flags.put("portugal.jpg", R.drawable.portugal);
        flags.put("romania.jpg", R.drawable.romania);
        flags.put("scotland.jpg", R.drawable.scotland);
        flags.put("spain.jpg", R.drawable.spain);
        flags.put("sweden.jpg", R.drawable.sweden);
        flags.put("switzerland.jpg", R.drawable.switzerland);
        flags.put("turkey.jpg", R.drawable.turkey);
    }

    @Override
    public HistoryAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = this.mInflater
                .inflate(R.layout.prediction_item, parent, false);

        return new HistoryAdapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.CustomViewHolder holder, int position) {
        BetToday tempPrediction = predictions.get(position);
        holder.country.setText(tempPrediction.getCountry());
        holder.teams.setText(tempPrediction.getTeamOwner() + " - " + tempPrediction.getTeamGuest());
        // SimpleDateFormat formatItem = new SimpleDateFormat("hh:mm");
        String time = tempPrediction.getTime().split(",")[1];
        holder.timeMatch.setText(time);
        holder.flag.setImageResource(flags.get(tempPrediction.getFlag()));
        holder.predictionToday.setText(tempPrediction.getBetPrediction());
        holder.keffGame.setText(String.valueOf(tempPrediction.getKeff()));
        int stateColor = 0;
        if (tempPrediction.getResultMatchGuest().equals("-1") == false) {
            holder.timeMatch.setText(tempPrediction.getTime());
            holder.teams.setText(tempPrediction.getTeamOwner() + " (" +
                    tempPrediction.getResultMatchOwner()+ "-" +
                    tempPrediction.getResultMatchGuest() + ") " + tempPrediction.getTeamGuest());
            stateColor = tempPrediction.getState().equals("1") ? BetToday.POSITIVE_COLOR :
                    tempPrediction.getState().equals("-1") ? BetToday.NEGATIVE_COLOR : BetToday.RETURN_COLOR;
        } else {
            stateColor = BetToday.NO_HISTORY;
        }
        holder.keffGame.setTextColor(stateColor);

        // holder.flag.setImageResource(R.drawable.tennis2018);
    }

    @Override
    public int getItemCount() {
        return predictions.size();
    }
}
