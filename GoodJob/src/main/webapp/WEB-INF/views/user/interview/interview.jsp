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
								<span>면접 후기 게시판</span>
							</h1>
							<p class="mt-6">면접 후기 정보를 서로 나누고, 성장하는 게시판입니다.</p>
						</div>

						<div>
							<form id="searchForm" method="GET"
								action="/good/board/interview/interview.do">
								<input type="text" name="cp_name" class="input"
									id="search-input" placeholder="기업명으로 검색하기">
								<button type="submit" class="search" id="search-btn">
									<span class="material-symbols-outlined"> <span
										class="material-symbols-outlined">search</span>
									</span>
								</button>
							</form>
						</div>
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
						</div>
					</div>

					<div class="md:col-6" id="interview-list">
						<c:forEach var="dto" items="${list}">
							<div class="card itvCard">
								<div class="card-content-wrapper">
									<div class="itvdropdownContent">
										<div class="itvImgBox">
											<img src="${dto.IMAGE}" id="itvImg" alt="" />

										</div>
										<div class="itvCardTags">
											<a class="tag" href="#">${dto.ITV_WHETHER}</a> <i
												class="fa-solid fa-arrow-down-short-wide dw"></i>
										</div>
										<div class="itvContentFooter">
											<h3>${dto.ITV_CPNAME}</h3>
											<div id="itvContentSummary">
												<p>${dto.ITV_CAREER}/${dto.ITV_MEETDATE}/ 전반적평가 :
													${dto.ITV_DIFFICULTY}</p>
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
				</div>
			</div>
			<nav class="PageBox z-custom" aria-label="Page navigation example">
						<ul class="pagination z-custom">${pagebar}</ul>
					</nav>
		</div>

	</section>


	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	<c:if test="${map.search == 'y'}">
	//검색중 상태 유지
	$('input[name=cp_name]').val('${map.cp_name}');
	</c:if>
	
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