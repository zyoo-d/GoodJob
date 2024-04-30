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
				<h1>활동내역</h1>
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
									<div class="dropdown d-inline mr-2">
										<button class="btn btn-outline-primary dropdown-toggle" type="button"
											id="dropdownMenuButton" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="true">모든 날짜</button>
										<div class="dropdown-menu dropsmall" x-placement="bottom-start"
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
										<div class="dropdown-menu dropsmall" x-placement="bottom-start"
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
											<th>기업명/직무</th>
											<th>면접시기</th>
											<th>합격여부</th>
											<th>작성일</th>
											<th>등록여부</th>
											<th>수정/삭제</th>
										</tr>
										<tr>
											<td>1</td>
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
											<td>2</td>
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
											<td>3</td>
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
											<td>4</td>
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
											<td>5</td>
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