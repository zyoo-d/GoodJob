<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정 찾기</title>
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
.mb-4 {
	font-family: Pretendard-Regular;
	margin-bottom: 0px;
}

.bg-white {
	margin: 0 auto;
}

.form-label {
	font-family: Pretendard-Regular;
	font-size: 1.2rem;
}

/* 공통 스타일 */
.container {
	max-width: 100%;
}
#changepw>div {
	text-align: center;
}
input[type="password"] {
	font-family: 'Pretendard-Regular';
	font-size: 1rem;
	border-radius: 10px;
	margin-bottom: 10px;
	width: 100%;
	border-color: rgb(235, 235, 235);
}

.btn_getAccount {
	background-image: linear-gradient(184.78deg, rgb(83, 90, 237) 7.64%,
	rgb(62, 178, 248) 120.07%);
	color: white;
	padding: 10px 20px;
	border-radius: 10px;
	font-size: .7rem;
	font-family: 'Pretendard-Regular';
	margin-left: 3px;
	width: 90%;
	margin-top: 20px;
	font-size: 1.3rem;
}
.form-content {
	border: 2px solid #4444;
	border-radius: 10px;

}
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
	<section>
		<div class="container">
			<div class="min-h-[980px] bg-white py-10 lg:col-6 lg:py-[114px]">
				<div class="mx-auto w-full max-w-[480px]">
					<h1 class="mb-4">비밀번호 재설정</h1>
					<p id="login_letter">비밀번호를 재설정합니다.</p>

					<form action="/good/user/changepw.do" method="POST">
						<div id="changepw" class="form-content">
							<label for="id" class="form-label"></label> 
							<input type="password" id="pw" name="pw" placeholder="비밀번호" /> 
							<label for="tel" class="form-label">비밀번호 확인</label> 
							<input type="password" id="password" placeholder="비밀번호 확인" />
							<div>
								<input type="submit" class="btn_getAccount" id="btn_getPw" value="비밀번호 재설정" />
							</div>
						</div>
	<input type="hidden" name="id" value="${id}" />
						</form>
						
						
					
				</div>
			</div>
		</div>
	</section>
	

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	
	</script>
</body>
</html>
