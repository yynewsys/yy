<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class="content-wrap">

    <ul class="nav nav-tabs">
        <li>
            <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/company/index')">机构列表</a>
        </li>
        <li>
            <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/company/form')">机构添加</a>
        </li>
        <li class="active">
            <a href="#">服务列表</a>
        </li>
    </ul>

    <section class="panel panel-default">
        <div class="panel-body">
            <div class="table-responsive no-border">

                <div class="datatable-top">
                       <div class="pull-left">
                        <form:form id="assignServiceForm" data-parsley-validate=""    onsubmit="return formSaveLoad('mainCenterDiv','assignServiceForm','${ctx}/sys/company/middleSaveService','${ctx}/sys/company/assignService?id=${company.id}')" method="post">
                            <input type="hidden" name="id" value="${company.id}"/>
                            <input id="idsArr" type="hidden" name="serviceIds" value=""/>
                        </form:form>
                           <label>机构名称 ：</label>  ${company.orgName} &nbsp;
                           <input id="assignButton" class="btn btn-primary" type="button" value="分配服务"/></div>
                    </div>
                </div>

                <table id="contentTable" class="table table-bordered mg-t datatable">
                    <thead><tr><th>服务名称</th><th>服务类型</th><th>服务类别</th><th>操作</th></tr></thead>

                    <tbody>
                    <c:forEach items="${serviceList}" var="service">
                    <tr>
                        <td>${service.serverName}</td>
                        <td>${service.serverType}</td>
                        <td>${service.serverClass}</td>
                        <td>
                             <a href="#"
                               onclick="confirmExtend('确认要将服务<b>[${service.serverName}]</b>从<b>[${company.orgName}]</b>组织机构中移除吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/company/outService?serviceId=${service.serviceId}&companyId=${company.id}','${ctx}/sys/company/assignService?id=${company.id}')})">移除</a>
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
            title:'分配服务',
            type: 2,
            area: ['500px', '520px'],
            fixed: false, //不固定
            maxmin: true,
            content: '${ctx}/sys/company/servicetoCompany?id=${company.id}',
            btn: ['确定', '取消'],
            yes:function(index,layero){
                //resultIds
                var pre_ids = $(layero).find("iframe")[0].contentWindow.pre_ids;
                var ids = $(layero).find("iframe")[0].contentWindow.ids;
                var resultIds = $(layero).find("iframe")[0].contentWindow.resultIds;
                if(ids[0]==''){
                    ids.shift();
                    pre_ids.shift();
                }
                if(pre_ids.sort().toString() == ids.sort().toString()){
                    alert("未给机构【${role.roleName}】分配新服务！");
                    return false;
                };
                // 执行保存
                var idsArr = "";
                for (var i = 0; i<resultIds.length; i++) {
                    idsArr = (idsArr + ids[i]) + (((i + 1)== resultIds.length) ? '':',');
                }
                $('#idsArr').val(idsArr);
                $('#assignServiceForm').submit();
                layer.close(index);
            } ,no:function(index){
                layer.close(index);
            }
        });
    });
</script>
