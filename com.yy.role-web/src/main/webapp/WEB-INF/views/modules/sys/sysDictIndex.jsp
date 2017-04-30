<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script>
    function page(n,s){
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchFormDict").submit();
        return false;
    }
</script>
    <!-- content wrapper -->
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/dict/index')">字典列表</a>
                </li>

                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/dict/form')">字典添加</a>
                </li>

            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <div class="datatable-top">
                            <div class="pull-left">
                                <form:form id="searchFormDict" modelAttribute="dict" onsubmit="return loadDivForm('mainCenterDiv','searchFormDict','${ctx}/sys/dict/index');" method="post" class="form-inline">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <label>类型：</label><form:select id="type" path="type" class="form-control" ><form:option value="" label=""/><form:options items="${typeList}" htmlEscape="false"/></form:select>
                                    &nbsp;&nbsp;<label>描述 ：</label><form:input path="description" htmlEscape="false" maxlength="50" class="form-control" />
                                    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                                </form:form>
                            </div>
                        </div>


                        <table id="contentTable" class="table table-bordered mg-t datatable">
                            <thead><tr><th>键值</th><th>标签</th><th>类型</th><th>描述</th><th>排序</th><th>操作</th></tr></thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="dict">
                            <tr>
                                <td>${dict.value}</td>
                                <td><a href="${ctx}/sys/dict/form?id=${dict.id}">${dict.label}</a></td>
                                <td><a href="javascript:" onclick="$('#type').val('${dict.type}');$('#searchForm').submit();return false;">${dict.type}</a></td>
                                <td>${dict.description}</td>
                                <td>${dict.sort}</td>
                                <td>
                                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/dict/form?id=${dict.id}')" class="btn btn-success btn-xs">修改</a>
                                    <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该字典吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/dict/delete?id=${dict.id}&type=${dict.type}','${ctx}/sys/dict/index')})">删除</a>
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