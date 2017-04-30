/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.OrgRole;
import com.yy.master.modules.sys.entity.OrgStaff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人员与组织机构对照DAO接口
 * @author CTQ
 * @version 2017-02-20
 */
@MyBatisDao
public interface OrgStaffDao extends CrudDao<OrgStaff> {

    /**
     * 根据userId直接删除用户机构科室信息
     * @param userId
     * @return
     * @author CTQ 2017-02-21 10:39:36
     */
    public void delByUserId(@Param("userId")String userId);
    /**
     * 根据userId逻辑删除用户机构科室信息
     * @param userId
     * @return
     * @author CTQ 2017-02-21 10:39:36
     */
    public void removeByUserId(@Param("userId")String userId);
    /**
     * 根据userId查询用户机构科室信息
     * @param userId
     * @return
     * @author CTQ 2017-02-22 10:45:24
     */
    public OrgStaff findByUserId(@Param("userId")String userId);


    /**
     * @AUTHOR CHENXY
     * @desc 显示当前组织机构下的所有人员
     * @return
     */
    public List<OrgStaff> findJoinPerson(OrgStaff orgStaff);
    /**
     * @AUTHOR CHENXY
     * @desc 显示当前角色下的所有人员
     * @return
     */
    public List<OrgStaff> findUserInfo(OrgRole orgRole);

    /**
     * 查询当前组织机构当前科室的全部人员
     * @param user
     * @return
     */
    public List<OrgStaff> findUserByDeptId(OrgStaff user);

    /**
     *
     * @param o
     * @return
     */
    public OrgStaff findJoinbyId(OrgStaff o);

    /**
     * save
     * @param o
     */
    public int saveStaffVsRole(OrgStaff o);

    /**
     * delete from staff_vs_role
     * @param orgStaff
     */
    public int deleteFromStaffVsRole(OrgStaff orgStaff);

    /**
     * 删除角色底下用户
     * @param roleId
     * @return
     */
    public int deleteVsRole(@Param("roleId")String roleId);

}