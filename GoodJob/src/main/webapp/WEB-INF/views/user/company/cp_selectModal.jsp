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

.report-reasons label {
	display: block;
	margin-bottom: 20px;
	color: #646B7F;
}

.actions {
	text-align: center;
	margin-top: 20px;
}

button {
	padding: 6px 12px;
}

.cancel-btn {
	position: absolute;
	top: 10px;
	right: 10px;
	font-size: 40px;
	background: none;
	border: none;
	cursor: pointer;
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

.modal {
	display: none;
}

#searchForm {
	width: 100%;
}

#company {
	width: 100%;
	border: none;
	display: inline-flex;
}

#cp_img {
	width: 20%;
	border: 1px solid #4444;
	border-radius: 10px;
	padding: 2px;
	margin: 0px 15px;
}

#cp_name {
	font-family: Pretendard-Regular;
	font-size: 1.1rem;
	font-weight: bold;
    margin-bottom: 0 !important;
    padding: 0 !important;
}

#company {
	margin: 2px auto;
}

#cp_industry {
	width: 100%;
	font-family: Pretendard-Regular;
	font-size: .8rem;
	margin-top: none;
	padding: none;
}

#cp_info{
	display: block;
		width: 100%;
}
#check {
	display: flex;
	padding: 10px 0;
}

#check input[type="checkbox"] {
    margin: 10px;
}

</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
	<div class="modal" id="reportModal">
		<div class="modal-content">
			<button class="cancel-btn">&times;</button>
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

			<div class="actions">
				<button class="report-btn">비교하기</button>
			</div>
		</div>
		</form>
	</div>
	</div>


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
</script>

</body>
</html>