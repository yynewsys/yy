/**
 * jims
 */
package com.yy.master.modules.sys.service;

import com.yy.master.common.persistence.Page;
import com.yy.master.common.service.TreeService;
import com.yy.master.modules.sys.dao.OrgDeptDao;
import com.yy.master.modules.sys.entity.OrgDept;
import com.yy.master.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构科室Service
 * @author zhangyao
 * @version 2017-02-17
 */
@Service
@Transactional(readOnly = true)
public class OrgDeptService extends TreeService<OrgDeptDao, OrgDept> {

    @Autowired
    private OrgDeptDao orgDeptDao;

    public OrgDept get(String id) {
		return super.get(id);
	}

    public List<OrgDept> findByParentIdsLike(OrgDept orgDept){
        orgDept.updateOrgId();
        if(orgDept != null){
            orgDept.setParentIds(orgDept.getParentIds()+"%");
            return dao.findByParentIdsLike(orgDept);
        }
        return  new ArrayList<OrgDept>();
    }
    public Page<OrgDept> findPageParentIdsLike(Page<OrgDept> page, OrgDept orgDept) {
        orgDept.updateOrgId();
        orgDept.setPage(page);
        page.setList(dao.findByParentIdsLike(orgDept));
        return page;
    }

	@Transactional(readOnly = false)
	public int save(OrgDept orgDept) {
		return super.save(orgDept);
	}
	
	@Transactional(readOnly = false)
	public int delete(OrgDept orgDept) {
		return super.delete(orgDept);
	}
    /**
     * 根据科室分组名获取ID
     * @param orgDept
     * @return
     * @author DT
     */
    public OrgDept findId(OrgDept orgDept){
        return orgDeptDao.findId(orgDept);
    }

    /**
     * 获取user权限科室
     * @return
     */
    public List<OrgDept> roleDept(String userId){
        return orgDeptDao.roleDept(userId);
    }

    /**
     * 获取所有下级科室
     * @return
     * @author fengyg
     */
    public List<OrgDept> getLowLevelDeptList(){
        return orgDeptDao.getLowLevelDeptList(UserUtils.getUser().getOrgId());
    }
}