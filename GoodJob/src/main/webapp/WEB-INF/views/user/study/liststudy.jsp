<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
	.studycard {
		cursor: pointer;
	}
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
								<span>스터디 게시판</span>
							</h1>
							<p class="mt-6">함께 할 준비 되셨나요? 고난과 도전을 함께 이겨내며 모두가 목표를 달성할 수 있는 그 날까지! IT's 굿잡 스터디에서 함께 실력을 쌓아가요.</p>
						</div>

						<div>
							<form id="searchForm" method="GET"
								action="/good/user/liststudy.do">
								<div class="InpBox">
									<select class="sorting" name="column">
										<option value="cp_name">기업</option>
										<option value="std_title">제목</option>
										<option value="std_content">내용</option>
									</select>
								</div>
								<input type="text" name="word" class="input" id="search-input"
									placeholder="검색어를 입력하세요">
								<button type="submit" class="search" id="search-btn">
									<span class="material-symbols-outlined"> <span
										class="material-symbols-outlined">search</span>
									</span>
								</button>
							</form>
						</div>
					</div>

					<div id="default_list_wrap" style="position: relative">
						<section class="list_recruiting">
							<div class="list-header">
								<div class="list-title">
									<p>
										<span class="material-symbols-outlined">subject</span>
									</p>
									<p>
										조회된 게시글은 총 <span id="list-cnt">${totalCount}</span>건입니다.
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
									<div class="row mt-14">

										<c:forEach items="${list}" var="dto">
											<div class="mb-8 sm:col-6 lg:col-4">
												<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8 studycard">
													<div class="relative inline-block comment-edit">
														<p class="education">
															<i class="fa-solid fa-calendar-days"></i> ~
															${dto.std_duedate}
														</p>
														<c:if test="${dto.std_ing=='N'}">
															<a class="tag" href="#">모집중</a>
														</c:if>
													</div>
														<h4 class="my-6 line_limit">${dto.cp_name}</h4>
														<p class="">${dto.std_title}</p>
														<p class="views-comments views mt-2">
															<i class="fa-regular fa-eye"></i>${dto.std_views}<i
																class="fa-regular fa-comment"></i>20
														</p>
													<input type="hidden" name="std_seq" value="${dto.std_seq}">
												</div>
											</div>
										</c:forEach>
										<div class="moving-btn studyAdd">
											<a href="/good/user/addstudy.do" class="btn btnList">글쓰기</a>
										</div>
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
		</div>

		<nav class="PageBox z-custom" aria-label="Page navigation example">
			<ul class="pagination z-custom">${pagebar}
			</ul>
		</nav>
	</section>
	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script type="text/javascript">
		<c:if test="${map.search == 'y'}">
		//검색중 상태 유지
		$('input[name=word]').val('${map.word}');
		$('select[name=column]').val('${map.column}');
		</c:if>

		$('.studycard').click(function() {
			var std_seq = $(this).find('input[name=std_seq]').val();
			location.href = '/good/user/viewstudy.do?std_seq=' + std_seq+'&column=${map.column}&word=${map.word}&search=${map.search}&page=${nowPage}';
		});
	</script>
</body>

</html>