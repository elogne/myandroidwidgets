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

	final Button btnSend = (Button) findViewById(R.id.btnStart);
	btnSend.setId(0);
	btnSend.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View v) {
		Button btn = (Button) v;
		if (btn.getId() == 0) {
		    isStopped = false;
		    btn.setId(1);
		    btn.setText("Cancel notification");
		    startNotification();
		} else if (btn.getId() == 1) {
		    btn.setId(0);
		    btn.setText("Send notification");
		    isStopped = true;
		    stopNotification();
		}
	    }
	});

	nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void startNotification() {

	notification = new Notification();

	Intent notificationIntent = new Intent();
	// Use the UPDATE_CURRENT FLAG for this notification since, we would be
	// changing the iconLevel of
	PendingIntent contentIntent = PendingIntent.getActivity(this,
		(int) System.currentTimeMillis(), notificationIntent,
		PendingIntent.FLAG_UPDATE_CURRENT);

	RemoteViews contentView = new RemoteViews(getPackageName(),
		R.layout.notify);

	notification.contentView = contentView;
	notification.contentIntent = contentIntent;

	notification.flags = Notification.FLAG_ONGOING_EVENT;
	notification.icon = R.drawable.level_list;

	notification.iconLevel = 0;

	nManager.notify(R.string.app_name, notification);

	Thread thread = new Thread(this);
	thread.start();
    }

    private void stopNotification() {
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
	    if (notification.iconLevel < 3) {
		notification.iconLevel++;
	    } else {
		notification.iconLevel = 0;
	    }
	    if (!isStopped) {
		nManager.notify(R.string.app_name, notification);
	    } else {
		nManager.cancel(R.string.app_name);
	    }
	    if (!isStopped) {
		nManager.notify(R.string.app_name, notification);
	    } else {
		nManager.cancel(R.string.app_name);
	    }
	    super.handleMessage(msg);
	}

    };

}