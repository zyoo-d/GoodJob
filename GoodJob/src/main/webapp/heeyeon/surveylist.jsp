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
.survey-form {
	margin: 50px 20px;
	background-color: white;
	padding: 10px;
	border-radius: 8px;
	border: 1px solid #6375F1;
}

.section {
	padding: 10px 30px;
	border-bottom: 1px solid #EEEEEE;
}

.section:last-child {
	border-bottom: none;
}

.section h2 .survey-num {
	font-size: 30px;
}

.section h2 {
	font-size: 24px;
	color: #595959;
	padding: 5px 10px;
}

.radio-toolbar {
	display: flex;
	justify-content: space-between;
	margin: 20px 5px;
	background: #F2F2F2;
	padding: 10px 50px;
	border-radius: 30px;
}

.radio-toolbar input[type="radio"] {
	display: none;
}

.radio-toolbar label {
	color: #595959;
	padding: 5px 20px;
	border-radius: 20px;
	display: inline-block;
	position: relative;
	cursor: pointer;
}

.radio-toolbar input[type="radio"]:checked+label {
	background: linear-gradient(184.78deg, #7476FA 7.64%, #0071BC 120.07%);
	color: #FFF;
}

.radio-toolbar label:hover {
	background: linear-gradient(184.78deg, #7476FA 7.64%, #0071BC 120.07%);
	color: #FFF;
}

.btn-survey-check {
	margin: 10px 20px;
	font-size: 1.25rem;
	border-radius: 8px;
}
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
	<div id="itvWriteContainer">
		<section class="page-hero pt-16 pb-6">
			<div class="container">
				<div class="card" id="itvWriteQnA">
					<div class="card-content-wrapper">
						<div class="px-4 text-center sizing">
							<h1 class="mt-3 gradiTitle">
								<span>Survey for GoodJob!</span>
							</h1>
							<p class="mt-6">
								설문조사 결과를 토대로 000님의 맞춤 기업을 추천해드립니다.<br> 성실하게 설문조사에 응하실 경우 보다
								정확한 정보가 제공됩니다.
							</p>

						</div>
						<form class="survey-form">
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">1 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">2 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">3 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">4 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">5 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">6 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">7 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">8 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">9 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">10 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">11 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">12 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">13 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">14 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">15 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">16 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">17 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">18 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">19 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">20 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">21 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">22 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">23 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">24 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
							<!-- 질문시작 -->
							<div class="section">
								<h2>
									Q<span class="survey-num">25 </span>맞춤기업 확인을 위한 질문입니다.
								</h2>
								<div class="radio-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">매우아니다</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">아니다</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">보통이다</label>
									<input type="radio" id="opinion4" name="opinion" value="4">
									<label for="opinion4">그렇다</label> <input type="radio"
										id="opinion5" name="opinion" value="5"> <label
										for="opinion5">매우그렇다</label>
								</div>
							</div>
							<!-- 질문끝 -->
						</form>
						<button type="submit" class="btn btn-primary btn-survey-check">다음</button>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>