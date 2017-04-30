package com.yy.master.modules.gen.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yy.master.common.data.StringData;
import com.yy.master.common.persistence.Page;
import com.yy.master.common.web.BaseController;
import com.yy.master.modules.gen.entity.GenScheme;
import com.yy.master.modules.gen.service.GenSchemeService;
import com.yy.master.modules.gen.service.GenTableService;
import com.yy.master.modules.gen.util.GenUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 生成方案Controller
 * @author ThinkGem
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/gen/genScheme")
public class GenSchemeController extends BaseController {

	@Autowired
	private GenSchemeService genSchemeService;
	
	@Autowired
	private GenTableService genTableService;
	
	@ModelAttribute
	public GenScheme get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return genSchemeService.get(id);
		}else{
			return new GenScheme();
		}
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(GenScheme genScheme, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GenScheme> page = genSchemeService.find(new Page<GenScheme>(request, response), genScheme);
        model.addAttribute("page", page);
 		return "modules/gen/genSchemeIndex";
	}

    @RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(GenScheme genScheme, Model model) {
		if (StringUtils.isBlank(genScheme.getPackageName())){
			genScheme.setPackageName("com.jims.master.modules");
		}
//		if (StringUtils.isBlank(genScheme.getFunctionAuthor())){
//			genScheme.setFunctionAuthor(UserUtils.getUser().getName());
//		}
		model.addAttribute("genScheme", genScheme);
		model.addAttribute("config", GenUtils.getConfig());
		model.addAttribute("tableList", genTableService.findAll());
		return "modules/gen/genSchemeForm";
	}

    @RequiresPermissions("user")
	@RequestMapping(value = "save")
    @ResponseBody
	public StringData save(GenScheme genScheme, Model model) {
        StringData data=new StringData();
		int i = genSchemeService.save(genScheme);
        if(i>0){
            data.setCode("success");
            data.setData("操作生成方案" + genScheme.getName() + "'成功");
        }else{
            data.setCode("error");
            data.setData("操作生成方案'" + genScheme.getName() + "'失败");
        }
		return data;
	}

    @RequiresPermissions("user")
	@RequestMapping(value = "delete")
    @ResponseBody
	public StringData delete(GenScheme genScheme) {
        StringData data=new StringData();
        int i=genSchemeService.delete(genScheme);
        if(i>0){
            data.setCode("success");
            data.setData("删除生成方案成功");
        }else{
            data.setCode("error");
            data.setData("删除生成方案失败");
        }
        return data;
	}

}
