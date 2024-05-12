<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style type="text/css">
.survey-buttons {
	min-width: 170px;
	text-align: right;
}
</style>
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
										<ul class="nav nav-tabs justify-content-center mb-4"
											id="myTab" role="tablist">
											<li class="nav-item"><a class="nav-link active"
												id="salary-tab" data-toggle="tab" href="#salary" role="tab"
												aria-controls="salary" aria-selected="true">연봉</a></li>
											<li class="nav-item"><a class="nav-link"
												id="stability-tab" data-toggle="tab" href="#stability"
												role="tab" aria-controls="stability" aria-selected="false">조직안정성</a>
											</li>
											<li class="nav-item"><a class="nav-link"
												id="culture-tab" data-toggle="tab" href="#culture"
												role="tab" aria-controls="culture" aria-selected="false">조직문화</a>
											</li>
											<li class="nav-item"><a class="nav-link"
												id="potential-tab" data-toggle="tab" href="#potential"
												role="tab" aria-controls="potential" aria-selected="false">성장가능성</a>
											</li>
											<li class="nav-item"><a class="nav-link"
												id="welfare-tab" data-toggle="tab" href="#welfare"
												role="tab" aria-controls="welfare" aria-selected="false">복지</a>
											</li>
										</ul>
										<div class="tab-content" id="myTabContent">
											<div class="tab-pane fade show active" id="salary"
												role="tabpanel" aria-labelledby="salary-tab">
												<div class="survey-container">

													<c:forEach items="${salList}" var="dto">
														<div class="survey-group">
															<div class="survey-header">
																<h4>${dto.sv_content}</h4>
																<div class="survey-buttons">
																	[ ${dto.compare} ] <a
																		class="btn btn-icon icon-left btn-primary editSurvey"
																		style="margin-left: 10px; color: #fff;"><i
																		class="far fa-edit"></i> 수정</a>
																</div>
															</div>
															<div class="survey-edit">
																<input type="text" class="form-control"
																	name="sv_content">
															</div>
															<input type="hidden" name="sv_seq" value="${dto.sv_seq}">
														</div>
													</c:forEach>
												</div>
											</div>
											<div class="tab-pane fade" id="stability" role="tabpanel"
												aria-labelledby="stability-tab">
												<div class="survey-container">
													<c:forEach items="${stabList}" var="dto">
														<div class="survey-group">
															<div class="survey-header">
																<h4>${dto.sv_content}</h4>
																<div class="survey-buttons">
																	[ ${dto.compare} ] <a
																		class="btn btn-icon icon-left btn-primary editSurvey"
																		style="margin-left: 10px; color: #fff;"><i
																		class="far fa-edit"></i> 수정</a>
																</div>
															</div>
															<div class="survey-edit">
																<input type="text" class="form-control"
																	name="sv_content">
															</div>
															<input type="hidden" name="sv_seq" value="${dto.sv_seq}">
														</div>
													</c:forEach>
												</div>
											</div>
											<div class="tab-pane fade" id="culture" role="tabpanel"
												aria-labelledby="culture-tab">
												<div class="survey-container">
													<c:forEach items="${culList}" var="dto">
														<div class="survey-group">
															<div class="survey-header">
																<h4>${dto.sv_content}</h4>
																<div class="survey-buttons">
																	[ ${dto.compare} ] <a
																		class="btn btn-icon icon-left btn-primary editSurvey"
																		style="margin-left: 10px; color: #fff;"><i
																		class="far fa-edit"></i> 수정</a>
																</div>
															</div>
															<div class="survey-edit">
																<input type="text" class="form-control"
																	name="sv_content">
															</div>
															<input type="hidden" name="sv_seq" value="${dto.sv_seq}">
														</div>
													</c:forEach>
												</div>
											</div>
											<div class="tab-pane fade" id="potential" role="tabpanel"
												aria-labelledby="potential-tab">
												<div class="survey-container">
													<c:forEach items="${potList}" var="dto">
														<div class="survey-group">
															<div class="survey-header">
																<h4>${dto.sv_content}</h4>
																<div class="survey-buttons">
																	[ ${dto.compare} ] <a
																		class="btn btn-icon icon-left btn-primary editSurvey"
																		style="margin-left: 10px; color: #fff;"><i
																		class="far fa-edit"></i> 수정</a>
																</div>
															</div>
															<div class="survey-edit">
																<input type="text" class="form-control"
																	name="sv_content">
															</div>
															<input type="hidden" name="sv_seq" value="${dto.sv_seq}">
														</div>
													</c:forEach>
												</div>
											</div>
											<div class="tab-pane fade" id="welfare" role="tabpanel"
												aria-labelledby="welfare-tab">
												<div class="survey-container">
													<c:forEach items="${welList}" var="dto">
														<div class="survey-group">
															<div class="survey-header">
																<h4>${dto.sv_content}</h4>
																<div class="survey-buttons">
																	[ ${dto.compare} ] <a
																		class="btn btn-icon icon-left btn-primary editSurvey"
																		style="margin-left: 10px; color: #fff;"><i
																		class="far fa-edit"></i> 수정</a>
																</div>
															</div>
															<div class="survey-edit">
																<input type="text" class="form-control"
																	name="sv_content">
															</div>
															<input type="hidden" name="sv_seq" value="${dto.sv_seq}">
														</div>
													</c:forEach>
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
		</div>
	</div>

	<%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>
	<script>
		$('.editSurvey').click(
				function() {
					var sv_seq = $(this).parent().parent().parent().find(
							'input[name="sv_seq"]').val();
					var sv_content = $(this).parent().parent().parent().find(
							'input[name="sv_content"]').val();
					location.href = '/good/admin/survey/editsurvey.do?sv_seq='
							+ sv_seq + '&sv_content=' + sv_content;
				});
	</script>
</body>
</html>