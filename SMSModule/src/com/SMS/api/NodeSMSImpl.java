package com.SMS.api;

import org.meshpoint.anode.AndroidContext;
import org.meshpoint.anode.module.IModule;
import org.meshpoint.anode.module.IModuleContext;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.util.Log;

public class NodeSMSImpl extends NodeSMS implements IModule {

	private Context androidContext;
	Thread t;
	
	boolean enviado;


	/**************************
	 * Module lifecycle methods
	 **************************/
	@Override
	public Object startModule(IModuleContext ctx) {
		androidContext = ((AndroidContext) ctx).getAndroidContext();
		enviado=false;
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
	

	/**************************
	 * NodeSMS methods
	 **************************/

	@Override
	public void sendSMS(String number, String body) {
		enviado = false;
		String SENT = "SMS_SENT";

		PendingIntent sentPI = PendingIntent.getBroadcast(androidContext, 0,
				new Intent(SENT), 0);

		// ---when the SMS has been sent---
		androidContext.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					enviado = true;
					Log.w("myapp", "SMS sent");
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Log.w("myapp", "Generic failure");
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Log.w("myapp", "No service");
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Log.w("myapp", "Null PDU");
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Log.w("myapp", "Radio off");
					break;
				}

			}
		}, new IntentFilter(SENT));

		// ---when the SMS has been delivered---

		SmsManager sm = SmsManager.getDefault();
		// here is where the destination of the text should go
		sm.sendTextMessage(number, null, body, sentPI, null);

		Log.w("myapp", "Fin de envio");

	}

	@Override
	public boolean getStatus() {
		return enviado;
	}

}
