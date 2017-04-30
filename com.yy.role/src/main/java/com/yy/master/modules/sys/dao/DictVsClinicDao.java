/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.DictVsClinic;

/**
 * 字典与诊疗项目对照DAO接口
 * @author CTQ
 * @version 2017-03-18
 */
@MyBatisDao
public interface DictVsClinicDao extends CrudDao<DictVsClinic> {
	
}