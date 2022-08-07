<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<!-- Bootstrap icon -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">

<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user.css" type="text/css">
</head>
<body>
<div id="body">
<%@include file="../includes/header.jsp"%>
	<div class="form">
		<form class="user-form" name="form" method="post" action="${pageContext.request.contextPath}/board/write">
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="userId" name="userId" placeholder="아이디를 입력하세요"/>
				<label for="userId">아이디</label>
			</div>
			<div class="form-floating mb-3">
				<input type="password" class="form-control" id="userPw" name="userPw" placeholder="아이디를 입력하세요"/>
				<label for="userPw">비밀번호</label>
			</div>		
			<button type="button" class="w-100 btn btn-lg btn-primary form-floating mb-2">회원가입</button>
		</form>
	</div>
<%@include file="../includes/footer.jsp"%>
</div>
</body>
</html>