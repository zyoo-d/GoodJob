<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
textarea {
	width: 100%;
	height: 100px;
	border: 1px solid #ccc;
	border-radius: 5px;
	padding: 10px;
	margin-top: 10px;
	resize: none;
}

.tag_meta {
	display: flex;
	flex-wrap: wrap;
	margin: 15px 3px;
	align-items: center;
	gap: 10px 5px;
}

.job_meta, .tag_meta {
	margin-top: 15px;
	padding: 5px 0;
}

.job_meta .job-keyword, .tag_meta .tag-keyword {
	display: inline-block;
	margin-bottom: 5px;
	cursor: pointer;
}

.comment-section {
	flex-basis: 100%;
	margin-top: 20px;
}
.fas {
	color: gold;
}
.far {
	color: #eee;
}
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
	<section class="page-hero pt-16 pb-6">
		<form method="post" action="/good/user/company/review/editreview.do">
			<input type="hidden" name="cp_seq" value="${dto.cp_seq}"> <input
				type="hidden" name="cp_rv_seq" value="${rdto.cp_rv_seq}">

			<div class="container">
				<div
					class="integration-single-container rounded-xl bg-white py-16 shadow-lg px-16 tab"
					id="itvWriteQnA">
					<div class="card-content-wrapper">


						<div class="px-4 text-center">
							<h1 class="mt-3 gradiTitle">
								<span>기업리뷰 수정</span>
							</h1>
							<p class="mt-2">기존에 작성해 주신 기업리뷰입니다. 관리자의 승인 후에는 수정/삭제가 불가능하며,
								반려된 리뷰는 삭제만 가능합니다.</p>


						</div>

						<div class="list_body mt-8 border-y border-border">
							<div class="list_item">

								<!-- 기업정보start -->

								<div class="list-header py-6 border-b-0 mx-0 bg-white px-10">
									<!-- 추가된 이미지 컨테이너 -->
									<div class="com-image">
										<img src="${cdto.image}"
											onerror="this.src='/good/asset/images/default.jpg'"
											alt="Company Logo">
									</div>

									<div class="com-title">
										<div class="col notification_info">
											<div class="job_tit">
												<h2>
													<a class="str_tit_title new" href="#" target="_blank">
														${cdto.cp_name} </a>
												</h2>
											</div>
											<div class="job_meta">
												<span class="job-keyword"><b>위치 </b>${cdto.cp_address}</span>
											</div>
										</div>
									</div>

								</div>
								<!--기업정보end -->

							</div>
						</div>





						<div class="ps-com">





							<!-- 별점등록START -->
							<div class="rating-section">
								<h4>별점 등록</h4>
								<div class="category">
									<label>연봉</label>
									<div class="stars" data-category="salary"
										data-initial-value="${rdto.salary_score}">
										<span class="star fa-solid fa-star" data-value="1"></span> <span
											class="star fa-solid fa-star" data-value="2"></span> <span
											class="star fa-solid fa-star" data-value="3"></span> <span
											class="star fa-solid fa-star" data-value="4"></span> <span
											class="star fa-solid fa-star" data-value="5"></span>
									</div>
									<input type="hidden" name="salary_score"
										value="${rdto.salary_score}" id="salary-highest">
								</div>
								<div class="category">
									<label>복지</label>
									<div class="stars" data-category="welfare"
										data-initial-value="${rdto.welfare_score}">
										<span class="star fa-solid fa-star" data-value="1"></span> <span
											class="star fa-solid fa-star" data-value="2"></span> <span
											class="star fa-solid fa-star" data-value="3"></span> <span
											class="star fa-solid fa-star" data-value="4"></span> <span
											class="star fa-solid fa-star" data-value="5"></span>
									</div>
									<input type="hidden" name="welfare_score"
										value="${rdto.welfare_score}" id="welfare-highest">
								</div>
								<div class="category">
									<label>조직안정성</label>
									<div class="stars" data-category="stability"
										data-initial-value="${rdto.stability_score}">
										<span class="star fa-solid fa-star" data-value="1"></span> <span
											class="star fa-solid fa-star" data-value="2"></span> <span
											class="star fa-solid fa-star" data-value="3"></span> <span
											class="star fa-solid fa-star" data-value="4"></span> <span
											class="star fa-solid fa-star" data-value="5"></span>
									</div>
									<input type="hidden" name="stability_score"
										value="${rdto.stability_score}" id="stability-highest">
								</div>
								<div class="category">
									<label>조직문화</label>
									<div class="stars" data-category="culture"
										data-initial-value="${rdto.culture_score}">
										<span class="star fa-solid fa-star" data-value="1"></span> <span
											class="star fa-solid fa-star" data-value="2"></span> <span
											class="star fa-solid fa-star" data-value="3"></span> <span
											class="star fa-solid fa-star" data-value="4"></span> <span
											class="star fa-solid fa-star" data-value="5"></span>
									</div>
									<input type="hidden" name="culture_score"
										value="${rdto.culture_score}" id="culture-highest">
								</div>
								<div class="category">
									<label>성장가능성</label>
									<div class="stars" data-category="growth"
										data-initial-value="${rdto.growth_score}">
										<span class="star fa-solid fa-star" data-value="1"></span> <span
											class="star fa-solid fa-star" data-value="2"></span> <span
											class="star fa-solid fa-star" data-value="3"></span> <span
											class="star fa-solid fa-star" data-value="4"></span> <span
											class="star fa-solid fa-star" data-value="5"></span>
									</div>
									<input type="hidden" name="growth_score"
										value="${rdto.growth_score}" id="growth-highest">
								</div>
							</div>
							<!-- 별점등록END -->


							<!-- com-tag section -->
							<div class="com-tag">
								<div class="click-tag">
									<h4>추천 태그</h4>
									<p class="tag-info mt-6">클릭하면 태그로 바로 등록!</p>
									<div class="tag_meta">
										<c:forEach items="${showTagList}" var="tlist">
											<span class="tag-keyword">${tlist}</span>
										</c:forEach>
									</div>
								</div>
								<!-- <div class="add-tag-text">
        <h3>태그 추가</h3>
        <p class="tag-info mt-6">추천 태그가 마음에 들지 않다면?</p>
        <div class="add-tag">
            <input type="text" name="tag_keyword" id="tag" placeholder="추가하실 태그를 입력하세요">
            <input type="button">추가</input>
        </div>
        <div class="tag-list tag_meta">
    Dynamically added tags will go here
