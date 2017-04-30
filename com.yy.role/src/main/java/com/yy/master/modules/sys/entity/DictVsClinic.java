/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.yy.master.common.persistence.DataEntity;

/**
 * 字典与诊疗项目对照Entity
 * @author CTQ
 * @version 2017-03-18
 */
public class DictVsClinic extends DataEntity<DictVsClinic> {
	
	private static final long serialVersionUID = 1L;
	private String dictId;		// 字典id
	private String clinicId;		// 诊疗项目ID
	private OrgDept executiveDept;//执行科室
	
	public DictVsClinic() {
		super();
	}

	public DictVsClinic(String id){
		super(id);
	}

	@Length(min=1, max=64, message="字典id长度必须介于 1 和 64 之间")
	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	
	@Length(min=1, max=64, message="诊疗项目ID长度必须介于 1 和 64 之间")
	public String getClinicId() {
		return clinicId;
	}

	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}

	public OrgDept getExecutiveDept() {
		return executiveDept;
	}

	public void setExecutiveDept(OrgDept executiveDept) {
		this.executiveDept = executiveDept;
	}
}