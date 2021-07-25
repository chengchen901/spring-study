package com.study.spring.event;

import com.study.spring.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class SpringEventTests {

    @Autowired
    PaymentService service;

    @Test
    void test() {
        service.pay(1, "支付成功");
    }
}
