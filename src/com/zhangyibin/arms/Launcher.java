package com.zhangyibin.arms;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * arms(武器系统)
 * 
 * Launcher(发射装置)
 * 
 * @author zhangyibin
 *
 */

public class Launcher {
	private static EV3IRSensor iRSensor = new EV3IRSensor(SensorPort.S1);// 远程传感器
	private static RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);

	/**
	 * 启动发射装置
	 * 
	 * @author zhangyibin
	 */
	public static void getLauncherRun() {
		while (true) {
			motorA.setSpeed(800);// 设置电机A的转速
			byte[] commandArrays = new byte[4];
			iRSensor.getRemoteCommands(commandArrays, 0, 4);
			for (float commandNum : commandArrays) {
				if (commandNum == 6) {
					motorA.forward();// 启动电机转动
					Delay.msDelay(5000);// 电机执行5秒
					motorA.stop();// 停止电机转动

				}
			}
		}
	}

}
