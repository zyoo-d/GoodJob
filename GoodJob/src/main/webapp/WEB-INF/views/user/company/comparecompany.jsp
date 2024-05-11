<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="/WEB-INF/views/inc/asset.jsp"%>
    <style>
        #cp_info {
            display: inline-block;
            height: 200PX;
        }
        .space-evenly#compare_fnc {
            width: 200px;
            padding: none;
            margin: 20px;
        }
        #cp_fnc {
            padding: 20px 0;
        }
        #cp_fnc > div {
            width: 100%;
            margin-bottom: 20px ;
        }
        
        div > canvas {
        margin-bottom: 30px ;
        
        }
        #goInfo {
            margin-top: 0;
        }
        .space-evenly {
            justify-content: space-evenly;
        }
        .matchplus {
            min-height: 200px;
            align-content: center;
        }

        .contents_item_row {
            display: flex;
            justify-content: flex-start;
        }
        #matchscore {
            margin-left: 35px;
            line-height: 2rem;
        }
        .contents_item_label {
            width: 68%;
        }
        #matchtotal {
            justify-content: center;
            gap: 26%;
            margin-bottom: 5px;
        }
        #salary_info > span{
            font-size: 1.1rem;
            color: #4e4e4e;
        }
        #salary_info {
            font-size: 1.4rem;
            color: black;
            border-bottom: 1px solid black;
        }
		#fnc_chart {
		margin-bottom: 10px; 
		
		}
    </style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
<section class="section pt-0">
    <div class="container mt-16">
        <div class="card px-10">
            <div class="px-4 text-center">
                <h1 class="mt-3 gradiTitle">
                    <span>기업 비교</span>
                </h1>
                <p class="mt-6">비교하고 싶은 기업을 지정해보세요.</p>
                <div class="moving-btn">
                    <a href="#" class="btn btnList">기업 다시 비교하기</a>
                </div>
            </div>
            <div></div>
            <div class="my-5 border-b border-border">
                <div class="row mt-10 integration-tab-items cpcComCard">
                    <c:forEach items="${list}" var="dto">
                        <div class="mb-8 md:col-6 lg:col-4 integration-tab-item" data-groups='["social"]'>
                            <div class="rounded-xl bg-white px-10 pt-11 pb-8 shadow-lg cpComInfo">
                                <div class="integration-card-head flex items-center space-x-4 cpCimg">
                                    <img src="${dto.image}" alt="" />
                                    <div>
                                        <h4 class="h4">${dto.cp_name}</h4>
                                        <span class="font-medium cpComFont">${dto.idst_name }</span>
                                    </div>
                                </div>
                                <div class="my-5 border-y border-border py-5 smallradarChart" id="cp_info">
                                    <span>대표 : ${dto.ceo}</span><br />
                                    <span>평균 근속년수 : ${dto.hire_avr_year}</span><br />
                                    <span>사원 수 : ${dto.hire_member }</span><br />
                                    <span>설립일 : ${dto.founded == null ? '정보 없음' : dto.founded}</span><br />
                                    <span>주소 : ${dto.cp_address}</span><br />
                                </div>
                                <div class="job_meta">
                                    <c:forEach var="tag" items="${dto.tag_list}">
                                        <span class="job-keyword">${tag}</span>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <h4>IT's Good Job 기업 점수</h4>
                    <div class="cpcCpChart">
                        <canvas id="comparisonBarChart"></canvas>
                    </div>
                </div>
            </div>
            <h4>재무정보 비교</h4>
            <div class="flex">
                <div class="cpsCompanyInfo">
                    <div class="row mt-8 integration-tab-items rounded-xl bg-white shadow-lg ml-4 mr-4" id="fnc_chart">
                        <c:forEach var="dto" items="${list}" varStatus="status">
                            <div class="my-8 md:col-6 lg:col-4 integration-tab-item px-6" data-groups='["social"]'>
                                <div class="pb-8 min-h-400">
                                    <div class="pb-5 integration-card-head flex items-center space-x-4 min-h-[105px]">
                                        <img src="${dto.cp_name}" alt="" />
                                        <div>
                                            <h5 class="h5">${dto.cp_name}</h5>
                                            <span class="font-medium">${dto.idst_name}</span>
                                        </div>
                                    </div>
