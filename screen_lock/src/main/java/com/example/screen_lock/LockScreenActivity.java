package com.example.screen_lock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class LockScreenActivity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    getWindow().addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
            | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);



    setContentView(R.layout.activity_lock_screen);

    findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {


        @Override

        public void onClick(View v) {

            finish();

        }

    });

}



}
