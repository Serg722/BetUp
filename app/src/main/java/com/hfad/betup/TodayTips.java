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
import com.hfad.betup.Adapters.PredictionAdapter;


public class TodayTips extends Activity implements View.OnClickListener {

    Button history;
    Button bonus;
    Button moreTips;
    private RecyclerView recyclerView;
    private PredictionAdapter mAdapter;
    private TextView header;
    DatabaseReference dbPredict;
    final String TAG = "Prediction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_today);
        dbPredict = FirebaseDatabase.getInstance().getReference().child("Predictions");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_today);
        header = findViewById(R.id.carrent_date);
        mAdapter = new PredictionAdapter(this, dbPredict);
        header.setText(mAdapter.getCurrendate());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        LinearLayout test = findViewById(R.id.test_anim);
        Animation animTest = AnimationUtils.loadAnimation(this, R.anim.test);
        test.startAnimation(animTest);
        history = (Button) findViewById(R.id.history);
        history.setOnClickListener(this);
        bonus = (Button) findViewById(R.id.bonus);
        bonus.setOnClickListener(this);
        moreTips = (Button) findViewById(R.id.moretips);
        moreTips.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.history:
                Intent intent = new Intent(this, History.class);
                startActivity(intent);
                break;
            case R.id.bonus:
                Intent intent_bonus = new Intent(this, BonusTips.class);
                startActivity(intent_bonus);
                break;
            case R.id.moretips:
                Intent intent_more = new Intent(this, MoreApp.class);
                startActivity(intent_more);
                break;

            default:
                break;
        }
    }
}
