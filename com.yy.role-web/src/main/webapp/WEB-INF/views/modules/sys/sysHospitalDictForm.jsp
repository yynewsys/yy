<%--
  Created by IntelliJ IDEA.
  User: 99386
  Date: 2017/2/21
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- content wrapper -->
<div class="content-wrap">
  <div class="wrapper" style="bottom: 50px;">
    <ul class="nav nav-tabs">
      <li>
        <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/hospitalDict/index')">医院字典列表</a>
      </li>
      <li class="active">
        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/hospitalDict/form')">
          <c:choose>
            <c:when test="${hospitalDict.id=='' || hospitalDict.id==null}">
              医院字典添加
            </c:when>
            <c:otherwise>医院字典修改</c:otherwise>
          </c:choose>
        </a>
      </li>

    </ul>
    <section class="panel panel-default">
      <div class="panel-body">
        <div class="col-lg-12">
          <form:form id="inputForm" data-parsley-validate="" modelAttribute="hospitalDict" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/hospitalDict/save','${ctx}/sys/hospitalDict/index');" method="get" class="form-horizontal">
            <form:hidden path="id"/>
            <div class="form-group">
              <label class="col-sm-2 control-label">键值:</label>
              <div class="col-sm-4">
                <form:input path="value" htmlEscape="false" data-parsley-required="true" maxlength="50"  class="form-control" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">标签:</label>
              <div class="col-sm-4">
                <form:input path="label" htmlEscape="false" maxlength="50" data-parsley-required="true" class="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">类型:</label>
              <div class="col-sm-4">
                <form:input path="type" htmlEscape="false" maxlength="50" data-parsley-required="true" class="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">描述:</label>
              <div class="col-sm-4">
                <form:input path="description" htmlEscape="false" maxlength="50" class="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">排序:</label>
              <div class="col-sm-4">
                <form:input path="sort" htmlEscape="false"  data-parsley-required="true" class="form-control"  placeholder="请输入数字……" />
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
                <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/hospitalDict/index')">
              </div>
            </div>
          </form:form>
        </div>

      </div>
    </section>
  </div>
</div>
