package com.ginger.wlfl.service;

import com.ginger.wlfl.pojo.SysLog;

import java.util.List;

public interface ISysLogService {
    public void saveSysLog(SysLog syslog);
    public List<SysLog> findAll();
}
