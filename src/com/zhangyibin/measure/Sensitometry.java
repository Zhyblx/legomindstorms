package com.zhangyibin.measure;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

/**
 * Sensitometry(感光测量)
 * 
 * @author zhangyibin
 *
 */

public class Sensitometry {

	/**
	 * 环境光线值检测
	 * 
	 * 说明：介于0到1之间的数值；0表示很暗，1表示最亮。
	 * 
	 * @return returnAmbientNum(返回环境光线值)
	 */
	public static float getSensitometryRun() {
		EV3ColorSensor eV3ColorSensor = new EV3ColorSensor(SensorPort.S2);
		SensorMode sensorMode = eV3ColorSensor.getAmbientMode();// 该方法表示开启“环境模式”
		float returnAmbientNum = 0;
		float[] floatSensorMode = new float[sensorMode.sampleSize()];
		sensorMode.fetchSample(floatSensorMode, 0);
		for (float ambientNum : floatSensorMode) {
			returnAmbientNum = ambientNum;

		}
		eV3ColorSensor.close();// 关闭传感器
		return returnAmbientNum;// 返回探测值

	}
}
