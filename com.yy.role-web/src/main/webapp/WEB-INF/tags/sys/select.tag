<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ attribute name="lists" type="java.util.List" description="数据源" %>
<%@ attribute name="checkValue" type="java.lang.String" description="选中数据" %>
<%@ attribute name="name" type="java.lang.String" description="name" %>
<%@ attribute name="className" type="java.lang.String" description="样式" %>
<select id="${name}" name="${name}" class="${className}">
    <option value="">----请选择----</option>
    <c:forEach items="${lists}" var="dict">
        <c:choose>
            <c:when test="${dict.value==checkValue}">
                <option value="${dict.value}" selected="selected">${dict.label}</option>
            </c:when>
            <c:otherwise>
                <option value="${dict.value}">${dict.label}</option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</select>
