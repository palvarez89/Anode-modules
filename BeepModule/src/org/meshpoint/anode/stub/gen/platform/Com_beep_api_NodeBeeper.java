/* This file has been automatically generated; do not edit */

package org.meshpoint.anode.stub.gen.platform;

public class Com_beep_api_NodeBeeper {

	private static Object[] __args = new Object[1];

	public static Object[] __getArgs() { return __args; }

	static Object __invoke(com.beep.api.NodeBeeper inst, int opIdx, Object[] args) {
		inst.beep(
			(int)((org.meshpoint.anode.js.JSValue)args[0]).longValue
		);
		return null;
	}

}
