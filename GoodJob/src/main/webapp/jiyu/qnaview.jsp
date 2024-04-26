<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<style>

</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp" %>
<body>

<!-- ./end floating assets -->

<!-- blog single -->
<section class="page-hero pt-16 pb-6">
  <div class="container">

        <form class="comment-form" action="#" method="POST">

        <div class="row mb-8">

            <div class="form-group mt-8 md:col-6 lg:col-4">
 
       <table>
            	<tr>
            		<th>제목</th>
            		<td>제목입니다.</td>
            	</tr>
            	<tr>
            		<th>기업명</th>
            		<td>기업입니다.</td>
            		</tr>
            		<tr>
            		<th>작성자</th>
            		<td>작성자</td>
            		</tr>
            		<tr>
            		<th>작성일자</th>
            		<td>작성일자</td>
            	</tr>
            </table>
 
            </div>
            <textarea cols="30" rows="10">내용입니다.내용입니다.내용입니다.</textarea>
          
          </div>

          <div class="form-group">
          </div>
         
          <div style="text-align:center">
          <input type="Submit" class="btn btn-primary mt-8 min-w-[189px]" value="이전글">
          <input type="Submit" class="btn btn-primary mt-8 min-w-[189px]" value="목록으로">
          <input type="Submit" class="btn btn-primary mt-8 min-w-[189px]" value="다음글">
          </div>
          
          <hr>
          <div>
          <h4>댓글</h4>
          <hr>
         	<div>
         	<span><input type="text"></span>
         	<span><input type="submit" class="btn btn-primary  mt-4" value="등록하기"></span>
         	</div>
         	<table>
         		<tr>
					<th>댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.</th>
					<td><input type="submit" class="btn btn-primary  mt-4" value="수정"></td>
					<td><input type="submit" class="btn btn-primary  mt-4" value="삭제"></td>
         		</tr>
         	
         	</table>
          
          
          
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