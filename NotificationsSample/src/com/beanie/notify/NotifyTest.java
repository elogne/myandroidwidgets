package com.beanie.notify;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class NotifyTest extends Activity implements Runnable {

    private Notification notification;
    private NotificationManager nManager;
    private boolean isStopped = false;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);

	// Button that handles the starting and stopping of the notification
	final Button btnSend = (Button) findViewById(R.id.btnStart);
	btnSend.setId(0);
	btnSend.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View v) {
		Button btn = (Button)v;
		if(btn.getId() == 0){
		    isStopped = false;
		    btn.setId(1);
		    btn.setText("Cancel notification");
		    startNotification();
		}else if(btn.getId() == 1){
		    btn.setId(0);
		    btn.setText("Send notification");
		    isStopped = true;
		    stopNotification();
		}
	    }
	});

	nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
    
    private void startNotification(){
	
	notification = new Notification();
	
	Intent notificationIntent = new Intent();
	
	PendingIntent contentIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(),
		notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

	// We are using the custom view for our notification instead of the setLatestEventInfo method
	RemoteViews contentView = new RemoteViews(getPackageName(),
		R.layout.notify);

	notification.contentView = contentView;

	// Since we do not use the setLatestEventInfo, we have to set the intent like this
	notification.contentIntent = contentIntent;

	// Set the notification type to Ongoing Event, so that you will not see the Clear button for
	// this notification
	notification.flags = Notification.FLAG_ONGOING_EVENT;
	
	// Set the notification icon, which is actually the LevelListDrawable that you have created
	notification.icon = R.drawable.level_list;

	// Set the initial iconLevel to 0
	notification.iconLevel = 0;

	nManager.notify(R.string.app_name, notification);

	Thread thread = new Thread(this);
	thread.start();
    }
    
    private void stopNotification(){
	nManager.cancel(R.string.app_name);
    }

    @Override
    public void run() {
	while (!isStopped) {
	    try {
		Thread.sleep(100);
		handler.sendEmptyMessage(1);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    private Handler handler = new Handler() {

	@Override
	public void handleMessage(Message msg) {
	    // Change the notification level as required
	    if (notification.iconLevel < 3) {
		notification.iconLevel++;
	    } else {
		notification.iconLevel = 0;
	    }
	    super.handleMessage(msg);
	}

    };

}