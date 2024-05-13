<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

	margin-left: 5px;
}
#right {
	flex: 1;
}


#cp_img {
	width: 60px;
	border: 1px solid #4444;
	border-radius: 10px;
	padding: 5px;
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
    margin: 23px  0;
    border-radius: 4px;
    height: 16px;
    width: 16px;
}

</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>

			<h4>기업 선택하기</h4>

			<form id="searchForm">
				<input type="text" name="word" class="input" id="search-input"
					placeholder="${totalCount }">
				<button type="submit" class="search" id="search-btn">
					<span class="material-symbols-outlined"> <span
						class="material-symbols-outlined">search</span>
					</span>
				</button>
			</form>
			
			<form action="/good/user/company/comparecompany.do" method="GET">
			<div>
			<div id="cp_selected">
<div id="cptag">
    <c:forEach items="${selectedCp}" var="companyId">
        <c:forEach items="${comListInfo}" var="dto">
            <c:if test="${dto.cp_seq == companyId}">
                <input type="checkbox" name="compareCp" checked id="${dto.cp_seq}" value="${dto.cp_seq}">
                <label for="${dto.cp_seq}">
                    ${dto.cp_name}
                    <i class="fa-solid fa-xmark"></i>
                </label>
            </c:if>
        </c:forEach>
    </c:forEach>
</div>

			<div id="compare">
    <div id="check">
        <c:forEach items="${comListInfo}" var="dto">
            <input type="checkbox" name="compareCp" value="${dto.cp_seq}" ${fn:contains(selectedCp, dto.cp_seq) ? 'checked' : ''}/>
        </c:forEach>
    </div>
				<div id="right">
				<c:forEach items="${comListInfo}" var="dto">
				<div id="company">
					<div id="cp_img">
						<img src="${dto.image}" alt="Company Logo" style="width: 40px; height: 40px;" />
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
				<button type="submit" class="report-btn" onclick="closePopupAndRedirect()">비교하기</button>
			</div>
		</div>
		</form>



	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	function closePopupAndRedirect() {
	    // 체크된 항목의 값을 담을 배열 생성
	    var selectedItems = [];

	    // 체크된 항목을 배열에 추가
	    $('#check input[type="checkbox"]:checked').each(function() {
	        selectedItems.push($(this).val());
	    });

	    // 선택된 항목이 없다면 경고 메시지 출력 후 함수 종료
	    if (selectedItems.length === 0) {
	        alert("선택된 항목이 없습니다.");
	        return;
	    }

	    // 선택된 항목들을 URL에 추가하여 링크 생성
	    var compareUrl = "/good/user/company/comparecompany.do?";
	    for (var i = 0; i < selectedItems.length; i++) {
	        compareUrl += "compareCp=" + encodeURIComponent(selectedItems[i]);
	        if (i !== selectedItems.length - 1) {
	            compareUrl += "&";
	        }
	    }

	    // 링크로 이동
	    opener.window.location = compareUrl;
	    close();
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
	
	
	
// 	$(document).ready(function() {
// 	    // 페이지 이동 시 선택된 항목을 URL에 추가하여 서버로 전송
// 	    $('a.page-link').click(function(event) {
// 	        event.preventDefault();
// 	        var nextPage = $(this).attr('href');
	        
// 	        // 체크된 항목의 값을 담을 배열 생성
// 	        var selectedItems = [];
// 	        $('#check input[type="checkbox"]:checked').each(function() {
// 	            selectedItems.push($(this).val());
// 	        });

// 	        // 선택된 항목을 쿼리 문자열로 추가
// 	        if (selectedItems.length > 0) {
// 	            nextPage += (nextPage.indexOf('?') === -1 ? '?' : '&') + 'compareCp=' + encodeURIComponent(selectedItems.join(','));
// 	        }
	        
// 	        // 페이지 이동
// 	        window.location.href = nextPage;
// 	    });

// 	    // 페이지 로드 시 선택된 항목을 체크박스에 반영
// 	    var selectedItems = "${selectedCp}";
// 	    if (selectedItems) {
// 	        selectedItems = selectedItems.split(',');
// 	        selectedItems.forEach(function(item) {
// 	            $('#check input[type="checkbox"][value="' + item + '"]').prop('checked', true);
// 	        });
// 	    }
// 	});

// function sendCheckboxDataToServer() {
//     var queryData = $('#check input[type="checkbox"]:checked').serialize();

//     $.ajax({
//         url: '/good/user/company/cp_selectModal.do',
//         type: 'GET',
//         data: queryData,
//         success: function(response) {
//             // 서버로부터의 응답 처리
//             console.log('Success:', response);
//         },
//         error: function(xhr, status, error) {
//             // 오류 처리
//             console.error('Error:', error);
//         }
//     });
// }

    $(document).ready(function() {
        // 페이지 이동 시 선택된 항목을 URL에 추가하여 서버로 전송
        $('a.page-link').click(function(event) {
            event.preventDefault();
            var nextPage = $(this).attr('href');
            
            // 체크된 항목의 값을 담을 배열 생성
            var selectedItems = [];
            $('#check input[type="checkbox"]:checked').each(function() {
                selectedItems.push($(this).val());
            });

            // 선택된 항목을 쿼리 문자열로 추가
            if (selectedItems.length > 0) {
                nextPage += (nextPage.indexOf('?') === -1 ? '?' : '&') + 'compareCp=' + encodeURIComponent(selectedItems.join(','));
            }
            
            // 페이지 이동
            window.location.href = nextPage;
        });

        // 페이지 로드 시 선택된 항목을 체크박스에 반영
        var selectedItems = "${selectedCp}";
        if (selectedItems) {
            selectedItems = selectedItems.split(',');
            selectedItems.forEach(function(item) {
                $('#check input[type="checkbox"][value="' + item + '"]').prop('checked', true);
            });
        }
    });




	



</script>

</body>
</html>