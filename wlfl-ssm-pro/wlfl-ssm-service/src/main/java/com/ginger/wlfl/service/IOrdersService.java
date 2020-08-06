package com.ginger.wlfl.service;

import com.ginger.wlfl.pojo.Orders;

import java.util.List;

public interface IOrdersService {
    public List<Orders> findAll(int page,int size);
}
