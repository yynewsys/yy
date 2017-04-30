/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yy.master.common.persistence.TreeEntity;
import org.hibernate.validator.constraints.Length;

import com.yy.master.common.persistence.DataEntity;

import java.util.List;

/**
 * 机构科室Entity
 * @author zhangyao
 * @version 2017-02-17
 */
public class OrgDept  extends TreeEntity<OrgDept> {
	
	private static final long serialVersionUID = 1L;
//	private OrgDept parent;		// 父级ID
//    private String parentId;   //父级Id
//	private String parentIds;		// 父级ID集合
	private String deptName;		// 科室名称
	private String deptCode;		// 科室code
	private String deptPropertity;		// 科室属性
	private String inputCode;		// 拼音码
	private String clinicAttr;		// 临床标识
	private String outpOrInp;		// 门诊住院科室属性
    private String [] outpOrInps; //门诊住院科室属性集合
	private String internalOrSerger;		// 内外科标识
    private List<String> powerDeptIdsList;//权限科室Ids

	public OrgDept() {
		super();
	}

	public OrgDept(String id){
		super(id);
	}

    public String[] getOutpOrInps() {
        return outpOrInps;
    }

    public void setOutpOrInps(String[] outpOrInps) {
        this.outpOrInps = outpOrInps;
    }

    @JsonBackReference
	public OrgDept getParent() {
		return parent;
	}

	public void setParent(OrgDept parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="父级ID集合长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=100, message="科室名称长度必须介于 1 和 100 之间")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Length(min=1, max=20, message="科室code长度必须介于 1 和 20 之间")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	@Length(min=0, max=1, message="科室属性长度必须介于 0 和 1 之间")
	public String getDeptPropertity() {
		return deptPropertity;
	}

	public void setDeptPropertity(String deptPropertity) {
		this.deptPropertity = deptPropertity;
	}
	
	@Length(min=0, max=100, message="拼音码长度必须介于 0 和 100 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	@Length(min=0, max=1, message="临床标识长度必须介于 0 和 1 之间")
	public String getClinicAttr() {
		return clinicAttr;
	}

	public void setClinicAttr(String clinicAttr) {
		this.clinicAttr = clinicAttr;
	}
	
	@Length(min=0, max=1, message="门诊住院科室属性长度必须介于 0 和 1 之间")
	public String getOutpOrInp() {
		return outpOrInp;
	}

	public void setOutpOrInp(String outpOrInp) {
		this.outpOrInp = outpOrInp;
	}
	
	@Length(min=0, max=1, message="内外科标识长度必须介于 0 和 1 之间")
	public String getInternalOrSerger() {
		return internalOrSerger;
	}

	public void setInternalOrSerger(String internalOrSerger) {
		this.internalOrSerger = internalOrSerger;
	}


//    public String getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(String parentId) {
//        this.parentId = parentId;
//    }


    public List<String> getPowerDeptIdsList() {
        return powerDeptIdsList;
    }

    public void setPowerDeptIdsList(List<String> powerDeptIdsList) {
        this.powerDeptIdsList = powerDeptIdsList;
    }
}