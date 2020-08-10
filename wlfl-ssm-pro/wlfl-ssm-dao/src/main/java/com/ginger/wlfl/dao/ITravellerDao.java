package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {
    /**
     * 根据订单id查询查询旅客
     * @param id
     * @return
     */
    @Select(" SELECT * " +
            " FROM traveller " +
            " WHERE id in ( " +
                " SELECT travellerid " +
                " FROM order_traveller " +
                " WHERE orderid = #{ordersId} " +
            " ) ")
    public List<Traveller> findTravellerOrdersById(String id);
}
