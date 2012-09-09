package com.example.api;


import org.meshpoint.anode.AndroidContext;
import org.meshpoint.anode.module.IModule;
import org.meshpoint.anode.module.IModuleContext;

import android.content.Context;
import android.os.Vibrator;

public class NodeVibratorImpl extends NodeVibrator implements IModule {

	private Context androidContext;
	private Vibrator vibrator;

	/**************************
	 * NodeVibrator methods
	 **************************/

	@Override
	public void vibrate(long ms) {
		vibrator.vibrate(ms);
	}
	
	@Override
	public void vibratePattern(int pattern[], int repeat) {
		long array[] = new long[pattern.length+1];
		array[0]=0;
		for (int i=0;i<pattern.length;i++){
			array[i+1]=pattern[i];
		}
		vibrator.vibrate(array, -1);
	}

	/**************************
	 * Module lifecycle methods
	 **************************/
	@Override
	public Object startModule(IModuleContext ctx) {
		androidContext = ((AndroidContext)ctx).getAndroidContext();
		/*
		 * perform any module initialisation here ...
		 */
		vibrator = (Vibrator)androidContext.getSystemService(Context.VIBRATOR_SERVICE);
		return this;
	}

	@Override
	public void stopModule() {
		/*
		 * perform any module shutdown here ...
		 */
	}

}
