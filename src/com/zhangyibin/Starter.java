package com.zhangyibin;

import com.zhangyibin.information.PageInformation;
import com.zhangyibin.measure.PowerMonitoring;
import com.zhangyibin.measure.Sensitometry;
import com.zhangyibin.mood.EV3LED;
import com.zhangyibin.power.CombatMode;
import com.zhangyibin.robot.Jeff;
import com.zhangyibin.securitycenter.Airbags;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

/**
 * Starter(程序启动器)
 * 
 * @author zhangyibin
 *
 */

public class Starter {

	private Starter() {
		// 私有化构造

	}

	public static Starter getStarter() {
		return new Starter();

	}

	public static Starter starter = Starter.getStarter();
	private Airbags airbags = new Airbags();

	/*
	 * 线程2:机器人参数检测
	 * 
	 * 返回：电池容量和环境光线
	 */
	private Thread robotParameterDetectionThread = new Thread() {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(60000);

				} catch (Exception e) {
					e.printStackTrace();

				}
				LCD.clear();// 清屏
				Delay.msDelay(1000);
				LCD.drawString("Battery:" + PowerMonitoring.getBatteryCapacityRun(), 0, 3, false); // 电池容量
				LCD.drawString("light:" + Sensitometry.getSensitometryRun(), 0, 5, false); // 环境光线

			}
		}
	};

	/*
	 * 线程3:机器人安全监控
	 */
	private Thread safetyDeviceDetection = new Thread() {
		@Override
		public void run() {
			/*
			 * 机器人安全监控
			 */
			EV3LED.setEv3Led(0); // 关闭LED灯
			Delay.msDelay(1000);
			starter.airbags.getAirbags();

		}
	};

	/*
	 * 线程1:机器人行动控制单元
	 */
	private Thread logicalControlThread = new Thread() {
		@Override
		public void run() {
			/*
			 * 机器人逻辑控制单元: 1.getWelcomeToEv3();欢迎信息和版本信息; 2.getRunningMode();跑步模式
			 * 
			 */
			PageInformation.getWelcomeToEv3(); // 欢迎信息和版本信息
			// RunningMode.getRunningModeRun(); //机器人跑步模式
			// WalkingMode.getWalkingModeRun(); // 机器人走路模式
			// CombatMode.getCombatModeRun(); // 机器人战斗模式
			Jeff.getJeffFunnyCatModel(); // 杰夫机器人的逗猫模式
			// PhotoAlbum.getIMG_2235();// 相册展示

		}
	};

	/*
	 * 机器人LED灯控制
	 */

	private Thread lighTingThread = new Thread() {
		@Override
		public void run() {
			while (true) {
				EV3LED.setEv3Led(1); // 绿
				Delay.msDelay(60000);
				EV3LED.setEv3Led(2); // 红
				Delay.msDelay(60000);
				EV3LED.setEv3Led(3); // 橙

			}
		}
	};

	public static void main(String[] args) {
		starter.lighTingThread.start(); // 机器人LED灯
		starter.robotParameterDetectionThread.start(); // 机器人电量和环境光线参数检测
		starter.logicalControlThread.start(); // 机器人行动控制单元
		starter.safetyDeviceDetection.start(); // 机器人安全监控单元

	}
}










