/* This file has been automatically generated; do not edit */

package org.meshpoint.anode.stub.gen.platform;

public class Com_acceleration_api_NodeAcceleration {

	private static Object[] __args = new Object[0];

	public static Object[] __getArgs() { return __args; }

	static Object __invoke(com.acceleration.api.NodeAcceleration inst, int opIdx, Object[] args) {
		Object result = null;
		switch(opIdx) {
		case 0: /* getX */
			result = org.meshpoint.anode.js.JSValue.asJSNumber((long)inst.getX());
			break;
		case 1: /* getY */
			result = org.meshpoint.anode.js.JSValue.asJSNumber((long)inst.getY());
			break;
		case 2: /* getZ */
			result = org.meshpoint.anode.js.JSValue.asJSNumber((long)inst.getZ());
			break;
		default:
		}
		return result;
	}

}
