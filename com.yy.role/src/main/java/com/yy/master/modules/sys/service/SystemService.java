/**
 * jims.
 */
package com.yy.master.modules.sys.service;

import com.yy.master.common.config.Global;
import com.yy.master.common.persistence.BaseEntity;
import com.yy.master.common.persistence.Page;
import com.yy.master.common.security.Digests;
import com.yy.master.common.security.shiro.session.SessionDAO;
import com.yy.master.common.service.BaseService;
import com.yy.master.common.utils.*;
import com.yy.master.common.web.impl.BaseDto;
import com.yy.master.modules.sys.dao.OrgStaffDao;
import com.yy.master.modules.sys.dao.PersonInfoDao;
import com.yy.master.modules.sys.dao.SysLoginNameDao;
import com.yy.master.modules.sys.dao.UserDao;
import com.yy.master.modules.sys.entity.*;
import com.yy.master.modules.sys.utils.UserUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author ThinkGem
 * @version 2013-12-05
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private PersonInfoDao personInfoDao;
	@Autowired
	private SysLoginNameDao loginNameDao;
	@Autowired
	private OrgStaffDao orgStaffDao;

	@Autowired
	private SessionDAO sessionDao;

	
	public SessionDAO getSessionDao() {
		return sessionDao;
	}


	//-- User Service --//
	
	/**
	 * 获取用户
	 * @param id
	 * @return
	 */
	public User getUser(String id) {
		return UserUtils.get(id);
	}

	/**
	 * 根据登录名获取用户
	 * @param currentLogin
	 * @return
	 */
	public User getUserByLoginName(String currentLogin) {
		return UserUtils.getByLoginName(currentLogin);
	}
	
	public Page<User> findUser(Page<User> page, User user) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(userDao.findList(user));
		return page;
	}

	public Page<User> findPage(Page<User> page, User user){
		List list = userDao.findAllList(user);
		user.setPage(page);
		page.setList(list);
		return page;
	}
	
	/**
	 * 无分页查询人员列表
	 * @param user
	 * @return
	 */
	public List<User> findUser(User user){
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		//user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		List<User> list = userDao.findList(user);
		return list;
	}

	/**
	 * 系统管理员查询所有用户
	 * @param user
	 * @return
	 */
	public  List<User> findAllList(User user){
		List<User> list = userDao.findAllList(user);
		return list;
	}

    /**
     * 通过多userID获取user
     * @param userIds
     * @return
     */
    public  List<BaseDto> getFindUser(String [] userIds){
        List<BaseDto> list = userDao.getFindUser(userIds);
        return list;
    }
	/**
	 * 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param 'user'
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserByDeptId(String deptId) {
		List<User> list = (List<User>) CacheUtils.get(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + deptId);
		if (list == null){
			User user = new User();
			user.setOrgDept(new OrgDept(deptId));
			list = userDao.findUserByDeptId(user);
			CacheUtils.put(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + deptId, list);
		}
		return list;
	}
	
	@Transactional(readOnly = false)
	public int saveUser(User user) {
		int num = 0;
		/**判断用户是新添加用户还是更新用户**/
		if (StringUtils.isBlank(user.getId())){
			/**保存用户基本信息**/
			PersonInfo personInfo = new PersonInfo();
			personInfo = modifyPersonInfo(personInfo,user);
			personInfo.preInsert();
			personInfoDao.insert(personInfo);
			/**保存用户密码信息**/
			user.setPersionId(personInfo.getId());
			user.setPassword(entryptPassword(user.getPassword()));
			user.preInsert();
			num = userDao.insert(user);
			/**保存用户相关信息**/
			SysLoginName loginName = new SysLoginName();
			loginName.setUser(user);
			saveUserInfo(loginName,user);

			return num;
		}else{
			// 查询原用户信息
			User oldUser = userDao.getInfoById(user.getId());
			/**更新persion_info**/
			PersonInfo personInfo = personInfoDao.get(user.getPersionId());
			personInfo = modifyPersonInfo(personInfo,user);
			personInfo.preUpdate();
			personInfoDao.update(personInfo);
			/**更新sys_login_name(先删除后插入)**/
			SysLoginName loginName = new SysLoginName();
			loginNameDao.delByUserId(user.getId());
			loginName.setUser(oldUser);
			/**更新org_staff（先删除后插入)**/
			orgStaffDao.removeByUserId(user.getId());
			/**保存用户相关信息**/
			saveUserInfo(loginName,user);

			// 更新用户数据
			user.preUpdate();
			num = userDao.update(user);

			/**清除指定用户缓存**/
			UserUtils.clearCache(user);
			return num;
		}
	}


	/**
	 * 用户基本信息共用代码
	 * @param personInfo
	 * @param user
	 * @author CTQ 2017-02-21 11:05:09
	 */
	public PersonInfo modifyPersonInfo(PersonInfo personInfo,User user){
		personInfo.setName(user.getName());
		personInfo.setEmail(user.getEmail());
		personInfo.setPhone(user.getPhone());
		personInfo.setIdCard(user.getIdCard());
		if(user.getIdCard()!=null&&!user.getIdCard().equals("")){
			IdcardInfoExtractor idcardInfo=new IdcardInfoExtractor(user.getIdCard());
			personInfo.setSex(idcardInfo.getGender());
		}
		personInfo.setInputCode(ChineseCharToEnUtils.getAllFirstLetter(user.getName()));
		return personInfo;
	}
	/**
	 * 用户登陆信息共用代码
	 * @param loginName
	 * @param user
	 * @author CTQ 2017-02-21 11:05:05
	 */
	public void saveUserInfo(SysLoginName loginName,User user){
		/**保存用户登录用户名信息**/
        loginName.setLoginName(user.getCurrentLogin());
        loginName.setType(BaseEntity.USER_TYPE_NAME);
        loginName.preInsert();
        loginNameDao.insert(loginName);
		if(StringUtils.isNotBlank(user.getPhone())){
			loginName.setLoginName(user.getPhone());
			loginName.setType(BaseEntity.USER_TYPE_PHONE);
			loginName.preInsert();
			loginNameDao.insert(loginName);
		}
		if(StringUtils.isNotBlank(user.getEmail())){
			loginName.setLoginName(user.getEmail());
			loginName.setType(BaseEntity.USER_TYPE_EMAIL);
			loginName.preInsert();
			loginNameDao.insert(loginName);
		}
		if(StringUtils.isNotBlank(user.getIdCard())){
			loginName.setLoginName(user.getIdCard());
			loginName.setType(BaseEntity.USER_TYPE_IDCARD);
			loginName.preInsert();
			loginNameDao.insert(loginName);
		}
		/**判断当前登录用户的org_id,如果非空，则插入org_staff数据**/
			String orgId = "";
			if (UserUtils.getUser().getUserType().equals("1")&&user.getUserType()!=null&&user.getUserType().equals("2")){/**系统管理员添加机构管理员用户**/
				orgId = user.getCompany()!=null?user.getCompany().getId():"";
			}else{
				orgId = UserUtils.getOrgId();
			}
			if(orgId!=null&&!orgId.equals("")){
				OrgStaff orgStaff = new OrgStaff();
				orgStaff.setUser(user);
				if(user.getOrgDept()!=null){
					orgStaff.setDeptId(user.getOrgDept().getId());
				}
				orgStaff.setOrgId(orgId);
				orgStaff.setTitle(user.getTitle());//职称
				orgStaff.preInsert();
				orgStaffDao.insert(orgStaff);
			}
	}
	
	@Transactional(readOnly = false)
	public void updateUserInfo(User user) {
		user.preUpdate();
		userDao.updateUserInfo(user);
		// 清除用户缓存
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	@Transactional(readOnly = false)
	public int deleteUserInfo(User user) {
		user = userDao.getInfoById(user.getId());
		int num = 0;
		/**1.删除org_staff**/
		orgStaffDao.removeByUserId(user.getId());

		/**2.删除sys_user**/
		num = userDao.delete(user);

		/**3.删除sys_login_name**/
		loginNameDao.removeByUserId(user.getId());
		/**4.删除person_info**/
		personInfoDao.delete(new PersonInfo(user.getPersionId()));

		// 清除用户缓存
		UserUtils.clearCache(user);
//		// 清除权限缓存

		return num;
	}

	@Transactional(readOnly = false)
	public int deleteUser(User user) {
		// 清除用户缓存
		UserUtils.clearCache(user);
//		// 清除权限缓存

		return userDao.delete(user);
	}

	
	@Transactional(readOnly = false)
	public int updatePasswordById(String id, String loginName, String newPassword) {
		int num = 0;
		User user = new User(id);
		user.setPassword(entryptPassword(newPassword));
		num = userDao.updatePasswordById(user);
		// 清除用户缓存
		user.setCurrentLogin(loginName);
		UserUtils.clearCache(user);
		return num;
	}

	/**
	 * 重置密码
	 * @param id
	 * @param newPass
	 * @return
	 * @author CTQ CREATE AT 2017-02-21 09:40:10
	 */
	@Transactional(readOnly = false)
	public int resetPass(String id,String newPass){
		int num = 0;
		User user = new User(id);
		user.setPassword(entryptPassword(newPass));
		num = userDao.updatePasswordById(user);
		UserUtils.clearCache(user);
		return num;
	}
	
	@Transactional(readOnly = false)
	public void updateUserLoginInfo(User user) {
		/*// 保存上次登录信息
		user.setOldLoginIp(user.getLoginIp());
		user.setOldLoginDate(user.getLoginDate());
		// 更新本次登录信息
		user.setLoginIp(StringUtils.getRemoteAddr(Servlets.getRequest()));
		user.setLoginDate(new Date());*/
		userDao.updateLoginInfo(user);
	}

	public User findUserByPersonId(String personId){
		return userDao.findUserByPersonId(personId);
	}
	
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}

    public static void main(String[] args) {
        String plain = Encodes.unescapeHtml("1");
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        System.out.print(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
    }

    /**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 获得活动会话
	 * @return
	 */
	public Collection<Session> getActiveSessions(){
		return sessionDao.getActiveSessions(false);
	}
	
	//-- Role Service --//
	
	/*public Role getRole(String id) {
		return roleDao.get(id);
	}

	public Role getRoleByName(String name) {
		Role r = new Role();
		r.setName(name);
		return roleDao.getByName(r);
	}
	
	public Role getRoleByEnname(String enname) {
		Role r = new Role();
		r.setEnname(enname);
		return roleDao.getByEnname(r);
	}
	
	public List<Role> findRole(Role role){
		return roleDao.findList(role);
	}
	
	public List<Role> findAllRole(){
		return UserUtils.getRoleList();
	}
	
	@Transactional(readOnly = false)
	public void saveRole(Role role) {
		if (StringUtils.isBlank(role.getId())){
			role.preInsert();
			roleDao.insert(role);
			// 同步到Activiti
		}else{
			role.preUpdate();
			roleDao.update(role);
		}
		// 更新角色与菜单关联
		roleDao.deleteRoleMenu(role);
		if (role.getMenuList().size() > 0){
			roleDao.insertRoleMenu(role);
		}
		// 更新角色与部门关联
		roleDao.deleteRoleOffice(role);
		if (role.getOfficeList().size() > 0){
			roleDao.insertRoleOffice(role);
		}
		// 同步到Activiti
		// 清除用户角色缓存
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional(readOnly = false)
	public void deleteRole(Role role) {
		roleDao.delete(role);
		// 同步到Activiti
		// 清除用户角色缓存
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	@Transactional(readOnly = false)
	public Boolean outUserInRole(Role role, User user) {
		*//*List<Role> roles = user.getRoleList();
		for (Role e : roles){
			if (e.getId().equals(role.getId())){
				roles.remove(e);
				saveUser(user);
				return true;
			}
		}*//*
		return false;
	}
	
	@Transactional(readOnly = false)
	public User assignUserToRole(Role role, User user) {
		if (user == null){
			return null;
		}
		*//*List<String> roleIds = user.getRoleIdList();
		if (roleIds.contains(role.getId())) {
			return null;
		}
		user.getRoleList().add(role);*//*
		saveUser(user);
		return user;
	}*/




	/**
	 * 获取Key加载信息
	 */
	public static boolean printKeyLoadMessage(){
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    欢迎使用阳元科技开发平台 - Powered By http://yy.com\r\n");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());

		return true;
	}
	
	///////////////// Synchronized to the Activiti //////////////////
	
	// 已废弃，同步见：ActGroupEntityServiceFactory.java、ActUserEntityServiceFactory.java


	
}
