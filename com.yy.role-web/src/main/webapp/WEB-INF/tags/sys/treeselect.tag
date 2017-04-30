<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="树结构数据地址"%>
<%@ attribute name="checked" type="java.lang.Boolean" required="false" description="是否显示复选框，如果不需要返回父节点，请设置notAllowSelectParent为true"%>
<%@ attribute name="extId" type="java.lang.String" required="false" description="排除掉的编号（不能选择的编号）"%>
<%@ attribute name="isAll" type="java.lang.Boolean" required="false" description="是否列出全部数据，设置true则不进行数据权限过滤（目前仅对Office有效）"%>
<%@ attribute name="notAllowSelectRoot" type="java.lang.Boolean" required="false" description="不允许选择根节点"%>
<%@ attribute name="notAllowSelectParent" type="java.lang.Boolean" required="false" description="不允许选择父节点"%>
<%@ attribute name="module" type="java.lang.String" required="false" description="过滤栏目模型（只显示指定模型，仅针对CMS的Category树）"%>
<%@ attribute name="selectScopeModule" type="java.lang.Boolean" required="false" description="选择范围内的模型（控制不能选择公共模型，不能选择本栏目外的模型）（仅针对CMS的Category树）"%>
<%@ attribute name="allowClear" type="java.lang.Boolean" required="false" description="是否允许清除"%>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="文本框可填写"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="smallBtn" type="java.lang.Boolean" required="false" description="缩小按钮显示"%>
<%@ attribute name="hideBtn" type="java.lang.Boolean" required="false" description="是否显示按钮"%>
<%@ attribute name="disabled" type="java.lang.String" required="false" description="是否限制选择，如果限制，设置为disabled"%>
<%@ attribute name="dataMsgRequired" type="java.lang.String" required="false" description=""%>
<%@ attribute name="update" type="java.lang.Boolean" required="false" description="是否可以修改"%>
<%@ attribute name="isnull" type="java.lang.Boolean" required="false" description="是否为空"%>
<%@ attribute name="method" type="java.lang.String" required="false" description="方法"%>
<%@ attribute name="selectedParent" type="java.lang.Boolean" required="true" description="不可选中父级"%>
<div class="input-group">
	<input id="${id}Id" name="${name}" class="${cssClass}" type="hidden" value="${value}"/>
	<input id="${id}Name" name="${labelName}" ${allowInput?'':'readonly="readonly"'} type="text"   ${isnull?'data-parsley-required="true"':''}  value="${labelValue}" data-msg-required="${dataMsgRequired}"
		class="${cssClass}" style="${cssStyle}"/>
    <span class="input-group-btn">
		<button  id="${id}Button"  class="btn btn-default" type="button" >搜</button>
	</span>
</div>
<script type="text/javascript">

	$("#${id}Button, #${id}Name").click(function(){
		// 是否限制选择，如果限制，设置为disabled
		if ($("#${id}Button").hasClass("disabled")){
			return true;
		}
        if('${value}'!='' && '${value}'!=null){
            if(${update}){
                toastr.error('已经存在数据不能选择');
                return false;
            }
        }
        var url='${ctx}/tag/treeselect?url='+encodeURIComponent("${url}")+'&module=${module}&checked=${checked}&extId=${extId}&isAll=${isAll}&id=${id}&selected=${selectedParent}&title=${title}';
        url=timestamp(url);
       layerTree =layer.open({
            title:'选择${title}',
            type: 2,
            area: ['300px', '420px'],
            fixed: false, //不固定
            maxmin: true,
            content: url,
           btn: ['确定', '取消'],
            yes:function(index,layero){
                var tree =$(layero).find("iframe")[0].contentWindow.tree;
                ok${id}Clinic(tree);
            } ,no:function(index){
                $("#${id}Id").val("");
                $("#${id}Name").val("");
                layer.close(index);
            }
        });
	});
    function ok${id}Clinic(tree){
        var ids = [], names = [], nodes = [];
        if ("${checked}" == "true"){
            nodes = tree.getCheckedNodes(true);
        }else{
            nodes = tree.getSelectedNodes();
        }
        for(var i=0; i<nodes.length; i++) {
            if(${selectedParent}){
                if(nodes[i].isParent){
                    toastr.error("不能选择根节点（"+nodes[i].name+"）请重新选择。");
                    return false;
                }
            }
            ids.push(nodes[i].id);
            names.push(nodes[i].name);//<c:if test="${!checked}">
            break; // 如果为非复选框选择，则返回第一个选择  </c:if>
        }
        $("#${id}Id").val(ids.join(",").replace(/u_/ig,""));
        $("#${id}Name").val(names.join(","));
        if(typeof ${id}TreeselectCallBack == 'function'){
            ${id}TreeselectCallBack(v, h, f);
        }
        var hiddenVal=$("#${id}Id").val();
        ${method}(hiddenVal);
        layer.close(layerTree);
    }
</script>