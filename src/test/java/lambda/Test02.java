package lambda;

import java.util.function.Consumer;

/**
 * 基础语法：java8中引入了一个新的操作符“->”,该操作符将lambda表达式拆分为两部分
 * 左侧：Lambda表达式的参数列表，接口中方法的参数列表
 * 右侧：Lambda表达式要执行操作，接口的方法重写的操作
 * 
 * 函数式接口：只有一个抽象方法的接口.lambda表达式需要函数式接口的支持
 * 匿名内部类使用外部类的成员变量，成员变量必须为final
 * 语法格式一、无参数无返回值
 *		()->System.out.println("hello world");
 * 语法格式二、一个参数无返回值小括号可以省略
 * 		(a)->System.out.println("hello world");
 * 语法格式三、有返回值有两个以上的参数lambda体中有多条语句（只有一条语句{}可以省略不写）
 * 		A a1 = (a,b,c,d)->{
 * 						System.out.println(a+b+c+d);
 * 						System.out.println("hello world");
 * 
 * 						return new A();
 * 					}
 * 语法格式四、参数列表中的数据类型可以省略不写，jvm的编译器可以根据上下文推断出参数的数据类型（类型推断）
 * @author 爱不会绝迹
 *
 */
public class Test02 {
	public static void main(String[] args) {
		Runnable r1 = ()->System.out.println("hello world");
		Consumer<String> con = (a)->System.out.println("hello world");
	}
}
