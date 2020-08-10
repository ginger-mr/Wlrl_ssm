package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    /**
     * 根据id查询会员
     * @param memberId
     * @return
     */
    @Select(" select * from member where id = #{memberId} ")
    public Member findById(String memberId);
}
