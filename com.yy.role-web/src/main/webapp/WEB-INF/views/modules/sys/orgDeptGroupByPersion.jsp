<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class="content-wrap">
    <div class="wrapper" style="bottom: 50px;">
        <ul class="nav nav-tabs">
            <li>
                <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgDeptGroup/index')">科室分组列表</a>
            </li>
            <li class="active">
                <a href="#" >分配人员</a>
            </li>
        </ul>
        <section class="panel panel-default">
            <div class="panel-body">
                <div class="table-responsive no-border">
                    <div class="datatable-top">
                           <div class="pull-left">
                            <form:form id="assignForm" modelAttribute="orgGroupVsUser" data-parsley-validate=""    onsubmit="return formSaveLoad('mainCenterDiv','assignForm','${ctx}/sys/orgGroupVsUser/save','${ctx}/sys/orgDeptGroup/orgDeptUserForm?id=${orgDeptGroup.id}');" method="post">
                                <input type="hidden" name="groupId" value="${orgDeptGroup.id}"/>
                                <input id="idsArr" type="hidden" name="staffIds" value=""/>
                            </form:form>
                               <label>科室分组名称 ：</label>  ${orgDeptGroup.name} &nbsp;
                               <input id="assignButton" class="btn btn-primary" type="button" value="分配人员"/></div>
                        </div>
                    </div>

                    <table id="contentTable" class="table table-bordered mg-t datatable">
                        <thead><tr><th>所属科室</th><th>姓名</th><th>电话</th><th>邮箱</th><th>操作</th></tr></thead>

                        <tbody>
                        <c:forEach items="${userList}" var="user">
                        <tr>
                            <td>${user.dept_name}</td>
                            <td>${user.name}</td>
                            <td>${user.phone}</td>
                            <td>${user.email}</td>
                            <td>
                                <a href="#" class="btn btn-danger btn-xs"
                                   onclick="confirmExtend('确认要将用户<b>[${user.name}]</b>从<b>[${orgDeptGroup.name}]</b>分组中移除吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/orgGroupVsUser/delete?user.id=${user.id}&groupId=${orgDeptGroup.id}','${ctx}/sys/orgDeptGroup/orgDeptUserForm?id=${orgDeptGroup.id}')})">移除</a>
                            </td>
                        </tr>
                        </c:forEach>
                     </table>
                </div>
        </section>
    </div>
</div>
<script type="text/javascript">
    /*分配人员*/
    $("#assignButton").click(function(){
        layerTree =layer.open({
            title:'分配人员',
            type: 2,
            area: ['500px', '520px'],
            fixed: false, //不固定
            maxmin: true,
            content: '${ctx}/sys/orgRole/usertorole?groupId=${orgDeptGroup.id}',
            btn: ['确定', '取消'],
            yes:function(index,layero){
                var resultIds = $(layero).find("iframe")[0].contentWindow.resultIds;
                $('#idsArr').val(resultIds);
                $('#assignForm').submit();
                layer.close(index);
            } ,no:function(index){
                layer.close(index);
            }
        });
    });
</script>
