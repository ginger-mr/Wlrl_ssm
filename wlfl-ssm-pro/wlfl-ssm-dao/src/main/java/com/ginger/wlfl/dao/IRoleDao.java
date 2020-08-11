package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    /**
     * 查询所有角色
     *
     * @return
     */
    @Select(" select * from role ")
    public List<Role> findAll();

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    @Select(" select * " +
            " from role " +
            " where id in ( " +
                " select roleid " +
                " from users_role " +
                " where userid = #{userId} " +
            " ) ")
    @Results(id="roleMap",value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", many = @Many(select = "com.ginger.wlfl.dao.IPermissionDao.findByRoleId")),
            @Result(property = "users", column = "id", many = @Many(select = "com.ginger.wlfl.dao.IUserDao.findByRoleId"))
    })
    public List<Role> findByUserId(String userId);

    /**
     * 保存角色
     * @param role
     */
    @Insert(" insert into role (rolename,roledesc) " +
            " values (#{roleName},#{roleDesc}) ")
    public void saveRole(Role role);

    /**
     *  根据id查询角色
      * @param roleId
     * @return
     */
    @Select("select * from role where id = #{roleId}")
    @ResultMap("roleMap")
    public Role findById(String roleId);
}
