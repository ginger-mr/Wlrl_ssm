package com.ginger.wlfl.web;

import com.ginger.wlfl.pojo.SysLog;
import com.ginger.wlfl.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findAll();
        mav.addObject("sysLogList",sysLogList );
        mav.setViewName("syslog-list");
        return mav;
    }
}
