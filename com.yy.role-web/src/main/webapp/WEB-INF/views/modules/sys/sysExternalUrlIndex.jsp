
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<script>
    function page(n, s) {
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchFormDict").submit();
        return false;
    }
</script>
<div class="content-wrap">
    <div class="wrapper" style="bottom: 50px;">
        <ul class="nav nav-tabs">
            <li class="active">
                <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/externalUrl/index')">外部链接列表</a>
            </li>
            <li>
                <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/externalUrl/form')">外部链接维护</a>
            </li>
        </ul>
        <section class="panel panel-default">
            <div class="panel-body">
                <div class="table-responsive no-border">
                    <div class="datatable-top">
                        <div class="pull-left">
                            <form:form id="searchFormDict" modelAttribute="externalUrl" onsubmit="return loadDivForm('mainCenterDiv','searchFormDict','${ctx}/sys/externalUrl/index');" method="post" class="form-inline">
                                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                <label>链接名称：</label><form:input id="name" path="name" class="form-control" ></form:input>
                                &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                            </form:form>
                        </div>
                    </div>
                    <table id="contentTable" class="table table-bordered mg-t datatable">
                        <thead><tr><th>链接名称</th><th>链接地址</th><th>链接类型</th><th>备注</th></thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="link">
                        <tr>
                            <td>${link.name}</td>
                            <td>${link.url}</td>
                            <td>${link.type}</td>
                            <td>${link.remarks}</td>
                            <td>
                                <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/externalUrl/form?id=${link.id}')" class="btn btn-success btn-xs">修改</a>
                                <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该字典吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/externalUrl/delete?id=${link.id}','${ctx}/sys/externalUrl/index')})">删除</a>
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
