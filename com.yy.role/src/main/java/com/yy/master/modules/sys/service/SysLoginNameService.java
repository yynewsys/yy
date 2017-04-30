/**
 * jims
 */
package com.yy.master.modules.sys.service;

import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.dao.SysLoginNameDao;
import com.yy.master.modules.sys.entity.SysLoginName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 登陆用户名Service
 * @author CTQ
 * @version 2017-02-17
 */
@Service
@Transactional(readOnly = true)
public class SysLoginNameService extends CrudService<SysLoginNameDao, SysLoginName> {
    @Autowired
    private SysLoginNameDao sysLoginNameDao;

    public String findValidate(String loginName){
        return sysLoginNameDao.findValidate(loginName);
    }

	
}