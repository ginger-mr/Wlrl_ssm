package com.ginger.wlfl.web;


import com.ginger.wlfl.pojo.Orders;
import com.ginger.wlfl.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    IOrdersService ordersService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mav.addObject("ordersList", ordersList);
        mav.setViewName("orders-list");
        return mav;
    }
}
