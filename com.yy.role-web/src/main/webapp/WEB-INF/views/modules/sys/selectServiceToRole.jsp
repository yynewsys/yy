<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分配服务</title>
	<meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/vendor/bootstrap/dist/css/bootstrap.min.css">
    <script src="${ctxStatic}/vendor/jquery/dist/jquery.js"></script>
    <script src="${ctxStatic}/vendor/bootstrap/dist/js/bootstrap.js"></script>
    <script src="${ctxStatic}/vendor/treeTable/jquery.treeTable.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/vendor/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
    <link href="${ctxStatic}/vendor/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
	
 		var selectedTree;//zTree已选择对象

        var selectedTreeMenu;//zTree已选择对象
		// 初始化
		$(document).ready(function(){
 			selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
            $.fn.zTree.init($("#serviceTree"), setting, pre_selectedNodes);
		});

		var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
				data: {simpleData: {enable: true}},
				callback: {onClick: treeOnClick}};

        var settingMenu = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
            data: {simpleData: {enable: true}},
            check: {
                enable: true
            },
            callback: {onClick: treeOnClick}};

		var pre_selectedNodes =[
   		        <c:forEach items="${serviceListAll}" var="service">
   		        {id:"${service.id}",
   		         pId:"0",
   		         name:"${service.serviceName}"},
   		        </c:forEach>];
		var selectedNodes =[
		        <c:forEach items="${serviceList}" var="service">
		        {id:"${service.serviceId}",
		         pId:"0",
		         name:"<font color='red' style='font-weight:bold;'>${service.serviceName}</font>"},
		        </c:forEach>];

        var selectedMenuNodes=[];
        var resultIds ="${selectIds}".split(",");

 		//点击选择项回调
		function treeOnClick(event, treeId, treeNode, clickFlag){
			$.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
 			if("serviceTree"==treeId){
                if(resultIds.indexOf(treeNode.id)==-1){
                    selectedTree.addNodes(null, treeNode);
                    getChkNode(selectedTree.getNodes());
                    $("#selectedTree_2_a").click();
                }
			}
            if("selectedTree"==treeId){
                var nodes = selectedTree.getNodes();
                var selfServiceIds="";
                for(var i=0;i<nodes.length;i++){
                    selfServiceIds+=nodes[i].id+","
                }
                selfServiceIds=selfServiceIds.substring(0,selfServiceIds.length-1);
                $.ajax({
                    url: '${ctx}/sys/orgSelfService/selfServiceSelectAjax?selfServiceId='+selfServiceIds,
                    success: function(data){
                       var menu="";
                        for(var i=0;i<data.datas1.length;i++){
                            menu+='{"id":"'+data.datas1[i].id+'","pId":"'+data.datas1[i].parentId+'","name":"'+data.datas1[i].name+'"},';
                        }
                        menu=menu.substring(0,menu.length-1);
                        menu=eval("["+menu+"]");
                        selectedTreeMenu=$.fn.zTree.init($("#selectedTreeMenu"), settingMenu, menu);
                        selectedTreeMenu.expandAll(true);
                        for(var j=0;j<data.datas2.length;j++) {
                            var node = selectedTreeMenu.getNodeByParam("id", data.datas2[j].id);
                            try{selectedTreeMenu.checkNode(node, true, false);}catch(e){}
                        }
                    }
                });
            }
		};
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
	</script>
</head>
<body>
	<div id="assignRole" class="col-lg-12">
		<%--<div class="col-xs-4" style="border-right: 1px solid #A8A8A8;">--%>
			<%--<p>所在科室：</p>--%>
			<%--<div id="officeTree" class="ztree"></div>--%>
		<%--</div>--%>
		<div class="col-xs-4">
			<p>待选服务：</p>
			<div id="serviceTree" class="ztree"></div>
		</div>
		<div class="col-xs-4" style="padding-left:16px;border-left: 1px solid #A8A8A8;">
			<p>已选服务：</p>
			<div id="selectedTree" class="ztree"></div>
		</div>
        <div class="col-xs-4" style="padding-left:16px;border-left: 1px solid #A8A8A8;">
            <p>配置菜单：</p>
            <div id="selectedTreeMenu" class="ztree"></div>
        </div>
	</div>
</body>
</html>
