/**
 * jims
 */
package com.yy.master.modules.sys.web;

import com.yy.master.common.data.StringData;
import com.yy.master.common.persistence.Page;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.common.web.BaseController;
import com.yy.master.modules.sys.entity.PerformFreqDict;
import com.yy.master.modules.sys.service.PerformFreqDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 频次字典表Controller
 * @author 刘方舟
 * @version 2017-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/performFreqDict")
public class PerformFreqDictController extends BaseController {

	@Autowired
	private PerformFreqDictService performFreqDictService;

    @ModelAttribute
 	public PerformFreqDict get(@RequestParam(required=false) String id) {
		PerformFreqDict entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = performFreqDictService.get(id);
		}
		if (entity == null){
			entity = new PerformFreqDict();
		}
		return entity;
	}

    /**
     *
     * @param id
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "findById")
    @ResponseBody
    public PerformFreqDict findById(@RequestParam(required=false) String id) {
        PerformFreqDict entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = performFreqDictService.get(id);
        }
        if (entity == null){
            entity = new PerformFreqDict();
        }
        return entity;
    }

	@RequiresPermissions("user")
	@RequestMapping(value = {"index", ""})
	public String list(PerformFreqDict performFreqDict, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PerformFreqDict> page = performFreqDictService.findPage(new Page<PerformFreqDict>(request, response), performFreqDict); 
		model.addAttribute("page", page);
		return "modules/sys/performFreqDictIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "form")
	public String form(PerformFreqDict performFreqDict, Model model) {
		model.addAttribute("performFreqDict", performFreqDict);
		return "modules/sys/performFreqDictForm";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public StringData save(PerformFreqDict performFreqDict, Model model) {
	    StringData data=new StringData();
		int i=performFreqDictService.save(performFreqDict);
        if(i>0){
            data.setCode("success");
            data.setData("保存频次字典成功");
        }else{
            data.setCode("error");
            data.setData("保存频次字典失败");
        }
        return data;
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public StringData delete(PerformFreqDict performFreqDict) {
	    StringData data=new StringData();
		int i=performFreqDictService.delete(performFreqDict);
        if(i>0){
            data.setCode("success");
            data.setData("删除频次字典成功");
        }else{
            data.setCode("error");
            data.setData("删除频次字典失败");
        }
		return data;
	}

}