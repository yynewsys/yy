<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        /*分配科室*/
        function assignDept(groupId){
            groupDeptLayer=layer.open({
                title:'分配科室',
                type: 2,
                area: ['30%', '70%'],
                content: '${ctx}/sys/orgGroupVsDept/findDeptIdByGroupId?url=/sys/orgDeptGroup/treeData'+'&groupId='+groupId,
                btn: ['确定', '取消'],
                yes:function(index,layero,nodes){
                    $(layero).find("iframe")[0].contentWindow.saveGroupDept(groupId);

                }
            });
        }
        function hrefDiv(data){
            loadDiv('mainCenterDiv','${ctx}/sys/orgDeptGroup/index');
            toastr.success(data);
            layer.close(groupDeptLayer);
        }
  </script>
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgDeptGroup/index')">科室分组列表</a>
                </li>
                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgDeptGroup/form')">科室分组添加</a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <div class="datatable-top">
                            <div class="pull-left">
                            	<form:form id="searchForm" modelAttribute="orgDeptGroup"  onsubmit="return loadDivForm('mainCenterDiv','searchForm','${ctx}/sys/orgDeptGroup/index');" class="form-inline">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <label>分组名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="form-control" />
                                   <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                                </form:form>
                            </div>
                        </div>
                        <table id="contentTable" class="table table-bordered mg-t datatable">
                            <thead>
                                <tr>
                                    <th>分组名称</th>
                                    <th>排序</th>
                                    <th>备注信息</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="orgDeptGroup">
                                <tr>
                                    <td> ${orgDeptGroup.name}</td>
                                    <td> ${orgDeptGroup.sort}</td>
                                    <td> ${orgDeptGroup.remarks}</td>
                                    <td>

                                        <a href="#" class="btn btn-primary btn-xs" onclick="assignDept('${orgDeptGroup.id}');">分配科室</a>
                                        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgDeptGroup/orgDeptUserForm?id=${orgDeptGroup.id}')" class="btn btn-info btn-xs">分配人员</a>
                                        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgDeptGroup/form?id=${orgDeptGroup.id}')" class="btn btn-success btn-xs">修改</a>
                                        <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该科室分组吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/orgDeptGroup/delete?id=${orgDeptGroup.id}','${ctx}/sys/orgDeptGroup/index')})">删除</a>
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