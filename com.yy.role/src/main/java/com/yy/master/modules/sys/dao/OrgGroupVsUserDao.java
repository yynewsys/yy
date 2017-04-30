/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.OrgGroupVsUser;
import com.yy.master.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分组人员维护DAO接口
 * @author CTQ
 * @version 2017-02-28
 */
@MyBatisDao
public interface OrgGroupVsUserDao extends CrudDao<OrgGroupVsUser> {

    public int delVsUser(OrgGroupVsUser orgGroupVsUser);

    /**
     * @Author chenxy
     * @desc 根据用户Id查询分组Id
     * @param userId
     * @return
     */
    public List<String> findGroupByUserId(@Param("userId") String userId);

    /**
     * 根据科室id查询权限科室人员
     * @param deptId
     * @return
     */
    public List<User> findUserByGroupId(@Param("deptId") String deptId);

    /**
     * 移除科室分组人员
     * @param groupId
     * @return
     */
    public int delVsGroup(@Param("groupId")String groupId);

}