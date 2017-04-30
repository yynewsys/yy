<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li>
                    <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/performFreqDict/index')">频次字典列表</a>
                </li>
                <li class="active">
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/performFreqDict/form')">
                        <c:choose>
                            <c:when test="${performFreqDict.id==''|| performFreqDict.id==null}">
                                频次字典添加
                            </c:when>
                            <c:otherwise>频次字典修改</c:otherwise>
                        </c:choose>
                    </a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <form:form id="inputForm" data-parsley-validate="" modelAttribute="performFreqDict" onsubmit="return formSaveLoad('mainCenterDiv','inputForm','${ctx}/sys/performFreqDict/save','${ctx}/sys/performFreqDict/index');" method="post" class="form-horizontal">
                            <form:hidden path="id"/>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">执行频率描述：</label>
                                    <div class="col-sm-4">
                                        <form:input path="freqDesc" htmlEscape="false" data-parsley-required="true" class="form-control"  maxlength="20" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">频率次数：</label>
                                    <div class="col-sm-4">
                                        <form:input path="freqCounter" data-parsley-required="true" htmlEscape="false" class="form-control"  />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">频次间隔：</label>
                                    <div class="col-sm-4">
                                        <form:input path="freqInterval" data-parsley-required="true" htmlEscape="false" class="form-control"  />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">频次单位：</label>
                                    <div class="col-sm-4">
                                        <form:input path="freqIntervalUnit" data-parsley-required="true" htmlEscape="false" class="form-control"  maxlength="20" />
                                    </div>
                                </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/performFreqDict/index')">
                                </div>
                            </div>
                        </form:form>
                    </div>

                </div>
            </section>
        </div>
    </div>