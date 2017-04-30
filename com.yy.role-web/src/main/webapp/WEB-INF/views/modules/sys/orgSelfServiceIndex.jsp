<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function maintain(orgServiceId){
            selfServerLayer=layer.open({
                title:'菜单维护',
                type: 2,
                area: ['23%', '70%'],
                content: '${ctx}/sys/orgSelfService/selfServiceSelect?url=/sys/orgSelfService/treeData'+'&selfServiceId='+orgServiceId,
                btn: ['确定', '取消'],
                yes:function(index,layero,nodes){
                    $(layero).find("iframe")[0].contentWindow.saveSelfMaintain(orgServiceId);

                }
            });
        }
    function hrefDiv(data){
        loadDiv('mainCenterDiv','${ctx}/sys/orgSelfService/index');
        toastr.success(data);
        layer.close(selfServerLayer);
    }
</script>
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfService/index')">自定义服务列表</a>
                </li>
                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfService/form')">自定义服务添加</a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <div class="datatable-top">
                            <div class="pull-left">
                            	<form:form id="searchForm" modelAttribute="orgSelfService"  onsubmit="return loadDivForm('mainCenterDiv','searchForm','${ctx}/sys/orgSelfService/index');" method="post" class="form-inline">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <label>服务名称：</label><form:input path="serviceName" htmlEscape="false" maxlength="50" class="form-control" />
                                    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                                </form:form>
                            </div>
                        </div>
                        <table id="contentTable" class="table table-bordered mg-t datatable" style="width:50%">
                            <thead>
                                <tr>
                                        <th>自定义服务名称</th>
                                        <th>排序</th>
                                        <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="orgSelfService">
                                <tr>
                                        <td> ${orgSelfService.serviceName}</td>
                                        <td>${orgSelfService.sort}</td>
                                    <td>
                                        <a href="#" class="btn btn-primary btn-xs" onclick="maintain('${orgSelfService.id}');">菜单维护</a>
                                        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfService/form?id=${orgSelfService.id}')" class="btn btn-success btn-xs">修改</a>
                                        <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该自定义服务吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/orgSelfService/delete?id=${orgSelfService.id}','${ctx}/sys/orgSelfService/index')})">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                        <div class="pagination" style="padding-right: 600px;">${page}</div>
                    </div>
                </div>
            </section>
        </div>
    </div>