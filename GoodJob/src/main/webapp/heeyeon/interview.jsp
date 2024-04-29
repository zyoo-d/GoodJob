<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<style>
</style>
<head>
<meta charset="UTF-8">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no"
	name="viewport">
<title>마이페이지 &mdash; IT's GOODJOB</title>
<%@include file="/WEB-INF/views/inc/adminasset.jsp"%>
</head>

<body>
	<%@include file="/WEB-INF/views/inc/mypageheader.jsp"%>
	<!-- Main Content -->
	<div class="main-content">
		<section class="section">
			<div class="section-header">
				<h1>Mypage</h1>
				<div class="section-header-breadcrumb">
					<div class="breadcrumb-item active">
						<a href="#">활동내역</a>
					</div>
					<div class="breadcrumb-item">면접후기</div>
				</div>
			</div>
			<div class="section-body">
				<h2 class="section-title">면접후기</h2>
				<div class="row mt-4">
					<div class="col-12">
						<div class="card" id="interviewTable">
							<div class="card-header" id="interviewHeader">
								<h4>5개의 후기를 작성하셨습니다.</h4>
								<div class="float-right">
									<form>
										<div class="input-group">
											<input type="text" class="form-control" placeholder="Search">
											<div class="input-group-append">
												<button class="btn btn-primary">
													<i class="fas fa-search"></i>
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-striped text-center">
										<tr>
											<th class="text-center pt-2">
												<div
													class="custom-checkbox custom-checkbox-table custom-control">
													<input type="checkbox" data-checkboxes="mygroup"
														data-checkbox-role="dad" class="custom-control-input"
														id="checkbox-all"> <label for="checkbox-all"
														class="custom-control-label">&nbsp;</label>
												</div>
											</th>
											<th>기업명/직무</th>
											<th>면접시기</th>
											<th>합격여부</th>
											<th>작성일</th>
											<th>등록여부</th>
											<th>수정/삭제</th>
										</tr>
										<tr>
											<td>
												<div class="custom-checkbox custom-control">
													<input type="checkbox" data-checkboxes="mygroup"
														class="custom-control-input" id="checkbox-2"> <label
														for="checkbox-2" class="custom-control-label">&nbsp;</label>
												</div>
											</td>
											<td>삼성전자/프론트</td>
											<td>2023년도 상반기</td>
											<td><div class="badge badge-warning">대기중</div></td>
											<td>2024-01-20</td>
											<td><div class="badge badge-warning">심사중</div></td>
											<td><div class="">
													<a href="#">수정</a> / <a href="#" class="text-danger">삭제</a>
												</div></td>
										</tr>
										<tr>
											<td>
												<div class="custom-checkbox custom-control">
													<input type="checkbox" data-checkboxes="mygroup"
														class="custom-control-input" id="checkbox-3"> <label
														for="checkbox-3" class="custom-control-label">&nbsp;</label>
												</div>
											</td>
											<td>삼성전자/프론트</td>
											<td>2023년도 상반기</td>
											<td><div class="badge badge-warning">대기중</div></td>
											<td>2024-01-20</td>
											<td><div class="badge badge-danger">반려</div></td>
											<td><div class="">
													<a href="#">수정</a> / <a href="#" class="text-danger">삭제</a>
												</div></td>
										</tr>
										<tr>
											<td>
												<div class="custom-checkbox custom-control">
													<input type="checkbox" data-checkboxes="mygroup"
														class="custom-control-input" id="checkbox-4"> <label
														for="checkbox-4" class="custom-control-label">&nbsp;</label>
												</div>
											</td>
											<td>삼성전자/프론트</td>
											<td>2023년도 상반기</td>
											<td><div class="badge badge-warning">대기중</div></td>
											<td>2024-01-20</td>
											<td><div class="badge badge-primary">등록 완료</div></td>
											<td><div class="">
													<a href="#" class="text-danger">삭제</a>
												</div></td>
										</tr>
										<tr>
											<td>
												<div class="custom-checkbox custom-control">
													<input type="checkbox" data-checkboxes="mygroup"
														class="custom-control-input" id="checkbox-5"> <label
														for="checkbox-5" class="custom-control-label">&nbsp;</label>
												</div>
											</td>
											<td>삼성전자/프론트</td>
											<td>2023년도 상반기</td>
											<td><div class="badge badge-warning">대기중</div></td>
											<td>2024-01-20</td>
											<td><div class="badge badge-primary">등록 완료</div></td>
											<td><div class="">
													<a href="#" class="text-danger">삭제</a>
												</div></td>
										</tr>
										<tr>
											<td>
												<div class="custom-checkbox custom-control">
													<input type="checkbox" data-checkboxes="mygroup"
														class="custom-control-input" id="checkbox-6"> <label
														for="checkbox-6" class="custom-control-label">&nbsp;</label>
												</div>
											</td>
											<td>삼성전자/프론트</td>
											<td>2023년도 상반기</td>
											<td><div class="badge badge-warning">대기중</div></td>
											<td>2024-01-20</td>
											<td><div class="badge badge-primary">등록 완료</div></td>
											<td><div class="">
													<a href="#" class="text-danger">삭제</a>
												</div></td>
										</tr>
									</table>
								</div>
								<div id="interviewPaging">
									<nav>
										<ul class="pagination">
											<li class="page-item disabled"><a class="page-link"
												href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
													<span class="sr-only">이전페이지</span>
											</a></li>
											<li class="page-item active"><a class="page-link"
												href="#">1</a></li>
											<li class="page-item"><a class="page-link" href="#">2</a>
											</li>
											<li class="page-item"><a class="page-link" href="#">3</a>
											</li>
											<li class="page-item"><a class="page-link" href="#"
												aria-label="Next"> <span aria-hidden="true">&raquo;</span>
													<span class="sr-only">다음페이지</span>
											</a></li>
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

</body>
</html>