package stream;

import lambda.entity.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test03 {

    List<Employee> employees = Arrays.asList(new Employee("����", 20, 4000.00),
            new Employee("����", 21, 5000.00),
            new Employee("����", 22, 8000.00),
            new Employee("����", 24, 1000.00),
            new Employee("����", 24, 1000.00),
            new Employee("����", 24, 1000.00),
            new Employee("����", 24, 1000.00),
            new Employee("�Ǻ�", 30, 9000.00));


    /*
        ��Լ
            reduce
            ���Խ����е�ԩ��u������������õ�һ��ֵ
     */

    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list.stream()
                //��ʼֵ   ��Ԫ����
                /*
                    1   x=0 ����ȡһ��Ԫ����Ϊy
                    2   x=x+y   ��ȡһ��Ԫ����Ϊy
                    3   ����������������
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
            �ռ�
            collect �Ѳ����ռ��ɼ���
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

        //���ʵ�ƽ��ֵ
        Double collect2 = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect2);

        //����
        Map<Integer, List<Employee>> collect3 = employees.stream().collect(Collectors.groupingBy(Employee::getAge));


        //�༶����

        employees.stream()
                .collect(
                        Collectors.groupingBy(Employee::getSalary,
                                Collectors.groupingBy(Employee::getAge)));



    }
    @Test
    public void test3(){
         //������Ϊtrue/false ������
        Map<Boolean, List<Employee>> collect = employees.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 20));
        System.out.println(collect.get(true));
    }

}
