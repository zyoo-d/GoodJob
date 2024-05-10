<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
</style>
</head>

<%@include file="/WEB-INF/views/inc/header.jsp"%>

<body>

	<section class="page-hero pt-16 pb-6">
		
			<div class="container">
				<div class="card" id="itvWriteQnA">
					<div class="card-content-wrapper">
						<div class="card-content">
							<div class="px-4 text-center">
								<h1 class="mt-3 gradiTitle">
									<span>기업검색</span>
								</h1>
								<p class="comlist mt-6">다양한 조건으로 기업을 검색하고 상세 정보를 확인해보세요.</p>
							</div>

							<div>
								<div id="searchForm">
									<input type="text" name="word" class="input" id="search-input"
										placeholder="기업명으로 검색하세요.">
									<button type="submit" class="search" id="search-btn">
										<span class="material-symbols-outlined">search</span>

									</button>
								</div>

								<button type="button" class="btn btnList w-full"
									id="detail-search-btn">평균연봉 / 지역 / 업종으로 상세조회하기</button>

							</div>

							<div>
								<form method="GET" action="/good/user/company/companylist.do">

									<section class="integration-single section pt-0 com-detail"
										style="display: none;" id="detailSection">

										<div class="container">
											<div class="justify-center">

												<div class="justify-center">

													<div class="border-border py-10" id="userDetail">

														<div>
															<h6 class="text-left preferTitle">
																<i class="fa-solid fa-hand-holding-dollar"></i> 희망연봉
															</h6>
															<select name="salary_seq">
																<option value="0">전체</option>
																<option value="9">2,600만원 이상</option>
																<option value="10">2,800만원 이상</option>
																<option value="11">3,000만원 이상</option>
																<option value="12">3,200만원 이상</option>
																<option value="13">3,400만원 이상</option>
																<option value="14">3,600만원 이상</option>
																<option value="15">3,800만원 이상</option>
																<option value="16">4,000만원 이상</option>
																<option value="17">5,000만원 이상</option>
																<option value="18">6,000만원 이상</option>
															</select>
														</div>

													</div>

													<!-- 선호근무지역 Start -->
													<div class="border-border">
														<h5 class="text-left preferTitle">
															<i class="fa-solid fa-location-dot"></i> 지역으로 조회하기(최대 3건)
														</h5>
														<div class="optionRegion com-list">
															<div class="region-list">
																<ul>
																	<li onclick="showLocations('서울')">서울</li>
																	<li onclick="showLocations('경기')">경기</li>
																	<li onclick="showLocations('인천')">인천</li>
																	<li onclick="showLocations('부산')">부산</li>
																	<li onclick="showLocations('대구')">대구</li>
																	<li onclick="showLocations('광주')">광주</li>
																	<li onclick="showLocations('대전')">대전</li>
																	<li onclick="showLocations('울산')">울산</li>
																	<li onclick="showLocations('세종')">세종</li>
																	<li onclick="showLocations('강원')">강원</li>
																	<li onclick="showLocations('경남')">경남</li>
																	<li onclick="showLocations('경북')">경북</li>
																	<li onclick="showLocations('전남')">전남</li>
																	<li onclick="showLocations('전북')">전북</li>
																	<li onclick="showLocations('충남')">충남</li>
																	<li onclick="showLocations('충북')">충북</li>
																	<li onclick="showLocations('제주')">제주</li>
																</ul>
															</div>

															<div class="location-list">
																<div id="locationCheckboxes"></div>

															</div>
															<div class="tag-group">
																<span id="lctag"> </span> <span class="clear"
																	onclick="clearLocations()"><i
																	class="fa-solid fa-rotate-right"></i> 지역 초기화</span>
															</div>
														</div>
													</div>
													<!-- 선호근무지역 End -->
													<!-- 선호직무 -->
													<div data-tab-group="service-tab" class="">


														<div class="px-4 text-center">
															<button class="btn" type="submit" id="btn-detail">선택
																조건으로 기업목록 조회하기</button>
														</div>
													</div>

												</div>

											</div>
										</div>
									</section>
								</form>
							</div>


						</div>

					</div>
					<div id="default_list_wrap" style="position: relative">
						<section class="list_recruiting">
							<div class="list-header">
								<div class="com-list list-title">
									<div class="list-title-content">
										<p>
											<span class="material-symbols-outlined">subject</span>
										</p>
										<c:if test="${map.search =='n' && map.hiring == 'n'}">
											<p>
												전체 기업은 총 <span id="list-cnt">${totalCount}</span>건입니다.
											</p>
										</c:if>
										<c:if test="${map.search=='y' || map.hiring=='y'}">
											<p>
												검색된 기업은 총 <span id="list-cnt">${searchTotalCount}</span>건입니다.
											</p>
										</c:if>

										<div class="checkbox-wrapper">
											<!-- 체크박스와 레이블 -->

											<input type="checkbox" id="hiring-only"
												class="sorting filter-checkbox" name="hiring"> <label
												for="hiring-only" class="filter-label">채용중인 기업</label>
											<button type="submit"
												style="border: 1px solid #ccc; margin-left: 3px; padding: 1px 3px; border-radius: 8px; font-size: 14px;">검색</button>

										</div>
									</div>

								</div>
								<div class="list_info">
									<button type="button" id="compare-btn" class="compare-button">기업
										맞춤 비교</button>
									<div class="InpBox">
										<select class="sorting" name="sort" id="sort">
											<option value="salary"
												${map.sort == 'salary' ? 'selected' : ''}>연봉순</option>
											<option value="riview"
												${map.sort == 'riview' ? 'selected' : ''}>리뷰순</option>
										</select>
									</div>

								</div>
							</div>

							<div class="list_body">
								<div id="rec-48103333" class="list_item">

									<!-- 기업목록start -->
									<c:forEach items="${comListInfo}" var="dto">

										<div
											class="box_item row mx-0 rounded-[20px] bg-white px-10 shadow-lg">

											<div class="com-list com-image">
												<img src="${dto.image}"
													onerror="this.src='/good/asset/images/default.jpg'"
													alt="Company Logo" style="width: 100px; height: 100px;"
													name="image">
												<!-- 예시 이미지 경로 -->
											</div>

											<div class="com-title">
												<div class="recruit-present-info">
													<div class="com-name">

														<span>지원가능한 채용공고 <b>${dto.com_rcrt_cnt}</b>건
														</span>
													</div>
												</div>
												<div class="col notification_info">

													<div class="job_tit"
														style="display: flex; align-items: flex-start;">
														<a
															href="/good/user/company/companyview.do?cp_seq=${dto.cp_seq}&word=${map.word}&search=${map.search}&hiring=${map.hiring}&page=${nowPage}"
															class="str_tit_title new">
															<h2>${dto.cp_name}</h2>
														</a>
														<button id="scrap" style="margin-left: 8px;" onclick="addScrap(${dto.cp_seq}, '${sessionScope.id}')">
															<i class="fa-regular fa-bookmark"></i>
														</button>
													</div>
													<div class="job_meta">
														<c:forEach items="${ComTaglist}" var="tdto">
															<c:if test="${tdto.cp_seq == dto.cp_seq}">
																<c:forEach items="${tdto.tag_keyword}" var="tag"
																	begin="0" end="4">
																	<span class="job-keyword">${tag}</span>
																</c:forEach>
															</c:if>
														</c:forEach>

													</div>
												</div>
											</div>
											<div class="com-list com-info">
												<div class="col recruit_info">
													<ul>
														<li><p class="work_place">
																<b>기업위치 </b>${dto.cp_address}</p></li>

														<li><p class="career">
																<b>총매출액 </b>
																<c:if test="${dto.fnc_sales == 0}">수집 정보 없음</c:if>
																<c:if test="${dto.fnc_sales != 0}">${dto.fnc_sales} ${dto.unit}</c:if>
															</p></li>
														<li><p class="education">
																<b>평균연봉 </b>
																<c:if test="${dto.hire_avr_salary == 0}">수집 정보 없음</c:if>
																<c:if test="${dto.hire_avr_salary !=0}">${dto.hire_avr_salary} 만원</c:if>
															</p></li>
														<li><p class="salary">
																<b>리뷰 수 </b>${dto.com_rv_cnt}건</p></li>
													</ul>
												</div>
											</div>
										</div>
									</c:forEach>
									<!-- 채용공고end -->

								</div>
							</div>
						</section>
					</div>
				</div>


			</div>
	


		<nav class="PageBox z-custom" aria-label="Page navigation example">
			<ul class="pagination z-custom">${pagebar}</ul>
		</nav>
	</section>

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>

	<script type="text/javascript">
		
	
	
		<c:if test="${map.search == 'y'}">
		//검색중 상태 유지
		$('input[name=word]').val('${map.word}');
		$('select[name=column]').val('${map.column}');
		$('input[name=hiring]').val('${map.hiring}');
		</c:if>

	
	
	$(document).ready(function() {
	    $("#sort").change(function() {
	        var selectedSort = $(this).val();
	        submitDetailSearch(selectedSort);
	    });
	});
	
	function submitDetailSearch(sortOption) {
	    var salary_seq = $('select[name="salary_seq"]').val();
	    var sort = sortOption || $('#sort').val();
	    var cp_address = [];
	    $('input[name="cp_address"]:checked').each(function() {
	        cp_address.push($(this).val());
	    });

	    $.ajax({
	        url: "/user/company/companylist.do",
	        method: "GET",
	        data: {
	            sort: sort,
	            page: ${nowPage},
	            column: '${map.column}',
	            word: '${map.word}',
	            salary_seq: salary_seq,
	            cp_address: cp_address,
	            search: 'y'
	        },
	        success: function(response) {
	            $(".list_body").html($(response).find(".list_body").html());
	            $(".pagination").html($(response).find(".pagination").html());
	        },
	        error: function(a, b, c) {
	            console.log(a, b, c);
	        }
	    });
	}
		
		document.addEventListener("DOMContentLoaded", function() {
			document.getElementById("detail-search-btn").addEventListener(
					"click",
					function() {
						var detailSection = document
								.getElementById("detailSection");
						if (detailSection.style.display === "none") {
							detailSection.style.display = "block";
						} else {
							detailSection.style.display = "none";
						}
					});
		});
		/* 선호근무지역 Start */
		//선호근무지역 생성기
		function showLocations(region) {
			var locationCheckboxes = document
					.getElementById('locationCheckboxes');
			locationCheckboxes.innerHTML = '';

			if (region === '서울') {
				var locations = [ '서울전체', '강남구', '강동구', '강북구', '강서구', '관악구',
						'광진구', '구로구', '금천구', '노원구', '도봉구', '동대문구', '동작구',
						'마포구', '서대문구', '서초구', '성동구', '성북구', '송파구', '양천구',
						'영등포구', '용산구', '은평구', '종로구', '중구', '중랑구' ];

				var locationcode = 101000;
			} else if (region === '경기') {
				var locations = [ '경기전체', '가평군', '고양시', '고양시 덕양구', '고양시 일산동구',
						'고양시 일산서구', '과천시', '광명시', '광주시', '구리시', '군포시', '김포시',
						'남양주시', '동두천시', '부천시', '부천시 소사구', '부천시 오정구', '부천시 원미구',
						'성남시', '성남시 분당구', '성남시 수정구', '성남시 중원구', '수원시',
						'수원시 권선구', '수원시 영통구', '수원시 장안구', '수원시 팔달구', '시흥시',
						'안산시', '안산시 단원구', '안산시 상록구', '안성시', '안양시', '안양시 동안구',
						'안양시 만안구', '양주시', '양평군', '여주시', '연천군', '오산시', '용인시',
						'용인시 기흥구', '용인시 수지구', '용인시 처인구', '의왕시', '의정부시', '이천시',
						'파주시', '평택시', '포천시', '하남시', '화성시' ];
				var locationcode = 102000;
			} else if (region === '광주') {
				var locations = [ '광주전체', '광산구', '남구', '동구', '북구', '서구' ];
				var locationcode = 103000;
			} else if (region === '대구') {
				var locations = [ '대구전체', '남구', '달서구', '달성군', '동구', '북구', '서구',
						'수성구', '중구', '군위군' ];

				var locationcode = 104000;
			} else if (region === '대전') {
				var locations = [ '대전전체', '대덕구', '동구', '서구', '유성구', '중구' ];
				var locationcode = 105000;
			} else if (region === '부산') {
				var locations = [ '부산전체', '강서구', '금정구', '기장군', '남구', '동구',
						'동래구', '부산진구', '북구', '사상구', '사하구', '서구', '수영구', '연제구',
						'영도구', '중구', '해운대구' ];

				var locationcode = 106000;
			} else if (region === '울산') {
				var locations = [ '울산전체', '남구', '동구', '북구', '울주군', '중구' ];

				var locationcode = 107000;
			} else if (region === '인천') {
				var locations = [ '인천전체', '강화군', '계양구', '미추홀구', '남동구', '동구',
						'부평구', '서구', '연수구', '옹진군', '중구' ];

				var locationcode = 108000;
			} else if (region === '강원') {
				var locations = [ '강원전체', '강릉시', '고성군', '동해시', '삼척시', '속초시',
						'양구군', '양양군', '영월군', '원주시', '인제군', '정선군', '철원군', '춘천시',
						'태백시', '평창군', '홍천군', '화천군', '횡성군' ];

				var locationcode = 109000;
			} else if (region === '경남') {
				var locations = [ '경남전체', '거제시', '거창군', '고성군', '김해시', '남해군',
						'창원시 마산회원구', '창원시 마산합포구', '창원시 성산구', '창원시 의창구', '밀양시',
						'사천시', '산청군', '양산시', '의령군', '진주시', '창원시 진해구', '창녕군',
						'창원시', '통영시', '하동군', '함안군', '함양군', '합천군' ];
				var locationcode = 110000;
			} else if (region === '경북') {
				var locations = [ '경북전체', '경산시', '경주시', '고령군', '구미시', '군위군',
						'김천시', '문경시', '봉화군', '상주시', '성주군', '안동시', '영덕군', '영양군',
						'영주시', '영천시', '예천군', '울릉군', '울진군', '의성군', '청도군', '청송군',
						'칠곡군', '포항시', '포항시 남구', '포항시 북구' ];
				var locationcode = 111000;
			} else if (region === '전남') {
				var locations = [ '전남전체', '강진군', '고흥군', '곡성군', '광양시', '구례군',
						'나주시', '담양군', '목포시', '무안군', '보성군', '순천시', '신안군', '여수시',
						'영광군', '영암군', '완도군', '장성군', '장흥군', '진도군', '함평군', '해남군',
						'화순군' ];
				var locationcode = 112000;
			} else if (region === '전북') {
				var locations = [ '전북전체', '고창군', '군산시', '김제시', '남원시', '무주군',
						'부안군', '순창군', '완주군', '익산시', '임실군', '장수군', '전주시',
						'전주시 덕진구', '전주시 완산구', '정읍시', '진안군' ];
				var locationcode = 113000;
			} else if (region === '충북') {
				var locations = [ '충북전체', '괴산군', '단양군', '보은군', '영동군', '옥천군',
						'음성군', '제천시', '증평군', '진천군', '청원군', '청주시', '청주시 상당구',
						'청주시 흥덕구', '충주시', '청주시 청원구', '청주시 서원구' ];

				var locationcode = 114000;
			} else if (region === '충남') {
				var locations = [ '충남전체', '계룡시', '공주시', '금산군', '논산시', '당진시',
						'보령시', '부여군', '서산시', '서천군', '아산시', '연기군', '예산군', '천안시',
						'천안시 동남구', '천안시 서북구', '청양군', '태안군', '홍성군' ];

				var locationcode = 115000;
			} else if (region === '제주') {
				var locations = [ '제주전체', '서귀포시', '제주시' ];

				var locationcode = 116000;
			} else if (region === '세종') {
				var locations = [ '세종특별자치시' ];

				var locationcode = 118000;
			}

			locations.forEach(function(location) {
				var label = document.createElement('label');
				var checkbox = document.createElement('input');
				checkbox.type = 'checkbox';
				checkbox.value = locationcode;
				label.appendChild(checkbox);
				label.appendChild(document.createTextNode(' ' + location));
				locationCheckboxes.appendChild(label);
				locationcode += 10;
			});

			//지역체크 유지하기 위한 코드
			document
					.querySelectorAll(
							'#locationCheckboxes input[type="checkbox"]')
					.forEach(
							function(locationCheckbox) {
								document
										.querySelectorAll(
												'#lctag input[type="checkbox"]')
										.forEach(
												function(lctagCheckbox) {
													if (locationCheckbox.value === lctagCheckbox.id) {
														locationCheckbox.checked = true;
													}
												});
							});

		}

		//지역 초기화 버튼
		function clearLocations() {
			$('#locationCheckboxes').html('');
			$('#lctag').html('');
		}

		//지역 체크시 태그 생성/삭제
		$('#locationCheckboxes')
				.on(
						'change',
						'input[type="checkbox"]',
						function() {
							var target = $(this);
							var lctag = $('#lctag');

							if (lctag.children().length >= 6
									&& target.is(':checked')) {
								alert('3개 이상 선택할 수 없습니다.');
								target.prop('checked', false);
								return;
							}

							if (target.is(':checked')) {
								var tagname = target.parent().text().trim();
								var tagId = target.val();

								lctag
								.append('<input type="checkbox" id="' + tagId + '" name="cp_address" checked value="' + tagname + '">' +
							             '<label for="' + tagId + '">' + tagname + ' <i class="fa-solid fa-xmark"></i></label>');
								
							} else {
								var tagId = target.val();
								$('#' + tagId).next().addBack().remove();
							}
						});

		//지역 태그 눌러서 삭제하기
		$('#lctag').on('change', 'input[type="checkbox"]', function() {
			var checkboxId = $(this).attr('id');

			$('#locationCheckboxes input[type="checkbox"]').each(function() {
				var locationCheckbox = $(this);
				if (locationCheckbox.val() === checkboxId) {
					locationCheckbox.prop('checked', false);
				}
			});
			$(this).next().addBack().remove();
		});
		/* 선호근무지역 End */
		
		 function addScrap(cpSeq, userId) {
   
        $.ajax({
            url: '/good/test/mg/addscrap.do',
            method: 'POST',
            data: {
                cp_seq: cpSeq,
                id: userId
            },
            success: function(response) {
   
                alert('스크랩 성공');
            },
            error: function(xhr, status, error) {

            	alert('스크랩 실패');
            }
        });
    }
		
	</script>
</body>

</html>