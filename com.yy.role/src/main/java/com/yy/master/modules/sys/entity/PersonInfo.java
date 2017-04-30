/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import com.yy.master.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 人员基本信息Entity
 * @author CTQ
 * @version 2017-02-17
 */
public class PersonInfo extends DataEntity<PersonInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String sex;		// 性别
	private String idCard;		// 身份证号
	private String phone;		// 联系电话
	private String email;		// 邮箱
	private String nickName;		// 昵称
	private String inputCode;//拼音码
	
	public PersonInfo() {
		super();
	}

	public PersonInfo(String id){
		super(id);
	}

	@Length(min=1, max=50, message="姓名长度必须介于 1 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="性别长度必须介于 0 和 2 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=1, max=50, message="身份证号长度必须介于 1 和 50 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Length(min=1, max=20, message="联系电话长度必须介于 1 和 20 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=1, max=30, message="邮箱长度必须介于 1 和 30 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=1, max=30, message="昵称长度必须介于 1 和 30 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
}