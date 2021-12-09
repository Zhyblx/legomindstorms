package com.zhangyibin.power;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * CombatMode(机器人战斗模式)
 * 
 * 描述：机器人走路模式增强版(加入武器系统)。
 * 
 * @author zhangyibin
 *
 */
public class CombatMode {
	private static EV3IRSensor iRSensor = new EV3IRSensor(SensorPort.S1);// 远程传感器
	private static RegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
	private static RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
	private static RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);

	private CombatMode() {
		// 私有化构造
		motorC.setSpeed(PowerInterface.SPEED);// 设置电机C的转速
		motorB.setSpeed(PowerInterface.SPEED + PowerInterface.PARAMETERTUNING);// 设置电机B的转速
		motorA.setSpeed(800);// 设置电机A的转速
	}

	private static CombatMode getCombatMode() {
		return new CombatMode();

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
			if (commandNum == 9) {
				this.token = true;

			}
		}
		return this.token;

	}

	public static CombatMode combatMode = CombatMode.getCombatMode();

	/**
	 * 增强型走路模式
	 * 
	 */
	public static void getCombatModeRun() {
		while (Button.DOWN.isDown() != true) {
			byte[] commandArrays = new byte[4];
			iRSensor.getRemoteCommands(commandArrays, 0, 4);
			for (float commandNum : commandArrays) {
				/*
				 * 机器人向前走动
				 */
				if (commandNum == 1) {
					while (true) {
						motorC.forward();
						Delay.msDelay(PowerInterface.PACE);
						motorC.stop();
						motorB.forward();
						Delay.msDelay(PowerInterface.PACE);
						motorB.stop();
						Delay.msDelay(PowerInterface.PACE);
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
								combatMode.getTokenBoolean();

							}
						};
						if (combatMode.getTokenBoolean()) {
							combatMode.token = false;
							break;

						}
					}
				}
				/*
				 * 机器人向后走动
				 */
				if (commandNum == 3) {
					while (true) {
						motorC.backward();
						Delay.msDelay(PowerInterface.PACE);
						motorC.stop();
						motorB.backward();
						Delay.msDelay(PowerInterface.PACE);
						motorB.stop();
						Delay.msDelay(PowerInterface.PACE);
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
								combatMode.getTokenBoolean();

							}
						};
						if (combatMode.getTokenBoolean()) {
							combatMode.token = false;
							break;

						}

					}
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
				 * 启动武器系统-发射装置
				 */
				if (commandNum == 6) {
					motorA.forward();
					Delay.msDelay(5000);
					motorA.stop();
				}
				/*
				 * 机器人停止行动
				 */
				if (commandNum == 9) {
					motorC.stop();
					motorB.stop();
				}
			}
		}
	}
}