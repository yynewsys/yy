/**
 * jims
 */
package com.yy.master.modules.sys.service;

import com.yy.master.common.persistence.Page;
import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.dao.PerformFreqDictDao;
import com.yy.master.modules.sys.entity.PerformFreqDict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 频次字典表Service
 * @author 刘方舟
 * @version 2017-03-15
 */
@Service
@Transactional(readOnly = true)
public class PerformFreqDictService extends CrudService<PerformFreqDictDao, PerformFreqDict> {

	public PerformFreqDict get(String id) {
		return super.get(id);
	}
	
	public List<PerformFreqDict> findList(PerformFreqDict performFreqDict) {
		return super.findList(performFreqDict);
	}
	
	public Page<PerformFreqDict> findPage(Page<PerformFreqDict> page, PerformFreqDict performFreqDict) {
		return super.findPage(page, performFreqDict);
	}
	
	@Transactional(readOnly = false)
	public int save(PerformFreqDict performFreqDict) {
		return super.save(performFreqDict);
	}
	
	@Transactional(readOnly = false)
	public int delete(PerformFreqDict performFreqDict) {
		return super.delete(performFreqDict);
	}
	
}