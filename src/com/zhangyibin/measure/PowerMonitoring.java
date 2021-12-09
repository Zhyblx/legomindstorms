package com.zhangyibin.measure;

import lejos.hardware.Battery;

/**
 * PowerMonitoring (机器人电力监控)
 * 
 * @author zhangyibin
 *
 */

public class PowerMonitoring {

	/**
	 * 电池容量检测
	 * 
	 * 说明：以 mV 为单位的电池电压。~9000 = 满。
	 * 
	 * @return batteryCapacity (返回电池容量)
	 */
	public static int getBatteryCapacityRun() {
		int batteryCapacity = Battery.getVoltageMilliVolt();
		return batteryCapacity;

	}

}
