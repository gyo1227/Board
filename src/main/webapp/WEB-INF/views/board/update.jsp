<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${boardDTO.title } - 수정</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<!-- Bootstrap icon -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" type="text/css">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<script type="text/javascript">
function update(){
	if(confirm('수정 하시겠습니까?')) {
		return true;
	}
	return false;
}
function cancel(){
	if(confirm('취소 하시겠습니까?')) {
		location.href="${pageContext.request.contextPath}/board/view/${boardDTO.boardNum}"
	}
	return false;
}
</script>
<body>
<div id="body">
<%@include file="../includes/header.jsp"%>
	<c:choose>
		<c:when test="${sessionScope.userId == boardDTO.userId }">
			<div class="form">
				<form class="write-form" name="write-form" method="post" action="${pageContext.request.contextPath}/board/update/${boardDTO.boardNum}">
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="title" name="title" placeholder="게시글 제목" value="${boardDTO.title }"/>
						<label for="title">게시글 제목</label>
					</div>
					<div class="form-floating mb-3">
						<textarea class="form-control" id="content" name="content" placeholder="내용을 입력해 주세요.">${boardDTO.content }</textarea>
						<label for="content">게시글 작성</label>
					</div>
					<div class="left">
						<button type="submit" class="btn btn-md btn-primary form-floating mb-2" onclick="return update()">수정하기</button>
						<button type="button" class="btn btn-md btn-primary btn-right form-floating mb-2" onclick="return cancel()">취소하기</button>
					</div>	
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<script>
				alert('로그인이 필요합니다.');
				location.href="${pageContext.request.contextPath}/user/login";
			</script>
		</c:otherwise>
	</c:choose>
	
<%@include file="../includes/footer.jsp"%>
</div>
</body>
</html>