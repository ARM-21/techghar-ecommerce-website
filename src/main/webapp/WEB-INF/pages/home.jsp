<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TechGhar Ecommerce Website</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
</head>

<body>
<%@ include file="/WEB-INF/pages/component/header.jsp" %>
 <div class="container">
      <!-- Page content -->
        <div class="content">
            <jsp:include page="${pageContent}" />
        </div>
    </div>
<%@ include file="/WEB-INF/pages/component/footer.jsp" %>
</body>
</html>