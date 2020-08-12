package com.ginger.wlfl.service;

import com.ginger.wlfl.pojo.Permission;
import com.ginger.wlfl.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll();
    public void saveRole(Role role);
    public Role findById(String roleId);
    public List<Permission> findByIdAndOtherPermission(String roleId);
    public void addPermissionToRole(String roleId,String[] ids);
}
