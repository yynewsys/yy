/**
 * jims
 */
package com.yy.master.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.yy.master.common.persistence.DataEntity;

/**
 * 机构对应服务Entity
 * @author 赵宁
 * @version 2017-02-22
 */
public class CompanyVsService extends DataEntity<CompanyVsService> {
	
	private static final long serialVersionUID = 1L;
	private String companyId;		// 机构ID
	private String serviceId;		// 服务ID
	
	public CompanyVsService() {
		super();
	}

	public CompanyVsService(String id){
		super(id);
	}

	@Length(min=1, max=64, message="机构ID长度必须介于 1 和 64 之间")
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	@Length(min=1, max=64, message="服务ID长度必须介于 1 和 64 之间")
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
}