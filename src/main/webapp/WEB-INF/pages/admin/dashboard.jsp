<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin DashBoard</title>
</head>
<style>
.main-content {
  margin-left: 250px; 
margin-top:50px;
  padding: 20px;
  overflow-x: auto;	
}

@media screen and (max-width: 910px) {
	.main-content {
		display: flex;
		justify-content: center;
		margin-left: 0px; 
		margin: 100px auto;
	}
}
</style>
<body>
	<%@ include file="/WEB-INF/pages/admin/component/navbar.jsp"%>
	<div class="main-content">
		<jsp:include page="${pageContent}" />
	</div>
	<%@ include file="/WEB-INF/pages/component/footer.jsp"%>
</body>
</html>