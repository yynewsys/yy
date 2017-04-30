/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.common.web.impl.BaseDto;
import com.yy.master.modules.sys.entity.OrgDeptGroup;

import java.util.List;

/**
 * 科室分组DAO接口
 * @author DT
 * @version 2017-02-28
 */
@MyBatisDao
public interface OrgDeptGroupDao extends CrudDao<OrgDeptGroup> {

    /**
     * 查询分组下所有人员信息
     * @param orgDeptGroup
     * @return
     * @author CTQ
     */
    public List<BaseDto> findUserList(OrgDeptGroup orgDeptGroup);




}