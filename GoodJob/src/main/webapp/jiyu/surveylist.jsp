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
<div id="itvWriteContainer">
    <section class="page-hero pt-16 pb-6">
        <div class="container">
            <div class="card" id="itvWriteQnA">
                <div class="card-content-wrapper">
                    <div class="survey-line px-4 text-center sizing">
                        <h1 class="mt-3 gradiTitle"><span>Survey for GoodJob!</span></h1>
                         <p class="survey-list mt-6">설문조사 결과를 토대로 000님의 맞춤 기업을 추천해드립니다.<br>
                         성실하게 설문조사에 응하실 경우 보다 정확한 정보가 제공됩니다.</p>
 
                    </div>
    <form class="survey-form">
    <!-- 질문시작 -->
        <div class="survey section">
            <h2>Q<span class="survey-num">1 </span>맞춤기업 확인을 위한 질문입니다.</h2>
                   <div class="survey radio-toolbar">
            <input type="radio" id="opinion1" name="opinion" value="1">
            <label for="opinion1">매우아니다</label>
            <input type="radio" id="opinion2" name="opinion" value="2">
            <label for="opinion2">아니다</label>
            <input type="radio" id="opinion3" name="opinion" value="3">
            <label for="opinion3">보통이다</label>
            <input type="radio" id="opinion4" name="opinion" value="4">
            <label for="opinion4">그렇다</label>
            <input type="radio" id="opinion5" name="opinion" value="5">
            <label for="opinion5">매우그렇다</label>
        </div>
    </div>
    <!-- 질문끝 -->
    
    </form>
        <button type="submit" class="btn btn-primary btn-survey-check">다음</button>
    </div>
    </div>
    </div>
    </section>
    </div>
    	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
    <script>
    </script>
</body>
</html>