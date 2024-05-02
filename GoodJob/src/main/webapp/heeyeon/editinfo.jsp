<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<style>
</style>
<%@include file="/WEB-INF/views/inc/adminasset.jsp"%>
<head>
<%@include file="/WEB-INF/views/inc/mypageheader.jsp"%>
</head>
<body>
	<!-- Main Content -->
	<div class="main-content">
		<section class="section">
			<div class="section-header">
				<h1>개인정보</h1>
				<div class="section-header-breadcrumb">
					<div class="breadcrumb-item active">
						<a href="#">개인정보</a>
					</div>
					<div class="breadcrumb-item">회원정보수정</div>
				</div>
			</div>
			<div class="card card-primary" id="editInfo">
				<div class="card-header">
					<h4>회원정보수정</h4>
				</div>
				<div class="card-body">
					<form method="POST">
						<div class="row">
							<div class="form-group col-6">
								<label for="id">ID</label> <input id="id" type="text"
									class="form-control" name="id" readonly>
							</div>
							<div class="form-group col-6">
								<label for="name">이름</label> <input id="name" type="text"
									class="form-control" name="name" readonly>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-6">
								<label for="password" class="d-block">비밀번호</label> <input
									id="password" type="password" class="form-control pwstrength"
									data-indicator="pwindicator" name="password" required>
								<div id="pwindicator" class="pwindicator">
									<div class="bar"></div>
									<div class="label"></div>
								</div>
							</div>
							<div class="form-group col-6">
								<label for="password2" class="d-block">비밀번호 확인</label> <input
									id="password2" type="password" class="form-control"
									name="password-confirm" required>
							</div>
						</div>
						<div class="form-group">
							<label for="email">Email</label> <input id="email" type="email"
								class="form-control" name="email" required>
						</div>
						<div class="form-group">
							<label for="address">주소</label>
							<div class="input-group mb-3">
								<input type="text" class="form-control" id="address"
									name="address" required readonly>
								<div class="input-group-append">
									<button class="btn btn-outline-primary" type="button"
										onclick="getAddress();">주소 찾기</button>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-6">
								<label>학력 정보(선택)</label>
								<div
									class="selectric-wrapper selectric-form-control selectric-selectric selectric-below">
									<div class="selectric-hide-select">
										<select class="form-control selectric" tabindex="-1">
											<option>고교 졸업 이하</option>
											<option>고등학교 졸업</option>
											<option>대학 졸업(2,3년제)</option>
											<option>대학 졸업(4년제)</option>
											<option>대학원 석사 졸업</option>
											<option>대학원 박사 졸업</option>
											<option>박사 졸업 이상</option>
										</select>
									</div>
								</div>
							</div>
							<div class="form-group col-6">
								<label>경력 정보(선택)</label> <input type="range"
									class="form-control" min="0" max="10" step="1">
							</div>
						</div>
						<div class="form-group">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" name="agree" class="custom-control-input"
									id="agree"> <label class="custom-control-label"
									for="agree">회원정보 수정에 동의합니다.</label>
							</div>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-lg btn-block"
								id="btnEditInfo">정보 수정</button>
						</div>
					</form>
				</div>
			</div>

		</section>
	</div>
	<%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		$('#btnEditInfo').click(function() {
			if (!$('#agree').is(':checked')) {
				alert('수정 사항을 확인하고 동의해 주세요.');
				return;
			} else {
				alert('수정!');
			}
		});

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
	</script>
</body>
</html>