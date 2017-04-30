/**
 * jims.
 */
package com.yy.master.modules.gen.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.gen.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableColumnDao extends CrudDao<GenTableColumn> {
	
	public void deleteByGenTableId(String genTableId);
}
