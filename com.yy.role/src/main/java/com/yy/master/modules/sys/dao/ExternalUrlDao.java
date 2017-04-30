package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.ExternalUrl;

/**
 * Created by DT on 2017/2/23.
 * 外部链接DAO接口
 */
@MyBatisDao
public interface ExternalUrlDao extends CrudDao<ExternalUrl> {
}
