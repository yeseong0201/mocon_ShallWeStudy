package com.example.mylock;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.app.AlertDialog;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class AlwaysOnTopService extends Service {
	private TextView mPopupView;							//�׻� ���̰� �� ��
	private WindowManager.LayoutParams mParams;		//layout params ��ü. ���� ��ġ �� ũ�⸦ �����ϴ� ��ü
	private WindowManager mWindowManager;			//������ �Ŵ���
	private SeekBar mSeekBar;								//���� ���� seek bar
	
	private float START_X, START_Y;							//�����̱� ���� ��ġ�� ���� ��
	private int PREV_X, PREV_Y;								//�����̱� ������ �䰡 ��ġ�� ��
	private int MAX_X = -1, MAX_Y = -1;					//���� ��ġ �ִ� ��
	
	private OnTouchListener mViewTouchListener = new OnTouchListener() {
		@Override public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:				//����� ��ġ �ٿ��̸�
					if(MAX_X == -1)
						setMaxPosition();
					START_X = event.getRawX();					//��ġ ���� ��
					START_Y = event.getRawY();					//��ġ ���� ��
					PREV_X = mParams.x;							//���� ���� ��
					PREV_Y = mParams.y;							//���� ���� ��
					break;
				case MotionEvent.ACTION_MOVE:
					int x = (int)(event.getRawX() - START_X);	//�̵��� �Ÿ�
					int y = (int)(event.getRawY() - START_Y);	//�̵��� �Ÿ�
					
					//��ġ�ؼ� �̵��� ��ŭ �̵� ��Ų��
					mParams.x = PREV_X + x;
					mParams.y = PREV_Y + y;
					
					optimizePosition();		//���� ��ġ ����ȭ
					mWindowManager.updateViewLayout(mPopupView, mParams);	//�� ������Ʈ
					break;
			}
			
			return true;
		}
	};
	
	@Override
	public IBinder onBind(Intent arg0) { return null; }
	
	@Override
	public void onCreate() {
		super.onCreate();

		mPopupView = new TextView(this);																//�� ����
		mPopupView.setText("�� ��� �׻� ���� �ִ�.\n������ & ��Ƽ�ӽ� �˾� ��� ����");	//�ؽ�Ʈ ����
		mPopupView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);								//�ؽ�Ʈ ũ�� 18sp
		mPopupView.setTextColor(Color.BLUE);															//���� ����
		mPopupView.setBackgroundColor(Color.argb(127, 0, 255, 255));								//�ؽ�Ʈ�� ��� ��
		
		mPopupView.setOnTouchListener(mViewTouchListener);										//�˾��信 ��ġ ������ ���

		//�ֻ��� �����쿡 �ֱ� ���� ����
		mParams = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.TYPE_PHONE,					//�׻� �� ������ �ְ�. status bar �ؿ� ����. ��ġ �̺�Ʈ ���� �� ����.
			WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,		//�� �Ӽ��� ���ָ� ��ġ & Ű �̺�Ʈ�� �԰� �ȴ�. 
																					//��Ŀ���� ���༭ �ڱ� ���� ����ġ�� �ν� ���ϰ� Ű�̺�Ʈ�� ������� �ʰ� ����
			PixelFormat.TRANSLUCENT);										//����
		mParams.gravity = Gravity.LEFT | Gravity.TOP;						//���� ��ܿ� ��ġ�ϰ� ��.
		
		mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);	//������ �Ŵ��� �ҷ���.
		mWindowManager.addView(mPopupView, mParams);		//�ֻ��� �����쿡 �� �ֱ�. *�߿� : ���⿡ permission�� �̸� ������ �ξ�� �Ѵ�. �Ŵ��佺Ʈ��
		
		addOpacityController();		//�˾� ���� ���� �����ϴ� ��Ʈ�ѷ� �߰�
	}
	
	/**
	 * ���� ��ġ�� ȭ�� �ȿ� �ְ� �ִ밪�� �����Ѵ�
	 */
	private void setMaxPosition() {
		DisplayMetrics matrix = new DisplayMetrics();
		mWindowManager.getDefaultDisplay().getMetrics(matrix);		//ȭ�� ������ �����ͼ�
		
		MAX_X = matrix.widthPixels - mPopupView.getWidth();			//x �ִ밪 ����
		MAX_Y = matrix.heightPixels - mPopupView.getHeight();			//y �ִ밪 ����
	}
	
	/**
	 * ���� ��ġ�� ȭ�� �ȿ� �ְ� �ϱ� ���ؼ� �˻��ϰ� �����Ѵ�.
	 */
	private void optimizePosition() {
		//�ִ밪 �Ѿ�� �ʰ� ����
		if(mParams.x > MAX_X) mParams.x = MAX_X;
		if(mParams.y > MAX_Y) mParams.y = MAX_Y;
		if(mParams.x < 0) mParams.x = 0;
		if(mParams.y < 0) mParams.y = 0;
	}
	
	/**
	 * ���İ� �����ϴ� ��Ʈ�ѷ��� �߰��Ѵ�
	 */
	private void addOpacityController() {
		mSeekBar = new SeekBar(this);		//���� ���� seek bar
		mSeekBar.setMax(100);					//�ƽ� �� ����.
		mSeekBar.setProgress(100);			//���� ���� ����. 100:������, 0�� ���� ����
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override public void onStopTrackingTouch(SeekBar seekBar) {}
			@Override public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override public void onProgressChanged(SeekBar seekBar, int progress,	boolean fromUser) {
				mParams.alpha = progress / 100.0f;			//���İ� ����
				mWindowManager.updateViewLayout(mPopupView, mParams);	//�˾� �� ������Ʈ
			}
		});
		
		//�ֻ��� �����쿡 �ֱ� ���� ����
		WindowManager.LayoutParams params = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.MATCH_PARENT,
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.TYPE_PHONE,					//�׻� �� ������ �ְ�. status bar �ؿ� ����. ��ġ �̺�Ʈ ���� �� ����.
			WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,		//�� �Ӽ��� ���ָ� ��ġ & Ű �̺�Ʈ�� �԰� �ȴ�. 
																					//��Ŀ���� ���༭ �ڱ� ���� ����ġ�� �ν� ���ϰ� Ű�̺�Ʈ�� ������� �ʰ� ����
			PixelFormat.TRANSLUCENT);										//����
		params.gravity = Gravity.LEFT | Gravity.TOP;							//���� ��ܿ� ��ġ�ϰ� ��.
		
		mWindowManager.addView(mSeekBar, params);
	}

	/**
	 * ���� / ���� ��� ���� �� �ִ밪 �ٽ� ������ �־�� ��.
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		setMaxPosition();		//�ִ밪 �ٽ� ����
		optimizePosition();		//�� ��ġ ����ȭ
	}
	
	@Override
	public void onDestroy() {
		if(mWindowManager != null) {		//���� ����� �� ����. *�߿� : �並 �� ���� �ؾ���.
			if(mPopupView != null) mWindowManager.removeView(mPopupView);
			if(mSeekBar != null) mWindowManager.removeView(mSeekBar);
		}
		super.onDestroy();
	}
}