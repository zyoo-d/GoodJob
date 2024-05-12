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
			<div class="row items-center mt-6 mb-4">
				<div class="lg:col-6">
					<h2 class="banner-title">IT's 굿잡! 당신을 위한 맞춤형 추천을 준비했어요.</h2>
					<p class="mt-6">당신의 성향을 분석하고 니즈와 선호도에 딱 맞는 맞춤형 기업 추천을 제공합니다.
						함께하는 과정에서 당신이 빛을 발할 수 있도록 도와드릴게요.</p>
					<c:if test="${prefer==null||prefer=='0'}">
						<a href="/good/user/matching/viewsurvey.do"
							class="btn btn-white mt-8 gradiTitle btnMatch"><span>굿잡forU 이동하기</span></a>
					</c:if>
					<c:if test="${wish=='0'&&prefer=='1'}">
						<a href="/good/user/matching/viewwish.do"
							class="btn btn-white mt-8 gradiTitle btnMatch"><span>굿잡forU 이동하기</span></a>
					</c:if>
					<c:if test="${wish=='1'&&prefer=='1'}">
						<a href="/good/user/matching.do" class="btn btn-white mt-8 gradiTitle btnMatch"><span>굿잡forU 이동하기</span></a>
					</c:if>
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
			<div class="pt-2 px-4 text-center border-b border-border" id="trend">
				<h5 class="h5">Trend Keyword</h5>
				<ul class="text-dark items-center pb-6">
					<c:forEach items="${keywords}" var="key">
						<li><a target="_blank" href="https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&query=${key}"># ${key}</a></li>
					</c:forEach>
				</ul>
				<h6 class="h6">
					<i class="fa-solid fa-arrow-pointer"></i> Try to Click
				</h6>
			</div>
			<form action="/good/main.do" method="get">
				<h3 class="mt-10">
					요즘 뜨는 기업!
					<c:if test="${hiring==null||hiring=='n'}">
					<button name="hiring" type="submit"	id="filterHire" class="tag"	value="${hiring}"><i class="fa-solid fa-check"></i> 채용중</button>
					</c:if>
					<c:if test="${hiring=='y'}">
					<button name="hiring" type="submit"	id="filterHire" class="tag non"	value="${hiring}"><i class="fa-solid fa-check"></i> 채용중</button>
					</c:if>
				</h3>
			</form>
			<div
				class="key-feature-grid mt-5 grid grid-cols-2 gap-7 md:grid-cols-3 xl:grid-cols-4">
				<c:forEach items="${clist}" var="cdto" begin="0" end="15">

					<div
						class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg mcard">
						<div class="logo">
							<c:if test="${cdto.com_rcrt_cnt > 0}">
								<a class="hiring tag" href="#">채용중</a>
							</c:if>
							<div class="image-container"
								style="background-image: url('${cdto.image}');"></div>
						</div>
						<div class="infoCard">
							<h3 class="h4 text-xl lg:text-2xl pt-2">
								${cdto.cp_name}
							</h3>
							<div class="flex justify-between">
								<p class="desciption">${cdto.idst_name}</p>
								<span><i class="fa-solid fa-star gold"></i>
									${cdto.review_avg}</span>
							</div>
							<input type="hidden" name="cp_seq" value="${cdto.cp_seq}">
						</div>

					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- ./end Key features -->


	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	$('.mcard').click(function() {
		var cp_seq = $(this).find('input[name=cp_seq]').val();
		location.href = '/good/user/company/companyview.do?cp_seq=' + cp_seq;
	});
	</script>
</body>
</html>