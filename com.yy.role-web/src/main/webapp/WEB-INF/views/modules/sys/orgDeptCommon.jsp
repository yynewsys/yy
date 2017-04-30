<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据选择</title>
    <link href="${ctxStatic}/vendor/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css"/>
    <script src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
    <script src="${ctxStatic}/vendor/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/vendor/layer/layer.js"></script>
    <script src="${ctxStatic}/js/jims-his.js" type="text/javascript"></script>
</head>
<body>
	<div id="tree" class="ztree" style="padding:15px 20px;"></div>
</body>
<script type="text/javascript">
    var key, lastValue = "", nodeList = [], type = getQueryString("type", "${url}");
    var tree, setting = {view:{selectedMulti:false,dblClickExpand:false},check:{enable:true,nocheckInherit:false},
        async:{enable:(type==3),url:"${ctx}/sys/user/treeData",autoParam:["id=officeId"]},
        data:{simpleData:{enable:true}},callback:{<%--
					beforeClick: function(treeId, treeNode){
						if("${checked}" == "true"){
							//tree.checkNode(treeNode, !node.checked, true, true);
							tree.expandNode(treeNode, true, false, false);
						}
					}, --%>
            onClick:function(event, treeId, treeNode){
                tree.expandNode(treeNode);
            },onCheck: function(e, treeId, treeNode){
                var nodes = tree.getCheckedNodes(true);
                for (var i=0, l=nodes.length; i<l; i++) {
                    tree.expandNode(nodes[i], true, false, false);
                }
                return false;
            },onAsyncSuccess: function(event, treeId, treeNode, msg) {
                var nodes = tree.getNodesByParam("pId", treeNode.id, null);
                for (var i = 0, l = nodes.length; i < l; i++) {
                    try {
                        tree.checkNode(nodes[i], treeNode.checked, true);
                    } catch (e) {
                    }
                    //tree.selectNode(nodes[i], false);
                }

                selectCheckNode();
            },onDblClick: function(){

            }
        }

    };

    function expandNodes(nodes) {
        if (!nodes) return;
        for (var i=0, l=nodes.length; i<l; i++) {
            tree.expandNode(nodes[i], true, false, false);
            if (nodes[i].isParent && nodes[i].zAsync) {
                expandNodes(nodes[i].children);
            }
        }
    }
    $(document).ready(function(){
        $.get("${ctx}${url}", function(zNodes){
            // 初始化树结构
            tree = $.fn.zTree.init($("#tree"), setting, zNodes);

            // 默认展开一级节点
            var nodes = tree.getNodesByParam("level", 0);
            for(var i=0; i<nodes.length; i++) {
                tree.expandNode(nodes[i], true, false, false);
            }
            //异步加载子节点（加载用户）
            var nodesOne = tree.getNodesByParam("isParent", true);
            for(var j=0; j<nodesOne.length; j++) {
                tree.reAsyncChildNodes(nodesOne[j],"!refresh",true);
            }
            selectCheckNode();
            // 默认选择节点

            var depts="<c:forEach items="${depts}" var="dept">${dept},</c:forEach>";
            var ids2=depts.split(",");
            for(var i=0; i<ids2.length; i++) {
                var node = tree.getNodeByParam("id", ids2[i]);
                try{tree.checkNode(node, true, false);}catch(e){}
            }
        });
    });

    //保存自定义服务菜单
    function saveGroupDept(groupId){
        var ids = [], nodes = tree.getCheckedNodes(true);
        for(var i=0; i<nodes.length; i++) {
            ids.push(nodes[i].id);
        }
        $.ajax({
            type:'post',
            url:'${ctx}/sys/orgGroupVsDept/save?groupId='+groupId+'&deptIds='+ids,
            success:function(data){
                if(data.code=="success"){
                    parent.hrefDiv(data.data);
                }
            }
        });

    }
    // 默认选择节点
    function selectCheckNode(){
        var ids = "${selectIds}".split(",");
        for(var i=0; i<ids.length; i++) {
            var node = tree.getNodeByParam("id", (type==3?"u_":"")+ids[i]);
            if("${checked}" == "true"){
                try{tree.checkNode(node, true, true);}catch(e){}
                tree.selectNode(node, false);
            }else{
                tree.selectNode(node, true);
            }
        }
    }

    //隐藏所有节点
    function hideAllNode(nodes){
        nodes = tree.transformToArray(nodes);
        for(var i=nodes.length-1; i>=0; i--) {
            tree.hideNode(nodes[i]);
        }
    }

    //显示所有节点
    function showAllNode(nodes){
        nodes = tree.transformToArray(nodes);
        for(var i=nodes.length-1; i>=0; i--) {
            /* if(!nodes[i].isParent){
             tree.showNode(nodes[i]);
             }else{ */
            if(nodes[i].getParentNode()!=null){
                tree.expandNode(nodes[i],false,false,false,false);
            }else{
                tree.expandNode(nodes[i],true,true,false,false);
            }
            tree.showNode(nodes[i]);
            showAllNode(nodes[i].children);
            /* } */
        }
    }

    //更新节点状态
    function updateNodes(nodeList) {
        tree.showNodes(nodeList);
        for(var i=0, l=nodeList.length; i<l; i++) {

            //展开当前节点的父节点
            tree.showNode(nodeList[i].getParentNode());
            //tree.expandNode(nodeList[i].getParentNode(), true, false, false);
            //显示展开符合条件节点的父节点
            while(nodeList[i].getParentNode()!=null){
                tree.expandNode(nodeList[i].getParentNode(), true, false, false);
                nodeList[i] = nodeList[i].getParentNode();
                tree.showNode(nodeList[i].getParentNode());
            }
            //显示根节点
            tree.showNode(nodeList[i].getParentNode());
            //展开根节点
            tree.expandNode(nodeList[i].getParentNode(), true, false, false);
        }
    }



</script>
</html>