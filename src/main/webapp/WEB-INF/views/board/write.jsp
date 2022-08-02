<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
	<form name="form" method="post" action="${pageContext.request.contextPath}/board/write">
		제목
		<input name="title" placeholder="제목을 입력해주세요"><br>
		글
		<textarea name="content"></textarea><br>
		<button type="submit">작성 완료</button>
	</form>
</body>
</html>