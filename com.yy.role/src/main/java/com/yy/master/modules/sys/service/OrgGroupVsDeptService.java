/**
 * jims
 */
package com.yy.master.modules.sys.service;


import com.yy.master.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.entity.OrgGroupVsDept;
import com.yy.master.modules.sys.dao.OrgGroupVsDeptDao;

import java.util.List;

/**
 * 分组科室Service
 * @author CTQ
 * @version 2017-02-28
 */
@Service
@Transactional(readOnly = true)
public class OrgGroupVsDeptService extends CrudService<OrgGroupVsDeptDao, OrgGroupVsDept> {
	@Autowired
	private OrgGroupVsDeptDao orgGroupVsDeptDao;

	public List<String> findDeptIdByGroupId(String groupId){
		return orgGroupVsDeptDao.findDeptIdByGroupId(groupId);
	}

	@Transactional(readOnly = false)
	public int saveVs(OrgGroupVsDept orgGroupVsDept) {
		int num = 0;
		orgGroupVsDeptDao.delByGroupId(orgGroupVsDept.getGroupId());
		if(StringUtils.isNotBlank(orgGroupVsDept.getDeptIds())){
			if(orgGroupVsDept.getDeptIds().contains(",")){
				String [] strs = orgGroupVsDept.getDeptIds().split(",");
				if(strs.length>0){
					for(String str :strs){
						orgGroupVsDept.setDeptId(str);
						orgGroupVsDept.preInsert();
						num = orgGroupVsDeptDao.insert(orgGroupVsDept);
					}
				}
			}
		}
		return num;
	}

    /**
     * 根据科室Id查找分组Ids
     * @param deptId
     * @return
     * @Author chenxy
     */
    public List<String> findGroupIdByDeptId(String deptId) {
        return this.orgGroupVsDeptDao.findGroupIdByDeptId(deptId);
    }
}