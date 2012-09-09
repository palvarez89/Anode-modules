package com.contacts.api;

import org.meshpoint.anode.bridge.Env;

import org.meshpoint.anode.java.Base;


public abstract class NodeContacts extends Base {
	private static short classId = Env.getInterfaceId(NodeContacts.class);
	protected NodeContacts() { super(classId); }

	public abstract int size();
	public abstract String getName(int index);
	public abstract String[] getNumbers(int index);

	
	
}
