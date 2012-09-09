package com.example.api;

import org.meshpoint.anode.bridge.Env;

import org.meshpoint.anode.java.Base;


public abstract class NodeVibrator extends Base {
	private static short classId = Env.getInterfaceId(NodeVibrator.class);
	protected NodeVibrator() { super(classId); }

	public abstract void vibrate(long ms);
	public abstract void vibratePattern(int pattern[], int repeat);
	
}
