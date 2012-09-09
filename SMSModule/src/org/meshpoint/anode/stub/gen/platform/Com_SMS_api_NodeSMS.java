/* This file has been automatically generated; do not edit */

package org.meshpoint.anode.stub.gen.platform;

public class Com_SMS_api_NodeSMS {

	private static Object[] __args = new Object[2];

	public static Object[] __getArgs() { return __args; }

	static Object __invoke(com.SMS.api.NodeSMS inst, int opIdx, Object[] args) {
		Object result = null;
		switch(opIdx) {
		case 0: /* getStatus */
			result = org.meshpoint.anode.js.JSValue.asJSBoolean(inst.getStatus());
			break;
		case 1: /* sendSMS */
			inst.sendSMS(
				(String)args[0],
				(String)args[1]
			);
			break;
		default:
		}
		return result;
	}

}
