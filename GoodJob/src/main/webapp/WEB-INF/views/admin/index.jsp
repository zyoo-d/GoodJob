<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/WEB-INF/views/inc/adminasset.jsp"%>
</head>

<body>
	<div id="app">
		<div class="main-wrapper main-wrapper-1">
			<%@include file="/WEB-INF/views/inc/adminheader.jsp"%>
			<!-- Main Content -->
			<div class="main-content">
				<section class="section">

					<div class="row">

						<div class="col-lg-3 col-md-6 col-sm-6 col-14">
							<div class="card card-statistic-2">
								<div class="card-chart"></div>
								<div class="card-icon shadow-primary bg-primary">
									<i class="fas fa-users"></i>
								</div>
								<div class="card-wrap">
									<div class="card-header">
										<h4>일일 신규 가입자</h4>
									</div>
									<div class="card-body">${getNewSubscriberCount}</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-6 col-sm-6 col-14">
							<div class="card card-statistic-2">
								<div class="card-chart"></div>
								<div class="card-icon shadow-primary bg-primary">
									<i class="fas fa-users"></i>
								</div>
								<div class="card-wrap">
									<div class="card-header">
										<h4>일일 방문자</h4>
									</div>
									<div class="card-body">${visit_count}</div>
								</div>
							</div>
						</div>

						<div class="col-lg-3 col-md-6 col-sm-6 col-14">
							<div class="card card-statistic-2">
								<div class="card-chart"></div>
								<div class="card-icon shadow-primary bg-primary">
									<i class="fas fa-building"></i>
								</div>
								<div class="card-wrap">
									<div class="card-header">
										<h4>등록된 기업수</h4>
									</div>
									<div class="card-body">${countCompanys}</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-6 col-sm-6 col-14">
							<div class="card card-statistic-2">
								<div class="card-chart">
									<!-- <canvas id="balance-chart" height="80"></canvas> -->
								</div>
								<div class="card-icon shadow-primary bg-primary">
									<i class="fas fa-user-plus"></i>
								</div>
								<div class="card-wrap">
									<div class="card-header">
										<h4>등록된 채용공고</h4>
									</div>
									<div class="card-body">${jobPostings_count}</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-8">
							<div class="card">
								<div
									class="card-header d-flex justify-content-between align-items-center">
									<h4>방문자 통계</h4>
									<div class="btn-group btn-group-toggle btn-group-spacing"
										data-toggle="buttons">
										<button type="button" class="btn btn-primary btn-sm active"
											id="recentDaysBtn">최근 10일</button>
										<button type="button" class="btn btn-primary btn-sm"
											id="monthStatsBtn">한달 통계</button>
										<button type="button" class="btn btn-primary btn-sm"
											id="yearStatsBtn">1년 통계</button>
									</div>
								</div>
								<div class="card-body">
									<canvas id="myChart" height="161.9"></canvas>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="card">
								<div class="card-header">
									<h4>게시판 통계</h4>
								</div>
								<div class="card-body">
									<canvas id="myChart3" height="140"></canvas>
								</div>
							</div>
							<div class="card">
								<div class="card-header">
									<img src="/good/assets/images/icons/siren.png"
										style="margin-right: 5px; padding-bottom: 10px;">
									<h4>최근 신고된 게시글</h4>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table class="table table-bordered table-md">

											<c:forEach items="${recentReportList}" var="recentReportList">
												<tr class="go-to-board"
													onclick="goToBoard('${recentReportList.sub_type}', ${recentReportList.seq});">
													<td>${recentReportList.title}</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
							</div>

						</div>
						<div class="col-lg-4"></div>
					</div>
					<div class="row">
						<div class="col-md-6"></div>
						<div class="col-md-6"></div>
					</div>



					<div class="row">
						<div class="col-md-8">
							<div class="card">
								<div class="card-header">
									<img src="/good/assets/images/icons/fire.png"
										style="margin-right: 5px;">
									<h4>일주일간 조회수 높은 게시글</h4>
									<div class="card-header-action">
										<a href="#" class="btn btn-danger">View More <i
											class="fas fa-chevron-right"></i></a>
									</div>
								</div>
								<div class="card-body p-0">
									<div class="table-responsive table-invoice">
										<table class="table table-striped">
											<tr>
												<th class="p-0 text-center">회원 아이디</th>
												<th class="p-0 text-center">제목</th>
												<th class="p-0 text-center">조회수</th>
												<th class="p-0 text-center">작성일</th>
												<th class="p-0 text-center">게시판 종류</th>
											</tr>
											
											<c:forEach items="${boardList}" var="board" begin="0" end="4">
												<tr class="go-to-board"
													onclick="goToBoard('${board.board_type}', ${board.seq});">
													<td class="p-0 text-center">${board.id }</td>
													<td class="font-weight-600 p-0 text-center">${board.title}</td>
													<td class="p-0 text-center">${board.views}</td>
													<td class="p-0 text-center">${board.regDate}</td>
													
													
													<td class="p-0 text-center">
													    <c:choose>
													        <c:when test="${board.board_type eq 'qna'}">
													            질문게시판(여기어때)
													        </c:when>
													        <c:when test="${board.board_type eq 'study'}">
													            스터디 게시판
													        </c:when>
													    </c:choose>
													</td>

													
												</tr>
											</c:forEach>

										</table>
									</div>
								</div>
							</div>
						</div>


						<div class="col-lg-4">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-14">
									<div class="card card-statistic-2">
										<div class="card-chart"></div>
										<div class="card-icon shadow-primary bg-primary">
											<i class="fas fa-comments"></i>
										</div>
										<div class="card-wrap">
											<div class="card-header">
												<h4>승인대기 리뷰</h4>
											</div>
											<div class="card-body">${pendingReview}</div>
										</div>
									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-14">
									<div class="card card-statistic-2">
										<div class="card-chart"></div>
										<div class="card-icon shadow-primary bg-primary">
											<i class="fas fa-comments"></i>
										</div>
										<div class="card-wrap">
											<div class="card-header">
												<h4>승인대기 면접후기</h4>
											</div>
											<div class="card-body">${pendingInterview}</div>
										</div>
									</div>
								</div>

							</div>

							<div class="card">
								<div
									class="card-header d-flex justify-content-between align-items-center"
									id="index-data-info">
									<h4>데이터 현황</h4>
									<span>2024-04-19 기준</span>
									<!-- 자스 함수 동적생성임 -->
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table class="table table-striped">
											<tbody>
												<tr>
													<th>데이터 종류</th>
													<th>업데이트 현황</th>
													<th>업로드</th>
												</tr>
												<c:forEach items="${updateDate}" var="updateDate">
													<tr>
														<td class="p-0 text-center">${updateDate.key}</td>
														<td class="p-0 text-center" style="font-size: 1.1rem;">${updateDate.value}</td>
														<td><button type="button">업로드</button></td>
													</tr>
												</c:forEach>
												<!-- 추가적인 데이터 항목을 여기에 작성할 수 있습니다 -->
											</tbody>
										</table>
									</div>
								</div>
							</div>

						</div>


					</div>
			</section>
		</div>
		<footer class="main-footer">
			<div class="footer-left">
				Copyright &copy; 2018
				<div class="bullet"></div>
				Design By <a href="https://nauval.in/">Muhamad Nauval Azhar</a>
			</div>
			<div class="footer-right"></div>
		</footer>
	</div>
