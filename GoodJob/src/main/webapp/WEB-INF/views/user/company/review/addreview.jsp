<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="/WEB-INF/views/inc/asset.jsp" %>
<style>


.comment-section {
    margin-top: 20px;
}

.category {
    margin: 20px;
    display: flex; 
    align-items: center; 
    justify-content: space-between; 
}

.category label {
    flex: 0 0 auto;
    margin-right: 10px;
    font-size: 1.5rem;
    text-align: left; 
    width: 30%;
}



textarea {
    width: 100%;
    height: 100px;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 10px;
    margin-top: 10px;
    resize: none;
}
.box_item {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 30px;
    margin-bottom: 15px;
    border-bottom: 1px solid #eee;
}
.com-image {
    flex-shrink: 0; 
    width: 100px;
    height: 100px;
    margin-right: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.com-image img {
    width: 100%;
    height: auto;
}
.com-title {
	flex: 6;
}

.com-info {
	flex: 2;
	border-left: 2px solid #ccc;
	padding-left: 20px;
}

.support_detail {
	text-align: right;
	display: flex;
	flex-wrap: wrap;
	justify-content: flex-end;
}
.com-name {
	font-size: 1.1rem;
	color: #707070;
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
    padding: 3px 5px;
    color: #6777EE;
    text-decoration: none;
    border: 1px solid #6777EE;
    border-radius: 5px;
    background-color: #fff;
    transition: background-color 0.3s, color 0.3s;
    cursor: pointer; 
    display: inline-block;
    margin-bottom: 5px;
}
.ps-com {
    display: flex;
    flex-wrap: wrap;
    padding: 10px;
    border-bottom: 1px solid #eee;

}

.ps-com > div {
    flex: 1 1 50%;
    padding: 10px; 
    padding-left:20px;
}

.comment-section {
    flex-basis: 100%; 
    margin-top: 20px;
}

/*태그 추가*/
.add-tag {
    position: relative;
    max-width: 400px;
    margin: 20px auto;
    display: flex;
    align-items: center;
    border-radius: 20px;
    box-shadow: 0 2px 6px rgba(0,0,0,0.1);
    background: #fff;
}

.add-tag input[type="text"] {
    flex-grow: 1;
    border: none;
    padding: 10px 20px;
    border-radius: 20px 0 0 20px;
    font-size: 16px;
}
.add-tag input:focus {
	outline: none;
	border: none;
	box-shadow: none;
}
.add-tag button {
    border: none;
    background-color: #5569D2;
    color:#FFF;
    padding: 10px;
    cursor: pointer;
    outline: none;
    position: absolute;
    right: 0;
    border-radius: 0 20px 20px 0; 
    display: flex;
    align-items: center;
    justify-content: center;
}

.add-tag button:hover {
    background-color: #3656CB;
}


.tag-list {
    padding-left: 20px;
    max-width: 400px; 
    margin: 10px auto;
}

.tag-list .tag-keyword {
    display: inline-block;
    background-color: #E8E8E8;
    border-radius: 10px;
    padding: 5px 10px;
    margin: 5px 5px 5px 0;
    font-size: 14px;
    color: #333;
}
.stars {
    flex: 1 1 auto; 
    justify-content: center;
    display: flex; 
    font-size: 2.5rem; 
    margin: 0 2px;
    cursor: pointer;
    transition: color 0.2s ease-in-out;
}


.star:hover {
    color: #ffd700;
}

.stars::before {
    letter-spacing: 5px; 
    background: linear-gradient(90deg, #ffc107 0%, #e4e5e9 0%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    display: block;
    text-align: center;
}
.star {
    color: #CCC;
    cursor: pointer;
    font-size: 24px;
}

.star.rated {
    color: gold;
}

.half-rated:before {
    content: "\f5c0";
}
.fas {
    color: gold;
}

.far {
    color: #eee; 
}

.textAreaWrapper {
    position: relative;
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
}



.textLengthWrap {
    display: flex;
    align-items: center;
    gap: 5px; /* 자간 조정 */
}
    </style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp" %>
<body>
<div id="itvWriteContainer">
	<section class="page-hero pt-16 pb-6">
			<form method="post" action="/good/user/company/review/addreview.do">
		<div class="container">
			<div class="card" id="itvWriteQnA">
				<div class="card-content-wrapper">
					
                    <div class="px-4 text-center sizing">
                        <h1 class="mt-3 gradiTitle"><span>기업리뷰등록</span></h1>
                        <p class="mt-6">작성해 주신 기업리뷰는 다른 구직자분들께 큰 도움이 될 거예요</p>
                    </div>

                  <hr>
        	<div class="list_body">
							<div class="list_item">
							
							<!-- 기업정보start -->
						
								<div class="box_item mx-0 bg-white px-10">
    <!-- 추가된 이미지 컨테이너 -->
    <div class="com-image">
        <img src="${dto.image}" onerror="this.src='/good/asset/images/default.jpg'" alt="Company Logo">
    </div>

    <div class="com-title">
        <div class="col notification_info">
            <div class="job_tit">
                <h2><a class="str_tit_title new" href="#" target="_blank">
                    ${dto.cp_name}
                </a></h2>
            </div>
            <div class="job_meta">
                <span class="job-keyword"><b>위치 </b>${dto.cp_address}</span>
                <span class="job-keyword"><b>총매출액 </b>${dto.idst_name}</span>
                <span class="job-keyword"><b>평균연봉 </b>${dto.hire_avr_salary} 만원</span>
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
            <h3>별점 등록</h3>
             <div class="category">
				    <label>연봉</label>
				    <div class="stars" data-category="salary">
				        <span class="star fa-solid fa-star" data-value="1"></span>
				        <span class="star fa-solid fa-star" data-value="2"></span>
				        <span class="star fa-solid fa-star" data-value="3"></span>
				        <span class="star fa-solid fa-star" data-value="4"></span>
				        <span class="star fa-solid fa-star" data-value="5"></span> 
				    </div>
				    <input type="hidden" name="salary_score" value="" id="salary-highest">
			</div>
            <div class="category">
				    <label>복지</label>
				    <div class="stars" data-category="welfare">
				        <span class="star fa-solid fa-star" data-value="1"></span>
				        <span class="star fa-solid fa-star" data-value="2"></span>
				        <span class="star fa-solid fa-star" data-value="3"></span>
				        <span class="star fa-solid fa-star" data-value="4"></span>
				        <span class="star fa-solid fa-star" data-value="5"></span>
				    </div>
				    <input type="hidden" name="welfare_score" value="" id="welfare-highest">
				</div>
				
				<div class="category">
				    <label>조직안정성</label>
				    <div class="stars" data-category="stability">
				        <span class="star fa-solid fa-star" data-value="1"></span>
				        <span class="star fa-solid fa-star" data-value="2"></span>
				        <span class="star fa-solid fa-star" data-value="3"></span>
				        <span class="star fa-solid fa-star" data-value="4"></span>
				        <span class="star fa-solid fa-star" data-value="5"></span>
				    </div>
				    <input type="hidden" name="stability_score" value="" id="stability-highest">
				</div>
				
				<div class="category">
				    <label>조직문화</label>
				    <div class="stars" data-category="culture">
				        <span class="star fa-solid fa-star" data-value="1"></span>
				        <span class="star fa-solid fa-star" data-value="2"></span>
				        <span class="star fa-solid fa-star" data-value="3"></span>
				        <span class="star fa-solid fa-star" data-value="4"></span>
				        <span class="star fa-solid fa-star" data-value="5"></span>
				    </div>
				    <input type="hidden" name="culture_score" value="" id="culture-highest">
				</div>
				
				<div class="category">
				    <label>성장가능성</label>
				    <div class="stars" data-category="growth">
				        <span class="star fa-solid fa-star" data-value="1"></span>
				        <span class="star fa-solid fa-star" data-value="2"></span>
				        <span class="star fa-solid fa-star" data-value="3"></span>
				        <span class="star fa-solid fa-star" data-value="4"></span>
				        <span class="star fa-solid fa-star" data-value="5"></span>
				    </div>
				    <input type="hidden" name="growth_score" value="" id="growth-highest">
				</div>
				
        </div>
        <!-- 별점등록END -->
        
        
        
        
         <!-- com-tag section -->
        <div class="com-tag">
    <div class="click-tag">
        <h3>추천 태그</h3>
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
            <h3>한 줄 기업 평가</h3>
             <div class="textLengthWrap">
   				 <p class="lineCount">0자</p>
    			<p class="lineTotal">/30자</p>
  			</div>
  			</div>
            <textarea id="lineBox" name="linereview" maxlength="33" placeholder="기업에 대한 한줄평을 작성해주세요!"></textarea>
        </div>
        
        <div class="review-text comment-section">
            <div class="textAreaWrapper">
            <h3>이런 부분은 좋았어요</h3>
            <div class="textLengthWrap">
            <p class="goodCount">0자</p>
            <p class="goodTotal">/330자</p>
        </div>
    </div>
        <textarea id="textGood" name="good" maxlength="330" placeholder="해당 기업에서 근무하면서 좋았던 점을 작성해주세요!"></textarea>
        
    </div>
        <div class="review-text comment-section">
         <div class="textAreaWrapper">
            <h3>이런 부분은 아쉬웠어요</h3>
             <div class="textLengthWrap">
    <p class="badCount">0자</p>
    <p class="badTotal">/330자</p>
  </div>
  </div>
            <textarea id="textBad" name="bad" maxlength="330" placeholder="해당 기업에서 근무하면서 아쉬웠던 점을 작성해주세요!"></textarea>
        </div>
        <hr>
				<div class="submit-info">
                <p>면접 후기에 대한 저작권은 (주)사람인에 있습니다.<br> 등록하신 콘텐츠는 임의로 삭제하실 수 없습니다.</p>
                </div>
                <div class="moving-btn">
                            <a href="/good/user/company/companyview.do?cp_seq=${dto.cp_seq}" class="btn btnBefore">이전으로</a>
                            <button type="submit"  name="cp_rv_confirm" value="0" class="btn btnList submitRatings">등록하기</button>
                </div>
            </div>
        </div>   
         </form>
    </section>
   
</div>
<%@include file="/WEB-INF/views/inc/footer.jsp" %>
<script src="/good/assets/js/tagify.min.js"></script>
<script>
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


document.querySelectorAll('.category .stars').forEach(starsContainer => {
    starsContainer.addEventListener('click', function(e) {
        if (e.target.classList.contains('star')) {
            const starIndex = parseInt(e.target.getAttribute('data-value'), 10);
            let currentValue = e.target.dataset.currentValue ? parseFloat(e.target.dataset.currentValue) : 0;

            // Toggle star rating logic
            if (currentValue < starIndex - 0.5) {
                currentValue = starIndex - 0.5;
            } else if (currentValue < starIndex) {
                currentValue = starIndex;
            } else {
                currentValue = starIndex - 1; // Toggle down
            }

            e.target.dataset.currentValue = currentValue;
            updateStars(starsContainer, currentValue);

            // Update the highest value for the category
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

    // Correctly reference the corresponding hidden input
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