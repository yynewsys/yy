/**
 * jims
 */
package com.yy.master.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.master.common.persistence.Page;
import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.entity.DictVsClinic;
import com.yy.master.modules.sys.dao.DictVsClinicDao;

/**
 * 字典与诊疗项目对照Service
 * @author CTQ
 * @version 2017-03-18
 */
@Service
@Transactional(readOnly = true)
public class DictVsClinicService extends CrudService<DictVsClinicDao, DictVsClinic> {

	public DictVsClinic get(String id) {
		return super.get(id);
	}
	
	public List<DictVsClinic> findList(DictVsClinic dictVsClinic) {
		return super.findList(dictVsClinic);
	}
	
	public Page<DictVsClinic> findPage(Page<DictVsClinic> page, DictVsClinic dictVsClinic) {
		return super.findPage(page, dictVsClinic);
	}
	
	@Transactional(readOnly = false)
	public int save(DictVsClinic dictVsClinic) {
		return super.save(dictVsClinic);
	}
	
	@Transactional(readOnly = false)
	public int delete(DictVsClinic dictVsClinic) {
		return super.delete(dictVsClinic);
	}
	
}