<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>

<style>

.card {
	height: auto;
	min-height: 100vh;
	overflow-y: visible;
	margin: 0 auto;
}

.list_info {
	display: flex;
	justify-content: flex-end;
	align-items: center;
}

.box_item {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px 20px;
	border-bottom: 1px solid #ccc;
	margin-bottom: 10px;
}

.list-index {
	flex: 0.1;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 40px;
	background-color: #6777EE;
	color: #FFF;
	border-radius: 50%; /* Use 50% for a perfect circle */
	font-size: 0.9rem;
	line-height: 40px; /* Match the circle's height */
	margin-right: 10px;
	text-align: center;
}

.com-title {
	flex: 6;
}

.com-info {
	flex: 0.9;
	border-left: 2px solid #ccc;
	padding-left: 20px;
}

.support_detail {
	text-align: right;
	display: flex;
	flex-wrap: wrap;
	justify-content: flex-end;
}

.date {
	font-size: 15px;
	margin-right: 8px;
}

.recruit-present-info {
	display: flex;
	justify-content: space-between;
	width: 100%;
	align-items: center;
}

.job_sector {
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 5px;
}

.com-name {
	font-size: 1.1rem;
	color: #707070;
}

.job_tit {
	font-size: 1.5rem;
	color: #333;
}

.PageBox {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100%;
	margin: 0 auto;
}

#list-cnt {
	color: #6777EE;
}

.list-header {
	margin: 15px 0;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.InpBox {
	margin-right: 5px;
	border: 1px solid #BFBFBF;
	border-radius: 6px;
	overflow: hidden;
	background-color: white;
	position: relative;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	width: auto;
}

.InpBox select {
	width: 100%;
	max-width: 80px;
	min-width: 80px;
	padding: 8px 16px;
	border: none;
	background: transparent;
	appearance: none;
	color: #707070;
	font-size: 14px;
}

.InpBox::after {
	content: '\25BC';
	position: absolute;
	top: 0;
	right: 10px;
	height: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
	color: #BFBFBF;
	font-size: 10px;
}

.InpBox:hover {
	border-color: #6777EE;
	background-color: #6777EE;
	cursor: pointer;
}

.InpBox:hover select {
	color: #FFF;
}

.InpBox select option {
	color: #707070;
}

.list {
	text-align: left;
	width: 500px;
}

#searchForm {
	display: flex;
	align-items: center;
	justify-content: center;
	background: #FFFFFF;
	padding: 10px 20px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	border-radius: 25px;
	width: 50%;
	margin: 20px auto;
}

#searchForm .input {
	flex-grow: 1;
	border: none;
	padding: 8px 15px;
	font-size: 14px;
	outline: none;
}

#searchForm button.search {
	background: #6777EE;
	border: none;
	color: white;
	padding: 8px 20px;
	border-radius: 0 15px 15px 0;
	cursor: pointer;
	font-size: 14px;
}

#searchForm button.search:hover {
	background-color: #5a67d8;
}

#searchForm .input:focus {
	outline: none;
	border: none;
	box-shadow: none;
}

.list-title {
	margin-left: 10px;
}

.list-title span, .list-title p {
	font-size: 1.5rem;
	display: inline;
	color: #333;
}

