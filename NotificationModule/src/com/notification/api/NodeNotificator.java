package com.notification.api;

import org.meshpoint.anode.bridge.Env;

import org.meshpoint.anode.java.Base;


public abstract class NodeNotificator extends Base {
	private static short classId = Env.getInterfaceId(NodeNotificator.class);
	protected NodeNotificator() { super(classId); }

	public abstract void notification(String ticker, String title, String text);

	
	
}
