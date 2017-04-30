package com.yy.master.modules.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.data.StringData;
import com.yy.master.common.persistence.DataEntity;
import com.yy.master.common.persistence.Page;
import com.yy.master.common.utils.ChineseCharToEnUtils;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.common.web.BaseController;
import com.yy.master.modules.sys.entity.Dict;
import com.yy.master.modules.sys.entity.HospitalDict;
import com.yy.master.modules.sys.service.DictService;
import com.yy.master.modules.sys.service.HospitalDictService;
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
import java.util.List;
import java.util.Map;

/**
 * Created by DT on 2017/2/21.
 * 医院字典Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/hospitalDict")
public class HospitalDictController extends BaseController {
    @Autowired
    private HospitalDictService hospitalDictService;

    /**
     * 根据ID获取并返回对象
     * @param id
     * @return
     */
    @ModelAttribute
    public HospitalDict get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)){
            return hospitalDictService.get(id);
        }else{
            return new HospitalDict();
        }
    }

    /**
     * 获取列表
     * @param hospitalDict
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = {"index", ""})
    public String list(HospitalDict hospitalDict, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<String> typeList = hospitalDictService.findTypeList();
        model.addAttribute("typeList", typeList);
        Page<HospitalDict> page = hospitalDictService.findPage(new Page<HospitalDict>(request, response), hospitalDict);
        model.addAttribute("page", page);
        return "modules/sys/sysHospitalDictIndex";
    }

    /**
     * 添加和编辑跳转
     * @param hospitalDict
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "form")
    public String form(HospitalDict hospitalDict, Model model) {
        model.addAttribute("hospitalDict", hospitalDict);
        return "modules/sys/sysHospitalDictForm";
    }

    /**
     * 数据更新与保存
     * @param hospitalDict
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "save")
    @ResponseBody
    public StringData save(HospitalDict hospitalDict, Model model) {
        hospitalDict.updateOrgId();
        hospitalDict.setInput_code(ChineseCharToEnUtils.getAllFirstLetter(hospitalDict.getLabel()));
        StringData data=new StringData();
        int i=hospitalDictService.save(hospitalDict);
        if(i>0){
            data.setCode("success");
            data.setData("保存成功");
        }else{
            data.setCode("error");
            data.setData("保存失败");
        }
        return data;
    }

    /**
     * 数据删除
     * @param hospitalDict
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "delete")
    @ResponseBody
    public StringData delete(HospitalDict hospitalDict) {
        StringData data=new StringData();
        int i=hospitalDictService.delete(hospitalDict);;
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
        HospitalDict hospitalDict = new HospitalDict();
        hospitalDict.setType(type);
        List<HospitalDict> list = hospitalDictService.findList(hospitalDict);
        for (int i=0; i<list.size(); i++){
            HospitalDict e = list.get(i);
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
    public List<HospitalDict> listData(@RequestParam(required=false) String type) {
        HospitalDict hospitalDict = new HospitalDict();
        hospitalDict.updateOrgId();
        return hospitalDictService.findList(hospitalDict);
    }
}
