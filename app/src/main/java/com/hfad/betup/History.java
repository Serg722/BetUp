package com.hfad.betup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class History extends Activity {


    Button todayTips;
    Button tipsBonus;

    private RecyclerView recyclerView;
    private PredictionAdapter mAdapter;
    DatabaseReference dbPredict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        dbPredict = FirebaseDatabase.getInstance().getReference().child("Predictions");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_history);
        mAdapter = new PredictionAdapter(true,this, dbPredict,false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        LinearLayout test=findViewById(R.id.test_anim);
        Animation animTest = AnimationUtils.loadAnimation(this,R.anim.test);
        test.startAnimation(animTest);

        todayTips = (Button) findViewById(R.id.todayTips);
        todayTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".TodayTips");
                startActivity(intent);
            }
        });

        tipsBonus = (Button) findViewById(R.id.bonus_tips);
        tipsBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".BonusTips");
                startActivity(intent);
            }
        });
    }
}
