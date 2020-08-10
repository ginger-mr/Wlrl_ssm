package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {

    /**
     * 根据用户id查询角色
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
    public List<Role> findByUserId(String userId);
}
