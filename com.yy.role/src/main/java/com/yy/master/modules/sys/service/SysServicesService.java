package com.yy.master.modules.sys.service;

import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.dao.SysServiceDao;
import com.yy.master.modules.sys.entity.SysService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017-02-16.
 * 服务service
 * @author 赵宁
 */
@Service
@Transactional(readOnly = true)
public class SysServicesService extends CrudService<SysServiceDao,SysService>{

}
