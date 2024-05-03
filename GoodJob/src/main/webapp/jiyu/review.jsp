<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="/WEB-INF/views/inc/asset.jsp" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
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

.stars {
    flex: 1 1 auto; 
    justify-content: center;
    display: flex; 
    font-size: 2.5rem; 
    margin: 0 2px;
    cursor: pointer;
    transition: color 0.2s ease-in-out;
}


.stars:hover {
    color: #ffd700;
}

.stars::before {
    content: '★★★★★';
    letter-spacing: 5px; 
    background: linear-gradient(90deg, #ffc107 0%, #e4e5e9 0%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    display: block;
    text-align: center;
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
        border-top: 1px solid #eee;
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

    </style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp" %>
<body>
<div id="itvWriteContainer">
	<section class="page-hero pt-16 pb-6">
		<div class="container">
			<div class="card" id="itvWriteQnA">
				<div class="card-content-wrapper">
					
                    <div class="px-4 text-center sizing">
                        <h1 class="mt-3 gradiTitle"><span>기업리뷰등록</span></h1>
                        <p class="mt-6">작성해 주신 기업리뷰는 다른 구직자분들께 큰 도움이 될 거예요</p>
                    </div>

                  
        	<div class="list_body">
							<div class="list_item">
							
							<!-- 채용공고start -->
						
								<div class="box_item mx-0 bg-white px-10">
    <!-- 추가된 이미지 컨테이너 -->
    <div class="com-image">
        <img src="company-logo.png" alt="Company Logo">
    </div>

    <div class="com-title">
        <div class="col notification_info">
            <div class="job_tit">
                <h2><a class="str_tit_title new" href="#" target="_blank">
                    네이버
                </a></h2>
            </div>
            <div class="job_meta">
                <span class="job-keyword"><b>위치 </b>서울 서초구</span>
                <span class="job-keyword"><b>총매출액 </b></span>
                <span class="job-keyword"><b>평균연봉 </b>5,000만원</span>
            </div>
        </div>
    </div>

</div>
								<!-- 채용공고end -->
								</div>
								</div>
		<div class="ps-com">
        <!-- Rating section -->
        <div class="rating-section">
            <h3>별점 등록</h3>

            <div class="category">
                <label>연봉</label>
                <div class="stars" data-category="salary"></div>
            </div>
            <div class="category">
                <label>복지</label>
                <div class="stars" data-category="welfare"></div>
            </div>
                        <div class="category">
                <label>근무안정성</label>
                <div class="stars" data-category="retention"></div>
            </div>
            <div class="category">
                <label>조직문화</label>
                <div class="stars" data-category="culture"></div>
            </div>
            <div class="category">
                <label>성장가능성</label>
                <div class="stars" data-category="growth"></div>
            </div>
        </div>
         <!-- com-tag section -->
        <div class="com-tag">
    <div class="click-tag">
        <h3>추천 태그</h3>
        <p class="tag-info mt-6">클릭하면 태그로 바로 등록!</p>
        <div class="tag_meta">
            <span class="tag-keyword">복지가 좋은</span>
            <span class="tag-keyword">야근이 많음</span>
            <span class="tag-keyword">오지마</span>
            <span class="tag-keyword">연령대높음</span>
            <span class="tag-keyword">부서바이부서</span>
            <span class="tag-keyword">성장하고있음</span>
        </div>
    </div>
    <div class="add-tag-text">
        <h3>태그 추가</h3>
        <p class="tag-info mt-6">추천 태그가 마음에 들지 않다면?</p>
        <div class="add-tag">
            <input type="text" id="new-tag" placeholder="추가하실 태그를 입력하세요">
            <button type="button" onclick="addTag()">추가</button>
        </div>
        <div class="tag-list tag_meta">
    <!-- Dynamically added tags will go here -->
</div>
        </div>

    </div>



        
        </div>
        </div>
        <!-- Comment section -->
        <div class="review-text comment-section">
            <h3>한 줄 기업 평가</h3>
            <textarea name="" placeholder="기업에 대한 한줄평을 작성해주세요!"></textarea>
        </div>
        <div class="review-text comment-section">
            <h3>이런 부분은 좋았어요</h3>
            <textarea name="" placeholder="해당 기업에서 근무하면서 좋았던 점을 작성해주세요!"></textarea>
        </div>
        <div class="review-text comment-section">
            <h3>이런 부분은 아쉬웠어요</h3>
            <textarea name="" placeholder="해당 기업에서 근무하면서 아쉬웠던 점을 작성해주세요!"></textarea>
        </div>
        
				<div class="submit-info">
                <p>면접 후기에 대한 저작권은 (주)사람인에 있습니다.<br> 등록하신 콘텐츠는 임의로 삭제하실 수 없습니다.</p>
                </div>
                <div class="moving-btn">
                            <a href="#" class="btn btnBefore">이전으로</a>
                            <a href="#" class="btn btnList">등록하기</a>
                </div>
            </div>
        </div>
     
        
    </section>
</div>
<%@include file="/WEB-INF/views/inc/footer.jsp" %>
<script>
function addTag() {
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
}
    

</script>
</body>
</html>