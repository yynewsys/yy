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
import com.yy.master.modules.sys.entity.DictVsClinic;
import com.yy.master.modules.sys.service.DictVsClinicService;

/**
 * 字典与诊疗项目对照Controller
 * @author CTQ
 * @version 2017-03-18
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dictVsClinic")
public class DictVsClinicController extends BaseController {

	@Autowired
	private DictVsClinicService dictVsClinicService;
	
	@ModelAttribute
	public DictVsClinic get(@RequestParam(required=false) String id) {
		DictVsClinic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dictVsClinicService.get(id);
		}
		if (entity == null){
			entity = new DictVsClinic();
		}
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(DictVsClinic dictVsClinic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DictVsClinic> page = dictVsClinicService.findPage(new Page<DictVsClinic>(request, response), dictVsClinic); 
		model.addAttribute("page", page);
		return "modules/sys/dictVsClinicIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(DictVsClinic dictVsClinic, Model model) {
		model.addAttribute("dictVsClinic", dictVsClinic);
		return "modules/sys/dictVsClinicForm";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(DictVsClinic dictVsClinic, Model model) {
	    StringData data=new StringData();
		int i=dictVsClinicService.save(dictVsClinic);
        if(i>0){
            data.setCode("success");
            data.setData("保存字典与诊疗项目对照成功");
        }else{
            data.setCode("error");
            data.setData("保存字典与诊疗项目对照失败");
        }
        return data;
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(DictVsClinic dictVsClinic) {
	    StringData data=new StringData();
		int i=dictVsClinicService.delete(dictVsClinic);
        if(i>0){
            data.setCode("success");
            data.setData("删除字典与诊疗项目对照成功");
        }else{
            data.setCode("error");
            data.setData("删除字典与诊疗项目对照失败");
        }
		return data;
	}

}