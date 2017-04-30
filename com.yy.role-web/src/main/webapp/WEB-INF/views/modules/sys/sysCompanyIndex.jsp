<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- content wrapper -->
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/company/index')">机构列表</a>
                </li>

                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/company/form')">机构添加</a>
                </li>

            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <div class="datatable-top">
                            <div class="pull-left">
                                <form:form id="searchFormDict" modelAttribute="company" onsubmit="return loadDivForm('mainCenterDiv','searchFormDict','${ctx}/sys/company/index');" method="post" class="form-inline">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                     &nbsp;&nbsp;<label>机构名称 ：</label><form:input path="orgName" htmlEscape="false" maxlength="50" class="form-control" />
                                    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                                </form:form>
                            </div>
                        </div>


                        <table id="contentTable" class="table table-bordered mg-t datatable">
                            <thead><tr><th>机构名称</th><th>地址</th><th>联系人</th><th>电话</th><th>申请状态</th><th>操作</th></tr></thead>
                            <tbody>
                             <c:forEach items="${page.list}" var="company" varStatus="status">
                                <%--&lt;%&ndash;子节点&ndash;%&gt;--%>
                                <c:if test="${!empty company.parentId}">
                                    <tr id='${company.id}' pId='${company.parentId}'>
                                        <td>${company.orgName}</td>
                                        <td>${company.address}</td>
                                        <td>${company.linkMan}</td>
                                        <td>${company.phone}</td>
                                        <td>${company.applyStatus}</td>
                                        <td>
                                            <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/company/assignService?id=${company.id}')" class="btn btn-success btn-xs">分配服务</a>
                                            <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/company/form?id=${company.id}')" class="btn btn-success btn-xs">修改</a>
                                            <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该组织机构吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/company/delete?id=${company.id}','${ctx}/sys/company/index')})">删除</a>
                                        </td>
                                    </tr>
                                </c:if>
                                <%--父节点--%>
                                <c:if test="${empty company.parentId}">
                                    <tr id='${company.id}'>
                                        <td>${company.orgName}</td>
                                        <td>${company.address}</td>
                                        <td>${company.linkMan}</td>
                                        <td>${company.phone}</td>
                                        <td>${company.applyStatus}</td>
                                        <td>
                                            <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/company/assignService?id=${company.id}')" class="btn btn-success btn-xs">分配服务</a>
                                            <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/company/form?id=${company.id}')" class="btn btn-success btn-xs">修改</a>
                                            <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该组织机构吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/company/delete?id=${company.id}','${ctx}/sys/company/index')})">删除</a>
                                        </td>
                                    </tr>
                                 </c:if>
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