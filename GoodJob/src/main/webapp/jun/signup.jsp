<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
#email {
	width: 50%;
}
#domain {
	width: 25%;
	border-left: 5px;
}
#checkId {
	border : 1px solid black;
	width: 17%;
	padding: 3px;
	border-radius: 10px;
	
}
#id {

	width : 80%;
	margin-right: 5px;
}
.main_container {
	margin: 0 auto;
	width: 800px;
	
}
body > section > div {
	margin-top: 50px;
}

body > section > div > div > div > div > form > input {
	margin-bottom : 50px;
}

/* .form-group > mt-4 #domain-list { */
/* 	width: 10000px; */
/* 	color: red; */
/* 	border : 10px solid red; */
/* } */

</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp" %>
<body>
<section class="">
  <div class="container max-w-full">
    <div class="row">
      <div class="main_container">
<!--       <div class="min-h-[980px] bg-white py-10 lg:col-6 lg:py-[114px]"> -->
        <div class="mx-auto w-full max-w-[800px]">
          <img class="mb-8" src="images/flower.svg" alt="" />
          <h1 class="mb-4">회원 가입</h1>
          <p>회원가입 해주세요</p>
<!--           <div class="signin-options mt-10"> -->
<!--             <a class="btn btn-outline-white block w-full text-dark" href="#" -->
<!--               >Sign Up With Google</a -->
<!--             > -->
<!--           </div> -->
          <div
            class="relative my-8 text-center after:absolute after:left-0 after:top-1/2 after:z-[0] after:w-full after:border-b after:border-border after:content-['']"
          >
            <span class="relative z-[1] inline-block bg-white px-2"
              >Join Us</span
            >
          </div>

          <form action="#">
            <div class="form-group">
              <label for="name" class="form-label">이름</label>
              <input
                type="text"
                id="name"
                class="form-control"
                placeholder="이름을 입력하세요."
              />
            </div>
            <div class="form-group" id="id-box">
              <label for="id" class="form-label">아이디</label>
              <input
                type="text"
                id="id"
                class="form-control"
                placeholder=" 아이디는 5 ~ 20자 이내로 영어 대소문자, 숫자, '-',  '_'만 입력이 가능합니다. "
              />
              <button id="checkId">중복확인</button>
            </div>
            <div class="form-group mt-4">
              <label for="password" class="form-label">비밀번호</label>
              <input
                type="password"
                id="password"
                class="form-control"
                placeholder="제약조건 설명"
              />
            </div>
            <div class="form-group mt-4">
              <label for="password_confirm" class="form-label">비밀번호 확인</label>
              <input
                type="password"
                id="_confirm"
                class="form-control"
                placeholder="비밀번호 확인"
              />
            </div>
            <div class="form-group mt-4">
              <label for="email" class="form-label">이메일</label>
              <input type="email" id="email" class="form-control"  placeholder="Your Email Address"/>
              @
              <input type="text" id="domain" class="form-control" placeholder="직접입력"/>
             <select id="domain-list">
             	<option value="직접입력">직접입력</option>
             	<option value="naver.com">naver.com</option>
             	<option value="gmail.com">gmail.com</option>
             	<option value="hanmail.net">hanmail.net</option>
             	<option value="nate.com">nate.com</option>
             	<option value="yahoo.com">yahoo.com</option>
             </select>
            </div>
            
            <div class="form-group mt-4">
              <label for="tel" class="form-label">연락처</label>
              <input
                type="password"
                id="tel"
                class="form-control"
                placeholder="연락처를 입력하세요."
              />
            </div>
            
			<div class="form-group mt-4">
              <label for="address" class="form-label">주소</label>
              <input
                type="text"
                id="address"
                class="form-control"
                placeholder="주소를 입력하세요."
              />
            </div>
            <div class="form-group mt-4">
              <label for="address" class="form-label">닉네임</label>
              <input
                type="text"
                id="nickname"
                class="form-control"
                placeholder="닉네임을 입력하세요."
              />
            </div>
            
            <input
              class="btn btn-primary mt-10 block w-full"
              type="submit"
              value="Sign Up"
            />
          </form>
        </div>
      </div>
	<!--  
      <div
        class="auth-banner bg-gradient flex flex-col items-center justify-center py-16 lg:col-6"
      >
        <img
          class="absolute top-0 left-0 h-full w-full"
          src="images/login-banner-bg.svg"
          alt=""
        />
        <div class="w-full text-center">
          <h2 class="h3 text-white">
            A suite product accelerate <br />
            your career design
          </h2>
          <div class="swiper auth-banner-carousel">
            <div class="swiper-wrapper">
              <div class="swiper-slide">
                <img
                  width="667"
                  height="557"
                  class="mx-auto"
                  src="images/signup-carousel-img-1.png"
                  alt=""
                />
              </div>
              <div class="swiper-slide">
                <img
                  width="667"
                  height="557"
                  class="mx-auto"
                  src="images/signup-carousel-img-1.png"
                  alt=""
                />
              </div>
              <div class="swiper-slide">
                <img
                  width="667"
                  height="557"
                  class="mx-auto"
                  src="images/signup-carousel-img-1.png"
                  alt=""
                />
              </div>
            </div>
            <div class="pagination"></div>
          </div>
        </div>
      </div>
        -->
    </div>
  </div>

</section>
	
	
<%@include file="/WEB-INF/views/inc/footer.jsp" %>	
	<script>

	</script>
</body>
</html>