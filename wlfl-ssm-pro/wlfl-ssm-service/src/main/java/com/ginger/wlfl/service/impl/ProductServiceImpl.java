package com.ginger.wlfl.service.impl;

import com.ginger.wlfl.dao.IProductDao;
import com.ginger.wlfl.pojo.Product;
import com.ginger.wlfl.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }
}
