<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="content-wrap">
    <div class="wrapper" style="bottom: 50px;">
        <ul class="nav nav-tabs">
            <li>
                <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgDept/index')">科室列表</a>
            </li>
            <li class="active">
                <a href="#" onclick="loadDiv('rigthCenterDiv','${ctx}/sys/orgDept/form')">
                    <c:choose>
                        <c:when test="${orgDept.id==''|| orgDept.id==null}">
                            科室添加
                        </c:when>
                        <c:otherwise>科室修改</c:otherwise>
                    </c:choose>
                </a>
            </li>
        </ul>
        <section class="panel panel-default">
            <div class="panel-body">
                <div class="col-lg-12">
                    <form:form id="inputForm" data-parsley-validate="" modelAttribute="orgDept" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/orgDept/save','${ctx}/sys/orgDept/index');" method="post" class="form-horizontal">
                        <form:hidden path="id"/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">上级科室：</label>
                            <div class="col-sm-4">
                                <sys:treeselect id="orgDept" selectedParent="false" name="parent.id" value="${orgDept.parent.id}"  update="${not empty orgDept.id}" labelName="${orgDept.parent.id}" labelValue="${orgDept.parent.deptName}"
                                                title="科室" url="/sys/orgDept/treeData" extId="${orgDept.id}" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">科室名称：</label>
                            <div class="col-sm-4">
                                <form:input path="deptName" htmlEscape="false" data-parsley-required="true" class="form-control"  maxlength="100" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">排序：</label>
                            <div class="col-sm-4">
                                <form:input path="sort" htmlEscape="false" data-parsley-type="digits"  class="form-control"  maxlength="3" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">科室代码：</label>
                            <div class="col-sm-4">
                                <form:input path="deptCode" htmlEscape="false"  data-parsley-required="true" class="form-control"  maxlength="20" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">临床标识：</label>
                            <div class="col-sm-4">
                                <form:select path="clinicAttr" class="form-control" >
                                    <form:option value="" label=""/>
                                    <form:options items="${fns:getDictList('DEPT_CLINIC_ATTR_DICT')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">住院门诊：</label>
                            <div class="col-sm-4">
                                <form:select path="outpOrInp" data-parsley-required="true" class="form-control" >
                                    <form:option value="" label=""/>
                                    <form:options items="${fns:getDictList('DEPT_OI_ATTR_DICT')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">内外科标识：</label>
                            <div class="col-sm-4">
                                <form:select path="internalOrSerger" class="form-control" >
                                    <form:option value="" label=""/>
                                    <form:options items="${fns:getDictList('DEPT_IS_ATTR_DICT')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">备注信息：</label>
                            <div class="col-sm-10">
                                <form:textarea path="remarks" htmlEscape="false" class="form-control"  maxlength="500" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/orgDept/index')">
                            </div>
                        </div>
                    </form:form>
                </div>

            </div>
        </section>
    </div>
</div>
