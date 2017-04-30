<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script>
    function page(n,s){
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchFormDict").submit();
        return false;
    }

    var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
        callback:{onClick:function(event, treeId, treeNode){
            var id = treeNode.id == '0' ? '' :treeNode.id;
            loadDiv('mainCenterDiv',"${ctx}/sys/user/list?deptId="+id);
        }
        }
    };
    $.getJSON("${ctx}/sys/orgDept/treeData",function(data){
        $.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
    });

</script>
<div class="app">
    <section class="layout">
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
        <section class="main-content" id="rigthCenterDiv">
            <div class="content-wrap">
                <div class="wrapper">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/user/index')">用户列表</a>
                        </li>

                        <li>
                            <a href="#" onclick="loadDiv('rigthCenterDiv','${ctx}/sys/user/form')">用户添加</a>
                        </li>

                    </ul>
                    <section class="panel">
                        <div class="panel-body">
                            <div class="table-responsive no-border">
                                <div class="datatable-top">
                                    <div class="pull-left">
                                        <form:form id="searchFormDict" modelAttribute="user" onsubmit="return loadDivForm('mainCenterDiv','searchFormDict','${ctx}/sys/user/index');" method="post" class="form-inline">
                                            <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                            <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                            <label>姓名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="form-control" />
                                            &nbsp;&nbsp;<label>手机 ：</label><form:input path="phone" htmlEscape="false" maxlength="50" class="form-control" />
                                            &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                                        </form:form>
                                    </div>
                                </div>
                                <table id="contentTable" class="table table-bordered mg-t datatable">
                                    <thead><tr><th>姓名</th><th>登录名</th><th>身份证号</th><th>手机</th><th>邮箱</th><th>用户类型</th><th>操作</th></tr></thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="user">
                                    <tr>
                                        <td><a href="${ctx}/sys/user/form?id=${user.id}">${user.name}</a></td>
                                        <td>${user.currentLogin}</td>
                                        <td>${user.idCard}</td>
                                        <td>${user.phone}</td>
                                        <td>${user.email}</td>
                                        <td>${fns:getDictLabels(user.userType, 'sys_user_type', '')}</td>
                                        <td>
                                            <a href="#" onclick="confirmExtend('确认要把该用户密码重置为【123456】吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/user/resetPass?id=${user.id}','${ctx}/sys/user/index')})" class="btn btn-primary btn-xs">重置密码</a>
                                            <a href="#" onclick="loadDiv('rigthCenterDiv','${ctx}/sys/user/form?id=${user.id}')" class="btn btn-success btn-xs">修改</a>
                                            <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要停用该用户吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/user/delete?id=${user.id}','${ctx}/sys/user/index')})">停用</a>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </table>
                                <div class="pagination">${page}</div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </section>
    </section>
</div>
