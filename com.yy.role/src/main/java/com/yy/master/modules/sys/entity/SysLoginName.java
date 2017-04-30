/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import com.yy.master.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 登陆用户名Entity
 * @author CTQ
 * @version 2017-02-17
 */
public class SysLoginName extends DataEntity<SysLoginName> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户id
	private String loginName;		// 登录名
	private String type;		// 类型
	
	public SysLoginName() {
		super();
	}

	public SysLoginName(String id){
		super(id);
	}

	@NotNull(message="用户id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=200, message="登录名长度必须介于 1 和 200 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}