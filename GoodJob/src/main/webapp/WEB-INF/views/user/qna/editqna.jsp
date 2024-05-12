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
								<span>여기어때</span>
							</h1>
							<p class="recruit-list mt-6">기업에 대해 궁금한 점을 자유롭게 물어보세요.</p>
						</div>


						<div id="write-form">
						
							<div class="form-group mt-8">
								<div class="view-form addqna">

									
										<div class="view-form addqna">
											<div class="addqna-form">
												<span id="write-title">제목</span> <input type="text"
													class="title write-title" placeholder="✏️제목을 입력하세요." name="qna_title" value="${dto.qna_title }">
											</div>
											<div class="addqna-form">
												<div id="searchResults1" class="dropdown-content addqna"></div>
												<span id="write-company">기업</span> <input type="text"
													id="searchInputCompany" class="company"
													placeholder="🔍기업명을 입력하세요." name="cp_name" value="${dto.cp_name }">
											</div>
										</div>
								</div>
							</div>
							<div class="textarea-group">
								<textarea cols="30" rows="10" placeholder="내용을 입력하세요." name="qna_content">${dto.qna_content}</textarea>
							</div>

						</div>

					</div>
				</div>

				<div class="moving-btn">
					<button type="button" class="btn btnBefore" onclick="history.back()">이전으로</button> 
					<button type="submit" class="btn btnList">수정하기</button>
				</div>
				<input type="hidden" name="qna_seq" value="${dto.qna_seq}" />
				</form>
			</div>
		</div>
	</section>

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	let today = new Date();
	let timezoneOffset = today.getTimezoneOffset() * 60000;
	today = new Date(today.getTime() - timezoneOffset);
	let dateStr = today.toISOString().slice(0, 10);
	$('#dateselect').attr("min", dateStr);

	$(document).ready(function(){
		$('#searchInputCompany').keyup(function(){
			var input = $(this).val();
			console.log(input.length);
			
			if(input.length==0){
				$('#searchResults1').html("");
			} else {
			$.ajax({ 
				type: 'get',
				url : '/good/user/searchcompany.do',
				data : {query : input},
				success : function(result){ 
					$('#searchResults1').html("");
					result.forEach(dto =>{
						$('#searchResults1').append(`<div class="dropdown-item">\${dto.cp_name}</div>`);
					});
				},
				error : function(){
					$('#searchResults1').append('<div class="dropdown-item">오류</div>');
				}
			});
			}
		});
		
		$('#searchResults1').on('click', '.dropdown-item', function() {
			$('#searchInputCompany').val($(this).text());
			$('#searchResults1').html("");
		});	
	})
	</script>


</body>
</html>