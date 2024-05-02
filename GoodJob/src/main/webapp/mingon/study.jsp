<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<style>

</style>
</head>

<%@include file="/WEB-INF/views/inc/header.jsp" %>

<body>

<!-- ./end floating assets -->

<section class="page-hero pt-16 pb-6">
  <div class="container">
    <div class="card" id="itvWriteQnA">
      <div class="card-content-wrapper">
        <div class="card-content">
          <div class="px-4 text-center">
            <h1 class="mt-3 gradiTitle"><span>스터디 스케줄 게시판</span></h1>
            <p class="mt-6">다양한 면접 스터디를 가입.개설 해주세요.</p>
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
              <input type="text" name="input" class="input" id="search-input" placeholder="검색어를 입력하세요">
              <button type="button" class="search" id="search-btn">
                <span class="material-symbols-outlined">
                  <span class="material-symbols-outlined">search</span>
                </span>
              </button>
            </form>
          </div>

          <div id="default_list_wrap" style="position:relative">
            <section class="list_recruiting">
              <div class="list-header">
                <div class="list-title">
                  <p><span class="material-symbols-outlined">subject</span></p><p>조회된 게시글은 총 <span id="list-cnt">1234</span>건입니다.</p>
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
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6 line_limit">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				<div class="mb-8 sm:col-6 lg:col-4">
					<div class="rounded-xl bg-white p-6 shadow-lg lg:p-8">
						<div class="gradient-number relative inline-block studyCard">
							<i class="fa-solid fa-calendar-days">2024-04-29 ~ 2024-05-05</i>
							<a class="tag" href="#">모집중</a>
						</div>
						<h4 class="my-6">자바 코딩테스트 스터디 모집합니다.</h4>
						<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
							class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
							class="changelogs-tag bg-[#973CFF]">코딩테스트</span>

					</div>
				</div>
				   <div class="moving-btn studyAdd">
                            <a href="#" class="btn btnList">글쓰기</a>
                </div>
			</div>
                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
    </div>
  </div>

  <nav class="PageBox" aria-label="Page navigation example">
    <ul class="pagination">
      <li class="page-item"><a class="page-link" href="#"><span class="material-symbols-outlined paging-icon">keyboard_double_arrow_left</span></a></li>
      <li class="page-item"><a class="page-link" href="#"><span class="material-symbols-outlined paging-icon">navigate_before</span></a></li>
      <li class="page-item"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item"><a class="page-link" href="#">4</a></li>
      <li class="page-item"><a class="page-link" href="#">5</a></li>
      <li class="page-item"><a class="page-link" href="#"><span class="material-symbols-outlined paging-icon">navigate_next</span></a></li>
      <li class="page-item"><a class="page-link" href="#"><span class="material-symbols-outlined paging-icon">keyboard_double_arrow_right</span></a></li>
    </ul>
  </nav>
</section>
<%@include file="/WEB-INF/views/inc/footer.jsp" %>

</body>

</html>