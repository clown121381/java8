package stream;

import org.junit.Test;

import java.util.stream.LongStream;

/**
 * �������ʹ�����
 * ���������ǰ�һ�����ݷֳɶ�����ݿ飬���ò�ͬ���߳̽��д������
 *
 */
public class Test04 {

    @Test
    public void test1(){
        long reduce = LongStream.rangeClosed(0, 10000000000L)
                //ת��Ϊ���������м���
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(reduce);
    }
}
