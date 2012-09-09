/* This file has been automatically generated; do not edit */

package org.meshpoint.anode.stub.gen.platform;

public class Com_contacts_api_NodeContacts {

	private static Object[] __args = new Object[1];

	public static Object[] __getArgs() { return __args; }

	static Object __invoke(com.contacts.api.NodeContacts inst, int opIdx, Object[] args) {
		Object result = null;
		switch(opIdx) {
		case 0: /* getName */
			result = inst.getName(
				(int)((org.meshpoint.anode.js.JSValue)args[0]).longValue
			);
			break;
		case 1: /* getNumbers */
			result = inst.getNumbers(
				(int)((org.meshpoint.anode.js.JSValue)args[0]).longValue
			);
			break;
		case 2: /* size */
			result = org.meshpoint.anode.js.JSValue.asJSNumber((long)inst.size());
			break;
		default:
		}
		return result;
	}

}
