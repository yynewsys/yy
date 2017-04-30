<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script>
    /*选择价表自动补全*/
    autoCompleteClinicItem("itemNameId");
    function autoCompleteClinicItem(inputId){
        var url="${ctx}/oper/clinicItemDict/automatic";
        autoComplete(inputId,url,
            function(data){
                var rows = [];
                for(var i=0; i<data.length; i++){
                    rows[rows.length] = {
                        data:data[i],
                        value:data[i].id,
                        result:data[i].itemName
                    };
                }
                return rows;
            },function(data, i, max){
                return data.inputCode+"|"+data.itemName+"|"+data.itemCode;
            },function(event,data,formatted){
                $('#clinicId').attr('value',data.id);
                $('#itemCode').attr('value',data.itemCode);
            })
    }
</script>
<div class="content-wrap">
    <div class="wrapper" style="bottom: 50px;">
        <ul class="nav nav-tabs">
            <li>
                <a href="#"  onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysOrgDict/index')">系统平台字典列表</a>
            </li>
            <li class="active">
                <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysOrgDict/dictVsClinicForm')">
                    添加对照
                </a>
            </li>
        </ul>
        <section class="panel panel-default">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-7">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form:form  modelAttribute="sysOrgDict" class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">标签：</label>
                                            <div class="col-sm-4">
                                                <input name="label" readonly class="form-control"  maxlength="50" value="${sysOrgDict.label}"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">类型：</label>
                                            <div class="col-sm-4">
                                                <input name="type" readonly class="form-control"  maxlength="200" value="${sysOrgDict.type}"/>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12" >
                                    <table id="contentTable" class="table table-bordered mg-t datatable">
                                        <thead>
                                        <tr>
                                            <th>诊疗类别</th>
                                            <th>诊疗代码</th>
                                            <th>项目名称</th>
                                            <th>输入码</th>
                                            <th>执行科室</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${list}" var="baseDto">
                                        <tr>
                                            <td> <c:if test="${baseDto.item_class!='A' and baseDto.item_class!='B'}">非药品</c:if><c:if test="${baseDto.item_class=='A' and baseDto.item_class=='B'}">药品</c:if>  </td>
                                            <td> ${baseDto.item_code}</td>
                                            <td> ${baseDto.item_name}</td>
                                            <td> ${baseDto.input_code}</td>
                                            <td> ${baseDto.dept_name}</td>
                                            <td>
                                                <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该对照项目吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/dictVsClinic/delete?dictId=${baseDto.dict_id}&clinicId=${baseDto.clinic_id}','${ctx}/sys/sysOrgDict/dictVsClinicForm?id=${baseDto.dict_id}')})">删除</a>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5" >
                        <form:form id="listInputForm" data-parsley-validate="" modelAttribute="dictVsClinic" onsubmit="return formSaveLoad('mainCenterDiv','listInputForm','${ctx}/sys/dictVsClinic/save','${ctx}/sys/sysOrgDict/dictVsClinicForm?id=${dictVsClinic.dictId}');" method="post" class="form-horizontal">

                            <input type="hidden" name="clinicId" id="clinicId" value="">
                            <input type="hidden" name="dictId" id="dictId" value="${dictVsClinic.dictId}">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目名称：</label>
                                <div class="col-sm-4">
                                    <input name="itemName" id="itemNameId" class="form-control"  maxlength="50" data-parsley-required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目代码：</label>
                                <div class="col-sm-4">
                                    <input name="itemCode" id="itemCode" readonly class="form-control"  maxlength="50" data-parsley-required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">执行科室：</label>
                                <div class="col-sm-4">
                                    <sys:treeselect id="orgDeptId" name="executiveDept.id" value="${executiveDept.id}" isnull="true"  update="${not empty executiveDept.id}" labelName="${executiveDept.deptName}" labelValue="${executiveDept.deptName}"
                                                    title="科室" url="/sys/orgDept/treeData"  cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
                                    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="loadDiv('mainCenterDiv','${ctx}/sys/sysOrgDict/index')">
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
