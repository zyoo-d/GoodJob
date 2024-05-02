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
.card {
    height: auto;
    min-height: 100vh;
    overflow-y: visible;
    max-width: 1000px;
    margin: 0 auto;
}

.list_info {
    display: flex;
    justify-content: flex-end;
    align-items: center;
}

.box_item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    border-bottom: 1px solid #ccc;
    margin-bottom: 10px;
}

.list-index {
    flex: 0.1;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 40px;
    background-color: #6777EE;
    color: #FFF;
    border-radius: 50%; /* Use 50% for a perfect circle */
    font-size: 0.9rem;
    line-height: 40px; /* Match the circle's height */
    margin-right: 10px;
    text-align: center;
}

.com-title {
    flex: 6;
}

.com-info {
    flex: 0.9;
    border-left: 2px solid #ccc;
    padding-left: 20px;
}

.support_detail {
    text-align: right;
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-end;
}

.date {
    font-size: 15px;
    margin-right: 8px;
}

.recruit-present-info {
    display: flex;
    justify-content: space-between;
    width: 100%;
    align-items: center;
}

.job_sector {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 5px;
}

.com-name {
    font-size: 1.1rem;
    color: #707070;
}

.job_tit {
    font-size: 1.5rem;
    color: #333;
}

.PageBox {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    margin: 0 auto;
}

#list-cnt {
    color: #6777EE;
}

.list-header {
    margin: 15px 0;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.InpBox {
    margin-right: 5px;
    border: 1px solid #BFBFBF;
    border-radius: 6px;
    overflow: hidden;
    background-color: white;
    position: relative;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    width: auto;
}

.InpBox select {
    width: 100%;
    max-width: 80px;
    min-width: 80px;
    padding: 8px 16px;
    border: none;
    background: transparent;
    appearance: none;
    color: #707070;
    font-size: 14px;
}

.InpBox::after {
    content: '\25BC';
    position: absolute;
    top: 0;
    right: 10px;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #BFBFBF;
    font-size: 10px;
}

.InpBox:hover {
    border-color: #6777EE;
    background-color: #6777EE;
    cursor: pointer;
}

.InpBox:hover select {
    color: #FFF;
}

.InpBox select option {
    color: #707070;
}

.list {
    text-align: left;
    width: 500px;
}

.studyAdd {
	display: flex;
	justify-content: flex-end;

}

.studyAdd a {
		color: #7F77F1;
}

#searchForm {
    display: flex;
    align-items: center;
    justify-content: center;
    background: #FFFFFF;
    padding: 10px 20px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    border-radius: 25px;
    width: 50%;
    margin: 20px auto;
}

#searchForm .input {
    flex-grow: 1;
    border: none;
    padding: 8px 15px;
    font-size: 14px;
    outline: none;
}

#searchForm button.search {
    background: #6777EE;
    border: none;
    color: white;
    padding: 8px 20px;
    border-radius: 0 15px 15px 0;
    cursor: pointer;
    font-size: 14px;
}

#searchForm button.search:hover {
    background-color: #5a67d8;
}

#searchForm .input:focus {
    outline: none;
    border: none;
    box-shadow: none;
}

.list-title {
    margin-left: 10px;
}

.list-title span,
.list-title p {
    font-size: 1.5rem;
    display: inline;
    color: #333;
}

.gradiTitle span {
    background: linear-gradient(184.78deg, #535AED 7.64%, #004F8B 120.07%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
}

.mt-6 {
    margin-top: 1.5rem;
}

.PageBox {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    margin-top: 3rem;
    margin: 3rem auto 0;
    text-align: center;
}

.pagination {
    display: flex;
    list-style: none;
    padding: 0;
}

.pagination li {
    margin: 0;
    display: flex;
    align-items: center;
}

.pagination li a {
    display: flex;
    align-items: center;
    justify-content: center;
    min-height: 36px;
    padding: 8px 12px;
    color: #000;
    text-decoration: none;
    border: 1px solid #ddd;
    background-color: #fff;
    transition: background-color 0.3s, color 0.3s;
}

.paging-icon {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 4px;
    margin-top: 3px;
    min-height: 23px;
    font-size: 18px;
    color: #6777EE;
}

.pagination li.active a,
.pagination li a:hover,
.pagination li.active a span,
.pagination li a:hover span {
    background-color: #6777EE;
    color: #FFF;
    border-color: #6777EE;
}

.pagination li:first-child a {
    border-top-left-radius: 5px;
    border-bottom-left-radius: 5px;
}

.pagination li:last-child a {
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
}
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