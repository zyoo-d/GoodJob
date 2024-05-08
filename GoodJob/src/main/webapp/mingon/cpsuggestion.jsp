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
				<div class="text-center">
					<h1 class="mt-3 gradiTitle">
						<span>굿잡 for U</span>
					</h1>
					<p class="mt-6">회원님과 어울리는 회사를 조회 해보세요.</p>
				</div>
				<div>
					<div class="cpsChart">
						<canvas id="cpSugradarChart"></canvas>
					</div>

					<div class="cpsContent">
						<div class="cpsMember">
							<h3>홍길동님의 성향</h3>
							<p>결국 일도 '사람'이 모여서 하는 것!</p>
						</div>
					</div>
				</div>
				<div>
					<div class="border-t border-border py-10">
						<div class="cpsCompanyList">
							<h3>홍길동님과 어울리는 회사</h3>
							<div class="cpsCompanyInfo">
								<div class="row mt-10 integration-tab-items">
									<div class="mb-8 md:col-6 lg:col-4 integration-tab-item"
										data-groups='["social"]'>
										<div
											class="rounded-xl bg-white px-10 py-8 shadow-lg min-h-400">
											<div
												class="integration-card-head flex items-center space-x-4">
												<img
													src="https://blog.kakaocdn.net/dn/UGS0q/btree5Viurw/l07AH1VgWJHm4stsAHLdL0/img.png"
													alt="" />
												<div>
													<h4 class="h4">Facebook</h4>
													<span class="font-medium">Social media</span>
												</div>
											</div>
											<div class="my-5 border-y border-border py-5 smallradarChart">
												<canvas id="cp1SugradarChart"></canvas>
											</div>
											<div class="job_meta">
												<span class="job-keyword">자바</span> <span
													class="job-keyword">코딩테스트</span>
											</div>
										</div>
									</div>
									<div class="mb-8 md:col-6 lg:col-4 integration-tab-item"
										data-groups='["social"]'>
										<div
											class="rounded-xl bg-white px-10 pt-11 pb-8 shadow-lg min-h-400">
											<div
												class="integration-card-head flex items-center space-x-4">
												<img
													src="https://blog.kakaocdn.net/dn/bPWm4U/btqw8CtgkMu/pYxOoDLchCJHKrRkhElo7k/img.jpg"
													alt="" />
												<div>
													<h4 class="h4">카카오</h4>
													<span class="font-medium">Social media</span>
												</div>
											</div>
											<div class="my-5 border-y border-border py-5 smallradarChart">
												<canvas id="cp2SugradarChart"></canvas>
											</div>
											<div class="my-5 border-b border-border">
												<span><i class="fa-regular fa-star"></i> 3.8</span>
											</div>
											<div class="job_meta">
												<span class="job-keyword">자바</span> <span
													class="job-keyword">코딩테스트</span>
											</div>
										</div>
									</div>
									<div class="mb-8 md:col-6 lg:col-4 integration-tab-item"
										data-groups='["social"]'>
										<div
											class="rounded-xl bg-white px-10 pt-11 pb-8 shadow-lg min-h-400">
											<div
												class="integration-card-head flex items-center space-x-4">
												<img
													src="https://blog.kakaocdn.net/dn/bVRwBA/btrTP3pbb8O/y4jYh4jMCajjwxb5ZZHJUk/img.png"
													alt="" />
												<div>
													<h4 class="h4">네이버</h4>
													<span class="font-medium">Social media</span>
												</div>
											</div>
											<div class="my-5 border-y border-border py-5 smallradarChart">
												<canvas id="cp3SugradarChart"></canvas>
											</div>
											<div class="job_meta">
												<span class="job-keyword">자바</span> <span
													class="job-keyword">코딩테스트</span>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>

				<div class="cpsCompany">
					<h3>현재 채용 중이에요 !</h3>
					<div class="row mt-14">
						<div class="mb-8 sm:col-6 lg:col-4">
							<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
								<div class="relative inline-block comment-edit">
									<p class="education">
										<i class="fa-solid fa-calendar-days"></i> ~ 05.05(일)
									</p>
									<a class="tag" href="#">모집중</a>
								</div>
								<h4 class="my-6 line_limit">2024 네이버 공개 채용</h4>
							</div>
						</div>
						<div class="mb-8 sm:col-6 lg:col-4">
							<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
								<div class="relative inline-block comment-edit">
									<p class="education">
										<i class="fa-solid fa-calendar-days"></i> ~ 05.05(일)
									</p>
									<a class="tag" href="#">모집중</a>
								</div>
								<h4 class="my-6 line_limit">2024 쿠팡 공채</h4>
							</div>
						</div>
						<div class="mb-8 sm:col-6 lg:col-4">
							<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
								<div class="relative inline-block comment-edit">
									<p class="education">
										<i class="fa-solid fa-calendar-days"></i> ~ 05.05(일)
									</p>
									<a class="tag" href="#">모집중</a>
								</div>
								<h4 class="my-6 line_limit">삼성 2024 공채</h4>
							</div>
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
		var ctx = document.getElementById('cpSugradarChart').getContext('2d');
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

		var ctx = document.getElementById('cp1SugradarChart').getContext('2d');
		var myRadarChart = new Chart(ctx, {
			type : 'radar',
			data : {
				labels : [ '연봉', '근속률', '조직문화', '성장 가능성', '복지' ],
				datasets : [ {
					label : '회사',
					data : [ 50, 70, 80, 40, 50 ],
					backgroundColor : 'rgba(255, 99, 132, 0.2)', // fill color
					borderColor : 'rgba(255, 99, 132, 1)', // border color
					borderWidth : 1
				}, {
					label : '회원',
					data : [ 40, 60, 90, 50, 60 ],
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
				},
				plugins : {
					legend : {
						display : false
					// Set display property to false to hide legend
					}
				}
			}
		});

		var ctx = document.getElementById('cp2SugradarChart').getContext('2d');
		var myRadarChart = new Chart(ctx, {
			type : 'radar',
			data : {
				labels : [ '연봉', '근속률', '조직문화', '성장 가능성', '복지' ],
				datasets : [ {
					label : '회사',
					data : [ 50, 70, 80, 40, 50 ],
					backgroundColor : 'rgba(255, 99, 132, 0.2)', // fill color
					borderColor : 'rgba(255, 99, 132, 1)', // border color
					borderWidth : 1
				}, {
					label : '회원',
					data : [ 40, 60, 90, 50, 60 ],
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
				},
				plugins : {
					legend : {
						display : false
					// Set display property to false to hide legend
					}
				}
			}
		});

		var ctx = document.getElementById('cp3SugradarChart').getContext('2d');
		var myRadarChart = new Chart(ctx, {
			type : 'radar',
			data : {
				labels : [ '연봉', '근속률', '조직문화', '성장 가능성', '복지' ],
				datasets : [ {
					label : '회사',
					data : [ 50, 70, 80, 40, 50 ],
					backgroundColor : 'rgba(255, 99, 132, 0.2)', // fill color
					borderColor : 'rgba(255, 99, 132, 1)', // border color
					borderWidth : 1
				}, {
					label : '회원',
					data : [ 40, 60, 90, 50, 60 ],
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
				},
				plugins : {
					legend : {
						display : false
					// Set display property to false to hide legend
					}
				}
			}
		});
	</script>
</body>
</html>