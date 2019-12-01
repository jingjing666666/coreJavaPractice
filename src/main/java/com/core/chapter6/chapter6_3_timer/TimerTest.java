package com.core.chapter6.chapter6_3_timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @Author: 020188
 * @Date: 2019/11/18
 */
public class TimerTest {

    public static void main(String[] args) {
        ActionListener timerPrinter = new TimerPrinter();
        Timer timer = new Timer(10000,timerPrinter);
        timer.start();
        JOptionPane.showMessageDialog(null,"Quit Program?");
        System.exit(0);
    }


}

class TimerPrinter implements ActionListener{
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the stone,the Time is "+new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}