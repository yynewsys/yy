/**
 * jims
 */
package com.yy.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.modules.sys.entity.ServiceVsMenu;
import com.yy.master.modules.sys.entity.SysMenuDict;
import com.yy.master.modules.sys.entity.User;
import com.yy.master.modules.sys.utils.MenuTreeTable;
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
import com.yy.master.modules.sys.entity.SysMenuDict;
import com.yy.master.modules.sys.service.SysMenuDictService;

import java.util.List;
import java.util.Map;

/**
 * 菜单字典表Controller
 * @author 赵宁
 * @version 2017-02-17
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysMenuDict")
public class SysMenuDictController extends BaseController {

	@Autowired
	private SysMenuDictService sysMenuDictService;
	
	@ModelAttribute
	public SysMenuDict get(@RequestParam(required=false) String id) {
		SysMenuDict entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysMenuDictService.get(id);
		}
		if (entity == null){
			entity = new SysMenuDict();
		}
		return entity;
	}

	/**
	 * 查询 菜单列表
	 * @param sysMenuDict
	 * @param model
	 * @return
	 * @author 赵宁
	 */
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String listTree(SysMenuDict sysMenuDict, Model model) {
		List<SysMenuDict> list=sysMenuDictService.findList(sysMenuDict);
		List<SysMenuDict> treeList=new MenuTreeTable(list).buildTree();
        if(treeList.size()==0){
            treeList=list;
        }
		model.addAttribute("treeList", treeList);
		return "modules/sys/sysMenuDictIndex";
	}




	/**
	 * 跳转添加、修改页面
	 * @param sysMenuDict
	 * @param model
	 * @return
	 * @author 赵宁
	 */

	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(SysMenuDict sysMenuDict, Model model) {
		model.addAttribute("sysMenuDict", sysMenuDict);
		return "modules/sys/sysMenuDictForm";
	}

	/**
	 * 保存数据
	 * @param sysMenuDict
	 * @param model
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(SysMenuDict sysMenuDict, Model model) {
	    StringData data=new StringData();
		int i=sysMenuDictService.save(sysMenuDict);
        if(i>0){
            data.setCode("success");
            data.setData("保存菜单字典表成功");
        }else{
            data.setCode("error");
            data.setData("保存菜单字典表失败");
        }
        return data;
	}

	/**
	 * 删除数据
	 * @param sysMenuDict
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(SysMenuDict sysMenuDict) {
	    StringData data=new StringData();
		int i=sysMenuDictService.delete(sysMenuDict);
        if(i>0){
            data.setCode("success");
            data.setData("删除菜单字典表成功");
        }else{
            data.setCode("error");
            data.setData("删除菜单字典表失败");
        }
		return data;
	}

	/**
	 * 跳转到菜单树形结构页面
	 * @param request
	 * @param url
	 * @param serviceId
	 * @param model
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "treeselect")
	public String treeselect(HttpServletRequest request,String url,String serviceId, Model model) {
		model.addAttribute("url", url); 	// 树结构数据URL
		model.addAttribute("isAll", request.getParameter("isAll")); 	// 是否读取全部数据，不进行权限过滤
		model.addAttribute("checked", request.getParameter("checked")); // 是否可复选
        List<String> serviceVsMenus = sysMenuDictService.findMenusByService(serviceId);
		model.addAttribute("menus",serviceVsMenus);

		return "modules/sys/maintain";
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
		List<Map<String, Object>> mapList = Lists.newArrayList();
		SysMenuDict sysMenuDict=new SysMenuDict();
		User user= UserUtils.getUser();
		//orgDept.setOrgId("1");
		List<SysMenuDict> list = sysMenuDictService.findList(sysMenuDict);
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
	 * 保存 服务和菜单对照表数据
	 * @param serviceId
	 * @param menusId
	 * @return
	 * @author 赵宁
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "saveMenuVSService")
	@ResponseBody
	public StringData saveMenuVSService(String serviceId,String menusId) {
		StringData data=new StringData();
		int i=sysMenuDictService.saveServiceVsMenu(serviceId,menusId);
		if(i>0){
			data.setCode("success");
			data.setData("保存数据成功");
		}else{
			data.setCode("error");
			data.setData("保存数据失败");
		}
		return data;
	}


}