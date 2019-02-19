package com.hfad.betup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    Button history;
    Button bonus;
    Button moreTips;
    Button todayTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todayTips = (Button) findViewById(R.id.todayTips);
        todayTips.setOnClickListener(this);
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
            case R.id.todayTips:
                Intent intent = new Intent(this, TodayTips.class);
                startActivity(intent);
                break;
            case R.id.history:
                Intent intent_history = new Intent(this, History.class);
                startActivity(intent_history);
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
