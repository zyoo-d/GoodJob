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
	<div id="itvWriteContainer">
		<section class="page-hero pt-16 pb-6">
			<div class="container">
				<div class="card" id="itvWriteQnA">
					<div class="card-content-wrapper">
						<div class="card-content">
							<div class="px-4 text-center">
								<h1 class="mt-3 gradiTitle">
									<span>스터디 게시판</span>
								</h1>
							</div>

							<div class="row mb-8">
								<div class="form-group mt-8">
									<div class="qna-view-form qnaview">
										<p class="company">스터디 게시판</p>
									</div>
									<div class="recruit-present-info mb-2">
										<h2>제목입니다.</h2>
										<p>🗓️ 스터디 모임 마감 : ~ 05.05(일)</p>
									</div>
								</div>
								<div class="textarea-group">
									<textarea cols="30" rows="10" readonly>내용입니다.내용입니다.내용입니다.</textarea>
								</div>

								<div class="writer-info">
									<p class="writer">zyww</p>
									<p class="write-date">(2024-05-01)</p>
									<button type="button" class="" value="수정">(수정</button>
									<button type="button" class="" value="삭제">/ 삭제)</button>
								</div>
							</div>
							<div class="moving-btn">
								<a href="#" class="btn btnBefore">이전글</a> <a href="#"
									class="btn btnList">목록으로</a> <a href="#" class="btn btnNext">다음글</a>
							</div>
						</div>
					</div>
					<div class="comment-list">
						<form>
							<div class="add-comment">
								<span><input type="text" id="commentContent" placeholder="댓글을 입력하세요"></span>
								<button type="button" class="btn btnAddComment">
									<span class="material-symbols-outlined">done_outline</span>
								</button>
							</div>
						</form>

						<form class="comment-form" action="#" method="POST">
							<table id="comment">
								<tbody>
									<tr>
										<th>번호</th>
										<th>댓글</th>
										<th>댓글정보</th>
									</tr>
									<tr>
										<td class="comment-num" id="commentNum">1</td>
										<td class="commentContent">
											<p>content</p>
										</td>
										<td class="commentInfo">
											<div>
												<p>2024-05-01 08:25</p>
												<div class="comment-edit">
													<p>작성자</p>
													<div class="comment-icon">
														<span class="material-symbols-outlined">delete</span> <span
															class="material-symbols-outlined">edit_note</span>
													</div>
												</div>

											</div>
										</td>
									</tr>
								</tbody>
							</table>

							<!-- 댓글 페이징 -->
							<div class="comment-paging">
								<button type="button" class="btn more-comment">댓글 더보기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	$(document).ready(function() {
	    $(".btnAddComment").click(function() {
	        submitComment(); // 클릭 시 AJAX를 통해 댓글을 제출하는 함수를 호출합니다.
	    });
	});

	function submitComment() {
	    var commentContent = $("#commentContent").val(); // 댓글 내용을 입력 받는 input 필드의 id가 "commentContent"로 가정합니다.
	    var id = "<%= session.getAttribute("id") %>"; 
	    var currentDate = new Date().toISOString(); // 현재 날짜와 시간을 ISO 형식으로 변환합니다.
	    $.ajax({
	        type: "POST",
	        url: "/good/study.do", // 백엔드 서블릿/컨트롤러의 URL
	        data: {
	            content: commentContent,
	            id: id,
	            currentDate: currentDate
	        }, // 댓글 내용과 함께 글 번호, 사용자 ID, 작성일자를 서버로 전송합니다.
	        success: function(response) {
	        	
	            // 응답에는 JSON 형식으로 새로 삽입된 댓글 데이터가 포함됩니다.
	            var newComment = response;
	            // 새로운 댓글을 동적으로 댓글 섹션에 추가합니다.
	            // jQuery를 사용하여 새로운 댓글을 HTML로 만들고 댓글 섹션에 추가합니다.
	        },
	        error: function(xhr, status, error) {
	            console.error("댓글 제출 중 오류가 발생했습니다:", error);
	        }
	    });
	}
	</script>
</body>
</html>