/**
 * jims
 */
package com.yy.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yy.master.common.web.impl.BaseDto;
import com.yy.master.modules.sys.entity.DictVsClinic;
import com.yy.master.modules.sys.utils.UserUtils;
import com.yy.master.modules.sys.vo.SysOrgDictVSClinicVo;
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
import com.yy.master.modules.sys.entity.SysOrgDict;
import com.yy.master.modules.sys.service.SysOrgDictService;

import java.util.List;

/**
 * 系统平台字典Controller
 * @author CTQ
 * @version 2017-03-18
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysOrgDict")
public class SysOrgDictController extends BaseController {

	@Autowired
	private SysOrgDictService sysOrgDictService;
	
	@ModelAttribute
	public SysOrgDict get(@RequestParam(required=false) String id) {
		SysOrgDict entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysOrgDictService.get(id);
		}
		if (entity == null){
			entity = new SysOrgDict();
		}
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(SysOrgDict sysOrgDict, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysOrgDict> page = sysOrgDictService.findPage(new Page<SysOrgDict>(request, response), sysOrgDict); 
		model.addAttribute("page", page);
		List<String> typeList = sysOrgDictService.findTypeList();
		model.addAttribute("typeList", typeList);
		return "modules/sys/sysOrgDictIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(SysOrgDict sysOrgDict, Model model) {
		model.addAttribute("sysOrgDict", sysOrgDict);
		return "modules/sys/sysOrgDictForm";
	}

	/**
	 * @param sysOrgDict 传递参数
	 * @return String   返回类型
	 * @throws
	 * @Title: dictVsClinicForm
	 * @Desription: (跳转到添加对照页面)
	 * @author CTQ
	 * @date 2017-03-18
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "dictVsClinicForm")
	public String dictVsClinicForm(SysOrgDict sysOrgDict, Model model) {
		model.addAttribute("sysOrgDict", sysOrgDict);
		DictVsClinic dictVsClinic = new DictVsClinic();
		dictVsClinic.setDictId(sysOrgDict.getId());
		model.addAttribute("dictVsClinic", dictVsClinic);
		List<BaseDto> list = sysOrgDictService.findVsListById(sysOrgDict.getId(), UserUtils.getOrgId());
		model.addAttribute("list", list);
		return "modules/sys/sysDictVsClinicForm";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(SysOrgDict sysOrgDict, Model model) {
	    StringData data=new StringData();
		int i=sysOrgDictService.save(sysOrgDict);
        if(i>0){
            data.setCode("success");
            data.setData("保存系统平台字典成功");
        }else{
            data.setCode("error");
            data.setData("保存系统平台字典失败");
        }
        return data;
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(SysOrgDict sysOrgDict) {
	    StringData data=new StringData();
		int i=sysOrgDictService.delete(sysOrgDict);
        if(i>0){
            data.setCode("success");
            data.setData("删除系统平台字典成功");
        }else{
            data.setCode("error");
            data.setData("删除系统平台字典失败");
        }
		return data;
	}



	@RequiresPermissions("user")
	@RequestMapping(value = {"bloodMatchList", ""})
	public String blood_match_list(SysOrgDict sysOrgDict, Model model) {
		sysOrgDict.updateOrgId();
		List<SysOrgDictVSClinicVo> list = sysOrgDictService.findBloodMatchList(sysOrgDict);
		model.addAttribute("list", list);
		model.addAttribute("type",sysOrgDict.getType());
		return "modules/blood/bloodMatchContrast";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "bloodMatchForm")
	public String bloodMatchForm(SysOrgDictVSClinicVo sysOrgDictVSClinicVo,SysOrgDict sysOrgDict, Model model) {
		if(StringUtils.isNotBlank(sysOrgDictVSClinicVo.getId())){
			sysOrgDict.updateOrgId();
			sysOrgDictVSClinicVo = sysOrgDictService.findBloodClinicItem(sysOrgDict);
		}
		model.addAttribute("sysOrgDictVSClinicVo", sysOrgDictVSClinicVo);
		model.addAttribute("type",sysOrgDict.getType());
		return "modules/blood/bloodMatchContrastForm";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "saveBlood")
	@ResponseBody
	public StringData saveBlood(SysOrgDictVSClinicVo sysOrgDictVSClinicVo) {
		StringData data=new StringData();
		int i=sysOrgDictService.saveBlood(sysOrgDictVSClinicVo);
		if(i>0){
			data.setCode("success");
			data.setData("保存系统平台字典成功");
		}else{
			data.setCode("error");
			data.setData("保存系统平台字典失败");
		}
		return data;
	}
}