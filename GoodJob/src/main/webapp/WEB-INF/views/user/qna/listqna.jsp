<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<style>
</style>
</head>

<%@include file="/WEB-INF/views/inc/header.jsp"%>

<body>

	<!-- ./end floating assets -->

	<section class="page-hero pt-16 pb-6">
		<div class="container">
			<div class="card" id="itvWriteQnA">
				<div class="card-content-wrapper">
					<div class="card-content">
						<div class="px-4 text-center">

							<h1 class="mt-3 gradiTitle">
								<span onclick="location.href='/good/user/qna/listqna.do';">여기어때</span>
							</h1>
							<p class="qanlist mt-6">자유롭게 회사정보를 공유해보세요.</p>
						</div>

						<div>
							<form id="searchForm" method="GET"
								action="/good/user/qna/listqna.do">
								<div class="InpBox">
									<select class="sorting" name="column">
										<option value="title">제목</option>
										<option value="content">내용</option>
										<option value="name">기업</option>
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
							<div class="list-title">
								<p>
									<span class="material-symbols-outlined">subject</span>
								</p>
								<p>
									조회된 게시글은 총 <span id="list-cnt">${totalCount}</span>건입니다.
								</p>
							</div>
							<div class="list-header">

								<div class="moving-btn">
									<a href="/good/user/qna/addqna.do" class="btn btnList">글 등록</a>
								</div>
								<div class="InpBox">
									<select class="sorting" name="sort" id="sort">
										<option value="latest"
											<c:if test="${param.sort == 'latest' || empty param.sort}">selected</c:if>>최신순</option>
										<option value="oldest"
											<c:if test="${param.sort == 'oldest'}">selected</c:if>>과거순</option>
										<option value="comments"
											<c:if test="${param.sort == 'comments'}">selected</c:if>>댓글순</option>
									</select>
								</div>
							</div>

							<div class="list_body">
								<div id="rec-48103333" class="list_item">
									<!--  -->
									<c:forEach items="${list}" var="dto">
										<div
											class="qna-list box_item row mx-0 rounded-[20px] bg-white px-10 shadow-lg">
											<div class="qna-list-index">${dto.qna_seq}</div>
											<div class="com-title">
												<div class="recruit-present-info">
													<div class="com-name">
														<a href="#" class="str_tit_com" target="_blank"></a> <span>${dto.cp_name}</span>
													</div>
												</div>
												<div class="col notification_info">
													<div class="qnalist job_tit">
														<a class="str_tit_title new" id="rec_link_48103333"
															onclick=""
															href="/good/user/qna/viewqna.do?qna_seq=${dto.qna_seq}"
															target="_blank" onmousedown=""><span>${dto.qna_title}</span></a>
													</div>
												</div>
											</div>
											<div class="com-info">
												<div class="col recruit_info">
													<ul>
														<li>
															<p class="writer">${dto.id}</p>
														</li>
														<li>
															<p class="qna-add-date">
																<fmt:formatDate value="${dto.qna_regdate}"
																	pattern="yyyy-MM-dd" />
															</p>
														</li>
														<li>
															<p class="views-comments">
																<i class="fa-regular fa-eye"></i>${dto.qna_views}<i
																	class="fa-regular fa-comment"></i>${dto.qna_comments}</p>
														</li>
													</ul>
												</div>
											</div>
										</div>
									</c:forEach>
									<!--  -->
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
	      url: "/good/user/qna/listqna.do",
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