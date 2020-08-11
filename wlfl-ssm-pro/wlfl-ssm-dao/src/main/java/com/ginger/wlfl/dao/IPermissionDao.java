package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface IPermissionDao {

    /**
     * 根据角色id查询资源权限
     * @param roleId
     * @return
     */
    @Select(" select * " +
            " from permission " +
            " where id in ( " +
                " select permissionid " +
                " from role_permission " +
                " where roleid = #{roleId} " +
            " ) ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "permissionName", column = "permissionName"),
            @Result(property = "url", column = "url")
    })
    public List<Permission> findByRoleId(String roleId);

    /**
     * 查询所有权限资源
     * @return
     */
    @Select(" select * from permission ")
    public List<Permission> findAll();

    /**
     * 保存资源权限
     * @param permission
     */
    @Insert(" insert into permission(permissionname,url) " +
            " values(#{permissionName},#{url}) ")
    public void savePermission(Permission permission);

    /**
     * 根据id查询权限资源
     * @param permissionId
     * @return
     */
    @Select(" select * from permission where id = #{permissionId} ")
    public Permission findById(String permissionId);

}
