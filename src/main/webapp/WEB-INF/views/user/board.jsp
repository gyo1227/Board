<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>작성 게시글</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<!-- Bootstrap icon -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user.css" type="text/css">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
function changeMode(off, on){
	if(on.css('display') === 'none') {
		on.show();
		off.hide();
	}
}

function cancel(off, on){
	if(off.css('display') === 'none') {
		on.hide();
		off.show();
	}
}
</script>
</head>
<body>
<div id="body">
<%@include file="../includes/header.jsp"%>
	<c:choose>
		<c:when test="${sessionScope.userId != null }">
			<div class="main">
				<div>
					<div class="side">
						<nav class="side-nav">
							<ul class="nav-list">
								<li><a href="${pageContext.request.contextPath}/user/info">회원 정보<i class="bi bi-chevron-compact-right"></i></a></li>
								<li><a class="active" href="${pageContext.request.contextPath}/user/board">작성 게시글<i class="bi bi-chevron-compact-right"></i></a></li>
							</ul>
						</nav>
					</div>
					<div class="user-table">
						<div class="user-title">
							<h3>작성 게시글</h3>
						</div>
						<div style="width: 700px; text-align: center;">작성한 게시글이 없습니다.</div>
						<div class="board-list">
							<table class="table">
								<colgroup>
									<col class="col-6">
									<col class="col-2">
									<col class="col-2">
									<col class="col-2">
								</colgroup>
								<thead>
									<tr>
										<th>제목</th>
										<th>작성일</th>
										<th>조회수</th>
										<th>수정/삭제</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<div class="myModal hidden">
				<div class="bg"></div>
				<div class="modalBox">
					<div class="modalContent"></div>
					<div class="modalFooter">
						<button type="button" id="modalClosed">닫기</button>
					</div>
				</div>
			</div>
			
		</c:when>
		<c:otherwise>
			<script>
				location.href="${pageContext.request.contextPath}/board/list";
			</script>
		</c:otherwise>
	</c:choose>

<%@include file="../includes/footer.jsp"%>
</div>
</body>
</html>