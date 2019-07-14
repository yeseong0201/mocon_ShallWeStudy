package com.example.first_app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;
import static android.os.Build.VERSION_CODES.O;

public class AlwaysOnTopActivity extends Activity {
    // public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;
    private static final int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 1;

    private SetTimeDialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final TextView setTime = findViewById(R.id.setTime);


        Button start = findViewById(R.id.start);
        Button end = findViewById(R.id.end);
        Button start_lock = findViewById(R.id.start_lock);


        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // Dialog();
                SetTimeDialog dialog = new SetTimeDialog(AlwaysOnTopActivity.this);
                dialog.callFunction(setTime);

            }
        });

        start_lock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();

            }
        });

        end.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(AlwaysOnTopActivity.this, AlwaysOnTopService.class));
            }
        });


    }

    public void Dialog() {

    }
//
//    Context context;
//
//    public Dialog(Context _context) {
//        super(context);
//        context = _context;
//
//    }


    public void checkPermission() { // o
        if (Build.VERSION.SDK_INT >= M) {   // 마시멜로우 이상일 경우
            if (!Settings.canDrawOverlays(this)) {              // 체크
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            } else {
                startService(new Intent(AlwaysOnTopActivity.this, AlwaysOnTopService.class));
            }
        } else {
            startService(new Intent(AlwaysOnTopActivity.this, AlwaysOnTopService.class));
        }
    }


    @TargetApi(M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                // TODO 동의를 얻지 못했을 경우의 처리

            } else {
                startService(new Intent(AlwaysOnTopActivity.this, AlwaysOnTopService.class));
            }
        }
    }
}
