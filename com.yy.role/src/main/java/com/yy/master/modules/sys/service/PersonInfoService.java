/**
 * jims
 */
package com.yy.master.modules.sys.service;

import com.yy.master.common.persistence.BaseEntity;
import com.yy.master.common.service.CrudService;
import com.yy.master.common.utils.ChineseCharToEnUtils;
import com.yy.master.common.utils.IdcardInfoExtractor;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.modules.sys.dao.PersonInfoDao;
import com.yy.master.modules.sys.dao.SysLoginNameDao;
import com.yy.master.modules.sys.entity.PersonInfo;
import com.yy.master.modules.sys.entity.SysLoginName;
import com.yy.master.modules.sys.entity.User;
import com.yy.master.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 人员基本信息Service
 * @author CTQ
 * @version 2017-02-17
 */
@Service
@Transactional(readOnly = true)
public class PersonInfoService extends CrudService<PersonInfoDao, PersonInfo> {
    @Autowired
    private PersonInfoDao personInfoDao;
    @Autowired
    private SysLoginNameDao loginNameDao;

    /**
     * 修改个人信息
     * @param personInfo
     * @return
     * @author CTQ
     * @create_date 2017-02-21 13:51:11
     */
    @Transactional(readOnly = false)
    public int savePersonInfo(PersonInfo personInfo){
        User user = UserUtils.getUser();
        if(personInfo.getIdCard()!=null&&!personInfo.getIdCard().equals("")){
            IdcardInfoExtractor idcardInfo=new IdcardInfoExtractor(personInfo.getIdCard());
            personInfo.setSex(idcardInfo.getGender());
        }
        personInfo.setInputCode(ChineseCharToEnUtils.getAllFirstLetter(user.getName()));

        SysLoginName loginName = new SysLoginName();
        loginNameDao.delByUserId(user.getId());
        loginName.setUser(user);
        /**保存用户登录用户名信息**/
        if(StringUtils.isNotBlank(personInfo.getPhone())){
            loginName.setLoginName(personInfo.getPhone());
            loginName.setType(BaseEntity.USER_TYPE_PHONE);
            loginName.preInsert();
            loginNameDao.insert(loginName);
        }
        if(StringUtils.isNotBlank(personInfo.getEmail())){
            loginName.setLoginName(personInfo.getEmail());
            loginName.setType(BaseEntity.USER_TYPE_EMAIL);
            loginName.preInsert();
            loginNameDao.insert(loginName);
        }
        if(StringUtils.isNotBlank(personInfo.getIdCard())){
            loginName.setLoginName(personInfo.getIdCard());
            loginName.setType(BaseEntity.USER_TYPE_IDCARD);
            loginName.preInsert();
            loginNameDao.insert(loginName);
        }
        personInfo.preUpdate();
        UserUtils.clearCache(user);
        return  personInfoDao.update(personInfo);
    }

	
}