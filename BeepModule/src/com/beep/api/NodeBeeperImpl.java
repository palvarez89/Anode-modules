package com.beep.api;

import org.meshpoint.anode.AndroidContext;
import org.meshpoint.anode.module.IModule;
import org.meshpoint.anode.module.IModuleContext;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

public class NodeBeeperImpl extends NodeBeeper implements IModule {

	private Context androidContext;
	int beepTimes = 0;
	Thread t;
	Uri soundUri;
	Ringtone ring;
	int duracion;

	/**************************
	 * NodeVibrator methods
	 **************************/

	@Override
	public void beep(int times) {
		beepTimes = times;
		Log.w("ANODEEEE", "va a sonar: " + beepTimes);

		BeepThread beeps = new BeepThread(times, duracion, ring);
		beeps.start();
	}

	class BeepThread extends Thread {
		int times;
		int duration;
		Ringtone ringer;

		BeepThread(int tim, int dur, Ringtone ring) {
			times = tim;
			duration = dur;
			ringer = ring;
		}

		public void run() {
			int countBeep = 0;
			while (countBeep < times) {
				Log.w("ANODEEEE", "Bucle: " + countBeep);
				ringer.stop();
				ringer.play();
				countBeep += 1;

				try {
					Thread.sleep(duration + 200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	/**************************
	 * Module lifecycle methods
	 **************************/
	@Override
	public Object startModule(IModuleContext ctx) {
		androidContext = ((AndroidContext) ctx).getAndroidContext();
		soundUri = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		MediaPlayer player = null;
		if (soundUri == null) {
			// alert is null, using backup
			soundUri = RingtoneManager
					.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
			if (soundUri == null) { // I can't see this ever being null (as
									// always have a default notification) but
									// just incase
				// alert backup is null, using 2nd backup
				soundUri = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_ALARM);
			}
		}
		ring = RingtoneManager.getRingtone(androidContext, soundUri);

		player = MediaPlayer.create(androidContext, soundUri);
		duracion = player.getDuration();

		return this;
	}

	@Override
	public void stopModule() {
		/*
		 * perform any module shutdown here ...
		 */
	}

}
