<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <!-- <h2 class="section-title">기업 목록</h2>
            <p class="section-lead">2024-04-26 기준</p> -->

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
                  <div class="card-chart">
                  </div>
                  <div class="card-icon shadow-primary bg-primary">
                    <i class="fas fa-bell"></i>
                  </div>
                  <div class="card-wrap">
                    <div class="card-header">
                      <h4>당일 신고된 게시글 수</h4>
                    </div>
                    <div class="card-body">
                      4,732
                    </div>
                  </div>
                </div>
                <div class="card card-statistic-2">
                  <div class="card-chart">
                  </div>
                  <div class="card-icon shadow-primary bg-primary">
                    <i class="fas fa-check"></i>
                  </div>
                  <div class="card-wrap">
                    <div class="card-header">
                      <h4>처리된 게시글</h4>
                    </div>
                    <div class="card-body">
                      4,732
                    </div>
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
                          <input type="text" class="form-control" placeholder="검색어를 입력하세요.">
                          <div class="input-group-btn">
                            <button class="btn btn-primary"><i class="fas fa-search"></i></button>
                          </div>
                          <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 차단 관리</a>
                          <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 회원 탈퇴</a>
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
                              <input type="checkbox" data-checkboxes="mygroup" data-checkbox-role="dad" class="custom-control-input" id="checkbox-all">
                              <label for="checkbox-all" class="custom-control-label">&nbsp;</label>
                            </div>
                          </th>
                          <th class="p-0 text-center">작성자</th>
                          <th class="p-0 text-center">게시판 유형</th>
                          <th class="p-0 text-center">글 유형</th>
                          <th class="p-0 text-center">제목</th>
                          <th class="p-0 text-center">작성 일자</th>
                          <th class="p-0 text-center">누적 신고수</th>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-1">
                              <label for="checkbox-1" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td class="p-0 text-center">qwer1234</td>
                          <td class="p-0 text-center">질문게시판(여기어때)</td>
                          <td class="p-0 text-center">게시글</td>
                          <td class="p-0 text-center">홍보합니다</td>
                          <td class="p-0 text-center">2024-04-30</td>
                          <td class="p-0 text-center">40</td>
                        </tr>
                        


                      </table>
                    </div>
                  </div>
                  <div class="card-footer text-center">
                    <nav class="d-inline-block">
                      <ul class="pagination mb-0">
                        <li class="page-item disabled">
                          <a class="page-link" href="#" tabindex="-1"><i class="fas fa-chevron-left"></i></a>
                        </li>
                        <li class="page-item active"><a class="page-link" href="#">1 <span class="sr-only">(current)</span></a></li>
                        <li class="page-item">
                          <a class="page-link" href="#">2</a>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                          <a class="page-link" href="#"><i class="fas fa-chevron-right"></i></a>
                        </li>
                      </ul>
                    </nav>
                  </div>
                  
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
      <%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>
    </div>
  </div>

  <!-- Page Specific JS File -->
  <script src="/good/assets/js/page/components-table.js"></script>
 
  <script>
  var ctx = document.getElementById('myChart').getContext('2d');
  var myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['2023-04-21', '2023-04-22', '2023-04-23', '2023-04-24', '2023-04-25', '2023-04-26', '2023-04-27', '2023-04-28', '2023-04-29', '2023-04-30'],
      datasets: [
        {
          label: '질문게시글',
          data: [81, 68, 62, 75, 88, 72, 64, 78, 92, 85],
          borderWidth: 2,
          borderColor: '#6777ef',
          pointBackgroundColor: '#ffffff',
          pointRadius: 4,
          fill: false
        },
        {
          label: '질문댓글',
          data: [74, 67, 55, 62, 78, 68, 59, 65, 81, 72],
          borderWidth: 2,
          borderColor: '#fc544b',
          pointBackgroundColor: '#ffffff',
          pointRadius: 4,
          fill: false
        },
        {
          label: '스터디게시글',
          data: [51, 58, 65, 72, 64, 59, 71, 62, 55, 48],
          borderWidth: 2,
          borderColor: '#47c363',
          pointBackgroundColor: '#ffffff',
          pointRadius: 4,
          fill: false
        },
        {
          label: '스터디댓글',
          data: [29, 51, 47, 38, 32, 45, 33, 28, 41, 35],
          borderWidth: 2,
          borderColor: '#f3b760',
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
              max: 100
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