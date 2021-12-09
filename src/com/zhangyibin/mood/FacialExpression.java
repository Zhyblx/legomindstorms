package com.zhangyibin.mood;

/**
 * FacialExpression(机器人的情绪表达)
 * 
 * @author zhangyibin
 *
 */
public enum FacialExpression {
	welcome("Welcome Ev3"), // 欢迎
	goodbye("goodbye YiBin"), // 再见
	version("Version:1.0"), // 版本
	moving("(+_+)"), // 感动
	happy("( *^_^* )"), // 开心
	sad("(T_T)"), // 伤心
	angry("(x_x)");// 生气

	private String expressionEnum = "";

	private FacialExpression(String expressionEnum) {
		this.expressionEnum = expressionEnum;

	}

	public String getExpressionEnum() {
		return this.expressionEnum;

	}

}
