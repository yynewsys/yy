/**
 * jims.
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.common.web.impl.BaseDto;
import com.yy.master.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {

	/**
	 * @param id
	 * @return User    返回类型
	 * @Title: getInfoById
	 * @Desciption: (根据用户ID查询用户相关信息)
	 * @author CTQ
	 * @date 2017-02-14
	 */
	public User getInfoById(String id);

    /**
     * 获取多个User
     * @param userIds
     * @return
     */
    public List<BaseDto> getFindUser(String [] userIds);


	/**
	 * 根据登录名称查询用户
	 * @param user
	 * @return
	 * @updateby CTQ at 2017-02-15 09:11:01
	 */
	public User getByLoginName(User user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByDeptId(User user);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);

	/**
	 * 根据personId查询USER
	 * @param personId
	 * @return
	 * @author CTQ 2017-02-21 14:44:30
	 */
	public User findUserByPersonId(@Param("personId") String personId);


	/**
	 * @param deptId 传递参数
	 * @return List<User>    返回类型
	 * @Desciption: (获取用户权限科室下的所有用户)
	 * @author CTQ
	 * @date 2017-03-31
	 */
	public List<User> getRoleUserList(@Param("deptId") String deptId);

}
