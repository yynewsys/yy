<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li>
                    <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfServiceVsMenu/index')">机构自定义服务菜单对照表列表</a>
                </li>
                <li class="active">
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfServiceVsMenu/form')">
                        <c:choose>
                            <c:when test="${orgSelfServiceVsMenu.id==''|| orgSelfServiceVsMenu.id==null}">
                                机构自定义服务菜单对照表添加
                            </c:when>
                            <c:otherwise>机构自定义服务菜单对照表修改</c:otherwise>
                        </c:choose>
                    </a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="inputForm" data-parsley-validate="" modelAttribute="orgSelfServiceVsMenu" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/orgSelfServiceVsMenu/save','${ctx}/sys/orgSelfServiceVsMenu/index');" method="post" class="form-horizontal">
                            <form:hidden path="id"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">自定义服务ID：</label>
                                    <div class="col-sm-4">
                                        <form:input path="selfServiceId" htmlEscape="false" class="form-control"  maxlength="64" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">菜单Id：</label>
                                    <div class="col-sm-4">
                                        <form:input path="menuId" htmlEscape="false" class="form-control"  maxlength="64" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfServiceVsMenu/index')">
                                </div>
                            </div>
                        </form:form>
                    </div>

                </div>
            </section>
        </div>
    </div>