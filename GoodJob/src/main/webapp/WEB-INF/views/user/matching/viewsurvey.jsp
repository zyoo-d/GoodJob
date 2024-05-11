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
	<section class="page-hero pt-16 pb-6">
		<div class="container">
			<div class="card" id="itvWriteQnA">
				<div class="card-content-wrapper">
					<div class="px-4 text-center sizing">
						<h1 class="mt-3 gradiTitle">
							<span>Survey for GoodJob!</span>
						</h1>
						<p class="survey-list mt-6">
							설문조사 결과를 토대로 ${name}님의 맞춤 기업을 추천해드립니다.<br> 성실하게 설문조사에 응하실 경우
							보다 정확한 정보가 제공됩니다.
						</p>

					</div>
					<c:if test="${mypage=='N'}">
						<form class="survey-form" id="surveyForm" method="post"
							action="/good/user/matching/addprefer.do?edit=N">
					</c:if>
					<c:if test="${mypage=='Y'}">
						<form class="survey-form" id="surveyForm" method="post"
							action="/good/user/matching/addprefer.do?edit=Y">
					</c:if>
					<!-- 질문시작 -->
					<c:forEach items="${list}" var="dto">
						<div class="survey section">
							<h2>
								Q<span class="survey-num">${dto.rnum} </span>${dto.sv_content}</h2>
							<div class="survey radio-toolbar">
								<input type="radio" id="${dto.sv_category}${dto.sv_seq}1"
									name="${dto.sv_category}${dto.sv_seq}" value="5" required>
								<label for="${dto.sv_category}${dto.sv_seq}1">매우그렇다</label> <input
									type="radio" id="${dto.sv_category}${dto.sv_seq}2"
									name="${dto.sv_category}${dto.sv_seq}" value="2"> <label
									for="${dto.sv_category}${dto.sv_seq}2">그렇다</label> <input
									type="radio" id="${dto.sv_category}${dto.sv_seq}3"
									name="${dto.sv_category}${dto.sv_seq}" value="-2"> <label
									for="${dto.sv_category}${dto.sv_seq}3">아니다</label> <input
									type="radio" id="${dto.sv_category}${dto.sv_seq}4"
									name="${dto.sv_category}${dto.sv_seq}" value="-5"> <label
									for="${dto.sv_category}${dto.sv_seq}4">매우아니다</label>
							</div>
						</div>
					</c:forEach>
					<!-- 질문끝 -->

					</form>
					<button form="surveyForm" type="submit"
						class="btn btn-primary btn-survey-check">제출하기</button>
				</div>
			</div>
		</div>
	</section>
	<script>
		
	</script>
</body>
</html>