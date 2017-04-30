<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li>
                    <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgDeptGroup/index')">科室分组列表</a>
                </li>
                <li class="active">
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgDeptGroup/form')">
                        <c:choose>
                            <c:when test="${orgDeptGroup.id==''|| orgDeptGroup.id==null}">
                                科室分组添加
                            </c:when>
                            <c:otherwise>科室分组修改</c:otherwise>
                        </c:choose>
                    </a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="inputForm" data-parsley-validate="" modelAttribute="orgDeptGroup" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/orgDeptGroup/save','${ctx}/sys/orgDeptGroup/index');" method="post" class="form-horizontal">
                            <form:hidden path="id"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">分组名称：</label>
                                    <div class="col-sm-4">
                                        <form:input path="name" data-parsley-required="true" htmlEscape="false" class="form-control"  maxlength="200" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" >排序：</label>
                                    <div class="col-sm-4">
                                        <form:input path="sort" data-parsley-required="true" htmlEscape="false" class="form-control"   placeholder="请输入数字……"  />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">备注信息：</label>
                                    <div class="col-sm-4">
                                        <form:textarea path="remarks" htmlEscape="false" class="form-control" rows="3"  maxlength="500" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgDeptGroup/index')">
                                </div>
                            </div>
                        </form:form>
                    </div>

                </div>
            </section>
        </div>
    </div>