package com.yy.master.modules.sys.utils;

import com.yy.master.modules.sys.entity.SysMenuDict;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/2/16.
 * 菜单管理 父子结构
 * @author 赵宁
 */
public class MenuTreeTable {

    private List<SysMenuDict> resultNodes = new ArrayList<SysMenuDict>();//树形结构排序之后list内容
    private List<SysMenuDict> nodes; //传入list参数
    public MenuTreeTable(List<SysMenuDict> nodes) {//通过构造函数初始化
        this.nodes = nodes;
    }

    /**
     * 构建树形结构list
     * @return 返回树形结构List列表
     */
    public List<SysMenuDict> buildTree() {
        for (SysMenuDict node : nodes) {
            if (node.getParent().getId() == null || "0".equals(node.getParent().getId())) {//通过循环一级节点 就可以通过递归获取二级以下节点
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
    private void build(SysMenuDict node) {
        List<SysMenuDict> children = getChildren(node);
        if (!children.isEmpty()) {//如果存在子节点
            for (SysMenuDict child : children) {//将子节点遍历加入返回值中
                resultNodes.add(child);
                build(child);
            }
        }
    }
    /**
     * @param node
     * @return 返回
     */
    private List<SysMenuDict> getChildren(SysMenuDict node) {
        List<SysMenuDict> children = new ArrayList<SysMenuDict>();
        String id = node.getId();
        for (SysMenuDict child : nodes) {
            if (id.equals(child.getParent().getId())) {//如果id等于父id
                children.add(child);//将该节点加入循环列表中
            }
        }
        return children;
    }


}




