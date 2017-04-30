<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>极目云医</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<div class="app">
    <!-- top header -->
    <header class="header header-fixed navbar" id="headerId">
        <div class="brand">
            <!-- toggle offscreen menu -->
            <a href="javascript:;" class="ti-menu off-left visible-xs" data-toggle="offscreen" data-move="ltr"></a>
            <!-- /toggle offscreen menu -->
            <!-- logo -->
            <a href="javascript:;" class="navbar-brand">
                <img src="${ctxStatic}/images/logo.png">
                <span><img src="${ctxStatic}/images/logo-01.png"></span>
            </a>
            <!-- /logo -->
        </div>

        <ul class="nav navbar-nav">
            <li class="hidden-xs">
                <!-- toggle small menu -->
                <a href="javascript:;" class="toggle-sidebar">
                    <i class="ti-menu"></i>
                </a>
                <!-- /toggle small menu -->
            </li>

        </ul>

        <ul class="nav navbar-nav navbar-right" >
            <li class="dropdown hidden-xs">
                <a href="javascript:;" data-toggle="dropdown" >
                    ${fns:getUser().orgDept.deptName}<span class="caret"></span>
                </a>
                <div class="dropdown-menu">
                    <div class="ztree" id="roleDept">
                    </div>
                </div>

            </li>
            <li class="dropdown hidden-xs">
                <a href="javascript:;" data-toggle="dropdown">
                   切换服务(<span id="currentServiceDiv" style="color:#DAF546;font-size: 14px;"></span>)
                </a>
                <ul class="dropdown-menu animated zoomIn switch" id="serviceUl">
                    <c:forEach items="${fns:getUser().orgSelfServiceList}" var="OrgSelfService">
                        <li>
                            <a href="javascript:;" onclick="serviceChange('${OrgSelfService.id}','${OrgSelfService.serviceName}')">${OrgSelfService.serviceName}</a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            <li class="off-right">
                <a href="javascript:;" data-toggle="dropdown">
                    <img src="${ctxStatic}/images/avatar.jpg" class="header-avatar img-circle" alt="user" title="user">
                    <span class="hidden-xs ml10">${fns:getUser().name}</span>
                    <i class="ti-angle-down ti-caret hidden-xs"></i>
                </a>
                <ul class="dropdown-menu switch">
                    <li>
                        <a href="javascript:;" onclick="loadDiv('mainCenterDiv','${ctx}/sys/personInfo/form')">个人设置</a>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="loadDiv('mainCenterDiv','${ctx}/sys/user/modifyPassForm')">修改密码</a>
                    </li>
                </ul>
            </li>
            <li class="off-right">
                <a href="${ctx}/logout">
                    <i class="ti-power-off"></i>
                </a>
            </li>
        </ul>
    </header>
    <!-- /top header -->

    <section class="layout">
        <!-- sidebar menu -->
        <aside class="sidebar offscreen-left">
            <!-- main navigation -->
            <nav class="main-navigation" id="treeMenuDiv" data-height="auto" data-size="6px" data-distance="0" data-rail-visible="true" data-wheel-step="10">

            </nav>

        </aside>
        <!-- /sidebar menu -->

        <!-- main content -->
        <section class="main-content">

            <!-- content wrapper -->
            <div class="content-wrap">

                <!-- inner content wrapper -->
                <div class="wrapper wrapper-img" id="mainCenterDiv">


                </div>
                <section class="main-content">
                <footer class="bg-white" id="footerId">
                    <p>© 北京极目云健康科技有限公司 2016</p>
                </footer>
                    </section>
                <!-- /inner content wrapper -->
            </div>
            <!-- /content wrapper -->
            <a class="exit-offscreen"></a>
        </section>
        <!-- /main content -->
    </section>
</div>
<!-- alpha div -->
<div id="maskLayer" style="display:none">
    <iframe id="maskLayer_iframe" frameBorder=0 scrolling=no style="filter:alpha(opacity=50)"></iframe>
    <div id="alphadiv" style="filter:alpha(opacity=50);-moz-opacity:0.5;opacity:0.5"></div>
    <div id="drag">
        <h3 id="drag_h"></h3>
        <div id="drag_con"></div><!-- drag_con end -->
    </div>
</div><!-- maskLayer end -->
</div>
<!-- alpha div end -->
<div id="sublist" style="display:none"></div>

</body>
</html>
