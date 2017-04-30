/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import com.yy.master.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.yy.master.common.persistence.DataEntity;

/**
 * 分组人员维护Entity
 * @author CTQ
 * @version 2017-02-28
 */
public class OrgGroupVsUser extends DataEntity<OrgGroupVsUser> {
	
	private static final long serialVersionUID = 1L;
	private String groupId;		// 分组ID
	private User user;		// 人员id
	
	public OrgGroupVsUser() {
		super();
	}

	public OrgGroupVsUser(String id){
		super(id);
	}

	@Length(min=1, max=64, message="分组ID长度必须介于 1 和 64 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@NotNull(message="人员id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}