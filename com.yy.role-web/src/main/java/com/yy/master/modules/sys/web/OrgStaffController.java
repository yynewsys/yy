/**
 * jims
 */
package com.yy.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yy.master.common.data.StringData;
import com.yy.master.common.persistence.Page;
import com.yy.master.common.web.BaseController;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.modules.sys.entity.OrgStaff;
import com.yy.master.modules.sys.service.OrgStaffService;

/**
 * 人员与组织机构对照Controller
 * @author CTQ
 * @version 2017-02-20
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/orgStaff")
public class OrgStaffController extends BaseController {

	@Autowired
	private OrgStaffService orgStaffService;
	
	@ModelAttribute
	public OrgStaff get(@RequestParam(required=false) String id) {
		OrgStaff entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgStaffService.get(id);
		}
		if (entity == null){
			entity = new OrgStaff();
		}
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(OrgStaff orgStaff, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgStaff> page = orgStaffService.findPage(new Page<OrgStaff>(request, response), orgStaff); 
		model.addAttribute("page", page);
		return "modules/sys/orgStaffIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(OrgStaff orgStaff, Model model) {
		model.addAttribute("orgStaff", orgStaff);
		return "modules/sys/orgStaffForm";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(OrgStaff orgStaff, Model model) {
	    StringData data=new StringData();
		int i=orgStaffService.save(orgStaff);
        if(i>0){
            data.setCode("success");
            data.setData("保存人员与组织机构对照成功");
        }else{
            data.setCode("error");
            data.setData("保存人员与组织机构对照失败");
        }
        return data;
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(OrgStaff orgStaff) {
	    StringData data=new StringData();
		int i=orgStaffService.delete(orgStaff);
        if(i>0){
            data.setCode("success");
            data.setData("删除人员与组织机构对照成功");
        }else{
            data.setCode("error");
            data.setData("删除人员与组织机构对照失败");
        }
		return data;
	}

}