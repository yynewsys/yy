<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class="content-wrap">

    <ul class="nav nav-tabs">
        <li>
            <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/index')">角色列表</a>
        </li>
        <li>
            <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/form')">角色添加</a>
        </li>
        <li class="active">
            <a href="#">人员列表</a>
        </li>
    </ul>

    <section class="panel panel-default">
        <div class="panel-body">
            <div class="table-responsive no-border">

                <div class="datatable-top">
                       <div class="pull-left">
                        <form:form id="assignRoleForm" data-parsley-validate=""    onsubmit="return formSaveLoad('mainCenterDiv','assignRoleForm','${ctx}/sys/orgRole/middleSave','${ctx}/sys/orgRole/assign?id=${role.id}');" method="post">
                            <input type="hidden" name="roleId" value="${role.id}"/>
                            <input id="idsArr" type="hidden" name="staffIds" value=""/>
                        </form:form>
                           <label>角色名称 ：</label>  ${role.roleName} &nbsp;
                           <input id="assignButton" class="btn btn-primary" type="button" value="分配人员"/></div>
                    </div>
                </div>

                <table id="contentTable" class="table table-bordered mg-t datatable">
                    <thead><tr><th>所属科室</th><th>姓名</th><th>电话</th><th>操作</th></tr></thead>

                    <tbody>
                    <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.deptName}</td>
                        <td>${user.name}</td>
                        <td>${user.phone}</td>
                        <td>

                            <a href="#"  class="btn btn-success btn-xs"
                               onclick="confirmExtend('确认要将用户<b>[${user.name}]</b>从<b>[${user.roleName}]</b>角色中移除吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/orgRole/outrole?staffId=${user.staffId}&roleId=${user.roleId}','${ctx}/sys/orgRole/assign?id=${role.id}')})">移除</a>
                        </td>
                    </tr>
                    </c:forEach>
                 </table>
            </div>
    </section>
 </div>
<script type="text/javascript">
    $("#assignButton").click(function(){
        layerTree =layer.open({
            title:'分配人员',
            type: 2,
            area: ['50%', '50%'],
            fixed: false, //不固定
            maxmin: true,
            content: '${ctx}/sys/orgRole/usertorole?roleId=${role.id}',
            btn: ['确定', '取消'],
            yes:function(index,layero){
                var resultIds = $(layero).find("iframe")[0].contentWindow.resultIds;
                $('#idsArr').val(resultIds);
                 $('#assignRoleForm').submit();
                layer.close(index);
            } ,no:function(index){
                layer.close(index);
            }
        });
    });
</script>
