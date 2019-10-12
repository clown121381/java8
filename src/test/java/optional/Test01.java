package optional;

import lambda.entity.Employee;
import org.junit.Test;

import java.util.Optional;

public class Test01 {
    /*
        optional �����࣬��װ�������nullpointer�쳣
        optional���÷���

        Optional.of(T t):����һ��Optionalʵ��
        Optional.empty():����һ���յ�Optionalʵ��
        Optional.ofNullable(T t):��T��Ϊnull����Optionalʵ�������򴴽���ʵ��
        isPresent():�ж��Ƿ����ֵ
        orElse(T t):������ö������ֵ�����ظ�ֵ�����򷵻�s��ȡ��ֵ
        orElseGet(Supplier s):������ö������ֵ���򷵻ظ�ֵ�����򷵻�s��ȡ��ֵ
        map(Function f):�����ֵ���䴦���󷵻ش��������Optional�����򷵻�OPtional.empty()
        flatMap(Function mapper):��map���ƣ�Ҫ�󷵻�ֵ������Optional
     */
    @Test
    public void test1() {
        Optional<Employee> op = Optional.of(new Employee());
        Employee emp = op.get();
        System.out.println(emp);
    }

    @Test
    public void test2() {
        Optional<Employee> emp = Optional.empty();
        System.out.println(emp.get());
    }

    @Test
    public void test3() {
        Optional<Employee> emp = Optional.ofNullable(null);
//        Optional<Employee> emp1 = Optional.ofNullable(new Employee());
//        if(emp1.isPresent()){
//            System.out.println(emp1.get());
//        }
//        System.out.println(emp1.get());
//
//        Employee emp3 = emp.orElse(new Employee());
//        System.out.println(emp3);

//      ����ʽ�ӿ�
        Employee employee = emp.orElseGet(Employee::new);

    }

    @Test
    public void test4(){
        Optional<Employee> emp = Optional.ofNullable(new Employee("zhansgan",18,8888.8));

        Optional<String> s = emp.map(Employee::getName);
        System.out.println(s.get());

        Optional<String> s1 = emp.flatMap(e -> Optional.of(e.getName()));

        System.out.println(s1.get());
    }
}





