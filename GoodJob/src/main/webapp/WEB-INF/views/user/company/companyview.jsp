<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
.main {
	width: 1100px;
	margin: 0 auto;
	display: flex;
}

.card {
	display: inline-flex;
	margin-bottom: 10px;
}
.card-footer {
	margin-top: 5px;
	padding-top: 5px;
}

#news-title {
	font-size: 1.1rem;
}

#summary, #income-info, #comment, #recruit, #keyword, #review, #welfare
	{
	border-radius: 15px;
	border-color: rgb(234, 237, 244);
	background-color: #FFFFFF;
	border: 0;
	margin-bottom: 20px;
	border-color: rgb(229, 231, 235);
	border-bottom-left-radius: 12px;
	border-bottom-right-radius: 12px;
	border-bottom-width: 0px;
	border-style: solid;
	border-left-width: 0px;
	border-right-width: 0px;
	border-top-left-radius: 12px;
	border-top-right-radius: 12px;
	box-shadow: rgba(0, 0, 0, 0.1) 0px 15px 34px 0px;
	box-sizing: border-box;
}

#comment {
	height: 600px;
	width: 100%;
	padding: 10px;
}

#recruit {
	display: flex;
	flex-wrap: wrap; /* 내용이 넘칠 경우 줄 바꿈 */
	justify-content: flex-start;
	padding: 12px;
	position: relative;
	padding: 11px;
	gap: 10px;
}

#recruit>div {
	position: static;
	margin-top: 10px;
	width: 32%;
	border: 1px solid #5566cc;
}

.form-control {
	width: 80%;
	height: 50px;
}

.bg-gray-200 {
	background-color: rgb(83, 90, 237);
	color: white;
	width: 100%;
	font-size: .7rem;
}

.bg-gray-200>span {
	float: right;
	color: #FAFAFA;
}

#review {
	padding: 10px;
}

#review_content {
	border-bottom: 1px solid #4444;
	padding: 10px;
	margin: 10px;
	margin-bottom: 20px;
	padding-bottom: 40px;
}

.fa-star {
	color: gold;
}

