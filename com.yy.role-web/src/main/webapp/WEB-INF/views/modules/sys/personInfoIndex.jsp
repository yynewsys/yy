<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
</script>
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/personInfo/index')">人员基本信息列表</a>
                </li>
                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/personInfo/form')">人员基本信息添加</a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <div class="datatable-top">
                            <div class="pull-left">
                            	<form:form id="searchForm" modelAttribute="personInfo"  onsubmit="return loadDivForm('mainCenterDiv','searchFormDict','${ctx}/sys/personInfo/index');" method="post" class="form-inline">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <ul class="ul-form">
                                            <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
                                            <li class="clearfix"></li>
                                    </ul>
                                </form:form>
                            </div>
                        </div>
                        <table id="contentTable" class="table table-bordered mg-t datatable">
                            <thead>
                                <tr>
                                        <th>主键</th>
                                        <th>姓名</th>
                                        <th>性别</th>
                                        <th>身份证号</th>
                                        <th>联系电话</th>
                                        <th>邮箱</th>
                                        <th>昵称</th>
                                        <th>标志</th>
                                        <th>创建人</th>
                                        <th>创建时间</th>
                                        <th>更新人</th>
                                        <th>更新时间</th>
                                        <th>备注信息</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="personInfo">
                                <tr>
                                        <td> ${personInfo.id}</td>
                                        <td> ${personInfo.name}</td>
                                        <td> ${personInfo.sex}</td>
                                        <td> ${personInfo.idCard}</td>
                                        <td> ${personInfo.phone}</td>
                                        <td> ${personInfo.email}</td>
                                        <td> ${personInfo.nickName}</td>
                                        <td> ${personInfo.delFlag}</td>
                                        <td> ${personInfo.createBy.id}</td>
                                        <td> ${personInfo.createDate}</td>
                                        <td> ${personInfo.updateBy.id}</td>
                                        <td> ${personInfo.updateDate}</td>
                                        <td> ${personInfo.remarks}</td>
                                    <td>
                                        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/personInfo/form?id=${personInfo.id}')" class="btn btn-success btn-xs">修改</a>
                                        <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该人员基本信息吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/personInfo/delete?id=${personInfo.id}','${ctx}/sys/dict/index')})">删除</a>
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