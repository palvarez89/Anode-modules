package com.beep.api;

import org.meshpoint.anode.bridge.Env;

import org.meshpoint.anode.java.Base;


public abstract class NodeBeeper extends Base {
	private static short classId = Env.getInterfaceId(NodeBeeper.class);
	protected NodeBeeper() { super(classId); }

	public abstract void beep(int times);

	
	
}
