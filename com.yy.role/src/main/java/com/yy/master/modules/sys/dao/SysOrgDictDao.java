/**
 * jims
 */
package com.yy.master.modules.sys.dao;

import com.yy.master.common.persistence.CrudDao;
import com.yy.master.common.persistence.annotation.MyBatisDao;
import com.yy.master.common.web.impl.BaseDto;
import com.yy.master.modules.sys.entity.SysOrgDict;
import com.yy.master.modules.sys.vo.SysOrgDictVSClinicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统平台字典DAO接口
 * @author CTQ
 * @version 2017-03-18
 */
@MyBatisDao
public interface SysOrgDictDao extends CrudDao<SysOrgDict> {
    /**
     * 查询字段类型列表
     * @return
     * @author CTQ
     */
    public List<String> findTypeList(SysOrgDict dict);

    /**
     * 根据字典ID查询对照的项目信息
     * @param dictId
     * @param orgId
     * @return
     * @author CTQ
     */
    public List<BaseDto> findVsListById(@Param("dictId") String dictId,@Param("orgId") String orgId);

    /**
     * 根据类型查询系统字典
     * @param type
     * @return
     * @author fengyg
     */
    public List<SysOrgDict> getListByType(@Param("type")String type);

    /**
     * 根据label获取字典
     * @param type
     * @return
     * @author
     */
    public SysOrgDict getSysOrgById(@Param("type")String type,@Param("label")String label);
    /**
     * 查询配血划价项目对照
     * @param sysOrgDict
     * @return
     * @author CTQ
     */
    public List<SysOrgDictVSClinicVo> findBloodMatchList(SysOrgDict sysOrgDict);
}