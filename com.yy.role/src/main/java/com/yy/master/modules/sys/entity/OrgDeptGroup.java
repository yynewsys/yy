/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.yy.master.common.persistence.DataEntity;

/**
 * 科室分组Entity
 * @author DT
 * @version 2017-02-28
 */
public class OrgDeptGroup extends DataEntity<OrgDeptGroup> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 分组名称
	private String sort;		// 排序

	public OrgDeptGroup() {
		super();
	}

	public OrgDeptGroup(String id){
		super(id);
	}

	@Length(min=1, max=200, message="分组名称长度必须介于 1 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	
}