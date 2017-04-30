/**
 * jims
 */
package com.yy.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.data.BaseData;
import com.yy.master.modules.sys.entity.SysMenuDict;
import com.yy.master.modules.sys.entity.User;
import com.yy.master.modules.sys.service.SysMenuDictService;
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
import com.yy.master.modules.sys.entity.OrgSelfService;
import com.yy.master.modules.sys.service.OrgSelfServiceService;

import java.util.List;
import java.util.Map;

/**
 * 自定义服务Controller
 * @author 赵宁
 * @version 2017-02-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/orgSelfService")
public class OrgSelfServiceController extends BaseController {

	@Autowired
	private OrgSelfServiceService orgSelfServiceService;
	@Autowired
	private SysMenuDictService sysMenuDictService;
	
	@ModelAttribute
	public OrgSelfService get(@RequestParam(required=false) String id) {
		OrgSelfService entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgSelfServiceService.get(id);
		}
		if (entity == null){
			entity = new OrgSelfService();
		}
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(OrgSelfService orgSelfService, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgSelfService> page = orgSelfServiceService.findPage(new Page<OrgSelfService>(request, response), orgSelfService); 
		model.addAttribute("page", page);
		return "modules/sys/orgSelfServiceIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(OrgSelfService orgSelfService, Model model) {
		model.addAttribute("orgSelfService", orgSelfService);
		return "modules/sys/orgSelfServiceForm";
	}

	/**
	 * 保存自定义服务
	 * @param orgSelfService
	 * @param model
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(OrgSelfService orgSelfService, Model model) {
		User user = UserUtils.getUser();
	    StringData data=new StringData();
		int i=orgSelfServiceService.save(orgSelfService,user);
        if(i>0){
            data.setCode("success");
            data.setData("保存自定义服务成功");
        }else{
            data.setCode("error");
            data.setData("保存自定义服务失败");
        }
        return data;
	}

	/**
	 * 删除自定义服务
	 * @param orgSelfService
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(OrgSelfService orgSelfService) {
	    StringData data=new StringData();
		int i=orgSelfServiceService.delete(orgSelfService);
        if(i>0){
            data.setCode("success");
            data.setData("删除自定义服务成功");
        }else{
            data.setCode("error");
            data.setData("删除自定义服务失败");
        }
		return data;
	}

	/**
	 * 机构自定义服务分配菜单页面
	 * @param selfServiceId
	 * @param url
	 * @param model
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "selfServiceSelect")
	public String selfServiceSelect(String selfServiceId,String url,Model model){
		List<String> serviceVsMenus = sysMenuDictService.findMenusBySelfServ(selfServiceId);
		model.addAttribute("menus",serviceVsMenus);
		model.addAttribute("url", url); 	// 树结构数据URL
		return "modules/sys/maintain";
	}

    /**
     * 通过服务名获取服务
     * @param selfServiceId
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "selfServiceSelectAjax")
    @ResponseBody
    public BaseData<SysMenuDict> selfServiceSelectAjax(String[] selfServiceId,String roleId){
        if(selfServiceId==null){
            return new BaseData<SysMenuDict>();
        }
        //查询角色下服务菜单
        List<SysMenuDict> sysMenuDictList = sysMenuDictService.findServiceMenuAjax(selfServiceId,roleId);
        //查询服务菜单
        List<SysMenuDict> sysMenuAllDictList = sysMenuDictService.findMenusBySelfServerAjax(selfServiceId);
        BaseData<SysMenuDict> menuDictBaseData=new BaseData<SysMenuDict>();
        menuDictBaseData.setDatas2(sysMenuDictList);
        menuDictBaseData.setDatas1(sysMenuAllDictList);
        return menuDictBaseData;
    }

	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param response
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		String orgId = UserUtils.getUser().getOrgId();
		List<Map<String, Object>> mapList = Lists.newArrayList();
		//SysMenuDict sysMenuDict=new SysMenuDict();
		//User user= UserUtils.getUser();
		//orgDept.setOrgId("1");
		List<SysMenuDict> list = sysMenuDictService.findMenuByOrgId(orgId);
		for (int i=0; i<list.size(); i++){
			SysMenuDict e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf("."+extId+".")==-1))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

	/**
	 * 保存自定义服务菜单 数据
	 * @param selfServiceId
	 * @param menuIds
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "saveSelfServiceMenu")
    public StringData saveSelfServiceMenu(String selfServiceId,String menuIds){
		 StringData data=new StringData();
         int  i=sysMenuDictService.saveSelfServiceMenu(selfServiceId,menuIds);
			if(i>=0){
				data.setCode("success");
				data.setData("保存数据成功");
			}else{
				data.setCode("error");
				data.setData("保存数据失败");
			}
		 return data;
	}
}