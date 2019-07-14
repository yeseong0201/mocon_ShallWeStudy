package com.example.app_lock2;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class LockActivity extends AppCompatActivity {
    Button toMain;
    public static LockActivity lockActivity;

    String data;
    static public int cnt;
    private int delaytime;
    private CountDownTimer timer;
    private Intent toLS;

    TextView timer_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        timer_txt = findViewById(R.id.timer_txt);

        toLS = new Intent(getApplicationContext(), LockService.class);


        Intent gettime = getIntent();

        data = gettime.getStringExtra("seTtime");
        cnt = Integer.parseInt(data);

        LeftTIME();


        toMain = findViewById(R.id.toMain);
        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public LockActivity() {
    }
    //public int getCnt(){ return cnt;}

    public void LeftTIME() {
        cnt += 1;
        int delay = cnt * 1000;
        timer = new CountDownTimer(delay, 1000) {//delay 시간 동안 1000 만큼 줄어들기
            @Override
            public void onTick(long millisUntilFinished) {
                cnt--;
                timer_txt.setText(cnt + "");
            }

            @Override
            public void onFinish() {
                finish();
            }
        };
        timer.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  메뉴 버튼 누르면 이벤트 발생
        startService(toLS);
        return true;
    }

    @Override
    protected void onUserLeaveHint() {
        // 홈키 누르면 이벤트 발생
        //홈키 버튼이 눌려졌을 때 화면이 보여지면 이벤트가 실행되는 것 같음
        super.onUserLeaveHint();
//        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(i);

    /*    Intent intent = getIntent(); // 메뉴 키 눌러도 화면이 새로고침 됨 왜 그러지 ??
        // finish();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/

        startService(toLS);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 뒤로가기 버튼 뉘르면 이벤트 발생
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Toast.makeText(this, "화면 잠금 중입니다!", Toast.LENGTH_SHORT).show();
                return false;

        }
        return super.onKeyDown(keyCode, event);
    }


}



