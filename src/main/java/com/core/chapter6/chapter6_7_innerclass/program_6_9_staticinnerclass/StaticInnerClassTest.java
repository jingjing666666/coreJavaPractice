package com.core.chapter6.chapter6_7_innerclass.program_6_9_staticinnerclass;

/**
 * 这个程序演示静态内部类类的用法
 * Created by yuanqingjing on 2019/12/1
 */
public class StaticInnerClassTest {
    public static void main(String[] args) {
        double[] array = new double[10];
        for (int i=0;i<array.length;i++) {
            array[i]=i;
        }

        ArrayAlg.Pair pair = ArrayAlg.minMax(array);
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
    }
}

class ArrayAlg{

    public static class Pair {
        private double second;
        private double first;

        public Pair(double second, double first) {
            this.first = first;
            this.second = second;
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }

        /**
         * 计算数组的最小值和最大值
         * @param array
         * @return
         */
       public static Pair minMax(double[] array){
            double min = Double.POSITIVE_INFINITY;
            double max = Double.NEGATIVE_INFINITY;
           for (double item:array) {
               if (item>max) max = item;
               if (item<min) min = item;
           }
           //内部类在静态方法中构造的必须声明为静态内部类
           //以一个对象返回两个值，只用遍历一遍数组
           return new Pair(max,min);
       }
    }


