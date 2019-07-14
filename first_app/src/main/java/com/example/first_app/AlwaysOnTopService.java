package com.example.first_app;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class AlwaysOnTopService extends Service {
    //    private View lockView;
//    private WindowManager.LayoutParams mParams;        //layout params ��ü. ���� ��ġ �� ũ�⸦ �����ϴ� ��ü
    // private WindowManager.LayoutParams params;
//    private WindowManager mWindowManager;            //������ �Ŵ���
//    private WindowManager wm;
    private WindowManager.LayoutParams params;

    ConstraintLayout constraintLayout;
    WindowManager wm;
    View mView;


    @Override
    public IBinder onBind(Intent arg) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        /*ViewGroup.LayoutParams.MATCH_PARENT*/
        params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        // params.gravity = Gravity.LEFT | Gravity.TOP;

        mView = inflate.inflate(R.layout.lock_activity, null);


        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.addView(mView, params);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wm != null) {
            if (mView != null) {
                wm.removeView(mView);
                mView = null;
            }
            wm = null;

        }
    }
}


//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        // View lockView;
//
//
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        lockView = inflater.inflate(R.layout.screen_lock_view, null);
//
//        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
////        params = new WindowManager.LayoutParams(
//////                WindowManager.LayoutParams.MATCH_PARENT,
//////                WindowManager.LayoutParams.MATCH_PARENT,
//////                WindowManager.LayoutParams.TYPE_PHONE,
//////                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
////                //PixelFormat.TRANSPARENT
////        );
////        params = new WindowManager.LayoutParams(
////                WindowManager.LayoutParams.MATCH_PARENT,
////                WindowManager.LayoutParams.WRAP_CONTENT,
////                WindowManager.LayoutParams.TYPE_PHONE,
////                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
////                PixelFormat.TRANSPARENT
////
////        );
////        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);    //������ �Ŵ��� �ҷ���.
//
////        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
////                ViewGroup.LayoutParams.WRAP_CONTENT,
////                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
////                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
////                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
////                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
////                PixelFormat.TRANSLUCENT
////        );
//
//        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                /*ViewGroup.LayoutParams.MATCH_PARENT*/400,
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        |WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//                        |WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
//                PixelFormat.TRANSLUCENT);
//        wm.addView(lockView,params);
//
//        //  mWindowManager.addView(lockView, params);        //�ֻ��� �����쿡 �� �ֱ�. *�߿� : ���⿡ permission�� �̸� ������ �ξ�� �Ѵ�. �Ŵ��佺Ʈ��
//
//
//
//    }
//
//    @Override
//    public void onDestroy() {
//        if (mWindowManager != null) {        //서비스 종료시 뷰 제거. *중요 : 뷰를 꼭 제거 해야함.
//         //   if (lockView != null) mWindowManager.removeView(lockView);
//        }
//
//        super.onDestroy();
//    }
//
//
//
//}
