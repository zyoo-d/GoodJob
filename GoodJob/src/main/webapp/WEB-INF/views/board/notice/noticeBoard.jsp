<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
	<section class="page-hero pt-16 pb-6">
		<div class="container">
			<div class="card" id="itvWriteQnA">
				<div class="card-content-wrapper">
					<div class="card-content">
						<div class="px-4 text-center">
							<h1 class="mt-3 gradiTitle">
								<span>공지사항</span>
							</h1>
							<p class="mt-6">관리자 공지사항을 확인해주세요..</p>
						</div>

						<div>
							<form id="searchForm" method="GET" action="/toy/board/list.do">
								<div class="InpBox">
									<select class="sorting">
										<option value="subject">기업</option>
										<option value="subject">제목</option>
										<option value="content">내용</option>
									</select>
								</div>
								<input type="text" name="input" class="input" id="search-input"
									placeholder="검색어를 입력하세요">
								<button type="button" class="search" id="search-btn">
									<span class="material-symbols-outlined"> <span
										class="material-symbols-outlined">search</span>
									</span>
								</button>
							</form>
						</div>

						<div id="default_list_wrap" style="position: relative">
							<section class="list_recruiting">
								<div class="list-header">
									<div class="list-title">
										<p>
											<span class="material-symbols-outlined">subject</span>
										</p>
										<p>
											조회된 게시글은 총 <span id="list-cnt">1234</span>건입니다.
										</p>
									</div>
									<div class="list_info">
										<div class="InpBox">
											<select class="sorting" name="page_count" id="page_count">
												<option value="20" selected>20개씩</option>
												<option value="30">30개씩</option>
												<option value="50">50개씩</option>
												<option value="100">100개씩</option>
											</select>
										</div>
										<div class="InpBox">
											<select class="sorting" name="sort" id="sort">
												<option value="RD" selected>최신순</option>
												<option value="EA">과거순</option>
												<option value="EA">댓글순</option>
											</select>
										</div>
									</div>
								</div>

								<div class="list_body">
									<div id="rec-48103333" class="list_item">
										<div
											class="box_item row mx-0 rounded-[20px] bg-white px-10 shadow-lg">
											<div class="list-index">1</div>
											<div class="com-title">
												<div class="recruit-present-info"></div>
												<div class="col notification_info">
													<div class="job_tit">
														<a class="str_tit_title new" id="rec_link_48103333"
															onclick="" href="#" target="_blank" onmousedown=""><span>관리자 공지사항 입니다.</span></a>
													</div>
												</div>
											</div>
											<c:forEach items="${list}" var="dto">
											<div class="com-info">
												<div class="col recruit_info">
													<ul>
														<li>
															<p class="writer">${dto.nt_title}</p>
														</li>
														<li>
															<p class="date">${dto.nt_regdate}</p>
														</li>
													</ul>
												</div>
											</div>
											</c:forEach>
										</div>
									</div>
								</div>
							</section>
						</div>
					</div>
				</div>
				<div class="moving-btn studyAdd">
				<c:if test="${lv == 2}">
					<a href="#" class="btn btnList">글쓰기</a>
				</c:if>
				</div>
				
			</div>
		</div>

		<nav class="PageBox z-custom" aria-label="Page navigation example">
			<ul class="pagination z-custom">
				<li class="page-item z-custom"><a class="page-link" href="#"><span
						class="material-symbols-outlined paging-icon z-custom">keyboard_double_arrow_left</span></a></li>
				<li class="page-item z-custom"><a class="page-link " href="#"><span
						class="material-symbols-outlined paging-icon z-custom">navigate_before</span></a></li>
				<li class="page-item z-custom"><a class="page-link" href="#">1</a></li>
				<li class="page-item z-custom"><a class="page-link" href="#">2</a></li>
				<li class="page-item z-custom"><a class="page-link" href="#">3</a></li>
				<li class="page-item z-custom"><a class="page-link" href="#">4</a></li>
				<li class="page-item z-custom"><a class="page-link" href="#">5</a></li>
				<li class="page-item z-custom"><a class="page-link" href="#"><span
						class="material-symbols-outlined paging-icon z-custom">navigate_next</span></a></li>
				<li class="page-item  z-custom"><a class="page-link" href="#"><span
						class="material-symbols-outlined paging-icon z-custom">keyboard_double_arrow_right</span></a></li>
			</ul>
		</nav>
	</section>
	<%@include file="/WEB-INF/views/inc/footer.jsp"%>

</body>

</html>