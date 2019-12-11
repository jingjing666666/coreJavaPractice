package com.core.chapter7.chapter7_1_exception;

import java.io.EOFException;
import java.io.IOException;

/**
 * 如果一个用户在运行程序期间，由于程序的错误或一些外部环境的影响造成用户数据丢失
 * 用户有可能不在使用这个程序，为了避免此类事情发生，至少应该做到以下几点
 * 1.向用户报告错误
 * 2.保存所有的工作结果
 * 3.允许用户以妥善形式退出程序
 * 对于异常情况，如可能造成程序崩溃的错误输入，Java使用一种称为异常处理的错误捕获机制处理
 * 一。处理错误：
 * Java程序运行期间出现了错误，程序应该：
 * 1.返回到一种安全状态，并能够让用户执行一些其他的命令
 * 2.或者允许用户保存所有的操作并以一种妥善的方式终止程序
 * 要做到这些并不是一件容易事
 * 因为检测错误条件的代码通常离那些能够让程序恢复正常状态的代码很远
 * 异常处理的任务就是将控制权从错误产生的地方转移给能够处理这种情况的错误处理器
 * 为了在程序中能够处理异常情况，就必须研究程序中可能会出现的错误和问题,以及哪类问题需要处理
 * 1.用户输入错误
 * 2.设备错误
 * 3.物理限制
 * 4.代码错误
 * 对于方法中的错误，传统的做法通常是返回一个特殊错误码，由调用方法分析。
 * 在Java中如果某个方法不能够采用正常的途径完成他的任务，就可以通过另外一个途径退出方法。在这种情况下，方法
 * 并不返回任何值，而是抛出throw一个封装了错误信息的对象。这个方法会立刻退出，并不返回任何值。
 * 调用这个方法的代码也将无法执行，取而代之的是，异常处理机制开始搜索能够处理这种异常情况的异常处理器（exception handle）
 * 1.异常分类
 * 异常对象都是派生于Throwable类的一个实例，Exception和 Error都是继承于Throwable类，IOException和RuntimeException都继承于Exception类
 * Error类层次结构描述了Java运行时系统的内部错误和资源耗尽错误。发生这种错误，除了通报给用户，让程序安全的终止，也无能为力
 * Exception层次结构
 * Exception层次结构分解为两个分支，一个分支派生于RunTimeException ，另一个分支包含其他异常，划分两个分支的规则是：
 * 1.由程序错误导致的异常属于RunTimeException
 * 2.程序本身没有问题但是由于像I/O错误这类问题的异常属于其它异常
 * 派生于RunTimeException的异常包含下面几种情况
 * 1.错误的类型转换
 * 2.数组访问越界
 * 3.访问null指针
 * 等等
 * 不是派生于RunTimeException
 * 1.试图在文件尾部后面读取数据
 * 2.试图打开一个不存在的文件
 * 3.试图根据给定的字符串查找Class对象，而这个字符串表示的类不存在
 * 等等
 *
 * 如果出现RunTimeException，那么就一定是你的问题，是一条相当有道理的规则，应当检测数组下标是否越界
 * 来避免ArrayIndexOfBoundsException异常，应该通过在使用变量之前检测是否为null来杜绝NullPointerException
 * 文件是否存在取决于环境，不取决于你的代码。
 * Java语言规范将派生于Error类和RuntimeException类的所有异常称为非受检查的异常（unchecked），所有其他的异常称为受检查的异常（checked）
 * 编译器将会核查是否为受检查异常提供了异常处理器
 * 2.声明受查异常
 * 如果遇到了无法处理的情况，Java可以抛出一个异常。这个道理很简单，一个方法不仅需要告诉编译器将要返回什么值，还要告诉编译器可能发生什么错误。
 * 例如，一段读取文件的代码有可能读取的文件不存在，或者内容为空，因此试图处理文件信息的代码就需要通知编译器可能会发生IOException
 * 方法应该在首部声明可能会抛出的异常，例：
 * public FileInputStream(String name)throws FileNotFoundException
 * 这个声明表示构造函数将根据name产生一个FileInputStream对象，但也有可能抛出一个FileNotFoundException
 * 如果发生了这种糟糕情况，构造器将不会初始化一个新的FileInputStream对象，而是抛出一个FileNotFoundException类对象，
 * 如果真的抛出了这样一个异常对象，运行时系统将会搜索异常处理器，以便知道如何处理FileNotFoundException对象
 * 遇到下面4种情况时应该抛出异常
 * 1：调用一个抛出受检查异常的方法，例如FileInputStream构造器
 * 2：运行过程中发现错误，并且利于throw语句抛出一个受查异常
 * 3：程序发生错误会抛出非受查异常
 * 4：Java虚拟机和运行时库出现的内部错误
 * 如果出现前两种情况，则必须告诉调用这个方法的程序员有可能抛出异常。因为任何一个抛出异常的方法都有可能是一个死亡陷阱，
 * 如果没有处理器捕获这个异常，当前线程就会结束。
 * 对于那些可能被他人使用的Java方法，应该根据Java异常规范（exception specification）,在方法的首部声明可能会抛出的异常
 * class MyAnimation{
 *     ...
 *     public void loadImage(String s)throws IOException{
 *
 *     }
 * }
 * 如果一个方法可能会抛出多个受查异常类型，必须在方法首部列出所有受查异常类，每个类之间用逗号分隔
 * class MyAnimation{
 *     ....
 *     public void loadImage(String s) throws FileNotFoundException,EOFException{
 *
 *     }
 * }
 * 但是不需要声明Java的内部错误，即从Error继承的错误。任何代码都具有抛出异常的潜能，我们对其没有控制能力
 * 也不应该声明从RunTimeException继承的那些非受查异常，运行时错误完全在我们的控制之下，如果特别关注数组下标越界，
 * 就应该将更多的时间花费在修正程序上，而不是说明这些错误发生的可能性上。
 * 总之一个方法必须声明所有可能会抛出的受检查异常，而非受检查异常，要么不可控制（ERROR），要么就应该避免发生（RunTimeException）
 * 除了声明异常，还可以捕获异常，这样会使异常不被抛到方法之外，也不需要throws规范
 *
 * 如何决定一个异常是被捕获，还是被抛出让其它处理器处理
 * 3.如何抛出异常
 * 1.假设在程序代码中发生了糟糕的事，一个名为readData的方法正在读取一个首部具有 Content-length :1024
 * 读到744个字符之后文件就结束了，我们认为这是一种不正常的情况，希望抛出一种异常。
 * 首先要决定应该抛出什么类型的异常，将上述异常归结为IOException是一种很好的选择，在JavaAPI中发现EOFException异常描述的是
 * 在输入过程中遇到了一个未预期的EOF后的信号。这个正是我们要抛出的异常，下面是抛出这个异常语句
 * throw new EOFException
 * 或者
 * EOFException e = new EOFException();
 * throw e;
 *
 * 完整代码
 * public void readData(Scanner in)throws EOFException{
 *     while(...){
 *          if(!in.hasNext()){
 *              if(n<len){
 *              throw new EOFException();
 *          }
 *     }
 * }
 * EOFException类还有一个含有字符串的构造器
 * String s = "Content-length: "+len+" received : "+n;
 * throw new EOFException(s);
 * 在前面我们已经看见对于一个已经存在的异常类，将其抛出非常容易，在这种情况下：
 * 1.找到一个合适的异常类
 * 2.创建这个类的一个对象
 * 3.将对象抛出
 * 一旦方法抛出了异常，这个方法就不可能返回到调用者，不必为返回的默认值和错误代码担心
 *
 * 4.创建异常类
 * 在程序中可能会遇到任何标准异常类都没办法描述清楚的问题，在这种情况下创建自己的异常类就是一件顺理成章的事情，
 * 我们需要做的只是定义一个派生于Exception或派生于Exception子类的类
 * 习惯上，定义的类需要有两个构造器，一个默认的构造器，一个带有详细描述信息的构造器（超类Throwable的toString方法将会打印这些详细信息）
 * 例：
 *   class FileFormatException extends IOException(){
 *       public FileFormatException(){
 *
 *       }
 *       public FileFormatException(String gripe){
 *          super(gripe);
 *
 *       }
 *   }
 *
 *  现在就可以抛出自己定义的异常类型
 *
 *  public void readData(Scanner in) throws FileFormatException{
 *      ...
 *      while(..){
 *          if(!in.hasNext()){
 *              if(n<len)
 *              throw new FileFormatException();
 *          }
 *
 *      }
 *  }
 * Throwable 类
 * public Throwable(){} 构造一个新的Throwable对象，没有信息描述
 * public Throwable(String message){}  构造一个新的Throwable对象，这个对象有特定的信息描述。
 * 习惯上所有的派生异常类都支持一个默认构造器和一个带有详细描述信息的构造器
 * public String getMessage(){
 * } 获得Throwable对象的详细描述信息
 *
 * 二。捕获异常
 * 目前为止，我们已经知道如何抛出一个异常，只要将其抛出就不用理睬了，当然了，有些代码必须捕获异常，
 * 捕获异常需要周密的计划。
 * 1.捕获异常
 * 如果某个异常发生的时候，没有在任何地方进行捕获，那程序就会终止进行，并在控制台上打印出异常信息，
 * 其中包括异常的类型和堆栈的内容
 * 要想捕获异常必须设置try/catch语句块,最简单的try/catch语句块如例：
 * try{
 *     code
 *     ....
 * }catch(ExceptionType e){
 *     handle for this type;
 * }
 * 如果在try语句块中的代码抛出catch里面声明的异常，那么：
 * 1.程序将跳过try语句块的代码
 * 2.执行catch子句中的处理器代码
 * 如果try语句块中的代码没有抛出任何异常，那么程序将跳过catch子句代码
 * 如果方法中的任何代码抛出了一个没有在catch中声明的异常，那么这个方法就会立刻退出
 * 演示捕获异常的处理过程：
 * public void read(String fileName){
 *     try{
 *         InputStream stream = new FileInputStream(fileName);
 *         int b;
 *         while((b=stream.read())!=-1){
 *             ........
 *         }
 *     }catch(IOException e){
 *         e.printStackTrack();
 *     }
 * }
 * 需要注意的是try语句中的代码都比较容易理解，读取并处理字节，直到遇到文件结束符为止，read方法会抛出IOException
 * 这种情况将跳出while循环，执行catch子句，并生成一个栈轨迹。对于一个普通程序来说，这样似乎合乎情理，还有其他的选择
 * 通常最好的选择是什么都不做，将异常传递给调用者，如果read方法出现了调用错误，交给read方法的调用者去处理，如果采取这种方式就必须声明
 * 一个IOException
 *  public void read(String fileName) throws IOException{
 *         InputStream stream = new FileInputStream(fileName);
 *         int b;
 *         while((b=stream.read())!=-1){
 *             ........
 *         }
 *
 * }
 * 请记住，编译器严格的执行throws语句，如果调用了一个抛出受查异常的方法就必须对它进行处理或者继续传递
 * 哪种方法更好呢，应该捕获那些知道如何处理的异常，而那些不知道如何处理的异常继续传递
 * 如果想传递一个异常就必须在方法首部添加一个throws说明符，以便告知调用者这个方法可能会抛出这个异常
 * 对于传递异常不必犹豫，将异常直接交给能够胜任的处理器处理比直接压制对他的处理要更好
 * 不允许在子类的方法中 throws超类没有的异常说明范围
 * 2.捕获多个异常
 * 在一个try语句块中可以捕获多个异常类型，并对不同类型的异常做出不同的处理，可以按照下列方式为每个异常类型
 * 使用一个单独的catch子句
 * try{
 *
 * }catch（FileNotFoundException e）{
 *
 * }catch(UnKnownHostException e){
 *
 * }catch(IOException e){
 *
 * }
 * 异常对象可能包含与异常本身的信息，e.getMessage()获取详细的异常信息
 * 获取异常对象的实际类型
 * e.getClass().getName()
 * 一个catch子句可以捕获多个异常类型，
 * try{
 *
 * }catch（FileNotFoundException| UnKnownHostException e）{
 *
 * }catch(IOException e){
 *
 * }
 * 捕获的异常彼此之间不存在子类关系时才可以捕获多个异常类型
 *
 * 3.再次抛出异常与异常链
 * 在catch子句中可以抛出一个异常，这样做的目的是改变异常的类型。如果开发了一个供其它程序员使用的
 * 子系统，那么用于表示子系统故障的异常类型可能会产生多种解释，ServletException异常类就是这样一个例子，
 * 执行servlet的代码可能不想知道发生错误的细节原因，但希望明确的知道servlet是否有问题
 * try{
 *     access the database
 * }catch(SQLException e){
 *     throw ServletException(" database error :"+e.getMessage());
 * }
 * 这里ServletException用带有异常信息文本的构造器来构造
 * 有一种更好的方法，将原始异常设置为新异常的原因
 * try{
 *     access the database;
 * }catch(SQLException e){
 *     Throwable th = new ServletException("database error");
 *     th.initCause(e);
 *     throw th;
 * }
 * 当捕获到异常时，就可以使用下面这个语句获取原始异常
 * Throwable e = th.getCause();
 * 强烈建议使用包装技术，这样可以让用户抛出子系统中的高级异常，而不会丢失原始异常的细节
 * 如果一个方法中发生了受查异常，而不允许抛出它，那么包装技术就十分有用，我们可以捕获这个受查
 * 异常，并将它包装成一个运行时异常
 *
 * 4.finally子句
 * 当代码抛出一个异常时，就会终止方法中剩余代码的处理，并退出这个方法的执行，如果方法获得了一些本地资源，
 * 并且只有这个方法自己知道，又如果这些资源在退出方法之前必须被回收，那么就会产生资源回收问题。
 * 一种解决方案是捕获并重新抛出所有的异常，这种解决方案比较乏味，需要在两个地方清除所分配的资源（一个正常代码，一个异常代码）
 * Java有一种更好的解决方案就是finally子句
 * 如果用Java编写数据库程序，就需要使用同样的技术关闭与数据库的连接。当发生异常时恰当的关闭数据库的连接是很重要的
 * 不管是否有异常被捕获，finally子句中的代码都会被执行，
 * InputStream in = new FileInputStream("...");
 * try{
 *
 *  //1
 *  code that might throw Exceptions
 *  //2
 * }catch(IOException e){
 *     ....//3
 *     show error message
 *     //4
 * }finally{
 *      //5
 *     in.close();
 * }
 * //6
 *
 * 上面代码中有三种情况会执行finally子句
 * 1.代码没有抛出异常，在这种情况下，程序首先执行try语句块中的代码，再执行finally子句中的代码，
 * 然后再执行try语句块之后的代码，执行顺序为1 2 5 6
 * 2.代码抛出了catch子句中捕获的异常，在这种情况下，程序会执行try语句块的所有代码直到发生IOException，
 * 然后跳过try语句块中剩余的代码，然后去执行catch子句中的代码，最后执行finally子句中的代码。
 * 如果catch子句没有抛出异常，执行顺序为 1 3 4 5 6
 * 如果catch子句抛出异常，异常将会抛回给方法的调用者，执行顺序为 1 3 5
 * 3.代码抛出了不是catch子句的异常，代码抛出了一个异常，但是这个异常不是catch子句捕获的，在这种情况下，
 * try语句块中的代码将会被执行直到异常抛出为止，此时将跳过try语句块中的代码执行finally子句中的代码，并将异常抛给方法的调用者
 * 执行顺序为 1 5
 * 无论try语句块是否发生异常，finally子句中的代码都会被执行，如果真的遇到一个异常，这个异常将会被重新抛出，
 * 并且必须由另一个catch子句捕获。
 * 事实上在需要关闭资源的时候使用catch子句是一个很不错的选择。
 * 有时候finally子句也会带来麻烦，清理资源的方法也有可能抛出异常，假设希望能够在流处理代码中遇到异常时将流
 * 关闭
 * InputStream in = new FileInputStream("....");
 * try{
 *     code that might throw Exception
 * }finally{
 *     in.close();
 * }
 * 假设在try语句块中抛出了一些非IO异常，这些异常只有方法的调用者才能处理，然后执行finally语句块，并调用close方法
 * 而close方法本身也有可能发生IOException,出现这种情况时，原始的异常将会丢失，
 * 转而抛出close方法的异常
 * 这会有问题，因为第一个异常很可能更有意思，如果你想做适当的处理，抛出原来的异常，代码会变得极其繁琐，例：
 * InputStream in = new FileInputStream("...");
 * Exception ex = null;
 * try{
 *
 * }catch(Exception e){
 *     ex = e;
 *     throw e;
 * }finally{
 * try{
 *      in.close();
 * }catch(Exception e){
 *     if(ex==null){
 *         throw e;
 *     }
 * }
 * }
 *
 * 5.带资源的try语句
 * 对于以下的代码格式
 * open resource
 * try{
 *     work with resource;
 * }finally{
 *     close resource
 * }
 * 假设资源属于一个实现了AutoCloseable接口的类，javaSE7为这种代码模式提供了一个很有用的快捷方式，
 * AutoCloseable为这个接口提供了一个close方法
 * void close() throws Exception
 * 带资源的try语句（try-with-resources）最简形式为：
 * try(Resource res = ){
 *     work with res
 * }
 * try块退出时会自动调用res.close(),例，这里要读出文件里的所有单词
 * try(Scanner in = new Scanner(new FileInputStream("/files/word.txt"),"utf-8")){
 *     while(in.hasNext()){
 *         System.out.println(in.next());
 *     }
 * }
 * 这个块正常退出时或者出现异常都会调用in.close()方法，就好像使用了finally块一样，
 * 还可以指定多个资源
 * try(Scanner in = new Scanner(new FileInputStream("/file/word.txt"),"UTF-8");
 * PrintWriter out = new PrintWriter("out.txt")){
 *     while(in.hasNext()){
 *         out.println(in.next().toUpperCase());
 *     }
 * }
 * 不论这个块如何退出都会关闭out和in
 * 上一节已经看到如果try块抛出异常，close方法也抛出一个异常，会带来难题，但是带
 * 资源的try语句可以很好的处理这种情况，原来的异常会重新抛出，close的异常会“被抑制”，
 * 这些异常将自动捕获，并由addSuppressed方法增加到原来的异常中，如果对这些异常感兴趣，可以调用getSuppressed方法
 * 可以得到从close方法抛出并被抑制的异常列表
 * 只要需要关闭资源可以使用带资源的try语句
 *
 * 6.分析堆栈轨迹元素
 * stack trace （堆栈轨迹）是一个方法调用过程的列表,它包含了程序执行过程中方法调用的特殊位置，
 * 当Java程序正常执行，没有捕获异常时，这个列表就会显示出来，
 * 可以调用Throwable类的printStackTrace方法访问堆栈轨迹的文本描述信息
 * Throwable t = new Throwable();
 * StringWriter out = new StringWriter();
 * t.printStackTrace(new PrintWriter(out));
 * String description = out.toString();
 * 一种更灵活的方法是使用getStackTrace（）方法
 * Throwable t = new Throwable();
 * StackTraceElement[] elements = t.getStackTrace();
 * for(StackTraceElement e:elements){
 *     analyze the e
 * }
 *
 * 三、使用异常机制的技巧
 * 异常机制的技巧
 * 1.异常处理不能代替简单的测试
 * 2.不要过分的细化异常
 * 3.利用异常层次结构
 * 4.不要压制异常
 * 5.在检测错误时，苛刻要比放任好
 * 6.不要羞于传递异常
 * 5.6可以归纳为“早抛出晚处理”
 *
 * Created by yuanqingjing on 2019/12/3
 */
public class IntroduceException {
    public static void main(String[] args) {
        Throwable t = new Throwable();
        t.printStackTrace();
    }
}
