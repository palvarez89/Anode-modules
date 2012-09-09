package com.camera.api;

import org.meshpoint.anode.bridge.Env;

import org.meshpoint.anode.java.Base;


public abstract class NodeCamera extends Base {
	private static short classId = Env.getInterfaceId(NodeCamera.class);
	protected NodeCamera() { super(classId); }

	public abstract void makePhoto();
	
	public abstract String getPath();

	public abstract String getName();

	
	
}
