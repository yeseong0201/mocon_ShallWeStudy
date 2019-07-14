package com.example.app_lock2;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity {
    private SettingTimeDialog dialog;
    EditText edt;
    Button lock_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = findViewById(R.id.timeSet);


        lock_btn = findViewById(R.id.lock_btn);
        lock_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LockActivity.class);
                i.putExtra("seTtime", edt.getText().toString());
                startActivity(i);


                //  dialog.show();
                // dialog = new SettingTimeDialog();
            }
        });

    }


}



