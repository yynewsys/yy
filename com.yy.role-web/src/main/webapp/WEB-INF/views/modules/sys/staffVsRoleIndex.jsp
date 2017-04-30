<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- content wrapper -->
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/MiddleList')">人员已分配角色列表</a>
                </li>

                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/middleListForm')">人员已分配角色</a>
                </li>

            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <%--<div class="datatable-top">--%>
                            <%--<div class="pull-left">--%>
                                <%--<form:form id="searchFormDict" modelAttribute="company" onsubmit="return loadDivForm('mainCenterDiv','searchFormDict','${ctx}/sys/company/index');" method="post" class="form-inline">--%>
                                    <%--<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>--%>
                                    <%--<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>--%>
                                     <%--&nbsp;&nbsp;<label>机构名称 ：</label><form:input path="orgName" htmlEscape="false" maxlength="50" class="form-control" />--%>
                                    <%--&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>--%>
                                <%--</form:form>--%>
                            <%--</div>--%>
                        <%--</div>--%>


                        <table id="contentTable" class="table table-bordered mg-t datatable">
                            <thead><tr><th>人员姓名</th><th>所在科室</th><th>角色名称</th><th>操作</th></tr></thead>
                            <tbody>
                             <c:forEach items="${page.list}" var="staffVsRole" varStatus="status">
                                <%--&lt;%&ndash;子节点&ndash;%&gt;--%>
                                     <tr>
                                        <td>${staffVsRole.staffName}</td>
                                        <td>${staffVsRole.deptName}</td>
                                        <td>${staffVsRole.roleName}</td>
                                        <td>
                                            <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/middleListForm?staffId=${staffVsRole.staffId}')" class="btn btn-success btn-xs">修改</a>
                                            <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该组织机构吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/company/delete?id=${company.id}','${ctx}/sys/company/index')})">删除</a>
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
<script>
    function page(n,s){
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchFormDict").submit();
        return false;
    }
    $("#contentTable").treeTable({expandLevel : 3});
</script>