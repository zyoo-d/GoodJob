<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
.modal {
	position: fixed;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	display: flex;
	justify-content: center;
	align-items: center;
}

.modal-content {
	position: relative;
	background-color: white;
	padding: 40px;
	border-radius: 4px;
	width: 400px;
	max-height: calc(100vh - 100px);
	overflow-y: auto;
}

h2 {
	margin-top: 0;
	padding-bottom: 10px;
	border-bottom: 1px solid black;
	margin-bottom: 20px;
}

.actions {
	text-align: center;
	margin-top: 20px;
}

button {
	padding: 6px 12px;
}


.report-btn {
	width: 60%;
	height: 50px;
	padding: 10px;
	background-color: #353535;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
}


#searchForm {
	width: 80%;
}

#company {
/* 	width: 100%; */
	border: none;
	margin: 10px 0;
	display: flex;
}
#compare {
	display: flex;
}
#check {
	flex: 0 1 10px;
	display: flex;
	flex-direction: column;
	margin-right: 0;
	width: 10px;
	margin: 0;
	padding: 0;
}
#right {
	flex: 1;
}


#cp_img {
	width: 60px;
	border: 1px solid #4444;
	border-radius: 10px;
	padding: 2px;
	margin: 0px 15px;
}

#cp_name {
	font-family: Pretendard-Regular;
	font-size: 1.1rem;
	font-weight: bold;
}


#cp_industry {
/* 	width: 100%; */
	font-family: Pretendard-Regular;
	font-size: .8rem;
	margin-top: none;
	padding: none;
}

#cp_info{
	display: block;
		width: 100%;
}

#check input[type="checkbox"] {
    margin: 22px  0;
    border-radius: 4px;
    height: 16px;
    width: 16px;
}

</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>

			<h4>기업 선택하기</h4>

			<div id="searchForm">
				<input type="text" name="input" class="input" id="search-input"
					placeholder="기업명으로 검색하세요.">
				<button type="button" class="search" id="search-btn">
					<span class="material-symbols-outlined"> <span
						class="material-symbols-outlined">search</span>
					</span>
				</button>
			</div>
			<form action="/good/user/company/comparecompany.do">
			<div>
			<div id="cp_selected">
			<span id="cptag"></span>
			</div>

			<div id="compare">
				<div id="check">
				<c:forEach items="${comListInfo}" var="dto">
						<input type="checkbox" name="" value="${dto.cp_name}" id="ssang" />
						</c:forEach>
				</div>
				<div id="right">
				<c:forEach items="${comListInfo}" var="dto">
				<div id="company">
					<div id="cp_img">
						<img src="images/naver.jpg" alt="" />
					</div>
					<div id="cp_info">
						<div id="cp_name">${dto.cp_name}</div>
						<div id="cp_industry">${dto.cp_address}</div>
					</div>
				</div>
				</c:forEach>
				</div>
			</div>
			
		<nav class="PageBox z-custom" aria-label="Page navigation example">
			<ul class="pagination z-custom">${pagebar}</ul>
		</nav>

			<div class="actions">
				<button class="report-btn">비교하기</button>
			</div>
		</div>
		</form>



	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	function clearLocations() {
		$('#locationCheckboxes').html('');
		$('#lctag').html('');
	}

	//지역 체크시 태그 생성/삭제
	$('#check').on(	'change', 'input[type="checkbox"]',
					function() {
						var target = $(this);
						var cptag = $('#cptag');

						if (cptag.children().length >= 6
								&& target.is(':checked')) {
							alert('3개 이상 선택할 수 없습니다.');
							target.prop('checked', false);
							return;
						}

						if (target.is(':checked')) {
							var tagname = target.val();
							var tagId = target.val();

							cptag.append('<input type="checkbox" name="compareCp" checked id="' + tagId + '" value="' + tagname + '">'
											+ '<label for="' + tagId + '">'
											+ tagname
											+ ' <i class="fa-solid fa-xmark"></i></label>');
						} else {
							var tagId = target.val();
							$('#' + tagId).next().addBack().remove();
						}
					});

	//지역 태그 눌러서 삭제하기
	$('#cptag').on('change', 'input[type="checkbox"]', function() {
		var checkboxId = $(this).attr('id');

		$('#company input[type="checkbox"]').each(function() {
			var locationCheckbox = $(this);
			if (locationCheckbox.val() === checkboxId) {
				locationCheckbox.prop('checked', false);
			}
		});
		$(this).next().addBack().remove();
	});
	
	function goToPage(pageNumber) {
	    var hiring = (document.getElementById("hiring").checked ? "y" : "n"); // 고용 여부 체크 여부에 따라 값 설정
	    var word = document.getElementById("search-input").value; // 검색어 가져오기

	    $.ajax({
	        url: "/good/user/company/cp_selectModal.do",
	        type: "GET",
	        data: {
	            page: pageNumber,
	            hiring: hiring,
	            word: word
	        },
	        success: function(data) {
	            // 서버에서 받은 데이터를 사용하여 모달 창의 내용 업데이트
	            $(".modal-content").html(data);
	        },
	        error: function(xhr, status, error) {
	            console.error("Error: " + status + ", Message: " + error);
	        }
	    });
	}
	
	// 페이지 번호를 클릭했을 때 호출되는 함수
	function goToPage(pageNumber) {
	    // AJAX 요청을 보내기 전에 페이지 번호와 필요한 데이터를 설정합니다.
	    var hiring = (document.getElementById("hiring").checked ? "y" : "n"); // 고용 여부 체크 여부에 따라 값 설정
	    var word = document.getElementById("search-input").value; // 검색어 가져오기

	    // AJAX 요청을 보냅니다.
	    $.ajax({
	        url: "/good/user/company/cp_selectModal.do",
	        type: "GET",
	        data: {
	            page: pageNumber,
	            hiring: hiring,
	            word: word
	        },
	        success: function(data) {
	            // 서버에서 받은 데이터로 모달 창 내의 콘텐츠를 업데이트합니다.
	            $(".modal-content").html(data);
	        },
	        error: function(xhr, status, error) {
	            console.error("Error: " + status + ", Message: " + error);
	        }
	    });
	}

</script>

</body>
</html>