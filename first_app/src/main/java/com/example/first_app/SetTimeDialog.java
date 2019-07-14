package com.example.first_app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class SetTimeDialog extends AppCompatActivity {
    EditText hour;
    EditText min;
    Button Ok;
    Button No;
    String hour_s;
    String min_s;
    int timeH;
    int timeM;


    private Context context;


    public SetTimeDialog(Context context) {
        this.context = context;
    }


//
//    public static SetTimeDialog getInstance(){
//        SetTimeDialog dialog = new SetTimeDialog();
//        return dialog;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // callFunction(final TextView setTime);
    }

    public void callFunction(final TextView setTime) {
        final Dialog dig = new Dialog(context);

        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dig.setContentView(R.layout.set_time_dialog);
        dig.show();

        hour = dig.findViewById(R.id.hour);
        min = dig.findViewById(R.id.min);

        Ok = dig.findViewById(R.id.ok);
        No = dig.findViewById(R.id.no);

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SetTimeDialog.this, AlwaysOnTopActivity.class);
//
//
                hour_s = hour.getText().toString();
                min_s = min.getText().toString();

                timeH = Integer.parseInt(hour_s);
                timeM = Integer.parseInt(min_s);

                setTime.setText(timeH + " 시간" + timeM + " 분");


                //  intent.putExtra("hour", timeH);
                //  intent.putExtra("minnute", timeM);
//                startActivity(intent);

                dig.dismiss();
            }
        });

        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig.dismiss();
            }
        });
    }
}
