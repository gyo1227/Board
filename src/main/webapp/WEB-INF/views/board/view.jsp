<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${boardDTO.title }</title>
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
$(document).ready(function(){
	replyList()
});

function replyList(){
	var boardNum = ${boardDTO.boardNum}
	var data = {
		boardNum: boardNum
	}
	$.ajax({
		url: '/reply/replyList/'+boardNum,
		type: 'get',
		data: data,
		success:function(result){
			var replyCnt = result.length
			$('#replyCnt').text(replyCnt)
			
			var list = '<ul>';
			for(var i in result) {
				list += '<li class="replyOne" id="' + result[i].replyNum + '">';
				list += '<div class="title">';
				list += '<span class="nickName">' + result[i].replyNickName + '</span>';
				list += '<span class="regDate"> (' + result[i].replyRegDate + ')</span>';
				list += '</div>';
				list += '<div class="content"><span style="margin-top: 14px;">' + result[i].replyContent + '</span></div>';
				list += '<div class="rereply" name="' + result[i].replyNum + '" onclick="return rereply(' + result[i].replyNum + ')"><a href="#">답글달기</a></div>';
				list += '</div>';
				list += '</li>';
			}
			list += '</ul>';
			$('#reply-list').html(list);
		}
	});
}

function checkContent() {
	var content = $("#replyContent").val()
	if(content != '') {
		$('#submit').attr('disabled', false)
	} else {
		$('#submit').attr('disabled', true)
	}
}

function replyWrite(){
	var boardNum = ${boardDTO.boardNum}
	var replyContent = $("#replyContent").val()
	
	if(replyContent == '') {
		alert('댓글을 입력해주세요.')
		return false;		
	}
	var data = {
		boardNum: boardNum,
		replyContent: replyContent
	}
	$.ajax({
		url: '/reply/write',
		type: 'post',
		data: data,
		success:function(result){
			if(result == 1) {
				alert('댓글이 등록되었습니다.');
				$("#replyContent").val('');
				replyList();
				$('#submit').attr('disabled', true);
				scrollTo(0,document.body.scrollHeight)
			}
		}
	});
}

function rereply(replyNum){
	
	$('#'+replyNum).append('<div class="reply-form"><div class="reply-input-btn"><div class="reply-input"><textarea id="replyContent" name="replyContent" placeholder="바른 언어를 사용합시다."></textarea></div><div class="reply-btn"><button type="submit" class="btn reply-submit" onclick="return replyWrite()">등록</button></div></div></div>');
}
	
function update_(){
	if(confirm('수정 하시겠습니까?')) {
		return true
	}
	return false;
}

function delete_(){
	if(confirm('삭제 하시겠습니까?')) {
		return true
	}
	return false;
}
</script>
<body>
<%@include file="../includes/header.jsp"%>
	<div class="container">
		<div class="board mb-5">
			<div class="board-info">
				<div class="mb-4" style="background: #f8f8f8; border-bottom: 1px solid #ccc;">
					<div class="row text-center p-3">
						<div class="col-4 text-start">
							작성자:
							<c:out value="${boardDTO.nickName }"/>
						</div>
						<div class="col-4">
							<fmt:parseDate value="${boardDTO.regDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
							<fmt:formatDate value="${parsedDateTime }" pattern="yyyy-MM-dd HH:mm" />
						</div>
						<div class="col-4 text-end">
							조회수:
							<c:out value="${boardDTO.viewCnt }"/>
						</div>
					</div>
				</div>
			</div>
			<c:if test="${sessionScope.userId == boardDTO.userId }">
				<div class="view-Opt">
				<div>
					<a href="${pageContext.request.contextPath}/board/update/${boardDTO.boardNum}" onclick="return update_()">수정</a>
				</div>|
				<div>
					<a href="${pageContext.request.contextPath}/board/delete/${boardDTO.boardNum}" onclick="return delete_()">삭제</a>
				</div>
				</div>
			</c:if>
			<div style="margin: 0 25px;">
				<div class="board-title mb-5">
					<h2>
						<c:out value="${boardDTO.title }"/><br>
					</h2>
				</div>
				<div class="board-content">
					<pre><c:out value="${boardDTO.content }"/></pre>
				</div>
			</div>
		</div>
	
		<div class="reply">
			<div class="pb-2">
				<i class="bi bi-chat"></i>
				<span style="font-size: 1.3rem;">댓글</span> (<span id="replyCnt"></span>)
			</div>
			<div class="reply-border-top"><!-- <c:out value="${boardDTO.replyCnt }"/> -->
				<div class="reply-form">
					<div class="reply-input-btn">
						<div class="reply-input">
							<textarea id="replyContent" name="replyContent" placeholder="바른 언어를 사용합시다." oninput="checkContent()"></textarea>
						</div>
						<div class="reply-btn">
							<button type="submit" class="btn reply-submit" id="submit" onclick="return replyWrite()" disabled>등록</button>
						</div>
					</div>
				</div>
				<div class="reply-list" id="reply-list">
					<ul>
						<li id="${replyNum }">
							<div>
								<div class="title">
									<span class="nickName">${replyNickName }</span>
									<span class="regDate">${replyRegDate }</span>
								</div>
								<div class="content">${replyContent }</div>			
							</div>
						</li>
					</ul>
				</div>
				
			</div>
		</div>
		
	</div>
	<c:if test="${sessionScope.userId == null }">
		<script>
			$('.reply-input-btn').click(function() {
				alert('댓글을 등록하려면 로그인이 필요합니다.')
				location.href='/user/login'
			});
		</script>
	</c:if>
<%@include file="../includes/footer.jsp"%>
</body>
</html>