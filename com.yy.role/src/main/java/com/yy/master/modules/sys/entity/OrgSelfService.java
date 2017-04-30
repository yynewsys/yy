/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.yy.master.common.persistence.DataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义服务Entity
 * @author 赵宁
 * @version 2017-02-16
 */
public class OrgSelfService extends DataEntity<OrgSelfService> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 组织机构Id
	private String serviceName;		// 自定义服务名称
    private String roleId;
    private String serviceId;
	private int sort ;//排序
    private String menuId;
    private List<SysMenuDict>  sysMenuDictList=new ArrayList<SysMenuDict>();  //自动定义服务菜单

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }



	
	public OrgSelfService() {

		super();
		this.sort = 10;
	}

	public OrgSelfService(String id){
		super(id);
	}

	@Length(min=1, max=64, message="组织机构Id长度必须介于 1 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=1, max=50, message="自定义服务名称长度必须介于 1 和 50 之间")
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

    public List<SysMenuDict> getSysMenuDictList() {
        return sysMenuDictList;
    }

    public void setSysMenuDictList(List<SysMenuDict> sysMenuDictList) {
        this.sysMenuDictList = sysMenuDictList;
    }
}