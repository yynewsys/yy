/**
 * jims.
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.Dict;
import com.yy.master.modules.sys.entity.SysService;

import java.util.List;

/**
 * 服务DAO接口
 * @author 赵宁
 * @version 2017-02-16
 */
@MyBatisDao
public interface SysServiceDao extends CrudDao<SysService> {

	
}
