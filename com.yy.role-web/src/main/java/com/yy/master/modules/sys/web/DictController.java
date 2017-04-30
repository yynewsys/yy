/**
 * jims.
 */
package com.yy.master.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yy.master.common.data.StringData;
import com.yy.master.common.utils.ChineseCharToEnUtils;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.modules.sys.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.config.Global;
import com.yy.master.common.persistence.Page;
import com.yy.master.common.web.BaseController;
import com.yy.master.modules.sys.entity.Dict;

/**
 * 字典Controller
 * @author ThinkGem
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;
	
	@ModelAttribute
	public Dict get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return dictService.get(id);
		}else{
			return new Dict();
		}
	}
    @RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> typeList = dictService.findTypeList();
		model.addAttribute("typeList", typeList);
        Page<Dict> page = dictService.findPage(new Page<Dict>(request, response), dict); 
        model.addAttribute("page", page);
		return "modules/sys/sysDictIndex";
	}

    @RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(Dict dict, Model model) {
		model.addAttribute("dict", dict);
		return "modules/sys/sysDictForm";
	}

    @RequiresPermissions("user")
	@RequestMapping(value = "save")
    @ResponseBody
	public StringData save(Dict dict, Model model) {
        dict.setInputCode(ChineseCharToEnUtils.getAllFirstLetter(dict.getLabel()));
        StringData data=new StringData();
		int i=dictService.save(dict);
        if(i>0){
            data.setCode("success");
            data.setData("保存成功");
        }else{
            data.setCode("error");
            data.setData("保存失败");
        }
        return data;
	}

    @RequiresPermissions("user")
	@RequestMapping(value = "delete")
    @ResponseBody
	public StringData delete(Dict dict) {
        StringData data=new StringData();
		int i=dictService.delete(dict);;
        if(i>0){
            data.setCode("success");
            data.setData("删除成功");
        }else{
            data.setCode("error");
            data.setData("删除失败");
        }
		return data;
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String type, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list = dictService.findList(dict);
		for (int i=0; i<list.size(); i++){
			Dict e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", StringUtils.replace(e.getLabel(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}
	
	@ResponseBody
	@RequestMapping(value = "listData")
	public List<Dict> listData(@RequestParam(required=false) String type) {
		Dict dict = new Dict();
		dict.setType(type);
		return dictService.findList(dict);
	}

}
