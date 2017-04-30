/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.SysLoginName;
import org.apache.ibatis.annotations.Param;

/**
 * 登陆用户名DAO接口
 * @author CTQ
 * @version 2017-02-17
 */
@MyBatisDao
public interface SysLoginNameDao extends CrudDao<SysLoginName> {
    /**
     * 验证用户名是否存在
     * @param loginName
     * @return
     * @author CTQ 2017-02-21 10:39:32
     */
    public String findValidate(@Param("loginName") String loginName);

    /**
     * 根据userId直接删除用户登录信息
     * @param userId
     * @return
     * @author CTQ 2017-02-21 10:39:36
     */
    public void delByUserId(@Param("userId")String userId);
    /**
     * 根据userId逻辑删除用户登录信息
     * @param userId
     * @return
     * @author CTQ 2017-02-21 10:39:36
     */
    public void removeByUserId(@Param("userId")String userId);
}