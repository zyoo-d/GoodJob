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
				<form method="post" action="/good/user/addstudy.do">
					<div class="card-content-wrapper">
						<div class="card-content">
							<div class="px-4 text-center">
								<h1 class="mt-3 gradiTitle">
									<span>스터디 게시판</span>
								</h1>
								<p class="recruit-list mt-6">혼자보다는 함께! 목표를 같이 달성해나갈 스터디원을
									모아보세요.</p>
							</div>
							<div id="write-form">
								<div class="form-group mt-8">
									<!-- 기업 검색창 -->
									<div class="view-form addqna">
										<div class="addqna-form">
											<span id="write-title">제목</span> <input type="text"
												class="title write-title" placeholder="✏️제목을 입력하세요.">
										</div>
										<div class="addqna-form">
											<div id="searchResults1" class="dropdown-content addqna"></div>
											<span id="write-company">기업</span> <input type="text"
												id="searchInputCompany" class="company"
												placeholder="🔍기업명을 입력하세요.">
										</div>
									</div>
									<!-- 여기까지 복사해서 쓰시면 됩니다 -->
										<div class="view-form addqna addqna-form">
											<span id="write-title">마감일</span> <input type="date"
												class="title write-title" id="dateselect" />
										</div>
								</div>
								<div class="textarea-group">
									<textarea cols="30" rows="10" placeholder="내용을 입력하세요."></textarea>
								</div>
							</div>

						</div>
					</div>

					<div class="moving-btn">
						<a href="#" class="btn btnBefore">이전으로</a> <a href="#"
							class="btn btnList">등록하기</a>
					</div>
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