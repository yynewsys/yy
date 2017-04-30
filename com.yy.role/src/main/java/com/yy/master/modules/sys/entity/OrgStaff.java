/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import com.yy.master.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 人员与组织机构对照Entity
 * @author CTQ
 * @version 2017-02-20
 */
public class OrgStaff extends DataEntity<OrgStaff> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 组织机构Id
	private User user;		// 用户ID
	private String deptId;		// 科室信息Id
	private String title;		// 职称

    private String deptName;
    private String name;
    private String phone;

    private String staffId;
    private String roleId;

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OrgStaff() {
		super();
	}

	public OrgStaff(String id){
		super(id);
	}

	@Length(min=1, max=64, message="组织机构Id长度必须介于 1 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@NotNull(message="用户ID不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=64, message="科室信息Id长度必须介于 1 和 64 之间")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Length(min=0, max=20, message="职称长度必须介于 0 和 20 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}