/**
 * jims
 */
package com.yy.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yy.master.modules.sys.entity.User;
import com.yy.master.modules.sys.service.SystemService;
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
import com.yy.master.modules.sys.entity.SysLoginName;
import com.yy.master.modules.sys.service.SysLoginNameService;

/**
 * 登陆用户名Controller
 * @author CTQ
 * @version 2017-02-17
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysLoginName")
public class SysLoginNameController extends BaseController {

	@Autowired
	private SysLoginNameService sysLoginNameService;
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public SysLoginName get(@RequestParam(required=false) String id) {
		SysLoginName entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysLoginNameService.get(id);
		}
		if (entity == null){
			entity = new SysLoginName();
		}
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(SysLoginName sysLoginName, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysLoginName> page = sysLoginNameService.findPage(new Page<SysLoginName>(request, response), sysLoginName); 
		model.addAttribute("page", page);
		return "modules/sys/sysLoginNameIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(SysLoginName sysLoginName, Model model) {
		model.addAttribute("sysLoginName", sysLoginName);
		return "modules/sys/sysLoginNameForm";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(SysLoginName sysLoginName, Model model) {
	    StringData data=new StringData();
		int i=sysLoginNameService.save(sysLoginName);
        if(i>0){
            data.setCode("success");
            data.setData("保存登陆用户名成功");
        }else{
            data.setCode("error");
            data.setData("保存登陆用户名失败");
        }
        return data;
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(SysLoginName sysLoginName) {
	    StringData data=new StringData();
		int i=sysLoginNameService.delete(sysLoginName);
        if(i>0){
            data.setCode("success");
            data.setData("删除登陆用户名成功");
        }else{
            data.setCode("error");
            data.setData("删除登陆用户名失败");
        }
		return data;
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "validateUnique")
	@ResponseBody
	public StringData validateUnique(User user,String personId,String type,String value) {
		StringData data=new StringData();
		int i = 0;
		String oldUserId = "";
		if(personId!=null&&!personId.equals("")){//用户管理修改用户信息
			user = systemService.findUserByPersonId(personId);
			oldUserId=sysLoginNameService.findValidate(value);
			if(user!=null){
				if(user.getId().equals(oldUserId)){
					data.setCode("pass");
					data.setData("");
				}else {
					data.setCode("error");
					data.setData("该信息已存在，请重新输入...");
				}
			}
		}else{
			oldUserId=sysLoginNameService.findValidate(value);
			if(oldUserId==null||oldUserId.equals("")){
				data.setCode("success");
				data.setData("唯一性校验成功");
			}else{
				data.setCode("error");
				data.setData("该信息已存在，请重新输入...");
			}
		}
		return data;
	}

}