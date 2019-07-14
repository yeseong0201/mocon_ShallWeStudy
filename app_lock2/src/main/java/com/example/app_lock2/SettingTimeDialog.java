package com.example.app_lock2;

import java.util.Calendar;

import java.util.GregorianCalendar;


import android.app.Activity;

import android.app.DatePickerDialog;

import android.app.TimePickerDialog;

import android.os.Bundle;

import android.view.View;

import android.widget.DatePicker;

import android.widget.TextView;

import android.widget.TimePicker;


public class SettingTimeDialog extends Activity {

    int mYear, mMonth, mDay, mHour, mMinute;

  //  TextView mTxtDate;

    TextView mTxtTime;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting_time_dialog);


        //텍스트뷰 2개 연결

      //  mTxtDate = (TextView) findViewById(R.id.txtdate);

        mTxtTime = (TextView) findViewById(R.id.txttime);


        //현재 날짜와 시간을 가져오기위한 Calendar 인스턴스 선언

        Calendar cal = new GregorianCalendar();

     //   mYear = cal.get(Calendar.YEAR);

    //    mMonth = cal.get(Calendar.MONTH);

    //    mDay = cal.get(Calendar.DAY_OF_MONTH);

        mHour = cal.get(Calendar.HOUR_OF_DAY);

        mMinute = cal.get(Calendar.MINUTE);


        UpdateNow();//화면에 텍스트뷰에 업데이트 해줌.

    }


    public void mOnClick(View v) {

        switch (v.getId()) {

            //날짜 대화상자 버튼이 눌리면 대화상자를 보여줌

//            case R.id.btnchangedate:
//
//                //여기서 리스너도 등록함
//
//                new DatePickerDialog(SettingTimeDialog.this, mDateSetListener, mYear,
//
//                        mMonth, mDay).show();
//
//                break;


            //시간 대화상자 버튼이 눌리면 대화상자를 보여줌

            case R.id.btnchangetime:

                //여기서 리스너도 등록함

                new TimePickerDialog(getApplicationContext(), mTimeSetListener, mHour,

                        mMinute, false).show();

                break;

        }

    }


    //날짜 대화상자 리스너 부분

//    DatePickerDialog.OnDateSetListener mDateSetListener =
//
//            new DatePickerDialog.OnDateSetListener() {
//
//
//                @Override
//
//                public void onDateSet(DatePicker view, int year, int monthOfYear,
//
//                                      int dayOfMonth) {
//
//                    // TODO Auto-generated method stub
//
//                    //사용자가 입력한 값을 가져온뒤
//
//                    mYear = year;
//
//                    mMonth = monthOfYear;
//
//                    mDay = dayOfMonth;
//
//                    //텍스트뷰의 값을 업데이트함
//
//                    UpdateNow();
//
//                }
//
//            };


    //시간 대화상자 리스너 부분

    TimePickerDialog.OnTimeSetListener mTimeSetListener =

            new TimePickerDialog.OnTimeSetListener() {


                @Override

                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    // TODO Auto-generated method stub

                    //사용자가 입력한 값을 가져온뒤

                    mHour = hourOfDay;

                    mMinute = minute;


                    //텍스트뷰의 값을 업데이트함

                    UpdateNow();


                }

            };


    //텍스트뷰의 값을 업데이트 하는 메소드

    void UpdateNow() {

     //   mTxtDate.setText(String.format("%d/%d/%d", mYear,cmMonth + 1, mDay));

        mTxtTime.setText(String.format("%d:%d", mHour, mMinute));


    }


}



