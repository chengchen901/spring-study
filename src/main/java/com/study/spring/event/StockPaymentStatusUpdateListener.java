package com.study.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 无序事件监听器，库存服务监听器
 */
@Service
public class StockPaymentStatusUpdateListener implements ApplicationListener<PaymentStatusUpdateEvent> {
    @Override
    @Async
    public void onApplicationEvent(PaymentStatusUpdateEvent paymentStatusUpdateEvent) {
        System.out.println("Thread: " + Thread.currentThread().getName() +
                " 库存服务, 收到支付状态更新的事件. " + paymentStatusUpdateEvent);
    }
}
