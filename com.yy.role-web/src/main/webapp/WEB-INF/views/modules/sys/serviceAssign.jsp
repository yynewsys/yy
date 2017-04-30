<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<script type="text/javascript">

    var selectedTree;//zTree已选择对象

    var selectedTreeMenu;//zTree已选择对象
    // 初始化


    var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
        data: {simpleData: {enable: true}},
        callback: {onClick: treeOnClick}};

    var settingServer = {view: {nameIsHTML:true,showTitle:false,dblClickExpand:false},
        data: {simpleData: {enable: true}},
        edit: {
            enable: true,
            showRenameBtn:false,
            showRemoveBtn:true
        },
        callback: {onClick: treeOnClick,beforeRemove: beforeRemove}};
    var settingMenu = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
        data: {simpleData: {enable: true}},
        check: {
            enable: true
        },
        callback: {onClick: treeOnClick}};
    var pre_selectedServerNodes =[
        <c:forEach items="${serviceListAll}" var="serviceAll">
        {id:"${serviceAll.id}",
            pId:"0",
            name:"${serviceAll.serviceName}"},
        </c:forEach>];
    var selectedServerNodes =[
        <c:forEach items="${serviceList}" var="service">
        {id:"${service.serviceId}",
            pId:"0",
            name:"<font color='red' style='font-weight:bold;'>${service.serviceName}</font>"},
        </c:forEach>];
    var selectedMenuNodes=[];
    var resultIds ="${selectIds}".split(",");
    var selectId="";
    //点击选择项回调
    function treeOnClick(event, treeId, treeNode, clickFlag){
        $.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
        if("serviceTree"==treeId){
            if(resultIds.indexOf(treeNode.id)==-1){
                selectedTree.addNodes(null, treeNode);
                getChkNode(selectedTree.getNodes());
                var node = selectedTree.getNodeByParam("id", treeNode.id);
                $("#"+node.tId+"_a").click();
            }
        }
        if("selectedTree"==treeId){
            selectId=treeNode.id;
            var roleId=$("#roleIdVal").val();
            $.ajax({
                url: '${ctx}/sys/orgSelfService/selfServiceSelectAjax?selfServiceId='+treeNode.id+"&roleId="+roleId,
                success: function(data){
                    var menu="";
                    if(data.datas1!=null) {
                        for (var i = 0; i < data.datas1.length; i++) {
                            menu += '{"id":"' + data.datas1[i].id + '","pId":"' + data.datas1[i].parentId + '","name":"' + data.datas1[i].name + '"},';
                        }
                    }
                    menu=menu.substring(0,menu.length-1);
                    menu=eval("["+menu+"]");
                    selectedTreeMenu=$.fn.zTree.init($("#selectedTreeMenu"), settingMenu, menu);
                    selectedTreeMenu.expandAll(true);
                    if(data.datas2!=null){
                        for(var j=0;j<data.datas2.length;j++) {
                            var node = selectedTreeMenu.getNodeByParam("id", data.datas2[j].id);
                            try{selectedTreeMenu.checkNode(node, true, false);}catch(e){}
                        }
                    }
                }
            });
        }
    };
    //组织数据
    function getChkNode(nodes){
        resultIds="";
        resultNames="";
        for(var i=0;i<nodes.length;i++){
            resultIds+=nodes[i].id+","
            resultNames+=nodes[i].name+","
        }
        if(nodes.length>0){
            resultIds=resultIds.substring(0,resultIds.length-1);
            resultNames=resultNames.substring(0,resultNames.length-1);
        }
    }
    //移除操作
    function beforeRemove(treeId, treeNode) {
        layer.confirm("确定移除此服务吗？移除后不可恢复", {
            skin: 'layui-layer-molv',
            btn: ['确定', '取消'] //按钮
        }, function (index) {
            selectId=treeNode.id;
            if(delRole('')){
                selectedTree.removeNode(treeNode);
                getChkNode(selectedTree.getNodes());
            }
            layer.close(index);
        }, function (index) {
            layer.close(index);
            return false;
        });
        return false;
    }
    $(document).ready(function(){
        selectedTree = $.fn.zTree.init($("#selectedTree"), settingServer, selectedServerNodes);
        $.fn.zTree.init($("#serviceTree"), setting, pre_selectedServerNodes);
    });
    //保存选择的服务
    function saveServerMenu(){
        var nodes = selectedTreeMenu.getCheckedNodes(true);
        var selectMenu="";
         for (var i=0;i<nodes.length;i++) {
             selectMenu += nodes[i].id + ",";
        }
        selectMenu=selectMenu.substring(0,selectMenu.length-1);
        delRole(selectMenu);
    }
    //移除服务
    function delRole(selectMenu){
        var roleId=$("#roleIdVal").val();
        var requestParams={serviceId:selectId,menuIds:selectMenu,roleId:roleId}
        if (flag) {
            flag = false;
            $.ajax({
                type: "POST",
                data:requestParams,
                async: false,
                url: "${ctx}/sys/orgRole/middleSaveService",
                dataType: "json",
                success: function (data) {
                    if (data.code == "success") {
                        toastr.success(data.data);
                        flag = true;
                        return true;
                    }
                    else {
                        toastr.error(data.data);
                        flag = true;
                        return false;
                    }
                },
                error: function (data) {
                    return false;
                    flag = true;
                    toastr.error('网络连接错误,请检查网络');
                }
            });
            return true;
        }
    }
