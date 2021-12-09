package com.zhangyibin.securitycenter;

import com.zhangyibin.information.PageInformation;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

/**
 * 类：Airbags(安全气囊) 描述： 1.当发生系统死循环时，按下触摸传感器(安全气囊)可以退出程序。 2.亦可当成正常的系统关闭按钮使用。
 * 
 * @author zhangyibin
 *
 */

public class Airbags {

	private EV3TouchSensor eV3TouchSensor = new EV3TouchSensor(SensorPort.S3);// 触摸传感器

	/**
	 * 启动安全气囊
	 * 
	 * @author 张益斌
	 */
	public void getAirbags() {
		while (true) {
			try {
				Thread.sleep(1000);

			} catch (Exception e) {
				e.printStackTrace();

			}

			SensorMode sensorMode = eV3TouchSensor.getTouchMode();
			float[] floatSensorMode = new float[sensorMode.sampleSize()];
			sensorMode.fetchSample(floatSensorMode, 0);
			for (float touchNum : floatSensorMode) {
				if (touchNum == 1.0) {
					PageInformation.getGoodBye(); // 系统关闭信息
					eV3TouchSensor.close();// 关闭传感器
					Delay.msDelay(2000);
					System.exit(0); // 关闭程序

				}
			}
		}
	}
}
