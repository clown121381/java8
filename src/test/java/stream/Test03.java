package stream;

import lambda.entity.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test03 {

    List<Employee> employees = Arrays.asList(new Employee("张三", 20, 4000.00),
            new Employee("李四", 21, 5000.00),
            new Employee("王五", 22, 8000.00),
            new Employee("赵六", 24, 1000.00),
            new Employee("赵六", 24, 1000.00),
            new Employee("赵六", 24, 1000.00),
            new Employee("赵六", 24, 1000.00),
            new Employee("呵呵", 30, 9000.00));


    /*
        规约
            reduce
            可以将流中的冤死u反复结合起来得到一个值
     */

    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list.stream()
                //起始值   二元运算
                /*
                    1   x=0 流中取一个元素作为y
                    2   x=x+y   再取一个元素作为y
                    3   。。。。。。。。
                 */
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        Double reduce1 = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0,Double::sum);
        System.out.println(reduce1);
    }


    @Test
    public void test2(){
        /*
            收集
            collect 把操作收集成集合
         */

        List<String> list = employees.stream()
                .map(Employee::getName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);


        HashSet<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));


        //count
        Long collect1 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.counting());

        //求工资的平均值
        Double collect2 = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect2);

        //分组
        Map<Integer, List<Employee>> collect3 = employees.stream().collect(Collectors.groupingBy(Employee::getAge));


        //多级分组

        employees.stream()
                .collect(
                        Collectors.groupingBy(Employee::getSalary,
                                Collectors.groupingBy(Employee::getAge)));



    }
    @Test
    public void test3(){
         //分区分为true/false 两个区
        Map<Boolean, List<Employee>> collect = employees.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 20));
        System.out.println(collect.get(true));
    }

}