</script>
<div class="content-wrap">

    <ul class="nav nav-tabs">
        <li>
            <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/index')">角色列表</a>
        </li>
        <li>
            <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/form')">角色添加</a>
        </li>
        <li class="active">
            <a href="#">分配权限</a>
        </li>
    </ul>

    <section class="panel panel-default" style="min-height: 89%">
        <div class="panel-body">
            <div class="table-responsive no-border">

                <div class="datatable-top">
                    <div class="pull-left">
                        <form:form id="assignServiceForm" data-parsley-validate=""
                                   onsubmit="return formSaveLoad('mainCenterDiv','assignServiceForm','${ctx}/sys/orgRole/middleSaveService','${ctx}/sys/orgRole/assignServer?id=${role.id}');"
                                   method="post">
                            <input type="hidden" id="roleIdVal" name="roleId" value="${role.id}"/>
                            <input id="idsArr" type="hidden" name="serviceIds" value=""/>
                        </form:form>
                        <label>角色名称 ：</label> ${role.roleName}
                </div>
            </div>
          </div>
            <div id="assignRole" class="col-lg-12">
                <div class="col-xs-4">
                    <p>待选服务：</p>
                    <div id="serviceTree" class="ztree"></div>
                </div>
                <div class="col-xs-4" style="padding-left:16px;border-left: 1px solid #A8A8A8;">
                    <p>已选服务：</p>
                    <div id="selectedTree" class="ztree"></div>
                </div>
                <div class="col-xs-4" style="padding-left:16px;border-left: 1px solid #A8A8A8;">
                    <p>配置菜单：<span class="pull-right"><button class="btn btn-primary" onclick="saveServerMenu()">保存</button></span></p>
                    <div id="selectedTreeMenu" class="ztree"></div>
                </div>
            </div>
        </div>

    </section>
</div>
<script type="text/javascript">
    $("#assignButton").click(function () {
        layerTree = layer.open({
            title: '分配服务',
            type: 2,
            area: ['40%', '65%'],
            fixed: false, //不固定
            maxmin: true,
            content: '${ctx}/sys/orgRole/servicetorole?id=${role.id}',
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                var resultIds = $(layero).find("iframe")[0].contentWindow.resultIds;
                $('#idsArr').val(resultIds);
                $('#assignServiceForm').submit();
                layer.close(index);
            }, no: function (index) {
                layer.close(index);
            }
        });
    });
</script>
