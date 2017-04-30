package com.yy.master.modules.sys.service;

import com.yy.master.common.persistence.Page;
import com.yy.master.common.service.CrudService;
import com.yy.master.modules.sys.dao.ExternalUrlDao;
import com.yy.master.modules.sys.entity.ExternalUrl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by DT on 2017/2/23.
 * 外部链接Service
 */
@Service
@Transactional(readOnly = true)
public class ExternalUrlService extends CrudService<ExternalUrlDao,ExternalUrl> {
    @Override
    public Page<ExternalUrl> findPage(Page<ExternalUrl> page, ExternalUrl entity) {
        entity.updateOrgId();
        entity.setPage(page);
        List list=dao.findList(entity);
        page.setList(list);
        return page;
    }
}
