/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.PerformFreqDict;

/**
 * 频次字典表DAO接口
 * @author 刘方舟
 * @version 2017-03-15
 */
@MyBatisDao
public interface PerformFreqDictDao extends CrudDao<PerformFreqDict> {
	
}