package com.yy.master.modules.sys.utils;

import com.yy.master.modules.sys.entity.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/2/16.
 */
public class CompanyTreeTable {

    private List<Company> resultNodes = new ArrayList<Company>();//树形结构排序之后list内容
    private List<Company> nodes; //传入list参数
    public CompanyTreeTable(List<Company> nodes) {//通过构造函数初始化
        this.nodes = nodes;
    }

    /**
     * 构建树形结构list
     * @return 返回树形结构List列表
     */
    public List<Company> buildTree() {
        for (Company node : nodes) {
             if (node.getParentId() == null || "".equals(node.getParentId())) {//通过循环一级节点 就可以通过递归获取二级以下节点
                resultNodes.add(node);//添加一级节点
                build(node);//递归获取二级、三级、。。。节点
            }
        }
        return resultNodes;
    }
    /**
     * 递归循环子节点
     *
     * @param node 当前节点
     */
    private void build(Company node) {
        List<Company> children = getChildren(node);
        if (!children.isEmpty()) {//如果存在子节点
            for (Company child : children) {//将子节点遍历加入返回值中
                resultNodes.add(child);
                build(child);
            }
        }
    }
    /**
     * @param node
     * @return 返回
     */
    private List<Company> getChildren(Company node) {
        List<Company> children = new ArrayList<Company>();
        String id = node.getId();
        for (Company child : nodes) {
            if (id.equals(child.getParentId())) {//如果id等于父id
                children.add(child);//将该节点加入循环列表中
            }
        }
        return children;
    }



}




