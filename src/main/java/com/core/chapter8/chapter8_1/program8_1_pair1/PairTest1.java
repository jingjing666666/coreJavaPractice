package com.core.chapter8.chapter8_1.program8_1_pair1;

/**
 * 演示定义一个简单的泛型类
 * 用一个具体的类型String实例化泛型类型
 * Created by yuanqingjing on 2019/12/22
 */
public class PairTest1 {

    public static void main(String[] args) {
        String[] array = new String[]{"8", "2", "3", "4"};
        Pair<String> pair = ArrayAlg.minmax(array);
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
    }
}

class ArrayAlg {

    /**
     * 得到数组的最小和最大值
     * @param a 数组
     * @return 泛型类
     */
    public static Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        String min = a[0];
        String max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i].compareTo(max) > 0) {
                max = a[i];
            }
            if (a[i].compareTo(min) < 0) {
                min = a[i];
            }
        }
        return new Pair<>(min, max);
    }
}

/**
 * 定义简单的泛型类
 * @param <T> 泛型变量
 */
class Pair<T> {
    private T first;
    private T second;

    public Pair() {

    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

}


