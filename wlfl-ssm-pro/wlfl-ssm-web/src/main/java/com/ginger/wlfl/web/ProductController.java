package com.ginger.wlfl.web;

import com.ginger.wlfl.pojo.Product;
import com.ginger.wlfl.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @RequestMapping("/findAll")
    @ResponseBody
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView();
        List<Product> productList = productService.findAll();
        mav.addObject("productList", productList);
        mav.setViewName("product-list");
        return mav;
    }
}
