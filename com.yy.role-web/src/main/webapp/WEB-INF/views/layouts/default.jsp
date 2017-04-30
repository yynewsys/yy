<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
	<title><sitemesh:title/></title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>		
	<!-- Baidu tongji analytics --><script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script>
	<sitemesh:head/>
</head>
<body>
	<sitemesh:body/>
    <%@include file="/WEB-INF/views/include/tail.jsp" %>
    <script>
        var flag=true;
        $(document).ready(function() {
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "positionClass": "toast-top-right",
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            if($("#serviceUl li").length==0){
                serviceChange('0','')
            }else{
                var serviceId='${fns:getUser().currentService.id}';
                if(serviceId=='' || serviceId==null){
                    $("#serviceUl li:first a").click();
                }else{
                    serviceChange(serviceId,'${fns:getUser().currentService.serviceName}');
                }

            }
            var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
                callback:{onClick:function(event, treeId, treeNode){
                    if(!treeNode.isParent){
                        var id = treeNode.id;
                        var name=treeNode.name;
                        switchDept(id,name);
                    }else{
                        toastr.error("请选择最下级科室");
                    }
                }
                }
            };
            $.getJSON("${ctx}/sys/orgDept/treeData?role=1",function(data){
                var deptRoleId='${fns:getUser().orgDept.deptName}';
                if((deptRoleId==''|| deptRoleId==null)&&data.length>0){
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-molv',
                        title:'请选择登录科室',
                        area: ['20%', '70%'],
                        fixed: false, //不固定
                        closeBtn: 0,
                        content: '<div class="ztree" id="ztreeDept"></div>'
                    });
                    $.fn.zTree.init($("#ztreeDept"), setting, data).expandAll(true);
                }
                $.fn.zTree.init($("#roleDept"), setting, data).expandAll(true);
            });

        })

        /**
         * 切换科室选择
         * */
        function switchDept(id,name){
            $.ajax({
                type: "get",
                url: '${ctx}/sys/user/updateUser?deptId='+id+"&deptName="+name,
                dataType: "json",
                success: function (data) {
                    window.location.reload();
                },
                error: function (data) {
                    toastr.error('网络连接错误,请检查网络');
                }
            })
        }
        function serviceChange(id,name){
            $("#currentServiceDiv").html(name);
            $("#treeMenuDiv").load("${ctx}/service/change?serviceId="+id);
        }

    </script>
</body>
</html>