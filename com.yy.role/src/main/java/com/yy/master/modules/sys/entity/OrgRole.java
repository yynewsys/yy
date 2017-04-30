/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import com.yy.master.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 组织机构角色信息Entity
 * @author CTQ
 * @version 2017-02-17
 */
public class OrgRole extends DataEntity<OrgRole> {
	
	private static final long serialVersionUID = 1L;
	private String roleName;		// 角色名称
    private String staffName;
    private String deptName;
    private String staffId;
    private String roleId;
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public OrgRole() {
		super();
	}

	public OrgRole(String id){
		super(id);
	}

	@Length(min=1, max=30, message="角色名称长度必须介于 1 和 30 之间")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
}