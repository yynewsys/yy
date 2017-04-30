/**
 * jims.
 */
package com.yy.master.modules.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.data.StringData;
import com.yy.master.common.persistence.Page;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.common.web.BaseController;
import com.yy.master.modules.sys.entity.SysService;
import com.yy.master.modules.sys.service.SysServicesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 服务Controller
 * @author 赵宁
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/service")
public class SysServiceController extends BaseController {

	@Autowired
	private SysServicesService sysservicesService;

	@ModelAttribute
	public SysService get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return sysservicesService.get(id);
		}else{
			return new SysService();
		}
	}
	/**
	 * 服务列表
	 * @param sysService
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(SysService sysService, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysService> page = sysservicesService.findPage(new Page<SysService>(request, response), sysService);
		model.addAttribute("page", page);
		model.addAttribute("sysService",sysService);
		return "modules/sys/sysServiceIndex";
	}

	/**
	 * 添加服务
	 * @param sysService
	 * @param model
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(SysService sysService, Model model) {
		model.addAttribute("sysService", sysService);
		return "modules/sys/sysServiceForm";
	}

	/**
	 * 保存服务
	 * @param sysService
	 * @param model
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(SysService sysService, Model model) {
		StringData data=new StringData();
		int i=sysservicesService.save(sysService);
		if(i>0){
			data.setCode("success");
			data.setData("保存成功");
		}else{
			data.setCode("error");
			data.setData("保存失败");
		}
		return data;
	}

	/**
	 * 删除数据
	 * @param sysService
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(SysService sysService) {
		StringData data=new StringData();
		int i=sysservicesService.delete(sysService);
		if(i>0){
			data.setCode("success");
			data.setData("删除成功");
		}else{
			data.setCode("error");
			data.setData("删除失败");
		}
		return data;
	}

}
