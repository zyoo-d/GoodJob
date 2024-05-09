<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
#itvContentSummary p{
	display: flex;
	justify-content: flex-start;
}



</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>


	<section class="section">
		<div class="container">
			<div class="card" id="itvWriteQnA">
				<div class="px-4 text-center">
					<h1 class="mt-3 gradiTitle">
						<span>면접 스터디 게시판</span>
					</h1>
					<p class="mt-6">면접 스터디를 개설 가입 하는 게시판입니다.</p>
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
									<div class="moving-btn studyAdd mr-2">
										<a href="/good/board/interview/itvWrite.do" class="btn btnList">글쓰기</a>
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
							</div>

				<div class="mb-8 md:col-6" id="interview-list">
					<c:forEach var="dto" items="${list}">
						<div class="card itvCard">
							<div class="card-content-wrapper">
								<div class="itvdropdownContent">
									<div class="itvImgBox">
										<img
											src="${dto.IMAGE}"
											id="itvImg" alt="" />

									</div>
									<div class="itvCardTags">
										<a class="tag" href="#">${dto.ITV_WHETHER}</a> <i
											class="fa-solid fa-arrow-down-short-wide dw"></i>
									</div>
									<div class="itvContentFooter">
										<h3 class="h4 card-title">

											<p>${dto.ITV_CPNAME}</p>
										</h3>
										<div id="itvContentSummary">
											<p>
												${dto.ITV_CAREER}
												${dto.ITV_MEETDATE} 
												전반적 평가 :${dto.ITV_DIFFICULTY}
											</p>
										</div>
									</div>
								</div>
								<div class="itvDetail">
									<div class="itvDetailEmo"></div>
									<div class="itvDetailInfo">
										<strong class="itvDetailInfoTitle">면접 유형</strong> <span>${dto.ITV_CATEGORY}</span>
									</div>
									<div class="itvDetailInfo">
										<strong class="itvDetailInfoTitle">면접 인원</strong> <span>${dto.ITV_PERSONNEL}</span>
									</div>
									<div class="itvDetailInfo">
										<strong class="itvDetailInfoTitle">면접 질문</strong>
										<p>${dto.ITV_QUESTION}</p>
									</div>
									<div class="itvDetailInfo">
										<strong class="itvDetailInfoTitle">TIP 및 특이사항</strong>
										<p>${dto.ITV_TIP}</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			<nav class="PageBox z-custom" aria-label="Page navigation example">
			<ul class="pagination z-custom">${pagebar}</ul>
			</nav>
			</div>
			
		</div>

	</section>


	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	const arrows = document.querySelectorAll('.dw');
	arrows.forEach(arrow => {
	  arrow.addEventListener('click', () => {
	    const detailContent = arrow.parentNode.parentNode.nextElementSibling;
	    if (detailContent) {
	      detailContent.classList.toggle('show'); // 클래스 토글링
	    }
	  });
	});
	
</script>


</body>
</html>