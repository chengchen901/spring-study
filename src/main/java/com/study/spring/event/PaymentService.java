package com.study.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    ApplicationContext applicationContext;

    public void pay(int id, String status) {
        // ...... 省略处理业务逻辑代码
        PaymentInfo paymentInfo = new PaymentInfo(id, status);
        // 发布事件
        applicationContext.publishEvent(new PaymentStatusUpdateEvent(paymentInfo));
    }
}
