package com.acceleration.api;

import org.meshpoint.anode.AndroidContext;
import org.meshpoint.anode.module.IModule;
import org.meshpoint.anode.module.IModuleContext;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class NodeAccelerationImpl extends NodeAcceleration implements IModule {
	String TAG = "ANODEEEE";
	double ax, ay, az; // these are the acceleration in x,y and z axis
	private SensorManager sensorManager;
	SensorEventListener al;
	private Context androidContext;

	/**************************
	 * Module lifecycle methods
	 **************************/
	@Override
	public Object startModule(IModuleContext ctx) {
		Log.w(TAG, "Cargando modulo acelerometro");

		androidContext = ((AndroidContext) ctx).getAndroidContext();
		al = new myAccelerometerListener();
		sensorManager = (SensorManager) androidContext
				.getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(al,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);

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
	 * NodeAcceleration methods
	 **************************/

	@Override
	public int getX() {
		return (int) (ax * 10000);
	}

	@Override
	public int getY() {
		return (int) (ay * 10000);
	}

	@Override
	public int getZ() {
		return (int) (az * 10000);
	}

	class myAccelerometerListener implements SensorEventListener {
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				ax = event.values[0];
				ay = event.values[1];
				az = event.values[2];
			}

		}
	}

}
