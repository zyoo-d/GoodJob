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
			</div>
			<div class="section-body">
				<h2 class="section-title">OOO님, 안녕하세요.</h2>
				<div class="row" id="mypageMainCard">
					<div class="col-12 col-md-4 col-lg-4">
						<div class="card blue">
							<div class="card-body">
								<div class="media">
									<img class="mr-3" src="/good/assets/img/example-image-50.jpg"
										alt="Generic placeholder image">
									<div class="media-body">
										<h6 class="mt-0">면접스터디</h6>
										<p class="mb-0">스터디하며 함께 취업 준비해요!</p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-12 col-md-4 col-lg-4">
						<div class="card blue">
							<div class="card-body">
								<div class="media">
									<img class="mr-3" src="/good/assets/img/example-image-50.jpg"
										alt="Generic placeholder image">
									<div class="media-body">
										<h6 class="mt-0">면접후기</h6>
										<p class="mb-0">기업 면접 후기를 보고 대비해보세요.</p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-12 col-md-4 col-lg-4">
						<div class="card blue">
							<div class="card-body">
								<div class="media">
									<img class="mr-3" src="/good/assets/img/example-image-50.jpg"
										alt="Generic placeholder image">
									<div class="media-body">
										<h6 class="mt-0">여기어때?</h6>
										<p class="mb-0">이 기업이 어떤지 궁금할 땐 여기어때에서 물어보세요!</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>

</body>
</html>