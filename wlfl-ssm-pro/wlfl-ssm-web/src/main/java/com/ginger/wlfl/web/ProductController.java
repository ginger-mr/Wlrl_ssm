package com.ginger.wlfl.web;

import com.ginger.wlfl.pojo.Product;
import com.ginger.wlfl.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 查询所有商品
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView();
        List<Product> productList = productService.findAll();
        mav.addObject("productList", productList);
        mav.setViewName("product-list");
        return mav;
    }

    /**
     * 添加商品
     * @param product
     * @return
     */
    @RequestMapping("/saveProduct.do")
    public String saveProduct(Product product) {
        ModelAndView mav = new ModelAndView();
        productService.saveProduct(product);
        return "redirect:findAll.do";
    }
}