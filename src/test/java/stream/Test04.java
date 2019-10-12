package stream;

import org.junit.Test;

import java.util.stream.LongStream;

/**
 * 并行流和串行流
 * 并行流就是把一个内容分成多个数据块，并用不同的线程进行处理操作
 *
 */
public class Test04 {

    @Test
    public void test1(){
        long reduce = LongStream.rangeClosed(0, 10000000000L)
                //转化为并行流进行计算
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(reduce);
    }
}
