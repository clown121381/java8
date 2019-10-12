package interfaces;

public interface interfaceOfJava8 {
    /**
     * java8�ӿڵ������ԣ�default�ؼ��֣��ӿ��еķ��������з�����
     * @return
     */
    default String getName(){
        return "haha";
    }

    public static void show(){
        System.out.println("java8�нӿڿ����о�̬����");
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
    //������ͻ��ķ�������ԭ��
    public static void main(String[] args) {
        SubClass sub = new SubClass();
        System.out.println(sub.getName());
    }
}