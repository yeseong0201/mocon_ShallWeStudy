package com.example.app_lock2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class LockService extends Service {
  //  IBinder binder = new LockBinder();
    LockActivity la = new LockActivity();


//    public class LockBinder extends Binder {
//        public LockService getService() {
//            return LockService.this;
//        }
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent toLA = new Intent(getApplicationContext(), LockActivity.class);
     //  toLA.putExtra("getCnt", la.getCnt());
        startActivity(toLA);


        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
