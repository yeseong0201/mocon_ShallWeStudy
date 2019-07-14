package com.example.mocon_shallwestudy;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DevicePolicyManager deviceMgr;
    ComponentName comp;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        if (BuildConfig.DEBUG) {
            Log.d("ScreenLock", "MainActivity oncreate.");
        }
        deviceMgr = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        comp = new ComponentName(this, ScreenLockDeviceAdminReceiver.class);
        if (!deviceMgr.isAdminActive(comp)) {
            Log.d("ScreenLock", "Main :admin is false");
            Intent intent = new Intent(
                    DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, comp);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    getString(R.string.devicePolicyManagerMessage));

            startActivityForResult(intent, 0);
        } else {
            Log.d("ScreenLock", "Main : admin is true");
            deviceMgr.lockNow();
            finish();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            deviceMgr.lockNow();
        } else {
            Toast.makeText(this, R.string.failActivation, Toast.LENGTH_LONG)
                    .show();
        }
        finish();


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        switch (keyCode) {
//
//            case KeyEvent.KEYCODE_BACK:
//                while(true) {
//                    i = new Intent(getApplicationContext(), RestartActivity.class);
//
//                    startActivity(i);
//                }
//        }
//        return super.onKeyDown(keyCode, event);
//
//    }
//
//    @Override
//    protected void onUserLeaveHint() {
//        i = new Intent(getApplicationContext(), RestartActivity.class);
//        startActivity(i);
//        super.onUserLeaveHint();
//
//    }

    }
}