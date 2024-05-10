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
            <h1>설문조사 관리</h1>
          </div>

          <div class="section-body">
            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-body">
                    <ul class="nav nav-tabs justify-content-center mb-4" id="myTab" role="tablist">
                      <li class="nav-item">
                        <a class="nav-link active" id="salary-tab" data-toggle="tab" href="#salary" role="tab" aria-controls="salary" aria-selected="true">연봉</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" id="lngvt-tab" data-toggle="tab" href="#lngvt" role="tab" aria-controls="lngvt" aria-selected="false">근속률</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" id="culture-tab" data-toggle="tab" href="#culture" role="tab" aria-controls="culture" aria-selected="false">조직문화</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" id="potential-tab" data-toggle="tab" href="#potential" role="tab" aria-controls="potential" aria-selected="false">성장가능성</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" id="welfare-tab" data-toggle="tab" href="#welfare" role="tab" aria-controls="welfare" aria-selected="false">복지</a>
                      </li>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                      <div class="tab-pane fade show active" id="salary" role="tabpanel" aria-labelledby="salary-tab">
                        <div class="survey-container">
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 1</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                      
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="tab-pane fade" id="lngvt" role="tabpanel" aria-labelledby="lngvt-tab">
                        <div class="survey-container">
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 1</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                      
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="tab-pane fade" id="culture" role="tabpanel" aria-labelledby="culture-tab">
                        <div class="survey-container">
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 1</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                      
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="tab-pane fade" id="potential" role="tabpanel" aria-labelledby="potential-tab">
                        <div class="survey-container">
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 1</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                      
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="tab-pane fade" id="welfare" role="tabpanel" aria-labelledby="welfare-tab">
                        <div class="survey-container">
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 1</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                      
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
                            </div>
                          </div>
                          <div class="survey-group">
                            <div class="survey-header">
                              <h4>설문조사 질문 2</h4>
                              <div class="survey-buttons">
                                <a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;"><i class="far fa-edit"></i> 수정</a>
                                <a href="#" class="btn btn-icon icon-left btn-danger" style="margin-left: 10px;"><i class="fas fa-times"></i> 삭제</a>
                              </div>
                            </div>
                            <div class="survey-answers">
                              <div class="survey-answer">1. 답변 1</div>
                              <div class="survey-answer">2. 답변 2</div>
                              <div class="survey-answer">3. 답변 3</div>
                              <div class="survey-answer">4. 답변 4</div>
                              <div class="survey-answer">5. 답변 5</div>
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
      <footer class="main-footer">
        <div class="footer-left">
          Copyright &copy; 2018 <div class="bullet"></div> Design By <a href="https://nauval.in/">Muhamad Nauval Azhar</a>
        </div>
        <div class="footer-right">
          
        </div>
      </footer>
    </div>
  </div>

  
</body>
</html>