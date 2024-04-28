<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<style>
</style>
<head>
<meta charset="UTF-8">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no"
	name="viewport">
<title>마이페이지 &mdash; IT's GOODJOB</title>
<%@include file="/WEB-INF/views/inc/adminasset.jsp"%>
</head>

<body>
	<%@include file="/WEB-INF/views/inc/mypageheader.jsp"%>
	<!-- Main Content -->
	<div class="main-content">
		<section class="section">
			<div class="section-header">
				<h1>Mypage</h1>
				<div class="section-header-breadcrumb">
					<div class="breadcrumb-item active">
						<a href="#">개인정보</a>
					</div>
					<div class="breadcrumb-item">회원정보수정</div>
				</div>
			</div>
			<div class="section-body">
				<div class="col-12 col-md-12 col-lg-7">
					<div class="card">
						<form method="post" class="needs-validation" novalidate="">
							<div class="card-header">
								<h4>회원정보수정</h4>
							</div>
							<div class="card-body">
								<div class="row">
									<div class="form-group col-md-6 col-12">
										<label>First Name</label> <input type="text"
											class="form-control" value="Ujang" required="">
										<div class="invalid-feedback">Please fill in the first
											name</div>
									</div>
									<div class="form-group col-md-6 col-12">
										<label>Last Name</label> <input type="text"
											class="form-control" value="Maman" required="">
										<div class="invalid-feedback">Please fill in the last
											name</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-7 col-12">
										<label>Email</label> <input type="email" class="form-control"
											value="ujang@maman.com" required="">
										<div class="invalid-feedback">Please fill in the email</div>
									</div>
									<div class="form-group col-md-5 col-12">
										<label>Phone</label> <input type="tel" class="form-control"
											value="">
									</div>
								</div>
								<div class="row">
									<div class="form-group col-12">
										<label>Bio</label>
										<textarea class="form-control summernote-simple">Ujang maman is a superhero name in <b>Indonesia</b>, especially in my family. He is not a fictional character but an original hero in my family, a hero for his children and for his wife. So, I use the name as a user in this template. Not a tribute, I'm just bored with <b>'John Doe'</b>.</textarea>
									</div>
								</div>
								<div class="row">
									<div class="form-group mb-0 col-12">
										<div class="custom-control custom-checkbox">
											<input type="checkbox" name="remember"
												class="custom-control-input" id="newsletter"> <label
												class="custom-control-label" for="newsletter">Subscribe
												to newsletter</label>
											<div class="text-muted form-text">You will get new
												information about products, offers and promotions</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer text-right">
								<button class="btn btn-primary">Save Changes</button>
							</div>
						</form>
					</div>
				</div>
			</div>
	</div>
	</section>
	</div>
	<%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>

</body>
</html>