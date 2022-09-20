<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
function logout(){

	if(confirm('로그아웃 하시겠습니까?')) {
		return true
	}
	return false;
}

$(document).ready(function(){
	$.ajax({
		url: '/user/nickName',
		type: 'post',
		success: function(result){
			if(result.nickName != null) {
				$('#nickName').text(result.nickName + ' 님')
			}
		},
		error:function(){
			console.log('에러입니다.');
		}
	})
})
</script>
<header class="p-3 border-bottom">

	<div class="container header bold">
		
		<div class="h2">
			<a href="${pageContext.request.contextPath}/board/list">BOARD</a>
		</div>
		
		<div class="dropdown text-end">
			<c:choose>
				<c:when test="${sessionScope.userId != null }">
					<a id="nickName" href="#" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
						
					</a>
					<ul class="dropdown-menu text-small">
						<li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/info">회원정보</a></li>
						<li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/logout" onclick="return logout()">로그아웃</a></li>
					</ul>
				</c:when>
				<c:otherwise>
				<a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
					<i class="bi bi-person-circle"></i>
				</a>
					<ul class="dropdown-menu text-small">
						<li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/login">로그인</a></li>
						<li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>