#review > div:last-child {
	border-bottom: 0;
}
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
	<div class="main">
		<section class="page-hero pt-16 pb-6">
			<div class="container">
				<div class="card" id="itvWriteQnA">
					<div class="company-info">
						<div class="card-content-wrapper">
							<div class="p-4 text-center border-b border-border">
								<h2 class="mt-3 gradiTitle">
									<span>기업 상세정보</span>
								</h2>
								<p class="my-2">기업의 상세한 정보를 확인하고 기업리뷰를 확인해 보세요.</p>
							</div>
						</div>


						<div
							class="box_item row mx-0 rounded-[20px] bg-white px-10 shadow-lg"
							id="summary">

							<div class="com-list com-image"
								style="background-image: url('${dto.image}');"
								alt="Company Logo" name="image">
								<!-- 예시 이미지 경로 -->
							</div>

							<div class="detail-title">
								<div class="col notification_info">

									<div class="job_tit mb-4"
										style="display: flex; align-items: flex-start;">
										<h3>${dto.cp_name}</h3>
										<button id="scrap" style="margin-left: 8px; color: #5566cc;">
											<i class="fa-regular fa-bookmark" style="font-size: 1.2rem;"></i>
											${dto.com_scrap_cnt}+
										</button>
									</div>
									<div class="job_meta" style="font-size: 0.8rem;">

										<c:if test="${not empty topTags}">
											<div class="job_meta">
												<c:forEach items="${topTags}" var="tag" begin="0" end="2">
													<span class="job-keyword">${tag}</span>
												</c:forEach>
											</div>
										</c:if>

									</div>
								</div>
							</div>
							<div class="detail-list com-info">
								<div class="col recruit_info">
									<ul>
										<li><p class="career">
												<b>주요업무 </b>${dto.idst_name}</p></li>
										<li><p>
												<b>위치 </b>${dto.cp_address}</p></li>
										<li><p>
												<b>사원 수 </b>${dto.hire_member} 명
											</p></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- 채용공고end -->


						<div class="border-border border-y py-6 my-4">
							<h5>예상 평균 연봉 / 평균 근속 연수</h5>
							<div id="salary">
								<div
									class="row rounded-[20px] bg-white shadow-lg">
									<h2>
										<span class="count"> <c:if
												test="${dto.hire_avr_salary == 0}">정보 없음</c:if> <c:if
												test="${dto.hire_avr_salary != 0}">
												<fmt:formatNumber type="number" maxFractionDigits="0"
													value="${dto.hire_avr_salary}" /> 만원</c:if></span> <span
											class="text-[#A3A1FB]">+</span>
									</h2>
									<p>예상 연봉</p>
								</div>
								<div
									class="row rounded-[20px] bg-white shadow-lg">
									<h2>
										<span class="count"> <c:if
												test="${dto.idst_avg_salary == 0}">정보 없음</c:if> <c:if
												test="${dto.idst_avg_salary != 0}">
												<fmt:formatNumber type="number" maxFractionDigits="0"
													value="${dto.idst_avg_salary}" /> 만원

									</c:if></span> <span class="text-[#A3A1FB]">+</span>
									</h2>
									<p>업계 평균 연봉</p>
								</div>
								<div
									class="row rounded-[20px] bg-white shadow-lg">
									<h2>
										<span class="count"> <c:if
												test="${dto.hire_avr_year == 0}">정보 없음</c:if> <c:if
												test="${dto.hire_avr_year != 0}">
						${dto.hire_avr_year} 년</c:if></span> <span class="text-[#5EE2A0]">+</span>
									</h2>
									<p>평균 근속 연수</p>
								</div>

							</div>
							<div id="income-info">

								<figure class="highcharts-figure">
									<div id="container"></div>
									<p class="highcharts-description"></p>
								</figure>
							</div>
						</div>

						<h5>해당 기업 공고</h5>
						<!-- 채용정보 -->
						<div id="recruit" class="mt-4">
							<c:forEach items="${comRecruitList}" var="rcdto" begin="0"
								end="5">
								<div
									class="rounded-xl border border-primary bg-white p-6 shadow-lg">
									<div class="flex items-center justify-between"
										id="recruit_card">
										<div>
											<h6>${rcdto.rcrt_name}</h6>
										</div>

									</div>
									<c:if test="${not empty rcdto.enddate}">
										<p class="mt-2">마감일 ${rcdto.enddate}</p>
									</c:if>
									<div class="my-6 border-y border-border py-6">
										<h6>이런 사람을 원해요!</h6>
										<ul class="mt-6">
											<li class="mb-3 flex items-center text-sm"><svg
													class="mr-2.5 text-primary" width="16" height="13"
													viewBox="0 0 16 13" fill="none"
													xmlns="http://www.w3.org/2000/svg">
                  <path d="M2 5.42857L6.36364 10L14 2"
														stroke="currentColor" stroke-width="3"
														stroke-linecap="round" />
                </svg> ${rcdto.edu_type}</li>
											<li class="mb-3 flex items-center text-sm"><svg
													class="mr-2.5 text-primary" width="16" height="13"
													viewBox="0 0 16 13" fill="none"
													xmlns="http://www.w3.org/2000/svg">
                  <path d="M2 5.42857L6.36364 10L14 2"
														stroke="currentColor" stroke-width="3"
														stroke-linecap="round" />
                </svg> 최소 경력 ${rcdto.min_career}년</li>
											<li class="mb-3 flex items-center text-sm"><svg
													class="mr-2.5 text-primary" width="16" height="13"
													viewBox="0 0 16 13" fill="none"
													xmlns="http://www.w3.org/2000/svg">
                  <path d="M2 5.42857L6.36364 10L14 2"
														stroke="currentColor" stroke-width="3"
														stroke-linecap="round" />
									
										
                							</svg> 최대 경력 <c:if test="${rcdto.max_career == '0'}">무관</c:if>
												<c:if test="${rcdto.max_career != '0'}">${rcdto.max_career}년</c:if></li>


											<li class="flex items-center text-sm"><svg
													class="mr-2.5 text-primary" width="16" height="13"
													viewBox="0 0 16 13" fill="none"
													xmlns="http://www.w3.org/2000/svg">
                  <path d="M2 5.42857L6.36364 10L14 2"
														stroke="currentColor" stroke-width="3"
														stroke-linecap="round" />
                </svg> ${rcdto.cp_address}</li>
										</ul>
									</div>
									<div class="text-center">
										<a
											class="btn btn-primary h-[48px] w-full rounded-[50px] leading-[30px]"
											href="${rcdto.rcrt_link}">지원하러 가기</a>

									</div>
								</div>
							</c:forEach>
							<%--  </c:forEach> --%>

							<c:if test="${dto.com_rcrt_cnt ==0}">
								<span> <b>${dto.cp_name}</b>에서 모집중인 채용공고가 없습니다.
								</span>
							</c:if>
						</div>
						<!-- div:recruit -->
						<%-- <span class="menu">기업 키워드</span>

						<div id="keyword" class="job_meta" style="text-align: center;">
							<c:if test="${not empty topTags}">
								<c:forEach items="${topTags}" var="tag" begin="0" end="5">
									<span class="job-keyword">${tag}</span>
								</c:forEach>
							</c:if>
							<c:if test="${empty topTags}">
								<span>등록된 키워드가 없습니다. 리뷰를 작성하여 키워드를 등록해 보세요.</span>
							</c:if>
						</div> --%>

						<!-- 기업복지 -->
						<div class="border-border border-y py-6 my-4">
							<h5>기업 복지</h5>

							<div id="welfare" class="mt-4">
								<c:choose>
									<c:when test="${not empty wlist}">
										<c:set var="prevWeltype" value="" />
										<c:forEach items="${wlist}" var="wdto">
											<c:if test="${wdto.weltype_name != prevWeltype}">
												<c:if test="${not empty prevWeltype}">
							</div>
							</c:if>
							<div class="weltype pt-5 py-3 px-5">
								<h6 style="text-align: center; margin-bottom: 5px;">[ ${wdto.weltype_name} ]</h6>
								<c:set var="prevWeltype" value="${wdto.weltype_name}" />
								</c:if>
								<p>- ${wdto.wel_name}</p>
								</c:forEach>
							</div>
							</c:when>
							<c:otherwise>
								<span>등록된 복지가 없습니다.</span>
							</c:otherwise>
							</c:choose>
						</div>
					</div>
					<!--  -->
					<div class="flex justify-between">
					<h5>기업 리뷰 보기</h5>
					<button id="add_review"
						onclick="checkLogin(${dto.cp_seq}, '${word}', '${map.search}', '${map.hiring}', ${nowPage})">리뷰
						쓰러가기</button>
