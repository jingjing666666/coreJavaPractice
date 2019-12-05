package com.core.chapter6.chapter6_8_proxy;

import java.lang.reflect.Proxy;

/**
 * 利用代理可以在运行时创建一个实现了一组给定接口的新类。
 * 这种功能只有在编译时无法确定需要实现哪个接口时才有必要使用。
 *
 * 一.何时使用代理
 * 假设有一个表示接口的Class对象（有可能只包含一个接口），它的确切类型在编译时无法知道，
 * 要想构造一个实现这些接口的类，需要使用newInstance方法或通过反射找出这个类的构造器，
 * 但是不能实例化一个接口，需要程序处于运行状态定义一个新类。代理类可以在运行时创建全新的类，
 * 这样的代理类能够实现指定的接口，尤其是它具有下列方法
 * 1.指定接口所需要的全部方法
 * 2.Object类中的全部方法，例如toString、equals等
 * 不能在运行时定义这些方法的新代码，而是要提供一个调用处理器 invocationHandle
 * 调用处理器是实现了InvocationHandle接口的类对象，接口中只有一个方法 invoke
 * Object invoke(Object proxy,Method method,Object[] args)
 * 无论何时调用代理对象的方法，调用处理器的invoke方法都会被调用，并像其
 * 传递Method 对象和原始的调用参数参数，调用处理器必须给出处理调用的方式
 *
 * 二.创建代理对象
 * 要想创建代理对象，需要使用Proxy类的 newProxyInstance方法,这个方法有三个参数
 * 1.ClassLoader 类加载器
 * 2.Class[] 一个Class对象数组  需要实现的接口
 * 3.InvocationHandle 调用处理器
 *
 * 还有两个需要解决的问题
 * 1.如何定义一个代理器
 * 2.能够用结果代理对象做些什么
 * 这两个问题的答案取决于使用代理机制解决什么问题，使用代理可能由于很多原因，例如
 * 1、路由对远程服务器方法的调用
 * 2、在程序运行期间将用户接口事件与动作关联起来
 * 3、为调试、跟踪方法调用
 * 在示例程序中使用代理和调用处理器跟踪方法调用，并且定义了一个TraceHandle包
 * 装器类存储包装的对象，其中的invoke方法打印出被调用方法的名字和参数，随后用包装好的对象作为隐式参数调用方法
 * class TraceHandle implements InvocationHandle{
 *      Object target;
 *      public TraceHandle(Object t){
 *          target = t;
 *      }
 *
 *      public Object invoke(Object proxy,Method method,Object[] args){
 *          ....
 *          //调用实际方法
 *          m.invoke(target,args);
 *      }
 *
 * }
 *
 * 三、代理类的特性
 * 代理类是在程序运行过程中创建的，一旦被创建就变成了常规类，与虚拟机中其他类并没有区别
 * 一个代理类只有一个实例域，就是InvocationHandle，它定义在Proxy的超类中
 * 为了履行代理对象的职责，它所需的附加数据都必须储存在调用处理器中，像6_10的代理Comparable对象时，
 * TranceHandle包装了实际的对象
 * 所有的代理类都覆盖了Object的toString（） equals（） hashCode（）方法，仅仅是在这些方法中调用处理器的invoke（）方法
 * 没有定义代理类的名字，Sun虚拟机中的Proxy类将生成一个$Proxy开头的类名
 * 对于特定的类加载器和预设的一组接口来说，只能有一个代理类，也就是说使用同一个类加载器和接口数组调用
 * 两次newProxyInstance(),只能够得到同一个类的两个对象，也可以利用getProxyClass获取这个类例：
 * Class ProxyClass = Proxy.getProxyClass(null,interfaces);
 * 代理类一定是public和final，如果代理类实现的所有接口都是public，代理类就不属于某个特定的包，否则所有非
 * 公有的接口都必须属于同一个包，同时代理类也属于这个包
 * proxy.isProxyClass(class)
 * 检测某个特定的Class对象是否代表一个代理类
 * Created by yuanqingjing on 2019/12/1
 */
public class IntroduceProxy {

    public static void main(String[] args) {
    }
}
