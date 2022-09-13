<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% pageContext.setAttribute("replace", "\n"); %>
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
sessionStorage.clear()

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
			var html = jQuery('<div>').html(result);
			var contents = html.find("div#reply").html();
			$('#reply').html(contents);
		}
	});
}

function checkReplyContent() {
	var content = $("#replyContent").val()
	if(content != '') {
		$('#replySubmit').removeClass('disabled')
	} else {
		$('#replySubmit').addClass('disabled')
	}
}

function checkReReplyContent() {
	var content = $("#rereplyContent").val()
	if(content != '') {
		$('#rereplySubmit').removeClass('disabled')
	} else {
		$('#rereplySubmit').addClass('disabled')
	}
}

function replyWrite(){
	var boardNum = ${boardDTO.boardNum}
	var content = $("#replyContent").val()
	
	if(content == '') {
		alert('댓글을 입력해주세요.')
		return false;		
	}
	var data = {
		'boardNum': boardNum,
		'content': content,
		'depth': 0
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
				$('#submit').addClass('disabled');
				scrollTo(0,document.body.scrollHeight)
			}
		}
	});
}

function rereplyWrite(){
	var boardNum = ${boardDTO.boardNum}
	var replyNum = ((((($('#rereplySubmit').closest('div')).closest('div')).closest('div')).closest('li')).attr('id')) 
	var content = $("#rereplyContent").val()
	
	if(content == '') {
		alert('댓글을 입력해주세요.')
		return false;		
	}
	var data = {
		'boardNum': boardNum,
		'content': content,
		'depth': 1,
		'replyGroup': replyNum
	}
	$.ajax({
		url: '/reply/write',
		type: 'post',
		data: data,
		success:function(result){
			if(result == 1) {
				alert('댓글이 등록되었습니다.');
				$("#rereplyContent").val('');
				replyList();
				$('#submit').addClass('disabled');
			}
		}
	});
}


$(document).on("click", ".reply-input-btn", function(){
	var session = sessionStorage.getItem('session')
	if(session == null) {
		alert('댓글을 등록하려면 로그인이 필요합니다.')
		location.href='/user/login'
		return false;
	}
})

$(document).on("click", ".rereply-btn a", function(){
	var replyNum = ($(this).closest('div')).closest('li').attr('id')
	console.log(($(this).closest('div')).closest('li').attr('id'))
	var session = sessionStorage.getItem('session')
	if(session == null) {
		alert('댓글을 등록하려면 로그인이 필요합니다.')
		location.href='/user/login'
		return false;
	}
	if($('#reply'+replyNum).length > 0) {
		$('div').remove('.rereply-form')
	} else {
		$('div').remove('.rereply-form')
		$('#'+replyNum).append('<div class="reply-form rereply-form" id="reply'+replyNum+'"><i class="bi bi-arrow-90deg-down"></i><div class="reply-input-btn"><div class="reply-input"><textarea id="rereplyContent" name="rereplyContent" placeholder="댓글을 입력해주세요." oninput="checkReReplyContent()"></textarea></div><div class="reply-btn"><button type="submit" class="mybtn reply-submit disabled" id="rereplySubmit" onclick="return rereplyWrite()">등록</button></div></div></div>');
		$('#rereplyContent').focus();
	}
})


$(document).on("click", "#replyUpdate", function(){
	var replyNum = ((($(this).closest('div')).closest('div')).closest('li')).attr('id')
	$('li#'+replyNum).html('<div class="reply-form rereply-form" id="'+replyNum+'"><div class="reply-input-btn"><div class="reply-input"><textarea id="rereplyContent" name="rereplyContent" placeholder="댓글을 입력해주세요.">'+$("li#"+replyNum+" .content").text()+'</textarea></div><div class="reply-btn"><button type="submit" class="mybtn reply-submit" onclick="return replyWrite()">등록</button></div></div></div>');
})

	
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
					<a href="${pageContext.request.contextPath}/board/update/${boardDTO.boardNum}" onclick="return update_()">수정</a>|<a href="${pageContext.request.contextPath}/board/delete/${boardDTO.boardNum}" onclick="return delete_()">삭제</a>
				</div>
			</c:if>
			<div style="margin: 0 25px;">
				<div class="board-title mb-5">
					<h2>
						<c:out value="${boardDTO.title }"/><br>
					</h2>
				</div>
				<div class="board-content">
					${fn:replace(boardDTO.content, replace, "<br/>") }
				</div>
			</div>
		</div>
		<div id="reply">
		
		</div>
		
		 
	</div>
	<c:if test="${sessionScope.userId != null }">
		<script>
			sessionStorage.setItem('session', '${sessionScope.nickName }')
		</script>
	</c:if>
<%@include file="../includes/footer.jsp"%>
</body>
</html>