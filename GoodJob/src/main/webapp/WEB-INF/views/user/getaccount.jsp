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


#login_submit{ 
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
.min-h-[980px] {
    min-height: 980px;
    width: 1000px;
}
.type {
	display: flex;
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
        <div class="cotainer">
          <img class="mb-8" src="images/flower.svg" alt="" />
          <h1 class="mb-4">계정 찾기</h1>
          <p id="login_letter">계정을 찾을 수 있습니다.</p>
         

          <form method="POST" action="/good/user/signin.do">
            <div class="type">
              <label for="id" class="form-label">아이디</label>
              <label for="password" class="form-label">비밀번호</label>
             
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</section>	
	
<%@include file="/WEB-INF/views/inc/footer.jsp" %>	
	<script>

	</script>
</body>
</html>