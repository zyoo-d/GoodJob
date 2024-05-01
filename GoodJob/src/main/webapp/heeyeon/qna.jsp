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
					<div class="breadcrumb-item">여기어때?</div>
				</div>
			</div>
			<div class="section-body">
				<h2 class="section-title">여기어때?</h2>
				<div class="row mt-4">
					<div class="col-12">
						<div class="card" id="studyTable">
							<div class="card-header" id="studyHeader">
								<h4>1개의 글을 작성하셨습니다.</h4>
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
								</div>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-striped text-center">
										<tr>
											<th>번호</th>
											<th>제목</th>
											<th>내용</th>
											<th>작성일</th>
											<th>수정/삭제</th>
										</tr>
										<tr>
											<td>1</td>
											<td>쌍용교육센터 어떤가요?</td>
											<td>제가 지원을 해보려고 하는데...</td>
											<td>2024-01-20</td>
											<td><div class="">
													<a href="#">수정</a> / <a href="#" class="text-danger">삭제</a>
												</div></td>
										</tr>
									</table>
								</div>
								<div id="studyPaging">
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
				<div class="row mt-4">
					<div class="col-12">
						<div class="card" id="stdCommentTable">
							<div class="card-header" id="stdCommentHeader">
								<h4>2개의 댓글을 작성하셨습니다.</h4>
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
								</div>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-striped text-center">
										<tr>
											<th>번호</th>
											<th>게시글 제목</th>
											<th>댓글 내용</th>
											<th>작성일</th>
											<th>수정/삭제</th>
										</tr>
										<tr>
											<td>1</td>
											<td>쌍용교육센터 어떤가요?</td>
											<td>좋다고 들었습니다!!</td>
											<td>2024-01-20</td>
											<td><div class="">
													<a href="#">수정</a> / <a href="#" class="text-danger">삭제</a>
												</div></td>
										</tr>
										<tr>
											<td>2</td>
											<td>코테 시험보는곳..</td>
											<td>너무 어려웠겠네요</td>
											<td>2024-01-20</td>
											<td><div class="">
													<a href="#">수정</a> / <a href="#" class="text-danger">삭제</a>
												</div></td>
										</tr>
									</table>
								</div>
								<div id="stdCommentPaging">
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