package interfaces;

public interface interfaceOfJava8 {
    /**
     * java8接口的新特性，default关键字，接口中的方法可以有方法体
     * @return
     */
    default String getName(){
        return "haha";
    }

    public static void show(){
        System.out.println("java8中接口可以有静态方法");
    }
}
interface MyInterface{

    default String getName(){
        return "haha";
    }
}
class MyClass{
    public String getName(){
        return "heiheihei";
    }
}

class SubClass /*extends MyClass*/ implements MyInterface,interfaceOfJava8{

    @Override
    public String getName() {
        return MyInterface.super.getName();
    }
}

class Test{
    //方法冲突类的方法优先原则
    public static void main(String[] args) {
        SubClass sub = new SubClass();
        System.out.println(sub.getName());
    }
}