<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
</script>
    <div class="content-wrap">
        <div class="wrapper" style="bottom: 50px;">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#"   onclick="loadDiv('mainCenterDiv','${ctx}/sys/performFreqDict/index')">频次字典列表</a>
                </li>
                <li>
                    <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/performFreqDict/form')">频次字典添加</a>
                </li>
            </ul>
            <section class="panel panel-default">
                <div class="panel-body">
                    <div class="table-responsive no-border">
                        <div class="datatable-top">
                            <div class="pull-left">
                            	<form:form id="searchForm" modelAttribute="performFreqDict"  onsubmit="return loadDivForm('mainCenterDiv','searchForm','${ctx}/sys/performFreqDict/index');" method="post" class="form-inline">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <label>频率描述 ：</label><form:input path="freqDesc" htmlEscape="false" maxlength="50" class="form-control" /> &nbsp;
                                    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
                                </form:form>
                            </div>
                        </div>
                        <table id="contentTable" class="table table-bordered table-striped mg-t datatable">
                            <thead>
                                <tr>
                                        <th>执行频率描述</th>
                                        <th>频率次数</th>
                                        <th>频次间隔</th>
                                        <th>频次单位</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="performFreqDict">
                                <tr>
                                        <td> ${performFreqDict.freqDesc}</td>
                                        <td> ${performFreqDict.freqCounter}</td>
                                        <td> ${performFreqDict.freqInterval}</td>
                                        <td> ${performFreqDict.freqIntervalUnit}</td>

                                    <td>
                                        <a href="#" onclick="loadDiv('mainCenterDiv','${ctx}/sys/performFreqDict/form?id=${performFreqDict.id}')" class="btn btn-success btn-xs">修改</a>
                                        <a href="#"  class="applyBt btn btn-danger btn-xs" onclick="confirmExtend('确认要删除该频次字典吗？',function(){delLoad('mainCenterDiv','${ctx}/sys/performFreqDict/delete?id=${performFreqDict.id}','${ctx}/sys/dict/index')})">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                        <div class="pagination">${page}</div>
                    </div>
                </div>
            </section>
        </div>
    </div>