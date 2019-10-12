package lambda;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import lambda.entity.Employee;

/**
 *
 * 方法引用：若lambda 体中的内容已经实现了的话，我们可以使用方法引用
 * 			（可以理解为方法引用时lambda表达式的另外一种表现形式）(省略参数列表写法的一种简化)
 * 主要有三种语法格式：
 * 
 * 对象：：实例方法名
 *
 * 类：：静态方法名
 *
 * lambda体中参数列表和返回值类型必须和要引用的方法的参数列表返回值一致才可以使用
 *
 *
 *
 * 类::实例方法名
 * 2、若Lambda参数列表中的第一个参数是实例方法的调用着，第二个是实例方法的是参数时可以使用该方法
 * 
 * 
 * 
 * 
 * 构造器引用：
 * 
 * 	[ClassName]::new
 * 
 * 数组引用：
 * 	[type]::new
 * 
 * 	例如： String[]::new;
 *
 */
public class Test04 {

	@Test
	public void Test1(){
		//等价于
		Consumer<String> con = (x)->System.out.println(x);
		Consumer<String> con1 = System.out::print;

//		Consumer<String> con2 = new Consumer<String>() {
//			@Override
//			public void accept(String s) {
//				System.out.println(s);
//			}
//		};
		//等价于


		con.accept("hello");
		con1.accept("hello");
	}
	
	
	@Test
	public void test2(){
		Employee emp = new Employee("mihao",122,121.00);
		Supplier<String> sup = emp::getName;
		Supplier<String> sup1 = ()->emp.getName();

		System.out.println(sup.get());
		System.out.println(sup1.get());

	}
	
	@Test
	public void comparator(){
		Comparator<Integer> com = Integer::compare;

		Comparator<Integer> com1 = (x,y)->Integer.compare(x,y);
		com.compare(1, 2);
		com1.compare(1, 2);
	}

	@Test
	public void test4(){
		//返回值boolean           类：：实例方法
		BiPredicate<String,String> bp = String::equals;

		//满足条件第一个参数是调用者
		BiPredicate<String,String> bp1 = (x,y)->x.equals(y);

	}

	@Test
	public void test5(){
		Supplier<Employee> sup = Employee::new;

		Supplier<Employee> sup1 = ()->new Employee();

		sup.get();
	}
	@Test
	public void test6() {

		Function<Integer, String[]> fun = String[]::new;
		Function<Integer,String[]> fun1 = (x)->new String[x];
		fun.apply(10);
	}
}














