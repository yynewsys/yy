package com.yy.master.modules.gen.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yy.master.common.data.StringData;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.modules.gen.service.GenTableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yy.master.common.persistence.Page;
import com.yy.master.common.web.BaseController;
import com.yy.master.modules.sys.entity.User;
import com.yy.master.modules.sys.utils.UserUtils;
import com.yy.master.modules.gen.entity.GenTable;
import com.yy.master.modules.gen.util.GenUtils;

/**
 * 业务表Controller
 * @author ThinkGem
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/gen/genTable")
public class GenTableController extends BaseController {

	@Autowired
	private GenTableService genTableService;
	
	@ModelAttribute
	public GenTable get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return genTableService.get(id);
		}else{
			return new GenTable();
		}
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(GenTable genTable, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			genTable.setCreateBy(user);
		}
        Page<GenTable> page = genTableService.find(new Page<GenTable>(request, response), genTable); 
        model.addAttribute("page", page);
		return "modules/gen/genTableIndex";
	}

    @RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(GenTable genTable, Model model) {
		// 获取物理表列表
		List<GenTable> tableList = genTableService.findTableListFormDb(new GenTable());
		model.addAttribute("tableList", tableList);
		// 验证表是否存在
		if (StringUtils.isBlank(genTable.getId()) && !genTableService.checkTableName(genTable.getName())){
			addMessage(model, "下一步失败！" + genTable.getName() + " 表已经添加！");
			genTable.setName("");
		}
		// 获取物理表字段
		else{
			genTable = genTableService.getTableFormDb(genTable);
		}
		model.addAttribute("genTable", genTable);
		model.addAttribute("config", GenUtils.getConfig());
		return "modules/gen/genTableForm";
	}

    @RequiresPermissions("user")
	@RequestMapping(value = "save")
    @ResponseBody
	public StringData save(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {
        StringData data=new StringData();
		// 验证表是否已经存在
		if (StringUtils.isBlank(genTable.getId()) && !genTableService.checkTableName(genTable.getName())){
            data.setCode("error");
            data.setData( "保存失败！" + genTable.getName() + " 表已经存在！");
			return data;
		}
		int i=genTableService.save(genTable);
        if(i>0){
            data.setCode("success");
            data.setData("保存业务表'" + genTable.getName() + "'成功");
        }else{
            data.setCode("error");
            data.setData("保存业务表'" + genTable.getName() + "'失败");
        }
		return data;
	}

    @RequiresPermissions("user")
	@RequestMapping(value = "delete")
    @ResponseBody
	public StringData delete(GenTable genTable, RedirectAttributes redirectAttributes) {
        StringData data=new StringData();
        int i=genTableService.delete(genTable);
        if(i>0){
            data.setCode("success");
            data.setData("删除业务表成功");
        }else{
            data.setCode("error");
            data.setData("删除业务表失败");
        }
        return data;
	}

}
