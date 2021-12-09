package com.zhangyibin.information;

import com.zhangyibin.mood.FacialExpression;
import com.zhangyibin.mood.PhotoAlbum;
import com.zhangyibin.mood.Voice;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

/**
 * PageInformation(Ev3信息页面)
 * 
 * @author zhangyibin
 *
 */
public class PageInformation {

	private PageInformation() {

	}

	private static String version = "Version: 0.9.3";// 机器人软件版本信息

	/**
	 * Ev3启动页面：欢迎信息和版本信息
	 * 
	 * @author zhangyibin
	 * 
	 */
	public static void getWelcomeToEv3() {
		LCD.clear();// 清屏
		LCD.drawString(FacialExpression.welcome.getExpressionEnum(), 0, 2, true);// 黑底白字
		LCD.drawString(version, 0, 3, false); // 白底黑字
		LCD.drawString(FacialExpression.happy.getExpressionEnum(), 0, 4, false);
		Delay.msDelay(5000);
		PhotoAlbum.getIMG_2377();
		Delay.msDelay(5000);
		Voice.getBeep();;// 蜂鸣(一声)提示：表示启动程序

	}

	/**
	 * Ev3系统关闭页面；
	 * 
	 * @author zhangyibin
	 */
	public static void getGoodBye() {
		PhotoAlbum.getIMG_2235();
		Delay.msDelay(5000);
		LCD.clear();
		LCD.drawString(FacialExpression.goodbye.getExpressionEnum(), 0, 3, false);
		Delay.msDelay(2000);
		Voice.getTwoBeeps();// 蜂鸣(两声)提示：表示关闭程序

	}

}
