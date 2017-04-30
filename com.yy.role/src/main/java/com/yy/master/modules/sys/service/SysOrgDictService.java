/**
 * jims
 */
package com.yy.master.modules.sys.service;

import java.util.List;

import com.yy.master.common.utils.StringUtils;
import com.yy.master.common.web.impl.BaseDto;
import com.yy.master.modules.sys.dao.DictVsClinicDao;
import com.yy.master.modules.sys.entity.Dict;
import com.yy.master.modules.sys.entity.DictVsClinic;
import com.yy.master.modules.sys.vo.SysOrgDictVSClinicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.master.common.persistence.Page;
import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.entity.SysOrgDict;
import com.yy.master.modules.sys.dao.SysOrgDictDao;

/**
 * 系统平台字典Service
 * @author CTQ
 * @version 2017-03-18
 */
@Service
@Transactional(readOnly = true)
public class SysOrgDictService extends CrudService<SysOrgDictDao, SysOrgDict> {
	@Autowired
	private DictVsClinicDao dictVsClinicDao;

	public SysOrgDict get(String id) {
		return super.get(id);
	}
	/**
	 * 根据字典ID查询对照的项目信息
	 * @param dictId
	 * @param orgId
	 * @return
	 * @author CTQ
	 */
	public List<BaseDto> findVsListById(String dictId,String orgId) {
		return dao.findVsListById(dictId,orgId);
	}

	/**
	 * 查询字段类型列表
	 * @return
	 * @author CTQ
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new SysOrgDict());
	}

	/**
	 * 查询配血划价/验血项目对照
	 * @param sysOrgDict
	 * @return
	 */
	public List<SysOrgDictVSClinicVo> findBloodMatchList(SysOrgDict sysOrgDict){
		return dao.findBloodMatchList(sysOrgDict);
	}
	/**
	 * 查询配血划价/验血项目对照
	 * @param sysOrgDict
	 * @return
	 */
	public SysOrgDictVSClinicVo findBloodClinicItem(SysOrgDict sysOrgDict){
		List<SysOrgDictVSClinicVo> list  = dao.findBloodMatchList(sysOrgDict);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Transactional(readOnly = false)
	public int saveBlood(SysOrgDictVSClinicVo vo) {
		DictVsClinic dc = new DictVsClinic();
		if (StringUtils.isNotBlank(vo.getId())){
			dc.setDictId(vo.getId());
		}
		dictVsClinicDao.delete(dc);
		if (StringUtils.isNotBlank(vo.getItemId())){
			dc.setClinicId(vo.getItemId());
		}
		if (vo.getExecutiveDept()!=null && StringUtils.isNotBlank(vo.getExecutiveDept().getId())){
			dc.setExecutiveDept(vo.getExecutiveDept());
		}
		dc.preInsert();
		dc.updateOrgId();
		return dictVsClinicDao.insert(dc);
	}
	
	@Transactional(readOnly = false)
	public int delete(SysOrgDict sysOrgDict) {
		return super.delete(sysOrgDict);
	}
	
}