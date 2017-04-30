/**
 * jims
 */
package com.yy.master.modules.sys.service;

import java.util.List;
import java.util.UUID;

import com.yy.master.common.data.StringData;
import com.yy.master.modules.sys.dao.OrgStaffDao;
import com.yy.master.modules.sys.entity.OrgRole;
import com.yy.master.modules.sys.entity.OrgStaff;
import com.yy.master.modules.sys.entity.User;
import com.yy.master.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.master.common.persistence.Page;
import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.entity.OrgSelfService;
import com.yy.master.modules.sys.dao.OrgSelfServiceDao;

/**
 * 自定义服务Service
 * @author 赵宁
 * @version 2017-02-16
 */
@Service
@Transactional(readOnly = false)
public class OrgSelfServiceService extends CrudService<OrgSelfServiceDao, OrgSelfService> {

    @Autowired
    private OrgSelfServiceDao orgSelfServiceDao;

    @Autowired
    private OrgStaffDao orgStaffDao;

	public OrgSelfService get(String id) {
		return super.get(id);
	}
	
	public List<OrgSelfService> findList(OrgSelfService orgSelfService) {
		return super.findList(orgSelfService);
	}
	
	public Page<OrgSelfService> findPage(Page<OrgSelfService> page, OrgSelfService orgSelfService) {
		return super.findPage(page, orgSelfService);
	}

	/**
	 * 保存自定义服务
	 * @param orgSelfService
	 * @return
	 * @author 赵宁
	 */
	@Transactional(readOnly = false)
	public int save(OrgSelfService orgSelfService,User user) {
		String orgId = user.getOrgId();
		int i =0;
		if(orgId!=null){
			 orgSelfService.setOrgId(orgId);
			return i=super.save(orgSelfService);
		}else{
			 return i;
		}
	}
	
	@Transactional(readOnly = false)
	public int delete(OrgSelfService orgSelfService) {
		return super.delete(orgSelfService);
	}

    /**
     *
     * @param orgSelfService
     * @return
     */
    public List<OrgSelfService> findServiceByRoleId(OrgSelfService orgSelfService) {
        return this.orgSelfServiceDao.findServiceByRoleId(orgSelfService);
    }

    /**
     *
     * @param serviceId
     * @return
     */
    public List<String> findMenuByServiceId(String serviceId) {
        return this.orgSelfServiceDao.findMenuByServiceId(serviceId);
    }

    /**
     *
     * @param orgSelfService
     * @return
     */
    @Transactional(readOnly = false)
    public int saveRoleVsService(OrgSelfService orgSelfService) {
        return this.orgSelfServiceDao.saveRoleVsService(orgSelfService);
    }

    /**
     *
     * @param orgSelfService
     * @return
     */
    @Transactional(readOnly = false)
    public int deleteFromRoleVsService(OrgSelfService orgSelfService) {
        return this.orgSelfServiceDao.deleteFromRoleVsService(orgSelfService);
    }


    /**
     *
     * @return
     */
    @Transactional(readOnly = false)
    public StringData middleSaveService(String roleId, String serviceId,String [] menuIds) {
        StringData data = new StringData();
        int newNum=0;
        OrgSelfService del = new OrgSelfService();
        del.setRoleId(roleId);
        del.setServiceId(serviceId);
        newNum=orgSelfServiceDao.deleteFromRoleVsService(del);
        for (int i = 0; i < menuIds.length; i++) {
            OrgSelfService orgSelfService = new OrgSelfService();
            orgSelfService.setServiceId(serviceId);
            orgSelfService.setRoleId(roleId);
            orgSelfService.setMenuId(menuIds[i]);
            orgSelfService.preInsert();
            newNum+=orgSelfServiceDao.saveRoleVsService(orgSelfService);
        }
        if(menuIds.length==0){
            data.setCode("success");
            data.setData("移除服务成功");
        }else{
            if (newNum > 0) {
                data.setCode("success");
                data.setData("权限配置成功");
            } else {
                data.setCode("error");
                data.setData("权限配置失败");
            }
        }
        return data;
    }

        public int assignRole(OrgRole role, String[] staffIds) {
            if(staffIds.length>0){
                orgStaffDao.deleteVsRole(role.getRoleId());
                for (int i = 0; i < staffIds.length; i++) {
                    OrgStaff o = new OrgStaff(staffIds[i]);
                    o.setRoleId(role.getRoleId());
                    orgStaffDao.saveStaffVsRole(o);
                }
            } else {
                orgStaffDao.deleteVsRole(role.getRoleId());
            }
        return 1;
    }

}