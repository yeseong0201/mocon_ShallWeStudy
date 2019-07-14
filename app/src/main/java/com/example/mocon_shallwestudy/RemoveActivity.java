package com.example.mocon_shallwestudy;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class RemoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        Log.d("ScreenLock", "RemoveActivity oncreate.");
        ComponentName cmpName = new ComponentName(this, ScreenLockDeviceAdminReceiver.class);
        DevicePolicyManager deviceMgr = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        if(deviceMgr.isAdminActive(cmpName)){
            Toast.makeText(this, R.string.tryAdminInactive, Toast.LENGTH_LONG).show();
            Log.d("ScreenLock", "Rmove :admin is true");
            deviceMgr.removeActiveAdmin(cmpName);
        }else{
            Log.d("ScreenLock", "Remove admin is false");
            Uri uri = Uri.fromParts("package", "net.xcoda.android.screenlock", null);
            Intent i = new Intent(Intent.ACTION_DELETE,uri );
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(i);

        }
        finish();
    }
}
