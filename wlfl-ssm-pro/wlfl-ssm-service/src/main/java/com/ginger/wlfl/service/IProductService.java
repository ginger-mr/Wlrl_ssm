package com.ginger.wlfl.service;

import com.ginger.wlfl.pojo.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();

    public void saveProduct(Product product);
}
