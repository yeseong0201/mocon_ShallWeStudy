package com.example.first_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LockActivity extends AppCompatActivity {
    TextView leftTimeHour;
    TextView leftTimeMin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock_activity);

        leftTimeHour = findViewById(R.id.left_time_hour);
        leftTimeMin = findViewById(R.id.left_time_min);



    }
}
