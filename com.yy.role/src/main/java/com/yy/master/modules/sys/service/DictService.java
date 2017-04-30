/**
 * jims.
 */
package com.yy.master.modules.sys.service;

import java.util.List;

import com.yy.master.common.utils.CacheUtils;
import com.yy.master.common.utils.ChineseCharToEnUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.dao.DictDao;
import com.yy.master.modules.sys.entity.Dict;
import com.yy.master.modules.sys.utils.DictUtils;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public int save(Dict dict) {
		int i=super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
        return i;
	}

	@Transactional(readOnly = false)
	public int delete(Dict dict) {
		int i=super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
        return i;
	}

}