<div id="cp_fnc">
                            <div id="salary_info">
                                평균연봉<br>
                                ${dto.hire_avr_salary} <span>만원</span>
                            </div><br>
                           <div id="cp_fnc">
                            <div>
                                <h6>매출액</h6>
                                <canvas id="salesChart_${status.index}" width="384" height="200"></canvas>
                            </div>
                            <div>
                                <h6>영업이익</h6>
                                <canvas id="ebitChart_${status.index}" width="384" height="200"></canvas>
                            </div>
                            <div>
                                <h6>당기순이익</h6>
                                <canvas id="incomeChart_${status.index}" width="384" height="200"></canvas>
                            </div>
                        </div>
                        </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <h4>리뷰 점수 비교</h4>
            <div class="cpsCompanyInfo">
                <div class="row mt-8 integration-tab-items rounded-xl bg-white shadow-lg ml-4 mr-4" id="top3card">
                    <c:forEach var="rv" items="${rvlist}" varStatus="status">
                        <div class="my-8 md:col-6 lg:col-4 integration-tab-item px-6" data-groups='["social"]'>
                            <div class="pb-8 min-h-400">
                                <div class="pb-5 integration-card-head flex items-center space-x-4 min-h-[105px]">
                                    <div>
                                        <h5 class="h5"> ${rv.cp_name }</h5>
                                    </div>
                                </div>
                                <div class="mb-5 pb-5">
                                    <div class="flex" id="matchtotal">
                                        <h4>리뷰 평점</h4><h4><i class="fa-solid fa-star cornflowerblue"></i> ${rv.avg_score}</h4>
                                    </div>
                                    <div id="matchscore" class="fnc_chart">
                                        <div class="contents_item_row">
                                            <div class="contents_item_label">연봉</div>
                                            <div class="avg_wrap">
                                                <span class="avg_icon color_or_400"><i class="fa-solid fa-star gold"></i></span> <span class="avg_text"> ${rv.salary_score}</span>
                                            </div>
                                        </div>
                                        <div class="contents_item_row">
                                            <div class="contents_item_label">조직문화</div>
                                            <div class="avg_wrap">
                                                <span class="avg_icon color_or_400"><i class="fa-solid fa-star gold"></i></span> <span class="avg_text"> ${rv.culture_score}</span>
                                            </div>
                                        </div>
                                        <div class="contents_item_row">
                                            <div class="contents_item_label">복지</div>
                                            <div class="avg_wrap">
                                                <span class="avg_icon color_or_400"><i class="fa-solid fa-star gold"></i></span> <span class="avg_text"> ${rv.welfare_score}</span>
                                            </div>
                                        </div>
                                        <div class="contents_item_row">
                                            <div class="contents_item_label">성장 가능성</div>
                                            <div class="avg_wrap">
                                                <span class="avg_icon color_or_400"><i class="fa-solid fa-star gold"></i></span> <span class="avg_text"> ${rv.growth_score}</span>
                                            </div>
                                        </div>
                                        <div class="contents_item_row">
                                            <div class="contents_item_label">조직 안정성</div>
                                            <div class="avg_wrap">
                                                <span class="avg_icon color_or_400"><i class="fa-solid fa-star gold"></i></span> <span class="avg_text"> ${rv.stability_score}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/views/inc/footer.jsp"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.4.1/chart.min.js"></script>
<script>
    // 기업 점수
    var backgroundcolorList = ['rgba(255, 99, 132)', 'rgba(54, 162, 235)', 'rgba(255, 206, 86)'];
    var borderColorList = ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)'];
    var datasets = [];
    <c:forEach items="${list}" var="dto" varStatus="status">
        datasets.push({
            label: '${dto.cp_name}',
            backgroundColor: backgroundcolorList[${status.index}],
            borderColor: borderColorList[${status.index}],
            borderWidth: 1,
            data: [${dto.salary}, ${dto.culture}, ${dto.potential}, ${dto.stability}, ${dto.welfare}]
        });
    </c:forEach>
    var ctx = document.getElementById('comparisonBarChart').getContext('2d');
    var comparisonBarChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['연봉', '조직문화', '성장가능성', '근속률', '복지'],
            datasets: datasets
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
    
    
    var years = [2021, 2022, 2023];

    <c:forEach items="${list}" var="company" varStatus="status">
    var salesData_${status.index} = [
        ${flist[0][status.index]}, 
        ${flist[0][status.index + 1]}, 
        ${flist[0][status.index + 2]}
    ];
    var ebitData_${status.index} = [
        ${flist[1][status.index]}, 
        ${flist[1][status.index + 1]}, 
        ${flist[1][status.index + 2]}
    ];
    var incomeData_${status.index} = [
        ${flist[2][status.index]}, 
        ${flist[2][status.index + 1]}, 
        ${flist[2][status.index + 2]}
    ];


    var salesCtx_${status.index} = document.getElementById('salesChart_${status.index}').getContext('2d');
    var salesChart_${status.index} = new Chart(salesCtx_${status.index}, {
        type: 'bar',
        data: {
            labels: years,
            datasets: [{
                label: '매출액',
                data: salesData_${status.index},
                backgroundColor: 'rgba(75, 192, 192, 0.6)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    var ebitCtx_${status.index} = document.getElementById('ebitChart_${status.index}').getContext('2d');
    var ebitChart_${status.index} = new Chart(ebitCtx_${status.index}, {
        type: 'bar',
        data: {
            labels: years,
            datasets: [{
                label: '영업이익',
                data: ebitData_${status.index},
                backgroundColor: 'rgba(255, 99, 132, 0.6)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    var incomeCtx_${status.index} = document.getElementById('incomeChart_${status.index}').getContext('2d');
    var incomeChart_${status.index} = new Chart(incomeCtx_${status.index}, {
        type: 'line',
        data: {
            labels: years,
            datasets: [{
                label: '당기순이익',
                data: incomeData_${status.index},
                backgroundColor: 'rgba(54, 162, 235, 0.6)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</c:forEach>
</script>

</body>
</html>
