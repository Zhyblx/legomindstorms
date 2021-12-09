package com.zhangyibin.power;

/**
 * PowerInterface(机器人动力参数接口)
 * 
 * @author zhangyibin
 *
 */

public interface PowerInterface {
	/**
	 * 电机转速
	 */
	public static final Integer SPEED = 300;

	/**
	 * 步伐;即，延时间隔时间。
	 */
	public static final Integer PACE = 1000;

	/**
	 * 角度
	 */
	public static final Integer ANGLE = 270;

	/**
	 * 参数微调
	 */
	public static final Integer PARAMETERTUNING = 10;

	/**
	 * Jeff机器人的电机转速
	 */
	public static final Integer JEFFSPEED = 100; // 足
	public static final Integer JEFFARMSSPEED = 50; // 激光笔
	public static final Integer JEFFANGLE_180 = 180; // 90度
	public static final Integer JEFFANGLE_270= 270; // 180度

}
