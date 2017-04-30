/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.yy.master.common.persistence.DataEntity;

/**
 * 系统平台字典Entity
 * @author CTQ
 * @version 2017-03-18
 */
public class SysOrgDict extends DataEntity<SysOrgDict> {
	
	private static final long serialVersionUID = 1L;
	private String label;		// 标签
	private String type;		// 类型
	private String value;		// 值
	private Integer sort;		// 排序
	
	public SysOrgDict() {
		super();
	}

	public SysOrgDict(String id){
		super(id);
	}

	@Length(min=1, max=200, message="标签长度必须介于 1 和 200 之间")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Length(min=1, max=200, message="类型长度必须介于 1 和 200 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=200, message="值长度必须介于 1 和 200 之间")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@NotNull(message="排序不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}