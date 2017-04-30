<%--
  Created by IntelliJ IDEA.
  User: 99386
  Date: 2017/2/27
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- content wrapper -->
<div class="content-wrap">
  <div class="wrapper" style="bottom: 50px;">
    <ul class="nav nav-tabs">
      <li>
        <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/externalUrl/index')">外部链接列表</a>
      </li>
      <li class="active">
        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/externalUrl/form')">
          <c:choose>
            <c:when test="${extendLink.id=='' || extendLink.id==null}">
              外部链接添加
            </c:when>
            <c:otherwise>外部链接修改</c:otherwise>
          </c:choose>
        </a>
      </li>

    </ul>
    <section class="panel panel-default">
      <div class="panel-body">
        <div class="col-lg-12">
          <form:form id="inputForm" data-parsley-validate="" modelAttribute="externalUrl" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/externalUrl/save','${ctx}/sys/externalUrl/index');" method="post" class="form-horizontal">
            <form:hidden path="id"/>
            <div class="form-group">
              <label class="col-sm-2 control-label">链接名称:</label>
              <div class="col-sm-4">
                <form:input path="name" htmlEscape="false" data-parsley-required="true" maxlength="50"  class="form-control" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">链接地址:</label>
              <div class="col-sm-4">
                <form:input path="url" htmlEscape="false" maxlength="50" data-parsley-required="true" class="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">链接类型:</label>
              <div class="col-sm-4">
                <form:input path="type" htmlEscape="false" maxlength="50" data-parsley-required="true" placeholder="请输入数字……"  class="form-control"/>
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
                <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/externalUrl/index')">
              </div>
            </div>
          </form:form>
        </div>
      </div>
    </section>
  </div>
</div>

