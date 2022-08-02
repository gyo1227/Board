<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
</head>
<body>
	<form name="form" method="post" action="${pageContext.request.contextPath}/board/write">
		제목<br>
		<c:out value="${dto.title }"/><br>
		글<br>
		<c:out value="${dto.content }"/><br>
	</form>
</body>
</html>