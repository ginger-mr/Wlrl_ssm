package com.ginger.wlfl.service;

import com.ginger.wlfl.pojo.Permission;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface IPermissionService {
    public List<Permission> findAll();
    public void savePermission(Permission permission);
    public Permission findById(String permissionId);
}
