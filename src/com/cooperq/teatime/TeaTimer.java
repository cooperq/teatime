package com.cooperq.teatime;

import android.os.CountDownTimer;

public class TeaTimer extends CountDownTimer {
	private static TeaTimer teaTimer;
	private static DisplayMessageActivity activity;
	
	private TeaTimer(long millisInFuture, long countDownInterval, DisplayMessageActivity activity) {
		super(millisInFuture, countDownInterval);
	}
	
	public static TeaTimer getTeaTimer(long millisInFuture, long countDownInterval, DisplayMessageActivity activity){
		if(teaTimer == null){
			teaTimer = new TeaTimer(millisInFuture, countDownInterval, activity);
	    	teaTimer.start();

		}
		TeaTimer.activity = activity;
		return teaTimer;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		TeaTimer.activity.onTick(millisUntilFinished);	 
	}
	
	@Override
	public void onFinish() {
		teaTimer = null;
		TeaTimer.activity.onFinish();
	}
	
	public void destroy(){
		teaTimer.cancel();
		teaTimer = null;
	}

}
