<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li>
                    <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/index')">角色列表</a>
                </li>
                <li class="active">
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/form')">
                        <c:choose>
                            <c:when test="${orgRole.id==''|| orgRole.id==null}">
                                角色添加
                            </c:when>
                            <c:otherwise>角色修改</c:otherwise>
                        </c:choose>
                    </a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="inputForm" data-parsley-validate="" modelAttribute="orgRole" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/orgRole/save','${ctx}/sys/orgRole/index');" method="post" class="form-horizontal">
                            <form:hidden path="id"/>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">角色名称：</label>
                                    <div class="col-sm-4">
                                        <form:input path="roleName" htmlEscape="false" class="form-control"  maxlength="30" data-parsley-required="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">备注信息：</label>
                                    <div class="col-sm-4">
                                        <form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgRole/index')">
                                </div>
                            </div>
                        </form:form>
                    </div>

                </div>
            </section>
        </div>
    </div>