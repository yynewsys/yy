<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script>
    /**
    *加载中间页面
     */
    function loadMainCenterDiv(url,li){
        $("#footerId").attr("class","bg-white-content bl bt");
        $(li).parent().parent().find("li").removeClass("active");
        $(li).parent().addClass("active");
        loadDiv('mainCenterDiv',url);
    }
</script>
<p class="nav-title">功能列表</p>
<ul class="nav">
    <c:choose>
        <c:when test="${fns:getUser().userType=='1'}">
            <li>
                <a href="javascript:;">
                    <i class="toggle-accordion"></i>
                    <i class="ti-layout-media-overlay-alt-2"></i>
                    <span>系统管理</span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="#" onclick="loadMainCenterDiv('${ctx}/sys/service/index',this)">
                            <span>服务管理</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="loadMainCenterDiv('${ctx}/sys/company/index',this)">
                            <span>机构管理</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="loadMainCenterDiv('${ctx}/sys/user/index',this)">
                            <span>用户管理</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="loadMainCenterDiv('${ctx}/sys/sysMenuDict/index',this)">
                            <span>菜单管理</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="loadMainCenterDiv('${ctx}/sys/dict/index',this)">
                            <span>字典管理</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="loadMainCenterDiv('${ctx}/sys/sysOrgDict/index',this)">
                            <span>系统医院字典</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="loadMainCenterDiv('${ctx}/gen/genTable/index',this)">
                            <span>代码生成器</span>
                        </a>
                    </li>
                </ul>
            </li>
        </c:when>
        <c:otherwise>
            <c:forEach items="${sysMenuDictList}" var="menu2">
                <c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1' || menu2.parent.id=='0'}">
                    <li>
                        <a href="#">
                            <i class="toggle-accordion"></i>
                            <i class="ti-layout-media-overlay-alt-2"></i>
                            <span>${menu2.name}</span>
                        </a>
                        <ul class="sub-menu">
                            <c:forEach items="${sysMenuDictList}" var="menu3">
                                <c:if test="${menu3.parent.id eq menu2.id&&menu3.isShow eq '1'}">
                                    <li>
                                        <a href="#" onclick="loadMainCenterDiv('${ctx}${menu3.href}',this)">
                                            <span>${menu3.name}</span>
                                        </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </li>
                </c:if>
            </c:forEach>
        </c:otherwise>
    </c:choose>


</ul>