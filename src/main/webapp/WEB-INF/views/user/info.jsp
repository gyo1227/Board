<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원정보</title>
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
function changePw() {
	
	var curPw = $('#cur_userPw');
	var newPw = $('#changeUserPw');
	var newPw_ok = $('#changeUserPw_ok');
	
	if(newPw.val() != newPw_ok.val()) {
		$('.myModal').removeClass("hidden");
		$('.modalContent').text('비밀번호가 다릅니다. 다시 입력해 주세요.')
		$('#modalClosed').click(function(){
			$('.myModal').addClass("hidden")
			newPw_ok.focus();
		})
		return false;
	}

	if(curPw.val() == newPw.val()) {
		$('.myModal').removeClass("hidden");
		$('.modalContent').text('기존과 다른 비밀번호를 입력해주세요.')
		$('#modalClosed').click(function(){
			$('.myModal').addClass("hidden")
			newPw.focus();
		})
		return false;
	}
	
	$.ajax({
		url: '/user/changePw',
		type: 'post',
		/* anync: false, */
		data: {
			'curPw':curPw.val(),
			'newPw':newPw.val()
		},
		success:function(result){
			$('.myModal').removeClass("hidden");
			
			if(result.message == 'OK') {
				$('.modalContent').text('비밀번호 변경에 성공하였습니다.')
				$('#modalClosed').click(function(){
					location.href="/user/info"
				})
			} else {
				$('.modalContent').text(result.message)
				$('#modalClosed').click(function(){
					$('.myModal').addClass("hidden")
					curPw.focus();
				})
			}
		},
		error:function(){
			console.log('에러입니다.');
		}
	})
}

function changeNick() {
	
	var nickName = $('#changeNickName');
	data = {
		'changeNickName':nickName.val(),
	}
	
	$.ajax({
		url: '/user/changeNickName',
		type: 'post',
		data: {
			'changeNickName':nickName.val(),
		},
		success:function(result){
			$('.myModal').removeClass("hidden");
			
			if(result.message == 'OK') {
				$('.modalContent').text('닉네임 변경에 성공하였습니다.')
				$('#modalClosed').click(function(){
					location.href="/user/info"
				})
			} else {
				$('.modalContent').text(result.message)
				$('#modalClosed').click(function(){
					$('.myModal').addClass("hidden")
				})
			}
		},
		error:function(){
			console.log('에러입니다.');
		}
	})
}

