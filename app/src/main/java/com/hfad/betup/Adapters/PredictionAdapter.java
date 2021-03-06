package com.hfad.betup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hfad.betup.BetToday;
import com.hfad.betup.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Abhi on 23 Sep 2017 023.
 */

public class PredictionAdapter extends RecyclerView.Adapter<PredictionAdapter.CustomViewHolder> {
    DatabaseReference db;
    private LayoutInflater mInflater;
    private List<BetToday> predictions = new ArrayList<>();
    final String TAG = "PredictionAdapter";
    TreeMap<String, Integer> flags = new TreeMap<>();
    private String currendate="";
    private Date filtr;


    public String getCurrendate() {
        return currendate;
    }

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

    public PredictionAdapter( Context ctx, DatabaseReference dbPrediction) {
          createFlag();
        this.mInflater = LayoutInflater.from(ctx);
        this.db = dbPrediction;
        Date tempDate=new Date();
        this.filtr=new Date();
        SimpleDateFormat formatItem = new SimpleDateFormat("dd.MM.yyyy");
        this.currendate=formatItem.format(tempDate);
        changeDateFiltr(this.filtr);
    }

    public void changeDateFiltr(Date dateFiltr) {
        this.filtr=dateFiltr;
        SimpleDateFormat formatItem = new SimpleDateFormat("dd.MM.yyyy");
        String date1=formatItem.format(this.filtr);
        Query request = db.orderByChild("date").equalTo(date1);
        request.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    predictions.clear();
                    for (DataSnapshot PredSnapshot : dataSnapshot.getChildren()) {
                        BetToday temp = PredSnapshot.getValue(BetToday.class);
                        predictions.add(temp);
                    }
                    Iterator<BetToday> it1=predictions.iterator();
                    while(it1.hasNext()){
                        BetToday temp1= it1.next();
                        if(temp1.getFlagBonus().equals("true")){
                            it1.remove();
                        }
                    }
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        flags.put("basket.png", R.drawable.basket);
        flags.put("tennis.png", R.drawable.tennis);
        flags.put("hockey.png", R.drawable.hockey);
        flags.put("world.jpg", R.drawable.world);
        flags.put("football.png", R.drawable.football);

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = this.mInflater
                .inflate(R.layout.prediction_item, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        BetToday tempPrediction = predictions.get(position);
        holder.country.setText(tempPrediction.getCountry());
        holder.teams.setText(tempPrediction.getTeamOwner() + " - " + tempPrediction.getTeamGuest());
        holder.timeMatch.setText(tempPrediction.getTime());
        holder.flag.setImageResource(flags.get(tempPrediction.getFlag()));
        holder.predictionToday.setText(tempPrediction.getBetPrediction());
        holder.keffGame.setText(String.valueOf(tempPrediction.getKeff()));
        int stateColor = 0;
           stateColor = BetToday.NO_HISTORY;
         holder.keffGame.setTextColor(stateColor);
    }

    @Override
    public int getItemCount() {
        return predictions.size();
    }

}

