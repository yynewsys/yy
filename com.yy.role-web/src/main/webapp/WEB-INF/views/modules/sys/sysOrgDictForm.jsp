<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li>
                    <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysOrgDict/index')">系统平台字典列表</a>
                </li>
                <li class="active">
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysOrgDict/form')">
                        <c:choose>
                            <c:when test="${sysOrgDict.id==''|| sysOrgDict.id==null}">
                                系统平台字典添加
                            </c:when>
                            <c:otherwise>系统平台字典修改</c:otherwise>
                        </c:choose>
                    </a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="inputForm" data-parsley-validate="" modelAttribute="sysOrgDict" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/sysOrgDict/save','${ctx}/sys/sysOrgDict/index');" method="post" class="form-horizontal">
                            <form:hidden path="id"/>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">标签：</label>
                                    <div class="col-sm-4">
                                        <form:input path="label" htmlEscape="false" class="form-control"  maxlength="200" data-parsley-required="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">类型：</label>
                                    <div class="col-sm-4">
                                        <form:input path="type" htmlEscape="false" class="form-control"  maxlength="200" data-parsley-required="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">值：</label>
                                    <div class="col-sm-4">
                                        <form:input path="value" htmlEscape="false" class="form-control"  maxlength="200" data-parsley-required="true" data-parsley-type="number"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">排序：</label>
                                    <div class="col-sm-4">
                                        <form:input path="sort" htmlEscape="false" class="form-control"  maxlength="11" data-parsley-required="true" data-parsley-type="number"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">备注信息：</label>
                                    <div class="col-sm-4">
                                        <form:textarea path="remarks" htmlEscape="false" class="form-control"  maxlength="1000" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysOrgDict/index')">
                                </div>
                            </div>
                        </form:form>
                    </div>

                </div>
            </section>
        </div>
    </div>