/**
 * jims
 */
package com.yy.master.modules.sys.entity;


import com.yy.master.common.persistence.TreeEntity;
import org.hibernate.validator.constraints.Length;

import com.yy.master.common.persistence.DataEntity;

/**
 * 菜单字典表Entity
 * @author 赵宁
 * @version 2017-02-17
 */
public class SysMenuDict extends TreeEntity<SysMenuDict> {
	
	private static final long serialVersionUID = 1L;
	/*private SysMenuDict parent;		// 父级菜单
	private String parentIds;		// 所有父级编号*/
	private String name;		// 菜单名称
	private String href;		// 菜单连接
	private String target;		// 打开方式
	private String icon;		// 菜单图标路径
	private String isShow;		// 是否显示
	//private String sort;		// 菜单排序
	private String permission;		// 权限标识
	
	public SysMenuDict() {
		super();
	}

	public SysMenuDict(String id){
		super(id);
	}

	public SysMenuDict getParent() {
		return parent;
	}

	public void setParent(SysMenuDict parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=20, message="菜单名称长度必须介于 1 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=50, message="菜单连接长度必须介于 1 和 100 之间")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	@Length(min=0, max=20, message="打开方式长度必须介于 0 和 20 之间")
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	@Length(min=0, max=50, message="菜单图标路径长度必须介于 0 和 50 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Length(min=0, max=1, message="是否显示长度必须介于 0 和 1 之间")
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}


	
	@Length(min=0, max=1000, message="权限标识长度必须介于 0 和 1000 之间")
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}