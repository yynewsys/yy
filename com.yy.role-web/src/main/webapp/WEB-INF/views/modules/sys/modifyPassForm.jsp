<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script>
    function validRepeat(){
        var old = $.trim($("#oldPassword").val());
        var newPass = $.trim($("#password").val());
        var confirmPass = $.trim($("#confirmPass").val());
        if(old!=null&&old!=''){
            if(newPass!=null&&newPass!=''){
                if(newPass==old){
                    $("#password").val('');
                    toastr.error('新密码不能与旧密码一致，请重新输入...');
                    return false;
                }
                if(confirmPass!=null&&confirmPass!=''){
                    if(confirmPass!=newPass){
                        $("#confirmPass").val('');
                        toastr.error('确认密码与新密码不一致，请重新输入...');
                        return false;
                    }
                }
            }
        }

    }
</script>
<div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="inputForm" data-parsley-validate="" modelAttribute="user" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/user/modifyPwd','${ctx}/sys/sysIndex');" method="post" class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">旧密码：</label>
                                    <div class="col-sm-4">
                                        <input name="oldPassword"  id="oldPassword" type="password" class="form-control"  maxlength="50" data-parsley-required="true" onblur="return validOldPass('oldPassword','${ctx}/sys/user/validOldPass')"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">新密码：</label>
                                    <div class="col-sm-4">
                                        <form:input path="password" id="password" type="password"  class="form-control"  maxlength="50" onblur="return validRepeat()" data-parsley-required="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">确认密码：</label>
                                    <div class="col-sm-4">
                                        <input name="confirmPass" id="confirmPass" type="password"  class="form-control"  maxlength="50" onblur="return validRepeat()" data-parsley-required="true" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                       <%-- <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/personInfo/index')">--%>
                                </div>
                            </div>
                        </form:form>
                    </div>

                </div>
            </section>
        </div>
    </div>