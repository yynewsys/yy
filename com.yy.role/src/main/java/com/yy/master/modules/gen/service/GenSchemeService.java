/**
 * jims.
 */
package com.yy.master.modules.gen.service;

import java.util.List;
import java.util.Map;

import com.yy.master.common.service.BaseService;
import com.yy.master.common.utils.StringUtils;
import com.yy.master.modules.gen.entity.GenConfig;
import com.yy.master.modules.gen.util.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.master.common.persistence.Page;
import com.yy.master.modules.gen.dao.GenSchemeDao;
import com.yy.master.modules.gen.dao.GenTableColumnDao;
import com.yy.master.modules.gen.dao.GenTableDao;
import com.yy.master.modules.gen.entity.GenScheme;
import com.yy.master.modules.gen.entity.GenTable;
import com.yy.master.modules.gen.entity.GenTableColumn;
import com.yy.master.modules.gen.entity.GenTemplate;

/**
 * 生成方案Service
 * @author ThinkGem
 * @version 2013-10-15
 */
@Service
@Transactional(readOnly = true)
public class GenSchemeService extends BaseService {

	@Autowired
	private GenSchemeDao genSchemeDao;
//	@Autowired
//	private GenTemplateDao genTemplateDao;
	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	
	public GenScheme get(String id) {
		return genSchemeDao.get(id);
	}
	
	public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme) {
		GenUtils.getTemplatePath();
		genScheme.setPage(page);
		page.setList(genSchemeDao.findList(genScheme));
		return page;
	}
	
	@Transactional(readOnly = false)
	public int save(GenScheme genScheme) {
        int i=0;
		if (StringUtils.isBlank(genScheme.getId())){
			genScheme.preInsert();
            i=genSchemeDao.insert(genScheme);
		}else{
			genScheme.preUpdate();
            i=genSchemeDao.update(genScheme);
		}
		// 生成代码
		if ("1".equals(genScheme.getFlag())){
			return generateCode(genScheme);
		}
		return i;
	}
	
	@Transactional(readOnly = false)
	public int delete(GenScheme genScheme) {
		return  genSchemeDao.delete(genScheme);
	}
	
	private int generateCode(GenScheme genScheme){

		StringBuilder result = new StringBuilder();
		
		// 查询主表及字段列
		GenTable genTable = genTableDao.get(genScheme.getGenTable().getId());
		genTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(new GenTable(genTable.getId()))));
		
		// 获取所有代码模板
		GenConfig config = GenUtils.getConfig();
		
		// 获取模板列表
		List<GenTemplate> templateList = GenUtils.getTemplateList(config, genScheme.getCategory(), false);
		List<GenTemplate> childTableTemplateList = GenUtils.getTemplateList(config, genScheme.getCategory(), true);
		
		// 如果有子表模板，则需要获取子表列表
		if (childTableTemplateList.size() > 0){
			GenTable parentTable = new GenTable();
			parentTable.setParentTable(genTable.getName());
			genTable.setChildList(genTableDao.findList(parentTable));
		}
		
		// 生成子表模板代码
		for (GenTable childTable : genTable.getChildList()){
			childTable.setParent(genTable);
			childTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(new GenTable(childTable.getId()))));
			genScheme.setGenTable(childTable);
			Map<String, Object> childTableModel = GenUtils.getDataModel(genScheme);
			for (GenTemplate tpl : childTableTemplateList){
				result.append(GenUtils.generateToFile(tpl, childTableModel, genScheme.getReplaceFile()));
			}
		}
		
		// 生成主表模板代码
		genScheme.setGenTable(genTable);
		Map<String, Object> model = GenUtils.getDataModel(genScheme);
		for (GenTemplate tpl : templateList){
			result.append(GenUtils.generateToFile(tpl, model, genScheme.getReplaceFile()));
		}
		return 1;
	}
}