</div>
        </div> -->

							</div>

						</div>
					</div>
					<!-- Comment section -->
					<div class="review-text comment-section">
						<div class="textAreaWrapper">
							<h4>한 줄 기업 평가</h4>
							<div class="textLengthWrap">
								<p class="lineCount">0자</p>
								<p class="lineTotal">/30자</p>
							</div>
						</div>
						<textarea id="lineBox" name="linereview" maxlength="33"
							placeholder="기업에 대한 한줄평을 작성해주세요!">${rdto.linereview}</textarea>
					</div>

					<div class="review-text comment-section">
						<div class="textAreaWrapper">
							<h4>이런 부분은 좋았어요</h4>
							<div class="textLengthWrap">
								<p class="goodCount">0자</p>
								<p class="goodTotal">/330자</p>
							</div>
						</div>
						<textarea id="textGood" name="good" maxlength="330"
							placeholder="해당 기업에서 근무하면서 좋았던 점을 작성해주세요!">${rdto.good}</textarea>

					</div>
					<div class="review-text comment-section">
						<div class="textAreaWrapper">
							<h4>이런 부분은 아쉬웠어요</h4>
							<div class="textLengthWrap">
								<p class="badCount">0자</p>
								<p class="badTotal">/330자</p>
							</div>
						</div>
						<textarea id="textBad" name="bad" maxlength="330"
							placeholder="해당 기업에서 근무하면서 아쉬웠던 점을 작성해주세요!">${rdto.bad}</textarea>
					</div>
					<hr>
					<div class="submit-info">
						<p>
							면접 후기에 대한 저작권은 IT's Good Job에 있습니다. <br> 등록하신 콘텐츠는 임의로 삭제하실 수 없습니다.
						</p>
					</div>
					<div class="moving-btn my-0">
						<c:if test="${rdto.id==id}">
							<a href="/good/user/mypage/myreview.do?id=${rdto.id}"
								class="btn btnBefore">이전으로</a>
						</c:if>


						<c:choose>
							<c:when test="${rdto.cp_rv_confirm == 0 && rdto.id==id}">
								<!-- 대기 상태일 때 수정 버튼 표시 -->

								<button type="submit" name="action" value="update"
									class="btn btnList submitRatings">수정하기</button>
							</c:when>
							<c:otherwise>
								<!-- 승인 또는 거절 상태일 때 수정/삭제 버튼 숨김 -->
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>

		</form>
	</section>

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script src="/good/assets/js/tagify.min.js"></script>
	<script>
	
