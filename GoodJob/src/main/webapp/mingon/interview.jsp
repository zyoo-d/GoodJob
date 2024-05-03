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
							<div class="itvdropdownContent">
								<div class="itvImgBox">
									<img
										src="https://blog.kakaocdn.net/dn/HDY7T/btrY2our4Rw/Fw6bz0QroBUp1YxglkkwEK/img.webp"
										id="itvImg" alt="" />
										
								</div>
								<div class="itvCardTags">
									<a class="tag" href="#">불합격</a> <i
										class="fa-solid fa-arrow-down-short-wide dw"></i>

								</div>
								<div class="itvContentFooter">
									<h3 class="h4 card-title">
										<p>회사 이름</p>
									</h3>
									<p> 경력 면접일자 전반적 평가 난이도 </p>
								</div>

							</div>

							<div class="itvDetail">
								<div class="itvDetailEmo"></div>
								<div class="itvDetailInfo">
									<strong class="itvDetailInfoTitle">면접 유형</strong> <span>태그</span>
								</div>
								<div class="itvDetailInfo">
									<strong class="itvDetailInfoTitle">면접 인원</strong> <span>태그</span>
								</div>
								<div class="itvDetailInfo">
									<strong class="itvDetailInfoTitle">면접 질문</strong>
									<p>가장 생각나는 질문 1가지</p>
								</div>
								<div class="itvDetailInfo">
									<strong class="itvDetailInfoTitle">TIP 및 특이사항</strong>
									<p>1차 면접은 1대 다 면접인지 몰랐기 때문에 많은 긴장을 하고 갔으나 압박 면접은 아니었고 회사
										분위기가 젊다 보니 그들과 잘 어우러질 수 있는지를 좀 많이 본 것 같고 2차 면접은 대표가 최종 결정을 하는
										면접 으로 크파가 원하는 멤버 상에 부합하는지를 많이 보는것 같다.</p>
								</div>

							</div>



						</div>
					</div>
					<div class="card itvCard">
						<div class="card-content-wrapper">
							<div class="itvdropdownContent">
								<div class="itvImgBox">
									<img
										src="https://blog.kakaocdn.net/dn/HDY7T/btrY2our4Rw/Fw6bz0QroBUp1YxglkkwEK/img.webp"
										id="itvImg" alt="" />
								</div>
								<div class="itvCardTags">
									<a class="tag" href="#">합격</a> <i
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
									<strong class="itvDetailInfoTitle">면접 유형</strong> <span>태그</span>
								</div>
								<div class="itvDetailInfo">
									<strong class="itvDetailInfoTitle">면접 인원</strong> <span>태그</span>
								</div>
								<div class="itvDetailInfo">
									<strong class="itvDetailInfoTitle">면접 질문</strong>
									<p>가장 생각나는 질문 1가지</p>
								</div>
								<div class="itvDetailInfo">
									<strong class="itvDetailInfoTitle">TIP 및 특이사항</strong>
									<p>1차 면접은 1대 다 면접인지 몰랐기 때문에 많은 긴장을 하고 갔으나 압박 면접은 아니었고 회사
										분위기가 젊다 보니 그들과 잘 어우러질 수 있는지를 좀 많이 본 것 같고 2차 면접은 대표가 최종 결정을 하는
										면접 으로 크파가 원하는 멤버 상에 부합하는지를 많이 보는것 같다.</p>
								</div>

							</div>



						</div>
					</div>
					<div class="card itvCard">
						<div class="card-content-wrapper">
							<div class="itvdropdownContent">
								<div class="itvImgBox">
									<img
										src="https://blog.kakaocdn.net/dn/HDY7T/btrY2our4Rw/Fw6bz0QroBUp1YxglkkwEK/img.webp"
										id="itvImg" alt="" />
								</div>
								<div class="itvCardTags">
									<a class="tag" href="#">합격</a> <i
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
									<strong class="itvDetailInfoTitle">면접 유형</strong> <span>태그</span>
								</div>
								<div class="itvDetailInfo">
									<strong class="itvDetailInfoTitle">면접 인원</strong> <span>태그</span>
								</div>
								<div class="itvDetailInfo">
									<strong class="itvDetailInfoTitle">면접 질문</strong>
									<p>가장 생각나는 질문 1가지</p>
								</div>
								<div class="itvDetailInfo">
									<strong class="itvDetailInfoTitle">TIP 및 특이사항</strong>
									<p>1차 면접은 1대 다 면접인지 몰랐기 때문에 많은 긴장을 하고 갔으나 압박 면접은 아니었고 회사
										분위기가 젊다 보니 그들과 잘 어우러질 수 있는지를 좀 많이 본 것 같고 2차 면접은 대표가 최종 결정을 하는
										면접 으로 크파가 원하는 멤버 상에 부합하는지를 많이 보는것 같다.</p>
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