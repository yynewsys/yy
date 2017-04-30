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
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfServiceVsMenu/index')">机构自定义服务菜单对照表列表</a>
                </li>
                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfServiceVsMenu/form')">机构自定义服务菜单对照表添加</a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <div class="datatable-top">
                            <div class="pull-left">
                            	<form:form id="searchForm" modelAttribute="orgSelfServiceVsMenu"  onsubmit="return loadDivForm('mainCenterDiv','searchFormDict','${ctx}/sys/orgSelfServiceVsMenu/index');" method="post" class="form-inline">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <ul class="ul-form">
                                            <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
                                            <li class="clearfix"></li>
                                    </ul>
                                </form:form>
                            </div>
                        </div>
                        <table id="contentTable" class="table table-bordered mg-t datatable">
                            <thead>
                                <tr>
                                        <th>自定义服务ID</th>
                                        <th>菜单Id</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="orgSelfServiceVsMenu">
                                <tr>
                                        <td> ${orgSelfServiceVsMenu.selfServiceId}</td>
                                        <td> ${orgSelfServiceVsMenu.menuId}</td>
                                    <td>
                                        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfServiceVsMenu/form?id=${orgSelfServiceVsMenu.id}')" class="btn btn-success btn-xs">修改</a>
                                        <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该机构自定义服务菜单对照表吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/orgSelfServiceVsMenu/delete?id=${orgSelfServiceVsMenu.id}','${ctx}/sys/dict/index')})">删除</a>
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