//관리자 승인,반려
function approveReview(cp_rv_seq) {
        if (confirm("리뷰를 승인하시겠습니까?")) {
            location.href = "/good/user/company/review/editreview.do?cp_rv_seq=" + cp_rv_seq + "&action=approve";
        }
    }

    function rejectReview(cp_rv_seq) {
        if (confirm("리뷰를 반려하시겠습니까?")) {
            location.href = "/good/user/company/review/editreview.do?cp_rv_seq=" + cp_rv_seq + "&action=reject";
        }
    }

    function rejectReview(cp_rv_seq) {
        document.getElementById("rejectModal").style.display = "block";
    }

	
$('#lineBox').keyup(function (e) {
	let linecontent = $(this).val();
    
    // 글자수 세기
    if (linecontent.length == 0 || linecontent == '') {
    	$('.lineCount').text('0자');
    } else {
    	$('.lineCount').text(linecontent.length + '자');
    }
    
    // 글자수 제한
    if (linecontent.length > 30) {
        $(this).val($(this).val().substring(0, 30));
        alert('글자수는 30자까지 입력 가능합니다.');
    };
});


$('#textGood').keyup(function (e) {
	let goodcontent = $(this).val();
    
    
    if (goodcontent.length == 0 || goodcontent == '') {
    	$('.goodCount').text('0자');
    } else {
    	$('.goodCount').text(goodcontent.length + '자');
    }
    
    
    if (goodcontent.length > 300) {
    	
        $(this).val($(this).val().substring(0, 300));
  
        alert('글자수는 300자까지 입력 가능합니다.');
    };
});

$('#textBad').keyup(function (e) {
	let badcontent = $(this).val();
    
    if (badcontent.length == 0 || badcontent == '') {
    	$('.badCount').text('0자');
    } else {
    	$('.badCount').text(badcontent.length + '자');
    }

    if (badcontent.length > 300) {
    
        $(this).val($(this).val().substring(0, 300));
     
        alert('글자수는 300자까지 입력 가능합니다.');
    };
});

//별점
document.querySelectorAll('.category .stars').forEach(starsContainer => {
    const initialValue = parseFloat(starsContainer.getAttribute('data-initial-value'));
    updateStars(starsContainer, initialValue);
    updateHighestValue(starsContainer);

    starsContainer.addEventListener('click', function(e) {
        if (e.target.classList.contains('star')) {
            const starIndex = parseInt(e.target.getAttribute('data-value'), 10);
            let currentValue = e.target.dataset.currentValue ? parseFloat(e.target.dataset.currentValue) : 0;

            
            if (currentValue < starIndex - 0.5) {
                currentValue = starIndex - 0.5;
            } else if (currentValue < starIndex) {
                currentValue = starIndex;
            } else {
                currentValue = starIndex - 1; 
            }

            e.target.dataset.currentValue = currentValue;
            updateStars(starsContainer, currentValue);

            
            updateHighestValue(starsContainer);
        }
    });
});

function updateStars(container, value) {
    const stars = container.querySelectorAll('.star');
    stars.forEach(star => {
        const starValue = parseFloat(star.getAttribute('data-value'));
        star.classList.remove('fas', 'half-rated', 'far');
        if (value >= starValue) {
            star.classList.add('fas');
        } else if (value + 0.5 == starValue) {
            star.classList.add('fas', 'half-rated');
        } else {
            star.classList.add('far');
        }
    });
}

function updateHighestValue(container) {
    const stars = container.querySelectorAll('.star');
    let highestValue = 0;
    stars.forEach(star => {
        const starValue = parseFloat(star.getAttribute('data-value'));
        if (star.classList.contains('fas') && starValue > highestValue) {
            highestValue = starValue;
        }
    });

   
    const category = container.getAttribute('data-category');
    document.getElementById(category + '-highest').value = highestValue;
}


//태그
new Tagify(document.getElementById('tag'));

/* function addTag() {
    var input = document.getElementById('new-tag');
    var newTag = input.value.trim();
    if(newTag) {
        var tagList = document.querySelector('.tag-list');
        var tag = document.createElement('span');
        tag.className = 'tag-keyword';
        tag.textContent = newTag;
        tagList.appendChild(tag);
        input.value = ''; // Clear input after adding
    }
} */




</script>
</body>
</html>