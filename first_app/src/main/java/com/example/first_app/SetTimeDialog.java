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
import android.widget.Toast;

import java.io.Serializable;

public class SetTimeDialog extends AppCompatActivity {
    EditText hour;
    EditText min;
    Button Ok;
    Button No;

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

    public void callFunction(final TextView setTimeH, final TextView setTimeM) {
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

                final String hour_s = hour.getText().toString();
                final String min_s = min.getText().toString();

                if (min_s.length() != 0 || hour_s.length() != 0) {
                    setTimeH.setText(hour_s);
                    setTimeM.setText(min_s);
                    dig.dismiss();
                }
                else Toast.makeText(context, "시간을 설정해주세요.", Toast.LENGTH_SHORT).show();

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
