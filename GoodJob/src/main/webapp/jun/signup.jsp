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
	border-color: rgb(235 235 235 / var(--tw-border-opacity));
	color: 
	
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
	margin-bottom : 250px;
}

/* .form-group > mt-4 #domain-list { */
/* 	width: 10000px; */
/* 	color: red; */
/* 	border : 10px solid red; */
/* } */

.form-group > span{
	color: red;
	font-family: Pretendard-Regular;
} 

#id-box > span{
	color: red;
	font-family: Pretendard-Regular;
}

#id-box > span{
	color: red;
	font-family: Pretendard-Regular;
}

.btn-primary{ 
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
     font-size: 1.5rem;
 }
div > label {
	 font-family: Pretendard-Regular;
/*      font-size: 1.5rem; */
}

.form-label {
    font-family: Pretendard-Regular;
    font-size: 1.5rem;
}

.mx-auto > p {
	font-family: Pretendard-Regular;
    font-size: 1.2rem;
}
 .mb-4 {
	font-family: Pretendard-Regular;
    font-size: 3rem;
}
#password{
	font-family: Pretendard-Regular;
    font-size: 1rem;
}
#password_confirm{
	font-family: Pretendard-Regular;
    font-size: 1rem;
}


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
          <p>정보를 등록 해주세요</p>
<!--           <div class="signin-options mt-10"> -->
<!--             <a class="btn btn-outline-white block w-full text-dark" href="#" -->
<!--               >Sign Up With Google</a -->
<!--             > -->
<!--           </div> -->
          <div
            class="relative my-8 text-center after:absolute after:left-0 after:top-1/2 after:z-[0] after:w-full after:border-b after:border-border after:content-['']"
          >
            <span class="relative z-[1] inline-block bg-white px-2" >Join Us</span>
          </div>

          <form action="#">
            <div class="form-group" id="id-box">
              <label for="id" class="form-label">아이디</label>
              <input type="text" id="id" class="form-control" placeholder=" 아이디는 5 ~ 20자 이내로 영어 대소문자, 숫자, '-',  '_'만 입력이 가능합니다. "/>
              <button id="checkId"> 중복확인 </button>
              <span id="incorrectid"></span>
            </div>
            <div class="form-group mt-4">
              <label for="password" class="form-label">비밀번호</label>
              <input type="password" id="pw" class="form-control"  placeholder=" 비밀번호는 8-16자로 이내로 영어 대문자, 영어 소문자, 숫자, ‘*’, ‘-’만 입력이 가능합니다. " />
			  <span id="incorrectPw"></span>            
            </div>
            <div class="form-group mt-4">
              <label for="password_confirm" class="form-label" id="confirmPw">비밀번호 확인</label>
              <input type="password"  id="pw_confirm" class="form-control" placeholder="비밀번호 확인"/>
              <span id="differentPw"></span>
            </div>
            
            <div class="form-group mt-4">
              <label for="name" class="form-label">이름</label>
              <input type="text" id="name" class="form-control" placeholder="이름을 입력하세요."/>
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
                type="text"
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
              value="회원가입"
            />
          </form>
        </div>
      </div>
    </div>
  </div>

</section>
<%@include file="/WEB-INF/views/inc/footer.jsp" %>	
	<script>
	$('#id').keydown(function () {
	    var idLimit = /^[a-zA-Z0-9-_]{5,20}$/; // 정규식 5~20자 (a~z, A~Z, 0~9, -, _만 입력 가능)

	    if (!idLimit.test($('#id').val())) {
	        $('#incorrectid').html("사용할 수 없는 아이디입니다.");
	    } else {
	    	// 정규식 만족 하는 경우
	        $('#incorrectid').html(""); 
	    }
	});
	
	//비밀번호 유효성 검사
	$('#pw').keydown(function () {
    var pwLimit = /^[a-zA-Z0-9!*-]{10,20}$/; // 정규식 (a~z, A~Z, 0~9, *-! 만 입력 가능)

    if (!pwLimit.test($('#pw').val())) {
        $('#incorrectPw').html("사용할 수 없는 비밀번호입니다.");
    } else {
    	// 정규식 만족 하는 경우
        $('#incorrectPw').html("");
    }
	});
	
	//비밀번호가 일치 확인
	$('#pw_confirm').keydown(function () {
    	if ($('#pw').val() !== $('#pw_confirm').val()) {
        	$('#differentPw').html("비밀번호가 일치하지 않습니다.");
    	} else {
        	$('#differentPw').html("");
    	}
	});
	
	$('#domain-list').change(function () {
		 var selectedDomain = $(this).val();
		// 이메일 도메인 입력 필드의 내용을 선택된 값으로 업데이트
		$('#domain').val(selectedDomain);
	});

	
	
	</script>
</body>
</html>