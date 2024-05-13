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
					<h5 style="color: #404040;">"${keywords.best}${keywords.worst}
						사람!"</h5>
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
											<div class="pb-8">
												<div class="min-h-480">
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
													<div class="mt-5 py-5 flex space-evenly pl-4">
														<div>
															<span
																class="bg-gradient inline-flex h-16 w-16 items-center justify-center rounded-full">
																${companyDTO.matchScore}% </span>
														</div>
														<span class="m-auto">"${companyDTO.matchText}" </span>
													</div>
													<div class="job_meta">
														<c:forEach var="tag" items="${companyDTO.dto.tag_list}">
															<span class="job-keyword">${tag}</span>
														</c:forEach>
													</div>
												</div>
												<div class="my-6 py-6 border-border border-y">
													<div class="flex" id="matchtotal">
														<h4>리뷰 평점</h4>
														<h4>
															<i class="fa-solid fa-star cornflowerblue"></i>
															${companyDTO.dto.review_avg}
														</h4>
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

												<div id="cp_fnc" class="mb-6">
													<div id="salary_info">
														<h4>평균연봉</h4>
														<div>${companyDTO.dto.hire_avr_salary} <span>만원</span></div>
													</div>
													<div>
														<h6>매출액</h6>
														<canvas id="salesChart_${status.index}" width="384"
															height="200"></canvas>
													</div>
													<div>
														<h6>영업이익</h6>
														<canvas id="ebitChart_${status.index}" width="384"
															height="200"></canvas>
													</div>
													<div>
														<h6>당기순이익</h6>
														<canvas id="incomeChart_${status.index}" width="384"
															height="200"></canvas>
													</div>
												</div>


												<div class="text-center">
													<a
														href="/good/user/company/companyview.do?cp_seq=${companyDTO.cp_seq}"
														class="btn btn-sm btn-primary">상세보기</a>
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
									class="rounded-xl bg-white p-6 shadow-lg lg:p-match matchplus mcard">
									<div class="integration-card-head flex items-center space-x-4">
										<img src="${list.dto.image}" alt="">
										<div>
											<h5 class="h5">${list.dto.cp_name}</h5>
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
									<input type="hidden" name="cp_seq" value="${list.cp_seq}">
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

	  $('.mcard').click(function() {
			var cp_seq = $(this).find('input[name=cp_seq]').val();
			location.href = '/good/user/company/companyview.do?cp_seq=' + cp_seq;
		});
	  
	  var years = [2021, 2022, 2023];

	    <c:forEach items="${top3}" var="company" varStatus="status">
	    var salesData_${status.index} = [
	        ${flist[0][status.index]}, 
	        ${flist[0][status.index + 1]}, 
	        ${flist[0][status.index + 2]}
	    ];
	    var ebitData_${status.index} = [
	        ${flist[1][status.index]}, 
	        ${flist[1][status.index + 1]}, 
	        ${flist[1][status.index + 2]}
	    ];
	    var incomeData_${status.index} = [
	        ${flist[2][status.index]}, 
	        ${flist[2][status.index + 1]}, 
	        ${flist[2][status.index + 2]}
	    ];


	    var salesCtx_${status.index} = document.getElementById('salesChart_${status.index}').getContext('2d');
	    var salesChart_${status.index} = new Chart(salesCtx_${status.index}, {
	        type: 'bar',
	        data: {
	            labels: years,
	            datasets: [{
	                label: '매출액',
	                data: salesData_${status.index},
	                backgroundColor: 'rgba(75, 192, 192, 0.6)',
	                borderWidth: 1
	            }]
	        },
	        options: {
	            scales: {
	                y: {
	                    beginAtZero: true
	                }
	            }
	        }
	    });

	    var ebitCtx_${status.index} = document.getElementById('ebitChart_${status.index}').getContext('2d');
	    var ebitChart_${status.index} = new Chart(ebitCtx_${status.index}, {
	        type: 'bar',
	        data: {
	            labels: years,
	            datasets: [{
	                label: '영업이익',
	                data: ebitData_${status.index},
	                backgroundColor: 'rgba(255, 99, 132, 0.6)',
	                borderWidth: 1
	            }]
	        },
	        options: {
	            scales: {
	                y: {
	                    beginAtZero: true
	                }
	            }
	        }
	    });

	    var incomeCtx_${status.index} = document.getElementById('incomeChart_${status.index}').getContext('2d');
	    var incomeChart_${status.index} = new Chart(incomeCtx_${status.index}, {
	        type: 'line',
	        data: {
	            labels: years,
	            datasets: [{
	                label: '당기순이익',
	                data: incomeData_${status.index},
	                backgroundColor: 'rgba(54, 162, 235, 0.6)',
	                borderWidth: 1
	            }]
	        },
	        options: {
	            scales: {
	                y: {
	                    beginAtZero: true
	                }
	            }
	        }
	    });
	</c:forEach>
	</script>
</body>
</html>