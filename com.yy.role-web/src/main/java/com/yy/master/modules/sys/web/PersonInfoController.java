/**
 * jims
 */
package com.yy.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yy.master.modules.sys.utils.UserUtils;
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
import com.yy.master.modules.sys.entity.PersonInfo;
import com.yy.master.modules.sys.service.PersonInfoService;

/**
 * 人员基本信息Controller
 * @author CTQ
 * @version 2017-02-17
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/personInfo")
public class PersonInfoController extends BaseController {

	@Autowired
	private PersonInfoService personInfoService;
	
	@ModelAttribute
	public PersonInfo get(@RequestParam(required=false) String id) {
		PersonInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = personInfoService.get(id);
		}
		if (entity == null){
			entity = new PersonInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(PersonInfo personInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PersonInfo> page = personInfoService.findPage(new Page<PersonInfo>(request, response), personInfo); 
		model.addAttribute("page", page);
		return "modules/sys/personInfoIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(PersonInfo personInfo, Model model) {
		personInfo = personInfoService.get(UserUtils.getUser().getPersionId());
		model.addAttribute("personInfo", personInfo);
		return "modules/sys/personInfoForm";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(PersonInfo personInfo) {
	    StringData data=new StringData();
		int i=personInfoService.savePersonInfo(personInfo);
        if(i>0){
            data.setCode("success");
            data.setData("保存个人信息成功");
        }else{
            data.setCode("error");
            data.setData("保存个人信息失败");
        }
        return data;
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(PersonInfo personInfo) {
	    StringData data=new StringData();
		int i=personInfoService.delete(personInfo);
        if(i>0){
            data.setCode("success");
            data.setData("删除人员基本信息成功");
        }else{
            data.setCode("error");
            data.setData("删除人员基本信息失败");
        }
		return data;
	}

}