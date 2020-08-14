package com.ginger.wlfl.service.impl;

import com.ginger.wlfl.dao.ISysLogDao;
import com.ginger.wlfl.pojo.SysLog;
import com.ginger.wlfl.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    ISysLogDao sysLogDao;

    @Override
    public void saveSysLog(SysLog syslog) {
        sysLogDao.saveSysLog(syslog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

}
