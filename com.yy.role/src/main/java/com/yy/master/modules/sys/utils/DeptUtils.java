package com.yy.master.modules.sys.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.utils.CacheUtils;
import com.yy.master.common.utils.SpringContextHolder;
import com.yy.master.modules.sys.dao.OrgDeptDao;
import com.yy.master.modules.sys.entity.OrgDept;
import com.yy.master.modules.sys.service.OrgDeptService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/2/16.
 * 科室
 */
public class DeptUtils {

    private static OrgDeptDao orgDeptDao = SpringContextHolder.getBean(OrgDeptDao.class);
    private static OrgDeptService deptService = SpringContextHolder.getBean(OrgDeptService.class);

    public static final String CACHE_DEPT_MAP = "deptMap";

    public static String getDeptName(String value, String defaultValue){
        if (StringUtils.isNotBlank(value) && StringUtils.isNotBlank(value)){
            for (OrgDept dict : getDeptList(value)){
                if (value.equals(dict.getId()) && value.equals(dict.getId())){
                    return dict.getDeptName();
                }
            }
        }
        return defaultValue;
    }

    public static List<OrgDept> getDeptList(String value){
        @SuppressWarnings("unchecked")
        Map<String, List<OrgDept>> dictMap = (Map<String, List<OrgDept>>) CacheUtils.get(CACHE_DEPT_MAP);
        if (dictMap==null){
            dictMap = Maps.newHashMap();
            for (OrgDept dict : orgDeptDao.findAllList(new OrgDept())){
                List<OrgDept> dictList = dictMap.get(dict.getId());
                if (dictList != null){
                    dictList.add(dict);
                }else{
                    dictMap.put(dict.getId(), Lists.newArrayList(dict));
                }
            }
            CacheUtils.put(CACHE_DEPT_MAP, dictMap);
        }
        List<OrgDept> dictList = dictMap.get(value);
        if (dictList == null){
            dictList = Lists.newArrayList();
        }
        return dictList;
    }

    /**
     * 获取所有下级科室
     * @return
     * @author fengyg
     */
    public static List<OrgDept> getLowLevelDeptList() {
        return deptService.getLowLevelDeptList();
    }
}
