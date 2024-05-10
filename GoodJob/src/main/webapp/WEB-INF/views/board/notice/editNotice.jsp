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
	<section class="page-hero pt-16 pb-6">
		<div class="container">
			<div class="card" id="itvWriteQnA">
				<form method="POST" action="/good/user/qna/editqna.do">
				<div class="card-content-wrapper">
					<div class="card-content">
						<div class="px-4 text-center">
							<h1 class="mt-3 gradiTitle">
								<span>공지사항</span>
							</h1>
							<p class="recruit-list mt-6">공지사항을 수정할 수 있습니다.</p>
						</div>
						<div id="write-form">
						
							<div class="form-group mt-8">
								<div class="view-form addqna">

									
										<div class="view-form addqna">
											<div class="addqna-form">
												<span id="write-title">제목</span> <input type="text"
													class="title write-title" placeholder="✏️제목을 입력하세요." name="title" value="${dto.nt_title}">

										</div>
								</div>
							</div>
							<div class="textarea-group">
								<textarea cols="30" rows="10" placeholder="내용을 입력하세요." name="content">${dto.nt_content}</textarea>
							</div>

						</div>

					</div>
				</div>

				<div class="moving-btn">
					<button type="button" class="btn btnBefore" onclick="history.back()">이전으로</button> 
					<button type="button" class="btn btnList" id="btn_edit">수정하기</button>
				</div>
				<input type="hidden" name="seq" value="${dto.nt_seq}" />
				</form>
			</div>
		</div>
	</section>

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	
	$('#btn_edit').click(function () {
		var content = $('textarea[name="content"]').val();
		var title = $('input[name="title"]').val();
		var seq = $('input[name="seq"]').val();
		console.log(seq);
		console.log(content);
		console.log(title);
		
		$.ajax({
			type: 'POST',
			url: '/good/board/notice/editnotice.do',
			data: 'seq=' + seq + '&content=' + content + '&title=' + title,
			dataType: 'json',
			success: function (result ) {
				if(result.result == 1){
					alert('수정 성공');
				} else {
					alert('수정 실패');
				}
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
			
		});
		
	});
	
	
	
	
	</script>


</body>
</html>