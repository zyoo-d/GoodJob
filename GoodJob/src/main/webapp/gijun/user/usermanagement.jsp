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
            <h1>유저 관리</h1>
          </div>

          <div class="section-body">
            <!-- <h2 class="section-title">기업 목록</h2>
            <p class="section-lead">2024-04-26 기준</p> -->

            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-header">
                    <h4>유저 목록</h4>
                    <div class="card-header-form">
                      <form>
                        <div class="input-group">
                          <input type="text" class="form-control" placeholder="검색어를 입력하세요.">
                          <div class="input-group-btn">
                            <button class="btn btn-primary"><i class="fas fa-search"></i></button>
                          </div>
                          <a href="#" class="btn btn-icon icon-left btn-primary" ><i class="far fa-edit"></i> 차단 관리</a>
                          <a href="#" class="btn btn-icon icon-left btn-danger"><i class="fas fa-times"></i> 회원 탈퇴</a>
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
                          <th class="p-0 text-center">유저 아이디</th>
                          <th class="p-0 text-center">이름</th>
                          <th class="p-0 text-center">게시글 수</th>
                          <th class="p-0 text-center">리뷰 수</th>
                          <th class="p-0 text-center">가입일자</th>
                          <th class="p-0 text-center">신고 횟수</th>
                          <th class="p-0 text-center">차단 횟수</th>
                          <th class="p-0 text-center">상태</th>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-1">
                              <label for="checkbox-1" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td class="p-0 text-center">qwer1234</td>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            300
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">정상</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-2">
                              <label for="checkbox-2" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td class="p-0 text-center">qwer1234</td>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            300
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">탈퇴</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-3">
                              <label for="checkbox-3" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td class="p-0 text-center">qwer1234</td>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            300
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">차단</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-1">
                              <label for="checkbox-1" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td class="p-0 text-center">qwer1234</td>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            300
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">정상</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-1">
                              <label for="checkbox-1" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td class="p-0 text-center">qwer1234</td>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            300
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">정상</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-1">
                              <label for="checkbox-1" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td class="p-0 text-center">qwer1234</td>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            300
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">정상</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-1">
                              <label for="checkbox-1" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td class="p-0 text-center">qwer1234</td>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            300
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">정상</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-1">
                              <label for="checkbox-1" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td class="p-0 text-center">qwer1234</td>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            300
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">정상</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-1">
                              <label for="checkbox-1" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td class="p-0 text-center">qwer1234</td>
                          <td class="p-0 text-center">
                            홍길동
                          </td>
                          <td class="p-0 text-center">
                            300
                          </td>
                          <td class="p-0 text-center">3</td>
                          <td class="p-0 text-center">2024-04-16</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">5</td>
                          <td class="p-0 text-center">정상</td>
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

  <!-- General JS Scripts -->
  <script src="assets/modules/jquery.min.js"></script>
  <script src="assets/modules/popper.js"></script>
  <script src="assets/modules/tooltip.js"></script>
  <script src="assets/modules/bootstrap/js/bootstrap.min.js"></script>
  <script src="assets/modules/nicescroll/jquery.nicescroll.min.js"></script>
  <script src="assets/modules/moment.min.js"></script>
  <script src="assets/js/stisla.js"></script>
  
  <!-- JS Libraies -->
  <script src="assets/modules/jquery-ui/jquery-ui.min.js"></script>

  <!-- Page Specific JS File -->
  <script src="assets/js/page/components-table.js"></script>
  
  <!-- Template JS File -->
  <script src="assets/js/scripts.js"></script>
  <script src="assets/js/custom.js"></script>
</body>
</html>