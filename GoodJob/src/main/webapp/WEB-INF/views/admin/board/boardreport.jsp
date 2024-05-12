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
					<div class="section-header">
						<h1>신고 게시글 관리</h1>
					</div>

					<div class="section-body">

						<div class="row">

							<div class="col-lg-9">
								<div class="card">
									<div class="card-body">
										<canvas id="myChart" height="54"></canvas>
									</div>
								</div>
							</div>

							<div class="col-lg-3 col-md-6 col-sm-6 col-12">
								<div class="card card-statistic-2">
									<div class="card-chart"></div>
									<div class="card-icon shadow-primary bg-primary">
										<i class="fas fa-bell"></i>
									</div>
									<div class="card-wrap">
										<div class="card-header">
											<h4>당일 신고된 게시글 수</h4>
										</div>
										<div class="card-body">${todayReportCount}</div>
									</div>
								</div>
								<div class="card card-statistic-2">
									<div class="card-chart"></div>
									<div class="card-icon shadow-primary bg-primary">
										<i class="fas fa-check"></i>
									</div>
									<div class="card-wrap">
										<div class="card-header">
											<h4>처리된 게시글</h4>
										</div>
										<div class="card-body">${totalCompleteReport}</div>
									</div>
								</div>
							</div>





						</div>




						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h4 style="color: rgb(52, 57, 94);">신고 게시글 목록</h4>
										<div class="card-header-form">
											<form>
												<div class="input-group">
													<input type="text" class="form-control"
														placeholder="검색어를 입력하세요.">
													<div class="input-group-btn">
														<button class="btn btn-primary">
															<i class="fas fa-search"></i>
														</button>
													</div>
													<a href="#" class="btn btn-icon icon-left btn-primary"
														style="margin-left: 10px;"><i class="far fa-edit"></i>
														차단 관리</a> <a href="#"
														class="btn btn-icon icon-left btn-danger"
														style="margin-left: 10px;"><i class="fas fa-times"></i>
														회원 탈퇴</a>
												</div>
											</form>
										</div>
									</div>
									<div class="card-body p-0">
										<div class="table-responsive">
											<table class="table table-striped">
												<tr>
													<th class="p-0 text-center">
														<div class="custom-checkbox custom-control">
															<input type="checkbox" data-checkboxes="mygroup"
																data-checkbox-role="dad" class="custom-control-input"
																id="checkbox-all"> <label for="checkbox-all"
																class="custom-control-label">&nbsp;</label>
														</div>
													</th>
													<th class="p-0 text-center">작성자</th>
													<th class="p-0 text-center">게시판 유형</th>
													<th class="p-0 text-center">글 유형</th>
													<th class="p-0 text-center">제목</th>
													<th class="p-0 text-center">작성 일자</th>
													<th class="p-0 text-center">누적 신고수</th>
												</tr>

												<c:forEach items="${allReportList}" var="report">
													<tr>
														<td class="p-0 text-center">
															<div class="custom-checkbox custom-control">
																<input type="checkbox" data-checkboxes="mygroup"
																	class="custom-control-input"
																	id="checkbox-${status.index}"> <label
																	for="checkbox-${status.index}"
																	class="custom-control-label">&nbsp;</label>
															</div>
														</td>
														<td class="p-0 text-center">${report.writer_id}</td>
														<td class="p-0 text-center">${report.sub_type}</td>
														<td class="p-0 text-center">${report.type}</td>
														<td class="p-0 text-center">${report.title}</td>
														<td class="p-0 text-center">${report.regdate}</td>
														<td class="p-0 text-center">${report.report_count}</td>
													</tr>
												</c:forEach>

											</table>
										</div>
									</div>

									<div class="card-footer text-center">
										<nav class="d-inline-block">
											<ul class="pagination mb-0">
												<c:if test="${pageUtil.hasPreviousPage()}">
													<li class="page-item"><a class="page-link"
														href="/good/admin/boardreport.do?page=${Math.max(pageUtil.startPage - 1, 1)}">
															<i class="fas fa-chevron-left"></i>
													</a></li>
												</c:if>

												<c:set var="startPage"
													value="${pageUtil.currentPage - (pageUtil.currentPage - 1) % 5}" />
												<c:set var="endPage"
													value="${startPage + 4 < pageUtil.totalPages ? startPage + 4 : pageUtil.totalPages}" />

												<c:forEach begin="${startPage}" end="${endPage}" var="page">
													<li
														class="page-item ${page == pageUtil.currentPage ? 'active' : ''}">
														<a class="page-link"
														href="/good/admin/boardreport.do?page=${page}">
															${page} </a>
													</li>
												</c:forEach>

												<c:if test="${pageUtil.hasNextPage()}">
													<li class="page-item"><a class="page-link"
														href="/good/admin/boardreport.do?page=${endPage + 1}">
															<i class="fas fa-chevron-right"></i>
													</a></li>
												</c:if>
											</ul>
										</nav>
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
	<script src="/good/assets/js/page/components-table.js"></script>

	<script>
  var ctx = document.getElementById('myChart').getContext('2d');
  
  var qna = [
	  <c:forEach var="date" items="${recentDays}">
	    ${recentReportCount[date.toString()].qna  != null ? recentReportCount[date.toString()].qna : 0},
	  </c:forEach>
	];

	var qna_cm = [
	  <c:forEach var="date" items="${recentDays}">
	    ${recentReportCount[date.toString()].qna_cm != null ? recentReportCount[date.toString()].qna_cm : 0},
	  </c:forEach>
	];

	var std = [
	  <c:forEach var="date" items="${recentDays}">
	    ${recentReportCount[date.toString()].std != null ? recentReportCount[date.toString()].std : 0},
	  </c:forEach>
	];

	var std_cm = [
	  <c:forEach var="date" items="${recentDays}">
	    ${recentReportCount[date.toString()].std_cm != null ? recentReportCount[date.toString()].std_cm : 0},
	  </c:forEach>
	];

	var live = [
		  <c:forEach var="date" items="${recentDays}">
		    ${recentReportCount[date.toString()].live != null ? recentReportCount[date.toString()].live : 0},
		  </c:forEach>
		];
	
	console.log("qna:", qna);
	console.log("qna_cm:", qna_cm);
	console.log("std:", std);
	console.log("std_cm:", std_cm);
	console.log("live:", live);
  
  var maxValue = Math.max.apply(null, qna.concat(qna_cm, std, std_cm, live));
  
  var yMax = Math.ceil(maxValue / 10) * 10 + 10;
  
  var myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [
    	  <c:forEach var="date" items="${recentDays}">
          '${date}',
      </c:forEach>
    	  
      ],
      datasets: [
        {
          label: '질문게시글',
          data: qna,
          borderWidth: 2,
          borderColor: '#6777ef',
          pointBackgroundColor: '#ffffff',
          pointRadius: 4,
          fill: false
        },
        {
          label: '질문댓글',
          data: qna_cm,
          borderWidth: 2,
          borderColor: '#fc544b',
          pointBackgroundColor: '#ffffff',
          pointRadius: 4,
          fill: false
        },
        {
          label: '스터디게시글',
          data: std,
          borderWidth: 2,
          borderColor: '#47c363',
          pointBackgroundColor: '#ffffff',
          pointRadius: 4,
          fill: false
        },
        {
          label: '스터디댓글',
          data: std_cm,
          borderWidth: 2,
          borderColor: '#f3b760',
          pointBackgroundColor: '#ffffff',
          pointRadius: 4,
          fill: false
        },
        {
          label: '기업실시간댓글',
          data: live,
          borderWidth: 2,
          borderColor: '#9467bd',
          pointBackgroundColor: '#ffffff',
          pointRadius: 4,
          fill: false
        }
      ]
    },
    options: {
      title: {
          display: true,
          text: '게시판 신고 통계',
          fontSize: 14, // 제목 글꼴 크기 (원하는 대로 조정 가능)
          fontColor: '#34395e'
        },
      legend: {
        display: true
      },
      scales: {
        yAxes: [
          {
            gridLines: {
              drawBorder: false,
              color: '#f2f2f2'
            },
            ticks: {
              beginAtZero: true,
              stepSize: 10,
              max: yMax 
            }
          }
        ],
        xAxes: [
          {
            ticks: {
              display: true
            },
            gridLines: {
              display: false
            }
          }
        ]
      }
    }
  });
  
  
  
  </script>


</body>
</html>