function deleteUser() {
	
	var userPw = $('#userPw');
	data = {
		'userPw':userPw.val(),
	}
	
	$.ajax({
		url: '/user/deleteUser',
		type: 'post',
		data: {
			'userPw':userPw.val(),
		},
		success:function(result){
			$('.myModal').removeClass("hidden");
			
			if(result.message == 'OK') {
				$('.modalContent').text('회원탈퇴가 완료되었습니다.')
				$('#modalClosed').click(function(){
					location.href="/board/list"
				})
			} else {
				$('.modalContent').text(result.message)
				$('#modalClosed').click(function(){
					$('.myModal').addClass("hidden")
				})
			}
		},
		error:function(){
			console.log('에러입니다.');
		}
	})
}

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
		$('#changeNickName').val('');
		$('#cur_userPw').val('');
		$('#changeUserPw').val('');
		$('#changeUserPw_ok').val('');
		$('#userPw').val('');
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
								<li><a class="active" href="${pageContext.request.contextPath}/user/info">회원 정보<i class="bi bi-chevron-compact-right"></i></a></li>
								<li><a href="${pageContext.request.contextPath}/user/boardList">작성 게시글<i class="bi bi-chevron-compact-right"></i></a></li>
								<li><a href="${pageContext.request.contextPath}/user/replyList">작성 댓글<i class="bi bi-chevron-compact-right"></i></a></li>
							</ul>
						</nav>
					</div>
					<div class="user-table">
						<div class="user-title">
							<h3>회원 정보</h3>
						</div>
						<table class="table-body">
							<colgroup>
								<col style="width: 176px;">
								<col style="width: *;">
							</colgroup>
							<tbody class="user-info-tbody">
								<tr>
									<th class="user-info-th">아이디</th>
									<td class="user-info-td">
										<span>${userDTO.userId }</span>
									</td>
								</tr>
							</tbody>
							<tbody id="password_modify_off" class="user-info-tbody">
								<tr>
									<th class="user-info-th">비밀번호</th>
									<td class="user-info-td">
										<strong>●●●●●●●●●●</strong>
										<a href="#" class="" style="margin-left: 10px;" onclick="changeMode($('#password_modify_off'), $('#password_modify_on'))">
											<span class="mybtn mybtn-sm">변경</span>
										</a>
									</td>
								</tr>
							</tbody>
							<tbody id="password_modify_on" class="user-info-tbody" style="">
								<tr>
									<th class="user-info-th" >비밀번호</th>
									<td class="user-info-td">
										<div class="input-group">
											<input type="password" class="myInput" id="cur_userPw" placeholder="현재 비밀번호">
										</div>
										<div class="input-group">
											<input type="password" class="myInput" id="changeUserPw" placeholder="신규 비밀번호" data-toggle="popover" data-placement="bottom" data-content="Content1">
											<small>4자~20자 영문, 숫자, 특수문자를 조합해 주세요.</small>
										</div>
										<div class="input-group">
											<input type="password" class="myInput" id="changeUserPw_ok" placeholder="비밀번호 확인" data-bs-toggle="popover" data-bs-placement="bottom" data-bs-content="비밀번호가 일치하지 않습니다. 다시 입력해 주세요." data-bs-custom-class="mypopover">
											<small> 비밀번호를 다시 한번 입력해 주세요. </small><br>
											<span><small style="display: none" id="chkpw"></small></span>
										</div>
										<nav>
											<a href="#" id="chagnePw" onclick="changePw()">
												<span class="mybtn mybtn-sm">변경</span>
											</a>
											<a href="#" onclick="cancel($('#password_modify_off'), $('#password_modify_on'))">
												<span class="mybtn mybtn-sm">취소</span>
											</a>
										</nav>
									</td>
								</tr>
							</tbody>
							<tbody id="nickName_modify_off" class="user-info-tbody">
								<tr>
									<th class="user-info-th">닉네임</th>
									<td class="user-info-td">
										<span>${userDTO.nickName }</span>
										<a href="#" style="margin-left: 10px;" onclick="changeMode($('#nickName_modify_off'), $('#nickName_modify_on'))">
											<span class="mybtn mybtn-sm">변경</span>
										</a>
									</td>
								</tr>
							</tbody>
							<tbody id="nickName_modify_on" class="user-info-tbody">
								<tr>
									<th class="user-info-th" >닉네임</th>
									<td class="user-info-td">
										<span class="text-bold">${userDTO.nickName }</span>
										<div class="input-group">
											<input type="text" class="myInput" id="changeNickName" placeholder="변경 닉네임">
											<small>사용하실 닉네임을 입력해 주세요.(최대 6글자)</small>
										</div>
										<nav>
											<a href="#" id="changeNick" onclick="changeNick()">
												<span class="mybtn mybtn-sm">변경</span>
											</a>
											<a href="#" class="" onclick="cancel($('#nickName_modify_off'), $('#nickName_modify_on'))">
												<span class="mybtn mybtn-sm">취소</span>
											</a>
										</nav>
									</td>
								</tr>
							</tbody>
							<tbody id="delete_user_off" class="user-info-tbody">
								<tr>
									<th class="user-info-th">탈퇴신청</th>
									<td class="user-info-td">
										<a href="#" onclick="changeMode($('#delete_user_off'), $('#delete_user_on'))">
											<span class="mybtn mybtn-sm">탈퇴</span>
										</a>
									</td>
								</tr>
							</tbody>
							<tbody id="delete_user_on" class="user-info-tbody">
								<tr>
									<th class="user-info-th">탈퇴신청</th>
									<td class="user-info-td">
										<div class="input-group">
											<input type="password" class="myInput" id="userPw" placeholder="비밀번호">
											<small>본인  인증을 위해 비밀번호를 입력해주세요.</small>
										</div>
										<nav>
											<a href="#" id="" onclick="deleteUser()">
												<span class="mybtn mybtn-sm">탈퇴</span>
											</a>
											<a href="#" class="" onclick="cancel($('#delete_user_off'), $('#delete_user_on'))">
												<span class="mybtn mybtn-sm">취소</span>
											</a>
										</nav>
									</td>
								</tr>
							</tbody>
						</table>
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

</div>
</body>
</html>