</div>
	<!-- Page Specific JS File -->
	<script>
  
  document.addEventListener('DOMContentLoaded', function() {
	    var ctx = document.getElementById('myChart').getContext('2d');
	    var myChart;

	    function getYAxisStep(data) {
	        const maxValue = Math.max(...data);
	        const step = Math.max(Math.round(maxValue / 10), 100);
	        return step;
	    }

	    function createChart(label, data, labels, yAxisStep = 100) {
	        if (myChart) {
	            myChart.destroy();
	        }

	        myChart = new Chart(ctx, {
	            type: 'line',
	            data: {
	                labels: labels,
	                datasets: [{
	                    label: label,
	                    data: data,
	                    borderWidth: 2,
	                    backgroundColor: 'transparent',
	                    borderColor: 'rgba(63,82,227,1)',
	                    pointBorderWidth: 0,
	                    pointRadius: 3.5,
	                    pointBackgroundColor: 'transparent',
	                    pointHoverBackgroundColor: 'rgba(63,82,227,1)',
	                    fill: false
	                }]
	            },
	            options: {
	                legend: {
	                    display: false
	                },
	                scales: {
	                    yAxes: [{
	                        gridLines: {
	                            drawBorder: false,
	                            color: '#f2f2f2',
	                        },
	                        ticks: {
	                            beginAtZero: true,
	                            stepSize: yAxisStep,
	                            callback: function(value, index, values) {
	                                return value;
	                            }
	                        }
	                    }],
	                    xAxes: [{
	                        gridLines: {
	                            display: false,
	                            tickMarkLength: 15,
	                        }
	                    }]
	                },
	            }
	        });
	    }

	    
	    
	    function renderChart(title, data, labels) {
	        const yAxisStep = getYAxisStep(data);
	        createChart(title, data, labels, yAxisStep);
	    }

	    const recentDays = [<c:forEach var="date" items="${recentDays}">'${date}',</c:forEach>];
	    const recentVisitors = [<c:forEach var="count" items="${recentVisitors}">${count},</c:forEach>];
	    const monthLabels = [<c:forEach var="label" items="${monthLabels}">'${label}',</c:forEach>];
	    const monthData = [<c:forEach var="count" items="${monthVisitors}">${count},</c:forEach>];
	    const yearLabels = [<c:forEach var="label" items="${yearLabels}">'${label}',</c:forEach>];
	    const yearData = [<c:forEach var="count" items="${yearVisitors}">${count},</c:forEach>];

	    const recentDaysBtn = document.getElementById('recentDaysBtn');
	    const monthStatsBtn = document.getElementById('monthStatsBtn');
	    const yearStatsBtn = document.getElementById('yearStatsBtn');

	    renderChart('최근 10일', recentVisitors, recentDays);

	    recentDaysBtn.addEventListener('click', function() {
	        renderChart('최근 10일', recentVisitors, recentDays);
	    });

	    monthStatsBtn.addEventListener('click', function() {
	        renderChart('한달 통계', monthData, monthLabels);
	    });

	    yearStatsBtn.addEventListener('click', function() {
	        renderChart('1년 통계', yearData, yearLabels);
	    });
	});
  </script>

	<script>
  var ctx3 = document.getElementById("myChart3").getContext('2d');
  var myChart3 = new Chart(ctx3, {
    type: 'doughnut',
    data: {
      datasets: [{
        data: [
        
	       ${countBoardList['QnA']},
	       ${countBoardList['Study']},
	       ${countBoardList['CompanyReview']},
	       ${countBoardList['Interview']},
       
        ],
        backgroundColor: [
          '#ff5252',
          '#ffa500',
          '#32cd32',
          '#00bfff',
        ],
        label: '게시판'
      }],
      labels: [
        '질문',
        '스터디',
        '기업리뷰',
        '면접후기',
      ],
    },
    options: {
      responsive: true,
      legend: {
        position: 'top',
        
      },
    }
  });
  
  function goToBoard(sub_type, seq){
	  
	  if(sub_type == "qna"){
		  location.href="/good/user/qna/viewqna.do?qna_seq=" + seq;
	  } else if(sub_type == "study"){
		  location.href="/good/user/study/viewstudy.do?std_seq=" + seq;
	  } else if(sub_type == "company"){
		  location.href="/good/user/company/companyview.do?cp_seq=" + seq;
	  }
  }
  
  
  var currentDate = new Date();

  var formattedDate = currentDate.toISOString().slice(0,10);

  var spanText = document.querySelector('#index-data-info span').textContent;
  var updatedText = spanText.replace(/\d{4}-\d{2}-\d{2}/, formattedDate);
  document.querySelector('#index-data-info span').textContent = updatedText;
  
  
  
  </script>

</body>
</html>