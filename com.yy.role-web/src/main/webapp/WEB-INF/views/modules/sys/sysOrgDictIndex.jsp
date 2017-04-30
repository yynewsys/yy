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
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysOrgDict/index')">系统平台字典列表</a>
                </li>
                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysOrgDict/form')">系统平台字典添加</a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <div class="datatable-top">
                            <div class="pull-left">
                            	<form:form id="searchForm" modelAttribute="sysOrgDict"  onsubmit="return loadDivForm('mainCenterDiv','searchForm','${ctx}/sys/sysOrgDict/index');" method="post" class="form-inline">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <label>类型：</label><form:select id="type" path="type" class="form-control" ><form:option value="" label=""/><form:options items="${typeList}" htmlEscape="false"/></form:select>
                                    &nbsp;&nbsp;<label>标签 ：</label><form:input path="label" htmlEscape="false" maxlength="50" class="form-control" />
                                    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                                </form:form>
                            </div>
                        </div>
                        <table id="contentTable" class="table table-bordered table-striped mg-t datatable">
                            <thead>
                                <tr>

                                        <th>标签</th>
                                        <th>类型</th>
                                        <th>值</th>
                                        <th>排序</th>
                                        <th>备注信息</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="sysOrgDict">
                                <tr>
                                        <td> ${sysOrgDict.label}</td>
                                        <td> ${sysOrgDict.type}</td>
                                        <td> ${sysOrgDict.value}</td>
                                        <td> ${sysOrgDict.sort}</td>
                                        <td> ${sysOrgDict.remarks}</td>
                                    <td>

                                        <c:if test="${sysOrgDict.type eq 'ADMINISTRATION_DICT'}">
                                            <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysOrgDict/dictVsClinicForm?id=${sysOrgDict.id}')" class="btn btn-primary btn-xs">添加对照</a>
                                        </c:if>
                                        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysOrgDict/form?id=${sysOrgDict.id}')" class="btn btn-success btn-xs">修改</a>
                                        <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该系统平台字典吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/sysOrgDict/delete?id=${sysOrgDict.id}','${ctx}/sys/dict/index')})">删除</a>
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