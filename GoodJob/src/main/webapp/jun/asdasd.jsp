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


	
	<div>

	</div>

				<div class="my-5 border-b border-border">
					<div class="row mt-10 integration-tab-items cpcComCard">
						<div class="mb-8 md:col-6 lg:col-4 integration-tab-item"
							data-groups='["social"]'>
							<div
								class="rounded-xl bg-white px-10 pt-11 pb-8 shadow-lg cpComInfo">

								<div
									class="integration-card-head flex items-center space-x-4 cpCimg">
									<img
										src="https://blog.kakaocdn.net/dn/UGS0q/btree5Viurw/l07AH1VgWJHm4stsAHLdL0/img.png"
										alt="" />
									<div>
										<h4 class="h4">Facebook</h4>
										<span class="font-medium cpComFont">Social media</span>
									</div>
								</div>
								<div class="my-5 border-y border-border py-5 smallradarChart">
									회사 정보</div>
								<div class="job_meta">
									<span class="job-keyword">자바</span> <span class="job-keyword">코딩테스트</span>
								</div>
							</div>
						</div>
						
						
						<div class="cpcCpChart">
							<canvas id="comparisonBarChart"></canvas>
						</div>
					</div>
				</div>


				<div class="flex">
					<div class="cpcComMemberChart">
						<canvas id="ComradarChart"></canvas>
					</div>

				</div>

			</div>

		</div>
	</section>



	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.4.1/chart.min.js"></script>
	<script>
		var ctx = document.getElementById('comparisonBarChart')
				.getContext('2d');
		var comparisonBarChart = new Chart(ctx, {
			type : 'bar',
			data : {
				labels : [ '연봉', '조직문화', '성장가능성', '근속률', '복지' ],
				datasets : [ {
					label : '쿠팡',
					backgroundColor : 'rgba(255, 99, 132, 0.2)',
					borderColor : 'rgba(255, 99, 132, 1)',
					borderWidth : 1,
					data : [ 60, 70, 80, 50, 60 ]
				}, {
					label : '카카오',
					backgroundColor : 'rgba(54, 162, 235, 0.2)',
					borderColor : 'rgba(54, 162, 235, 1)',
					borderWidth : 1,
					data : [ 80, 90, 70, 80, 90 ]
				}, {
					label : '페이스북',
					backgroundColor : 'rgba(255, 206, 86, 0.2)',
					borderColor : 'rgba(255, 206, 86, 1)',
					borderWidth : 1,
					data : [ 90, 80, 90, 70, 80 ]
				} ]
			},
			options : {
				scales : {
					y : {
						beginAtZero : true
					}
				}
			}
		});

		var ctx = document.getElementById('ComradarChart').getContext('2d');
		var myRadarChart = new Chart(ctx, {
			type : 'radar',
			data : {
				labels : [ '연봉', '근속률', '조직문화', '성장 가능성', '복지' ],
				datasets : [ {

					label : '회원', // 새로운 데이터셋의 라벨
					data : [ 40, 60, 90, 50, 60 ], // 새로운 데이터셋의 데이터
					backgroundColor : 'rgba(54, 162, 235, 0.2)', // fill color
					borderColor : 'rgba(54, 162, 235, 1)', // border color
					borderWidth : 1
				} ]
			},
			options : {
				scales : {
					r : {
						min : 0,
						max : 100,
						ticks : {
							stepSize : 20
						}
					}
				}
			}
		});
	</script>
</body>
</html>