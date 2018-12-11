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

import java.util.ArrayList;


public class TodayTips extends Activity {

    private RecyclerView recyclerView;
    private PredictionAdapter mAdapter;
    private Button history;
    private Button tipsBonus;
    private TextView header;
    //FirebaseFirestore db;
    DatabaseReference dbPredict;
    final String TAG = "Prediction";
     ArrayList<BetToday> spisok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_today);
        dbPredict=FirebaseDatabase.getInstance().getReference().child("Predictions");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_today);
        header = findViewById(R.id.carrent_date);
        mAdapter = new PredictionAdapter(false,this,dbPredict, false);
        header.setText(mAdapter.currendate);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
       // mAdapter.notifyDataSetChanged();
        LinearLayout test=findViewById(R.id.test_anim);
        Animation animTest = AnimationUtils.loadAnimation(this,R.anim.test);
        test.startAnimation(animTest);

        addListerOnButtonHistory();
        addListerOnButtonBonus();
    }
    public void addListerOnButtonHistory() {
        history = (Button) findViewById(R.id.history);
        history.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".History");
                        startActivity(intent);
                    }
                }
        );
    }
    public void addListerOnButtonBonus() {
        tipsBonus = (Button) findViewById(R.id.bonus);
        tipsBonus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".BonusTips");
                        startActivity(intent);
                    }
                }
        );
    }

    }
