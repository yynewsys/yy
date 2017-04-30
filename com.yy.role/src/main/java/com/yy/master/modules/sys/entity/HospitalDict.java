package com.yy.master.modules.sys.entity;

import com.yy.master.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by 99386 on 2017/2/21.
 */
public class HospitalDict extends DataEntity<HospitalDict> {
    private static final long serialVersionUID = 1L;
    private String value;	// 数据值
    private String label;	// 标签名
    private String type;	// 类型
    private String description;// 描述
    private  String input_code; //拼音码
    private Integer sort;	// 排序
    private String parentId;//父Id


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @XmlAttribute
    @Length(min=1, max=100)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    @XmlAttribute
    @Length(min=1, max=100)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    @Length(min=1, max=100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInput_code() {
        return input_code;
    }

    public void setInput_code(String input_code) {
        this.input_code = input_code;
    }
    @XmlAttribute
    @Length(min=0, max=100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @NotNull
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    @Length(min=1, max=100)
    public String getParentId() {
        return parentId;
    }
    @Override
    public String toString() {
        return label;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public HospitalDict() {super();
    }

    public HospitalDict(String id) {
        super(id);
    }

    public HospitalDict(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
