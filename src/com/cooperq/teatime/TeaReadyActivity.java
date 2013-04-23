package com.cooperq.teatime;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class TeaReadyActivity extends Activity {
	Ringtone r;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
		setContentView(R.layout.activity_tea_ready);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tea_ready, menu);
		return true;
	}
	
	@Override
	public void onStop(){
		super.onStop();
		r.stop();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,MainActivity.class);
		startActivity(i);
		r.stop();
	}
	
	public void stopAlarm(View view){
		Intent i = new Intent(this,MainActivity.class);
		startActivity(i);
		r.stop();
	}

}
