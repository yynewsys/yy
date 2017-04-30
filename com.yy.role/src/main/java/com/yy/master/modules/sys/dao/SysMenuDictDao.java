/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.TreeDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.OrgSelfServiceVsMenu;
import com.yy.master.modules.sys.entity.ServiceVsMenu;
import com.yy.master.modules.sys.entity.SysMenuDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单字典表DAO接口
 * @author 赵宁
 * @version 2017-02-17
 */
@MyBatisDao
public interface SysMenuDictDao extends TreeDao<SysMenuDict> {
    /**
     * 保存 菜单服务 对照数据
     * @param serviceVsMenu
     * @return
     * @author 赵宁
     */
     public int saveMenuVsService(ServiceVsMenu serviceVsMenu);

    /**
     * 根据服务ID 查询 所选菜单
     * @param serviceId
     * @return
     * @author 赵宁
     */
    public List<String> findMenusByService(@Param("serviceId")String serviceId);

    /**
     * 删除系统级服务表和中间表数据
     * @param serviceId
     * @author 赵宁
     */
    public void deleteMenuBy(@Param("serviceId")String serviceId);

    /**
     * 根据机构ID 查询机构服务，根据服务ID查询菜单
     * @param orgId
     * @return
     * @author 赵宁
     */
    public List<SysMenuDict> findMenuByOrgId(@Param("orgId")String orgId);

    /**
     * 保存 自定义服务菜单
     * @param orgSelfServiceVsMenu
     * @return
     * @author 赵宁
     */
    public int saveSelfServiceMenu(OrgSelfServiceVsMenu orgSelfServiceVsMenu);

    /**
     * 根据机构级服务ID查询菜单
     * @param selfServiceId
     * @return
     * @author 赵宁
     */
    public List<String> findMenusBySelfSev(@Param("selfServiceId")String selfServiceId);

    /**
     * 通过自定义服务查询菜单
     * @param selfServiceId
     * @return
     */
    public List<SysMenuDict> findMenusBySelfServerAjax(@Param("serviceId")String[] selfServiceId);


    /**
     * 删除机构级服务菜单中间表
     * @param selfServiceId
     * @author 赵宁
     */
	public void deleteSelfServiceMenu(@Param("selfServiceId")String selfServiceId);

    /**
     * 通过自定义服务获取用户菜单
     * @param serviceId
     * @return
     */
    public List<SysMenuDict> findServiceMenu(@Param("serviceId")String serviceId,@Param("userId")String userId);

    public List<SysMenuDict> findServiceMenuAjax(@Param("serviceId")String[] serviceId,@Param("roleId")String roleId);
}