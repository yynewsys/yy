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
            <li>
                <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/gen/genTable/index')">业务表列表</a>
            </li>
            <li>
                <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/gen/genTable/form')">业务表添加</a>
            </li>
            <li class="active">
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
                            <form:form id="searchFormDict" modelAttribute="genScheme" onsubmit="return loadDivForm('mainCenterDiv','searchFormDict','${ctx}/gen/genScheme/index');" method="post" class="form-inline">
                                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                <label>方案名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="form-control"/>
                                &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                            </form:form>
                        </div>
                    </div>

                    <table id="contentTable" class="table table-bordered mg-t datatable">
                        <thead><tr><th>方案名称</th><th>生成模块</th><th>模块名</th><th>功能名</th><th>功能作者</th><th>操作</th></tr></thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="genScheme">
                            <tr>
                                <td><a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/gen/genScheme/form?id=${genScheme.id}')" >${genScheme.name}</a></td>
                                <td>${genScheme.packageName}</td>
                                <td>${genScheme.moduleName}${not empty genScheme.subModuleName?'.':''}${genScheme.subModuleName}</td>
                                <td>${genScheme.functionName}</td>
                                <td>${genScheme.functionAuthor}</td>
                                <td>
                                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/gen/genScheme/form?id=${genScheme.id}')" class="btn btn-success btn-xs">修改</a>
                                    <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该生成方案吗？',function(){delLoad('mainCenterDiv','${ctx}/gen/genScheme/delete?id=${genScheme.id}','${ctx}/gen/genScheme/index')})">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        </tbody>
                    </table>
                    <div class="pagination">${page}</div>
                </div>
            </div>
        </section>
    </div>
</div>