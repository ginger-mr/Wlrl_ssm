package com.ginger.wlfl.service;

import com.ginger.wlfl.pojo.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll();
    public void saveRole(Role role);
    public Role findById(String roleId);
}
