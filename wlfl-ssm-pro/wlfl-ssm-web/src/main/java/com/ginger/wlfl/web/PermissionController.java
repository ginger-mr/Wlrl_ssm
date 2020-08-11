package com.ginger.wlfl.web;

import com.ginger.wlfl.pojo.Permission;
import com.ginger.wlfl.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    /**
     * 查询所有资源权限
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mav.addObject("permissionList", permissionList);
        mav.setViewName("permission-list");
        return mav;
    }

    /**
     * 保存资源权限
     *
     * @param permission
     * @return
     */
    @RequestMapping("/save.do")
    public String savePermission(Permission permission) {
        permissionService.savePermission(permission);
        return "redirect:findAll.do";
    }

    /**
     * 根据id查询权限资源
     * @param permissionId
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id") String permissionId) {
        ModelAndView mav = new ModelAndView();
        Permission permission = permissionService.findById(permissionId);
        mav.addObject("permission",permission);
        mav.setViewName("permission-show");
        return mav;
    }
}
