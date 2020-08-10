package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select(" select * from users where username = #{username} ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id",many = @Many(select = "com.ginger.wlfl.dao.IRoleDao.findByUserId"))
    })
    public UserInfo findByUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    @Select(" select * from users ")
    public List<UserInfo> findAll();
}
