package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    /**
     * 根据id查询商品
     * @param productId
     * @return
     */
    @Select("select * from product where id = #{productId}")
    public Product findById(String productId);


    /**
     * 查询所有商品
     * @return
     */
    @Select("select * from product")
    public List<Product> findAll();

    /**
     * 插入商品
     * @param product
     */
    @Insert("insert into" +
            "  product(" +
            "    productNum," +
            "    productName," +
            "    cityName," +
            "    departureTime," +
            "    productPrice," +
            "    productDesc," +
            "    productStatus" +
            "  )" +
            "values(" +
            "    #{ productNum }," +
            "    #{ productName }," +
            "    #{ cityName }," +
            "    #{ departureTime }," +
            "    #{ productPrice }," +
            "    #{ productDesc }," +
            "    #{ productStatus }" +
            "  )")
    public void saveProduct(Product product);
}
