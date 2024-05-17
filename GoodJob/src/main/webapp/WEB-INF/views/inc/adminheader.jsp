<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<header>

	<div class="navbar-bg"></div>
	<nav class="navbar navbar-expand-lg main-navbar">
		<form class="form-inline mr-auto">
			<ul class="navbar-nav mr-3">
				<li><a href="#" data-toggle="sidebar"
					class="nav-link nav-link-lg"><i class="fas fa-bars"></i></a></li>
			</ul>
		</form>
		<ul class="navbar-nav navbar-right">
			<li class="dropdown"><a href="#" data-toggle="dropdown"
				class="nav-link dropdown-toggle nav-link-lg nav-link-user"> <img
					alt="image" src="/good/assets/img/avatar/avatar-1.png"
					class="rounded-circle mr-1">
					<div class="d-sm-none d-lg-inline-block">관리자</div>
			</a>
				<div class="dropdown-menu dropdown-menu-right">
					<a href="good/main.do" class="dropdown-item has-icon"> <i
						class="far fa-user"></i> Client Main
					</a> <a href="#!" class="dropdown-item has-icon"> <i
						class="fas fa-cog"></i> Settings
					</a>
					<div class="dropdown-divider"></div>
					<a href="/good/user/logout.do"
						class="dropdown-item has-icon text-danger"> <i
						class="fas fa-sign-out-alt"></i> Logout
					</a>
				</div></li>
		</ul>
	</nav>
	<div class="main-sidebar sidebar-style-2">
		<aside id="sidebar-wrapper">
			<div class="sidebar-brand">
				<a href="/good/admin/main.do" id="main-logo"><img
					src="/good/assets/logo.png" height="30" width="147" alt="logo" /></a>
			</div>
			<div class="sidebar-brand sidebar-brand-sm">
				<a href="/good/admin/main.do">GJ</a>
			</div>
			<ul class="sidebar-menu">
				<li class="dropdown" id="menu-user"><a href="#"
					class="nav-link has-dropdown"><i class="fas fa-user"></i><span>회원
							관리</span></a>
					<ul class="dropdown-menu">
						<li id="submenu-usermanagement"><a class="nav-link"
							href="/good/admin/listuser.do">회원 목록</a></li>
						<li id="submenu-userblock"><a class="nav-link"
							href="/good/admin/listblockuser.do">차단 관리</a></li>
						<li id="submenu-userreport"><a class="nav-link"
							href="/good/admin/listreportuser.do">신고 회원 관리</a></li>
					</ul></li>
				<li class="dropdown" id="menu-board"><a href="#"
					class="nav-link has-dropdown" data-toggle="dropdown"><i
						class="fas fa-chalkboard-user"></i> <span>게시판 관리</span></a>
					<ul class="dropdown-menu">
						<li id="submenu-reviewmain"><a class="nav-link"
							href="/good/admin/review/reviewstatistics.do">리뷰 통계</a></li>
						<li id="submenu-review"><a class="nav-link"
							href="/good/admin/review/review.do">승인 대기 리뷰</a></li>
						<li id="submenu-boardreport"><a class="nav-link"
							href="/good/admin/interview/interviewhold.do">승인 대기 면접 후기</a></li>
						<li id="submenu-boardreport"><a class="nav-link"
							href="/good/admin/boardreport.do">신고 게시글 관리</a></li>
						<li id="submenu-notice"><a class="nav-link"
							href="/good/board/notice.do">공지사항 관리</a></li>
					</ul></li>
				<li class="dropdown" id="menu-company"><a href="#"
					class="nav-link has-dropdown"><i class="fas fa-building"></i> <span>기업
							관리</span></a>
					<ul class="dropdown-menu">
						<li id="submenu-addcompany"><a class="nav-link"
							href="/good/gijun/company/addcompany.jsp">기업 등록</a></li>
						<li id="submenu-company"><a class="nav-link"
							href="/good/admin/company/company.do">기업 목록</a></li>
					</ul></li>
				<li class="dropdown" id="menu-survey"><a href="#"
					class="nav-link has-dropdown"><i class="far fa-file-alt"></i> <span>설문조사
							관리</span></a>
					<ul class="dropdown-menu">
						<li id="submenu-survey"><a class="nav-link"
							href="/good/admin/survey/survey.do">설문조사 목록/수정</a></li>
					</ul></li>
				<li  id="menu-mainpage"><a href="/good/main.do"
					class="nav-link"><i class="far fa-file-alt"></i> <span>회원 페이지</span></a>
					</li>
			</ul>
		</aside>
	</div>
</header>

<script>
	$(document).ready(

	$("li").on('click',

	function() {
		$(this).siblings().removeClass('active');
		$(this).addClass('active');
	}),

	$("a").on('click',

	function() {
		$(this).siblings().removeClass('active');
		$(this).addClass('active');
	}));
</script>