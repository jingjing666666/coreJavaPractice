package com.core.chapter6.chapter6_7_innerclass.program_6_7_innerclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 这个程序演示内部类的用法
 * Created by yuanqingjing on 2019/12/1
 */
public class InnerClassTest {

    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock(1000,true);
        clock.start();
        JOptionPane.showMessageDialog(null,"Quit Program");
        System.exit(0);
    }
}

/**
 * 一个闹钟在有规则几秒后输出时间
 */
class TalkingClock{

    private int interval;

    private boolean beep;

    /**
     * TalkingClock的构造函数
     * @param interval 几秒后
     * @param beep 是否停止
     */
    public TalkingClock(int interval,boolean beep){
        this.interval = interval;
        this.beep = beep;
    }

    /**
     * 开启时钟
     */
    public void  start(){
        ActionListener actionListener = new TimePrinter();
        Timer timer = new Timer(100,actionListener);
        timer.start();

    }

    /**
     * 内部类TimerPrinter
     */
    public class TimePrinter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(" At the stone, the time is "+new Date());
            //内部类访问外围类的数据域
            if (beep){
                System.out.println("beef");
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }



}
