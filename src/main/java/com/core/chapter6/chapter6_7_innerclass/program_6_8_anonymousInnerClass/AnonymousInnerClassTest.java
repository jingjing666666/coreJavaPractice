package com.core.chapter6.chapter6_7_innerclass.program_6_8_anonymousInnerClass;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * 这个程序演示匿名内部类的用法
 * Created by yuanqingjing on 2019/12/1
 */
public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        new TalkingClock().start(100, true);
        JOptionPane.showMessageDialog(null, "Quit program");
        System.exit(0);
    }
}

class TalkingClock {

    public void start(int interavl, boolean beep) {
        Timer timer = new Timer(100, event -> {
            System.out.println(" At the stone, the time is " + new Date());
            if (beep) {
                System.out.println("beep");
                Toolkit.getDefaultToolkit().beep();
            }
        });
        timer.start();
    }
}
