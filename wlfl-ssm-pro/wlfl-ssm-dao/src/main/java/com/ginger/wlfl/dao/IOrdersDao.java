package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    /**
     * 查询所有订单
     * @return
     */
    @Select(" select * from orders ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", one = @One(select = "com.ginger.wlfl.dao.IProductDao.findById"))
    })
    public List<Orders> findAll();

    /**
     * 根据id查询订单详情
     * @param ordersId
     * @return
     */
    @Select(" select * from orders where id = #{ordersId} ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", one = @One(select = "com.ginger.wlfl.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", one = @One(select = "com.ginger.wlfl.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", many = @Many(select = "com.ginger.wlfl.dao.ITravellerDao.findTravellerOrdersById"))
    })
    public Orders findById(String ordersId);

}
