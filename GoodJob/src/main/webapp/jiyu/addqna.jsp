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

	<section class="page-hero pt-16 pb-6">
		<div class="container">
			<div class="card" id="itvWriteQnA">
				<div class="card-content-wrapper">
					<div class="card-content">
						<div class="px-4 text-center">
							<h1 class="mt-3 gradiTitle">
								<span>여기어때</span>
							</h1>
							<p class="recruit-list mt-6">기업에 대해 궁금한 점을 자유롭게 물어보세요.</p>
						</div>

                       


                        <div id="write-form">
                            <div class="form-group mt-8">
                                <div class="view-form addqna">
                                    <div class="addqna-form">
                                    	<span id="write-title">제목</span>
                                        <input type="text" class="title write-title" placeholder="✏️제목을 입력하세요.">
                                    </div>
                                    <div class="addqna-form">
                                    	<span id="write-company">기업</span>
                                        <input type="text" id="searchInputCompany" class="company" placeholder="🔍기업명을 입력하세요.">
                                        <div id="searchResults1" class="dropdown-content addqna"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="textarea-group">
                                <textarea cols="30" rows="10" placeholder="내용을 입력하세요."></textarea>
                            </div>
                        </div>
                        
                    </div>
                </div>
                
                <div class="moving-btn">
                            <a href="#" class="btn btnBefore">이전으로</a>
                            <a href="#" class="btn btnList">등록하기</a>
                </div>
            </div>
        </div>
    </section>

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
<script>
    function fetchRelatedContent(searchTerm, dropdownId) {
        const relatedContent = [
            "Related Content 1",
            "Related Content 2",
            "Related Content 3"
        ];

        document.getElementById(dropdownId).innerHTML = '';

        relatedContent.forEach(content => {
            const item = document.createElement('div');
            item.classList.add('dropdown-item');
            item.textContent = content;
            item.addEventListener('click', () => {
                document.getElementById(searchTerm).value = content;
                document.getElementById(dropdownId).innerHTML = '';
            });
            document.getElementById(dropdownId).appendChild(item);
        });
    }

    document.getElementById('searchInputCompany').addEventListener('input', function(event) {
        const searchTerm = event.target.value.trim();
        const dropdownId = 'searchResults1';
        if (searchTerm.length > 0) {
            fetchRelatedContent('searchInputCompany', dropdownId);
        } else {
            document.getElementById(dropdownId).innerHTML = '';
        }
    });

    document.addEventListener('click', function(event) {
        const dropdown1 = document.getElementById('searchResults1');
        if (!event.target.matches('#searchInputCompany')) {
            dropdown1.innerHTML = '';
        }
    });
</script>
</body>
</html>