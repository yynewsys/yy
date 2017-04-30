/**
 * jims
 */
package com.yy.master.modules.sys.service;

import java.util.List;

import com.yy.master.common.web.impl.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.master.common.persistence.Page;
import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.entity.OrgDeptGroup;
import com.yy.master.modules.sys.dao.OrgDeptGroupDao;

/**
 * 科室分组Service
 * @author DT
 * @version 2017-02-28
 */
@Service
@Transactional(readOnly = true)
public class OrgDeptGroupService extends CrudService<OrgDeptGroupDao, OrgDeptGroup> {
	@Autowired
	private OrgDeptGroupDao orgDeptGroupDao;

	/**
	 * @param        orgDeptGroup     传递参数
	 * @return
	 * @Description: (查询科室分组下人员列表)
	 * @author CTQ
	 * @date 2017-03-09
	 */
	public List<BaseDto> findUserList(OrgDeptGroup orgDeptGroup) {
		return orgDeptGroupDao.findUserList(orgDeptGroup);
	}


}