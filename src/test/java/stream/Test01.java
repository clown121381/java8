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
 * Stream API �򵥸�Ч�Ĳ������� �����µ�������Ӱ��ԭ��������Դ
 * ע�⣺
 * Stream ���ᴢ��Ԫ��
 * Stream ����ı�Ԫ���󣬻����һ�����н��������
 * Stream ����ʱ�ӳ�ִ�еģ�����ζ�����ǻ����Ҫ�����ʱ���ִ�в���
 *
 * һ��Stream ����������
 *
 * 1������Stream
 *
 * 2���м����
 *
 * 3����ֹ�������ն˲�����
 *
 */
public class Test01 {

    @Test
    public void test1(){
        //����Stream
        //1 ���ϴ���
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //2 Arrays���鴴��
        IntStream stream1 = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0});

        //3 Stream �ľ�̬of����
        Stream<String> stream2 = Stream.of("a", "b", "c");

        //4 ���������� ����
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        iterate.limit(10).forEach(System.out::println);
        //   ���ɲ���
        Stream.generate(()->Math.random()).forEach(System.out::println);
    }


    List<Employee> employees = Arrays.asList(new Employee("����", 20, 4000.00),
            new Employee("����", 21, 5000.00),
            new Employee("����", 22, 8000.00),
            new Employee("����", 24, 1000.00),
            new Employee("����", 24, 1000.00),
            new Employee("����", 24, 1000.00),
            new Employee("����", 24, 1000.00),
            new Employee("�Ǻ�", 30, 9000.00));


    @Test
    public void test2(){
        //�м����
        /*
         * ��ѡ����Ƭ
         * filter--����Lambda���������ų�ĳЩԪ��
         * limit--�ض�����ʹ��Ԫ�ز�����������������
         * skip(n)--����Ԫ�أ�����һ���ӵ���ǰn��Ԫ�ص�����������n��Ԫ�أ��򷵻�һ����������limit(n) ����
         * distinct--��ѡ��ͨ���������ɵ�Ԫ�ص�hashcode()��equals()����ȥ���ظ�Ԫ��
         *
         * ������ �ؼײ���ֻ�е���ֹ����ʱ����һ����ִ�����в���
         *
         */

        employees.stream()
                //�м����filter ��Ҫһ�����Խӿ�
                .filter(emp->emp.getAge()>25)
                //��ֹ���� һ����ִ�����е��м���� ������ֵ����������
                .forEach(emp-> System.out.println(emp));
    }
    @Test
    public void test3(){

        employees.stream()
                .filter(e->e.getSalary()>5000)
                //limit ������ ���ض�����ʹ��Ԫ�ز���������������
                .limit(1)
                .forEach(System.out::print);
    }

    @Test
    public void test4(){
        employees.stream()
                //ͨ��hashcode + equals �Ƚ�Ԫ�ز���ȥ��
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
