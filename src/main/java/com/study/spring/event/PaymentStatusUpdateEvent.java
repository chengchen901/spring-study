package com.study.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 支付状态更新的事件，以PaymentInfo作为传输的载体。
 */
public class PaymentStatusUpdateEvent extends ApplicationEvent {
    public PaymentStatusUpdateEvent(PaymentInfo source) {
        super(source);
    }
}
