<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分配角色</title>
	<meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/vendor/bootstrap/dist/css/bootstrap.min.css">
    <script src="${ctxStatic}/vendor/jquery/dist/jquery.js"></script>
    <script src="${ctxStatic}/vendor/bootstrap/dist/js/bootstrap.js"></script>
    <script src="${ctxStatic}/vendor/treeTable/jquery.treeTable.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/vendor/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
    <link href="${ctxStatic}/vendor/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
	
		var officeTree;
		var selectedTree;//zTree已选择对象
		
		// 初始化
		$(document).ready(function(){
			officeTree = $.fn.zTree.init($("#officeTree"), setting, officeNodes);
			selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
		});

		var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
				data: {simpleData: {enable: true}},
				callback: {onClick: treeOnClick}};
		
		var officeNodes=[
	            <c:forEach items="${officeList}" var="office">
	            {id:"${office.id}",
	             pId:"${not empty office.parent?office.parent.id:0}", 
	             name:"${office.deptName}"},
	            </c:forEach>];
		
		var selectedNodes =[
		        <c:forEach items="${userList}" var="user">
		        {id:"${user.id}",
		         pId:"0",
		         name:"<font color='red' style='font-weight:bold;'>${user.name}</font>",
                 userid:"${user.userid}"
                },
		        </c:forEach>];

		var resultIds ="${selectIds}".split(",");
        var resultNames="";
 		//点击选择项回调
		function treeOnClick(event, treeId, treeNode, clickFlag){
			$.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
			if("officeTree"==treeId){
                //
				$.get("${ctx}/sys/orgRole/users?officeId=" + treeNode.id, function(userNodes){
					$.fn.zTree.init($("#userTree"), setting, userNodes);
				});
			}
			if("userTree"==treeId){
   				if(resultIds.indexOf(treeNode.userid)==-1){
  					selectedTree.addNodes(null, treeNode);
                    getChkNode(selectedTree.getNodes());
   				}
			}
            if("selectedTree"==treeId){
                selectedTree.removeNode(treeNode);
                getChkNode(selectedTree.getNodes());
            }
		};
        function getChkNode(nodes){
            resultIds="";
            resultNames="";
            for(var i=0;i<nodes.length;i++){
                resultIds+=nodes[i].userid+","
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
		<div class="col-xs-4" style="border-right: 1px solid #A8A8A8;">
			<p>所在科室：</p>
			<div id="officeTree" class="ztree"></div>
		</div>
		<div class="col-xs-4">
			<p>待选人员：</p>
			<div id="userTree" class="ztree"></div>
		</div>
		<div class="col-xs-4" style="padding-left:16px;border-left: 1px solid #A8A8A8;">
			<p>已选人员：</p>
			<div id="selectedTree" class="ztree"></div>
		</div>
	</div>
</body>
</html>
