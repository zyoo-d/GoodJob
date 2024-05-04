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
				<div class="card-content-wrapper">
					<div class="card-content">
						<div class="px-4 text-center">
							<h1 class="mt-3 gradiTitle">
								<span>스터디 게시판</span>
							</h1>
							<p class="recruit-list mt-6">스터디 함께 하고, 함께 합격해요!</p>
						</div>
						<div id="write-form">
							<div class="form-group mt-8">
								<div class="view-form addqna">
									<div class="addqna-form">
										<span id="write-title">제목</span> <input type="text"
											class="title write-title" placeholder="✏️제목을 입력하세요.">
									</div>
									<div class="addqna-form">
										<span id="write-company">기업</span> <input type="text"
											id="searchInputCompany" class="company"
											placeholder="🔍기업명을 입력하세요.">
										<div id="searchResults1" class="dropdown-content addqna"></div>
									</div>
								</div>
								<div class="studyDate">
									<p>
										🗓️스터디 모임 마감 기간 <input type="date" class="company" />
									</p>

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
			</div>
		</div>
	</section>

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	
	$(document).ready(function(){
		$('#searchInputCompany').keyup(function(){
			var input = $(this).val();
			console.log(input.length);
			if(input.length>1){
				
			$.ajax({ 
				type: 'get',
				url : '/good/user/searchcompany.do',
				data : {query : input},
				dataType: 'json',
				success : function(result){ 
					$('#searchResults1').html("");
				
					result.forEach(dto =>{
						$('#searchResults1').append('<div class="dropdown-item">' + ${dto.cp_name} + '</div>');
					});
				},
				error : function(){
					$('#searchResults1').append('<div class="dropdown-item">잘못된 입력입니다.</div>');
				}
			});
			} else {
				$('#searchResults1').html("");
			}
		});

		$('.dropdown-item').click(function(){
			$('#searchInputCompany').val($(this).text());
			$('#searchResults1').html("");
		});
	})
	</script>
</body>
</html>