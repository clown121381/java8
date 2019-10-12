package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import lambda.entity.Employee;
import lambda.entity.MyCompare;

public class Test01 {

	// 比较大小，不用lambda表达式
	@Test
	public void test() {
		Comparator<Integer> com = new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return Integer.compare(arg0, arg1);
			}
		};

	}

	// 用lambda表达式
	@Test
	public void test01() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
	}

	// 比较employee对象并且 过滤通过年龄
	public static List<Employee> compareEmployeesWithAge(List<Employee> emps, Integer age) {
		List<Employee> li = new ArrayList<>();
		for (Employee e : emps) {
			if (e.getAge() > age) {
				li.add(e);
			}
		}
		return li;
	}

	// 比较employee对象并且 过滤通过工资
	public static List<Employee> compareEmployeesWithSalary(List<Employee> emps, Double salary) {
		List<Employee> li = new ArrayList<>();
		for (Employee e : emps) {
			if (e.getSalary() > salary) {
				li.add(e);
			}
		}
		return li;
	}

	public static List<Employee> compareEmployee(List<Employee> emps ,MyCompare<Employee> mycom){
		List<Employee> li = new ArrayList<>();
		for (Employee e : emps) {
			if (mycom.compare(e)) {
				li.add(e);
			}
		}
		return li;
	}
	
	public static void main(String[] args) {

		Employee[] emps = new Employee[] {  new Employee("张三", 20, 4000.00), 
											new Employee("李四", 21, 5000.00),
											new Employee("王五", 22, 8000.00),
											new Employee("赵六", 24, 1000.00),
											new Employee("呵呵", 30, 9000.00) };
		List<Employee> list = Arrays.asList(emps);
		//不使用lambda表达式 普通的写法
		//System.out.println(compareEmployeesWithAge(list, 20));
		//System.out.println(compareEmployeesWithSalary(list, 5000.00));
		
		//不使用lambda表达式 接口实现类（或者匿名内部类）的写法(策略设计模式)
		//System.out.println(compareEmployee(list, new MyCompareImpl()));
		//System.out.println(compareEmployee(list, new MyCompareImpl1()));
		
		
		//System.out.println(compareEmployee(list, (l)->l.getAge() > 20));
		
		list.stream().filter(e->e.getSalary()>5000.00).limit(2).forEach(System.out::println);
	
		
	}

}
