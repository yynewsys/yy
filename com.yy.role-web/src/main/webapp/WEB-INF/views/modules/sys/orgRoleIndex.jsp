<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
</script>
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/index')">角色列表</a>
                </li>
                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/form')">角色添加</a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <div class="datatable-top">
                            <div class="pull-left">
                            	<form:form id="searchFormRole" modelAttribute="orgRole" onsubmit="return loadDivForm('mainCenterDiv','searchFormRole','${ctx}/sys/orgRole/index');" method="post" class="form-inline">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <label>角色名称 ：</label><form:input path="roleName" htmlEscape="false" maxlength="50" class="form-control" /> &nbsp;
                                    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                                </form:form>
                            </div>
                        </div>
                        <table id="contentTable" class="table table-bordered mg-t datatable">
                            <thead>
                                <tr>
                                    <th>角色名称</th>
                                    <th>组织机构名称</th>
                                    <th>创建时间</th>
                                    <th>备注信息</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="orgRole">
                                <tr>
                                        <td> ${orgRole.roleName}</td>
                                        <td> ${orgRole.orgName}</td>
                                        <td><fmt:formatDate value="${orgRole.createDate}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
                                        <td> ${orgRole.remarks}</td>
                                    <td>
                                        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/assignServer?id=${orgRole.id}')" class="btn btn-success btn-xs">分配权限</a>
                                        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/assign?id=${orgRole.id}')" class="btn btn-success btn-xs">分配人员</a>
                                        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/form?id=${orgRole.id}')" class="btn btn-success btn-xs">修改</a>
                                        <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该组织机构角色信息吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/orgRole/delete?id=${orgRole.id}','${ctx}/sys/orgRole/index')})">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                        <div class="pagination">${page}</div>
                    </div>
                </div>
            </section>
        </div>
    </div>