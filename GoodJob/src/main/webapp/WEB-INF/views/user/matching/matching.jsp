<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>

<style>
.space-evenly {
	justify-content: space-evenly;
}

.matchplus {
	min-height: 200px;
	align-content: center;
}

#top3card>div:nth-child(2) {
	border-left-width: 1px;
	border-right-width: 1px;
	border-color: rgb(235, 235, 235);
}

.contents_item_row {
	display: flex;
	justify-content: flex-start;
}

#matchscore {
	margin-left: 35px;
	line-height: 2rem;
}

.contents_item_label {
	width: 68%;
}
#matchtotal {
	justify-content: center;
    gap: 26%;
    margin-bottom: 5px;
}
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
	<section class="section pt-0">
		<div class="container mt-16">
			<div class="card px-5 py-16">
				<div class="text-center px-4">
					<h1 class="my-3 gradiTitle">
						<span>IT's Good Job for U</span>
					</h1>
					<p class="mt-6">굿잡forU 추천은 고객님의 성향 조사를 기반으로, 매칭을 해드립니다.</p>
				</div>
				<div id="matchinfo" class="mt-4">
					<h3>'${name}'님의 성향 :</h3>
					<h5>"${keywords.best}${keywords.worst} 사람!"</h5>
				</div>
				<div>
					<div class="border-b border-border py-8 mt-6 mb-8">
						<div class="cpsCompanyList">
							<h3 class="pl-6">매칭 기업 Top3</h3>
							<div class="cpsCompanyInfo">
								<div
									class="row mt-8 integration-tab-items rounded-xl bg-white shadow-lg ml-4 mr-4"
									id="top3card">

									<c:forEach var="companyDTO" items="${top3}" varStatus="status">
										<div class="my-8 md:col-6 lg:col-4 integration-tab-item px-6"
											data-groups='["social"]'>
											<div class="pb-8 min-h-400">
												<div
													class="pb-5 integration-card-head flex items-center space-x-4 min-h-[105px]">
													<img src="${companyDTO.dto.image}" alt="" />
													<div>
														<h5 class="h5">${companyDTO.dto.cp_name}</h5>
														<span class="font-medium">${companyDTO.dto.idst_name}</span>
													</div>
												</div>
												<div class="my-5 py-5 smallradarChart">
													<canvas id="cp${status.index + 1}SugradarChart"></canvas>
												</div>
												<div class="my-5 py-5 flex space-evenly pl-4 border-border border-b">
													<div>
														<span
															class="bg-gradient inline-flex h-16 w-16 items-center justify-center rounded-full">
															${companyDTO.matchScore}% </span>
													</div>
													<span class="m-auto">"${companyDTO.matchText}"
													</span>

												</div>
												<div class="mb-5 pb-5">
													<div class="flex" id="matchtotal">
														<h4>리뷰 평점</h4><h4><i
																	class="fa-solid fa-star cornflowerblue"></i> ${companyDTO.dto.review_avg}</h4>
													</div>
													<div id="matchscore">
														<div class="contents_item_row">
															<div class="contents_item_label">연봉</div>
															<div class="avg_wrap">
																<span class="avg_icon color_or_400"><i
																	class="fa-solid fa-star gold"></i></span> <span
																	class="avg_text"> ${companyDTO.dto.sal_avg}</span>
															</div>
														</div>
														<div class="contents_item_row">
															<div class="contents_item_label">조직문화</div>
															<div class="avg_wrap">
																<span class="avg_icon color_or_400"><i
																	class="fa-solid fa-star gold"></i></span> <span
																	class="avg_text"> ${companyDTO.dto.cul_avg}</span>
															</div>
														</div>
														<div class="contents_item_row">
															<div class="contents_item_label">복지</div>
															<div class="avg_wrap">
																<span class="avg_icon color_or_400"><i
																	class="fa-solid fa-star gold"></i></span> <span
																	class="avg_text"> ${companyDTO.dto.wel_avg}</span>
															</div>
														</div>
														<div class="contents_item_row">
															<div class="contents_item_label">성장 가능성</div>
															<div class="avg_wrap">
																<span class="avg_icon color_or_400"><i
																	class="fa-solid fa-star gold"></i></span> <span
																	class="avg_text"> ${companyDTO.dto.pot_avg}</span>
															</div>
														</div>
														<div class="contents_item_row">
															<div class="contents_item_label">조직 안정성</div>
															<div class="avg_wrap">
																<span class="avg_icon color_or_400"><i
																	class="fa-solid fa-star gold"></i></span> <span
																	class="avg_text"> ${companyDTO.dto.stab_avg}</span>
															</div>
														</div>
													</div>
												</div>
												<div class="job_meta">

													<c:forEach var="tag" items="${companyDTO.dto.tag_list}">
														<span class="job-keyword">${tag}</span>
													</c:forEach>

												</div>
											</div>
										</div>

									</c:forEach>

								</div>

							</div>
						</div>
					</div>
				</div>

				<div class="cpsCompany">
					<h3 class="pl-6">아쉽게 순위에 들지 못한 기업</h3>
					<div class="row mt-8 cpsCompanyInfo">

						<c:forEach items="${list}" var="list">
							<div class="mb-8 sm:col-6 lg:col-4">
								<div
									class="rounded-xl bg-white p-6 shadow-lg lg:p-match matchplus">
									<div class="integration-card-head flex items-center space-x-4">
										<img src="${list.dto.image}" alt="">
										<div>
											<h5 class="h5">${list.dto.cp_name }</h5>
											<span class="font-medium">${list.dto.idst_name}</span>
										</div>

									</div>
									<div class="job_meta mt-2">
										<c:forEach items="${list.dto.tag_list}" var="tag">
											<span class="job-keyword text-xs">${tag}</span>
										</c:forEach>
									</div>

									<div class="text-right text-lg">
										<i class="fa-solid fa-star gold"></i> ${list.dto.review_avg}
									</div>
								</div>
							</div>
						</c:forEach>



					</div>
				</div>
			</div>
		</div>
	</section>

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.4.1/chart.min.js"></script>
	<script>
		<c:forEach var="companyDTO" items="${top3}" varStatus="status">
	    var ctx = document.getElementById('cp${status.index + 1}SugradarChart').getContext('2d');
	    var myRadarChart = new Chart(ctx, {
	      type: 'radar',
	      data: {
	        labels: ['성장 가능성', '연봉', '조직문화', '조직안정성', '복지'],
	        datasets: [{
	          label: '회사',
	          data: [${companyDTO.potential}, ${companyDTO.salary}, ${companyDTO.culture}, ${companyDTO.stability}, ${companyDTO.welfare}],
	          backgroundColor: 'rgba(255, 99, 132, 0.2)',
	          borderColor: 'rgba(255, 99, 132, 1)',
	          borderWidth: 1
	        }, {
	          label: '회원',
	          data: [${dto.potential}, ${dto.salary}, ${dto.culture}, ${dto.stability}, ${dto.welfare}],
	          backgroundColor: 'rgba(54, 162, 235, 0.2)',
	          borderColor: 'rgba(54, 162, 235, 1)',
	          borderWidth: 1
	        }]
	      },
	      options: {
	        scales: {
	          r: {
	            min: 0,
	            max: 100,
	            ticks: {
	              stepSize: 20
	            }
	          }
	        },
	        plugins: {
	          legend: {
	            display: false
	          }
	        }
	      }
	    });
	  </c:forEach>

	</script>
</body>
</html>