package com.core.chapter8.chapter8_1;

import com.sun.corba.se.impl.presentation.rmi.ExceptionHandler;

/**
 * Created by yuanqingjing on 2019/12/14
 * 一。为什么要使用泛型
 *  泛型程序设计意味着编写的代码可以被很多不同类型的对象所重用
 *  例如，我们不需要为聚集File和String分别设计不同的类，实际上，也不需要这样做，
 *  因为一个ArrayList的类可以聚集任何类型的对象，这是泛型程序设计的实例
 * 1.类型参数的好处
 * 在java中增加泛型类之前，泛型程序设计是用继承实现的，ArrayList类只维护一个Object引用的数组
 * public class ArrayList{ //before generic class
 *     private Object[] elementData;
 *     .....
 *     public Object get(int index){
 *         ..
 *     }
 *     public void set(Object obj){
 *         ...
 *     }
 * }
 * 这种方法有两个问题，当获取一个值必须进行强制类型转换，
 * ArrayList files = new ArrayList();
 * ....
 * String file = (String)files.get(0)
 * 这里没有错误检查，可以像数组列表中添加任何类的对象，
 * files.add(new File("...."));
 * 这里编译和运行都不会报错，但是在get时将结果强制转换成String，就会出现类型转换异常
 * 泛型提供了一个更好的解决方案，类型参数（type parameters）,ArrayList类有一个类型参数
 * 用来指示元素的类型：
 * ArrayList<String> files = new ArrayList<>();
 * 这使得代码具有很好的可读性，一看就知道ArrayList中装的是String类型的对象。
 * 有了类型参数后，当get和add操作时编译器都会检验 add的元素是不是String类型的对象，get时自动转换为String类型
 * 编译器可以进行检查，避免插入错误的类型对象或者类型强制转换错误
 * 出现编译错误比类在运行时出现类的强制类型转换异常要好得多。
 * 类型参数的魅力在于：使程序具有很好的可读性和安全性。
 *
 * 2.谁想成为泛型程序员
 * 实现一个泛型类没那么容易，对于类型参数，使用这段代码的程序员可能想要内置（plugin）所有的类
 * 他们希望在没有过多的限制以及混乱的错误消息状态下，做所有的事情。
 * 因此，一个泛型程序员的任务就是预测出所用类的未来可能有的所有用途
 * 这一任务难到什么程度呢，下面是标准类库的设计者们肯定产生争议的一个典型问题，ArrayList类有一个方法addAll()
 * 用来添加另一个集合的全部元素，程序员可能想要将ArrayList<Manager> 中的元素添加到ArrayList<Employee>中，
 * 然而反过来就不行了。如果只能允许前一个调用，而不能允许后一个调用呢？Java语言的设计者发明了一个具有独创性的新概念
 * 通配符类型（wildcard Type）,它解决了这个问题，通配符类型非常抽象，然而，他们能让库的构建者编写尽可能灵活的方法
 *
 * 泛型程序设计分为3个能力级别，
 * 1.基本级别是：仅仅使用泛型类--典型的是像ArrayList这样的集合--不必考虑他们的工作方式和原因
 * 大多数程序员将会停留在这一级别上，直到出现了什么问题，当把不同类型的泛型类混合在一起，或是在于类型参数
 * 一无所知的遗留的代码进行衔接时，可能会看到含混不清的错误信息，如果这样的话就需要学习Java泛型来系统的解决问题
 * 而不是胡乱的猜测，当然，最终可能想要实现自己的泛型类和泛型方法。
 * 应用程序员可能不太喜欢编写太多的泛型代码，JDK开发人员以及做了很大的努力，为所有的集合类提供类型参数。
 * 凭经验来说，那些原本涉及许多来自通用类型（Object）的强制转换的代码一定会因为使用类型参数而受益。
 *
 * 二、定义简单的泛型类
 * 一个泛型类就是具有一个或多个类型变量的类，本章使用一个简单的Pair类作为例子，对于这个类来说我们只关注泛型，
 * 而不会为数据存储的细节烦恼
 * public class Pair<T>{
 *     private T first;
 *     private T second;
 *     public T getFirst(){
 *         return first;
 *     }
 *
 *     public T getSecond(){
 *         return second;
 *     }
 *
 *     public void setFirst(T first){
 *         this.first = first;
 *     }
 *
 *     public void setSecond(T second){
 *         this.second = second;
 *     }
 *
 * }
 * Pair类引入了一个类型变量T,用<>括起来，并放在类名的后面。泛型类可以有多个类型变量
 *例如，可以定义Pair类，其中第一个域和第二个域使用不同的类型。
 * public class Pair<P,U>{
 *     ....
 * }
 * 类定义中的类型变量指定方法的返回类型以及域和局部变量的类型，例
 * private P first;
 * 类型变量用大写，较短，常规情况下 E表示集合的元素类型，K和V表示表的关键字和值类型，T表示任意类型
 * 用具体的类型替换类型变量就可以实例化泛型类型，
 * 例如 Pair<String>
 * 可以将结果想象成带有构造器的普通类
 * Pair<String>()
 * Pair<String>(String,String)
 * 方法
 * public String getFirst()
 * public String getSecond()
 * public void setFirst(String)
 * public void serSecond(String)
 * 换句话说泛型类可以看成普通类的工厂
 * 程序清单8_1中的程序使用了Pair，静态的minmax方法遍历了数组，并同时计算出最大值和最小值，他用一个Pair
 * 对象返回了两个结果
 *
 * 三、泛型方法
 * 前面已经介绍了定义一个泛型类，实际上还可以定义一个带有类型参数的简单方法
 * class ArrayAlg{
 *
 *     public static <T> T getMiddle(T... a){
 *     return a[a.length/2];
 * }
 * 这个方法是在普通类中定义的而不是在泛型类中定义的，这是一个泛型方法，可以从尖括号和类型变量中看出
 * 类型变量放在修饰符（public static）的后面,返回类型的前面。
 * 泛型方法可以定义在泛型类中，也可以定义在普通类中。
 * 当调用一个泛型方法时，在方法名前的前括号中放入具体的类型
 * String middle = ArrayAlg.<String>getMiddle("John","july","lily");
 * 在这种情况下，方法调用中可以省去<String>类型参数，编译器有足够的信息能够推断出所调用的方法
 * 它用names的类型（String[]）与泛型类型T[]进行匹配并推断出T是String类型，也就是说可以直接调用
 * String middle = ArrayAlg.getMiddle("John","Lucy");
 * 几乎大多数情况下对于泛型方法的类型引用几乎没有问题，偶尔编译器也会提示错误，此时需要解释错误报告，
 * 例
 *  double middle = ArrayAlg.getMiddle(4.12,3,4);
 *  编译器会自动打包参数为一个Double，两个Integer类型的对象，而后寻找这些类的共同超类型。事实上，找到两个这样的
 *  超类型Number和Comparable接口，其本身也是一个泛型类型，在这种情况下所采取的补救措施是将所有参数都写为double值
 *
 *
 */
public class Introduce {
    public static void main(String[] args) {

    }
}
