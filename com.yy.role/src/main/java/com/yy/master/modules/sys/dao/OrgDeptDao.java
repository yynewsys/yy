/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.TreeDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.OrgDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机构科室DAO接口
 * @author zhangyao
 * @version 2017-02-17
 */
@MyBatisDao
public interface OrgDeptDao extends TreeDao<OrgDept> {
    /**
     * 根据科室分组名获取ID
     * @param orgDept
     * @return
     * @author DT
     */
    public OrgDept findId(OrgDept orgDept);

    /**
     * 获取user权限科室
     * @param userId
     * @return
     */
    public List<OrgDept> roleDept(@Param("userId")String userId);

    /**
     * 获取所有下级科室
     * @param orgId 组织机构ID
     * @return
     * @author fengyg
     */
    public List<OrgDept> getLowLevelDeptList(@Param("orgId")String orgId);
}