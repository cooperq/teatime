package com.cooperq.teatime;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public final static String TEA_ID = "tea_id";
	protected NotificationCompat.Builder mBuilder;
	protected NotificationManager mNotifyMgr;
	int mNotificationId = 666;
	CountDownTimer timer;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
        setContentView(R.layout.activity_main);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
        	@Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        		
        		sendMessage(v, (int) id);
            }

        });
    }
	
	protected void raiseToast(String str){
		Context context = getApplicationContext();
		CharSequence text = str;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void sendMessage(View view, int tea_id){
    	//respond to button press
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	intent.putExtra(TEA_ID, tea_id);
    	//start the display message activity, passing in the intent
    	startActivity(intent);
    }
    
    
    
}
