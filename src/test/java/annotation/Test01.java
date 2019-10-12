package annotation;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.lang.reflect.Method;

public class Test01 {


    @Test
    public void test() throws NoSuchMethodException {
        Class<Test01> test01Class = Test01.class;

        Method show = test01Class.getMethod("show");
        MyAnnotation[] annotations = show.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation a:annotations){
            System.out.println(a.value());
        }

    }

    //���ظ�ע��
    @MyAnnotation("hello")
    @MyAnnotation("asd")
    @MyAnnotation("adszxvc")
    @MyAnnotation("gfdag")
    @MyAnnotation("dfgjfghj")
    public void show(){

    }

    private @NotNull Object obj;
    //����ע�⵽���Ͳ���
    public void hahah(@MyAnnotation int a){}
}
