/**
 * jims.
 */
package com.yy.master.modules.sys.utils;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.service.BaseService;
import com.yy.master.common.utils.CacheUtils;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.modules.sys.dao.*;
import com.yy.master.modules.sys.entity.*;
import com.yy.master.modules.sys.security.SystemAuthorizingRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.yy.master.common.utils.SpringContextHolder;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static OrgRoleDao roleDao = SpringContextHolder.getBean(OrgRoleDao.class);
    private static OrgStaffDao orgStaffDao = SpringContextHolder.getBean(OrgStaffDao.class);




    public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";

	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
    public static final String CACHE_STAFF_ALL_LIST = "orgStaffList";
    public static final String CACHE_ROLE_ALL_LIST = "orgRoleList";
    public static final String CACHE_USER_ALL_LIST = "userList";


    /**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id){
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user ==  null){
			//user = userDao.get(id);
			user = userDao.getInfoById(id);
			if (user == null){
				return null;
			}
			//user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getCurrentLogin(), user);
		}
		return user;
	}

    /**
     * chenxy
     * 获取用户名称
     * @param userId
     * @return
     */
    public static String getUserName(String userId){
        return get(userId)!=null?get(userId).getName():null;
    }

    /**
     * 获取orgId
     * @return 取不到返回null
     */
    public static String getOrgId(){
       User user =getUser();
        if(StringUtils.isBlank(user.getOrgId())){
            return "";
        }
        return user.getOrgId();
    }
	/**
	 * 根据登录名获取用户
	 * @param currentLogin
	 * @return 取不到返回null
	 */
	public static User getByLoginName(String currentLogin){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + currentLogin);
		if (user == null){
			user = userDao.getByLoginName(new User(null, currentLogin));
			if (user == null){
				return null;
			}
			//user.setRoleList(roleDao.findList(new Role(user)));
			//CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			//CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getCurrentLogin(), user);
		}
		return user;
	}
	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_OFFICE_LIST);
		removeCache(CACHE_OFFICE_ALL_LIST);
		UserUtils.clearCache(getUser());
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(User user){
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getCurrentLogin());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
		/*if (user.getOffice() != null && user.getOffice().getId() != null){
			CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
		}*/
	}

    /**
     * 更新用户信息
     * @param user
     */
    public static void updateCache(User user){
        CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
        CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getCurrentLogin());
        CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
        CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
        CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getCurrentLogin(), user);
    }
	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		SystemAuthorizingRealm.Principal principal = getPrincipal();
		if (principal!=null){
			User user = get(principal.getId());
			if (user != null){
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
	/*public static List<Role> getRoleList(){
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>)getCache(CACHE_ROLE_LIST);
		if (roleList == null){
			User user = getUser();
			if (user.isAdmin()){
				roleList = roleDao.findAllList(new Role());
			}else{
				Role role = new Role();
				role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
				roleList = roleDao.findList(role);
			}
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}*/
	


	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static SystemAuthorizingRealm.Principal getPrincipal(){
        try{
            Subject subject = SecurityUtils.getSubject();
            SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal)subject.getPrincipal();
            if (principal != null){
                return principal;
            }
//			subject.logout();
        }catch (UnavailableSecurityManagerException e) {

        }catch (InvalidSessionException e){

        }
        return null;
    }

    public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
//			subject.logout();
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
//		getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
//		getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}
	
//	public static Map<String, Object> getCacheMap(){
//		Principal principal = getPrincipal();
//		if(principal!=null){
//			return principal.getCacheMap();
//		}
//		return new HashMap<String, Object>();
//	}
    /**
     * 是否是验证码登录
     * @param useruame 用户名
     * @param isFail 计数加1
     * @param clean 计数清零
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
        if (loginFailMap==null){
            loginFailMap = Maps.newHashMap();
            CacheUtils.put("loginFailMap", loginFailMap);
        }
        Integer loginFailNum = loginFailMap.get(useruame);
        if (loginFailNum==null){
            loginFailNum = 0;
        }
        if (isFail){
            loginFailNum++;
            loginFailMap.put(useruame, loginFailNum);
        }
        if (clean){
            loginFailMap.remove(useruame);
        }
        return loginFailNum >= 3;
    }


    /**
     * @author chenxy
     * @desc 查询当前组织机构下的用户信息
     * @return
     */
    public static List<OrgStaff> getOrgStaffList(){
        @SuppressWarnings("unchecked")
        List<OrgStaff> orgStaffsList = (List<OrgStaff>)getCache(CACHE_STAFF_ALL_LIST);
        if (orgStaffsList == null){
            OrgStaff orgStaff=new OrgStaff();
            String orgId=UserUtils.getUser().getOrgId();
            orgStaff.setOrgId(orgId);
            orgStaffsList = orgStaffDao.findJoinPerson(orgStaff);
        }
        return orgStaffsList;
    }

    /**
     *  @desc 查询所有角色信息
     * @Author chenxy
     * @return
     */
    public static List<OrgRole> getRoleList(){
        @SuppressWarnings("unchecked")
        List<OrgRole> orgRoleList = (List<OrgRole>)getCache(CACHE_ROLE_ALL_LIST);
        if (orgRoleList == null){
            OrgRole orgRole=new OrgRole();
            orgRole.updateOrgId();
            orgRoleList = roleDao.findList(orgRole);
        }
        return orgRoleList;
    }

//    getUserList

    public static List<User> getUserList(){
        List<User> users = (List<User>)getCache(CACHE_USER_ALL_LIST);
        if (users == null){
            User user=new User();
            user.updateOrgId();
            users = userDao.findList(user);
        }
        return users;
    }

    public static List<User> getRoleUserList(String deptId){
    	List<User> users =  userDao.getRoleUserList(deptId);
    	return users;
	}
}
