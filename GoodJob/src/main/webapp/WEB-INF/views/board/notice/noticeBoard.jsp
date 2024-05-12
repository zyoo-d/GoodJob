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
							<form id="searchForm" method="GET" action="/good/board/notice.do">
								<div class="InpBox">
									<select class="sorting" name="column">
										<option value="nt_title">제목</option>
										<option value="nt_content">내용</option>
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
									<c:if test="${lv == 2 }">
										<div class="moving-btn studyAdd mr-2">
											<a href="/good/board/noticewrite.do" class="btn btnList">글쓰기</a>
										</div>
									</c:if>
									<div class="InpBox">
										<select class="sorting" name="sort" id="sort">
											<option value="latest"
												<c:if test="${param.sort == 'latest' || empty param.sort}">selected</c:if>>최신순</option>
											<option value="oldest"
												<c:if test="${param.sort == 'oldest'}">selected</c:if>>과거순</option>
										</select>
									</div>
								</div>
							</div>
							<div class="list_body">
								<div id="rec-48103333" class="list_item">
									<c:forEach items="${list}" var="dto">
										<div
											class="qna-list box_item row mx-0 rounded-[20px] bg-white px-10 shadow-lg">
											<div class="qna-list-index">${dto.nt_seq}</div>
											<div class="com-title">
												<div class="recruit-present-info">
													<div class="com-name">
														<a href="#" class="str_tit_com" target="_blank"></a> <span>${dto.nickname}</span>
													</div>
												</div>
												<div class="col notification_info">
													<div class="qnalist job_tit">
														<a class="str_tit_title new" id="rec_link_48103333"
															onclick=""
															href="/good/board/notice/viewnotice.do?seq=${dto.nt_seq}"
															target="_blank" onmousedown=""><span>${dto.nt_title}</span></a>
													</div>
												</div>
											</div>

											<div class="com-info">
												<div class="col recruit_info">
													<ul>
														<li>
															<p class="writer">${dto.nickname}</p>
														</li>
														<li>
															<p class="qna-add-date">
																<fmt:formatDate value="${dto.nt_regdate}"
																	pattern="yyyy-MM-dd" />
															</p>
														</li>
														
														<li>
															<p class="views-comments">
																<i class="fa-regular fa-eye"></i>${dto.nt_views}
															</p>
														</li>
													</ul>
												</div>
											</div>
										</div>
									</c:forEach>
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
	</script>
	<script>
	$(document).ready(function() {
			  $("#sort").change(function() {
			    var selectedSort = $(this).val();
			    $.ajax({
			      url: "/good/board/notice.do",
			      method: "GET",
			      data: { 
			        sort: selectedSort,
			        page: ${nowPage},
			        column: '${map.column}',
			        word: '${map.word}'
			      },
			      success: function(response) {
			    	  
			        $(".list_body").html($(response).find(".list_body").html());
			        $(".pagination").html($(response).find(".pagination").html());
			      },
			      error: function(a, b, c) {
			        console.log(a,b,c);
			      }
			    });
			  });
			});
	</script>

</body>
</html>