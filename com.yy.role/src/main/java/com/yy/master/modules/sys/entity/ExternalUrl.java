package com.yy.master.modules.sys.entity;

import com.yy.master.common.persistence.DataEntity;

/**
 * Created by DT on 2017/2/23.
 * 外部链接Entity
 */
public class ExternalUrl extends DataEntity<ExternalUrl> {
    private static final long serialVersionUID = 1L;
    private String url;    // 链接地址
    private String name;    // 链接名称
    private String type;    // 链接类型
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
