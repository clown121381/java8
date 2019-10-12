package stream;

import lambda.entity.Employee;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream API 简单高效的操作数据 产生新的流不会影响原来的数据源
 * 注意：
 * Stream 不会储存元素
 * Stream 不会改变元对象，会产生一个持有结果的新流
 * Stream 操作时延迟执行的，这意味着他们会等需要结果的时候才执行操作
 *
 * 一、Stream 的三个步骤
 *
 * 1、创建Stream
 *
 * 2、中间操作
 *
 * 3、终止操作（终端操作）
 *
 */
public class Test01 {

    @Test
    public void test1(){
        //创建Stream
        //1 集合创建
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //2 Arrays数组创建
        IntStream stream1 = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0});

        //3 Stream 的静态of方法
        Stream<String> stream2 = Stream.of("a", "b", "c");

        //4 创建无限流 迭代
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        iterate.limit(10).forEach(System.out::println);
        //   生成操作
        Stream.generate(()->Math.random()).forEach(System.out::println);
    }


    List<Employee> employees = Arrays.asList(new Employee("张三", 20, 4000.00),
            new Employee("李四", 21, 5000.00),
            new Employee("王五", 22, 8000.00),
            new Employee("赵六", 24, 1000.00),
            new Employee("赵六", 24, 1000.00),
            new Employee("赵六", 24, 1000.00),
            new Employee("赵六", 24, 1000.00),
            new Employee("呵呵", 30, 9000.00));


    @Test
    public void test2(){
        //中间操作
        /*
         * 赛选与切片
         * filter--接受Lambda，从流中排除某些元素
         * limit--截断流，使其元素不超过给定的数量。
         * skip(n)--跳过元素，返回一个扔掉了前n个元素的流，若不足n个元素，则返回一个空流，与limit(n) 互补
         * distinct--赛选，通过流所生成的元素的hashcode()和equals()方法去除重复元素
         *
         * 懒加载 重甲操作只有当终止操作时，才一次性执行所有操作
         *
         */

        employees.stream()
                //中间操作filter 需要一个断言接口
                .filter(emp->emp.getAge()>25)
                //终止操作 一次性执行所有的中间操作 惰性求值类似懒加载
                .forEach(emp-> System.out.println(emp));
    }
    @Test
    public void test3(){

        employees.stream()
                .filter(e->e.getSalary()>5000)
                //limit 限制流 量截断流，使其元素不超过给定的数量
                .limit(1)
                .forEach(System.out::print);
    }

    @Test
    public void test4(){
        employees.stream()
                //通过hashcode + equals 比较元素并且去重
                .distinct()
                .forEach(System.out::print);
    }
    @Test
    public void test5(){
        employees.stream()
                .skip(2)
                .forEach(System.out::print);
    }
}
