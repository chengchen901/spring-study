package com.study.spring.event;

import com.study.spring.Application;
import com.study.spring.aop.CustomerService;
import com.study.spring.aop.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class DeclareParentsTests {

    @Autowired
    private CustomerService customerService;

    @Test
    void test() {
        final String result = customerService.addAddress(12, "hash", "整个世界都是我的");
        System.out.println("调用成功addAddress,返回值是:" + result);
        // 引用增强验证:customerService引入了GoodsService的功能
        System.out.println("【引用增强】实现:customerService引入了GoodsService的功能");
        GoodsService goodsService = (GoodsService) customerService;
        goodsService.sendGoods("hash", "送你整个世界");
    }
}
