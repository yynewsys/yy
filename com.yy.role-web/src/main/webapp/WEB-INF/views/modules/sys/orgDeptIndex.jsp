<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">

        var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
            callback:{onClick:function(event, treeId, treeNode){
                var id = treeNode.pId == '0' ? '' :treeNode.pId;
                loadDiv('mainCenterDiv',"${ctx}/sys/orgDept/list?id="+id+"&parentIds="+treeNode.pIds);
            }
            }
        };
        $.getJSON("${ctx}/sys/orgDept/treeData",function(data){
            $.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
        });
        $("#contentTable").treeTable({expandLevel : 3});
</script>
<div class="app">
    <section class="layout">
        <!-- sidebar menu -->

        <!-- /sidebar menu -->

        <!-- mail sidebar navigation -->
        <aside class="sidebar-250 canvas-right bg-default ">
            <header class="header navbar clearfix bb bg-default pl0 pr0">
                <p class="navbar-text">科室列表</p>
                <div class="btn-group pull-right mr10">
                    <button type="button" class="btn btn-sm btn-primary navbar-btn">
                        <i class="ti-comment-alt"></i>
                    </button>
                    <button type="button" class="btn btn-sm btn-primary navbar-btn">
                        <i class="ti-help-alt"></i>
                    </button>
                </div>
            </header>

            <div class="content-wrap no-p">

                <div class="wrapper">

                    <div class="m15">
                        <div id="ztree" class="ztree">
                           
                        </div>
                    </div>

                </div>
            </div>
        </aside>
        <!-- /mail sidebar navigation -->

        <!-- main content -->
        <section class="main-content" id="rigthCenterDiv">
            <div class="content-wrap">
                <div class="wrapper" >
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgDept/index')">科室列表</a>
                        </li>
                        <li>
                            <a href="#" onclick="loadDiv('rigthCenterDiv','${ctx}/sys/orgDept/form')">科室添加</a>
                        </li>
                    </ul>
                    <section class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive no-border">
                                <div class="datatable-top">
                                    <div class="pull-left">

                                    </div>
                                </div>
                                <table id="contentTable" class="table table-bordered mg-t datatable">
                                    <thead>
                                    <tr>
                                        <th>科室名称</th>
                                        <th>科室代码</th>
                                        <th>临床标识</th>
                                        <th>门诊住院属性</th>
                                        <th>内外科标识</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${treeList}" var="orgDept">
                                    <c:choose>
                                        <c:when test="${!empty orgDept.parent.id}">
                                            <tr id='${orgDept.id}' pId='${orgDept.parent.id}'>
                                        </c:when>
                                        <c:otherwise>
                                            <tr id='${orgDept.id}'>
                                        </c:otherwise>
                                    </c:choose>
                                        <td> ${orgDept.deptName}</td>
                                        <td> ${orgDept.deptCode}</td>
                                        <td>${fns:getDictLabel(orgDept.clinicAttr, 'DEPT_CLINIC_ATTR_DICT', '')}</td>
                                        <td>${fns:getDictLabel(orgDept.outpOrInp, 'DEPT_OI_ATTR_DICT', '')}</td>
                                        <td>${fns:getDictLabel(orgDept.internalOrSerger, 'DEPT_IS_ATTR_DICT', '')}</td>
                                        <td>
                                            <a href="#" onclick="loadDiv('rigthCenterDiv','${ctx}/sys/orgDept/form?id=${orgDept.id}')" class="btn btn-success btn-xs">修改</a>
                                            <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该科室吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/orgDept/delete?id=${orgDept.id}','${ctx}/sys/orgDept/index')})">删除</a>
                                        </td>
                                    </tr>
                                    </c:forEach>

                                </table>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </section>
        <!-- /main content -->
    </section>
</div>

