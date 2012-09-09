package com.notification.api;


import org.meshpoint.anode.AndroidContext;
import org.meshpoint.anode.R;
//import com.example.R;
import org.meshpoint.anode.module.IModule;
import org.meshpoint.anode.module.IModuleContext;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;


public class NodeNotificatorImpl extends NodeNotificator implements IModule {

	private Context androidContext;
	NotificationManager mNotificationManager;
	Notification notification;

	/**************************
	 * NodeVibrator methods
	 **************************/

	@Override 
	public void notification(String ticker, String title, String text) {
		Log.w("ANODEEEE", ticker);
		Log.w("ANODEEEE", title);
		Log.w("ANODEEEE", text);
		int icon = R.drawable.notification_image;
    	CharSequence tickerText = ticker;
    	long when = System.currentTimeMillis();

    	notification = new Notification(icon, tickerText, when);
    	
    	CharSequence contentTitle = title;
    	CharSequence contentText = text;
    	
    	notification.setLatestEventInfo(androidContext, contentTitle, contentText, null);
    	
    	int IDENT = 1;

    	mNotificationManager.notify(IDENT, notification);
	}
	

	/**************************
	 * Module lifecycle methods
	 **************************/
	@Override
	public Object startModule(IModuleContext ctx) {
		androidContext = ((AndroidContext)ctx).getAndroidContext();

		
		String ns = Context.NOTIFICATION_SERVICE;
    	mNotificationManager = (NotificationManager) androidContext.getSystemService(ns);
    	
		/*
		 * perform any module initialisation here ...
		 */

		return this;
	}

	@Override
	public void stopModule() {
		/*
		 * perform any module shutdown here ...
		 */
	}

}
