<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>

<style>
#cp_info {
	display: inline-block;
	height: 200PX;
}
.space-evenly#compare_fnc {
width: 200px;
padding: none;
margin: 20px;
}

#cp_fnc {
	padding: 20px 0;
}
#cp_fnc > div {
	border-bottom : 1px solid black;
	width: 100%;
}
#goInfo {
	margin-top: 0;
}
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
	<section class="section pt-0">
		<div class="container mt-16">
			<div class="card px-10">
				<div class="px-4 text-center">
					<h1 class="mt-3 gradiTitle">
						<span>기업 비교</span>
					</h1>
					<p class="mt-6">비교하고 싶은 기업을 지정해보세요.</p>

					<div class="moving-btn">
						<a href="#" class="btn btnList">기업 다시 비교하기</a>
					</div>
				</div>



				<div></div>

				<div class="my-5 border-b border-border">
					<div class="row mt-10 integration-tab-items cpcComCard">

						<c:forEach items="${list}" var="dto">
							<div class="mb-8 md:col-6 lg:col-4 integration-tab-item"
								data-groups='["social"]'>
								<div
									class="rounded-xl bg-white px-10 pt-11 pb-8 shadow-lg cpComInfo">
									<div
										class="integration-card-head flex items-center space-x-4 cpCimg">
										<img src="${dto.image}" alt="" />
										<div>
											<h4 class="h4">${dto.cp_name}</h4>
											<span class="font-medium cpComFont">${dto.idst_name }</span>
										</div>
									</div>
									<div class="my-5 border-y border-border py-5 smallradarChart"
										id="cp_info">
										<span>대표 : ${dto.ceo}</span><br /> <span>평균 근속년수 :
											${dto.hire_avr_year}</span><br /> <span>사원 수 :
											${dto.hire_member }</span><br /> <span>설립일 : ${dto.founded == null ? '정보 없음' : dto.founded}</span><br />
										<span>주소 : ${dto.cp_address}</span><br />


									</div>
									<div class="job_meta">
										<c:forEach var="tag" items="${dto.tag_list}">
											<span class="job-keyword">${tag}</span>
										</c:forEach>
									</div>
								</div>
							</div>
						</c:forEach>
<h4>IT's Good Job 기업 점수</h4>
						<div class="cpcCpChart">
							<canvas id="comparisonBarChart"></canvas>
						</div>
					</div>
				</div>
				<h4>재무정보 비교</h4>
				<div class="flex">
				<div class="cpsCompanyInfo">
								<div class="row mt-8 integration-tab-items">

									<c:forEach var="dto" items="${list}" varStatus="status">
										<div class="mb-8 md:col-6 lg:col-4 integration-tab-item"
											data-groups='["social"]'>
											<div
												class="rounded-xl bg-white px-8 py-8 shadow-lg min-h-400">
												<div
													class="pb-5 integration-card-head flex items-center space-x-4 border-b border-border min-h-[105px]">
													<img src="${dto.cp_name}" alt="" />
													<div>
														<h5 class="h5">${dto.cp_name}</h5>
														<span class="font-medium">${dto.idst_name}</span>
													</div>
												</div>
												
												<div id="cp_fnc">
												<div class="font-medium">${dto.fnc_sales}</div><br>
												<div class="font-medium">${dto.fnc_income}</div><br>
												<div class="font-medium">${dto.fnc_ebit}</div><br>
												<div class="font-medium">${dto.hire_avr_salary}</div><br>
<!-- 												<div class="my-5 py-5 smallradarChart"> -->
<%-- 													<canvas id="cp${status.index + 1}SugradarChart"></canvas> --%>
<!-- 												</div> -->
												</div>
												<div
													class="my-5 py-5 border-y border-border flex space-evenly" id="goInfo">

													<button class="btn btn-primary h-[48px] w-full rounded-[50px] leading-[30px]">기업상세 보기</button>

												</div>
											</div>
										</div>

									</c:forEach>

								</div>

							</div>
</div>
				<h4>리뷰 점수 비교</h4>
				<div class="flex">
				<div class="cpsCompanyInfo">
								<div class="row mt-8 integration-tab-items">

									<c:forEach var="rv" items="${rvlist}" >
										<div class="mb-8 md:col-6 lg:col-4 integration-tab-item"
											data-groups='["social"]'>
											<div
												class="rounded-xl bg-white px-8 py-8 shadow-lg min-h-400">
												
												
												<div id="cp_fnc">
												<div class="font-medium">복지 점수: ${rv.welfare_score}</div><br>
												<div class="font-medium">성장 점수: ${rv.growth_score}</div><br>
												<div class="font-medium">안정성 점수: ${rv.stability_score}</div><br>
												<div class="font-medium">연봉 점수: ${rv.salary_score}</div><br>
												<div class="font-medium">문화 점수: ${rv.culture_score}</div><br>
<%-- 												<div class="font-medium">평균연봉: ${dto.hire_avr_salary}</div><br> --%>
<!-- 												<div class="my-5 py-5 smallradarChart"> -->
<%-- 													<canvas id="cp${status.index + 1}SugradarChart"></canvas> --%>
<!-- 												</div> -->
												</div>
												<div
													class="my-5 py-5 border-y border-border flex space-evenly" id="goInfo">

													<button class="btn btn-primary h-[48px] w-full rounded-[50px] leading-[30px]">기업상세 보기</button>

												</div>
											</div>
										</div>

									</c:forEach>

								</div>

							</div>
</div>
			</div>

		</div>
	</section>


	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.4.1/chart.min.js"></script>
	<script>
					
	var backgroundcolorList = ['rgba(255, 99, 132)', 'rgba(54, 162, 235)', 'rgba(255, 206, 86)'];
	var borderColorList = ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)'];
	
	var datasets = [];
	<c:forEach items="${list}" var="dto" varStatus="status">
	    datasets.push({
	        label: '${dto.cp_name}',
	        backgroundColor: backgroundcolorList[${status.index}],
	        borderColor: borderColorList[${status.index}],
	        borderWidth: 1,
	        data: [${dto.salary}, ${dto.culture}, ${dto.potential}, ${dto.stability}, ${dto.welfare}]
	    });
	    console.log(${status.index} );
	</c:forEach>

	var ctx = document.getElementById('comparisonBarChart').getContext('2d');
	var comparisonBarChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: ['연봉', '조직문화', '성장가능성', '근속률', '복지'],
	        datasets: datasets
	    },
	    options: {
	        scales: {
	            y: {
	                beginAtZero: true
	            }
	        }
	    }
	});

	
	</script>
</body>
</html>