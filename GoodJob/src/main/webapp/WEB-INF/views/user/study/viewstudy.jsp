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
									<div class="qna-view-form qnaview recruit-present-info">
										<p class="company"> ${dto.cp_name}</p>
										<p>🗓️ 모집 마감일 : ~ ${dto.std_duedate}</p>
									</div>
									<div class="recruit-present-info mb-2">
										<h2>${dto.std_title}</h2>
										<c:if test="${dto.std_ing=='N'}">
											<a class="tag" href="#">모집중</a>
										</c:if>
									</div>
								</div>
								<div class="textarea-group">
									<textarea cols="30" rows="10" readonly>${dto.std_content}</textarea>
								</div>

								<div class="writer-info">
									<p class="writer">${dto.nickname}</p>

									<p class="write-date">${dto.std_regdate}</p>
									<c:if test="${not empty id && (dto.id==id || lv == '2')}">
									<a href="/good/user/editstudy.do?std_seq=${dto.std_seq}">(수정</a>
									<button type="button" id="btnDelStudy">/ 삭제)</button>
									</c:if>
								</div>
							</div>
							<div class="moving-btn">
								<a href="/good/user/liststudy.do?column=${column}&word=${word}&page=${page}" class="btn btnList">목록으로</a>
							</div>
						</div>
					</div>
					<div class="comment-list">
						<form>
							<div class="add-comment">
								<span><input type="text" placeholder="댓글을 입력하세요"></span>
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
										<td class="comment-num">1</td>
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
		$('#btnDelStudy').click(function() {
			if (confirm("게시글을 삭제하시겠습니까?")) {
				location.href = "/good/user/delstudy.do?std_seq=${dto.std_seq}";
			}
		});
	</script>
</body>
</html>