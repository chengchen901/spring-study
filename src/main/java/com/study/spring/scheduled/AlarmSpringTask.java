package com.study.spring.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
//@EnableScheduling
public class AlarmSpringTask {
    /**默认是fixedDelay 上一次执行完毕时间后执行下一轮*/
    //@Scheduled(cron = "0/5 * * * * *")
    public void run() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用cron "+getTime(new Date()));
        Thread.sleep(6000);
    }

    /**fixedRate:上一次开始执行时间点之后5秒再执行*/
    //@Scheduled(fixedRate = 5000)
    public void run1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用fixedRate "+getTime(new Date()));
        Thread.sleep(6000);
    }

    /**fixedDelay:上一次执行完毕时间点之后5秒再执行*/
    //@Scheduled(fixedDelay = 5000)
    public void run2() throws InterruptedException {
        Thread.sleep(7000);
        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用fixedDelay  "+getTime(new Date()));
    }

    /**第一次延迟2秒后执行，之后按fixedDelay的规则每5秒执行一次*/
    @Scheduled(initialDelay = 2000, fixedDelay = 5000)
    public void run3() {
        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用initialDelay  "+getTime(new Date()));
    }

    private String getTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
