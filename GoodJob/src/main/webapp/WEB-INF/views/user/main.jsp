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
<%@include file="/WEB-INF/views/inc/header.jsp"%>
</head>

<body>
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
		<div class="container card">
			<div class="pt-1 px-4 text-center border-b border-border" id="trend">
				<h5 class="h5">Trend Keyword</h5>
				<ul class="text-dark items-center pb-6">
					<li><a class="" href="#"># 이슈</a></li>
					<li><a class="" href="#"># 이슈</a></li>
					<li><a class="" href="#"># 이슈</a></li>
					<li><a class="" href="#"># 이슈</a></li>
					<li><a class="" href="#"># 이슈</a></li>
					<li><a class="" href="#"># 이슈</a></li>
				</ul>
				<button class="btn btn-outline-primary block btn-sm" id="mainUpdate">
					<i class="fa-solid fa-rotate-right"></i> 업데이트
				</button>
			</div>
			<form action = "/good/main.do" method="get">
			<h3 class="mt-10">
				요즘 뜨는 기업!<span><a id="filterHire" class="tag" href="#"><i
						class="fa-solid fa-check"></i> 채용중</a></span> 
				<input type="hidden" name="hiring" value="">
			</h3>
			</form>
			<div class="key-feature-grid mt-5 grid grid-cols-2 gap-7 md:grid-cols-3 xl:grid-cols-4">
			<c:forEach items="${clist}" var="cdto" begin="0" end="7">
		
				<div class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg">
					<div class="logo">
						<img class="" src="${cdto.image}" onerror="this.src='/good/asset/images/default.jpg'" alt="Company Logo" />
						<i class="fa-regular fa-bookmark scrap"></i>
					</div>
					<div class="infoCard">
					
						<h3 class="h4 text-xl lg:text-2xl"><a href="/good/user/company/companyview.do?cp_seq=${cdto.cp_seq}">${cdto.cp_name}</a></h3>
						<p class="desciption">${cdto.idst_name}</p>
						<c:if test="${cdto.com_rcrt_cnt > 0}">
						<a class="hiring tag" href="#">채용중</a>
						</c:if>
						
					</div>

				</div>
			</c:forEach>
			</div>
		</div>
	</section>
	<!-- ./end Key features -->


	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	

	
	
	</script>
</body>
</html>