package com.yy.master.modules.sys.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.utils.CacheUtils;
import com.yy.master.common.utils.SpringContextHolder;
import com.yy.master.modules.sys.dao.CompanyDao;
import com.yy.master.modules.sys.dao.DictDao;
import com.yy.master.modules.sys.entity.Company;
import com.yy.master.modules.sys.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/2/16.
 */
public class CompanyUtils {


    private static CompanyDao companyDao = SpringContextHolder.getBean(CompanyDao.class);
    public static final String CACHE_DICT_MAP = "companyMap";


   public static List<Company> getCompanyList(){
        return companyDao.findList(new Company());
   }

}