</div>

					<div>

						<div id="review" class="mt-4">
							<c:if test="${not empty listReview}">
								<c:forEach items="${listReview}" var="rdto">
									<c:if
										test="${rdto.cp_rv_confirm == 1 || sessionScope.user.id == rdto.id || sessionScope.user.lv == 2}">

										<div id="review_content">
											<div id="oneline">"${rdto.linereview}"</div>
											<div id="score">
												<div class="score_rating" id="score_detail">
													<div id="salary_score">
														<span id="salary_review">연봉 </span><br />
														<c:forEach var="i" begin="1" end="${rdto.salary_score}">
															<i class="fa-solid fa-star"></i>
														</c:forEach>
													</div>
													<div class="score_rating" id="welfare_score">
														<span id="welfare_review">복지 </span> <br />
														<c:forEach var="i" begin="1" end="${rdto.welfare_score}">
															<i class="fa-solid fa-star"></i>
														</c:forEach>
													</div>
													<div class="score_rating" id="ingvt_score">
														<span id="ingvt_review">근무 안정성 </span> <br />
														<c:forEach var="i" begin="1" end="${rdto.stability_score}">
															<i class="fa-solid fa-star"></i>
														</c:forEach>
													</div>
													<div class="score_rating" id="culture_score">
														<span id="culture_review">조직문화</span> <br />
														<c:forEach var="i" begin="1" end="${rdto.culture_score}">
															<i class="fa-solid fa-star"></i>
														</c:forEach>
													</div>
													<div class="score_rating" id="growth_score">
														<span id="growth_review">성장 가능성 <br /></span>
														<c:forEach var="i" begin="1" end="${rdto.growth_score}">
															<i class="fa-solid fa-star"></i>
														</c:forEach>

													</div>
													<div class="score_rating" id="total_score">
														<span id="total_review">총 평점 </span> <br />
														<c:forEach var="i" begin="1"
															end="${(rdto.salary_score+rdto.welfare_score+rdto.stability_score+rdto.culture_score+rdto.growth_score)/5}">
															<i class="fa-solid fa-star"></i>
														</c:forEach>
													</div>
												</div>
											</div>
											<div class="goodbad_title" id="good_title">
												<i class="fa-regular fa-thumbs-up"></i> 이런 부분은 좋았어요.
												<div id="good">${rdto.good}</div>
											</div>

											<div class="goodbad_title" id="bad_title">
												<i class="fa-regular fa-thumbs-down"></i> 이런 부분은 아쉬웠어요.
												<div id="bad">${rdto.bad}</div>
											</div>


										</div>

										<div></div>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${empty listReview}">등록된 리뷰가 없습니다. 직접 등록해보세요!</c:if>
						</div>
					</div>
				</div>

			</div>
	</div>

	</section>



	<!--div:company-Info  -->



	<div class="right-container pt-16">
		<div id="news" class="w-full">
			<h5 class="mb-4">관련 기사 보기</h5>
			<c:forEach items="${nlist}" var="ndto" end="1">
				<div class="card" id="card_news">
					<div id="card_news_content">
						<h6 id="news-title">
							<a href="${ndto.link}">${ndto.title}</a>
						</h6>
						<p id="article">${ndto.description}</p>
						<div class="card-footer mt-6 flex space-x-4">
							<span class="inline-flex items-center text-xs text-[#666]">
								<svg class="mr-1.5" width="14" height="16" viewBox="0 0 14 16"
									fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path
										d="M12.5 2H11V0.375C11 0.16875 10.8313 0 10.625 0H9.375C9.16875 0 9 0.16875 9 0.375V2H5V0.375C5 0.16875 4.83125 0 4.625 0H3.375C3.16875 0 3 0.16875 3 0.375V2H1.5C0.671875 2 0 2.67188 0 3.5V14.5C0 15.3281 0.671875 16 1.5 16H12.5C13.3281 16 14 15.3281 14 14.5V3.5C14 2.67188 13.3281 2 12.5 2ZM12.3125 14.5H1.6875C1.58438 14.5 1.5 14.4156 1.5 14.3125V5H12.5V14.3125C12.5 14.4156 12.4156 14.5 12.3125 14.5Z"
										fill="#939393" />
                </svg> ${ndto.pubDate}
							</span>
						</div>
					</div>
				</c:forEach>


			</div>
			<span class="menu">실시간 댓글
				<button>
					<i class="fa-solid fa-rotate-right"></i>
				</button>
			</span>



			<div id="comment">
				<div class="card chat-box" id="mychatbox">

					<div id="commentList">
						<c:forEach items="${livecommentlist}" var="livecommentdto">
							<div class="mb-2">
								<br />
								<p class="bg-gray-200 text-gray-700 rounded-lg py-2 px-4"
									id="chatchat">${livecommentdto.content}</p>
								<div id="comment_content">
									${livecommentdto.nickname} <span id="chat_regdate">${livecommentdto.regdate}</span><br>
									<div id="CplivecommentBtn">
										<button id="report-btn" data-boardtype="live" data-seq="${livecommentlist.cm_seq}">[ 신고 ]</button>
										<c:if
											test="${not empty id && (livecommentdto.id == id || lv == 2)}">

											<button id="liveCommentDelBtn"
												data-seq="${livecommentdto.cm_seq}"
												onclick="del(${livecommentdto.cm_seq});">[ 삭제 ]</button>
										</c:if>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
			</c:forEach>




			<div class="card-footer chat-form">
				<div id="chat-form" class="flex justify-around mt-2">
					<input type="text" class="form-control"
						placeholder="Type a message" name="liveCommentContent">
					<button class="btn btn-primary" id="liveCommentBtn">
						<i class="far fa-paper-plane"></i>
					</button>
				</div>
			</div>
		</div>
		<div id="go_container">

			<button class="go" id="goReview">기업리뷰 바로보기</button>
			<button class="go" id="goInterview">면접후기 바로보기</button>
		</div>


	</div>
	<!-- right-container -->

	</div>
	<!-- main  -->


	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<%@include file="/WEB-INF/views/user/report/reportmodal.jsp"%>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/export-data.js"></script>
	<script src="https://code.highcharts.com/modules/accessibility.js"></script>
	<!-- <script src="/good/assets/modules/chart.min.js"></script> -->
	<script src="/good/assets/js/page/index-0.js"></script>
	<script>
	$(document).ready(function() {
	    $("#scrap").click(function() {
	        var cpSeq = ${dto.cp_seq};
	        var id = '${sessionScope.id}';
	        addScrap(cpSeq, id);
	    });
	});
		// .chat-box 요소의 스크롤을 맨 아래로 이동하는 함수
		function scrollToBottom() {
			var chatBox = document.querySelector(".chat-box");
			chatBox.scrollTop = chatBox.scrollHeight;
		}

		// 페이지 로드 시 .chat-box의 스크롤을 맨 아래로 이동
		window.onload = function() {
			scrollToBottom();
		};
		
   
		function checkLogin(cp_seq, word, search, hiring, page) {
		    const id = '<%=session.getAttribute("id")%>';
		    if (id === 'null') {
		        // 로그인되어 있지 않은 경우 로그인 페이지로 이동
		        location.href = '/good/user/signin.do?cp_seq=' + cp_seq;
		    } else {
		        // 로그인되어 있는 경우 리뷰 작성 페이지로 이동
		        location.href = '/good/user/company/review/addreview.do?cp_seq=' + cp_seq + '&word=' + word + '&search=' + search + '&hiring=' + hiring + '&page=' + page;
		    }
		}
		//리뷰삭제
		function confirmDelete() {
		    if (confirm("리뷰를 삭제하시겠습니까?")) {
		       
		        document.forms[0].submit();
		    }
		}
		
		$(document).ready(function() {
		    // 데이터 리스트
		    var sales = [<c:forEach items="${salesList}" var="sale" varStatus="status">${sale}${status.last ? '' : ','}</c:forEach>];
		    var ebit = [<c:forEach items="${ebitList}" var="eb" varStatus="status">${eb}${status.last ? '' : ','}</c:forEach>];
		    var income = [<c:forEach items="${incomeList}" var="inc" varStatus="status">${inc}${status.last ? '' : ','}</c:forEach>];

		    // 데이터가 비어있는지 확인
		    if (sales.length === 0 && ebit.length === 0 && income.length === 0) {
		        // 데이터가 없을 경우 메시지 표시
		        $('#container').html('<p>데이터를 수집중입니다...</p>');
		    } else {
		        // Highcharts를 이용한 차트 그리기
		        Highcharts.chart('container', {
		            chart: {
		                type: 'line'
		            },
		            title: {
		                text: '기업 재무 정보'
		            },
		            xAxis: {
		                categories: ['2021', '2022', '2023']
		            },
		            plotOptions: {
		                line: {
		                    dataLabels: {
		                        enabled: true
		                    },
		                    enableMouseTracking: false
		                }
		            },
		            series: [{
		                name: '매출액',
		                data: sales
		            }, {
		                name: '영업이익',
		                data: ebit
		            }, {
		                name: '당기순이익',
		                data: income
		            }]
		        });
		    }
		});
		
	
	
	$("#liveCommentBtn")
	.click(
			function() {
			
				
				var commentContent = $(
						"input[name='liveCommentContent']").val();
				
				var cpSeq = ${dto.cp_seq};
				
			 	var userId = "${sessionScope.id}";
			    if (!userId) {
			        alert("로그인해주세요."); 
			        return;
			    } 
				

				if (commentContent.trim() === "") {
					alert("댓글 내용을 입력해주세요.");
					return;
				}

				$.ajax({
							url : "/good/board/comment/AddLivecomment.do",
							type : "POST",
							data : {
								cp_seq : cpSeq,
								content : commentContent
							},
							dataType : 'json',
							success : function(response) {
								console.log(response);
								var newComment = response.dto;
								console.log(newComment);
								var commentHtml = '<div class="mb-2">'
									+ '  <br />'
									+ '  <p class="bg-gray-200 text-gray-700 rounded-lg py-2 px-4" id="chatchat">' + newComment.live_content + '</p>'
									+ '  <div id="comment_content">'
									+ '    ' + newComment.nickname + ' <span id="chat_regdate">' + newComment.live_cm_regdate + '</span>'
									+ '    <button id="singo">[ 신고 ]</button>'
									+ '  </div>'
									+ '</div>';
								
							

								// 새로운 댓글을 목록에 추가
								$("#commentList").append(commentHtml);

								// 사용자에게 댓글이 추가되었음을 알림
								alert("댓글이 추가되었습니다.");

								$("input[name='liveCommentContent']").val(""); // 댓글 입력 필드 초기화
							},
							error : function() {
								alert("댓글 작성 중 오류가 발생했습니다.");
							}
						});
			});
	

	function del(seq) {
	    if (confirm('삭제하겠습니까?')) {
	        $.ajax({
	            type: 'POST',
	            url: '/good/board/comment/livedelcomment.do',
	            data: { seq: seq },
	            dataType: 'json',
	            success: function(result) {
	                if (result.result == 1) {
	                    // 삭제된 댓글을 화면에서 제거
	                    $('#commentList').find('.mb-2').each(function() {
	                        if ($(this).find('#liveCommentDelBtn').data('seq') == seq) {
	                            $(this).remove();
	                            return false;
	                        }
	                    });
	                    alert('댓글이 삭제되었습니다.');
	                } else {
	                    alert('댓글 삭제를 실패했습니다.');
	                }
	            },
	            error: function(a, b, c) {
	                console.log(a, b, c);
	            }
	        });
	    }
	}
	function addScrap(cpSeq, id) {
	    if (!id) {
	        alert("로그인이 필요합니다.");
	        return;
	    }

	    $.ajax({
	        url: "/good/test/mg/addscrap.do",
	        type: "POST",
	        data: {
	            id: id,
	            cp_seq: cpSeq
	        },
	        success: function(response) {
	            alert("스크랩 되었습니다.");
	            // 스크랩 수 업데이트
	            var scrapCount = parseInt($("#scrap").text().replace("+", "")) + 1;
	            $("#scrap").html('<i class="fa-regular fa-bookmark"></i> ' + scrapCount + '+');
	        },
	        error: function() {
	            alert("스크랩 중 오류가 발생했습니다.");
	        }
	    });
	}
	
			
		
	</script>
</body>
</html>