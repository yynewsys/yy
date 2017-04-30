package com.yy.master.modules.sys.service;

import com.yy.master.common.service.CrudService;
import com.yy.master.common.utils.CacheUtils;
import com.yy.master.modules.sys.dao.HospitalDictDao;
import com.yy.master.modules.sys.entity.HospitalDict;
import com.yy.master.modules.sys.utils.DictUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 99386 on 2017/2/21.
 */
@Service
@Transactional(readOnly = true)
public class HospitalDictService extends CrudService<HospitalDictDao, HospitalDict> {
    /**
     * 查询字段类型列表
     * @return
     */
    public List<String> findTypeList(){
        return dao.findTypeList(new HospitalDict());
    }

    @Transactional(readOnly = false)
    public int save(HospitalDict hospitaldict) {
        int i=super.save(hospitaldict);
//        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
        return i;
    }

    @Transactional(readOnly = false)
    public int delete(HospitalDict hospitaldict) {
        hospitaldict.updateOrgId();
        int i=super.delete(hospitaldict);
//        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
        return i;
    }

}
