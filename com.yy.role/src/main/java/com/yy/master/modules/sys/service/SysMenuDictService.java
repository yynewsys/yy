/**
 * jims
 */
package com.yy.master.modules.sys.service;

import java.util.List;

import com.yy.master.common.service.TreeService;
import com.yy.master.modules.sys.entity.OrgSelfServiceVsMenu;
import com.yy.master.modules.sys.entity.ServiceVsMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.master.common.persistence.Page;
import com.yy.master.modules.sys.entity.SysMenuDict;
import com.yy.master.modules.sys.dao.SysMenuDictDao;

/**
 * 菜单字典表Service
 * @author 赵宁
 * @version 2017-02-17
 */
@Service
@Transactional(readOnly = true)
public class SysMenuDictService extends TreeService<SysMenuDictDao, SysMenuDict> {
	@Autowired
    private SysMenuDictDao sysMenuDictDao;
	public SysMenuDict get(String id) {
		return super.get(id);
	}

	public List<SysMenuDict> findList(SysMenuDict sysMenuDict) {
		return super.findList(sysMenuDict);
	}

	public Page<SysMenuDict> findPage(Page<SysMenuDict> page, SysMenuDict sysMenuDict) {
		return super.findPage(page, sysMenuDict);
	}

	@Transactional(readOnly = false)
	public int save(SysMenuDict sysMenuDict) {
		return super.save(sysMenuDict);
	}

	@Transactional(readOnly = false)
	public int delete(SysMenuDict sysMenuDict) {
		return super.delete(sysMenuDict);
	}

	/**
	 * 保存系统级服务菜单对照数据
	 * @param serviceId
	 * @param menusId
	 * @return
	 * @author 赵宁
	 */
	@Transactional(readOnly = false)
	public int saveServiceVsMenu(String serviceId,String menusId){
		sysMenuDictDao.deleteMenuBy(serviceId);
		String[]  menuId=menusId.split(",");
		int code = 0;
		if(menuId.length>0 && menuId!=null && !("").equals(menusId)){
			for(int i=0;i<menuId.length;i++){
				String menu=menuId[i];
				ServiceVsMenu serviceVsMenu = new ServiceVsMenu();
				serviceVsMenu.setServiceId(serviceId);
				serviceVsMenu.setMenuId(menu);

				code=sysMenuDictDao.saveMenuVsService(serviceVsMenu);
			}
		}

        return code;
	}

	/**
	 * 根据系统级服务 查询菜单
	 * @param serviceId
	 * @return
	 * @author 赵宁
	 */
	public List<String> findMenusByService(String serviceId) {
		return sysMenuDictDao.findMenusByService(serviceId);
	}

	/**
	 * 根据机构ID查询机构服务，根据服务ID 查询菜单
	 * @param orgId
	 * @return
	 * @author 赵宁
	 */
	public List<SysMenuDict> findMenuByOrgId(String orgId){
       return sysMenuDictDao.findMenuByOrgId(orgId);
	}

	/**
	 * 保存机构及服务菜单
	 * @param selfServiceId
	 * @param menusId
	 * @return
	 * @author 赵宁
	 */
	@Transactional(readOnly = false)
	public int saveSelfServiceMenu(String selfServiceId,String menusId) {
        sysMenuDictDao.deleteSelfServiceMenu(selfServiceId);
		String[]  menuId=menusId.split(",");
		int code = 0;
		if(menuId.length>0 && menuId!=null && !("").equals(menusId)){
			for(int i=0;i<menuId.length;i++){
				String menu=menuId[i];
				OrgSelfServiceVsMenu orgSelfServiceVsMenu = new OrgSelfServiceVsMenu();
				orgSelfServiceVsMenu.setSelfServiceId(selfServiceId);
				orgSelfServiceVsMenu.setMenuId(menu);
				code=sysMenuDictDao.saveSelfServiceMenu(orgSelfServiceVsMenu);
			}
		}
		return code;
	}

	/**
	 * 根据机构及服务ID查询 菜单
	 * @param selfServiceId
	 * @return
	 * @author 赵宁
	 */
	public List<String> findMenusBySelfServ(String selfServiceId) {
		return sysMenuDictDao.findMenusBySelfSev(selfServiceId);
	}

    /**
     * 根据机构及服务ID查询 菜单
     * @param selfServiceId
     * @return
     */
    public List<SysMenuDict> findMenusBySelfServerAjax(String[] selfServiceId) {
        return sysMenuDictDao.findMenusBySelfServerAjax(selfServiceId);
    }
    /**
     * 通过自定义服务名称获取当前角色权限服务
     * @param serviceId
     * @return
     */
    public List<SysMenuDict> findServiceMenu(String serviceId,String userId) {
        return sysMenuDictDao.findServiceMenu(serviceId,userId);
    }
    public List<SysMenuDict> findServiceMenuAjax(String[] serviceId,String roleId) {
        return sysMenuDictDao.findServiceMenuAjax(serviceId,roleId);
    }

}