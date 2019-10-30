package com.core.chapter5.chapter5_16_arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 这个程序显示了使用反射操作数组
 * @Author: 020188
 * @Date: 2019/10/30
 */
public class CopyOfTest {

    public static void main(String[] args){
        int[] a={1,3,5};
        a=(int[]) goodCopyOf(a,10);
        System.out.println(Arrays.toString(a));

        String[] b={"Tom","Jack","Jerry"};
        b=(String[]) goodCopyOf(b,10);
        System.out.println(Arrays.toString(b));
        System.out.println(" This following call will generate an Exception ");
        b =(String[]) badCopyOf(b,10);
        //报错  Object[]不能强转为为String[]
        // (如果这个数组之前是String[]类型，然后转为Object[],是可以再转为String[])
        //Java 数组会记住每个元素的类型，即创建数 组时 new 表达式中使用的元素类型
    }


    /**
     * 该方法通过分配相同类型的新数组并复制所有元素来增长数组
     * @param a 原数组
     * @param newLength 新数组长度
     * @return 返回一个更大的数组包括原数组所有的长度
     */
    public static Object goodCopyOf(Object a,int newLength) {
        Class clazz = a.getClass();
        if (!clazz.isArray()) return null;
        Class classComponentType = clazz.getComponentType();
        int length = Array.getLength(a);
        //创建与原数组类型相同的新数组
        Object newArray = Array.newInstance(classComponentType,newLength);
        System.arraycopy(a,0,newArray,0,Math.min(length,newLength));
        return newArray;
    }

    /**
     * 这个方法通过分配一个新数组并复制所有元素来增长数组
     * @param b 原数组
     * @param newLength 新数组长度
     * @return 返回一个更大的数组包括原数组所有的长度,但是返回的数组类型不是b的数组类型而是Object
     */
    public static Object[] badCopyOf(Object[] b,int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(b,0,newArray,0,Math.min(newLength,b.length));
        return newArray;
    }

}
