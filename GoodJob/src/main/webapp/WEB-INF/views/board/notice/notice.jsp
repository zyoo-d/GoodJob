
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
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>

<body>
	<div id="itvWriteContainer">
		<section class="page-hero pt-16 pb-6">
			<div class="container">
				<div class="card" id="itvWriteQnA">
					<div class="card-content-wrapper">
						<div class="card-content">
							<div class="px-4 text-center">
								<h1 class="mt-3 gradiTitle">
									<span>공지사항</span>
								</h1>
							</div>

							<div class="row mb-8">
								<div class="form-group mt-8 md:col-6 lg:col-4">
									<div class="qna-view-form qnaview">
										<p class="company">공지사항</p>
										<div class="title">
											<h2>${dto.nt_title}</h2>
										</div>
									</div>
								</div>
								<div class="textarea-group">
									<textarea cols="30" rows="10" readonly>${dto.nt_content}</textarea>
								</div>

								<div class="writer-info">
									<p class="writer">${dto.nickname }</p>
									<p class="write-date">(${dto.nt_regdate })</p>
									<c:if test="${lv == 2}">
										<button type="button" class="" value="수정">(수정</button>
										<form action="/good/board/notice/delnotice.do" method="post">
											<input type="hidden" name="nt_seq" value="${dto.nt_seq}">
											<button type="submit">삭제</button>
										</form>
									</c:if>
								</div>
							</div>
							<div class="moving-btn">
								<a href="#" class="btn btnBefore">이전글</a> <a href="#"
									class="btn btnList">목록으로</a> <a href="#" class="btn btnNext">다음글</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
		
	</script>
</body>
</html>