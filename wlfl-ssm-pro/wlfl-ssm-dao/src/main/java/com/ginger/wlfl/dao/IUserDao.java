package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.Role;
import com.ginger.wlfl.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Select(" select * from users where username = #{username} ")
    @Results(id = "usersMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", many = @Many(select = "com.ginger.wlfl.dao.IRoleDao.findByUserId"))
    })
    public UserInfo findByUsername(String username);

    /**
     * 查询所有用户
     *
     * @return
     */
    @Select(" select * from users ")
    public List<UserInfo> findAll();

    /**
     * 保存用户
     *
     * @param userInfo
     */
    @Insert(" insert into users (email,username,password,phonenum,status) " +
            " values (#{email},#{username},#{password},#{phoneNum},#{status}) ")
    public void saveUserInfo(UserInfo userInfo);

    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    @Select(" select * from users where id = #{userId} ")
    @ResultMap("usersMap")
    public UserInfo findById(String userId);

    /**
     * 根据角色id查询用户
     *
     * @param roleId
     * @return
     */
    @Select(" select * " +
            " from users " +
            " where id in ( " +
                " select userid " +
                " from users_role " +
                " where roleid = #{roleId} " +
            " ) ")
    @ResultMap("usersMap")
    public List<UserInfo> findByRoleId(String roleId);

    /**
     * 根据用户的id查询该用户中没有的角色
     * @param userId
     * @return
     */
    @Select(" select * " +
            " from role " +
            " where id not in ( " +
                " select roleid " +
                " from users_role " +
                " where userid = #{userId} " +
            " ) ")
    public List<Role> findByIdAndOtherRole(String userId);

    /**
     * 添加角色到用户
     *
     * @param userId
     * @param roleId
     */
    @Insert(" insert into users_role(userid,roleid) " +
            " values(#{userId},#{roleId}) ")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
