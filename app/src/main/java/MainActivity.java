package com.hfad.betup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button go_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListerOnButton();
    }

    public void addListerOnButton() {
        go_two = (Button) findViewById(R.id.go_two);
        go_two.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".TodayTips");
                        startActivity(intent);
                    }
                }
        );


    }
}
