package com.study.spring.aop;

public class GoodsServiceImpl implements GoodsService {
    @Override
    public void sendGoods(String userName, String goodsName) {
        System.out.println(String.format("userName={%s}, goodsName={%s}", userName, goodsName));
    }
}
