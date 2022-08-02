<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 리스트</title>
</head>
<body>

	<table>
		<thead>
			<tr>
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
					<td>
						<c:out value="${list.boardNum}"/>
					</td>			
					<td>
						<a href='${pageContext.request.contextPath}/board/view/<c:out value="${list.boardNum }"/>'><c:out value="${list.title}"/><b>[ <c:out value="${list.replyCnt}" /> ]</b></a>
					</td>			
					<td><c:out value="${list.nickName}"/></td>	
 					<td><c:out value="${list.regDate}"/></td>		
					<td><c:out value="${list.viewCnt}"/></td>		
				</tr>
			</c:forEach>
		</tbody>
	</table>



	<a href="${pageContext.request.contextPath}/board/write">글쓰기</a>
</body>
</html>