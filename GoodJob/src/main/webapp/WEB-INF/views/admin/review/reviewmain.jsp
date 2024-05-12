<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/WEB-INF/views/inc/adminasset.jsp"%>
</head>

<body>
  <div id="app">
    <div class="main-wrapper main-wrapper-1">
      <%@include file="/WEB-INF/views/inc/adminheader.jsp"%>
      <!-- Main Content -->
      <div class="main-content">
        <section class="section">
          <div class="section-header">
            <h1>리뷰 관리</h1>
          </div>

          <div class="section-body">


            
            <div class="row">
              
              <div class="col-lg-8">
                <div class="card">
                  <div class="card-header">
                    <h4>월별 리뷰 통계</h4>
                  </div>
                  <div class="card-body">
                    <canvas id="myChart2" height="180"></canvas>
                  </div>
                </div>
              </div>

              <div class="col-lg-4">
                <div class="card">
                  <div class="card-header">
                    <h4>리뷰 개수 TOP5</h4>
                  </div>
                  <div class="card-body">
                    <canvas id="myChart4"></canvas>
                  </div>
                </div>
                <div class="card">
                  <div class="card-header">
                    <h4>리뷰 평점 TOP5</h4>
                  </div>
                  <div class="card-body">
                    <canvas id="myChart3"></canvas>
                  </div>
                </div>
              </div>


            </div>
            
          </div>
        </section>
      </div>
      <%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>
    </div>
  </div>

  <!-- Page Specific JS File -->
  <script src="/good/assets/js/page/components-table.js"></script>
  <script src="/good/assets/js/page/reviewmain.js"></script>
  
  
</body>
</html>