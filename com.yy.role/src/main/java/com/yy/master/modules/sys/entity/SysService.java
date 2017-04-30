package com.yy.master.modules.sys.entity;

import com.yy.master.common.persistence.DataEntity;

/**
 * Created by Administrator on 2017-02-16.
 * 服务 entity
 * @Author 赵宁
 */
public class SysService extends DataEntity<SysService> {

    private static final long serialVersionUID = 1L;
    private String serverName;//服务名
    private String serverType;//服务类型
    private String serverClass;//服务类别
    private String serviceImage;//服务图片路径
    private String description;//服务描述
    private String serviceId;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public SysService() {
        super();
    }

    public SysService(String id){
        super(id);
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getServerClass() {
        return serverClass;
    }

    public void setServerClass(String serverClass) {
        this.serverClass = serverClass;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
