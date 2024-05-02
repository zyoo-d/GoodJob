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
            <h1>리뷰 관리</h1>
          </div>

          <div class="section-body">


            
            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-header">
                    <h4 class="review-h4">승인 대기 목록</h4>
                  </div>
                  <div class="card-body p-0">
                    <div class="table-responsive">
                      <table class="table table-striped">
                        <tr>
                          <th class="p-0 text-center">유저 아이디</th>
                          <th class="p-0 text-center">기업명</th>
                          <th class="p-0 text-center">리뷰 수</th>
                          <th class="p-0 text-center">신고수</th>
                          <th class="p-0 text-center">가입 일자</th>
                          <th class="p-0 text-center">작성 일자</th>
                          
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            네이버
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">2024-04-16</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            네이버
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">2024-04-16</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            네이버
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">2024-04-16</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            네이버
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">2024-04-16</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            네이버
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">2024-04-16</td>
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
            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-header">
                    <h4 class="review-h4">승인 반려 목록</h4>
                  </div>
                  <div class="card-body p-0">
                    <div class="table-responsive">
                      <table class="table table-striped">
                        <tr>
                          <th class="p-0 text-center">유저 아이디</th>
                          <th class="p-0 text-center">기업명</th>
                          <th class="p-0 text-center">관리자 아이디</th>
                          <th class="p-0 text-center">반려 사유</th>
                          <th class="p-0 text-center">작성 일자</th>
                          <th class="p-0 text-center">재신청 일자</th>
                          
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            네이버
                          </td>
                          <td class="p-0 text-center">hong</td>
                          <td class="p-0 text-center">성의 없음</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">2024-04-16</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            네이버
                          </td>
                          <td class="p-0 text-center">hong</td>
                          <td class="p-0 text-center">성의 없음</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">2024-04-16</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            네이버
                          </td>
                          <td class="p-0 text-center">hong</td>
                          <td class="p-0 text-center">성의 없음</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">2024-04-16</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            네이버
                          </td>
                          <td class="p-0 text-center">hong</td>
                          <td class="p-0 text-center">성의 없음</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">-</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            네이버
                          </td>
                          <td class="p-0 text-center">hong</td>
                          <td class="p-0 text-center">성의 없음</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">-</td>
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
  <script src="/good/assets/js/page/components-table.js"></script>
  
</body>
</html>