/**
 * jims
 */
package com.yy.master.modules.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.data.StringData;
import com.yy.master.common.persistence.Page;
import com.yy.master.common.utils.Collections3;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.common.web.BaseController;
import com.yy.master.common.web.impl.BaseDto;
import com.yy.master.modules.sys.entity.*;
import com.yy.master.modules.sys.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 组织机构角色信息Controller
 *
 * @author CTQ
 * @version 2017-02-17
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/orgRole")
public class OrgRoleController extends BaseController {

    @Autowired
    private OrgRoleService orgRoleService;

    @Autowired
    private OrgStaffService orgStaffService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private OrgDeptService orgDeptService;

    @Autowired
    private OrgSelfServiceService orgSelfServiceService;
    @Autowired
    private OrgDeptGroupService orgDeptGroupService;


    @ModelAttribute
    public OrgRole get(@RequestParam(required = false) String id) {
        OrgRole entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = orgRoleService.get(id);
        }
        if (entity == null) {
            entity = new OrgRole();
        }
        return entity;
    }

    @RequiresPermissions("user")
    @RequestMapping(value = {"index", ""})
    public String list(OrgRole orgRole, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<OrgRole> page = orgRoleService.findPage(new Page<OrgRole>(request, response), orgRole);
        model.addAttribute("page", page);
        return "modules/sys/orgRoleIndex";
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "form")
    public String form(OrgRole orgRole, Model model) {
        //拿到当前登录人员的组织机构
        model.addAttribute("orgRole", orgRole);
        return "modules/sys/orgRoleForm";
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "save")
    @ResponseBody
    public StringData save(OrgRole orgRole, Model model) {
        StringData data = new StringData();

        int i = orgRoleService.save(orgRole);
        if (i > 0) {
            data.setCode("success");
            data.setData("保存组织机构角色信息成功");
        } else {
            data.setCode("error");
            data.setData("保存组织机构角色信息失败");
        }
        return data;
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "delete")
    @ResponseBody
    public StringData delete(OrgRole orgRole) {
        StringData data = new StringData();
        int i = orgRoleService.delete(orgRole);
        if (i > 0) {
            data.setCode("success");
            data.setData("删除组织机构角色信息成功");
        } else {
            data.setCode("error");
            data.setData("删除组织机构角色信息失败");
        }
        return data;
    }

    /**
     * MiddleList
     *
     * @param orgRole
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = {"MiddleList", ""})
    public String MiddleList(OrgRole orgRole, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<OrgRole> page = orgRoleService.findMiddlePage(new Page<OrgRole>(request, response), orgRole);
        model.addAttribute("page", page);
        return "modules/sys/staffVsRoleIndex";
    }

    //middleListForm
    @RequiresPermissions("user")
    @RequestMapping(value = "middleListForm")
    public String middleListForm(OrgRole orgRole, Model model) {
        model.addAttribute("orgRole", orgRole);
        return "modules/sys/middleListForm";
    }
    //middleSave

    /**
     * 角色分配
     *
     * @param role
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "middleSave")
    @ResponseBody
    public StringData assignRole(OrgRole role, String [] staffIds, RedirectAttributes redirectAttributes) {
        int newNum = 0;
        newNum=this.orgSelfServiceService.assignRole(role,staffIds);
        StringData data = new StringData();
        if (newNum > 0) {
            data.setCode("success");
            data.setData("分配人员成功");
        } else {
            data.setCode("error");
            data.setData("分配人员失败");
        }
        return data;
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "assign")
    public String assign(OrgRole role, Model model) {
        //查询到这个角色下的人员
        OrgRole orgRole = this.orgRoleService.get(role.getId());
        List<OrgStaff> userList = this.orgStaffService.findUser(new OrgRole(role.getId()));
        model.addAttribute("userList", userList);
        model.addAttribute("role", orgRole);
        return "modules/sys/roleAssign";
    }

    /**
     * 角色分配 -- 打开角色分配对话框
     *
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "usertorole")
    public String selectUserToRole(String roleId,String groupId,String [] userIds,Model model) {
        List<OrgStaff> userList = this.orgStaffService.findUser(new OrgRole(roleId));
        /**角色分配人员时，人员回选与科室分组分配人员会选共用**/
        String path="";
        if(StringUtils.isNotBlank(roleId)){
            model.addAttribute("userList", userList);
            model.addAttribute("selectIds", Collections3.extractToString(userList, "staffId", ","));
            path="modules/sys/selectUserToRole";
        }else if(StringUtils.isNotBlank(groupId)){
            List<BaseDto> list = orgDeptGroupService.findUserList(new OrgDeptGroup(groupId));
            model.addAttribute("userList", list);
            model.addAttribute("selectIds", Collections3.extractToString(list, "staffId", ","));
            path="modules/sys/selectUserToGroup";
        }else{
            List<BaseDto> list=new ArrayList<BaseDto>();
            if(userIds!=null && userIds.length>0) {
                list = systemService.getFindUser(userIds);
            }
            model.addAttribute("userList", list);
            model.addAttribute("selectIds", Collections3.extractToString(list, "staffId", ","));
            path="modules/sys/selectUserToGroup";
        }
        OrgDept orgDept = new OrgDept();
        model.addAttribute("officeList", this.orgDeptService.findList(orgDept));
        return path;
    }


    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "users")
    public List<Map<String, Object>> users(String officeId, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        OrgStaff user = new OrgStaff();
        user.setDeptId(officeId);
//        user.updateOrgId();
//        Page<User> page = systemService.findUser(new Page<User>(1, -1), user);
        List<OrgStaff> list = this.orgStaffService.findUserByDeptId(user);
        for (OrgStaff e : list) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", e.getId());
            map.put("pId", 0);
            map.put("name", e.getName());
            map.put("userid", e.getUser().getId());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * @param staffId
     * @param roleId
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "outrole")
    @ResponseBody
    public StringData outrole(String staffId, String roleId) {
        OrgStaff orgStaff = new OrgStaff();
        orgStaff.setRoleId(roleId);
        orgStaff.setStaffId(staffId);
        int i = this.orgStaffService.deleteFromStaffVsRole(orgStaff);
        StringData data = new StringData();
        if (i > 0) {
            data.setCode("success");
            data.setData("移除人员成功");
        } else {
            data.setCode("error");
            data.setData("移除人员失败");
        }
        return data;
    }

    //    assignServer
    @RequiresPermissions("user")
    @RequestMapping(value = "assignServer")
    public String assignServer(OrgRole role, Model model) {
        ///查找这个角色下面已有得服务
        OrgSelfService orgSelfService = new OrgSelfService();
        orgSelfService.setRoleId(role.getId());
        orgSelfService.updateOrgId();
        List<OrgSelfService> orgSelfServices = this.orgSelfServiceService.findServiceByRoleId(orgSelfService);
        model.addAttribute("role", role);
        model.addAttribute("serviceList", orgSelfServices);
        model.addAttribute("selectIds", Collections3.extractToString(orgSelfServices, "serviceId", ","));
        orgSelfService.setRoleId(null);
        model.addAttribute("serviceListAll", this.orgSelfServiceService.findList(orgSelfService));
        return "modules/sys/serviceAssign";
    }


    /**
     * @param role
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "servicetorole")
    public String servicetorole(OrgRole role, Model model) {
        //查找这个角色下面已有得服务
        OrgSelfService orgSelfService = new OrgSelfService();
        orgSelfService.setRoleId(role.getId());
        orgSelfService.updateOrgId();
        orgSelfService.updateOrgId();
        List<OrgSelfService> orgSelfServices = this.orgSelfServiceService.findServiceByRoleId(orgSelfService);
        model.addAttribute("role", role);
        model.addAttribute("serviceList", orgSelfServices);
        model.addAttribute("selectIds", Collections3.extractToString(orgSelfServices, "serviceId", ","));
        orgSelfService.setRoleId(null);
        model.addAttribute("serviceListAll", this.orgSelfServiceService.findList(orgSelfService));
        return "modules/sys/selectServiceToRole";
    }


    @RequiresPermissions("user")
    @RequestMapping(value = "middleSaveService")
    @ResponseBody
    public StringData middleSaveService(String roleId, String serviceId,String [] menuIds) {
        StringData data=this.orgSelfServiceService.middleSaveService(roleId,serviceId,menuIds);
        return data;
    }


    @RequiresPermissions("user")
    @RequestMapping(value = "outService")
    @ResponseBody
    public StringData outService(String serviceId, String roleId) {
        OrgSelfService orgSelfService = new OrgSelfService();
        orgSelfService.setServiceId(serviceId);
        orgSelfService.setRoleId(roleId);
        int i = this.orgSelfServiceService.deleteFromRoleVsService(orgSelfService);
        StringData data = new StringData();
        if (i > 0) {
            data.setCode("success");
            data.setData("移除服务成功");
        } else {
            data.setCode("error");
            data.setData("移除服务失败");
        }
        return data;
    }

}