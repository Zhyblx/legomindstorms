package com.zhangyibin.mood;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.LED;

/**
 * EV3LED(EV3自带的LED灯光)
 * 
 * Ev3
 * LED控制参考：https://lejosnews.wordpress.com/2015/02/26/ev3-to-ev3-using-remote-methods/
 * 
 * @author zhangyibin
 *
 */
public class EV3LED {

	/**
	 * 设置LED参数
	 * 
	 * 说明：ledPattern(0-关闭、1-绿、2-红、3-橙)
	 * 
	 * @param ledPattern (LED参数)
	 */
	public static void setEv3Led(int ledPattern) {
		Brick brick = BrickFinder.getLocal(); // 或 .getDefault()
		LED led = brick.getLED();
		led.setPattern(ledPattern);

	}
}

/*
 * 《Ev3LED使用资料》
 * 
 * 在之前的文章中，我们研究了如何将多个 EV3 连接到一个 PAN（个人区域网络）中， 在本文中，我们将看到如何使用 PAN 编写一个简单的程序来控制一个
 * EV3 的资源从另一个。 有几种方法可以在 Java 和 leJOS 中创建这种类型的网络程序，这里我们将使用一种使用远程方法调用的技术。
 * 本质上，我们将编写一个程序，该程序附加到每个其他 EV3 上，获取代表远程砖块资源的对象； 然后调用该对象上的方法来访问资源。我们来看看代码。
 */
//import lejos.hardware.Brick;
//import lejos.hardware.BrickFinder;
//import lejos.hardware.Button;
//import lejos.hardware.LED;
//import lejos.remote.ev3.RemoteEV3;
//import lejos.utility.Delay;
//
//public class EV3Test2
//{
//
//    public static void remoteLEDTest()
//    {
//        String[] names = {"EV1", "EV2", "EV3", "EV4"};
//        Brick[] bricks = new Brick[names.length];
//        try {
//            bricks[0] = BrickFinder.getLocal();// 获取本地砖
//            for(int i = 1; i < bricks.length; i++)
//            {
//                System.out.println("Connect " + names[i]);
//                /*
//                 * 
//                 */
//                bricks[i] = new RemoteEV3(BrickFinder.find(names[i])[0].getIPAddress());
//            }
//            LED[] leds = new LED[bricks.length];
//            for(int i = 0; i < bricks.length; i++)
//                leds[i] = bricks[i].getLED();//访问本地砖的LED
//            int i = 0;
//            int pat = 1;
//            while(Button.ENTER.isUp())
//            {
//                leds[(i++) % leds.length].setPattern(0);
//                if (i % leds.length == 0)
//                {
//                    pat = ((pat + 1) % 3) + 1;
//                }
//                leds[(i) % leds.length].setPattern(pat);
//                Delay.msDelay(100);
//            }
//            for(LED l : leds)
//                l.setPattern(0);
//        }
//        catch (Exception e)
//        {
//            System.out.println("Got exception " + e);
//        }
//    }    
//
//    public static void main(String[] args)
//    {
//        remoteLEDTest();
//    }
//}
