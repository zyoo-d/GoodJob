<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style type="text/css">
.com-image {
	width: 70px;
	height: 70px;
	background-size: contain;
	background-position: center;
	background-repeat: no-repeat;
}
#logo-img {
	display: flex;
	align-items: center;
	justify-content: center;
	height: 80px;
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
						<h1>기업 정보</h1>
					</div>

					<div class="section-body">
						<!-- <h2 class="section-title">기업 목록</h2>
            <p class="section-lead">2024-04-26 기준</p> -->

						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h4>기업 목록</h4>
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
													<a href="#" class="btn btn-icon icon-left btn-primary"><i
														class="far fa-edit"></i> 수정</a> <a href="#"
														class="btn btn-icon icon-left btn-danger"><i
														class="fas fa-times"></i> 삭제</a>
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
													<th class="p-0 text-center">기업 로고</th>
													<th class="p-0 text-center">기업명</th>
													<th class="p-0 text-center">위치</th>
													<th class="p-0 text-center">매출액</th>
													<th class="p-0 text-center">평균연봉</th>
													<th class="p-0 text-center">리뷰 개수</th>
												</tr>
												<c:forEach items="${comListInfo}" var="dto">
													<tr>
														<td class="p-0 text-center">
															<div class="custom-checkbox custom-control">
																<input type="checkbox" data-checkboxes="mygroup"
																	class="custom-control-input" id="checkbox-1"> <label
																	for="checkbox-1" class="custom-control-label">&nbsp;</label>
															</div>
														</td>
														<td class="p-0 text-center"  id="logo-img">
															<div class="com-list com-image"
																style="background-image: url('${dto.image}');"
																alt="Company Logo" name="image">
														</td>
														<td class="p-0 text-center">${dto.cp_name}</td>
														<td class="p-0 text-center">${dto.cp_address}</td>
														<td class="p-0 text-center"><c:if
																test="${dto.fnc_sales == 0}">수집 정보 없음</c:if> <c:if
																test="${dto.fnc_sales != 0}">
																${dto.fnc_sales}</c:if></td>
														<td class="p-0 text-center"><c:if
																test="${dto.hire_avr_salary == 0}">수집 정보 없음</c:if> <c:if
																test="${dto.hire_avr_salary !=0 || not empty dto.hire_avr_salary}">
																<fmt:formatNumber type="number" maxFractionDigits="0"
																	value="${dto.hire_avr_salary/10000}" />
																				만원
																			</c:if></td>
														<td class="p-0 text-center">${dto.com_rv_cnt !=0 || not empty dto.com_rv_cnt?dto.com_rv_cnt:'-'}
														</td>
													</tr>
												</c:forEach>
											</table>
										</div>
									</div>
									<div class="card-footer text-center">
										<nav class="d-inline-block">
											<ul class="pagination mb-0">${pagebar}
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

</body>
</html>