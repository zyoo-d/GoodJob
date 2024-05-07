<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>마이페이지 &mdash; IT's GOODJOB</title>
<div id="app">
	<div class="main-wrapper main-wrapper-1">
		<div class="navbar-bg" id="mypageNavbar"></div>
		<nav class="navbar navbar-expand-lg main-navbar">
			<form class="form-inline mr-auto">
				<ul class="navbar-nav mr-3">
					<li><a href="#" data-toggle="sidebar"
						class="nav-link nav-link-lg"><i class="fas fa-bars"></i></a></li>
					<li><a href="#" data-toggle="search"
						class="nav-link nav-link-lg d-sm-none"><i
							class="fas fa-search"></i></a></li>
				</ul>
			</form>
			<ul class="navbar-nav navbar-right">
				<li class="dropdown dropdown-list-toggle"><a href="#"
					data-toggle="dropdown"
					class="nav-link nav-link-lg message-toggle beep"><i
						class="far fa-envelope"></i></a>
					<div class="dropdown-menu dropdown-list dropdown-menu-right">
						<div class="dropdown-header">
							Messages
							<div class="float-right">
								<a href="#">Mark All As Read</a>
							</div>
						</div>
						<div class="dropdown-list-content dropdown-list-message">
							<a href="#" class="dropdown-item dropdown-item-unread">
								<div class="dropdown-item-avatar">
									<img alt="image" src="assets/img/avatar/avatar-1.png"
										class="rounded-circle">
									<div class="is-online"></div>
								</div>
								<div class="dropdown-item-desc">
									<b>Kusnaedi</b>
									<p>Hello, Bro!</p>
									<div class="time">10 Hours Ago</div>
								</div>
							</a> <a href="#" class="dropdown-item dropdown-item-unread">
								<div class="dropdown-item-avatar">
									<img alt="image" src="assets/img/avatar/avatar-2.png"
										class="rounded-circle">
								</div>
								<div class="dropdown-item-desc">
									<b>Dedik Sugiharto</b>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit</p>
									<div class="time">12 Hours Ago</div>
								</div>
							</a> <a href="#" class="dropdown-item dropdown-item-unread">
								<div class="dropdown-item-avatar">
									<img alt="image" src="assets/img/avatar/avatar-3.png"
										class="rounded-circle">
									<div class="is-online"></div>
								</div>
								<div class="dropdown-item-desc">
									<b>Agung Ardiansyah</b>
									<p>Sunt in culpa qui officia deserunt mollit anim id est
										laborum.</p>
									<div class="time">12 Hours Ago</div>
								</div>
							</a> <a href="#" class="dropdown-item">
								<div class="dropdown-item-avatar">
									<img alt="image" src="assets/img/avatar/avatar-4.png"
										class="rounded-circle">
								</div>
								<div class="dropdown-item-desc">
									<b>Ardian Rahardiansyah</b>
									<p>Duis aute irure dolor in reprehenderit in voluptate
										velit ess</p>
									<div class="time">16 Hours Ago</div>
								</div>
							</a> <a href="#" class="dropdown-item">
								<div class="dropdown-item-avatar">
									<img alt="image" src="assets/img/avatar/avatar-5.png"
										class="rounded-circle">
								</div>
								<div class="dropdown-item-desc">
									<b>Alfa Zulkarnain</b>
									<p>Exercitation ullamco laboris nisi ut aliquip ex ea
										commodo</p>
									<div class="time">Yesterday</div>
								</div>
							</a>
						</div>
						<div class="dropdown-footer text-center">
							<a href="#">View All <i class="fas fa-chevron-right"></i></a>
						</div>
					</div></li>
				<li class="dropdown dropdown-list-toggle"><a href="#"
					data-toggle="dropdown"
					class="nav-link notification-toggle nav-link-lg beep"><i
						class="far fa-bell"></i></a>
					<div class="dropdown-menu dropdown-list dropdown-menu-right">
						<div class="dropdown-header">
							Notifications
							<div class="float-right">
								<a href="#">Mark All As Read</a>
							</div>
						</div>
						<div class="dropdown-list-content dropdown-list-icons">
							<a href="#" class="dropdown-item dropdown-item-unread">
								<div class="dropdown-item-icon bg-primary text-white">
									<i class="fas fa-code"></i>
								</div>
								<div class="dropdown-item-desc">
									Template update is available now!
									<div class="time text-primary">2 Min Ago</div>
								</div>
							</a> <a href="#" class="dropdown-item">
								<div class="dropdown-item-icon bg-info text-white">
									<i class="far fa-user"></i>
								</div>
								<div class="dropdown-item-desc">
									<b>You</b> and <b>Dedik Sugiharto</b> are now friends
									<div class="time">10 Hours Ago</div>
								</div>
							</a> <a href="#" class="dropdown-item">
								<div class="dropdown-item-icon bg-success text-white">
									<i class="fas fa-check"></i>
								</div>
								<div class="dropdown-item-desc">
									<b>Kusnaedi</b> has moved task <b>Fix bug header</b> to <b>Done</b>
									<div class="time">12 Hours Ago</div>
								</div>
							</a> <a href="#" class="dropdown-item">
								<div class="dropdown-item-icon bg-danger text-white">
									<i class="fas fa-exclamation-triangle"></i>
								</div>
								<div class="dropdown-item-desc">
									Low disk space. Let's clean it!
									<div class="time">17 Hours Ago</div>
								</div>
							</a> <a href="#" class="dropdown-item">
								<div class="dropdown-item-icon bg-info text-white">
									<i class="fas fa-bell"></i>
								</div>
								<div class="dropdown-item-desc">
									Welcome to Stisla template!
									<div class="time">Yesterday</div>
								</div>
							</a>
						</div>
						<div class="dropdown-footer text-center">
							<a href="#">View All <i class="fas fa-chevron-right"></i></a>
						</div>
					</div></li>
				<li class="dropdown"><a href="#" data-toggle="dropdown"
					class="nav-link dropdown-toggle nav-link-lg nav-link-user"> <img
						alt="image" src="/good/assets/img/avatar/avatar-1.png"
						class="rounded-circle mr-1"></a>
					<div class="dropdown-menu dropdown-menu-right">
						<a href="/good/user/mypage/main.do" class="dropdown-item has-icon">
							<i class="fas fa-cog"></i> Mypage
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item has-icon text-danger"> <i
							class="fas fa-sign-out-alt"></i> Logout
						</a>
					</div></li>
			</ul>
		</nav>
		<div class="main-sidebar sidebar-style-2" id="mypageSidebar">
			<aside id="sidebar-wrapper">
				<div class="sidebar-brand">
					<a href="/good/main.do">IT's Good Job</a>
				</div>
				<div class="sidebar-brand sidebar-brand-sm">
					<a href="/good/main.do">GJ</a>
				</div>
				<ul class="sidebar-menu">
					<li class="dropdown"><a href="#" class="nav-link has-dropdown"><i
							class="far fa-user"></i><span>개인정보</span></a>
						<ul class="dropdown-menu">
							<li><a class="nav-link" href="/good/user/mypage/editinfo.do">회원정보수정</a></li>
							<li><a class="nav-link" href="/good/user/mypage/unregister.do">회원탈퇴</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="nav-link has-dropdown"
						data-toggle="dropdown"><i class="far fa-star"></i> <span>스크랩</span></a>
						<ul class="dropdown-menu">
							<li><a class="nav-link" href="/good/heeyeon/scrap.jsp">내 스크랩 보기</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="nav-link has-dropdown"><i
							class="far fa-comments"></i> <span>활동내역</span></a>
						<ul class="dropdown-menu">
							<li><a class="nav-link" href="/good/heeyeon/review.jsp">기업리뷰</a></li>
							<li><a class="nav-link" href="/good/heeyeon/interview.jsp">면접후기</a></li>
							<li><a class="nav-link" href="/good/user/mypage/mystudy.do">스터디</a></li>
							<li><a class="nav-link" href="/good/heeyeon/qna.jsp">여기어때?</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="nav-link has-dropdown"><i
							class="far fa-thumbs-up"></i> <span>굿잡forU</span></a>
						<ul class="dropdown-menu">
							<li><a class="nav-link" href="/good/user/matching/viewwish.do">선호근무조건 입력/관리</a></li>
							<c:if test="${prefer==null||prefer=='0'}"><li><a class="nav-link" href="/good/user/matching/viewsurvey.do">굿잡forU 검사하기</a></li></c:if>
							<c:if test="${prefer=='1'}"><li><a class="nav-link" href="/good/user/matching/viewsurvey.do">굿잡forU 검사하기</a></li></c:if>
							<li><a class="nav-link" href="">검사 결과보기</a></li>
						</ul>
			</aside>
		</div>