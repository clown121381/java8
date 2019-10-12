# Lambda表达式

---

1.  ***基础语法***：java8中引入了一个新的操作符“**->**”,该操作符将lambda表达式拆分为两部分
   + 左侧：Lambda表达式的**参数列表**，接口中方法的**参数列表**
   + 右侧：Lambda表达式要**执行操作**，接口的方法**重写**的操作

2. ***函数式接口***：只有一个抽象方法的接口.lambda表达式需要**函数式接口**的支持

   + 匿名内部类使用外部类的成员变量，成员变量必须为**final**

     + 语法格式一、无参数无返回值

       ```java
       ()->System.out.println("hello world");
       ```

     + 语法格式二、一个参数无返回值小括号可以**省略**

       ```java
       (a)->System.out.println("hello world");
       ```

     + 语法格式三、有返回值有两个以上的参数lambda体中有多条语句（**只有一条语句{}可以省略不写**）

       ```java
       A a1 = (a,b,c,d)->{
           System.out.println(a+b+c+d);
           System.out.println("hello world");
           return new A();
       }
       ```

     + 语法格式四、参数列表中的**数据类型**可以省略不写，jvm的编译器可以根据上下文推断出参数的数据类型（类型推断）

       四大核心函数是接口

3. ***java8四大核心函数式接口***

   Consumer<T> :**消费型接口**

   ```java
   void accept(T t);
   ```

   Supplier<T> :**供给型接口**

   ```java
   T get();
   ```

   Function<T,R>:**函数型接口**

   ```java
   R apply(T t);
   ```

   Predicate<T> :**断言型接口**

   ```java
   boolean test(T t);
   ```

4. ***demo案例***

   ```java
   //lambda表达式
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
   ```

   ````java
   //四大函数接口
   public class Test03 {
   	@Test
   	public void test1(){
   		happy(1000,m->System.out.println("m:"+m));
   	}
   	
   	public void happy(double money,Consumer<Double> con){
   		con.accept(money);
   	}
   	
   	
   	@Test
   	public void test2(){
   		List<Integer> li = getNumber(10,()->(int)Math.random()*100);
   		System.out.println(li);
   	
   	}
   
   	private List<Integer> getNumber(int num,Supplier<Integer> sup) {
   		List<Integer> list = new ArrayList<>();
   		for(int i = 0; i < num;i ++){
   			list.add(sup.get());
   		}
   		return list;
   	}
   	
   	
   	@Test
   	public void test3(){
   		String s = strHandler("hello world",(str)->str.trim().toUpperCase());
   		System.out.println(s);
   	}
   	
   	public String strHandler(String str,Function<String,String> fun){
   		return fun.apply(str);
   	}
   	@Test
   	public void test4(){
   		List<String> li = Arrays.asList("hello","world","nihao","shit","nibuhao");
   		List<String> newli = filterStr(li,s->s.length()>5);
   		System.out.println(newli);
   	
   	}
   	public List<String> filterStr(List<String> list,Predicate<String> pre){
   		List<String> strList = new ArrayList<>();
   		for(String s : list){
   			if(pre.test(s)){
   				strList.add(s);
   			}
   		}
   		return strList;
   	}
   }
   ````

   

   ```java
   //方法引用
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
   ```

   
