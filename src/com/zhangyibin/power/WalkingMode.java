package com.zhangyibin.power;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * WalkingMode(机器人走路模式)
 * 
 * @author zhangyibin
 *
 */
public class WalkingMode {
	private static EV3IRSensor iRSensor = new EV3IRSensor(SensorPort.S1);// 远程传感器
	private static RegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
	private static RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);

	/**
	 * 走路模式
	 * 
	 */
	public static void getWalkingModeRun() {
		while (true) {
			motorC.setSpeed(PowerInterface.SPEED);// 设置电机C的转速
			motorB.setSpeed(PowerInterface.SPEED);// 设置电机B的转速

			while (Button.DOWN.isDown() != true) {
				byte[] commandArrays = new byte[4];
				iRSensor.getRemoteCommands(commandArrays, 0, 4);
				for (float commandNum : commandArrays) {
					/*
					 * 机器人向前走动
					 */
					if (commandNum == 1) {
						motorC.forward();
						Delay.msDelay(PowerInterface.PACE);
						motorC.stop();
						motorB.forward();
						Delay.msDelay(PowerInterface.PACE);
						motorB.stop();

					}

					/*
					 * 机器人向后走动
					 */
					if (commandNum == 3) {
						motorC.backward();
						Delay.msDelay(PowerInterface.PACE);
						motorC.stop();
						motorB.backward();
						Delay.msDelay(PowerInterface.PACE);
						motorB.stop();

					}

					/*
					 * 机器人向左转
					 */
					if (commandNum == 2) {
						motorC.resetTachoCount();
						motorC.rotate(PowerInterface.ANGLE);

					}

					/*
					 * 机器人向右转
					 */
					if (commandNum == 4) {
						motorB.resetTachoCount();
						motorB.rotate(PowerInterface.ANGLE);

					}

					/*
					 * 机器人停止行动
					 */
					if (commandNum == 9) {
						motorC.stop();
						motorB.stop();

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
