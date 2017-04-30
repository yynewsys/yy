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
		
		// 初始化
		$(document).ready(function(){
 			selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
            $.fn.zTree.init($("#serviceTree"), setting, pre_selectedNodes);
		});

		var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
				data: {simpleData: {enable: true}},
				callback: {onClick: treeOnClick}};
		

		var pre_selectedNodes =[
   		        <c:forEach items="${serviceListAll}" var="service">
   		        {id:"${service.id}",
   		         pId:"0",
   		         name:"<font style='font-weight:bold;'>${service.serverName}</font>"},
   		        </c:forEach>];
		
		var selectedNodes =[
		        <c:forEach items="${serviceList}" var="service">
		        {id:"${service.serviceId}",
		         pId:"0",
		         name:"<font color='red' style='font-weight:bold;'>${service.serverName}</font>"},
		        </c:forEach>];
		
		var pre_ids = "${selectIds}".split(",");
		var ids = "${selectIds}".split(",");
        var resultIds=[];
 		//点击选择项回调
		function treeOnClick(event, treeId, treeNode, clickFlag){
			$.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
 			if("serviceTree"==treeId){
 				//alert(treeNode.id + " | " + ids);
				//alert(typeof ids[0] + " | " +  typeof treeNode.id);
				if($.inArray(String(treeNode.id), ids)<0){
                    //当ids 不包含id 时
 					selectedTree.addNodes(null, treeNode);
					ids.push(String(treeNode.id));
                    resultIds.push(String(treeNode.id));
 				}
			};
		};
	</script>
</head>
<body>
	<div id="assignRole" class="col-lg-12">
 		<div class="col-xs-6">
			<p>待选服务：</p>
			<div id="serviceTree" class="ztree"></div>
		</div>
		<div class="col-xs-6" style="padding-left:16px;border-left: 1px solid #A8A8A8;">
			<p>已选服务：</p>
			<div id="selectedTree" class="ztree"></div>
		</div>
	</div>
</body>
</html>
