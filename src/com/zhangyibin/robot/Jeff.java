package com.zhangyibin.robot;

import com.zhangyibin.power.PowerInterface;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * Jeff(机器人“杰夫”)
 * 
 * 描述：2021年11月13日看了一本触动你心弦的电影《芬奇-2021》它讲述了在地球的末日时代，
 * 一位名叫芬奇的工程师自知自己的生命即将走到尽头，于是创造了机器人Jeff来照顾他的伙伴“狗”。
 * 基于这个背景我想通过lego-EV3的开发组件创造一台可以陪伴我家人“招财”、“进宝”、“荷花”游戏的Jeff机器人。
 * 
 * 
 * @author zhangyibin
 *
 */

public class Jeff {

	private static EV3IRSensor iRSensor = new EV3IRSensor(SensorPort.S1);// 远程传感器
	private static RegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
	private static RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
	private static RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A); // 武器系统(激光笔)

	private Jeff() {
		// 私有化构造
		motorC.setSpeed(PowerInterface.JEFFSPEED);// 设置电机C的转速
		motorB.setSpeed(PowerInterface.JEFFSPEED + PowerInterface.PARAMETERTUNING);// 设置电机B的转速
		motorA.setSpeed(PowerInterface.JEFFARMSSPEED);// 设置电机武器系统(激光笔)A的转速

	}

	private static Jeff getJeff() {
		return new Jeff();

	}
	/*
	 * 判断机器人是否中断行动；
	 * 
	 * 说明：作用于监控机器人[向前或向后]行动的中断(或停止)指令。
	 * 
	 * 1.token是指令-令牌；默认情况下是false.
	 * 
	 * 2.当监控-收集来自信标[标号9]的指令，那么token令牌值变更为true.
	 * 
	 */

	private Boolean token = false;

	public boolean getTokenBoolean() {
		byte[] commandArrays = new byte[4];
		iRSensor.getRemoteCommands(commandArrays, 0, 4);
		for (float commandNum : commandArrays) {
			if (commandNum == 6) {
				this.token = true;

			}
		}
		return this.token;

	}

	/**
	 * Jeff机器人的逗猫模式； 模式一：[标号1]
	 * 
	 */
	public static Jeff jeff = Jeff.getJeff();

	public static void getJeffFunnyCatModel() {
		while (Button.DOWN.isDown() != true) {
			byte[] commandArrays = new byte[4];
			iRSensor.getRemoteCommands(commandArrays, 0, 4);
			for (float commandNum : commandArrays) {

				// 1-前
				if (commandNum == 1) {
					motorC.forward();
					Delay.msDelay(PowerInterface.PACE);
					motorC.stop();
					motorB.forward();
					Delay.msDelay(PowerInterface.PACE);
					motorB.stop();

				}
				// 2-后
				if (commandNum == 2) {
					motorC.backward();
					Delay.msDelay(PowerInterface.PACE);
					motorC.stop();
					motorB.backward();
					Delay.msDelay(PowerInterface.PACE);
					motorB.stop();

				}
				// 3-左
				if (commandNum == 3) {
					motorC.resetTachoCount();
					motorC.rotateTo(PowerInterface.JEFFANGLE_180);
					Delay.msDelay(PowerInterface.PACE);
					motorC.stop();

				}
				// 4-右
				if (commandNum == 4) {
					motorB.resetTachoCount();
					motorB.rotateTo(PowerInterface.JEFFANGLE_180);
					Delay.msDelay(PowerInterface.PACE);
					motorB.stop();

				}

				/*
				 * 模式一：
				 */
				if (commandNum == 5) {
					//晃动激光笔10秒
					motorA.forward();
					Delay.msDelay(10000);
					motorA.stop();
					Delay.msDelay(1000);
					
					// 向右转180度
					motorB.resetTachoCount();
					motorB.rotateTo(PowerInterface.JEFFANGLE_180);
					Delay.msDelay(1000);
					motorB.stop();
					Delay.msDelay(1000);
					
					//晃动激光笔60秒
					motorA.forward();
					Delay.msDelay(10000);
					motorA.stop();
					Delay.msDelay(1000);
					
					while (true) {
						// 向左转270度
						// 打开激光笔
						
						motorA.forward();
						Delay.msDelay(2000);
						motorA.stop();
						
						motorC.resetTachoCount();
						motorC.rotateTo(PowerInterface.JEFFANGLE_270);
						Delay.msDelay(1000);
						motorC.stop();
						
						
						
						
						// 向右转270度
						// 打开激光笔
						motorA.forward();
						Delay.msDelay(2000);
						motorA.stop();
						
						motorB.resetTachoCount();
						motorB.rotateTo(PowerInterface.JEFFANGLE_270);
						Delay.msDelay(1000);
						motorB.stop();
						
						/*
						 * 指令-令牌判断为true时：
						 * 
						 * 1.令牌指令值重置为false。
						 * 
						 * 2.跳出循环。
						 * 
						 */
						new Thread() {
							@Override
							public void run() {
								jeff.getTokenBoolean();

							}
						};
						if (jeff.getTokenBoolean()) {
							jeff.token = false;
							motorA.stop();
							break;

						}
					}
				}

				/*
				 * 机器人停止行动
				 */
				if (commandNum == 6) {
					motorC.stop();
					motorB.stop();

				}
			}
		}
	}
}
