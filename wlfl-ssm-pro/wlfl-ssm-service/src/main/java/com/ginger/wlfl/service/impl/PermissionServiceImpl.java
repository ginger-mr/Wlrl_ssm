package com.ginger.wlfl.service.impl;

import com.ginger.wlfl.dao.IPermissionDao;
import com.ginger.wlfl.pojo.Permission;
import com.ginger.wlfl.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }

    @Override
    public Permission findById(String permissionId) {
        return permissionDao.findById(permissionId);
    }


}
