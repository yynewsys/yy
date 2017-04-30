package com.yy.master.modules.sys.vo;

import com.yy.master.modules.sys.entity.OrgDept;

/**
 * Created by CTQ on 2017-04-11.
 */
public class SysOrgDictVSClinicVo {
    private String id;
    private String label;		// 标签
    private String type;		// 类型
    private String value;		// 值

    private String itemId;
    private String itemCode;
    private String itemClass;
    private String itemName;

    private OrgDept executiveDept;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public OrgDept getExecutiveDept() {
        return executiveDept;
    }

    public void setExecutiveDept(OrgDept executiveDept) {
        this.executiveDept = executiveDept;
    }
}
