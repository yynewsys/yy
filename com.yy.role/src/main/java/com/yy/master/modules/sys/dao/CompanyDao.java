/**
 * jims.
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.modules.sys.entity.Company;
import com.yy.master.modules.sys.entity.Dict;
import com.yy.master.modules.sys.entity.SysService;

import java.util.List;

/**
 *
 */
@MyBatisDao
public interface CompanyDao extends CrudDao<Company> {

    /**
     *
     * @param company
     * @return
     */
    public List<SysService> findServiceByOrg(Company company);


    public  int saveCompanyVsService(Company c);

    public int deleteFromCompanyVsService(Company company);
}
