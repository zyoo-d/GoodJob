<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
body > section > div > div > div {
	margin: 0 auto;
}
#login_letter {
	margin-bottom: 30px;
}
.mb-4 {
	margin-bottom: 0px;
}


#login_submit{ 
     cursor: pointer; 
     border-radius: 50px; 
     border-width: 0px; 
     background-image: linear-gradient(to top left, var(--tw-gradient-stops)); 
     text-align: center; 
     font-weight: 500; 
     --tw-text-opacity: 1; 
     color: rgb(255 255 255 / var(--tw-text-opacity)); 
     background-image: linear-gradient(184.78deg, #535AED 7.64%, #3EB2F8 120.07%); 
     font-family: Pretendard-Regular;
     margin-bottom: 20px;
     margin-top: 20px;
     font-size: 1.5rem;
 }

#login_letter {
    margin-bottom: 30px;
    font-family: Pretendard-Regular;
    font-size: 1.5rem;
}
.mb-4 {
    font-family: Pretendard-Regular;
}
.form-label {
    font-family: Pretendard-Regular;
    font-size: 1.2rem;
}
 
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp" %>
<body>
<section class="">
  <div class="container max-w-full">
    <div class="row">
      <div class="min-h-[980px] bg-white py-10 lg:col-6 lg:py-[114px]">
        <div class="mx-auto w-full max-w-[480px]">
          <img class="mb-8" src="images/flower.svg" alt="" />
          <h1 class="mb-4">로그인</h1>
          <p id="login_letter">로그인 후 굿잡을 이용해보세요.</p>
         

          <form method="POST" action="/good/user/signin.do">
            <div class="form-group">
              <label for="id" class="form-label">아이디</label>
              <input type="text" id="text" name="id" class="form-control" placeholder="아이디"  name="id"/>
            </div>
            <div class="form-group mt-4">
              <label for="password" class="form-label">비밀번호</label>
              <input type="password"  id="password" name="pw" class="form-control" placeholder="비밀번호" name="pw"/>
            </div>
            <div class="submit-cotainer">
            <input class="btn btn-primary mt-10 block w-full" type="submit" id="login_submit"value="로그인" />
            <p class="mt-6 text-center">
				<span>로그인이 안되시나요?</span> <br />
              <a class="text-dark" href="/good/user/getaccount.do">계정찾기</a> 혹은 <a class="text-dark" href="/good/user/signup.do">회원가입</a>를 이용해보세요.
            </p>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  
  	<div style="display: flex; justify-content: space-between;">
		<form method="POST" action="/good/user/signin.do">
			<input type="hidden" name="id" value="hong123">
			<input type="hidden" name="pw" value="123123123123">
			<input type="submit" value="  홍길동   ">
		</form>
		<form method="POST" action="/good/user/signin.do">
			<input type="hidden" name="id" value="admin">
			<input type="hidden" name="pw" value="123456789">
			<input type="submit" value="  관리자   " >
		</form>

	</div>
  
</section>	


	
<%@include file="/WEB-INF/views/inc/footer.jsp" %>	
	<script>

	</script>
</body>
</html>