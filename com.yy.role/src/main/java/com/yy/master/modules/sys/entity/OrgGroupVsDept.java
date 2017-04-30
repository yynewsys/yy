/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.yy.master.common.persistence.DataEntity;

/**
 * 分组科室Entity
 * @author CTQ
 * @version 2017-02-28
 */
public class OrgGroupVsDept extends DataEntity<OrgGroupVsDept> {
	
	private static final long serialVersionUID = 1L;
	private String groupId;		// 分组ID
	private String deptId;		// 科室ID
	private String deptIds;		// 科室ID
	public OrgGroupVsDept() {
		super();
	}

	public OrgGroupVsDept(String id){
		super(id);
	}

	@Length(min=1, max=64, message="分组ID长度必须介于 1 和 64 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Length(min=1, max=64, message="科室ID长度必须介于 1 和 64 之间")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
}