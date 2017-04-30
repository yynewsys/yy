<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
    function page(n,s){
        if(n) $("#pageNo").val(n);
        if(s) $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }
</script>
<div class="content-wrap">
    <div class="wrapper" style="bottom: 50px;">
        <ul class="nav nav-tabs">
            <li class="active">
                <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/gen/genTable/index')">业务表列表</a>
            </li>
            <li>
                <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/gen/genTable/form')">业务表添加</a>
            </li>
            <li>
                <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/gen/genScheme/index')">方案列表</a>
            </li>
            <li>
                <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/gen/genScheme/form')">方案添加</a>
            </li>

        </ul>
        <section class="panel panel-default">
            <div class="panel-body">
                <div class="table-responsive no-border">
                    <div class="datatable-top">
                        <div class="pull-left">
                            <form:form id="searchFormDict" modelAttribute="genTable" onsubmit="return loadDivForm('mainCenterDiv','searchFormDict','${ctx}/gen/genTable/index');" method="post" class="form-inline">
                                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                <label>表名：</label><form:input path="nameLike" htmlEscape="false" maxlength="50" class="form-control"/>
                                <label>说明：</label><form:input path="comments" htmlEscape="false" maxlength="50" class="form-control"/>
                                <label>父表表名：</label><form:input path="parentTable" htmlEscape="false" maxlength="50" class="form-control"/>
                                &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                            </form:form>
                        </div>
                    </div>


                    <table id="contentTable" class="table table-bordered mg-t datatable">
                        <thead><tr><th class="sort-column name">表名</th><th>说明</th><th class="sort-column class_name">类名</th><th class="sort-column parent_table">父表</th><shiro:hasPermission name="gen:genTable:edit"><th>操作</th></shiro:hasPermission></tr></thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="genTable">
                            <tr>
                                <td><a href="${ctx}/gen/genTable/form?id=${genTable.id}">${genTable.name}</a></td>
                                <td>${genTable.comments}</td>
                                <td>${genTable.className}</td>
                                <td title="点击查询子表"><a href="javascript:" onclick="$('#parentTable').val('${genTable.parentTable}');$('#searchForm').submit();">${genTable.parentTable}</a></td>
                                <td>
                                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/gen/genTable/form?id=${genTable.id}')" class="btn btn-success btn-xs">修改</a>
                                    <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该业务表吗？',function(){delLoad('mainCenterDiv','${ctx}/gen/genTable/delete?id=${genTable.id}','${ctx}/gen/genTable/index')})">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination">${page}</div>
                </div>
            </div>
        </section>
    </div>
</div>