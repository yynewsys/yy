package com.yy.master.modules.sys.web;

import com.yy.master.common.data.StringData;
import com.yy.master.common.persistence.Page;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.common.web.BaseController;
import com.yy.master.modules.sys.entity.ExternalUrl;
import com.yy.master.modules.sys.service.ExternalUrlService;
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

/**
 * Created by DT on 2017/2/23.
 * 外部链接Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/externalUrl")
public class ExternalUrlController extends BaseController{
    @Autowired
    private ExternalUrlService externalUrlService;

    /**
     * 根据ID获取并返回对象
     * @param id
     * @return
     */
    @ModelAttribute
    public ExternalUrl get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)){
            return externalUrlService.get(id);
        }else{
            return new ExternalUrl();
        }
    }

    /**
     *获取列表
     * @param externalUrl
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = {"index", ""})
    public String index(ExternalUrl externalUrl, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<ExternalUrl> page = externalUrlService.findPage(new Page<ExternalUrl>(request, response), externalUrl);
        model.addAttribute("page", page);
        return "modules/sys/sysExternalUrlIndex";
    }

    /**
     * 添加和编辑跳转
     * @param externalUrl
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "form")
    public String form(ExternalUrl externalUrl, Model model) {
        model.addAttribute("extendLink", externalUrl);
        return "modules/sys/sysExternalUrlForm";
    }

    /**
     * 数据更新与保存
     * @param externalUrl
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "save")
    @ResponseBody
    public StringData save(ExternalUrl externalUrl, Model model) {
        externalUrl.updateOrgId();
        StringData data=new StringData();
        int i=externalUrlService.save(externalUrl);
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
     * @param externalUrl
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "delete")
    @ResponseBody
    public StringData delete(ExternalUrl externalUrl) {
        StringData data=new StringData();
        int i=externalUrlService.delete(externalUrl);;
        if(i>0){
            data.setCode("success");
            data.setData("删除成功");
        }else{
            data.setCode("error");
            data.setData("删除失败");
        }
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "listData")
    public List<ExternalUrl> listData(@RequestParam(required=false) String type) {
        ExternalUrl externalUrl = new ExternalUrl();
        externalUrl.updateOrgId();
        return externalUrlService.findList(externalUrl);
    }
}
