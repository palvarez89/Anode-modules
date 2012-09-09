/* This file has been automatically generated; do not edit */

package org.meshpoint.anode.stub.gen.platform;

public class Com_camera_api_NodeCamera {

	private static Object[] __args = new Object[0];

	public static Object[] __getArgs() { return __args; }

	static Object __invoke(com.camera.api.NodeCamera inst, int opIdx, Object[] args) {
		Object result = null;
		switch(opIdx) {
		case 0: /* getName */
			result = inst.getName();
			break;
		case 1: /* getPath */
			result = inst.getPath();
			break;
		case 2: /* makePhoto */
			inst.makePhoto();
			break;
		default:
		}
		return result;
	}

}
