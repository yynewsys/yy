/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.yy.master.common.persistence.DataEntity;

/**
 * 服务菜单对照表Entity
 * @author 赵宁
 * @version 2017-02-21
 */
public class ServiceVsMenu  {
	
	private static final long serialVersionUID = 1L;
	private String serviceId;		// 服务ID
	private String menuId;		// 菜单Id
	
	public ServiceVsMenu() {
		super();
	}


	@Length(min=1, max=64, message="服务ID长度必须介于 1 和 64 之间")
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	@Length(min=1, max=64, message="菜单Id长度必须介于 1 和 64 之间")
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}