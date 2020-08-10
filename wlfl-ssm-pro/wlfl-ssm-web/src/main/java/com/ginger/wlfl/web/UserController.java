package com.ginger.wlfl.web;

import com.ginger.wlfl.pojo.UserInfo;
import com.ginger.wlfl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView();
        List<UserInfo> userInfos = userService.findAll();
        mav.addObject("userInfos",userInfos);
        mav.setViewName("user-list");
        return mav;
    }

    /**
     * 保存用户
     * @param userInfo
     * @return
     */
    @RequestMapping("/save.do")
    public String saveUserInfo(UserInfo userInfo){
        userService.saveUserInfo(userInfo);
        return "redirect:findAll.do";
    }



}
