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

          <div class="row">
        
            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
              <div class="card card-statistic-2">
                <div class="card-chart">
                  <!-- <canvas id="balance-chart" height="80"></canvas> -->
                </div>
                <div class="card-icon shadow-primary bg-primary">
                  <i class="fas fa-users"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>일일 방문자</h4>
                    
                  </div>
                  <div class="card-body">
                    300
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
              <div class="card card-statistic-2">
                <div class="card-chart">
                  <!-- <canvas id="balance-chart" height="80"></canvas> -->
                </div>
                <div class="card-icon shadow-primary bg-primary">
                  <i class="fas fa-comments"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>승인대기 리뷰</h4>
                  </div>
                  <div class="card-body">
                    300
                  </div>
                </div>
              </div>
            </div>
            
            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
              <div class="card card-statistic-2">
                <div class="card-chart">
                  <!-- <canvas id="sales-chart" height="80"></canvas> -->
                </div>
                <div class="card-icon shadow-primary bg-primary">
                  <i class="fas fa-building"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>등록된 기업수</h4>
                  </div>
                  <div class="card-body">
                    4,732
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
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
                  <div class="card-body">
                    300
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-8">
              <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                  <h4>방문자 통계</h4>
                  <div class="btn-group btn-group-toggle btn-group-spacing" data-toggle="buttons">
                    <button type="button" class="btn btn-primary btn-sm active" id="recentDaysBtn">최근 10일</button>
                    <button type="button" class="btn btn-primary btn-sm" id="monthStatsBtn">한달 통계</button>
                    <button type="button" class="btn btn-primary btn-sm" id="yearStatsBtn">1년 통계</button>
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
                  <h4>최근 신고된 게시글</h4>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-bordered table-md">
                      <tr>
                          <td>ㅁㄴㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇ</td>
                      </tr>
                      <tr>
                          <td>ㅁㄴㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇ</td>
                      </tr>
                      <tr>
                          <td>ㅁㄴㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇ</td>
                      </tr>
                      <tr>
                          <td>ㅁㄴㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇ</td>
                      </tr>
                    </table>
                  </div>
                </div>
              </div>

            </div>
            <div class="col-lg-4">

            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
            </div>
            <div class="col-md-6">
            </div>
          </div>
          <div class="row">
            <div class="col-md-8">
              <div class="card">
                <div class="card-header">
                  <h4>일일 조회수 높은 게시글</h4>
                  <div class="card-header-action">
                    <a href="#" class="btn btn-danger">View More <i class="fas fa-chevron-right"></i></a>
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
                      <tr>
                        <td class="p-0 text-center">qwer124</td>
                        <td class="font-weight-600 p-0 text-center">네이버 면접 후기</td>
                        <td class="p-0 text-center">500</td>
                        <td class="p-0 text-center">2024-04-16</td>
                        <td class="p-0 text-center">
                          면접 후기
                        </td>
                      </tr>
                      <tr>
                        <td class="p-0 text-center">qwer124</td>
                        <td class="font-weight-600 p-0 text-center">네이버 면접 후기</td>
                        <td class="p-0 text-center">500</td>
                        <td class="p-0 text-center">2024-04-16</td>
                        <td class="p-0 text-center">
                          면접 후기
                        </td>
                      </tr>
                      <tr>
                        <td class="p-0 text-center">qwer124</td>
                        <td class="font-weight-600 p-0 text-center">네이버 면접 후기</td>
                        <td class="p-0 text-center">500</td>
                        <td class="p-0 text-center">2024-04-16</td>
                        <td class="p-0 text-center">
                          면접 후기
                        </td>
                      </tr>
                      <tr>
                        <td class="p-0 text-center">qwer124</td>
                        <td class="font-weight-600 p-0 text-center">네이버 면접 후기</td>
                        <td class="p-0 text-center">500</td>
                        <td class="p-0 text-center">2024-04-16</td>
                        <td class="p-0 text-center">
                          면접 후기
                        </td>
                      </tr>
                      <tr>
                        <td class="p-0 text-center">qwer124</td>
                        <td class="font-weight-600 p-0 text-center">네이버 면접 후기</td>
                        <td class="p-0 text-center">500</td>
                        <td class="p-0 text-center">2024-04-16</td>
                        <td class="p-0 text-center">
                          면접 후기
                        </td>
                      </tr>
                     
                    </table>
                  </div>
                </div>
              </div>
            </div>


            <div class="col-lg-4 col-md-6 col-sm-6 col-12">
              <div class="card card-statistic-2">
                <div class="card-chart">
                  <canvas id="balance-chart" height="80"></canvas>
                </div>
                <div class="card-icon shadow-primary bg-primary">
                  <i class="fas fa-user-plus"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>일일 신규 가입자</h4>
                  </div>
                  <div class="card-body">
                    3
                  </div>
                </div>
              </div>

              <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center" id="index-data-info">
                  <h4>데이터 현황</h4>
                  <span>2024-04-19 기준</span>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <tbody>
                        <tr>
                          <td class="p-0 text-center">국민연금</td>
                          <td class="p-0 text-center" style="font-size: 1.1rem;">2024-04-07</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">DART</td>
                          <td class="p-0 text-center" style="font-size: 1.1rem;">2024-03-17</td>
                        </tr>
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
          Copyright &copy; 2018 <div class="bullet"></div> Design By <a href="https://nauval.in/">Muhamad Nauval Azhar</a>
        </div>
        <div class="footer-right">
          
        </div>
      </footer>
    </div>
  </div>

  <!-- Page Specific JS File -->
  <script src="/good/assets/js/page/index.js"></script>
  
</body>
</html>