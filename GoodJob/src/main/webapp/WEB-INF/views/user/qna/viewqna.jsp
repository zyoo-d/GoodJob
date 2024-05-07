
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<style>

#qna-title{
	margin: 10px 0;
}

.view-count i {
    margin-left: 5px;
    
}
#report-form{
	display: inline;
}

.report-btn {
    margin-left: 10px; /* 신고 버튼과 다른 요소 사이 간격 */
    padding: 5px 10px; /* 버튼 패딩 */
    background-color: rgb(54, 86, 203); /* 버튼 배경색 */
    border: none; /* 버튼 테두리 제거 */
    cursor: pointer; /* 커서 모양 변경 */
    color: #fff;
    border-radius: 5px;
}

</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp" %>

<body>

<!-- ./end floating assets -->

<!-- blog single -->

<section class="page-hero pt-16 pb-6">
  <div class="container">
    <div class="card" id="itvWriteQnA">
     <div class="card-content-wrapper">
					<div class="card-content">
						<div class="px-4 text-center">
            <h1 class="mt-3 gradiTitle"><span onclick="location.href='/good/user/qna/listqna.do';">여기어때</span></h1>
            <p class="mt-6">궁금한 기업에 대한 정보를 물어보세요.</p>
          </div>
    
   
 
            <div class="row mb-8">
              <div class="form-group mt-8 md:col-6 lg:col-4">
				<div class="qna-view-form qnaview">
				<p class="company">${dto.cp_name}</p>
				<div class="title" id="qna-title">
					<h3>${dto.qna_title}</h3>
				</div>
				</div>

              </div>
              <div class="textarea-group">
                <textarea cols="30" rows="10" readonly>${dto.qna_content}</textarea>
              </div>
              
			<div class="writer-info">
					<p class="writer">${dto.id}</p>
					<p class="write-date"><fmt:formatDate value="${dto.qna_regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
					<c:if test="${not empty id && (dto.id == id || lv == '2') }">
					<button type="button" class="" value="수정" onclick="location.href='/good/user/qna/editqna.do?qna_seq=${dto.qna_seq}';">(수정</button>
					<button type="button" class="" value="삭제" onclick="location.href='/good/user/qna/delqna.do?qna_seq=${dto.qna_seq}';">/ 삭제)</button>
					</c:if>
					<p class="view-count"><i class="fa-regular fa-eye"></i> ${dto.qna_views}</p>
					<form action="#" method="POST" id="report-form">
						<input type="hidden" name="member_id" value="${dto.id}"> 
						<input type="hidden" name="post_id" value="${dto.qna_seq}">
						<button type="submit" class="report-btn">신고</button>
					</form>
					
					
				</div>
				 </div>
           <div class="moving-btn">
  <a href="/good/user/qna/listqna.do" class="btn btnList">목록으로</a>
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