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
import com.yy.master.modules.sys.entity.OrgGroupVsUser;
import com.yy.master.modules.sys.service.OrgGroupVsUserService;

/**
 * 分组人员维护Controller
 * @author CTQ
 * @version 2017-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/orgGroupVsUser")
public class OrgGroupVsUserController extends BaseController {

	@Autowired
	private OrgGroupVsUserService orgGroupVsUserService;
	
	@ModelAttribute
	public OrgGroupVsUser get(@RequestParam(required=false) String id) {
		OrgGroupVsUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgGroupVsUserService.get(id);
		}
		if (entity == null){
			entity = new OrgGroupVsUser();
		}
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(OrgGroupVsUser orgGroupVsUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgGroupVsUser> page = orgGroupVsUserService.findPage(new Page<OrgGroupVsUser>(request, response), orgGroupVsUser); 
		model.addAttribute("page", page);
		return "modules/sys/orgGroupVsUserIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(OrgGroupVsUser orgGroupVsUser, Model model) {
		model.addAttribute("orgGroupVsUser", orgGroupVsUser);
		return "modules/sys/orgGroupVsUserForm";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(OrgGroupVsUser orgGroupVsUser,String[] staffIds, Model model) {
	    StringData data=new StringData();
		int i=orgGroupVsUserService.saveVsUser(orgGroupVsUser,staffIds);
        if(i>0){
            data.setCode("success");
            data.setData("保存分组人员维护成功");
        }else{
            data.setCode("error");
            data.setData("保存分组人员维护失败");
        }
        return data;
	}



	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(OrgGroupVsUser orgGroupVsUser) {
	    StringData data=new StringData();
		int i=orgGroupVsUserService.delVsUser(orgGroupVsUser);
        if(i>0){
            data.setCode("success");
            data.setData("删除分组人员成功");
        }else{
            data.setCode("error");
            data.setData("删除分组人员失败");
        }
		return data;
	}

}