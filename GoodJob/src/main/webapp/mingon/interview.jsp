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





				<div class="mb-8 md:col-6">


					<div class="card itvCard">

						<div class="card-content-wrapper">


							<div class="card-content">

								<div class="itvdropdownContent">
									<div class="itvImgBox">
										<img
											src="https://blog.kakaocdn.net/dn/HDY7T/btrY2our4Rw/Fw6bz0QroBUp1YxglkkwEK/img.webp"
											id="itvImg" alt="" />
									</div>
									<div class="card-tags">
										<a class="tag" href="#">합격 후기</a> <i
											class="fa-solid fa-arrow-down-short-wide dw"></i>

									</div>
									<div class="itvContentFooter">
										<h3 class="h4 card-title">
											<p>네이버 하반기 공채 면접</p>
										</h3>
										<p>면접내용 요약면접내용</p>
									</div>

								</div>

								<div class="itvDetail">
									<div class="itvDetailEmo"></div>
									<div class="itvDetailInfo">
										<strong>면접 유형</strong>
											   <span>태그</span>
									</div>
									<div class="itvDetailInfo">
										<strong>면접 인원</strong>
											   <span>태그</span>
									</div>
									<div class="itvDetailInfo">
										<strong>전형 및 면접 진행 방식</strong>
											   <span>내용</span>
									</div>
									<div class="itvDetailInfo">
										<strong>면접 질문</strong>
											   <span>내용</span>
									</div>

								</div>


							</div>
						</div>
					</div>
					<div class="card itvCard">

						<div class="card-content-wrapper">


							<div class="card-content">

								<div class="itvdropdownContent">
									<div class="itvImgBox">
										<img
											src="https://blog.kakaocdn.net/dn/HDY7T/btrY2our4Rw/Fw6bz0QroBUp1YxglkkwEK/img.webp"
											id="itvImg" alt="" />
									</div>
									<div class="card-tags">
										<a class="tag" href="#">합격 후기</a> <i
											class="fa-solid fa-arrow-down-short-wide dw"></i>

									</div>
									<div class="itvContentFooter">
										<h3 class="h4 card-title">
											<p>네이버 하반기 공채 면접</p>
										</h3>
										<p>면접내용 요약면접내용</p>
									</div>

								</div>

								<div class="itvDetail">
									<div class="itvDetailEmo"></div>
									<div class="itvDetailInfo">
										<strong>면접 유형</strong>
										<p>내용</p>
									</div>
									<div class="itvDetailInfo">
										<strong>면접 인원</strong>
										<p>내용</p>
									</div>
									<div class="itvDetailInfo">
										<strong>전형 및 면접 진행 방식</strong>
										<p>내용</p>
									</div>
									<div class="itvDetailInfo">
										<strong>면접 질문</strong>
										<p>내용</p>
									</div>

								</div>


							</div>
						</div>
					</div>
				



				</div>
					   <div class="moving-btn studyAdd">
                            <a href="#" class="btn btnList">글쓰기</a>
                </div>
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