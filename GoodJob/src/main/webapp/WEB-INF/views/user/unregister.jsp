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
					<div class="breadcrumb-item">회원탈퇴</div>
				</div>
			</div>
			<div class="card">
				<div class="card-header">
					<h4>회원탈퇴</h4>
				</div>
				<div class="card-body">
					<div class="jumbotron text-center">
						<h2>지금까지 이용해주셔서 감사드립니다.</h2>
						<p class="text-muted mt-3 text-left text-lg">
							- 탈퇴하신 아이디는 복구가 불가능하며, 추후 동일한 아이디로 재가입이 되지않습니다.<br> - 회원탈퇴 시
							등록한 게시물은 삭제되지 않으므로, 삭제를 원하시면 회원탈퇴 전에 삭제해 주시기 바랍니다.
						</p>
						<div class="form-group text-lg">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" name="agree" id="agree">
								<label for="agree">유의 사항을 모두 확인했으며, 이에 동의합니다.</label>
							</div>
						</div>
						<button type="submit" class="btn btn-primary btn-lg" id="btnUnregister">탈퇴하기</button>
					</div>
				</div>
			</div>

		</section>
	</div>
	<%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>
	<script>
 		$('#btnUnregister').click(function() {
			if(!$('#agree').is(':checked')) {
				alert('유의 사항을 확인해 주세요.');
				return;
			} else {
				alert('탈퇴!');
			}
		});
	</script>
</body>
</html>