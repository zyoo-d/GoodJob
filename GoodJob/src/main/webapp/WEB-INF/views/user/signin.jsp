<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
	<section class="integration-single section pt-0">
		<div class="container">
			<div class="row justify-center">

				<div
					class="mt-16 integration-single-container rounded-xl bg-white py-16 px-5 shadow-lg md:px-10 tab w-70">
					<div class="mx-auto w-full max-w-[480px]">
						<h2 class="mb-0 mt-4">로그인</h2>
						<p id="login_letter">로그인 후 굿잡을 이용해보세요.</p>

						<div style="display: flex; justify-content: space-between; border: 1px solid cornflowerblue">
							<form method="POST" action="/good/user/signin.do">
								<input type="hidden" name="id" value="hong123"> <input
									type="hidden" name="pw" value="123123123123"> <input
									type="submit" value="  홍길동   ">
							</form>
							<form method="POST" action="/good/user/signin.do">
								<input type="hidden" name="id" value="admin"> <input
									type="hidden" name="pw" value="123"> <input
									type="submit" value="  관리자   ">
							</form>

						</div>

						<form method="POST" action="/good/user/signin.do">
							<div class="form-group">
								<label for="id" class="form-label text-lg">아이디</label> <input
									type="text" name="id" class="form-control" placeholder="아이디"
									name="id" />
							</div>
							<div class="form-group mt-4">
								<label for="password" class="form-label text-lg">비밀번호</label> <input
									type="password" name="pw" class="form-control"
									placeholder="비밀번호" name="pw" />
							</div>
							<div class="submit-cotainer">
								<input class="btn btn-primary mt-10 block w-full text-xl" type="submit"
									id="login_submit" value="로그인" />
								<p class="mt-6 text-center">
									<span>로그인이 안되시나요?</span> <br /> <a class="text-dark"
										href="/good/user/getaccount.do">계정찾기</a> 혹은 <a
										class="text-dark" href="/good/user/signup.do">회원가입</a>를
									이용해보세요.
								</p>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>



	</section>



	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
		
	</script>
</body>
</html>