<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:forEach items="${requestScope.nodes}" var="node">
		<c:choose>
			<c:when test="${node.rootNode == true}">
				<shiro:hasPermission name="${node.permission.permissionUrl}">
				<li>
					<a href="javascript:;"> 
						<i class="fa fa-cogs"></i> 
						<span class="title">${node.name}</span>
						<span class="selected"></span>
						<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<c:set var="nodes" value="${node.children}" scope="request" />
						<jsp:include page="sidebar-menu.jsp" />
					</ul>
				</li>
				</shiro:hasPermission>
			</c:when>
			<c:when test="${node.children.size() == 0 && node.rootNode == false}">
				<shiro:hasPermission name="${node.permission.permissionUrl}">
					<li>
						<a class="ajaxify" href="${ctx}${node.url}">${node.name}</a>
					</li>
				</shiro:hasPermission>
			</c:when>
			<c:otherwise>
				<ul class="sub-menu">
					<c:set var="nodes" value="${node.children}" scope="request" />
					<jsp:include page="sidebar-menu.jsp" />
				</ul>
			</c:otherwise>
		</c:choose>
</c:forEach>