/**
 * jims.
 */
package com.yy.master.modules.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.data.StringData;
import com.yy.master.common.persistence.Page;
import com.yy.master.common.utils.Collections3;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.common.web.BaseController;
import com.yy.master.modules.sys.dao.SysServiceDao;
import com.yy.master.modules.sys.entity.Company;
import com.yy.master.modules.sys.entity.Dict;
import com.yy.master.modules.sys.entity.SysService;
import com.yy.master.modules.sys.service.CompanyService;
import com.yy.master.modules.sys.service.DictService;
import com.yy.master.modules.sys.utils.CompanyTreeTable;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 *@version  1.0
 * @since 1.0
 * @author 陈晓阳
 * <p>
 *     机构分配服务与机构信息
 * </p>
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/company")
public class CompanyController extends BaseController {

	@Autowired
	private CompanyService companyService;

    @Autowired
    private SysServiceDao sysServiceDao;

    /**
     * 获取单个对象
     * @param id
     * @return
     */
	@ModelAttribute
	public Company get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return companyService.get(id);
		}else{
			return new Company();
		}
	}

    /**
     *为treeTable准备数据
     * @Author chenxy
     * @param company
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(Company company, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Company> page = companyService.findPage(new Page<Company>(request, response), company);
        List<Company> list=page.getList();
        List<Company> treeList=new CompanyTreeTable(list).buildTree();
        page.setList(treeList);
        model.addAttribute("page", page);
		return "modules/sys/sysCompanyIndex";
	}
    /*
    * form 表单跳转
     */
    @RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(Company company, Model model) {
		model.addAttribute("company", company);
		return "modules/sys/sysCompanyForm";
	}

    @RequiresPermissions("user")
	@RequestMapping(value = "save")
    @ResponseBody
	public StringData save(Company company, Model model) {
        StringData data=new StringData();
        int i =companyService.save(company);
        if(i>0){
            data.setCode("success");
            data.setData("保存机构成功");
        }else{
            data.setCode("error");
            data.setData("保存机构失败");
        }
        return data;
	}

    @RequiresPermissions("user")
	@RequestMapping(value = "delete")
    @ResponseBody
	public StringData delete(Company company, RedirectAttributes redirectAttributes) {
        int i = companyService.delete(company);
        StringData data=new StringData();
        if(i>0){
            data.setCode("success");
            data.setData("保存机构成功");
        }else{
            data.setCode("error");
            data.setData("保存机构失败");
        }
        return data;
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String type, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
 		List<Company> list = companyService.findList(new Company());
		for (int i=0; i<list.size(); i++){
            Company e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", e.getOrgName());
			mapList.add(map);
		}
		return mapList;
	}
	
//    assignService
    @RequiresPermissions("user")
    @RequestMapping(value = "assignService")
    public String assignServer(Company company, Model model) {
        //查询到这个机构下的服务
        Company company1 = this.companyService.get(company.getId());
        List<SysService> sysServices=this.companyService.findServiceByOrg(company);
        model.addAttribute("serviceList", sysServices);
        model.addAttribute("company", company1);
        return "modules/sys/companyAssign";
    }



    @RequiresPermissions("user")
    @RequestMapping(value = "servicetoCompany")
    public String servicetorole(Company company, Model model) {
        //查找这个机构下面已有得服务
        Company company1 = this.companyService.get(company.getId());
        List<SysService> sysServices=this.companyService.findServiceByOrg(company);
        // 查找所有得系统服务
        model.addAttribute("company", company1);
        model.addAttribute("serviceList", sysServices);
        model.addAttribute("selectIds", Collections3.extractToString(sysServices, "serviceId", ","));
        model.addAttribute("serviceListAll", this.sysServiceDao.findAllList(new SysService()));
        return "modules/sys/selectServiceToCompany";
    }

//    middleSaveService

    @RequiresPermissions("user")
    @RequestMapping(value = "middleSaveService")
    @ResponseBody
    public StringData middleSaveService(Company company, String[] serviceIds, RedirectAttributes redirectAttributes) {
        StringBuilder msg = new StringBuilder();
        int newNum = 0;
        StringData data = new StringData();
        newNum=this.companyService.middleSaveService(serviceIds,company);
        if (newNum > 0) {
            data.setCode("success");
            data.setData("分配服务成功");
        } else {
            data.setCode("error");
            data.setData("分配服务失败");
        }
        return data;
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "outService")
    @ResponseBody
    public StringData outService(String serviceId, String companyId) {
         Company company=new Company();
         company.setCompanyId(companyId);
         company.setServiceId(serviceId);
         int i = this.companyService.deleteFromCompanyVsService(company);
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
