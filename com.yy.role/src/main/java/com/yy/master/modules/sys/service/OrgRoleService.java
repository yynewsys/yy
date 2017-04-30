/**
 * jims
 */
package com.yy.master.modules.sys.service;

import com.yy.master.common.persistence.Page;
import com.yy.master.common.service.CrudService;

import com.yy.master.modules.sys.dao.OrgRoleDao;
import com.yy.master.modules.sys.entity.OrgRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 组织机构角色信息Service
 * @author CTQ
 * @version 2017-02-17
 */
@Service
@Transactional(readOnly = true)
public class OrgRoleService extends CrudService<OrgRoleDao, OrgRole> {

    @Autowired
    private OrgRoleDao orgRoleDao;

    /**
     * @CHENXY
     * @DESC 查询人员分配角色
     * @param orgRolePage
     * @param orgRole
     * @return
     */
    public Page<OrgRole> findMiddlePage(Page<OrgRole> orgRolePage, OrgRole orgRole) {
        orgRole.updateOrgId();
        orgRole.setPage(orgRolePage);
        orgRolePage.setList(dao.findStaffVsRole());
        return orgRolePage;
    }
}