/**
 * jims.
 */
package com.yy.master.modules.sys.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.yy.master.common.persistence.DataEntity;
import com.yy.master.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.yy.master.common.config.Global;
import com.yy.master.common.supcan.annotation.treelist.cols.SupCol;
import com.yy.master.common.utils.Collections3;

/**
 * 用户Entity
 * @author ThinkGem
 * @version 2013-12-05
 */
public class User extends DataEntity<User> {

	private static final long serialVersionUID = 1L;
	private String userType;//用户类型
	private String currentLogin;// 登录名
	private String password;// 密码

	private OrgDept orgDept;//科室ID
	private String phone;	// 手机
	private String idCard;//身份证号
	private String name;	// 姓名
	private String email;	// 邮箱
	private String persionId;//persion_info主键

	private String type;// 用户登陆类型
	private String title;//职称

	private String loginFlag;	// 是否允许登陆


	private String oldLoginName;// 原登录名
	private String newPassword;	// 新密码
	private  Company company;//机构id

    private String orgStaffId; //组织机构人员ID

    private String userId;
    private String userName;


    private List<OrgSelfService> orgSelfServiceList =new ArrayList<OrgSelfService>(); //自定义服务

    private OrgSelfService currentService; ///当前服务

	public User() {
		super();
		this.loginFlag = Global.YES;
	}

	public User(String id){
		super(id);
	}

	public User(String id, String currentLogin){
		super(id);
		this.currentLogin = currentLogin;
	}
	public String getLoginFlag() {
		return loginFlag;
	}

    public String getOrgStaffId() {
        return orgStaffId;
    }

    public void setOrgStaffId(String orgStaffId) {
        this.orgStaffId = orgStaffId;
    }

    @SupCol(isUnique="true", isHide="true")
	@ExcelField(title="ID", type=1, align=2, sort=1)
	public String getId() {
		return id;
	}



	@Length(min=1, max=100, message="登录名长度必须介于 1 和 100 之间")
	@ExcelField(title="登录名", align=2, sort=30)
	public String getCurrentLogin() {
		return currentLogin;
	}

	public void setCurrentLogin(String currentLogin) {
		this.currentLogin = currentLogin;
	}

	@JsonIgnore
	@Length(min=1, max=100, message="密码长度必须介于 1 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Length(min=1, max=100, message="姓名长度必须介于 1 和 100 之间")
	@ExcelField(title="姓名", align=2, sort=40)
	public String getName() {
		return name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Email(message="邮箱格式不正确")
	@Length(min=0, max=200, message="邮箱长度必须介于 1 和 200 之间")
	@ExcelField(title="邮箱", align=1, sort=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=200, message="电话长度必须介于 1 和 200 之间")
	@ExcelField(title="电话", align=2, sort=60)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPersionId() {
		return persionId;
	}

	public void setPersionId(String persionId) {
		this.persionId = persionId;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}


	@ExcelField(title="备注", align=1, sort=900)
	public String getRemarks() {
		return remarks;
	}


	@ExcelField(title="创建时间", type=0, align=1, sort=90)
	public Date getCreateDate() {
		return createDate;
	}



	public String getOldLoginName() {
		return oldLoginName;
	}

	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	public static boolean isAdmin(String id){
		return id != null && "1".equals(id);
	}
	
	@Override
	public String toString() {
		return id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public OrgDept getOrgDept() {
		return orgDept;
	}

	public void setOrgDept(OrgDept orgDept) {
		this.orgDept = orgDept;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

    public List<OrgSelfService> getOrgSelfServiceList() {
        return orgSelfServiceList;
    }

    public void setOrgSelfServiceList(List<OrgSelfService> orgSelfServiceList) {
        this.orgSelfServiceList = orgSelfServiceList;
    }

    public OrgSelfService getCurrentService() {
        return currentService;
    }

    public void setCurrentService(OrgSelfService currentService) {
        this.currentService = currentService;
    }
}