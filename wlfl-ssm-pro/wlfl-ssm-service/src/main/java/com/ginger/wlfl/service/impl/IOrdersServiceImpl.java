package com.ginger.wlfl.service.impl;

import com.ginger.wlfl.dao.IOrdersDao;
import com.ginger.wlfl.pojo.Orders;
import com.ginger.wlfl.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrdersServiceImpl implements IOrdersService{

    @Autowired
    IOrdersDao ordersDao;

    public List<Orders> findAll(){
        return ordersDao.findAll();
    }
}
