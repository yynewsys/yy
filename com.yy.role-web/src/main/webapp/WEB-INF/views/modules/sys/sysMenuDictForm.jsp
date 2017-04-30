<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li>
                    <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysMenuDict/index')">菜单字典表列表</a>
                </li>
                <li class="active">
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysMenuDict/form')">
                        <c:choose>
                            <c:when test="${sysMenuDict.id==''|| sysMenuDict.id==null}">
                                菜单字典表添加
                            </c:when>
                            <c:otherwise>菜单字典表修改</c:otherwise>
                        </c:choose>
                    </a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="inputForm" data-parsley-validate="" modelAttribute="sysMenuDict" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/sysMenuDict/save','${ctx}/sys/sysMenuDict/index');" method="post" class="form-horizontal">
                            <form:hidden path="id"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">父级菜单：</label>
                                    <div class="col-sm-4">
                                        <sys:treeselect selectedParent="false" id="sysMenuDict" name="parent.id" value="${sysMenuDict.parent.id}"  update="${not empty sysMenuDict.id}" labelName="parent.name" labelValue="${sysMenuDict.parent.name}"
                                                        title="菜单" url="/sys/sysMenuDict/treeData" extId="${sysMenuDict.id}" cssClass="form-control" />

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">菜单名称：</label>
                                    <div class="col-sm-4">
                                        <form:input path="name" data-parsley-required="true" htmlEscape="false" class="form-control"  maxlength="20" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">菜单连接：</label>
                                    <div class="col-sm-4">
                                        <form:input path="href" data-parsley-required="true" htmlEscape="false" class="form-control"  maxlength="100" />
                                    </div>
                                </div>
                              <%--  <div class="form-group">
                                    <label class="col-sm-2 control-label">打开方式：</label>
                                    <div class="col-sm-4">
                                        <form:input path="target" htmlEscape="false" class="form-control"  maxlength="20" />
                                    </div>
                                </div>--%>
                               <%-- <div class="form-group">
                                    <label class="col-sm-2 control-label">菜单图标路径：</label>
                                    <div class="col-sm-4">
                                        <form:input path="icon" htmlEscape="false" class="form-control"  maxlength="50" />
                                    </div>
                                </div>--%>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">是否显示：</label>
                                    <div class="col-sm-4">
                                      <%--  <form:input path="isShow" htmlEscape="false" class="form-control"  maxlength="1" />--%>
                                        <form:radiobutton path="isShow" value="1" checked="true"/>是&nbsp;&nbsp;&nbsp;&nbsp;
                                        <form:radiobutton path="isShow" value="0"/>否
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">菜单排序：</label>
                                    <div class="col-sm-4">
                                        <form:input path="sort" htmlEscape="false" class="form-control"  maxlength="11" />
                                    </div>
                                </div>
                               <%-- <div class="form-group">
                                    <label class="col-sm-2 control-label">权限标识：</label>
                                    <div class="col-sm-4">
                                        <form:input path="permission" htmlEscape="false" class="form-control"  maxlength="1000" />
                                    </div>
                                </div>--%>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">备注信息：</label>
                                    <div class="col-sm-4">
                                        <form:textarea path="remarks" htmlEscape="false" class="form-control"  maxlength="1000" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysMenuDict/index')">
                                </div>
                            </div>
                        </form:form>
                    </div>

                </div>
            </section>
        </div>
    </div>