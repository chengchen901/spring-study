package com.study.spring.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

//@Configuration
public class GlobalAsyncConfig {
    /**
     * 线程池维护线程的最小数量
     */
    private int corePoolSize =2;
    /**
     * 线程池维护线程的最大数量
     */
    private int maxPoolSize =2;
    /**
     * 队列最大长度
     */
    private int queueCapacity = 100;

    /**
     * 获取异步线程池执行对象
     */
    @Bean("asyncExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        //用于调试
        taskExecutor.setThreadNamePrefix("GlobalAsyncExecutor-");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //拒绝策略 CallerRunsPolicy 由调用线程处理该任务
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

    // 名字必须是applicationEventMulticaster，Spring内部通过这个名字来获取Bean的
    @Bean("applicationEventMulticaster")
    public SimpleApplicationEventMulticaster simpleApplicationEventMulticaster(Executor asyncExecutor) {
        // Spring 事件多播处理器
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        multicaster.setTaskExecutor(asyncExecutor);
        return multicaster;
    }
}
