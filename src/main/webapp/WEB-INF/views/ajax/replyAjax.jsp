<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="reply" id="reply">
		<div class="pb-2">
			<i class="bi bi-chat"></i>
			<span style="font-size: 1.3rem;">댓글</span> (<span id="replyCnt">${replyCnt }</span>)
		</div>
		<div class="reply-border-top">
			<div class="reply-form">
				<div class="reply-input-btn">
					<div class="reply-input">
						<textarea id="replyContent" name="replyContent" placeholder="댓글을 입력해주세요." oninput="checkContent(this)"></textarea>
					</div>
					<div class="reply-btn">
						<button type="submit" class="mybtn reply-submit disabled" id="replySubmit">등록</button>
					</div>
				</div>
			</div>
			<div class="reply-list" id="reply-list">
				<ul>
					<c:forEach items="${list }" var="list">
						<c:choose>
							<c:when test="${list.deleteChk == 1}">
								<li class="replyOne delReply">
									삭제된 댓글입니다.
								</li>
							</c:when>
							<c:otherwise>
								<li class="replyOne${list.depth == 1 ? ' rereply' : '' }" id="${list.replyNum }">
									<c:if test="${list.depth == 1 }">
										<i class="bi bi-arrow-90deg-down rereply-arrow"></i>
									</c:if>
									<div class="title">
										<input type="hidden" class="depth" value="${list.depth }">
										<span class="nickName">${list.nickName }</span>
										<fmt:parseDate value="${list.regDate }" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
										<span class="regDate">(<fmt:formatDate value="${regDate }" pattern="yyyy-MM-dd HH:mm" />)</span>
										<c:if test="${sessionScope.userId == list.userId }">
											<a class="link-dark text-decoration-none replyOpt" data-bs-toggle="dropdown" aria-expanded="false">
												<i class="bi bi-three-dots-vertical"></i>
											</a>
											<ul class="dropdown-menu text-small">
												<li><a class="dropdown-item" id="replyUpdate">수정</a></li>
												<li><a class="dropdown-item" id="replyDelete">삭제</a></li>
											</ul>
										</c:if>
									</div>
									<div class="content">${list.content }</div>
									<c:if test="${list.depth == 0 }">
										<div class="rereply-btn">
											<a>답글달기</a>
										</div>
									</c:if>
								</li>
							</c:otherwise>
						</c:choose>
						
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	
</body>
</html>