package com.study.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @AroundTag
    @Override
    public String addAddress(long customerId, String userName, String address) {
        System.out.println(String.format("调用成功addAddress,当前请求参数coustomerId={%d}, userName={%s}, address={%s}",
                customerId, userName, address));
        // 抛出一个异常,验证最终增强和异常增强
        // int a = 10 / 0; // 除数不能为0
        return "【正常返回】";
    }
}
