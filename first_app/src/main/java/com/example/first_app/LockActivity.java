package com.example.first_app;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

public class LockActivity extends AppCompatActivity {

    private static final int MILLISINFUTURE = 11 * 1000;
    private static final int COUNT_DOWN_INTERVAL = 1000;
    private static final int HOUR = 3600 * 1000;
    private static final int MINUTE = 60 * 1000;

    TextView leftTimeHour;
    TextView leftTimeMin;
    TextView leftTimeSec;
    int timeH, timeM, timeS = 60;
    String strH;
    String strM;
    CountDownTimer countDownTimer;
    int TotalTime;


    ProgressBar progressBar;
    int count = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock_activity);


//        CountDownTimer();
//        countDownTimer.start();
//
//        ProgressBarRunning();

        // startService(new Intent(LockActivity.this, AlwaysOnTopService.class));

    }


    public void ProgressBarRunning() {
        progressBar = findViewById(R.id.circular_progress_bar);
        ObjectAnimator anim = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        anim.setDuration(TotalTime);
        anim.start();
    }

    public void CountDownTimer() {
        leftTimeHour = findViewById(R.id.left_time_hour);
        leftTimeMin = findViewById(R.id.left_time_min);
        leftTimeSec = findViewById(R.id.left_time_second);

        Intent intent = getIntent();
        strH = intent.getExtras().getString("Hour");
        strM = intent.getExtras().getString("Minute");
        timeH = Integer.parseInt(intent.getExtras().getString("Hour"));
        timeM = Integer.parseInt(intent.getExtras().getString("Minute"));

        TotalTime = (timeH * HOUR) + (timeM * MINUTE);

        countDownTimer = new CountDownTimer(TotalTime, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {

                leftTimeHour.setText(String.valueOf(millisUntilFinished / (HOUR)));

                leftTimeMin.setText(String.valueOf((millisUntilFinished % HOUR) / MINUTE));


//                while ((millisUntilFinished % HOUR) / MINUTE < 1) {
//                    int cnt = 59;
//                    if (cnt > 0) cnt--;
//                    if (cnt > 59) cnt -= 60;
//                    leftTimeSec.setText(String.valueOf(cnt));
//                }

            }

            @Override
            public void onFinish() {
                leftTimeHour.setText("00");
                leftTimeMin.setText("00");
                leftTimeSec.setText("00");
                stopService(new Intent(LockActivity.this, AlwaysOnTopService.class));

            }
        };
    }
}
