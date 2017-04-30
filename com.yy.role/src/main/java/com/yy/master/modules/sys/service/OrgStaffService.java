/**
 * jims
 */
package com.yy.master.modules.sys.service;

import com.yy.master.common.persistence.Page;
import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.dao.OrgStaffDao;
import com.yy.master.modules.sys.entity.OrgRole;
import com.yy.master.modules.sys.entity.OrgSelfService;
import com.yy.master.modules.sys.entity.OrgStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 人员与组织机构对照Service
 * @author CTQ
 * @version 2017-02-20
 */
@Service
@Transactional(readOnly = false)
public class OrgStaffService extends CrudService<OrgStaffDao, OrgStaff> {
	@Autowired
	private OrgStaffDao orgStaffDao;


	public OrgStaff findByUserId(String userId) {
        return orgStaffDao.findByUserId(userId);
    }

	public OrgStaff get(String id) {
		return super.get(id);
	}
	
	public List<OrgStaff> findList(OrgStaff orgStaff) {
		return super.findList(orgStaff);
	}
	
	public Page<OrgStaff> findPage(Page<OrgStaff> page, OrgStaff orgStaff) {
		return super.findPage(page, orgStaff);
	}
	
	@Transactional(readOnly = false)
	public int save(OrgStaff orgStaff) {
		return super.save(orgStaff);
	}
	
	@Transactional(readOnly = false)
	public int delete(OrgStaff orgStaff) {
		return super.delete(orgStaff);
	}

    public List<OrgStaff> findUser(OrgRole orgRole) {
         return this.orgStaffDao.findUserInfo(orgRole);
    }

    public List<OrgStaff> findUserByDeptId(OrgStaff user) {
        return this.orgStaffDao.findUserByDeptId(user);
    }

    /**
     *
     * @param o
     * @return
     */
    public OrgStaff findJoinbyId(OrgStaff o) {
        return this.orgStaffDao.findJoinbyId(o);
    }

    /**
     *
     * @param o
     */
    @Transactional(readOnly = false)
    public int saveStaffVsRole(OrgStaff o) {
        return this.orgStaffDao.saveStaffVsRole(o);
    }

    @Transactional(readOnly = false)
    public int  deleteFromStaffVsRole(OrgStaff orgStaff) {
        return this.orgStaffDao.deleteFromStaffVsRole(orgStaff);

    }

}