package optional;

import lambda.entity.Employee;
import org.junit.Test;

import java.util.Optional;

public class Test01 {
    /*
        optional 容器类，封装对象避免nullpointer异常
        optional常用方法

        Optional.of(T t):创建一个Optional实例
        Optional.empty():创建一个空的Optional实例
        Optional.ofNullable(T t):若T不为null创建Optional实例，否则创建空实例
        isPresent():判断是否包含值
        orElse(T t):如果调用对象包含值，返回该值，否则返回s获取的值
        orElseGet(Supplier s):如果调用对象包含值，则返回该值，否则返回s获取的值
        map(Function f):如果有值对其处理，斌返回处理结果后的Optional，否则返回OPtional.empty()
        flatMap(Function mapper):于map类似，要求返回值必须是Optional
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

//      函数式接口
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





