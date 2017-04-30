<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
    <!-- content wrapper -->
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li>
                    <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/company/index')">机构列表</a>
                </li>
                <li class="active">
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/company/form')">
                        <c:choose>
                            <c:when test="${company.id=='' || company.id==null}">
                                机构添加
                            </c:when>
                            <c:otherwise>机构修改</c:otherwise>
                        </c:choose>
                      </a>
                </li>

            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="inputForm" modelAttribute="company" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/company/save','${ctx}/sys/company/index');" method="post" class="form-horizontal">
                            <form:hidden path="id"/>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">上级机构</label>
                                <div class="col-sm-4">
                                    <sys:treeselect id="parentId" name="parentId" value="${company.parent.id}"  update="${not empty company.id}" labelName="${company.parent.id}" labelValue="${company.parent.orgName}"
                                                    title="机构" url="/sys/company/treeData" extId="${company.id}" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">机构名称:</label>
                                <div class="col-sm-4">
                                    <form:input path="orgName" htmlEscape="false" maxlength="50" class="form-control"  data-parsley-required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">机构代码:</label>
                                <div class="col-sm-4">
                                    <form:input path="orgCode" htmlEscape="false" maxlength="50" class="form-control" data-parsley-required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系电话:</label>
                                <div class="col-sm-4">
                                    <form:input path="phone" htmlEscape="false" maxlength="50" class="form-control" data-parsley-required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">地址:</label>
                                <div class="col-sm-4">
                                    <form:input path="address" htmlEscape="false" maxlength="50" class="form-control" data-parsley-required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系人:</label>
                                <div class="col-sm-4">
                                    <form:input path="linkMan" htmlEscape="false" maxlength="11" class="form-control" data-parsley-required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">邮箱:</label>
                                <div class="col-sm-4">
                                    <form:input path="email" htmlEscape="false" maxlength="11" class="form-control" data-parsley-required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注:</label>
                                <div class="col-sm-6">
                                    <form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/company/index')">
                                </div>
                            </div>
                        </form:form>
                    </div>

                </div>
            </section>
        </div>
    </div>