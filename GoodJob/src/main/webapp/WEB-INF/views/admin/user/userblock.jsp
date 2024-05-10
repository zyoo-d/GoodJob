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

            <div class="card" id="userblock-card">
              <div class="card-header">
                <h4>회원 차단 목록</h4>
                <div class="card-header-form">
                  <form>
                    <div class="input-group">
                      <input type="text" class="form-control" placeholder="검색어를 입력하세요.">
                      <div class="input-group-btn">
                        <button class="btn btn-primary"><i class="fas fa-search"></i></button>
                      </div>
                    </div>
                  </form>
                </div>
              </div>

              <div class="card-body">


                <div class="row">
                  <div class="col-12 col-md-6 col-lg-6">
                    <div class="card">
                      <div class="card-header">
                        <h4>임시 차단</h4>
                      </div>
                      <div class="card-body">
                        <div class="table-responsive">
                          <table class="table table-bordered table-md table-striped">
                            <tr>
                              <th class="text-center">번호</th>
                              <th class="text-center">아이디</th>
                              <th class="text-center">차단 일자</th>
                              <th class="text-center">해제 일자</th>
                              <th class="text-center">사유</th>
                            </tr>
                            <tr>
                              <td class="text-center">1</td>
                              <td class="text-center">qwer1234</td>
                              <td class="text-center">2017-01-09</td>
                              <td class="text-center">2017-01-09</td>
                              <td class="text-center"><a href="#" class="btn btn-secondary">Detail</a></td>
                            </tr>
                            <tr>
                              <td class="text-center">2</td>
                              <td class="text-center">asdf1234</td>
                              <td class="text-center">2017-01-09</td>
                              <td class="text-center">2017-01-09</td>
                              <td class="text-center"><a href="#" class="btn btn-secondary">Detail</a></td>
                            </tr>
                            <tr>
                              <td class="text-center">3</td>
                              <td class="text-center">Kusnadi</td>
                              <td class="text-center">2017-01-11</td>
                              <td class="text-center">2017-01-13</td>
                              <td class="text-center"><a href="#" class="btn btn-secondary">Detail</a></td>
                            <tr>
                              <td class="text-center">4</td>
                              <td class="text-center">Rizal Fakhri</td>
                              <td class="text-center">2017-01-11</td>
                              <td class="text-center">2017-01-13</td>
                              <td class="text-center"><a href="#" class="btn btn-secondary">Detail</a></td>
                            </tr>
                          </table>
                        </div>
                      </div>
                      <div class="card-footer text-right">
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
                  <div class="col-12 col-md-6 col-lg-6">
    
                    <div class="card">
                      <div class="card-header">
                        <h4>영구 차단</h4>
                      </div>
                      <div class="card-body">
                        <div class="table-responsive">
                          <table class="table table-bordered table-md table-striped">
                            <tr>
                              <th class="text-center">번호</th>
                              <th class="text-center">아이디</th>
                              <th class="text-center">가입 일자</th>
                              <th class="text-center">차단 일자</th>
                              <th class="text-center">사유</th>
                            </tr>
                            <tr>
                              <td class="text-center">1</td>
                              <td class="text-center">qwer1234</td>
                              <td class="text-center">2017-01-09</td>
                              <td class="text-center">2017-01-09</td>
                              <td class="text-center"><a href="#" class="btn btn-secondary">Detail</a></td>
                            </tr>
                            <tr>
                              <td class="text-center">2</td>
                              <td class="text-center">asdf1234</td>
                              <td class="text-center">2017-01-09</td>
                              <td class="text-center">2017-01-09</td>
                              <td class="text-center"><a href="#" class="btn btn-secondary">Detail</a></td>
                            </tr>
                            <tr>
                              <td class="text-center">3</td>
                              <td class="text-center">Kusnadi</td>
                              <td class="text-center">2017-01-11</td>
                              <td class="text-center">2017-01-09</td>
                              <td class="text-center"><a href="#" class="btn btn-secondary">Detail</a></td>
                            <tr>
                              <td class="text-center">4</td>
                              <td class="text-center">Rizal Fakhri</td>
                              <td class="text-center">2017-01-11</td>
                              <td class="text-center">2017-01-09</td>
                              <td class="text-center"><a href="#" class="btn btn-secondary">Detail</a></td>
                            </tr>
                          </table>
                        </div>
                      </div>
                      <div class="card-footer text-right">
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


            </div>
           
            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-header">
                    <h4>상세 정보</h4>
                    <div class="card-header-form">
                      <form>
                        <div class="input-group">
                          <button type="button" class="btn btn-primary" id="btn-block-user">차단 관리</button>
                          <button type="button" class="btn btn-primary" id="btn-block-alluser">일괄 처리</button>
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
                          <th>아이디</th>
                          <th>차단 일자</th>
                          <th>해제 일자</th>
                          <th>사유</th>
                          <th>상태</th>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-1">
                              <label for="checkbox-1" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td>kmkm111</td>
                          <td>2024-04-12</td>
                          <td>9999-04-30</td>
                          <td>광고 도배</td>
                          <td>차단</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-2">
                              <label for="checkbox-2" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td>kmkm111</td>
                          <td>2024-04-12</td>
                          <td>2024-04-30</td>
                          <td>욕설</td>
                          <td>차단</td>
                        </tr>
                        <tr>
                          <td class="p-0 text-center">
                            <div class="custom-checkbox custom-control">
                              <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-3">
                              <label for="checkbox-3" class="custom-control-label">&nbsp;</label>
                            </div>
                          </td>
                          <td>kmkm111</td>
                          <td>2024-04-12</td>
                          <td>2024-04-30</td>
                          <td>도배</td>
                          <td>차단</td>
                        </tr>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>



          </div>
        </section>

        <div id="modal-part" class="modal-part">
          <form>
            <div class="form-group">
              <label for="user-id">아이디</label>
              <input type="text" class="form-control" id="user-id" readonly>
            </div>
            <div class="form-group">
              <label for="block-date">차단 일자</label>
              <input type="text" class="form-control" id="block-date" readonly>
            </div>
            <div class="form-group">
              <label for="release-date">해제 일자</label>
              <input type="text" class="form-control" id="release-date" readonly>
            </div>
            <div class="form-group">
              <label for="block-reason">사유</label>
              <input type="text" class="form-control" id="block-reason" readonly>
            </div>
            <div class="form-group">
              <label for="status">상태</label>
              <select class="form-control" id="status">
                <option value="3일정지">3일 정지</option>
                <option value="5일정지">5일 정지</option>
                <option value="7일정지">7일 정지</option>
                <option value="영구정지">영구 정지</option>
                <option value="정상">정상</option>
              </select>
            </div>
            <div class="form-group">
              <label for="change-reason">변경 사유</label>
              <input type="text" class="form-control" id="change-reason">
            </div>
          </form>
        </div>

        <div id="modal-bulk-part" class="modal-part">
          <form>
            <div class="form-group">
              <label for="bulk-user-count">선택된 유저 수</label>
              <input type="text" class="form-control" id="bulk-user-count" readonly>
            </div>
            <div class="form-group">
              <label for="bulk-status">상태</label>
              <select class="form-control" id="bulk-status">
                <option value="3일정지">3일 정지</option>
                <option value="5일정지">5일 정지</option>
                <option value="7일정지">7일 정지</option>
                <option value="영구정지">영구 정지</option>
                <option value="정상">정상</option>
              </select>
            </div>
            <div class="form-group">
              <label for="bulk-change-reason">변경 사유</label>
              <input type="text" class="form-control" id="bulk-change-reason">
            </div>
          </form>
        </div>




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
  <script src="assets/modules/prism/prism.js"></script>

  <!-- Page Specific JS File -->
  <script src="assets/js/page/components-table.js"></script>
  <script src="assets/js/page/bootstrap-modal.js"></script>
  
  <!-- Template JS File -->
  <script src="assets/js/scripts.js"></script>
  <script src="assets/js/custom.js"></script>
</body>
</html>