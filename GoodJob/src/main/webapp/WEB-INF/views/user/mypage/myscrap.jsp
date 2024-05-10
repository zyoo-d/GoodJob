<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<style>
</style>
<%@include file="/WEB-INF/views/inc/adminasset.jsp"%>
<head>
<%@include file="/WEB-INF/views/inc/mypageheader.jsp"%>
</head>
<body>
	<!-- Main Content -->
	<div class="main-content">
		<section class="section">
			<div class="section-header">
				<h1>스크랩</h1>
				<div class="section-header-breadcrumb">
					<div class="breadcrumb-item active">
						<a href="#">스크랩</a>
					</div>
					<div class="breadcrumb-item">내 스크랩 보기</div>
				</div>
			</div>
				<div class="section-body">
				<h2 class="section-title">내 스크랩 보기</h2>
				<div class="row" id="scrapCards">
				        <c:forEach var="scrap" items="${ScrapList}">
                        <div class="col-6 col-md-4 col-lg-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="logo">
                                        <img class="" src="${scrap.image}" alt="" />
                                        <i class="fas fa-bookmark scrap"></i>
                                    </div>
                                    <div class="infoCard">
                                  		<p class="mt-0">${scrap.cp_name}</p><br>
                                        <p class="mt-0">${scrap.idst_name}</p>
                                        <span class="hiring">채용중</span>
                                    </div>
                                </div>
                            </div>
                        	<input type="hidden" class="cp_seq" value="${scrap.cp_seq}">
                        </div>
                    </c:forEach>
				</div>
			</div>
		</section>
	</div>
	<%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>
	<script>
		$('.fas.fa-bookmark.scrap').click(function() {
			$(this).parents().eq(3).remove();
			//데이터 처리도 해주기
		});
		$('.fas.fa-bookmark.scrap').click(function() {
		    var cpSeq = $(this).closest('.col-6').find('.cp_seq').val();
		    console.log(cpSeq);
		    
		    // AJAX 요청 
		    $.ajax({
		        type: 'POST',
		        url: '/good/user/mypage/myscrap.do', // 서블릿 URL
		        data: { cp_seq: cpSeq },
		        success: function(response) {
		            // 서버로부터 성공 응답 받았을 때 처리
		            if (response === "success") {
		                alert('스크랩 삭제에 성공했습니다.');
		            } else {
		                alert('스크랩 삭제에 실패했습니다.');
		            }
		        },
		        error: function(xhr, status, error) {
		            // 서버 요청 실패 시 처리 
		            alert('서버 요청 실패: ' + error);
		        }
		    });
		});
	</script>
</body>
</html>