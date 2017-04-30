/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.PersonInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 人员基本信息DAO接口
 * @author CTQ
 * @version 2017-02-17
 */
@MyBatisDao
public interface PersonInfoDao extends CrudDao<PersonInfo> {

    /**
     * 根据用户ID查询名称
     * @param userId
     * @return
     * @author fengyg
     */
    public PersonInfo getNameByUserId(@Param("id")String userId);
}