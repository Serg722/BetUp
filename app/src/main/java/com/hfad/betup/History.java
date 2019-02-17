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
import com.hfad.betup.Adapters.HistoryAdapter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class History extends Activity implements View.OnClickListener {

    Button bonus;
    Button moreTips;
    Button todayTips;
    Button left;
    Button right;
    Date dataConst;
    Date currentDate;
    private RecyclerView recyclerView;
    private HistoryAdapter mAdapter;
    DatabaseReference dbPredict;
    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        currentDate = new Date();
        dataConst = new Date();
        currentDate.setDate(currentDate.getDate() - 1);
        dataConst.setDate(dataConst.getDate() - 1);
        SimpleDateFormat formatItem = new SimpleDateFormat("dd.MM.yyyy");
        String date1 = formatItem.format(this.currentDate);
        setContentView(R.layout.history);
        dbPredict = FirebaseDatabase.getInstance().getReference().child("Predictions");
        header = findViewById(R.id.carrent_date);
        mAdapter = new HistoryAdapter(this, dbPredict, currentDate);
        header.setText(date1);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_history);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        LinearLayout test = findViewById(R.id.test_anim);
        Animation animTest = AnimationUtils.loadAnimation(this, R.anim.test);
        test.startAnimation(animTest);

        todayTips = (Button) findViewById(R.id.todayTips);
        todayTips.setOnClickListener(this);
        bonus = (Button) findViewById(R.id.bonus);
        bonus.setOnClickListener(this);
        moreTips = (Button) findViewById(R.id.moretips);
        moreTips.setOnClickListener(this);
        left = (Button) findViewById(R.id.left);
        left.setOnClickListener(this);
        right = (Button) findViewById(R.id.right);
        right.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.todayTips:
                Intent intent = new Intent(this, TodayTips.class);
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
            case R.id.left:
                currentDate.setDate(currentDate.getDate() - 1);
                mAdapter.changeDateFiltr(this.currentDate);
                repaintDateHeader();
                break;
            case R.id.right:
                if (currentDate.before(dataConst)) {
                    currentDate.setDate(currentDate.getDate() + 1);
                    mAdapter.changeDateFiltr(this.currentDate);
                    repaintDateHeader();
                }
                break;
            default:
                break;
        }
    }

    void repaintDateHeader() {
        SimpleDateFormat formatItem = new SimpleDateFormat("dd.MM.yyyy");
        String date1 = formatItem.format(this.currentDate);
        header.setText(date1);
    }
}
