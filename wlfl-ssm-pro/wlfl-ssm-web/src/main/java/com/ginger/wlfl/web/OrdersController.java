package com.ginger.wlfl.web;


import com.ginger.wlfl.pojo.Orders;
import com.ginger.wlfl.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    IOrdersService ordersService;

    /*
        未分页查询所有订单
        @RequestMapping("/findAll.do")
        public ModelAndView findAll() {
            ModelAndView mav = new ModelAndView();
            List<Orders> ordersList = ordersService.findAll();
            mav.addObject("ordersList", ordersList);
            mav.setViewName("orders-list");
            return mav;
        }
    */

    /**
     * 分页查询所有订单
     * @param page 当前页
     * @param size 当前页显示的条数
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page,
                                @RequestParam(name = "size", required = true, defaultValue = "5") int size) {
        ModelAndView mav = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);
        /**
         *  创建PageInfo对象，并将查询到的数据ordersList作为构造参数传递到PageInfo对象中，
         *  其实PageInfo对象，就是以前的PageBean对象，里面封装着页面所需要的信息，可以查看源码。
         */
        PageInfo pageInfo = new PageInfo(ordersList);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("orders-list");
        return mav;
    }

    /**
     * 根据id查询订单详情
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mav = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mav.addObject("orders", orders);
        mav.setViewName("orders-show");
        return mav;
    }
}
