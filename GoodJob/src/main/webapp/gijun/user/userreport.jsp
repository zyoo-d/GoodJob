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
            <h1>신고 접수 관리</h1>
          </div>

          <div class="section-body">
            <!-- <h2 class="section-title">기업 목록</h2>
            <p class="section-lead">2024-04-26 기준</p> -->

            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-header">
                    <h4>신고 내역 목록</h4>
                    <div class="card-header-form">
                      <form>
                        <div class="input-group">
                          <input type="text" class="form-control" placeholder="검색어를 입력하세요.">
                          <div class="input-group-btn">
                            <button class="btn btn-primary"><i class="fas fa-search"></i></button>
                          </div>
                          <a href="#" class="btn btn-icon icon-left btn-primary"><i class="far fa-edit"></i> 차단 관리</a>
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
                          <th class="p-0 text-center">작성자</th>
                          <th class="p-0 text-center">게시판 유형</th>
                          <th class="p-0 text-center">제목</th>
                          <th class="p-0 text-center">신고자</th>
                          <th class="p-0 text-center">신고 유형</th>
                          <th class="p-0 text-center">신고 내용</th>
                          <th class="p-0 text-center">신고 일자</th>
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
                          <td class="p-0 text-center">홍보합니다</td>
                          <td class="p-0 text-center">아무개</td>
                          <td class="p-0 text-center">홍보</td>
                          <td class="p-0 text-center">이 사람 홍보해요</td>
                          <td class="p-0 text-center">2024-04-30</td>
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
                          <td class="p-0 text-center">홍보합니다</td>
                          <td class="p-0 text-center">아무개</td>
                          <td class="p-0 text-center">홍보</td>
                          <td class="p-0 text-center">이 사람 홍보해요</td>
                          <td class="p-0 text-center">2024-04-30</td>
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
                          <td class="p-0 text-center">홍보합니다</td>
                          <td class="p-0 text-center">아무개</td>
                          <td class="p-0 text-center">홍보</td>
                          <td class="p-0 text-center">이 사람 홍보해요</td>
                          <td class="p-0 text-center">2024-04-30</td>
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
                          <td class="p-0 text-center">홍보합니다</td>
                          <td class="p-0 text-center">아무개</td>
                          <td class="p-0 text-center">홍보</td>
                          <td class="p-0 text-center">이 사람 홍보해요</td>
                          <td class="p-0 text-center">2024-04-30</td>
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
                          <td class="p-0 text-center">홍보합니다</td>
                          <td class="p-0 text-center">아무개</td>
                          <td class="p-0 text-center">홍보</td>
                          <td class="p-0 text-center">이 사람 홍보해요</td>
                          <td class="p-0 text-center">2024-04-30</td>
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
                          <td class="p-0 text-center">홍보합니다</td>
                          <td class="p-0 text-center">아무개</td>
                          <td class="p-0 text-center">홍보</td>
                          <td class="p-0 text-center">이 사람 홍보해요</td>
                          <td class="p-0 text-center">2024-04-30</td>
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