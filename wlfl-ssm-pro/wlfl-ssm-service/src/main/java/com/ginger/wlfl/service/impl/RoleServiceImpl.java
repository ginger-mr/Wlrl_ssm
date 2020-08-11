package com.ginger.wlfl.service.impl;

import com.ginger.wlfl.dao.IRoleDao;
import com.ginger.wlfl.pojo.Role;
import com.ginger.wlfl.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }
}
