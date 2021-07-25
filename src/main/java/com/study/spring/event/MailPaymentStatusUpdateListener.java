package com.study.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 有序监听器，邮件服务监听器
 */
@Service
public class MailPaymentStatusUpdateListener extends AbstractPaymentStatusListener implements SmartApplicationListener {
    // 排序，数字越小执行的优先级越高
    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    @Async
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("Thread: " + Thread.currentThread().getName()+
                " 邮件服务, 收到支付状态更新的通知. " + applicationEvent);
    }
}
