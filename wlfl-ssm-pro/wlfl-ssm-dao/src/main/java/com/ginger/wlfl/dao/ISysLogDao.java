package com.ginger.wlfl.dao;

import com.ginger.wlfl.pojo.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {

    /**
     * 保存系统操作日志
     * @param syslog
     */
    @Insert(" insert into syslog(visitTime,username,ip,url,executionTime,method) " +
            " values (#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method}) ")
    public void saveSysLog(SysLog syslog);

    /**
     * 查询所有系统操作日志
     * @return
     */
    @Select("select * from syslog")
    public List<SysLog> findAll();
}
