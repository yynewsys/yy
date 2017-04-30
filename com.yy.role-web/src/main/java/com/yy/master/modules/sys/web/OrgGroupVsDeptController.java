/**
 * jims
 */
package com.yy.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yy.master.modules.sys.entity.User;
import com.yy.master.modules.sys.service.OrgGroupVsUserService;
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
import com.yy.master.modules.sys.entity.OrgGroupVsDept;
import com.yy.master.modules.sys.service.OrgGroupVsDeptService;

import java.util.ArrayList;
import java.util.List;

/**
 * 分组科室Controller
 * @author CTQ
 * @version 2017-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/orgGroupVsDept")
public class OrgGroupVsDeptController extends BaseController {

	@Autowired
	private OrgGroupVsDeptService orgGroupVsDeptService;
    @Autowired
    private OrgGroupVsUserService orgGroupVsUserService;
	
	@ModelAttribute
	public OrgGroupVsDept get(@RequestParam(required=false) String id) {
		OrgGroupVsDept entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgGroupVsDeptService.get(id);
		}
		if (entity == null){
			entity = new OrgGroupVsDept();
		}
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(OrgGroupVsDept orgGroupVsDept, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgGroupVsDept> page = orgGroupVsDeptService.findPage(new Page<OrgGroupVsDept>(request, response), orgGroupVsDept); 
		model.addAttribute("page", page);
		return "modules/sys/orgGroupVsDeptIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(OrgGroupVsDept orgGroupVsDept, Model model) {
		model.addAttribute("orgGroupVsDept", orgGroupVsDept);
		return "modules/sys/orgGroupVsDeptForm";
	}



	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(OrgGroupVsDept orgGroupVsDept, Model model) {
	    StringData data=new StringData();

		int i=orgGroupVsDeptService.saveVs(orgGroupVsDept);
        if(i>0){
            data.setCode("success");
            data.setData("保存分组科室成功");
        }else{
            data.setCode("error");
            data.setData("保存分组科室失败");
        }
        return data;
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(OrgGroupVsDept orgGroupVsDept) {
	    StringData data=new StringData();
		int i=orgGroupVsDeptService.delete(orgGroupVsDept);
        if(i>0){
            data.setCode("success");
            data.setData("删除分组科室成功");
        }else{
            data.setCode("error");
            data.setData("删除分组科室失败");
        }
		return data;
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "findDeptIdByGroupId")
	public String findDeptIdByGroupId(String groupId,String url,Model model){
		List<String> deptIds = orgGroupVsDeptService.findDeptIdByGroupId(groupId);
		model.addAttribute("depts",deptIds);
		model.addAttribute("url", url); 	// 树结构数据URL
		return "modules/sys/orgDeptCommon";
	}
    /**
     * 根据科室Id查找user
     * @param deptId  科室Id
     * @return
     * @Author chenxy
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "findUserByDeptId")
    @ResponseBody
    public List<User> findUserByDeptId(@RequestParam(required = true) String deptId) {
        List<User> result=new ArrayList<User>();
        result=this.orgGroupVsUserService.findUserbyGroupId(deptId);
        return result;
    }
}