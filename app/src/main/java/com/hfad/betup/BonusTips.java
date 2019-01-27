package com.hfad.betup;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hfad.betup.Adapters.BonusAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BonusTips extends Activity {

    private Button todayTipsBonus;
    private Button history;

    private RecyclerView recyclerView;
    private BonusAdapter mAdapter;
    DatabaseReference dbPredict;
    private TextView header;
    final String TAG = "Prediction";
    List<BetToday> listPrediction=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_bonus);
        dbPredict=FirebaseDatabase.getInstance().getReference().child("Predictions");
        Query zapros=dbPredict.orderByKey();
        zapros.addValueEventListener(predictEventListener);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_bonus);
        header = findViewById(R.id.carrent_date);

        mAdapter = new BonusAdapter(listPrediction,this);
        header.setText(mAdapter.getCurrendate());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        // mAdapter.notifyDataSetChanged();
        LinearLayout test=findViewById(R.id.test_anim);
        Animation animTest = AnimationUtils.loadAnimation(this,R.anim.test);
        test.startAnimation(animTest);

        todayTipsBonus = (Button) findViewById(R.id.todayTips);


        todayTipsBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".TodayTips");
                startActivity(intent);
            }
        });

        history = (Button) findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".History");
                startActivity(intent);
            }
        });

    }
    ValueEventListener predictEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            // listGrupCurrenUser.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot temp:dataSnapshot.getChildren()){
                    BetToday predict=temp.getValue(BetToday.class);
                    if(predict.getFlagBonus().equals("true")){
                        listPrediction.add(predict);
                    }

                }
            }
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
