<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시글 리스트</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<!-- Bootstrap icon -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css">
</head>
<body>
	<header class="p-3 mb-3 border-bottom">
		<div class="container left">
			<div class="dropdown text-end">
				<a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
					<i class="bi bi-person-circle"></i>
				</a>
				<ul class="dropdown-menu text-small">
					<li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/login">로그인</a></li>
					<li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
				</ul>
			</div>
		</div>
	</header>
	<div class="container">
	
		<div class="board-list">
			<table class="table">
				<colgroup>
					<col class="col-1">
					<col class="col-8">
					<col class="col-1">
					<col class="col-1">
					<col class="col-1">
				</colgroup>
				<thead>
					<tr class="text-center">
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="list">
						<tr>
							<td class="text-center">
								<c:out value="${list.boardNum}"/>
							</td>			
							<td>
								<a href='${pageContext.request.contextPath}/board/view/<c:out value="${list.boardNum }"/>'><c:out value="${list.title}"/><span class="reply"> [<c:out value="${list.replyCnt}" />]</span></a>
							</td>			
							<td class="text-center"><c:out value="${list.nickName}"/></td>	
		 					<td class="text-center">
		 					<fmt:parseDate value="${list.regDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
		 					<fmt:formatDate pattern="HH:mm" value="${parsedDateTime }"/>
		 						<%-- <fmt:formatDate value="${list.regDate }"/> --%>
	 						</td>		
							<td class="text-center"><c:out value="${list.viewCnt}"/></td>		
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="container board-bottom">
		<div class="left">
			<a href="${pageContext.request.contextPath}/board/write" class="btn btn-dark">글쓰기</a>
		</div>
		
		<form action="#" method="get" class="search">
			<select name="name">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="nickName">닉네임</option>
				<option value="subjcont">제목+내용</option>
			</select>
			<input name="">
			<button type="submit" class="btn search-btn"><i class="bi bi-search"></i></button>
			
		</form>
	</div>
</body>
</html>