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
<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" type="text/css">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<%@include file="../includes/header.jsp"%>
	<div class="container">
	
		<div class="board-list">
			<table class="table">
				<colgroup>
					<col class="col-1">
					<col class="col-6">
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
					<c:choose>
						<c:when test="${list != null }">
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
				 						<fmt:formatDate value="${parsedDateTime }" pattern="MM-dd" />
			 						</td>		
									<td class="text-center"><c:out value="${list.viewCnt}"/></td>		
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<td id="nolist" colspan="5">작성된 게시글이 없습니다.</td>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<div class="container board-bottom">
		<div class="left">
			<a href="${pageContext.request.contextPath}/board/write" class="btn btn-dark">글쓰기</a>
		</div>
		
		<form action="${pageContext.request.contextPath}/board/list" method="get" class="search">
			<div class="select-list">
				<select class="form-select form-select-sm" id="option" name="option">
					<option value="title" selected>제목</option>
					<option value="content">내용</option>
					<option value="nickName">닉네임</option>
					<option value="titleContent">제목+내용</option>
				</select>
				<input id="query" name="query">
				<button type="submit" class="btn search-btn" id="select-btn"><i class="bi bi-search"></i></button>
			</div>
		</form>
		<c:if test="${pageDTO.option != '' }">
			<script>
				if('${pageDTO.option}' == 'title' || '${pageDTO.option}' == 'content' || 
					'${pageDTO.option}' == 'nickName' || '${pageDTO.option}' == 'titleContent') {
						$('#option').val('${pageDTO.option}').prop('selected', true)
				}
				$('#query').val('${pageDTO.query}')
			</script>
		</c:if>
		<nav id="pagination" aria-label="Page navigation example">
			<c:if test="${total != 0}">
				<ul class="pagination justify-content-center">
					<li class="page-item ${pageDTO.prev == false ? 'disabled': '' }">
						<a class="page-link" href="list?p=${pageDTO.startPage-10 }${pageDTO.option != '' ? '&option=' : ''}${pageDTO.option != '' ? pageDTO.option : ''}${pageDTO.query != '' ? '&query=' : ''}${pageDTO.query != '' ? pageDTO.query : ''}" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
					<c:forEach var="num" begin="${pageDTO.startPage }" end="${pageDTO.endPage }">
						<li class="page-item"><a class="page-link ${pageDTO.curPage == num ? 'active': '' }" href="list?p=${num }${pageDTO.option != '' ? '&option=' : ''}${pageDTO.option != '' ? pageDTO.option : ''}${pageDTO.query != '' ? '&query=' : ''}${pageDTO.query != '' ? pageDTO.query : ''}">${num }</a></li>
					</c:forEach>
					<li class="page-item ${pageDTO.next == false ? 'disabled': '' }">
						<a class="page-link" href="list?p=${pageDTO.startPage +10 }${pageDTO.option != '' ? '&option=' : ''}${pageDTO.option != '' ? pageDTO.option : ''}${pageDTO.query != '' ? '&query=' : ''}${pageDTO.query != '' ? pageDTO.query : ''}" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</ul>
			</c:if>
		</nav>
	</div>
<%@include file="../includes/footer.jsp"%>
</body>
</html>