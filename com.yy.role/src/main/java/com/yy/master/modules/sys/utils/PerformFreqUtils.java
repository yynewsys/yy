package com.yy.master.modules.sys.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.utils.CacheUtils;
import com.yy.master.common.utils.SpringContextHolder;
import com.yy.master.modules.sys.dao.CompanyDao;
import com.yy.master.modules.sys.dao.PerformFreqDictDao;
import com.yy.master.modules.sys.entity.Company;
import com.yy.master.modules.sys.entity.PerformFreqDict;

import java.util.List;
import java.util.Map;

/**
 * Created by CTQ on 2017-03-17 09:20:27.
 */
public class PerformFreqUtils {


    private static final String CACHE_FREQ_DICT_MAP ="perform_freq_dict";
    private static PerformFreqDictDao performFreqDictDao = SpringContextHolder.getBean(PerformFreqDictDao.class);


    public static List<PerformFreqDict> findFreqList(){
       Map<String, List<PerformFreqDict>> dictMap = (Map<String, List<PerformFreqDict>>) CacheUtils.get(CACHE_FREQ_DICT_MAP);
       if (dictMap==null){
           dictMap = Maps.newHashMap();
           for (PerformFreqDict dict : performFreqDictDao.findAllList(new PerformFreqDict())){
               List<PerformFreqDict> dictList = dictMap.get(CACHE_FREQ_DICT_MAP);
               if (dictList != null){
                   dictList.add(dict);
               }else{
                   dictMap.put(CACHE_FREQ_DICT_MAP, Lists.newArrayList(dict));
               }
           }
           CacheUtils.put(CACHE_FREQ_DICT_MAP, dictMap);
       }
       List<PerformFreqDict> dictList = dictMap.get(CACHE_FREQ_DICT_MAP);
       if (dictList == null){
           dictList = Lists.newArrayList();
       }
       return dictList;
    }

    /**
     * 根据id查询频次
     * @param id
     * @return
     */
    public static String findFreqDesc(String id,String defaultValue){
        PerformFreqDict dict = performFreqDictDao.get(id);
        if (dict!=null){
            return dict.getFreqDesc();
        }
       return defaultValue;
    }



}
