<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<style>

</style>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
</head>

<body>
	<section class="sidebar fixed" id="trend">
		<div class="career-single-sidebar mt-8 lg:mt-0">
			<div class="mb-8 rounded-xl bg-white py-10 px-7 shadow-lg">
				<h5 class="h5">Trend Keyword</h5>
				<ul class="text-dark items-center">
					<li class="my-1"><a class="" href="#"># 이슈</a></li>
					<li class="my-1"><a class="" href="#"># 이슈</a></li>
					<li class="my-1"><a class="" href="#"># 이슈</a></li>
					<li class="my-1"><a class="" href="#"># 이슈</a></li>
					<li class="my-1"><a class="" href="#"># 이슈</a></li>
					<li class="my-1"><a class="" href="#"># 이슈</a></li>
				</ul>
				<button class="btn btn-primary block btn-sm">더 보기</button>
			</div>
		</div>
	</section>
	<!-- Banner -->
	<section class="section banner relative" id="banner">
		<div class="container">
			<div class="row items-center">
				<div class="lg:col-6">
					<h2 class="banner-title">IT's 굿잡! 당신을 위한 맞춤형 추천을 준비했어요.</h2>
					<p class="mt-6">당신의 성향을 분석하고 니즈와 선호도에 딱 맞는 맞춤형 기업 추천을 제공합니다.
						함께하는 과정에서 당신이 빛을 발할 수 있도록 도와드릴게요.</p>
					<a class="btn btn-white mt-8" href="#">굿잡forU 이동하기</a>
				</div>
				<div class="lg:col-6">
					<img class="w-full object-contain"
						src="/good/assets/images/banner-img.png" width="603" height="396"
						alt="" />
				</div>
			</div>
		</div>
	</section>
	<!-- ./end Banner -->

	<!-- Key features -->
	<section class="section key-feature relative" id="mainCard">
		<div class="container">
			<h3 class="mt-10">
				요즘 뜨는 기업!<span class="material-symbols-outlined" id="filterHire">done채용중</span>
			</h3>
			<div
				class="key-feature-grid mt-5 grid grid-cols-2 gap-7 md:grid-cols-3 xl:grid-cols-4">
				<div
					class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg">
					<div class="logo">
						<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
						<span class="material-symbols-outlined scrap">star</span>
					</div>
					<div class="infoCard">
						<h3 class="h4 text-xl lg:text-2xl">회사명</h3>
						<p class="desciption">제약회사</p>
						<span class="hiring">채용중</span>
					</div>

				</div>
				<div
					class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg">
					<div class="logo">
						<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
						<span class="material-symbols-outlined scrap">star</span>
					</div>
					<div class="infoCard">
						<h3 class="h4 text-xl lg:text-2xl">회사명</h3>
						<p class="desciption">제약회사</p>
						<span class="hiring">채용중</span>
					</div>

				</div>
				<div
					class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg">
					<div class="logo">
						<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
						<span class="material-symbols-outlined scrap">star</span>
					</div>
					<div class="infoCard">
						<h3 class="h4 text-xl lg:text-2xl">회사명</h3>
						<p class="desciption">제약회사</p>
						<span class="hiring">채용중</span>
					</div>

				</div>
				<div
					class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg">
					<div class="logo">
						<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
						<span class="material-symbols-outlined scrap">star</span>
					</div>
					<div class="infoCard">
						<h3 class="h4 text-xl lg:text-2xl">회사명</h3>
						<p class="desciption">제약회사</p>
						<span class="hiring">채용중</span>
					</div>

				</div>
				<div
					class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg">
					<div class="logo">
						<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
						<span class="material-symbols-outlined scrap">star</span>
					</div>
					<div class="infoCard">
						<h3 class="h4 text-xl lg:text-2xl">회사명</h3>
						<p class="desciption">제약회사</p>
						<span class="hiring">채용중</span>
					</div>

				</div>
				<div
					class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg">
					<div class="logo">
						<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
						<span class="material-symbols-outlined scrap">star</span>
					</div>
					<div class="infoCard">
						<h3 class="h4 text-xl lg:text-2xl">회사명</h3>
						<p class="desciption">제약회사</p>
						<span class="hiring">채용중</span>
					</div>

				</div>
				<div
					class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg">
					<div class="logo">
						<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
						<span class="material-symbols-outlined scrap">star</span>
					</div>
					<div class="infoCard">
						<h3 class="h4 text-xl lg:text-2xl">회사명</h3>
						<p class="desciption">제약회사</p>
						<span class="hiring">채용중</span>
					</div>

				</div>
				<div
					class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg">
					<div class="logo">
						<img class="" src="/good/assets/images/logo/default.jpg" alt="" />
						<span class="material-symbols-outlined scrap">star</span>
					</div>
					<div class="infoCard">
						<h3 class="h4 text-xl lg:text-2xl">회사명</h3>
						<p class="desciption">제약회사</p>
						<span class="hiring">채용중</span>
					</div>

				</div>
			</div>
		</div>
	</section>
	<!-- ./end Key features -->


	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
		
	</script>
</body>
</html>