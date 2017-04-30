/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.yy.master.common.persistence.DataEntity;

/**
 * 机构自定义服务菜单对照表Entity
 * @author 赵宁
 * @version 2017-02-22
 */
public class OrgSelfServiceVsMenu  {
	
	private static final long serialVersionUID = 1L;
	private String selfServiceId;		// 自定义服务ID
	private String menuId;		// 菜单Id
	
	public OrgSelfServiceVsMenu() {
		super();
	}

/*	public OrgSelfServiceVsMenu(String id){
		super(id);
	}*/

	@Length(min=1, max=64, message="自定义服务ID长度必须介于 1 和 64 之间")
	public String getSelfServiceId() {
		return selfServiceId;
	}

	public void setSelfServiceId(String selfServiceId) {
		this.selfServiceId = selfServiceId;
	}
	
	@Length(min=1, max=64, message="菜单Id长度必须介于 1 和 64 之间")
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}