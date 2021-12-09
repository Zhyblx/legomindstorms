package com.zhangyibin.power;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * RunningMode(机器人跑步模式) 描述：正常情况下跑步模式；
 * 
 * @author zhangyibin
 *
 */

public class RunningMode {

	private static EV3IRSensor iRSensor = new EV3IRSensor(SensorPort.S1);// 远程传感器
	private static RegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
	private static RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);

	/**
	 * 跑步模式
	 */
	public static void getRunningModeRun() {
		while (true) {
			motorC.setSpeed(PowerInterface.SPEED);// 设置电机C的转速
			motorB.setSpeed(PowerInterface.SPEED);// 设置电机B的转速

			while (Button.DOWN.isDown() != true) {
				byte[] commandArrays = new byte[4];
				iRSensor.getRemoteCommands(commandArrays, 0, 4);
				for (float commandNum : commandArrays) {
					if (commandNum != 0) {
						if (commandNum == 1) {
							/*
							 * forward:前进
							 */
							motorC.forward();
							motorB.forward();

						} else if (commandNum == 3) {
							/*
							 * backward:后退
							 */
							motorC.backward();
							motorB.backward();

						} else if (commandNum == 9) {
							/*
							 * stop:停止
							 */
							motorC.stop();
							motorB.stop();

						} else if (commandNum == 2) {
							/*
							 * resetTachoCount: 重置角度 rotate：转向角度>200度(C端口是向左)
							 */
							motorC.resetTachoCount();
							motorC.rotate(PowerInterface.ANGLE);

						} else if (commandNum == 4) {
							/*
							 * resetTachoCount: 重置角度 rotate：转向角度>200度(B端口是向右)
							 */
							motorB.resetTachoCount();
							motorB.rotate(PowerInterface.ANGLE);

						} else if (commandNum == 5) {
							/*
							 * setSpeed:设置电机转速
							 */
							motorC.setSpeed(500);
							motorB.setSpeed(500);

						} else if (commandNum == 8) {
							/*
							 * setSpeed:设置电机转速
							 */
							motorC.setSpeed(300);
							motorB.setSpeed(300);

						}
					}
				}
				Delay.msDelay(1000);// 延迟1秒

			}
			iRSensor.close();// 关闭传感器
			System.exit(0);
			break;

		}
	}
}
