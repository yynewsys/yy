<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
    <!-- content wrapper -->
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li>
                    <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/service/index')">服务列表</a>
                </li>
                <li class="active">
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/service/form')">
                        <c:choose>
                            <c:when test="${sysService.id=='' || sysService.id==null}">
                                服务添加
                            </c:when>
                            <c:otherwise>服务修改</c:otherwise>
                        </c:choose>
                    </a>
                </li>

            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="inputForm" data-parsley-validate="" modelAttribute="sysService" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/service/save','${ctx}/sys/service/index');" method="post" class="form-horizontal">
                            <form:hidden path="id"/>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">服务名称:</label>
                                <div class="col-sm-4">
                                    <form:input path="serverName" data-parsley-required="true" htmlEscape="false" maxlength="50" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">服务类型:</label>
                                <div class="col-sm-4">
                                    <select name="serverType" class="form-control" data-parsley-required="true">
                                        <c:forEach items="${fns:getDictList('service_type')}" var="dict">
                                            <c:choose>
                                                <c:when test="${dict.value==sysService.serverType}">
                                                   <option value="${dict.value}" selected="selected">${dict.label}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${dict.value}">${dict.label}</option>
                                                </c:otherwise>
                                            </c:choose>


                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">服务类别:</label>
                                <div class="col-sm-4">
                                    <%--<form:select path="serverClass" items="${fns:getDictList('service_class')}" class="form-control"></form:select>--%>
                                    <select name="serverClass" class="form-control" data-parsley-required="true">
                                        <c:forEach items="${fns:getDictList('service_class')}" var="dict">
                                            <c:choose>
                                                <c:when test="${dict.value==sysService.serverClass}">
                                                    <option value="${dict.value}" selected="selected">${dict.label}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${dict.value}">${dict.label}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                           <%-- <div class="form-group">
                                <label class="col-sm-2 control-label">服务图片路径:</label>
                                <div class="col-sm-4">
                                    <form:input path="serviceImage" htmlEscape="false" maxlength="50" class="form-control"/>
                                </div>
                            </div>--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">描述:</label>
                                <div class="col-sm-4">
                                    <form:textarea path="description" htmlEscape="false" maxlength="50" class="form-control"></form:textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/service/index')">
                                </div>
                            </div>
                        </form:form>
                    </div>

                </div>
            </section>
        </div>
    </div>