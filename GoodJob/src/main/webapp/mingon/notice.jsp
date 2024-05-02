
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<style>
.sizing {
    padding: 50px;
}
.gradiTitle span {
    background: linear-gradient(184.78deg, #535AED 7.64%, #004F8B 120.07%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
}
.add-qna-write {
    display: inline-block;
}
textarea {
    width: 90%;
    resize: none;
    font-size: 18px;
}
textarea:focus {
    outline: none;
    border: none;
    box-shadow: none;
}
.card-content-wrapper {
    width: 100%;
    display: flex;
    flex-direction: column;
}
.card-content {
    margin-top: 20px;
    padding: 40px;
    border: 1px solid #3741C4;
    border-radius: 10px;
    background-color: #FEFEFE;
}
.form-group {
    margin-bottom: 20px;
}
.textarea-group {
    margin-bottom: 0;
}
.textarea-group textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    resize: none;
}
.moving-btn {
    text-align: center;
}
.moving-btn .btn {
    border-radius: 5px;
    width: 100px;
    height: 40px;
}
.moving-btn a {
    padding: 5px 10px;
    border: none;
    border-radius: 5px;
    background-color: #6777EE;
    color: #fff;
    cursor: pointer;
    transition: background-color 0.3s;
    line-height: 30px; 
}
.moving-btn a:hover {
    background-color: #5566cc;
}
.view-form p {
    margin: 0;
}
.writer-info p {
    display: inline-block;
}
.view-form .company {
    color: #525DED;
    font-size: 1.3rem;
}
.writer-info {
    margin-top: 0;
    display: inline-block;
    text-align: right;
    font-size: 0.9rem;
}
.writer-info .writer {
    font-size: 1.1rem;
    color: #595959;
}
.writer-info button {
    font-size: 0.8rem;
}
.view-form .title {
    margin-top: 5px;
}
#comment {
    border-top: 1px solid #ccc;
}
.comment-list {
    margin-top: 30px;
    border-top: 1px solid #ccc;
    padding: 20px;
}
.comment-list table {
    width: 100%;
}
.comment-list table th,
.comment-list table td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
}
.comment-list table th {
    background-color: #F9F9F9;
    width: 80%;
    font-weight: bold;
}
.comment-list table td {
    width: 10%;
    text-align: center;
}
.add-comment {
    display: flex;
    align-items: center;
    justify-content: center;
    background: #FFFFFF;
    padding: 10px 20px;
    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    border-radius: 25px;
    width: 70%;
    margin: 20px auto;
}
.add-comment input[type="text"] {
    border: none;
    width: 600px;
    font-size: 18px;
}
.add-comment .btnAddComment {
    border-radius: 15px;
    background-color: #525DED;
    color: #fff;
    transition: background-color 0.3s;
    width: 60px;
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
}
.add-comment .btnAddComment:hover {
    background-color: #3741C4;
}
.add-comment input[type="text"]:focus {
    outline: none;
    border: none;
    box-shadow: none;
}
#comment th:nth-child(1), #comment td:nth-child(1) {
    width: 60px;
    text-align: center;
}
#comment th:nth-child(2), #comment td:nth-child(2) {
    width: auto;
    text-align: left;
}
#comment th:nth-child(3), #comment td:nth-child(3) {
    width: 160px;
    text-align: left;
}
#comment .commentContent {
    font-size: 16px;
    text-align: right;
}
.comment-edit {
    display: flex;
    justify-content: space-between;
}
.comment-edit div {
    display: flex;
    justify-content: unset;
}
.comment-edit span, .writer-info .comment-icon span:hover {
    cursor: pointer;
}
.comment-edit span:hover, .writer-info .comment-icon span:hover {
    color: #515CEB;
}
.commentInfo span {
    cursor: pointer;
}
.commentInfo span:hover {
    color: 4C57E4;
}
.comment-paging {
    margin-top: 20px;
    text-align: center;
    font-size: 14px;
}
.more-comment {
    border-radius: 15px;
    background-color: #525DED;
    color: #fff;
    transition: background-color 0.3s;
    text-align: center;
}
.more-comment:hover {
    background-color: #3741C4;
}
.write-date {
    font-size: 12px;
}

</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp" %>

<body>

<!-- ./end floating assets -->

<!-- blog single -->
<div id="itvWriteContainer">

<section class="page-hero pt-16 pb-6">
  <div class="container">
    <div class="card" id="itvWriteQnA">
      <div class="card-content-wrapper">
 		<div class="px-4 text-center sizing">
            <h1 class="mt-3 gradiTitle"><span>공지사항</span></h1>
          </div>
        <div class="card-content">

 
            <div class="row mb-8">
              <div class="form-group mt-8 md:col-6 lg:col-4">
				<div class="view-form">
				<p class="company">공지사항</p>
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
           <div class="moving-btn">
  <a href="#" class="btn btnBefore">이전글</a>
  <a href="#" class="btn btnList">목록으로</a>
  <a href="#" class="btn btnNext">다음글</a>
</div>
 </div>
            			
            </div>
                       

            <div class="comment-list">
              <h4>댓글</h4>

              <div class="">
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
    </div>
</div>
</section>

</div>

<%@include file="/WEB-INF/views/inc/footer.jsp" %>
<script>

</script>
</body>
</html>