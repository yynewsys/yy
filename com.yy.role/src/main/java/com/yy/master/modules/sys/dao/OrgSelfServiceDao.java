/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.OrgSelfService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义服务DAO接口
 * @author 赵宁
 * @version 2017-02-16
 */
@MyBatisDao
public interface OrgSelfServiceDao extends CrudDao<OrgSelfService> {
    /**
     *
     * @param orgSelfService
     * @return
     */
   public List<OrgSelfService> findServiceByRoleId(OrgSelfService orgSelfService);

    /**
     *
     * @param serviceId
     * @return
     */
   public List<String> findMenuByServiceId(@Param("serviceId") String serviceId);

    /**
     *
     * @param orgSelfService
     * @return
     */
   public int saveRoleVsService(OrgSelfService orgSelfService);

    /**
     *
     * @param orgSelfService
     * @returnN
     */
    public int deleteFromRoleVsService(OrgSelfService orgSelfService);

    /**
     * 获取当前登录人所有的自动定义服务
     * @return
     */
    public List<OrgSelfService> findUserList(@Param("orgStaffId") String orgStaffId);


}