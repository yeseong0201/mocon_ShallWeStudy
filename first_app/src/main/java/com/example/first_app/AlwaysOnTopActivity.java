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
    private static final int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 1;
    private static final int REQ_CODE_OVERLAY_PERMISSION = 1;


    private SetTimeDialog dialog;

    TextView setTimeH;
    TextView setTimeM;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);



        Intent intent = new Intent(getApplicationContext(), AlwaysOnTopService.class);
        setTimeH = findViewById(R.id.setTimeH);
        setTimeM = findViewById(R.id.setTimeM);
        Button start = findViewById(R.id.start);
        Button end = findViewById(R.id.end);
        final Button start_lock = findViewById(R.id.start_lock);


        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dialog();
                SetTimeDialog dialog = new SetTimeDialog(AlwaysOnTopActivity.this);
                dialog.callFunction(setTimeH, setTimeM);
                start_lock.setVisibility(View.VISIBLE);

            }
        });

        start_lock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    openView();

                // checkPermission();
//                Intent intent = new Intent(AlwaysOnTopActivity.this, LockActivity.class);
//                intent.putExtra("Hour", setTimeH.getText().toString());
//                intent.putExtra("Minute", setTimeM.getText().toString());
//                startActivity(intent);

            }
        });

        end.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeView();
                // stopService(new Intent(AlwaysOnTopActivity.this, AlwaysOnTopService.class));
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

    public void openView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(AlwaysOnTopActivity.this, AlwaysOnTopService.class);
                intent.putExtra("Hour", setTimeH.getText().toString());
                intent.putExtra("Minute", setTimeM.getText().toString());
                startService(intent);
            }


            else
                onObtainingPermissionOverlayWindow();
        }
    }

    public void closeView() {
        stopService(new Intent(this, AlwaysOnTopService.class));
    }

    public void onObtainingPermissionOverlayWindow() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQ_CODE_OVERLAY_PERMISSION);
    }

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
