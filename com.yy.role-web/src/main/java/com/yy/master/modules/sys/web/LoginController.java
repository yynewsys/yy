/**
 * jims.
 */
package com.yy.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yy.master.common.security.shiro.session.SessionDAO;
import com.yy.master.common.servlet.ValidateCodeServlet;
import com.yy.master.common.utils.*;
import com.yy.master.common.web.BaseController;
import com.yy.master.modules.sys.entity.OrgSelfService;
import com.yy.master.modules.sys.entity.SysMenuDict;
import com.yy.master.modules.sys.entity.User;
import com.yy.master.modules.sys.security.FormAuthenticationFilter;
import com.yy.master.modules.sys.security.SystemAuthorizingRealm;
import com.yy.master.modules.sys.service.OrgSelfServiceService;
import com.yy.master.modules.sys.service.SysMenuDictService;
import com.yy.master.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yy.master.common.config.Global;

import java.util.List;

/**
 * 登录Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
public class LoginController extends BaseController {
	
	@Autowired
	private SessionDAO sessionDAO;

    @Autowired
    private SysMenuDictService sysMenuDictService;
    @Autowired
    private OrgSelfServiceService orgSelfServiceService;



	
	/**
	 * 管理登录
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();

//		// 默认页签模式
//		String tabmode = CookieUtils.getCookie(request, "tabmode");
//		if (tabmode == null){
//			CookieUtils.setCookie(response, "tabmode", "1");
//		}
		
		if (logger.isDebugEnabled()){
			logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			CookieUtils.setCookie(response, "LOGINED", "false");
		}
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null && !principal.isMobileLogin()){
			return "redirect:" + adminPath;
		}
//		String view;
//		view = "/WEB-INF/views/modules/sys/sysLogin.jsp";
//		view = "classpath:";
//		view += "jar:file:/D:/GitHub/Jims/src/main/webapp/WEB-INF/lib/Jims.jar!";
//		view += "/"+getClass().getName().replaceAll("\\.", "/").replace(getClass().getSimpleName(), "")+"view/sysLogin";
//		view += ".jsp";
		return "modules/sys/sysLogin";
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
	public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null){
			return "redirect:" + adminPath;
		}

		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		
		if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")){
			message = "用户或密码错误, 请重试.";
		}

		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
		
		if (logger.isDebugEnabled()){
			logger.debug("login fail, active session size: {}, message: {}, exception: {}", 
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		
		// 非授权异常，登录失败，验证码加1。
		if (!UnauthorizedException.class.getName().equals(exception)){
			model.addAttribute("isValidateCodeLogin",UserUtils.isValidateCodeLogin(username, true, false));
		}
		
		// 验证失败清空验证码
		//request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());
		
		// 如果是手机登录，则返回JSON字符串
		if (mobile){
	        return renderString(response, model);
		}
		
		return "modules/sys/sysLogin";
	}

	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();

		// 登录成功后，验证码计算器清零
        UserUtils.isValidateCodeLogin(principal.getLoginName(), false, true);
		
		if (logger.isDebugEnabled()){
			logger.debug("show index, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			String logined = CookieUtils.getCookie(request, "LOGINED");
			if (StringUtils.isBlank(logined) || "false".equals(logined)){
				CookieUtils.setCookie(response, "LOGINED", "true");
			}else if (StringUtils.equals(logined, "true")){
				UserUtils.getSubject().logout();
				return "redirect:" + adminPath + "/login";
			}
		}
		
		// 如果是手机登录，则返回JSON字符串
		if (principal.isMobileLogin()){
			if (request.getParameter("login") != null){
				return renderString(response, principal);
			}
			if (request.getParameter("index") != null){
				return "modules/sys/sysIndex";
			}
			return "redirect:" + adminPath + "/login";
		}
		
//		// 登录成功后，获取上次登录的当前站点ID
		User user = UserUtils.getUser();
		CacheUtils.put("userType",user.getUserType());
		if(user!=null&&StringUtils.isNotBlank(user.getId())){
//			JedisUtils.setObject("userRole" + user.getId(), "", 0);
		}
		return "modules/sys/sysIndex";
	}
	
	/**
	 * 获取主题方案
	 */
	@RequestMapping(value = "/theme/{theme}")
	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response){
		if (StringUtils.isNotBlank(theme)){
			CookieUtils.setCookie(response, "theme", theme);
		}else{
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:"+request.getParameter("url");
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "${adminPath}/personInfo")
	public String personInfo(HttpServletRequest request, HttpServletResponse response) {
		return "modules/sys/sysIndex";
	}

    /**
     * 切换自定义服务
     * @param serviceId
     * @param model
     * @return
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "${adminPath}/service/change")
    public String change(String serviceId,Model model) {
        User user=UserUtils.getUser();
        List<SysMenuDict> sysMenuDictList=null;
        if("2".equals(user.getUserType())){
            sysMenuDictList= sysMenuDictService.findMenuByOrgId(user.getOrgId());
        }else{
            sysMenuDictList= sysMenuDictService.findServiceMenu(serviceId,user.getId());
            OrgSelfService orgSelfService=orgSelfServiceService.get(serviceId);
            user.setCurrentService(orgSelfService);
            UserUtils.updateCache(user);
        }

        model.addAttribute("sysMenuDictList", sysMenuDictList);
        return "modules/sys/treeMenu";
    }


    @RequestMapping(value = "${adminPath}/logout")
    public String logout() {
        UserUtils.clearCache(UserUtils.getUser());
        UserUtils.getSubject().logout();
        return "redirect:" + adminPath;
    }

}
