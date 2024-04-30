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
				<h1>스크랩</h1>
				<div class="section-header-breadcrumb">
					<div class="breadcrumb-item active">
						<a href="#">스크랩</a>
					</div>
					<div class="breadcrumb-item">내 스크랩 보기</div>
				</div>
			</div>
			<div class="section-body">
				<h2 class="section-title">내 스크랩 보기</h2>
				<div class="row" id="scrapCards">
					<div class="col-6 col-md-4 col-lg-3">
						<div class="card">
							<div class="card-body">
								<div class="logo">
									<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
									<i class="fas fa-star" id="star"></i>
								</div>
								<div class="infoCard">
									<h3 class="mt-0">회사명</h3>
									<p class="mt-0">제약회사</p>
									<span class="hiring">채용중</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-6 col-md-4 col-lg-3">
						<div class="card">
							<div class="card-body">
								<div class="logo">
									<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
									<i class="fas fa-star" id="star"></i>
								</div>
								<div class="infoCard">
									<h3 class="mt-0">회사명</h3>
									<p class="mt-0">제약회사</p>
									<span class="hiring">채용중</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-6 col-md-4 col-lg-3">
						<div class="card">
							<div class="card-body">
								<div class="logo">
									<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
									<i class="fas fa-star" id="star"></i>
								</div>
								<div class="infoCard">
									<h3 class="mt-0">회사명</h3>
									<p class="mt-0">제약회사</p>
									<span class="hiring">채용중</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-6 col-md-4 col-lg-3">
						<div class="card">
							<div class="card-body">
								<div class="logo">
									<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
									<i class="fas fa-star" id="star"></i>
								</div>
								<div class="infoCard">
									<h3 class="mt-0">회사명</h3>
									<p class="mt-0">제약회사</p>
									<span class="hiring">채용중</span>
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