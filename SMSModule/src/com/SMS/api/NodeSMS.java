package com.SMS.api;

import org.meshpoint.anode.bridge.Env;

import org.meshpoint.anode.java.Base;


public abstract class NodeSMS extends Base {
	private static short classId = Env.getInterfaceId(NodeSMS.class);
	protected NodeSMS() { super(classId); }

	public abstract void sendSMS(String number, String body);
	
	public abstract boolean getStatus();

	
	
}
