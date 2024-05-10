<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<style>
</style>
<%@include file="/WEB-INF/views/inc/adminasset.jsp"%>
<head>
<%@include file="/WEB-INF/views/inc/mypageheader.jsp"%>
</head>
<body>
	<!-- Main Content -->
	<div class="main-content">
		<section class="section">
			<div class="section-header">
				<h1>활동내역</h1>
				<div class="section-header-breadcrumb">
					<div class="breadcrumb-item active">
						<a href="#">활동내역</a>
					</div>
					<div class="breadcrumb-item">기업리뷰</div>
				</div>
			</div>
			<div class="section-body">
				<h2 class="section-title">기업리뷰</h2>
				<div class="row mt-4">
					<div class="col-12">
						<div class="card" id="reviewTable">
							<div class="card-header" id="reviewHeader">
								<h4>${totalCount}개의 리뷰를 작성하셨습니다.</h4>
								<div class="float-right">
									<div class="dropdown d-inline mr-2">
										<button class="btn btn-outline-primary dropdown-toggle"
											type="button" id="dropdownMenuButton" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="true">모든 날짜</button>
										<div class="dropdown-menu dropsmall"
											x-placement="bottom-start"
											style="position: absolute; transform: translate3d(0px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
											<a class="dropdown-item dropsmall" href="#">지난 1주</a> <a
												class="dropdown-item dropsmall" href="#">지난 1개월</a> <a
												class="dropdown-item dropsmall" href="#">지난 1년</a>
										</div>
									</div>
									<div class="dropdown d-inline mr-2">
										<button class="btn btn-primary dropdown-toggle" type="button"
											id="dropdownMenuButton" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="true">등록여부</button>
										<div class="dropdown-menu dropsmall"
											x-placement="bottom-start"
											style="position: absolute; transform: translate3d(0px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
											<a class="dropdown-item dropsmall" href="#">등록 완료</a> <a
												class="dropdown-item dropsmall" href="#">심사중</a> <a
												class="dropdown-item dropsmall" href="#">반려</a>
										</div>
									</div>
								</div>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-striped text-center">
										<tr>
											<th>번호</th>
											<th>기업명</th>
											<th>리뷰 내용</th>
											<th>작성일</th>
											<th>등록여부</th>
											<th>수정/삭제</th>
										</tr>
										<c:forEach items="${list}" var="dto">
											<tr>
												<td>${dto.rnum}</td>
												<td>${dto.cp_name}</td>
												<td>${dto.linereview}</td>
												<td>${dto.cp_rv_regdate}</td>
												<td><c:if test="${dto.cp_rv_confirm=='1'}">
														<div class="badge badge-primary">등록 완료</div>
													</c:if> <c:if test="${dto.cp_rv_confirm=='0'}">
														<div class="badge badge-warning">심사중</div>
													</c:if> <c:if test="${dto.cp_rv_confirm=='2'}">
														<div class="badge badge-danger">반려</div>
													</c:if></td>
												<td><div class="">
														<c:if test="${dto.cp_rv_confirm=='0'}">
															<a
																href="/good/user/company/review/editreview.do?cp_rv_seq=${dto.cp_rv_seq}">수정</a> / <a
																href="#" class="text-danger">삭제</a>
														</c:if>
														<c:if test="${dto.cp_rv_confirm=='1'}">
															-
														</c:if>
														<c:if test="${dto.cp_rv_confirm=='2'}">
															<a href="#" class="text-danger">삭제</a>
														</c:if>
														<input type="hidden" name="cp_rv_seq" value="${dto.cp_rv_seq}">
													</div></td>
											</tr>
										</c:forEach>
									</table>
								</div>
								<div id="reviewPaging">
									<nav>
										<ul class="pagination">
											${pagebar}
										</ul>
									</nav>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>
	<script>
	$('.text-danger')
	.click(
			function() {
				if (confirm('리뷰를 삭제하시겠습니까?')) {
					var cpRvSeqValue = $(this).closest('div').find(
							'input[type="hidden"]').val();
					location.href = "/good/user/company/review/delreview.do?cp_rv_seq="
							+ cpRvSeqValue;
					$(this).parents().eq(2).remove();
				}
			});
	</script>
</body>
</html>