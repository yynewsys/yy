/**
 * jims
 */
package com.yy.master.modules.sys.service;

import java.util.List;

import com.yy.master.modules.sys.dao.OrgStaffDao;
import com.yy.master.modules.sys.entity.OrgStaff;
import com.yy.master.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.master.common.persistence.Page;
import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.entity.OrgGroupVsUser;
import com.yy.master.modules.sys.dao.OrgGroupVsUserDao;

/**
 * 分组人员维护Service
 * @author CTQ
 * @version 2017-02-28
 */
@Service
@Transactional(readOnly = true)
public class OrgGroupVsUserService extends CrudService<OrgGroupVsUserDao, OrgGroupVsUser> {
	@Autowired
	private OrgGroupVsUserDao orgGroupVsUserDao;
	@Autowired
	private OrgStaffDao orgStaffDao;

	public List<OrgGroupVsUser> findList(OrgGroupVsUser orgGroupVsUser) {
		return super.findList(orgGroupVsUser);
	}

	@Transactional(readOnly = false)
	public int saveVsUser(OrgGroupVsUser orgGroupVsUser,String[] staffIds) {
		if(staffIds.length>0){
            orgGroupVsUserDao.delVsGroup(orgGroupVsUser.getGroupId());
			for(String str : staffIds){
                OrgGroupVsUser o=new OrgGroupVsUser();
				orgGroupVsUser.setUser(new User(str));
                orgGroupVsUser.setGroupId(orgGroupVsUser.getGroupId());
				orgGroupVsUser.preInsert();
				orgGroupVsUserDao.insert(orgGroupVsUser);
			}
		}else{
            orgGroupVsUserDao.delVsGroup(orgGroupVsUser.getGroupId());
        }
		return 1;
	}
	@Transactional(readOnly = false)
	public int delVsUser(OrgGroupVsUser orgGroupVsUser){
		return orgGroupVsUserDao.delVsUser(orgGroupVsUser);
	}
     /**
     * @Author chenxy
      * @desc  根据用户Id查询分组Id
     * @param userId
     * @return
     */
    public List<String> findGroupByUserId(String userId) {
        return this.orgGroupVsUserDao.findGroupByUserId(userId);
    }

    /**
     * @Author chenxy
     * @desc  根据分组Id查询User
     * @param
     * @return
     */
    public List<User> findUserbyGroupId(String deptId) {
        return this.orgGroupVsUserDao.findUserByGroupId(deptId);
    }
}