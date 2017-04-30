<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
        $("#contentTable").treeTable({expandLevel : 3});
</script>
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysMenuDict/index')">菜单字典表列表</a>
                </li>
                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysMenuDict/form')">菜单字典表添加</a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <div class="datatable-top">
                            <div class="pull-left">
                            	<form:form id="searchForm" modelAttribute="sysMenuDict"  onsubmit="return loadDivForm('mainCenterDiv','searchForm','${ctx}/sys/sysMenuDict/index');" method="post" class="form-inline">
                                    &nbsp;&nbsp;<label>菜单名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="form-control" />
                                    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                                </form:form>
                            </div>
                        </div>
                        <table id="contentTable" class="table table-bordered mg-t datatable">
                            <thead>
                                <tr>

                                        <th >菜单名称</th>
                                        <th >菜单连接</th>
                                        <th >打开方式</th>
                                        <th >菜单图标路径</th>
                                        <th >是否显示</th>
                                        <th >菜单排序</th>
                                        <th >权限标识</th>
                                        <th >操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${treeList}" var="sysMenuDict">
                            <c:choose>
                            <c:when test="${!empty sysMenuDict.parent.id}">
                            <tr id='${sysMenuDict.id}' pId='${sysMenuDict.parent.id}'>
                                </c:when>
                                <c:otherwise>
                            <tr id='${sysMenuDict.id}'>
                                </c:otherwise>
                                    </c:choose>
                                    <td > ${sysMenuDict.name}</td>
                                    <td > ${sysMenuDict.href}</td>
                                    <td > ${sysMenuDict.target}</td>
                                    <td > ${sysMenuDict.icon}</td>
                                    <td > ${sysMenuDict.isShow}</td>
                                    <td > ${sysMenuDict.sort}</td>
                                    <td > ${sysMenuDict.permission}</td>
                                    <td>
                                        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysMenuDict/form?id=${sysMenuDict.id}')" class="btn btn-success btn-xs">修改</a>
                                        <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该菜单字典表吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/sysMenuDict/delete?id=${sysMenuDict.id}','${ctx}/sys/sysMenuDict/index')})">删除</a>
                                    </td>

                            </c:forEach>

                        </table>
                    </div>
                </div>
            </section>
        </div>
    </div>