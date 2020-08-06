package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {
    /**
     * 查询所有商品
     * @return
     */
    @Select("SELECT * FROM product")
    public List<Product> findAll();

}
