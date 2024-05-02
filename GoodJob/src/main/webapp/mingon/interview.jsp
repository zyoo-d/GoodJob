<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<style>

.studyCard {
  display: flex;
  justify-content: space-between; 
  font-size: 13px;
}

#studyAddBtn {
	float: right;
	bottom: 20px;
}

 .itvContainer {
    
        display: flex;
        justify-content: space-between;
        margin-right: 750px;
        height: auto;
        
    }

.itvCard{
	
	margin: auto;
	width: 1000px;
	margin-bottom: 30px;	
	min-height: auto;
	margin-left: 10px;
}

.card-content-wrapper {
	
    display:flex;
 
    
}


.card-content-wrapper.card-content {
	  flex: 1;
    text-align: center; 
}


.card-content-wrapper .itvImgBox {

    margin-left: 1px;
}

#itvImg {
	width: 150px;
    height: 150px;
}



.card-tags i {
	position: relative;
	top: 110px;
}



#itvAddBtn {
	float: right;
	bottom: 20px;
}

.itvIconContainer select {
	margin-left: 10px;
	width: 200px;
}


#itvWriteContainer {
	margin:auto;
	width: 1000px;
	
}


.itvCpContent{
	width: auto;
	display: flex;
	justify-content: space-between;	
	align-items: center;
	
	
}

 #itvWriteContainer .card {
    margin-bottom: 20px; /* Adjust as needed */
  }

  .itvCpContent label {
    margin-right: 10px; /* Adjust as needed */
  }
  
  .itvCpContent i {
    margin-right: 10px; /* Adjust as needed */
  }

  .itvCpContent input[type="text"] {
    margin-right: 5px; /* Adjust as needed */
  }
  
.itvBtn {
	border-radius: 10px;
	width: 100px;
	text-align: center;
	height: 45px;
	
}
.itvQnABtn {
	border-radius: 10px;
	width: 100px;
	text-align: center;
	
}

.form-label {
	margin-top: 12px;
}

.itvInput {
	width: 600px;
	height: 150px;
	
}

.itvBasic {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.card-content-wrapper {
    flex-basis: calc(50% - 10px); /* 50% width with spacing */
    margin-bottom: 20px; /* Adjust spacing between rows */
}

.itvCpname {
	height: 40px;
}

.itvBtnList {
	
	display: flex;
	justify-content:flex-end;
	gap: 10px;
	
}

.itvDetail {
	display:none;
	color: red;
	
}

.itvdropdownContent { 
	    display: flex;
    flex-wrap: wrap;
    align-content: stretch;
    justify-content: flex-start;
    margin-bottom: 20px;

}
.itvContentFooter {
	display: flex;
	justify-content: space-around;
	flex-direction: column;
	 
}
.card-tags i {
	cursor: pointer;
	
}

.itvSession {
	margin: auto;
	
}
</style>
</head>

<%@include file="/WEB-INF/views/inc/header.jsp"%>

<body>

	<!-- ./end floating assets -->

	<section class="page-hero pt-16 pb-6">
		<div class="container">
			
				<div class="card-content-wrapper">
				<div class="card">
					<div class="card-content">
						<div class="px-4 text-center">
							<h1 class="mt-3 gradiTitle">
								<span>면접 후기 게시판</span>
							</h1>
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
								<input type="text" name="input" class="input" id="search-input"
									placeholder="검색어를 입력하세요">
								<button type="button" class="search" id="search-btn">
									<span class="material-symbols-outlined"> <span
										class="material-symbols-outlined">search</span>
									</span>
								</button>
							</form>
						</div>

						<div id="default_list_wrap" style="position: relative">
							<section class="list_recruiting">
								<div class="list-header">
									<div class="list-title">
										<p>
											<span class="material-symbols-outlined">subject</span>
										</p>
										<p>
											조회된 게시글은 총 <span id="list-cnt">1234</span>건입니다.
										</p>
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
										<section class="section">
		<div class="container">

		<div class="itvContainer">

			<div class="mb-8 md:col-6">
				<div class="card itvCard">

					<div class="card-content-wrapper">


						<div class="card-content itvCardContent">

							<div class="itvdropdownContent">
								<div class="itvImgBox">
									<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNH5sdJHmEqJ7qj_gOa--Q3ZSDeceHGQlfqZL_5A9ufw&s" id="itvImg" alt="" />
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
				<div class="card itvCard">

					<div class="card-content-wrapper">


						<div class="card-content">

							<div class="itvdropdownContent">
								<div class="itvImgBox">
									<img src="https://blog.kakaocdn.net/dn/HDY7T/btrY2our4Rw/Fw6bz0QroBUp1YxglkkwEK/img.webp" id="itvImg" alt="" />
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
				<div class="card itvCard">

					<div class="card-content-wrapper">


						<div class="card-content">

							<div class="itvdropdownContent">
								<div class="itvImgBox">
									<img src="https://img.etnews.com/photonews/2112/1484863_20211216163257_767_0001.jpg" id="itvImg" alt="" />
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
		
		</div>
			<div class="itvAdd">
				<a
					class="btn btn-outline-primary m-3 block min-w-[160px] sm:inline-block"
					id="itvAddBtn" href="#">글쓰기</a>
			</div>
	</div>

	</section>
									
									
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
				<li class="page-item"><a class="page-link" href="#"><span
						class="material-symbols-outlined paging-icon">keyboard_double_arrow_left</span></a></li>
				<li class="page-item"><a class="page-link" href="#"><span
						class="material-symbols-outlined paging-icon">navigate_before</span></a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">4</a></li>
				<li class="page-item"><a class="page-link" href="#">5</a></li>
				<li class="page-item"><a class="page-link" href="#"><span
						class="material-symbols-outlined paging-icon">navigate_next</span></a></li>
				<li class="page-item"><a class="page-link" href="#"><span
						class="material-symbols-outlined paging-icon">keyboard_double_arrow_right</span></a></li>
			</ul>
		</nav>
	</section>
	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
</body>
	<script>
  const arrows = document.querySelectorAll('.dw');
  arrows.forEach(arrow => {
    arrow.addEventListener('click', () => {
      const detailContent = arrow.parentNode.parentNode.nextElementSibling;
      if (detailContent) {
        detailContent.style.display = detailContent.style.display === 'none' ? 'block' : 'none';
      }
    });
  });
</script>
</html>