<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>작성 댓글</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<!-- Bootstrap icon -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css" type="text/css">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
function chkDelete(){
	if(confirm('삭제 하시겠습니까?')) {
		var chkArr = new Array();
		
		$('input[class="chBox"]:checked').each(function(){
			chkArr.push($(this).val())
		})
		console.log(chkArr)
		if(chkArr.length == 0) {
			alert('선택된 게시글이 없습니다.')
			return false
		}
		
		$.ajax({
			url: '/board/chkDelete',
			type: 'post',
			data: {
				'chkArr': chkArr
			},
			success:function(){
				alert('삭제가 완료되었습니다.')
				location.href='/user/board';
			},
			error:function(){
				console.log('에러입니다.')
			}
		})
		
	}
	return false;
}

$(document).ready(function(){
	$('#allCheck').click(function(){
		if($('#allCheck').prop('checked')){
			$('input[type=checkbox]').prop('checked',true)
		} else {
			$('input[type=checkbox]').prop('checked',false)
		}
	})
	$(".chBox").click(function(){
		$("#allCheck").prop("checked", false);
	});
})
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
								<li><a href="${pageContext.request.contextPath}/user/boardList">작성 게시글<i class="bi bi-chevron-compact-right"></i></a></li>
								<li><a class="active" href="${pageContext.request.contextPath}/user/replyList">작성 댓글<i class="bi bi-chevron-compact-right"></i></a></li>
							</ul>
						</nav>
					</div>
					<div class="user-table">
						<div class="user-title">
							<h3>작성 댓글</h3>
						</div>
						<c:choose>
							<c:when test="${list != null }">
								<div class="board-list">
									<table class="table table-body">
										<colgroup>
											<col class="col-2">
											<col class="col-6">
											<col class="col-2">
											<col class="col-2">
										</colgroup>
										<thead>
											<tr class="text-center">
												<th><input type="checkbox" id="allCheck"><label style="font-size: 14px;padding-left: 6px;">전체선택</label></th>
												<th>제목</th>
												<th>작성일</th>
												<th>조회수</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list }" var="list">
												<tr>
													<td class="text-center">
														<input type="checkbox" class="chBox" value="${list.boardNum }">
													</td>
													<td>
														<a href='${pageContext.request.contextPath}/board/view/<c:out value="${list.boardNum }"/>'><c:out value="${list.title}"/><span class="reply"> [<c:out value="${list.replyCnt}" />]</span></a>
													</td>
													<td class="text-center">
														<fmt:parseDate value="${list.regDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
				 										<c:set var="ymddate"><fmt:formatDate value="${parsedDateTime }" pattern="yyyy-MM-dd" /></c:set>
				 										<c:set var="mddate"><fmt:formatDate value="${parsedDateTime }" pattern="MM-dd" /></c:set>
				 										<c:set var="time"><fmt:formatDate value="${parsedDateTime }" pattern="HH:mm" /></c:set>
				 										<c:choose>
				 											<c:when test="${ymddate == today }">
						 										<c:out value="${time }"></c:out>
				 											</c:when>
				 											<c:otherwise>
						 										<c:out value="${mddate }"></c:out>
				 											</c:otherwise>
				 										</c:choose>
													</td>
													<td class="text-center">${list.viewCnt }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<nav id="pagination" aria-label="Page navigation example">
										<c:if test="${total != 0}">
											<ul class="page justify-content-center">
												<li>
													<a class="paging ${pageDTO.prev == false ? 'disabled': '' }" href="ReplyList?p=${pageDTO.startPage-10 }" aria-label="Previous">
														<span aria-hidden="true">&laquo;</span>
													</a>
												</li>
												<c:forEach var="num" begin="${pageDTO.startPage }" end="${pageDTO.endPage }">
													<li><a class="paging ${pageDTO.curPage == num ? 'active': '' }" href="ReplyList?p=${num }">${num }</a></li>
												</c:forEach>
												<li>
													<a class="paging ${pageDTO.next == false ? 'disabled': '' }" href="ReplyList?p=${pageDTO.startPage +10 }" aria-label="Next">
														<span aria-hidden="true">&raquo;</span>
													</a>
												</li>
											</ul>
										</c:if>
									</nav>
								</div>
								<div>
									<a href="#" onclick="return chkDelete()">
										<span class="mybtn mybtn-sm">선택삭제</span>
									</a>
								</div>
							
							</c:when>
							<c:otherwise>
								<div style="width: 700px; text-align: center;">작성한 게시글이 없습니다.</div>
							</c:otherwise>
						</c:choose>
	
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

</div>
</body>
</html>