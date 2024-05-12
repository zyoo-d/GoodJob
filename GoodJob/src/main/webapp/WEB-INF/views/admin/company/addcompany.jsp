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
            <h1>기업 관리</h1>
          </div>

          <div class="section-body">
            
            <div class="card" id="addcompany-card">
              <div class="card-header">
                <h4>기업 정보 등록</h4>
                <div class="card-header-form">
                  <form>
                    <div class="input-group">
                      <a href="#" class="btn btn-icon icon-left btn-primary" id="addcompany-btn"><i class="far fa-edit"></i> 등록 하기</a>
                    </div>
                  </form>
                </div>
              </div>

              
              <div class="card-body">

                <div class="row">
                  <div class="col-12 col-md-6 col-lg-6">
                    <div class="card">
                      <div class="card-header">
                        <h4>기초 정보</h4>
                      </div>
                      <div class="card-body" id="addcompany-info">
                        
                        <div class="form-group">
                          <label>기업명</label>
                          <div class="input-group">
                            <div class="input-group-prepend">
                              <div class="input-group-text">
                                <i class="fas fa-building"></i>
                              </div>
                            </div>
                            <input type="text" class="form-control">
                          </div>
                        </div>
    
                        <div class="form-group">
                          <label>주소</label>
                          <div class="input-group">
                            <div class="input-group-prepend">
                              <div class="input-group-text">
                                <i class="fas fa-map"></i>
                              </div>
                            </div>
                            <input type="text" class="form-control">
                          </div>
                        </div>
    
    
                        <div class="form-group">
                          <label>전화 번호</label>
                          <div class="input-group">
                            <div class="input-group-prepend">
                              <div class="input-group-text">
                                <i class="fas fa-phone"></i>
                              </div>
                            </div>
                            <input type="text" class="form-control phone-number">
                          </div>
                        </div>
    
                        <div class="form-group">
                          <label>대표자명</label>
                          <div class="input-group">
                            <div class="input-group-prepend">
                              <div class="input-group-text">
                                <i class="fas fa-user"></i>
                              </div>
                            </div>
                            <input type="text" class="form-control">
                          </div>
                        </div>
    
                        <div class="form-group">
                          <label>설립일</label>
                          <div class="input-group">
                            <div class="input-group-prepend">
                              <div class="input-group-text">
                                <i class="fas fa-calendar"></i>
                              </div>
                            </div>
                            <input type="text" class="form-control datepicker">
                          </div>
                          
                        </div>
    
                        <div class="form-group">
                          <label>업종</label>
                          <select class="form-control selectric">
                            <option>응용 소프트웨어 개발 및 공급업
                            </option>
                            <option>데이터베이스 및 온라인 정보 제공업
                            </option>
                            <option>자료 처리업
                            </option>
                            <option>컴퓨터 및 주변 기기 수리업
                            </option>
                            <option>기타 정보 기술 및 컴퓨터 운영 관련 서비스업
                            </option>
                            <option>컴퓨터 시스템 통합 자문 및 구축 서비스업
                            </option>
                          </select>
                        </div>
    
                      </div>
                    </div>
    
                  </div>
    
    
                  <div class="col-12 col-md-6 col-lg-6">
                    <div class="card">
                      <div class="card-header">
                        <h4>추가 정보</h4>
                      </div>
                      <div class="card-body">
    
                        <div class="form-group">
                          <label>재직자수</label>
                          <div class="input-group">
                            <div class="input-group-prepend">
                              <div class="input-group-text">
                                <i class="fas fa-users"></i>
                              </div>
                            </div>
                            <input type="text" class="form-control">
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label>매출액</label>
                          <div class="input-group">
                            <div class="input-group-prepend">
                              <div class="input-group-text">
                                <i class="fas fa-won-sign"></i>
                              </div>
                            </div>
                            <input type="text" class="form-control currency">
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label>영업이익</label>
                          <div class="input-group">
                            <div class="input-group-prepend">
                              <div class="input-group-text">
                                <i class="fas fa-won-sign"></i>
                              </div>
                            </div>
                            <input type="text" class="form-control currency">
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label>당기순이익</label>
                          <div class="input-group">
                            <div class="input-group-prepend">
                              <div class="input-group-text">
                                <i class="fas fa-won-sign"></i>
                              </div>
                            </div>
                            <input type="text" class="form-control currency">
                          </div>
                        </div>
    
                        <div class="form-group">
                          <label>기업 로고</label>
                          <div class="input-group mb-3">
                            <div class="custom-file">
                              <input type="file" class="custom-file-input" id="logo-upload" accept=".jpg, .png">
                              <label class="custom-file-label" for="logo-upload">파일 선택</label>
                            </div>
                          </div>
                          <div id="addcompany-logo" class="d-flex justify-content-center align-items-center">
                            <img id="addcompany-logo-preview" src="" alt="">
                            <i id="addcompany-logo-icon" class="fas fa-image"></i>
                          </div>
                        </div>

              </div>


              
            </div>


            </div>
            


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
  <script src="/good/assets/js/page/forms-advanced-forms.js"></script>
  
  

  <script>
    
  </script>
</body>
</html>

