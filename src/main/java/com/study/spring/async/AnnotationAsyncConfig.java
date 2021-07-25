package com.study.spring.async;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AnnotationAsyncConfig implements AsyncConfigurer {
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
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        //用于调试
        taskExecutor.setThreadNamePrefix("AnnotationAsyncExecutor-");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //拒绝策略 CallerRunsPolicy 由调用线程处理该任务
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}
