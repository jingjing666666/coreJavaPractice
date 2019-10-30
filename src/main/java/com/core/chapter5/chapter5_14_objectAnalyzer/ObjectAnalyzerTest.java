package com.core.chapter5.chapter5_14_objectAnalyzer;

import com.core.chapter5.chapter5_15_objectAnalyzer.ObjectAnalyzer;

import java.util.ArrayList;

/**
 * 这个程序用反射分析对象，分析对象的数据类型，和其中的变量数据类型和值
 * @Author: 020188
 * @Date: 2019/10/28
 */
public class ObjectAnalyzerTest {

    public static void main(String[] args){
        ArrayList<Integer> squares = new ArrayList<Integer>();

        for (int i = 1; i <=5 ; i++) {
            squares.add(i*i);
        }
        System.out.println(new ObjectAnalyzer().toString(squares));
    }
}
