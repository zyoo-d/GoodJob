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
<section class="section blog-single">
  <div class="container">

        <form class="comment-form" action="#" method="POST">
        <div class="row mb-8">
            <div class="form-group mt-8 md:col-6 lg:col-4">
            <table>
            	<tr>
            		<th>제목</th>
            		<td><input type="text"></td>
            	</tr>
            	<tr>
            		<th>기업</th>
            		<td><input type="text"></td>
            	</tr>
            	<tr>
            		<th>작성자</th>
            		<td><input type=""></td>
            	</tr>
            	<tr>
            		<th>작성일자</th>
            		<td><input type=""></td>
            	</tr>
            </table>
            </div>
          
          </div>
          <p class="mb-4">내용</p>
          <div class="form-group">
            <textarea cols="30" rows="10"></textarea>
          </div>
          
          <div class="form-group relative flex pl-6">

          </div>
          <input type="Submit" class="btn btn-primary mt-8 min-w-[189px] cursor-pointer" value="등록하기">
          <input type="Submit" class="btn btn-primary mt-8 min-w-[189px] cursor-pointer" value="이전으로">
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