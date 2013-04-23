package com.cooperq.teatime;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends Activity {
	protected NotificationCompat.Builder mBuilder;
	protected NotificationManager mNotifyMgr;
	protected int mNotificationId = 666;	
	protected TeaTimer timer;
	protected TextView textView;
	public final long DEFAULT_TIME = 30000;
	private static Intent teaReadyIntent;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //Get the message from the Intent
        
		setContentView(R.layout.activity_display_message);
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
        Intent intent = getIntent();
        int teaId = intent.getIntExtra(MainActivity.TEA_ID, 0);
		startTimer(teaId);
	}
	
	protected void startTimer(int teaId){
		//Building up a new intent to send to the notification
    	Intent intent2 = new Intent(this,DisplayMessageActivity.class);
    	PendingIntent resultPendingIntent =
    		    PendingIntent.getActivity(
    		    this,
    		    0,
    		    intent2,
    		    PendingIntent.FLAG_UPDATE_CURRENT
    		);
    	//build a notification
    	mBuilder =
    		    new NotificationCompat.Builder(this)
    		    .setSmallIcon(R.drawable.ic_launcher)
    		    .setContentTitle("Tea Timer")
    		    .setContentText("Preparing timer...")
    		    .setContentIntent(resultPendingIntent);
    	// Gets an instance of the NotificationManager service
    	mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    	// Builds the notification and issues it.
    	mNotifyMgr.notify(mNotificationId, mBuilder.build());
    	
		timer = TeaTimer.getTeaTimer(this.DEFAULT_TIME, 1000, this);	
	}
	
	public void cancelTimer(View view){
		mNotifyMgr.cancel(mNotificationId);
		timer.destroy();
		raiseToast(getString(R.string.timer_cancelled));
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);

	}
	
	protected void raiseToast(String str){
		Context context = getApplicationContext();
		CharSequence text = str;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
				getActionBar().setDisplayHomeAsUpEnabled(true);
			}
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@SuppressLint("DefaultLocale")
	public void onTick(long millisUntilFinished) {
		long seconds = (millisUntilFinished / 1000) % 60;
		long minutes = (millisUntilFinished / 1000) / 60;
		String clock = String.format("%02d:%02d",minutes,seconds);
		mBuilder.setContentText(clock + " remaining");
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
        TextView textView = (TextView)findViewById(R.id.textView1);
        textView.setText(clock);    	         
    }

    public void onFinish() {
    	raiseToast("on finished called!");	
        mBuilder.setContentText("Your tea is ready!");
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
        
        teaReadyIntent = new Intent(this,TeaReadyActivity.class);
        teaReadyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplication().startActivity(teaReadyIntent);
       
    }
    

}
