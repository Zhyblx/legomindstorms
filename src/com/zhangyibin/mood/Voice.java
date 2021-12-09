package com.zhangyibin.mood;

import lejos.hardware.Sound;

/**
 * Voice(声音)
 * 
 * @author zhangyibin
 *
 */
public class Voice {

	/**
	 * 机器人程序启动
	 * 
	 * 返回：蜂鸣一次
	 */
	public static void getBeep() {
		Sound.beep();

	}

	/**
	 * 机器人程序关闭
	 * 
	 * 返回：蜂鸣两次
	 */
	public static void getTwoBeeps() {
		Sound.twoBeeps();

	}

	/**
	 * 播放系统声音
	 * 
	 * @param aQueued
	 * @param aCode
	 * @deprecated
	 */

	public static void getSystemSound(boolean aQueued, int aCode) {
		Sound.systemSound(true, 0);

	}

}
