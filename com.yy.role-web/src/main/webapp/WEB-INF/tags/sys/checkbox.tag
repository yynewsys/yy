<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ attribute name="lists" type="java.util.List" description="数据源" %>
<%@ attribute name="checkValue" type="java.lang.String" description="选中数据" %>
<%@ attribute name="name" type="java.lang.String" description="name" %>
<%@ attribute name="typeText" type="java.lang.String" description="判断是checkbox还是radio" %>
<c:forEach items="${lists}" var="o">
    <c:choose>
        <c:when test="${typeText==1}">
            <input type="radio" onmousedown="cancelRadioCheck(this,'0')" onclick="cancelRadioCheck(this,'1')" value="${o.value}" name="${name}"/>${o.label}
        </c:when>
        <c:when test="${typeText==2}">
            <td><input type="checkbox" value="${o.value}" name="${name}"/></td>
        </c:when>
        <c:otherwise>
            <input type="checkbox" value="${o.value}" name="${name}"/>${o.label}
        </c:otherwise>
    </c:choose>
</c:forEach>
<script>
    var checkValue = "," + '${checkValue}' + ",";
    $("input[name=${name}]").each(function () {
        if (checkValue.indexOf(',' + $(this).val() + ',') >= 0) {
            $(this).attr("checked", 'true');
        }
    });
</script>
