/**
 * jims.
 */
package com.yy.master.modules.sys.service;

import com.yy.master.common.service.CrudService;
import com.yy.master.common.utils.CacheUtils;
import com.yy.master.modules.sys.dao.CompanyDao;
import com.yy.master.modules.sys.entity.Company;
import com.yy.master.modules.sys.entity.SysService;
import com.yy.master.modules.sys.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional(readOnly = false)
public class CompanyService extends CrudService<CompanyDao, Company> {

    @Autowired
    private CompanyDao companyDao;


	public List<Company> findList(){
		return super.findList(new Company());
	}

	@Transactional(readOnly = false)
	public int save(Company company) {
		return super.save(company);
	}

	@Transactional(readOnly = false)
	public int delete(Company company) {
        return super.delete(company);
 	}

    /**
     *
     * @param company
     * @return
     */
    public List<SysService> findServiceByOrg(Company company) {
         return this.companyDao.findServiceByOrg(company);
    }
    @Transactional(readOnly = false)
    public int saveCompanyVsService(Company c) {
        return this.companyDao.saveCompanyVsService(c);
    }
    @Transactional(readOnly = false)
    public int deleteFromCompanyVsService(Company company) {
        return this.companyDao.deleteFromCompanyVsService(company);
    }

    @Transactional(readOnly = false)
    public int middleSaveService(String[] serviceIds,Company company) {
        int newNum=0;
        for (int i = 0; i < serviceIds.length; i++) {
            Company c=new Company();
            c.setCompanyId(company.getId());
            c.setServiceId(serviceIds[i]);
            c.preInsert();
            newNum += saveCompanyVsService(c);
        }
        return 0;
    }
}
