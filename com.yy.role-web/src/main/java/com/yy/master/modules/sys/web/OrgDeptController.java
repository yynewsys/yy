/**
 * jims
 */
package com.yy.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.utils.ChineseCharToEnUtils;
import com.yy.master.modules.sys.entity.User;
import com.yy.master.modules.sys.utils.DeptTreeTable;
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
import com.yy.master.modules.sys.entity.OrgDept;
import com.yy.master.modules.sys.service.OrgDeptService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 机构科室Controller
 * @author zhangyao
 * @version 2017-02-17
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/orgDept")
public class OrgDeptController extends BaseController {

	@Autowired
	private OrgDeptService orgDeptService;
	
	@ModelAttribute
	public OrgDept get(@RequestParam(required=false) String id) {
		OrgDept entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgDeptService.get(id);
		}
		if (entity == null){
			entity = new OrgDept();
		}
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String index(OrgDept orgDept, Model model) {
        List<OrgDept> list=orgDeptService.findList(orgDept);
        List<OrgDept> treeList=new DeptTreeTable(list).buildTree();
		model.addAttribute("treeList", treeList);
		return "modules/sys/orgDeptIndex";
	}
    @RequiresPermissions("user")
    @RequestMapping(value = {"list"})
    public String list(OrgDept orgDept, Model model) {
        List<OrgDept> list=orgDeptService.findByParentIdsLike(orgDept);
        model.addAttribute("treeList", list);
        return "modules/sys/orgDeptIndex";
    }
	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(OrgDept orgDept, Model model) {
        if (StringUtils.isBlank(orgDept.getId())&&orgDept.getParent()!=null){
            int size = 0;
            List<OrgDept> list = orgDeptService.findList(orgDept);
            for (int i=0; i<list.size(); i++){
                OrgDept e = list.get(i);
                if (e.getParent()!=null && e.getParent().getId()!=null
                        && e.getParent().getId().equals(orgDept.getParent().getId())){
                    size++;
                }
            }
        }
        model.addAttribute("orgDept", orgDept);
		return "modules/sys/orgDeptForm";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(OrgDept orgDept, Model model) {
        orgDept.setInputCode(ChineseCharToEnUtils.getAllFirstLetter(orgDept.getDeptName()));
	    StringData data=new StringData();
		int i=orgDeptService.save(orgDept);
        if(i>0){
            data.setCode("success");
            data.setData("保存科室成功");
        }else{
            data.setCode("error");
            data.setData("保存科室失败");
        }
        return data;
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(OrgDept orgDept) {
	    StringData data=new StringData();
		int i=orgDeptService.delete(orgDept);
        if(i>0){
            data.setCode("success");
            data.setData("删除科室成功");
        }else{
            data.setCode("error");
            data.setData("删除科室失败");
        }
		return data;
	}

    /**
     * 获取机构JSON数据。
     * @param extId 排除的ID
     * @param response
     * @update by chenxy 跟新orgid
     * @return
     */
    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,OrgDept orgDept,String role,HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<OrgDept> list=null;
        if("1".equals(role)){
            User user=UserUtils.getUser();
            list = orgDeptService.roleDept(user.getId());
        }else{
            list = orgDeptService.findList(orgDept);
        }

        for (int i=0; i<list.size(); i++){
            OrgDept e = list.get(i);
            if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf("."+extId+".")==-1))){
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getParentId());
                map.put("pIds", e.getParentIds());
                map.put("name", e.getDeptName());
                mapList.add(map);
            }
        }
        return mapList;
    }

}