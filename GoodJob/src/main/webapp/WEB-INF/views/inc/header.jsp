<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
</style>
<header class="header">
	<nav class="navbar container">
		<!-- logo -->
		<div class="order-0 mb-2 mr-3">
			<a href="/good/main.do"> <img src="/good/assets/logo.png"
				height="30" width="147" alt="logo" />
			</a>
		</div>
		<!-- navbar toggler -->
		<input id="nav-toggle" type="checkbox" class="hidden" /> <label
			id="show-button" for="nav-toggle"
			class="order-1 flex cursor-pointer items-center lg:order-1 lg:hidden">
			<title>Menu Open</title>
		</label> <label id="hide-button" for="nav-toggle"
			class="order-2 hidden cursor-pointer items-center lg:order-1">
			<title>Menu Close</title>
		</label>
		<!-- /navbar toggler -->
		<ul id="nav-menu"
			class="navbar-nav order-2 hidden flex-[0_0_100%] lg:order-1 lg:flex lg:w-auto lg:flex-auto lg:justify-center space-x-4">
			<li class="nav-item"><a href="/good/user/company/companylist.do"
				class="nav-link">기업</a></li>
			<li class="nav-item"><a href="/good/user/recruit/listrecruit.do"
				class="nav-link">채용</a></li>
			<li class="nav-item"><c:if test="${prefer==null||prefer=='0'}">
					<a href="/good/user/matching/viewsurvey.do" class="nav-link">굿잡forU</a>
				</c:if> <c:if test="${wish=='0'&&prefer=='1'}">
					<a href="/good/user/matching/viewwish.do" class="nav-link">굿잡forU</a>
				</c:if> <c:if test="${wish=='1'&&prefer=='1'}">
					<a href="/good/user/matching.do" class="nav-link">굿잡forU</a>
				</c:if></li>
			<li class="nav-item nav-dropdown group relative"><span
				id="communav" class="nav-link inline-flex items-center"> 커뮤니티
			</span>
				<ul
					class="nav-dropdown-list hidden group-hover:block lg:invisible lg:absolute lg:block lg:opacity-0 lg:group-hover:visible lg:group-hover:opacity-100"
					id="commu-menu">
					<li class="nav-dropdown-item"><a
						href="/good/user/study/liststudy.do" class="nav-dropdown-link">스터디</a></li>
					<li class="nav-dropdown-item"><a href="#!"
						class="nav-dropdown-link">면접후기</a></li>
					<li class="nav-dropdown-item"><a
						href="/good/user/qna/listqna.do" class="nav-dropdown-link">여기어때?</a></li>
				</ul></li>
			<li class="nav-item"><a href="/good/board/notice.do"
				class="nav-link">공지사항</a></li>
			<li class="nav-item">
				<form method="get" action="/good/user/company/companyview.do?">
					<div class="view-form addqna">

						<div class="search-element addqna-form" id="headSearch">
							<div id="searchResults" class="dropdown-content addqna"></div>
							<input class="form-control company" type="search"
								placeholder="기업을 검색해보세요." aria-label="Search"
								id="searchInputHead">
							<button id="btnSearch" class="btn" type="submit">
								<i class="fas fa-search"></i>
							</button>
							<input type="hidden" id="cp_seq" value="" name="cp_seq">
						</div>
					</div>

				</form>
			</li>

			<!-- 로그인 전 -->
			<c:if test="${empty id}">
				<li class="nav-item mt-3.5 lg:hidden"><a
					class="btn btn-primary no-shadow btn-sm mb-2"
					href="/good/user/signin.do">로그인</a></li>
				<li class="nav-item">
					<div
						class="order-1 ml-auto hidden items-center md:order-2 md:ml-0 lg:flex mt-2">
						<a class="btn btn-primary no-shadow btn-sm"
							href="/good/user/signin.do">로그인</a>
					</div>
				</li>
				<li class="nav-item">
					<div
						class="mt-2 order-1 ml-auto hidden items-center md:order-2 md:ml-0 lg:flex">
						<a class="btn btn-outline-primary btn-sm"
							href="/good/user/signup.do">회원가입</a>
					</div>
				</li>
			</c:if>

			<!-- 로그인 후 -->
			<c:if test="${not empty id}">
				<li class="nav-item mt-3.5 lg:hidden"><a
					class="btn btn-primary  no-shadowbtn-sm mb-2"
					href="/good/user/logout.do">로그아웃</a></li>
				<li class="nav-item">
					<div
						class="mt-2 order-1 ml-auto hidden items-center md:order-2 md:ml-0 lg:flex">
						<a class="btn btn-primary no-shadow btn-sm"
							href="/good/user/mypage/main.do">마이페이지</a>
					</div>
				</li>
				<li class="nav-item">
					<div
						class="mt-2 order-1 ml-auto hidden items-center md:order-2 md:ml-0 lg:flex">
						<a class="btn btn-outline-primary btn-sm"
							href="/good/user/logout.do">로그아웃</a>
					</div>
				</li>
			</c:if>
		</ul>
	</nav>
</header>
<script>

//기업 검색 자동완성 js
$(document).ready(function(){
	$('#searchInputHead').keyup(function(){
		var input = $(this).val();
		console.log(input.length);
		
		if(input.length==0){
			$('#searchResults').html("");
		} else {
		$.ajax({ 
			type: 'get',
			url : '/good/user/searchcompany.do',
			data : {query : input},
			success : function(result){ 
				$('#searchResults').html("");
				result.forEach(dto =>{
					$('#searchResults').append(`<div class="dropdown-item" data-cpseq="\${dto.cp_seq}">\${dto.cp_name}</div>`);
				});
			},
			error : function(){
				$('#searchResults').append('<div class="dropdown-item">오류</div>');
			}
		});
		}
	});
	
	$('#searchResults').on('click', '.dropdown-item', function() {
		$('#searchInputHead').val($(this).text());
		$('#cp_seq').val($(this).data('cpseq'));
		$('#searchResults').html("");
	});	
})


	$(document).ready(
			function() {
				var currentPath = window.location.pathname;

				$('#nav-menu a').each(
						function() {
							var linkPath = new URL($(this).attr('href'),
									window.location.origin).pathname;

							if (currentPath.indexOf(linkPath) === 0) {
								$(this).addClass('active');

								if ($(this).parents("#commu-menu").length) {
									$('#communav').addClass('active');
								}
							}
						});
			});
</script>