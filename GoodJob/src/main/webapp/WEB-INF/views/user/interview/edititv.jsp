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
	<section class="integration-single section pt-0">
		<div class="container">
			<div class="row justify-center">
				<div class="lg:col-10 mt-16">

					<div
						class="integration-single-container rounded-xl bg-white py-16 px-5 shadow-lg md:px-10 tab"
						data-tab-group="service-tab" id="itvWriteQnA">
						<form action="/good/editWrite.do" method="post">
							<div class="px-4 text-center">
								<h1 class="mt-3 gradiTitle">
									<span>면접 후기</span>
								</h1>
								<p class="mt-2">작성해주신 면접 후기는 익명으로 등록됩니다.</p>
							</div>
							<div class="mt-8 border-y border-border py-10 text-left">
								<div>
									<h5>기본 정보 입력</h5>

									<!-- 기업 검색창 -->
									<div class="view-form addqna">
										<div class="addqna-form">
											<div id="searchResults1" class="dropdown-content addqna"></div>
											<span id="write-company">기업</span> <input type="text"
												id="searchInputCompany" class="company" name="itvCpName"
												placeholder="🔍기업명을 입력하세요." value="${dto.ITV_CPNAME}"
												required>
											<!-- hidden 태그로 기업번호 보내기 -->
											<input type="hidden" id="cp_seq" value="${dto.CP_SEQ}"
												name="cp_seq">
										</div>
									</div>

									<!-- 여기까지 복사해서 쓰시면 됩니다 -->
								</div>
								<div class="space-btw mt-2">
									<div class="itvCpContent">
										<label class="itv-label">면접 당시 경력</label>
										<div class="form-group itvCpContent">
											<input type="radio" name="ITV_CAREER" id="new" value="신입"
												${dto.ITV_CAREER == '신입' ? 'checked' : ''}> <label
												for="new">신입</label> <input type="radio" name="ITV_CAREER"
												id="career" value="경력"
												${dto.ITV_CAREER == '경력' ? 'checked' : ''}> <label
												for="career">경력</label>
										</div>
									</div>
									<div class="itvCpContent">
										<label class="itv-label">면접 일자</label>
										<div class="form-group itvCpContent">
											<input type="date" name="ITV_MEETDATE"
												value="${dto.ITV_MEETDATE}" />
										</div>
									</div>
								</div>
							</div>
							<div class="border-b border-border py-10">
								<h5>면접 정보 입력</h5>
								<div class="itvCpContent">
									<label class="itv-label" for="name">전반적 평가</label>
									<div class="itv-toolbar">
										<input type="radio" id="opinion1" name="ITV_EVALUATION"
											value="긍정적" ${dto.ITV_EVALUATION == '긍정적' ? 'checked' : ''}>
										<label for="opinion1"> 긍정적</label> <input type="radio"
											id="opinion2" name="ITV_EVALUATION" value="보통"
											${dto.ITV_EVALUATION == '보통' ? 'checked' : ''}> <label
											for="opinion2">보통</label> <input type="radio" id="opinion3"
											name="ITV_EVALUATION" value="부정적"
											${dto.ITV_EVALUATION == '부정적' ? 'checked' : ''}> <label
											for="opinion3">부정적</label>
									</div>

								</div>
								<div class="itvCpContent">
									<label class="itv-label" for="name">난이도</label>
									<div class="itv-toolbar">
										<input type="radio" id="opinion4" name="ITV_DIFFICULTY"
											value="쉬움" ${dto.ITV_DIFFICULTY == '쉬움' ? 'checked' : ''}>
										<label for="opinion4">쉬움</label> <input type="radio"
											id="opinion5" name="ITV_DIFFICULTY" value="보통"
											${dto.ITV_DIFFICULTY == '보통' ? 'checked' : ''}> <label
											for="opinion5">보통</label> <input type="radio" id="opinion6"
											name="ITV_DIFFICULTY" value="어려움"
											${dto.ITV_DIFFICULTY == '어려움' ? 'checked' : ''}> <label
											for="opinion6">어려움</label>
									</div>

								</div>
								<div class="mt-6">
									<label class="itv-label" for="name">면접 및 전형 유형 필수 (다중
										선택 가능)</label>
									<div class="itvCpContent">
										<label> <input type="checkbox" name="ITV_CATEGORY"
											value="직무 및 인성면접"
											${dto.ITV_CATEGORY.contains('직무 및 인성면접') ? 'checked' : ''}>
											직무 및 인성면접
										</label> <label> <input type="checkbox" name="ITV_CATEGORY"
											value="PT면접"
											${dto.ITV_CATEGORY.contains('PT면접') ? 'checked' : ''}>
											PT면접
										</label> <label> <input type="checkbox" name="ITV_CATEGORY"
											value="토론면접"
											${dto.ITV_CATEGORY.contains('토론면접') ? 'checked' : ''}>
											토론면접
										</label> <label> <input type="checkbox" name="ITV_CATEGORY"
											value="실무 과제 및 시험"
											${dto.ITV_CATEGORY.contains('실무 과제 및 시험') ? 'checked' : ''}>
											실무 과제 및 시험
										</label> <label> <input type="checkbox" name="ITV_CATEGORY"
											value="인적성 검사"
											${dto.ITV_CATEGORY.contains('인적성 검사') ? 'checked' : ''}>
											인적성 검사
										</label> <label> <input type="checkbox" name="ITV_CATEGORY"
											value="기타"
											${dto.ITV_CATEGORY.contains('기타') ? 'checked' : ''}>
											기타
										</label>
									</div>
								</div>

								<div class="mt-6">
									<label class="itv-label" for="name">면접 인원 필수</label>
									<div class="itvCpContent">
										<label> <input type="radio" name="ITV_PERSONNEL"
											value="1:1 면접"
											${dto.ITV_PERSONNEL == '1:1 면접' ? 'checked' : ''}>
											1:1 면접
										</label> <label> <input type="radio" name="ITV_PERSONNEL"
											value="지원자 1명, 면접관다수"
											${dto.ITV_PERSONNEL == '지원자 1명, 면접관다수' ? 'checked' : ''}>
											지원자 1명, 면접관다수
										</label> <label> <input type="radio" name="ITV_PERSONNEL"
											value="그룹 면접"
											${dto.ITV_PERSONNEL == '그룹 면접' ? 'checked' : ''}> 그룹
											면접
										</label>
									</div>
								</div>
							</div>
							<div class="border-b border-border py-10">
								<h5>합격 정보 입력</h5>
								<div class="mt-6">
									<label class="itv-label" for="name">가장 기억에 남는 면접 질문</label>
									<div class="form-group mt-2">
										<form>
											<input class="form-control h-1" type="text"
												name="ITV_QUESTION" placeholder="ex) 회사를 선택한 이유"
												value="${dto.ITV_QUESTION}" />
										</form>
									</div>
								</div>
								<div class="form-group mt-6">
									<label class="itv-label" for="name">면접 TIP 및 특이 사항</label>
									<div class="form-group itvCpContent mt-2">
										<input class="form-control h-1" type="text" name="ITV_TIP"
											placeholder="분위기, 준비 TIP을 공유해주세요." value="${dto.ITV_TIP}" />
									</div>
								</div>
								<div class="form-group mt-6">
									<label class="itv-label" for="name">합격 여부</label>
									<div class="form-group itvCpContent">
										<div class="itv-toolbar">
											<input type="radio" id="pass" name="ITV_WHETHER" value="합격"
												${dto.ITV_WHETHER == '합격' ? 'checked' : ''}> <label
												for="pass">합격</label> <input type="radio" id="fail"
												name="ITV_WHETHER" value="불합격"
												${dto.ITV_WHETHER == '불합격' ? 'checked' : ''}> <label
												for="fail">불합격</label> <input type="radio" id="waiting"
												name="ITV_WHETHER" value="대기중"
												${dto.ITV_WHETHER == '대기중' ? 'checked' : ''}> <label
												for="waiting">대기중</label>

										</div>
									</div>
								</div>
							</div>
							<div class="text-center mt-4">
								<p>
									면접 후기에 대한 저작권은 IT's Good Job에 있습니다. <br>등록하신 콘텐츠는 임의로 삭제하실
									수 없습니다.
								</p>
								<div class="moving-btn">
									<button type="button" class="btn btnBefore"
										onclick="history.back()">이전으로</button>
									<button type="submit" class="btn btnList">수정하기</button>
								</div>
								<input type="hidden" name="itv_seq" value="${dto.itv_seq}" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>








	</section>
	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	//기업 검색 자동완성 js
	$(document).ready(function(){
		$('#searchInputCompany').keyup(function(){
			var input = $(this).val();
			console.log(input.length);
			
			if(input.length==0){
				$('#searchResults1').html("");
			} else {
			$.ajax({ 
				type: 'get',
				url : '/good/user/searchcompany.do',
				data : {query : input},
				success : function(result){ 
					$('#searchResults1').html("");
					result.forEach(dto =>{
						$('#searchResults1').append(`<div class="dropdown-item" data-cpseq="\${dto.cp_seq}">\${dto.cp_name}</div>`);
					});
				},
				error : function(){
					$('#searchResults1').append('<div class="dropdown-item">오류</div>');
				}
			});
			}
		});
		
		$('#searchResults1').on('click', '.dropdown-item', function() {
			$('#searchInputCompany').val($(this).text());
			$('#cp_seq').val($(this).data('cpseq'));
			$('#searchResults1').html("");
		});	
	})
	

	 $(document).ready(function() {
        $("#itvWriteBtn").click(function() {
            // 폼 데이터를 가져옵니다.
           	var cpName = $("input[name='ITV_CPNAME']").val();
            var career = $("input[name='ITV_CAREER']:checked").val();
            var meetDate = $("input[name='ITV_MEETDATE']").val();
            var evaluation = $("input[name='ITV_EVALUATION']:checked").val();
            var difficulty = $("input[name='ITV_DIFFICULTY']:checked").val();
            var categories = $("input[name='ITV_CATEGORY']:checked").length;
            var personnel = $("input[name='ITV_PERSONNEL']:checked").val();
            var question = $("input[name='ITV_QUESTION']").val();
            var tip = $("input[name='ITV_TIP']").val();
            var whether = $("input[name='ITV_WHETHER']:checked").val();
            var id = $("input[name=id]").val();

            // 사용자가 입력하지 않은 부분이 있으면 알림창을 띄웁니다.
            if (cpName === "" || career === undefined || meetDate === "" || evaluation === undefined ||
                difficulty === undefined || categories === 0 || personnel === undefined || question === "" ||
                tip === "" || whether === undefined) {
                alert("모든 정보를 입력해주세요.");
            } else {
                // 모든 정보를 입력한 경우에만 폼을 제출합니다.
                $("form").submit();
            }
        });
    });
	</script>
</body>
</html>