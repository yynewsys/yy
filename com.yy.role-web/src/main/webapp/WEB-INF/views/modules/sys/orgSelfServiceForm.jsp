<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li>
                    <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfService/index')">自定义服务列表</a>
                </li>
                <li class="active">
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfService/form')">
                        <c:choose>
                            <c:when test="${orgSelfService.id==''|| orgSelfService.id==null}">
                                自定义服务添加
                            </c:when>
                            <c:otherwise>自定义服务修改</c:otherwise>
                        </c:choose>
                    </a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="inputForm" data-parsley-validate="" modelAttribute="orgSelfService" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/orgSelfService/save','${ctx}/sys/orgSelfService/index');" method="post" class="form-horizontal">
                            <form:hidden path="id"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">自定义服务名称：</label>
                                    <div class="col-sm-4">
                                        <form:input path="serviceName" data-parsley-required="true"  htmlEscape="false" class="form-control"  maxlength="50" />
                                    </div>
                                </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">排序：</label>
                                <div class="col-sm-4">
                                    <form:input path="sort" data-parsley-required="true"  htmlEscape="false" class="form-control"  maxlength="50" />
                                </div>
                            </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgSelfService/index')">
                                </div>
                            </div>
                        </form:form>
                    </div>

                </div>
            </section>
        </div>
    </div>