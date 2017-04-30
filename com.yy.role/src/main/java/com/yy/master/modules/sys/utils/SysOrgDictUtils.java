/**
 * jims.
 */
package com.yy.master.modules.sys.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.master.common.mapper.JsonMapper;
import com.yy.master.common.utils.CacheUtils;
import com.yy.master.common.utils.SpringContextHolder;
import com.yy.master.modules.sys.dao.DictDao;
import com.yy.master.modules.sys.dao.SysOrgDictDao;
import com.yy.master.modules.sys.entity.Dict;
import com.yy.master.modules.sys.entity.SysOrgDict;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 平台字典工具类
 * @author CTQ
 * @version 2017-03-18 14:21:02
 */
public class SysOrgDictUtils {
	
	private static SysOrgDictDao dictDao = SpringContextHolder.getBean(SysOrgDictDao.class);

	public static final String CACHE_SYS_DICT_MAP = "sysDictMap";

	public static String getSysDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (SysOrgDict dict : getSysDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getId())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}


	public static String getSysDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (SysOrgDict dict : getSysDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<SysOrgDict> getSysDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<SysOrgDict>> dictMap = (Map<String, List<SysOrgDict>>) CacheUtils.get(CACHE_SYS_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (SysOrgDict dict : dictDao.findAllList(new SysOrgDict())){
				List<SysOrgDict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_SYS_DICT_MAP, dictMap);
		}
		List<SysOrgDict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getSysDictList(type));
	}
	
}
