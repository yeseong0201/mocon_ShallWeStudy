package com.example.applock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Intent serviceIntent;
    Intent i;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                // 뒤로가기 버튼 눌렀을 때 잠금 화면 돌아가기

                if (event.getRepeatCount() == 0) {
//                    i = new Intent(getApplicationContext(), LockActivity.class);
//                    startActivity(i);
                }

                //return true;
        }
        return super.onKeyDown(keyCode, event);

    }

            protected void setAlarmTimer() {
            final Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);
            Intent intent = new Intent(this, AlarmRecever.class);
            PendingIntent sender = PendingIntent.getBroadcast(this, 0,intent,0);

            AlarmManager mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            mAlarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
        }
    @Override
    protected void onUserLeaveHint() {
        // 홈 키 눌렀을 때 잠금 화면을 돌아가기
//        i = new Intent(getApplicationContext(), LockActivity.class);
//        startActivity(i);
        super.onUserLeaveHint();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (RealService.serviceIntent == null) {
            serviceIntent = new Intent(this, RealService.class);
            startService(serviceIntent);
        } else {
            serviceIntent = RealService.serviceIntent;//getInstance().getApplication();
            Toast.makeText(getApplicationContext(), "already", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceIntent != null) {
            stopService(serviceIntent);
            serviceIntent = null;
        }
    }
}


