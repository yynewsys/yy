<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script>
    function page(n,s){
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchFormService").submit();
        return false;
    }
    //维护菜单弹出页
    function maintainMenu(serviceId){
        serviceLayer=layer.open({
            title:'菜单维护',
            type: 2,
            area: ['30%', '70%'],
            content: '${ctx}/sys/sysMenuDict/treeselect?url=/sys/sysMenuDict/treeData'+'&serviceId='+serviceId,
            btn: ['确定', '取消'],
            yes:function(index,layero,nodes){
                $(layero).find("iframe")[0].contentWindow.saveMaintain(serviceId);

            }
        });
    }
    //分配菜单成功后跳转方法
    function hrefDiv(data){
        loadDiv('mainCenterDiv','${ctx}/sys/service/index');
        toastr.success(data);
        layer.close(serviceLayer);

    }
</script>
    <!-- content wrapper -->
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/service/index')">服务列表</a>
                </li>

                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/service/form')">服务添加</a>
                </li>

            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <div class="datatable-top">
                            <div class="pull-left">
                                <form:form id="searchFormService" modelAttribute="sysService" onsubmit="return loadDivForm('mainCenterDiv','searchFormService','${ctx}/sys/service/index');" method="post" class="form-inline">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <label>服务名称：</label><form:input path="serverName" htmlEscape="false" maxlength="50" class="form-control" />
                                    &nbsp;&nbsp;<label>服务类型 ：
                                    <select name="serverType" class="form-control">
                                        <option value=""></option>
                                    <c:forEach items="${fns:getDictList('service_type')}" var="dict">
                                        <c:choose>
                                            <c:when test="${dict.value==sysService.serverType}">
                                                <option value="${dict.value}" selected="selected">${dict.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${dict.value}">${dict.label}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    </select>
                                    </label>
                                    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                                </form:form>
                            </div>
                        </div>


                        <table id="contentTable" class="table table-bordered mg-t datatable">
                            <thead>
                            <tr>
                                <th>服务名称</th>
                                <th>服务类型</th>
                                <th>服务类别</th>
                                <th>服务图片</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="service">
                            <tr>
                                <td>${service.serverName}</td>
                                <td>${fns:getDictLabels(service.serverType, 'service_type', '')}</td>
                                <td>${fns:getDictLabels(service.serverClass, 'service_class', '')}</td>
                                <td>${service.serviceImage}</td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" onclick="maintainMenu('${service.id}');">菜单维护</a>
                                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/service/form?id=${service.id}')" class="btn btn-success btn-xs">修改</a>
                                    <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该服务吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/service/delete?id=${service.id}','${ctx}/sys/service/index')})">删除</a>
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