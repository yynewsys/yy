/**
 * jims.
 */
package com.yy.master.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.yy.master.common.beanvalidator.BeanValidators;
import com.yy.master.common.data.StringData;
import com.yy.master.common.persistence.Page;
import com.yy.master.common.utils.CacheUtils;
import com.yy.master.common.utils.DateUtils;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.common.utils.excel.ExportExcel;
import com.yy.master.common.utils.excel.ImportExcel;
import com.yy.master.common.web.BaseController;
import com.yy.master.modules.sys.entity.Company;
import com.yy.master.modules.sys.entity.OrgDept;
import com.yy.master.modules.sys.entity.OrgStaff;
import com.yy.master.modules.sys.entity.User;
import com.yy.master.modules.sys.service.CompanyService;
import com.yy.master.modules.sys.service.OrgDeptService;
import com.yy.master.modules.sys.service.OrgStaffService;
import com.yy.master.modules.sys.service.SystemService;
import com.yy.master.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.config.Global;

/**
 * 用户Controller
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {
	@Autowired
	private SystemService systemService;
	@Autowired
	private OrgDeptService orgDeptService;
	@Autowired
	private OrgStaffService orgStaffService;
	@Autowired
	private CompanyService companyService;
	
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getUser(id);
		}else{
			return new User();
		}
	}

	@RequiresPermissions("user")
	@RequestMapping(value = {"index"})
	public String index(User user,HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = new Page<User>();
		if(StringUtils.isNotBlank(UserUtils.getOrgId())){
			page = systemService.findUser(new Page<User>(request, response), user);
		}else{
			page = systemService.findPage(new Page<User>(request, response), user);
		}
		model.addAttribute("page", page);
		return "modules/sys/sysUserIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = {"list", ""})
	public String list(User user, String deptId,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotBlank(deptId)){
			user.setOrgDept(new OrgDept(deptId));
		}
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/sys/sysUserIndex";
	}
	
	@ResponseBody
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"listData"})
	public Page<User> listData(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
		return page;
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		if(user!=null&&StringUtils.isNotBlank(user.getId())){
			OrgStaff orgStaff = orgStaffService.findByUserId(user.getId());
			if(orgStaff!=null){
				OrgDept orgDept = orgDeptService.get(orgStaff.getDeptId());
				Company company = companyService.get(orgStaff.getOrgId());
				user.setOrgDept(orgDept);
				user.setCompany(company);
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("currentUser",UserUtils.getUser());
		return "modules/sys/sysUserForm";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(User user) {
		StringData data=new StringData();
		int i=systemService.saveUser(user);
		if(i>0){
			data.setCode("success");
			data.setData("保存成功");
		}else {
			data.setCode("error");
			data.setData("保存失败");
		}
		return data;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(User user) {
		StringData sd=new StringData();
		int i=systemService.deleteUserInfo(user);
		if(i>0){
			sd.setCode("success");
			sd.setData("删除成功");
		}else{
			sd.setCode("error");
			sd.setData("删除失败");
		}
		return sd;
	}
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<User> page = systemService.findUser(new Page<User>(request, response, -1), user);
    		new ExportExcel("用户数据", User.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<User> list = ei.getDataList(User.class);
			for (User user : list){
				try{
					if ("true".equals(checkLoginName("", user.getCurrentLogin()))){
						user.setPassword(SystemService.entryptPassword("123456"));
						BeanValidators.validateWithException(validator, user);
						systemService.saveUser(user);
						successNum++;
					}else{
						failureMsg.append("<br/>登录名 "+user.getCurrentLogin()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>登录名 "+user.getCurrentLogin()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>登录名 "+user.getCurrentLogin()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据导入模板.xlsx";
    		List<User> list = Lists.newArrayList(); list.add(UserUtils.getUser());
    		new ExportExcel("用户数据", User.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName !=null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}



	/**
	 * 返回用户信息
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}
	/**
	 * 跳转到修改密码页面
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPassForm")
	public String modifyPassForm() {
		return "modules/sys/modifyPassForm";
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "validOldPass")
	@ResponseBody
	public StringData validOldPass(String password) {
		StringData data=new StringData();
		User user = UserUtils.getUser();
		if (SystemService.validatePassword(password, user.getPassword())){
			data.setCode("success");
		}else {
			data.setCode("error");
			data.setData("旧密码输入错误，请重新输入...");
		}
		return data;
	}
	/**
	 * 修改个人用户密码
	 * @param password
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPwd")
	@ResponseBody
	public StringData modifyPwd(String password) {
		User user = UserUtils.getUser();
		StringData data=new StringData();
		if (StringUtils.isNotBlank(password)){
			int count = systemService.updatePasswordById(user.getId(), user.getCurrentLogin(), password);
			if(count>0){
				data.setCode("success");
				data.setData("密码修改成功");
			}else{
				data.setCode("error");
				data.setData("密码修改失败");
			}
		}
		return data;
	}
	/**
	 * 重置指定用户密码
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "resetPass")
	@ResponseBody
	public StringData resetPass(String id) {
		StringData data=new StringData();
		int i = 0;
		i = systemService.resetPass(id,"123456");
		if(i>0){
			data.setCode("success");
			data.setData("该用户密码重置成功");
		}else{
			data.setCode("error");
			data.setData("该用户重置密码失败");
		}
		return data;
	}


	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String deptId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<User> list = systemService.findUserByDeptId(deptId);
		for (int i=0; i<list.size(); i++){
			User e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_"+e.getId());
			map.put("pId", deptId);
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

    /**
     * 更新用户权限科室信息
     * @param deptId
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "updateUser")
    @ResponseBody
    public StringData updateUser(String deptId,String deptName) {
        StringData data=new StringData();
        User user=UserUtils.getUser();
        OrgDept orgDept=new OrgDept();
        orgDept.setId(deptId);
        orgDept.setDeptName(deptName);
        user.setOrgDept(orgDept);
        UserUtils.updateCache(user);
        data.setCode("success");
        data.setData("更新用户权限科室信息成功");
        return data;
    }
}
