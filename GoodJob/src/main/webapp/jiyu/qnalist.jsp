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
    <div class="z-custom card" id="itvWriteQnA">
      <div class="">
        <div class="card-content">
          <div class="px-4 text-center">
            <h1 class="mt-3 gradiTitle"><span>여기어때</span></h1>
            <p class="qanlist mt-6">자유롭게 회사정보를 공유해보세요.</p>
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
                  <div class="qna-list box_item row mx-0 rounded-[20px] bg-white px-10 shadow-lg">
                    <div class="qna-list-index">
                      1
                    </div>
                    <div class="com-title">
                      <div class="recruit-present-info">
                        <div class="com-name">
                          <a href="#" class="str_tit_com" target="_blank"></a>
                          <span>네이버</span>
                        </div>
                      </div>
                      <div class="col notification_info">
                        <div class="qnalist job_tit">
                          <a class="str_tit_title new" id="rec_link_48103333" onclick="" href="#" target="_blank" onmousedown=""><span>이 회사 어때요?</span></a>
                        </div>
                      </div>
                    </div>
                    <div class="com-info">
                      <div class="col recruit_info">
                        <ul>
                          <li>
                            <p class="writer">zyxxx</p>
                          </li>
                          <li>
                            <p class="qna-add-date">2024-04-30</p>
                          </li>
                        </ul>
                      </div>
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

</body>

</html>