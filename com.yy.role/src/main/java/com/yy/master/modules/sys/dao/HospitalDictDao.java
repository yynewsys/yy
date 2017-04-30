package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.HospitalDict;

import java.util.List;

/**
 * Created by 99386 on 2017/2/21.
 */
@MyBatisDao
public interface HospitalDictDao extends CrudDao<HospitalDict> {

    public List<String> findTypeList(HospitalDict hospitaldict);
}
