package com.ginger.wlfl.service.impl;

import com.ginger.wlfl.dao.IOrdersDao;
import com.ginger.wlfl.pojo.Orders;
import com.ginger.wlfl.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrdersServiceImpl implements IOrdersService {

    @Autowired
    IOrdersDao ordersDao;

    /**
     * 查询所有订单
     * @param page 当前页
     * @param size 当前页显示的条数
     * @return
     */
    public List<Orders> findAll(int page,int size) {
        /**
         * 注意：PageHelper.startPage(page, size)这段代码一定要写在去dao查询数据的前一行，
         * 中间不能有任何代码,不然会有问题。
         */
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) {
        return ordersDao.findById(ordersId);
    }
}
