/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.OrgRole;

import java.util.List;

/**
 * 组织机构角色信息DAO接口
 * @author CTQ
 * @version 2017-02-17
 */
@MyBatisDao
public interface OrgRoleDao extends CrudDao<OrgRole> {
    /**
     * @version 1.o
     * @Author chenxy
     * @desc 查询人员分配的角色
     * @return
     */
    List<OrgRole> findStaffVsRole();
}