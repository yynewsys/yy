<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="inputForm" data-parsley-validate="" modelAttribute="personInfo" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/personInfo/save','${ctx}/sys/sysIndex');" method="post" class="form-horizontal">
                            <form:hidden path="id" id="personId"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">姓名：</label>
                                    <div class="col-sm-4">
                                        <form:input path="name" htmlEscape="false" class="form-control"  maxlength="50" data-parsley-required="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">身份证号：</label>
                                    <div class="col-sm-4">
                                        <form:input path="idCard" htmlEscape="false" class="form-control"  maxlength="50" data-parsley-required="true" data-parsley-checkidcard="3" onblur="return validPersonInfoUnique('idCard','idCard','personId');"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">联系电话：</label>
                                    <div class="col-sm-4">
                                        <form:input path="phone" htmlEscape="false" class="form-control"  maxlength="20" data-parsley-required="true" data-parsley-mobilephone="3" onblur="return validPersonInfoUnique('phone','phone','personId');"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮箱：</label>
                                    <div class="col-sm-4">
                                        <form:input path="email" htmlEscape="false" class="form-control"  maxlength="30" data-parsley-type="email" data-parsley-required="true" data-parsley-trigger="change" onblur="return validPersonInfoUnique('email','email','personId');"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">昵称：</label>
                                    <div class="col-sm-4">
                                        <form:input path="nickName" htmlEscape="false" class="form-control"  maxlength="30" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">备注信息：</label>
                                    <div class="col-sm-4">
                                        <form:textarea path="remarks" htmlEscape="false" class="form-control"  maxlength="500" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                        <%--<input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/personInfo/index')">--%>
                                </div>
                            </div>
                        </form:form>
                    </div>

                </div>
            </section>
        </div>
    </div>