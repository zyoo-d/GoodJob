<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
.list {
	text-align:left;
	width: 500px;
}
tr{
	border-bottom: 1px solid blue;
}

</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp" %>
<body>


<!-- Common hero -->
<section class="page-hero pt-16 pb-10">
  <div class="container">
    <div class="text-center">
      <ul
        class="breadcrumb inline-flex h-8 items-center justify-center space-x-2 rounded-3xl bg-theme-light px-4 py-2"
      >
        <li class="leading-none text-dark">
          <a class="inline-flex items-center text-primary" href="#">
            <svg
              class="mr-1.5"
              width="15"
              height="15"
              viewBox="0 0 16 16"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M13.1769 15.0588H10.3533V9.41178H5.64744V15.0588H2.82391V6.58825H1.88274V16H14.118V6.58825H13.1769V15.0588ZM6.58862 15.0588V10.353H9.41215V15.0588H6.58862ZM15.8084 6.09225L15.2512 6.85178L8.00038 1.52472L0.749559 6.8499L0.192383 6.09131L8.00038 0.357666L15.8084 6.09225Z"
                fill="black"
              />
            </svg>
            <span class="text-sm leading-none mx-auto max-w-[768px]">질문게시판</span>
          </a>
        </li>
      </ul>
    </div>
    <div class="page-hero-content  text-center">
      <h1 class="mb-5 mt-8">
        여기어때?
      </h1>
    </div>
  </div>
</section>
<!-- end Common hero -->

<!-- Integrations -->
<div>
	
</div>
<section class="section pt-0">
  <div class="container">
    <div class="row justify-center">
    </div>
    <div class="row mt-10 integration-tab-items">
     

        
      <div data-groups='["social"]'>
        <div class="rounded-xl bg-white px-10 pt-11 pb-8 shadow-lg">
          <div class="integration-card-head flex items-center space-x-4">
            <img src="images/icons/twitter-colored.svg" alt="" />
            <div>
            	<table class="list">
            		<tr class="list-header">
              			<th><h4 class="h4">Title</h4></th>
              			<th>기업명</th>
            			<th>작성자</th>
            			<th>작성시간</th>
            		</tr>
	            	<tr>
						<td><h4 class="h4">여기에서 근무해보신분?</h4></td>
						<td>기업명</td>
						<td>홍길동</td>
						<td>2024-04-26</td>
					</tr>
          
            	</table>
            </div>
          </div>
        </div>
      </div>
     
<!-- ./end Integrations -->	
	
<%@include file="/WEB-INF/views/inc/footer.jsp" %>	
	<script>

	</script>
</body>
</html>