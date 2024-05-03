
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<style>


</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp" %>

<body>

<!-- ./end floating assets -->

<!-- blog single -->

<section class="page-hero pt-16 pb-6">
  <div class="container">
    <div class="z-custom card" id="itvWriteQnA">
     <div class="card-content-wrapper">
					<div class="card-content">
						<div class="px-4 text-center">
            <h1 class="mt-3 gradiTitle"><span>여기어때</span></h1>
            <p class="mt-6">궁금한 기업에 대한 정보를 물어보세요.</p>
          </div>
    
   
 
            <div class="row mb-8">
              <div class="form-group mt-8 md:col-6 lg:col-4">
				<div class="qna-view-form qnaview">
				<p class="company">네이버</p>
				<div class="title"><h2>제목입니다.</h2></div>
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
  <a href="#" class="btn btnBefore">이전글</a>
  <a href="#" class="btn btnList">목록으로</a>
  <a href="#" class="btn btnNext">다음글</a>
</div>
 </div>
            			
            </div>
                     

            <div class="comment-list">



              <form>
              <div class="add-comment">    
	              
	              <span><input type="text" placeholder="댓글을 입력하세요"></span>
	              <button type="button" class="btn btnAddComment"">
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
								<span class="material-symbols-outlined">delete</span>
								<span class="material-symbols-outlined">edit_note</span>
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



<%@include file="/WEB-INF/views/inc/footer.jsp" %>
<script>

</script>
</body>
</html>