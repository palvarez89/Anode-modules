package com.acceleration.api;

import org.meshpoint.anode.bridge.Env;

import org.meshpoint.anode.java.Base;

public abstract class NodeAcceleration extends Base {
	private static short classId = Env.getInterfaceId(NodeAcceleration.class);

	protected NodeAcceleration() {
		super(classId);
	}

	public abstract int getX();

	public abstract int getY();

	public abstract int getZ();

}
