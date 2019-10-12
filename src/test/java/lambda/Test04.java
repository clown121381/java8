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
 * �������ã���lambda ���е������Ѿ�ʵ���˵Ļ������ǿ���ʹ�÷�������
 * 			���������Ϊ��������ʱlambda���ʽ������һ�ֱ�����ʽ��(ʡ�Բ����б�д����һ�ּ�)
 * ��Ҫ�������﷨��ʽ��
 * 
 * ���󣺣�ʵ��������
 *
 * �ࣺ����̬������
 *
 * lambda���в����б�ͷ���ֵ���ͱ����Ҫ���õķ����Ĳ����б���ֵһ�²ſ���ʹ��
 *
 *
 *
 * ��::ʵ��������
 * 2����Lambda�����б��еĵ�һ��������ʵ�������ĵ����ţ��ڶ�����ʵ���������ǲ���ʱ����ʹ�ø÷���
 * 
 * 
 * 
 * 
 * ���������ã�
 * 
 * 	[ClassName]::new
 * 
 * �������ã�
 * 	[type]::new
 * 
 * 	���磺 String[]::new;
 *
 */
public class Test04 {

	@Test
	public void Test1(){
		//�ȼ���
		Consumer<String> con = (x)->System.out.println(x);
		Consumer<String> con1 = System.out::print;

//		Consumer<String> con2 = new Consumer<String>() {
//			@Override
//			public void accept(String s) {
//				System.out.println(s);
//			}
//		};
		//�ȼ���


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
		//����ֵboolean           �ࣺ��ʵ������
		BiPredicate<String,String> bp = String::equals;

		//����������һ�������ǵ�����
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














