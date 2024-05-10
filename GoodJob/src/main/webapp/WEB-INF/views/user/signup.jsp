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
			<div class="row">
				<div class="main_container">
					<div
						class="mt-16 integration-single-container rounded-xl bg-white py-16 px-5 shadow-lg md:px-10 tab">
						<h2 class="mb-2">회원 가입</h2>
						<p>정보를 등록 해주세요</p>
						<div
							class="relative my-8 text-center after:absolute after:left-0 after:top-1/2 after:z-[0] after:w-full after:border-b after:border-border after:content-['']">
							<span class="relative z-[1] inline-block bg-white px-2">Join
								Us</span>
						</div>

						<form method="POST" action="/good/user/signup.do">
							<div class="form-group" id="id-box">
								<label for="id" class="form-label text-xl">아이디</label> <input
									type="text" name="id" id="id" class="form-control"
									placeholder=" 5 ~ 20자 이내의 영어 대소문자, 숫자, (-),  (_)만 입력이 가능합니다. " />
								<input type="button" id="checkId" value="중복확인"> <span
									id="idError" class="tomato"></span>
							</div>
							<div class="form-group mt-4">
								<label for="password" class="form-label text-xl">비밀번호</label> <input
									type="password" name="pw" id="pw" class="form-control"
									placeholder=" 8-16자 이내의 영어 대소문자, 숫자와 (*), (-), (!)만 입력이 가능합니다. " />
								<span id="pwError" class="tomato"></span>
							</div>
							<div class="form-group mt-4">
								<label for="password_confirm" class="form-label text-xl"
									id="confirmPw">비밀번호 확인</label> <input type="password"
									id="pw_confirm" class="form-control" placeholder="비밀번호 확인" />
								<span id="pwCheckError" class="tomato"></span>
							</div>

							<div class="form-group mt-4">
								<label for="name" class="form-label text-xl">이름</label> <input
									type="text" id="name" name="name" class="form-control"
									placeholder="이름을 입력하세요." /> <span id="nameError"
									class="tomato"></span>
							</div>

							<div class="form-group mt-4">
								<label for="email" class="form-label text-xl">이메일</label> <input
									type="text" id="email" name="email" class="form-control"
									placeholder="이메일을 입력하세요." /> @ <input type="text" id="domain"
									name="domain" class="form-control" placeholder="직접입력" /> <select
									id="domain-list">
									<option value="직접입력">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="nate.com">nate.com</option>
									<option value="yahoo.com">yahoo.com</option>
									<option value="yahoo.com">kakao.com</option>
									<option value="yahoo.com">msn.com</option>
								</select> <br /> <span class="tomato" id="emailError"></span>
							</div>

							<div class="form-group mt-4">
								<label for="tel" class="form-label text-xl">연락처</label> <input
									type="text" id="tel" name="tel" class="form-control"
									placeholder="연락처를 입력하세요." /> <span class="tomato"
									id="telError"></span>
							</div>

							<div class="form-group mt-4" id="address_group">
								<label for="address" class="form-label text-xl">주소</label> <input
									type="text" id="address" name="address" class="form-control"
									placeholder="주소를 입력하세요." /> <input type="button" value="주소찾기"
									id="addressapi" onclick="getAddress()" /> <span
									id="addressError" class="tomato"></span>
							</div>
							<div class="form-group mt-4">
								<label for="address" class="form-label text-xl">닉네임</label> <input
									type="text" id="nickname" name="nickname" class="form-control"
									placeholder="닉네임을 입력하세요." /> <input type="button"
									id="checkNick" value="중복확인"> <span class="tomato"
									id="nicknameError"></span>
							</div>

							<input class="btn btn-primary mt-10 block w-full" id="btn_signup"
								type="submit" value="회원가입" />
						</form>
					</div>
				</div>
			</div>
		</div>

	</section>
	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function getAddress() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var addr = ''; // 주소 변수
							var extraAddr = ''; // 참고항목 변수

							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								addr = data.roadAddress;
							} else { // 사용자가 지번 주소를 선택했을 경우(J)
								addr = data.jibunAddress;
							}

							// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
							if (data.userSelectedType === 'R') {
								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								if (data.bname !== ''
										&& /[동|로|가]$/g.test(data.bname)) {
									extraAddr += data.bname;
								}
								// 건물명이 있고, 공동주택일 경우 추가한다.
								if (data.buildingName !== ''
										&& data.apartment === 'Y') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraAddr !== '') {
									extraAddr = ' (' + extraAddr + ')';
								}

							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById("address").value = addr;

						}
					}).open();
		}

		$(document)
				.ready(
						function() {
							var errorId = [ "idError", "pwError",
									"pwCheckError", "telError", "emailError",
									"nicknameError", "nameError",
									"addressError" ];

							// 아이디 유효성 검사
							function validateId() {
								var idLimit = /^[a-zA-Z0-9-_]{5,20}$/; // 정규식 5~20자 (a~z, A~Z, 0~9, -, _만 입력 가능)
								if (!idLimit.test($('#id').val())) {
									$('#' + errorId[0])
											.text("사용할 수 없는 아이디입니다.");
								} else {
									$('#' + errorId[0]).text("");
								}
							}

							// 비밀번호 유효성 검사
							function validatePassword() {
								var pwLimit = /^[a-zA-Z0-9!*-]{10,20}$/; // 정규식 (a~z, A~Z, 0~9, *-! 만 입력 가능)
								if (!pwLimit.test($('#pw').val())) {
									$('#' + errorId[1]).text(
											"사용할 수 없는 비밀번호입니다.");
								} else {
									$('#' + errorId[1]).text("");
								}
							}

							// 비밀번호 일치 여부 확인
							function confirmPassword() {
								if ($('#pw').val() !== $('#pw_confirm').val()) {
									$('#' + errorId[2])
											.text("비밀번호가 일치하지 않습니다.");
								} else {
									$('#' + errorId[2]).text("");
								}
							}

							// 전화번호 유효성 검사
							function validateTel() {
								var telLimit = /^01[0|1|6|7|8|9]{1}[0-9]{8}$/; // 정규식 유효한 전화번호
								if (!telLimit.test($('#tel').val())) {
									$('#' + errorId[3])
											.text("유효하지 않은 전화번호입니다.");
								} else {
									$('#' + errorId[3]).text("");
								}
							}

							// 이메일 유효성 검사
							function validateEmail() {
								var emailLimit = /[0-9a-zA-Z\-_.]/; // 이메일 유효성 검사 정규식
								if (!emailLimit.test($('#email').val())) {
									$('#' + errorId[4]).text(
											"유효하지 않은 이메일 주소입니다.");
								} else {
									$('#' + errorId[4]).text("");
								}

								// 직접입력인 경우에는 도메인을 입력하도록 안내
								var domain = $('#domain').val();
								if (domain === "직접입력" || domain === "") {
									$('#' + errorId[4]).text("도메인을 입력하세요.");
								} else {
									$('#' + errorId[4]).text("");
								}
							}

							// 닉네임 입력 확인
							function validateNickname() {
								if ($('#nickname').val().trim() === "") {
									$('#' + errorId[5]).text("닉네임을 입력하세요.");
								} else {
									$('#' + errorId[5]).text("");
								}
							}

							// 이름 입력 확인
							function validateName() {
								if ($('#name').val().trim() === "") {
									$('#' + errorId[6]).text("이름을 입력하세요.");
								} else {
									$('#' + errorId[6]).text("");
								}
							}

							// 주소 입력 확인
							function validateAddress() {
								if ($('#address').val().trim() === "") {
									$('#' + errorId[7]).text("주소를 입력하세요.");
								} else {
									$('#' + errorId[7]).text("");
								}
							}

							// 아이디 중복 확인
							$('#checkId').click(function() {
								var id = $('#id').val();
								$.ajax({
									type : 'POST',
									url : '/good/user/checkid.do',
									data : 'id=' + id,
									dataType : 'json',
									success : function(result) {
										if (result == 0) {
											alert('사용 가능한 아이디 입니다.');
										} else {
											alert('사용 불가능한 아이디 입니다.');
										}
									},
									error : function(a, b, c) {
										console.log(a, b, c);
									}
								});
							});

							//닉네임 중복 확인
							$('#checkNick').click(function() {
								var nickname = $('#nickname').val();
								console.log($('#nickname').val());
								$.ajax({
									type : 'POST',
									url : '/good/user/checknick.do',
									data : 'nickname=' + nickname,
									dataType : 'json',
									success : function(result) {
										if (result == 0) {
											alert('사용 가능한 닉네임 입니다.');
										} else {
											alert('사용 불가능한 닉네임 입니다.');
										}
									},
									error : function(a, b, c) {
										console.log(a, b, c);
									}
								});
							});

							// 폼 제출
							$('form')
									.on(
											'submit',
											function(event) {
												event.preventDefault(); // 기본 폼 제출 방지

												validateId();
												validatePassword();
												confirmPassword();
												validateTel();
												validateEmail();
												validateNickname();
												validateName();
												validateAddress();

												// 모든 입력 필드가 유효한 경우에만 제출
												var isValid = errorId
														.every(function(error) {
															return $(
																	'#' + error)
																	.text()
																	.trim() === "";
														});

												if (isValid) {
													// 데이터 준비
													var formData = {
														id : $('#id').val(),
														pw : $('#pw').val(),
														tel : $('#tel').val(),
														email : $('#email')
																.val(),
														domain : $('#domain')
																.val(),
														nickname : $(
																'#nickname')
																.val(),
														name : $('#name').val(),
														address : $('#address')
																.val()
													};

													// AJAX 요청
													$
															.ajax({
																type : 'POST',
																url : '/good/user/signup.do',
																data : formData,
																dataType : 'json',
																success : function(
																		response) {
																	if (response.success) {
																		alert("회원가입이 완료되었습니다. It's GoodJob의 멤버가 되신 것을 환영합니다.");
																		window.location.href = "/good/user/signin.do";
																	} else {
																		alert("회원가입에 실패했습니다. 다시 시도해주세요.");
																	}
																},
																error : function(
																		a, b, c) {
																	// 오류 처리
																	console
																			.error(
																					a,
																					b,
																					c);
																}
															});
												}
											});

							// 입력 값 변경 시 유효성 검사
							$('#id').on('input', validateId);
							$('#pw').on('input', validatePassword);
							$('#pw_confirm').on('input', confirmPassword);
							$('#tel').on('input', validateTel);
							$('#email').on('input', validateEmail);
							$('#domain').on('input', validateEmail);
							$('#nickname').on('input', validateNickname);
							$('#name').on('input', validateName);
							$('#address').on('input', validateAddress);

							$('#domain-list').change(function() {
								// 선택된 옵션의 값을 가져옵니다.
								var selectedDomain = $(this).val();
								$('#domain').val(selectedDomain);
							});

						});
	</script>
</body>
</html>