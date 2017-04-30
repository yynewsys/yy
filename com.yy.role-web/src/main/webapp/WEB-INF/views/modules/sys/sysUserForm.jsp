<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class="content-wrap">
    <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li>
                    <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/user/index')">用户列表</a>
                </li>
                <li class="active">
                    <a href="#" onclick="loadDiv('rigthCenterDiv','${ctx}/sys/user/form')">
                        <c:choose>
                            <c:when test="${user.id=='' || user.id==null}">
                                用户添加
                            </c:when>
                            <c:otherwise>用户修改</c:otherwise>
                        </c:choose>
                    </a>
                </li>
            </ul>
            <section class="panel">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="userForm" data-parsley-validate="" modelAttribute="user" onsubmit="return formSaveLoad('mainCenterDiv','userForm','${ctx}/sys/user/save','${ctx}/sys/user/index');" method="post" class="form-horizontal">
                            <form:hidden path="id" />
                            <form:hidden path="persionId" id="personId"/>
                            <c:if test="${currentUser.userType==1 || currentUser.userType=='1'}">
                            <div class="form-group" id="form-group">
                                <label class="col-sm-2 control-label">所属机构:</label>
                                <div class="col-sm-4">
                                    <sys:treeselect selectedParent="false" id="parentId" name="company.id" value="${user.company.parent.id}"  update="${not empty company.id}" labelName="${user.company.id}" labelValue="${user.company.orgName}"
                                                    title="机构" url="/sys/company/treeData" extId="${company.id}" cssClass="form-control" />
                                </div>
                            </div>
                            </c:if>
                            <c:if test="${currentUser.userType!=1 && currentUser.userType!='1'}">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">所属科室:</label>
                                    <div class="col-sm-4">
                                        <sys:treeselect selectedParent="true" id="orgDeptId" name="orgDept.id" value="${user.orgDept.id}"  update="${not empty orgDept.id}" labelName="${user.orgDept.deptName}" labelValue="${user.orgDept.deptName}"
                                                        title="科室" url="/sys/orgDept/treeData" extId="${orgDept.id}" cssClass="form-control" isnull="true"/>
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">登录名:</label>
                                <div class="col-sm-4">
                                    <form:input path="currentLogin" htmlEscape="false" maxlength="50" class="form-control" data-parsley-required="true" onblur="return validPersonInfoUnique('currentLogin','currentLogin','personId','${ctx}/sys/sysLoginName/validateUnique');"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">用户类型:</label>
                                <div class="col-sm-4">
                                    <form:select path="userType" id="userType" class="form-control" data-parsley-required="true" >
                                        <form:option value="" label=""/>
                                        <c:choose>
                                            <c:when test="${currentUser.userType==1 || currentUser.userType=='1'}">
                                                <form:option value="1" label="系统管理员"/>
                                                <form:option value="2" label="机构管理员"/>
                                            </c:when>
                                            <c:when test="${currentUser.userType==2 || currentUser.userType=='2'}">
                                                <form:option value="2" label="机构管理员"/>
                                                <form:option value="3" label="一般人员"/>
                                            </c:when>
                                            <c:otherwise>
                                                <form:option value="3" label="一般人员"/>
                                            </c:otherwise>
                                        </c:choose>
                                       <%-- <form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                                    </form:select>
                                </div>
                            </div>
                            <c:if test="${currentUser.userType!=1 && currentUser.userType!='1'}">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">职称:</label>
                                    <div class="col-sm-4">
                                        <form:select path="title" class="form-control" data-parsley-required="true">
                                            <form:option value="" label=""/>
                                            <form:options items="${fns:getDictList('DOCTOR_TITLE_DICT')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                        </form:select>
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名:</label>
                                <div class="col-sm-4">
                                    <form:input path="name" htmlEscape="false" maxlength="50" class="form-control" data-parsley-required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号:</label>
                                <div class="col-sm-4">
                                    <form:input path="idCard" id="idCard" htmlEscape="false" maxlength="50" class="form-control"  data-parsley-required="true" data-parsley-checkidcard="3"  onblur="return validPersonInfoUnique('idCard','idCard','personId','${ctx}/sys/sysLoginName/validateUnique');"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">手机:</label>
                                <div class="col-sm-4">
                                    <form:input path="phone" id="phone" htmlEscape="false" maxlength="50" class="form-control"  data-parsley-required="true" data-parsley-mobilephone="3" onblur="return validPersonInfoUnique('phone','phone','personId','${ctx}/sys/sysLoginName/validateUnique');"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">邮箱:</label>
                                <div class="col-sm-4">
                                    <form:input path="email" id="email" htmlEscape="false" maxlength="50" class="form-control" data-parsley-type="email" data-parsley-required="true" data-parsley-trigger="change" onblur="return validPersonInfoUnique('email','email','personId','${ctx}/sys/sysLoginName/validateUnique');"/>
                                </div>
                            </div>
                            <c:if test="${user.id=='' || user.id==null}">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">默认密码:</label>
                                <div class="col-sm-4">
                                    <form:input name="password" type="password" path="password"  value="123456" maxlength="11" class="form-control" data-parsley-required="true" readonly="true"/>
                                </div>
                                <span class="help-inline">默认密码:123456</span>
                            </div>
                            </c:if>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/user/index')">
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </section>
    </div>
</div>
