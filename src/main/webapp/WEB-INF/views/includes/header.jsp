<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="p-3 mb-3 border-bottom">
	<div class="container header bold">
		
		<div class="h2">
			<a href="${pageContext.request.contextPath}/board/list">BOARD</a>
		</div>
		
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