package dateTimeApi;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * java8���¶�����ʱ���������ͣ�
 */
public class Test01 {

    /*
        ��ͳ��ʱ��ʱ��������̲߳���ȫ
     */
    @Test
    public void test01() throws ExecutionException, InterruptedException {
        /*
            �쳣
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        ExecutorService pool = Executors.newFixedThreadPool(10);
//        �̲߳���ȫ
//        Callable<Date> call = ()-> simpleDateFormat.parse("20161218");
        Callable<Date> call = ()-> DateFormatThreadLocal.convert("20161218");
        List<Future<Date>> results = new ArrayList<>();
        for(int i = 0;i < 10;i ++){
            results.add(pool.submit(call));
        }
        for(Future<Date> f:results){
            System.out.println(f.get());
        }
        pool.shutdown();
    }
    @Test
    public void test02() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        //java8ȫ�µ�ʵ���̰߳�ȫ��date
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> call = ()->LocalDate.parse("20161218",dateTimeFormatter);


        List<Future<LocalDate>> results = new ArrayList<>();
        for(int i = 0;i < 10;i ++){
            results.add(pool.submit(call));
        }
        for(Future<LocalDate> f:results){
            System.out.println(f.get());
        }
        pool.shutdown();
    }

    @Test
    public void test3(){
        /*
            LocalDate
            LocalTime
            LocalDateTime
         */
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        LocalDateTime dateTime1 = LocalDateTime.of(2019,10,19,22,12,10);
        System.out.println(dateTime1);

        System.out.println(dateTime.plusYears(2));
        System.out.println(dateTime.minusMonths(2));

        System.out.println(dateTime.getMonthValue());
        System.out.println(dateTime.getMonth().getValue());
    }
    @Test
    public void test4(){
        // Instant ʱ�������ֵ
        Instant ins = Instant.now();    //utcʱ����ʱ��      1970-01-01T00:00:00Z
        System.out.println(ins);

        OffsetDateTime offsetDateTime = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(offsetDateTime.toEpochSecond());

        Instant ins1 = Instant.ofEpochSecond(0);
        System.out.println(ins1);
    }

    @Test
    public void test5() throws InterruptedException {
//        Duration  ����ʱ��ļ��
//        Period    �������ڵļ��

        Instant ins1 = Instant.now();
        TimeUnit.SECONDS.sleep(5);
        Instant ins2 = Instant.now();

        Duration between = Duration.between(ins1, ins2);
        System.out.println(between.getSeconds());
        System.out.println(between.getNano());
        System.out.println(between.toMillis());
    }

    @Test
    public void test6(){
        LocalDateTime dateTime = LocalDateTime.now();

        //ָ��ʱ��
        LocalDateTime dateTime1 = dateTime.withDayOfMonth(10);
        System.out.println(dateTime1);

        //ʱ������� ָ��ʱ�䣬һ��֮�ڵ�����
        LocalDateTime dateTime2 = dateTime1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(dateTime2);

        LocalDateTime with = dateTime1.with((l) -> {
            LocalDateTime l4 = (LocalDateTime) l;
            DayOfWeek day = l4.getDayOfWeek();
            if (day.equals(DayOfWeek.FRIDAY)) {
                return l4.plusDays(3);
            } else {
                return l4.plusDays(1);
            }
        });
        System.out.println(with);
    }

    @Test
    public void test7(){
        //��ʽ������ʱ��
        DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;

        LocalDateTime ldt = LocalDateTime.now();

        String format = isoDateTime.format(ldt);

        System.out.println(format);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy��MM��dd�� HH��mm��ss");

        String format1 = dateTimeFormatter.format(ldt);
        LocalDateTime parse = ldt.parse(format1, dateTimeFormatter);

        System.out.println(format1);
        System.out.println(parse);
    }
}
