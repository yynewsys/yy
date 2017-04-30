/**
 * jims
 */
package com.yy.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.web.impl.BaseDto;
import com.yy.master.modules.sys.entity.OrgDept;
import com.yy.master.modules.sys.entity.OrgRole;
import com.yy.master.modules.sys.entity.OrgStaff;
import com.yy.master.modules.sys.service.OrgDeptService;
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
import com.yy.master.modules.sys.entity.OrgDeptGroup;
import com.yy.master.modules.sys.service.OrgDeptGroupService;

import java.util.List;
import java.util.Map;

/**
 * 科室分组Controller
 *
 * @author DT
 * @version 2017-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/orgDeptGroup")
public class OrgDeptGroupController extends BaseController {

	@Autowired
	private OrgDeptGroupService orgDeptGroupService;
	@Autowired
	private OrgDeptService orgDeptService;

	/**
	 * 根据ID获取并返回对象
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public OrgDeptGroup get(@RequestParam(required=false) String id) {
		OrgDeptGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgDeptGroupService.get(id);
		}
		if (entity == null){
			entity = new OrgDeptGroup();
		}
		return entity;
	}

	/**
	 * 获取列表
	 * @param orgDeptGroup
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(OrgDeptGroup orgDeptGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgDeptGroup> page = orgDeptGroupService.findPage(new Page<OrgDeptGroup>(request, response), orgDeptGroup);
		model.addAttribute("page", page);
		return "modules/sys/orgDeptGroupIndex";
	}

	/**
	 * 添加和编辑跳转
	 * @param orgDeptGroup
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(OrgDeptGroup orgDeptGroup, Model model) {
		model.addAttribute("orgDeptGroup", orgDeptGroup);
		return "modules/sys/orgDeptGroupForm";
	}

	/**
	 * @param        orgDeptGroup     传递参数
	 * @return   String  返回类型
	 * @throws
	 * @Title: orgDeptUserForm
	 * @Description: (查询科室分组下人员列表)
	 * @author CTQ
	 * @date 2017-03-09
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "orgDeptUserForm")
	public String orgDeptUserForm(OrgDeptGroup orgDeptGroup, Model model) {
		List<BaseDto> userList = orgDeptGroupService.findUserList(orgDeptGroup);
		model.addAttribute("userList", userList);
		model.addAttribute("orgDeptGroup", orgDeptGroup);
		return "modules/sys/orgDeptGroupByPersion";
	}

	/**
	 * 数据更新与保存
	 * @param orgDeptGroup
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(OrgDeptGroup orgDeptGroup, Model model) {
		orgDeptGroup.updateOrgId();
		StringData data=new StringData();
		int i=orgDeptGroupService.save(orgDeptGroup);
		if(i>0){
			data.setCode("success");
			data.setData("保存科室分组成功");
		}else{
			data.setCode("error");
			data.setData("保存科室分组失败");
		}
		return data;
	}

	/**
	 * 数据删除
	 * @param orgDeptGroup
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(OrgDeptGroup orgDeptGroup) {
		StringData data=new StringData();
		int i=orgDeptGroupService.delete(orgDeptGroup);
		if(i>0){
			data.setCode("success");
			data.setData("删除科室分组成功");
		}else{
			data.setCode("error");
			data.setData("删除科室分组失败");
		}
		return data;
	}


	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId) {
		String orgId = UserUtils.getUser().getOrgId();
		List<Map<String, Object>> mapList = Lists.newArrayList();
		OrgDept orgDept = new OrgDept();
		orgDept.setOrgId(orgId);
		List<OrgDept> list = orgDeptService.findList(orgDept);
		for (int i=0; i<list.size(); i++){
			OrgDept dept = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(dept.getId()) && dept.getParentIds().indexOf("."+extId+".")==-1))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", dept.getId());
				map.put("pId", dept.getParent().getId());
				map.put("pIds", dept.getParentIds());
				map.put("name", dept.getDeptName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}