.gradiTitle span {
	background: linear-gradient(184.78deg, #535AED 7.64%, #004F8B 120.07%);
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
}

.mt-6 {
	margin-top: 1.5rem;
}

.PageBox {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100%;
	margin-top: 3rem;
	margin: 3rem auto 0;
	text-align: center;
}

.pagination {
	display: flex;
	list-style: none;
	padding: 0;
}

.pagination li {
	margin: 0;
	display: flex;
	align-items: center;
}

.pagination li a {
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 36px;
	padding: 8px 12px;
	color: #000;
	text-decoration: none;
	border: 1px solid #ddd;
	background-color: #fff;
	transition: background-color 0.3s, color 0.3s;
}

.paging-icon {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 4px;
	margin-top: 3px;
	min-height: 23px;
	font-size: 18px;
	color: #6777EE;
}

.pagination li.active a, .pagination li a:hover, .pagination li.active a span,
	.pagination li a:hover span {
	background-color: #6777EE;
	color: #FFF;
	border-color: #6777EE;
}

.pagination li:first-child a {
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
}

.pagination li:last-child a {
	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
}

.cpCimg img {
  max-width: 100px; /* 최대 너비 설정 */
  max-height: 100px; /* 최대 높이 설정 */
}



.cpcCpChart {
  width: 800px; 
  height: 400px; 
  margin: auto;
  margin-top: 40px;
  margin-bottom: 50px;
}

.cpCMember h3{
	  margin-top: 40px;
  margin-bottom: 30px;
}

.cpCMember p{

  margin-bottom: 60px;
}
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>


	<div class="container">
		<div class="card comCard">
		<div class="px-4 text-center">
		<h1 class="mt-3 gradiTitle">
			<span>기업 비교</span>
		</h1>
		<p class="mt-6">비교하고 싶은 기업을 지정해보세요.</p>
	</div>

	<div>
		<form id="searchForm" method="GET" action="/toy/board/list.do">
			<div class="InpBox">
				<select class="sorting">
					<option value="subject">기업</option>
					<option value="subject">제목</option>
					<option value="content">내용</option>
				</select>
			</div>
			<input type="text" name="input" class="input" id="search-input"
				placeholder="검색어를 입력하세요">
			<button type="button" class="search" id="search-btn">
				<span class="material-symbols-outlined"> <span
					class="material-symbols-outlined">search</span>
				</span>
			</button>
		</form>
	</div>
		
			<div class="comContainer">

				<div class="comContent">
					<div class="comCp">
						<div class="row mt-10 integration-tab-items cpcComCard">
							<div class="mb-8 md:col-6 lg:col-4 integration-tab-item"
								data-groups='["social"]'>
								<div
									class="rounded-xl bg-white px-10 pt-11 pb-8 shadow-lg cpComInfo">
									<div class="integration-card-head flex items-center space-x-4 cpCimg">
										<img
											src="https://blog.kakaocdn.net/dn/UGS0q/btree5Viurw/l07AH1VgWJHm4stsAHLdL0/img.png"
											alt="" />
										<div>
											<h4 class="h4">Facebook</h4>
											<span class="font-medium cpComFont">Social media</span>
										</div>
									</div>
									<div class="my-5 border-y border-border py-5 smallradarChart">
										회사 정보</div>
									<span class="changelogs-tag bg-[#00CE92]">네이버</span> <span
										class="changelogs-tag bg-[#3A9CFF]">자바</span> <span
										class="changelogs-tag bg-[#973CFF]">코딩테스트</span>
								</div>
							</div>
							<div class="mb-8 md:col-6 lg:col-4 integration-tab-item"
								data-groups='["social"]'>
								<div
									class="rounded-xl bg-white px-10 pt-11 pb-8 shadow-lg cpComInfo">
									<div class="integration-card-head flex items-center space-x-4 cpCimg">
										<img
											src="https://cdn.imweb.me/thumbnail/20220403/a8e484f2dfe39.png"
											alt="" />
										<div>
											<h4 class="h4">카카오</h4>
											<span class="font-medium cpComFont">Social media</span>
										</div>
									</div>
									<div class="my-5 border-y border-border py-5 smallradarChart">
										회사정보</div>
									<span class="changelogs-tag bg-[#00CE92]">카카오</span> <span
										class="changelogs-tag bg-[#3A9CFF]">조직 문화 좋음</span>
								</div>
							</div>
							<div class="mb-8 md:col-6 lg:col-4 integration-tab-item"
								data-groups='["social"]'>
								<div
									class="rounded-xl bg-white px-10 pt-11 pb-8 shadow-lg cpComInfo">
									<div class="integration-card-head flex items-center space-x-4 cpCimg">
										<img
											src="https://mblogthumb-phinf.pstatic.net/20160902_124/ppanppane_14728003076370owh6_PNG/%C4%ED%C6%CE%B7%CE%B0%ED_%281%29.png?type=w800"
											alt=""/>
										<div>
											<h4 class="h4">쿠팡</h4>
											<span class="font-medium">Social media</span>
										</div>
									</div>
									<div class="my-5 border-y border-border py-5 smallradarChart">
										회사정보</div>
									<span class="changelogs-tag bg-[#00CE92]">성장 가능성 높음</span> <span
										class="changelogs-tag bg-[#3A9CFF]">쿠팡</span>

								</div>
							</div>


						</div>

					</div>
					<div class="cpcCpChart">
						<canvas id="comparisonBarChart"></canvas>
						</div>
					<div class="cpCMember">
						<h3>홍길동님의 성향</h3>
						<p>결국 일도 '사람'이 모여서 하는 것! 다른 조건보다 함께 일하는 동료들을 우선하는 당신. 성격이 나쁘거나
							일 못하는 동료 때문에 스트레스 받는 걸 가장 싫어합니다. 취직 또는 이직을 고민하고 있다면, 그 회사에서 일하고
							있는 직장인과 커피챗을 해보는 등 조직문화와 관련한 정보를 수집해보는 게 좋겠어요.</p>

					</div>

					<div class="flex">
						<div class="cpcComMemberChart">
							<canvas id="ComradarChart"></canvas>
						</div>
						<div class="ml-6">
							<h4 class="text-lg font-bold mb-2">추천 근거</h4>
							<p>결국 일도 '사람'이 모여서 하는 것! 다른 조건보다 함께 일하는 동료들을 우선하는 당신. 성격이 나쁘거나
							일 못하는 동료 때문에 스트레스 받는 걸 가장 싫어합니다. 취직 또는 이직을 고민하고 있다면, 그 회사에서 일하고
							있는 직장인과 커피챗을 해보는 등 조직문화와 관련한 정보를 수집해보는 게 좋겠어요.결국 일도 '사람'이 모여서 하는 것! 다른 조건보다 함께 일하는 동료들을 우선하는 당신. 성격이 나쁘거나
							일 못하는 동료 때문에 스트레스 받는 걸 가장 싫어합니다. 취직 또는 이직을 고민하고 있다면, 그 회사에서 일하고
							있는 직장인과 커피챗을 해보는 등 조직문화와 관련한 정보를 수집해보는 게 좋겠어요.결국 일도 '사람'이 모여서 하는 것! 다른 조건보다 함께 일하는 동료들을 우선하는 당신. 성격이 나쁘거나
							일 못하는 동료 때문에 스트레스 받는 걸 가장 싫어합니다. 취직 또는 이직을 고민하고 있다면, 그 회사에서 일하고
							있는 직장인과 커피챗을 해보는 등 조직문화와 관련한 정보를 수집해보는 게 좋겠어요.결국 일도 '사람'이 모여서 하는 것! 다른 조건보다 함께 일하는 동료들을 우선하는 당신. 성격이 나쁘거나
							일 못하는 동료 때문에 스트레스 받는 걸 가장 싫어합니다. 취직 또는 이직을 고민하고 있다면, 그 회사에서 일하고
							있는 직장인과 커피챗을 해보는 등 조직문화와 관련한 정보를 수집해보는 게 좋겠어요.</p>
						</div>
					</div>

				</div>

			</div>

		</div>
	</div>




	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.4.1/chart.min.js"></script> 
	<script>
	var ctx = document.getElementById('comparisonBarChart').getContext('2d');
	var comparisonBarChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: ['연봉', '조직문화', '성장가능성', '근속률', '복지'],
	        datasets: [{
	            label: '쿠팡',
	            backgroundColor: 'rgba(255, 99, 132, 0.2)',
	            borderColor: 'rgba(255, 99, 132, 1)',
	            borderWidth: 1,
	            data: [60, 70, 80, 50, 60]
	        }, {
	            label: '카카오',
	            backgroundColor: 'rgba(54, 162, 235, 0.2)',
	            borderColor: 'rgba(54, 162, 235, 1)',
	            borderWidth: 1,
	            data: [80, 90, 70, 80, 90]
	        }, {
	            label: '페이스북',
	            backgroundColor: 'rgba(255, 206, 86, 0.2)',
	            borderColor: 'rgba(255, 206, 86, 1)',
	            borderWidth: 1,
	            data: [90, 80, 90, 70, 80]
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
    
    
    var ctx = document.getElementById('ComradarChart').getContext('2d');
	var myRadarChart = new Chart(ctx, {
		type : 'radar',
		data : {
			labels : [ '연봉', '근속률', '조직문화', '성장 가능성', '복지' ],
			datasets : [ {
				 
				label : '회원', // 새로운 데이터셋의 라벨
				data : [ 40, 60, 90, 50, 60 ], // 새로운 데이터셋의 데이터
				backgroundColor : 'rgba(54, 162, 235, 0.2)', // fill color
				borderColor : 'rgba(54, 162, 235, 1)', // border color
				borderWidth : 1
			} ]
		},
		options : {
			scales : {
				r : {
					min : 0,
					max : 100,
					ticks : {
						stepSize : 20
					}
				}
			}
		}
	});
	</script>
</body>
</html>