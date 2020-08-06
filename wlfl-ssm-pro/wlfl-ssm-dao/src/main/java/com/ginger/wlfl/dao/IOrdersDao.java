package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.Orders;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrdersDao {

    /**
     * 查询所有订单
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime" ,column = "orderTime"),
            @Result(property = "orderStatus" ,column = "orderStatus"),
            @Result(property = "peopleCount" ,column = "peopleCount"),
            @Result(property = "payType" ,column = "payType"),
            @Result(property = "orderDesc" ,column = "orderDesc"),
            @Result(property = "product",column = "productId",one = @One(select = "com.ginger.wlfl.dao.IProductDao.findById"))
    })
    public List<Orders> findAll();

}
