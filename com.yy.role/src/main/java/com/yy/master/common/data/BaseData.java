package com.yy.master.common.data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class BaseData<T> implements Serializable {


    private T data;  //实体

    private List<T> datas;  //返回对象list

    private List<T> datas1;  //返回对象list

    private List<T> datas2;  //返回对象list

    private List<T> datas3;  //返回对象list

    private String code;   //返回状态

    private Map<String,T> dataMap; //返回参数

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public List<T> getDatas1() {
        return datas1;
    }

    public void setDatas1(List<T> datas1) {
        this.datas1 = datas1;
    }

    public List<T> getDatas2() {
        return datas2;
    }

    public void setDatas2(List<T> datas2) {
        this.datas2 = datas2;
    }

    public List<T> getDatas3() {
        return datas3;
    }

    public void setDatas3(List<T> datas3) {
        this.datas3 = datas3;
    }

    public Map<String, T> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, T> dataMap) {
        this.dataMap = dataMap;
    }
}
