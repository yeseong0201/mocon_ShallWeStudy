package com.example.mylock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlwaysOnTopActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

//		findViewById(R.id.start).setOnClickListener(this);		//���۹�ư
//		findViewById(R.id.end).setOnClickListener(this);			//�߽ù�ư

        Button btn_start = findViewById(R.id.start);
        Button btn_end = findViewById(R.id.end);

        btn_start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(AlwaysOnTopActivity.this, AlwaysOnTopService.class));
            }
        });

    }

    public void startOverlayWindowService(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(context)) {
            getView().onObtainingPermissionOverlayWindow();

        } else {
            getView().onStartOverlay();
        }
    }

//	@Override
//	public void onClick(View v) {
//		int view = v.getId();
//		if(view == R.id.start)
//			startService(new Intent(this, AlwaysOnTopService.class));	//���� ����
//		else
//			stopService(new Intent(this, AlwaysOnTopService.class));	//���� ����
//	}
}