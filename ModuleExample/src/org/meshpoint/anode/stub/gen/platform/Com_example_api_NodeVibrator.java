/* This file has been automatically generated; do not edit */

package org.meshpoint.anode.stub.gen.platform;

public class Com_example_api_NodeVibrator {

	private static Object[] __args = new Object[2];

	public static Object[] __getArgs() { return __args; }

	static Object __invoke(com.example.api.NodeVibrator inst, int opIdx, Object[] args) {
		Object result = null;
		switch(opIdx) {
		case 0: /* vibrate */
			inst.vibrate(
				((org.meshpoint.anode.js.JSValue)args[0]).longValue
			);
			break;
		case 1: /* vibratePattern */
			inst.vibratePattern(
				(int[])args[0],
				(int)((org.meshpoint.anode.js.JSValue)args[1]).longValue
			);
			break;
		default:
		}
		return result;
	}

}
