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
 * java8中新定义了时间日期类型，
 */
public class Test01 {

    /*
        传统的时期时间操作，线程不安全
     */
    @Test
    public void test01() throws ExecutionException, InterruptedException {
        /*
            异常
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        ExecutorService pool = Executors.newFixedThreadPool(10);
//        线程不安全
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

        //java8全新的实例线程安全的date
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
        // Instant 时间戳毫秒值
        Instant ins = Instant.now();    //utc时区的时间      1970-01-01T00:00:00Z
        System.out.println(ins);

        OffsetDateTime offsetDateTime = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(offsetDateTime.toEpochSecond());

        Instant ins1 = Instant.ofEpochSecond(0);
        System.out.println(ins1);
    }

    @Test
    public void test5() throws InterruptedException {
//        Duration  计算时间的间隔
//        Period    计算日期的间隔

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

        //指定时间
        LocalDateTime dateTime1 = dateTime.withDayOfMonth(10);
        System.out.println(dateTime1);

        //时间矫正器 指定时间，一周之内的周日
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
        //格式化日期时间
        DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;

        LocalDateTime ldt = LocalDateTime.now();

        String format = isoDateTime.format(ldt);

        System.out.println(format);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH：mm：ss");

        String format1 = dateTimeFormatter.format(ldt);
        LocalDateTime parse = ldt.parse(format1, dateTimeFormatter);

        System.out.println(format1);
        System.out.println(parse);
    }
}
