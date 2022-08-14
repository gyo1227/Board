<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>
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
</head>
<script type="text/javascript">
function checkId() {
	var userId = $('#userId').val()
	if(userId == '') {
		$('#idCheck').css("display", "none")
		$('#idCheck').text('')
		$('#idCheck').removeClass('ok')
		$('#idCheck').removeClass('no')
		$('#submit').attr('disabled', true)
	} else if(userId.replace(/\s/g,'').length == 0) {
		$('#idCheck').css("display", "inline-block")
		$('#idCheck').text('공백은 입력할 수 없습니다.')
		$('#idCheck').removeClass('ok')
		$('#idCheck').addClass('no')
		$('#submit').attr('disabled', true)
	} else {
		$.ajax({
			url:'/user/idCheck',
			type:'post',
			data:{userId:userId},
			success:function(cnt){
				if(cnt != 1) {
					$('#idCheck').css("display", "inline-block")
					$('#idCheck').text('사용 가능한 아이디입니다.')
					$('#idCheck').removeClass('no')
					$('#idCheck').addClass('ok')
					if($('#nickNamecheck').text() == '사용 가능한 닉네임입니다.'
							&& $('#pwCheck').text() == '사용 가능한 비밀번호입니다.') {
						$('#submit').attr('disabled', false)
					}
				} else {
					$('#idCheck').css("display", "inline-block")
					$('#idCheck').text('이미 사용중인 아이디입니다.')
					$('#idCheck').removeClass('ok')
					$('#idCheck').addClass('no')
					$('#submit').attr('disabled', true)
				}
			},
			error:function(){
				console.log("에러입니다")
			}
		});
	}
};
function checkNickName() {
	var nickName = $('#nickName').val()
	if(nickName == '') {
		$('#nickNamecheck').css("display", "none")
		$('#nickNamecheck').text('')
		$('#nickNamecheck').removeClass('ok')
		$('#nickNamecheck').removeClass('no')
		$('#submit').attr('disabled', true)
	} else if(nickName.replace(/\s/g,'').length == 0) {
		$('#nickNamecheck').css("display", "inline-block")
		$('#nickNamecheck').text('공백은 입력할 수 없습니다.')
		$('#nickNamecheck').removeClass('ok')
		$('#nickNamecheck').addClass('no')
		$('#submit').attr('disabled', true)
	} else {
		$.ajax({
			url:'/user/nickNameCheck',
			type:'post',
			data:{nickName:nickName},
			success:function(cnt){
				if(cnt != 1) {
					$('#nickNamecheck').css("display", "inline-block")
					$('#nickNamecheck').text('사용 가능한 닉네임입니다.')
					$('#nickNamecheck').removeClass('no')
					$('#nickNamecheck').addClass('ok')
					if($('#idCheck').text() == '사용 가능한 아이디입니다.'
							&& $('#pwCheck').text() == '사용 가능한 비밀번호입니다.') {
						$('#submit').attr('disabled', false)
					}
				} else {
					$('#nickNamecheck').css("display", "inline-block")
					$('#nickNamecheck').text('이미 사용중인 닉네임입니다.')
					$('#nickNamecheck').removeClass('ok')
					$('#nickNamecheck').addClass('no')
					$('#submit').attr('disabled', true)
				}
			},
			error:function(){
				console.log("에러입니다");
			}
		});
	}
};

function checkPw() {
	var userPw = $('#userPw').val()
	if(userPw == '') {
		$('#pwCheck').css("display", "none")
		$('#pwCheck').text('')
		$('#pwCheck').removeClass('ok')
		$('#pwCheck').removeClass('no')
		$('#submit').attr('disabled', true)
	} else if(userPw.replace(/\s/g,'').length >= 4 && userPw.replace(/\s/g,'').length <= 20) {
		$('#pwCheck').css("display", "inline-block")
		$('#pwCheck').text('사용 가능한 비밀번호입니다.')
		$('#pwCheck').removeClass('no')
		$('#pwCheck').addClass('ok')
		if($('#idCheck').text() == '사용 가능한 아이디입니다.'
				&& $('#nickNamecheck').text() == '사용 가능한 닉네임입니다.') {
			$('#submit').attr('disabled', false)
		}
	} else {
		$('#pwCheck').css("display", "inline-block")
		$('#pwCheck').text('사용 불가능한 비밀번호입니다.')
		$('#pwCheck').removeClass('ok')
		$('#pwCheck').addClass('no')
		$('#submit').attr('disabled', true)
	}
}
</script>
<body>
<div id="body">
<%@include file="../includes/header.jsp"%>
<c:if test="${message == '회원가입을 축하합니다.' }">
	<script>
		alert('${message}')
		location.href="${pageContext.request.contextPath}/board/list";
	</script>
</c:if>
<c:choose>
	<c:when test="${sessionScope.userId == null }">
		<div class="form">
			<form class="user-form" name="form" method="post" action="${pageContext.request.contextPath}/user/join">
				<div class="form-floating mb-3">
					<input type="text" class="form-control" id="userId" name="userId" placeholder="아이디를 입력하세요." oninput="checkId()"/>
					<label for="userId">아이디</label>
					<span class="check" id="idCheck">이미 사용중인 아이디입니다.</span>
				</div>
				<div class="form-floating mb-3">
					<input type="password" class="form-control" id="userPw" name="userPw" placeholder="아이디를 입력하세요." oninput="checkPw()"/>
					<label for="userPw">비밀번호</label>
					<span class="check" id="pwCheck"></span>
				</div>		
				<div class="form-floating mb-3">
					<input type="text" class="form-control" id="nickName" name="nickName" placeholder="닉네임을 입력하세요." oninput="checkNickName()"/>
					<label for="nickName">닉네임</label>
					<span class="check" id="nickNamecheck">이미 사용중인 닉네임입니다.</span>
				</div>
				<button type="submit" id="submit" class="w-100 btn btn-lg btn-primary form-floating mb-2" disabled>회원가입</button>
			</form>
		</div>
	</c:when>
	<c:otherwise>
		<script>
			location.href="${pageContext.request.contextPath}/board/list";
		</script>
	</c:otherwise>
</c:choose>
<%@include file="../includes/footer.jsp"%>
</div>
</body>
</html>