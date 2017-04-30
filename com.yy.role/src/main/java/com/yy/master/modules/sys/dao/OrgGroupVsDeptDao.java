/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.OrgGroupVsDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分组科室DAO接口
 * @author CTQ
 * @version 2017-02-28
 */
@MyBatisDao
public interface OrgGroupVsDeptDao extends CrudDao<OrgGroupVsDept> {

    public List<String> findDeptIdByGroupId(@Param("groupId") String groupId);

    public void delByGroupId(@Param("groupId") String groupId);

    public List<String> findGroupIdByDeptId(@Param("deptId") String deptId);
}