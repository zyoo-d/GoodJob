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
						<div class="px-4 text-center">
							<h1 class="mt-3 gradiTitle">
								<span>면접 후기</span>
							</h1>
							<p class="mt-2">작성해주신 면접 후기는 익명으로 등록됩니다.</p>
						</div>
						<div class="mt-8 border-y border-border py-10 text-left">
							<div>
								<h5>기본 정보 입력</h5>
								<div class="form-group mb-5 mt-2 itvCpContent">
									<label class="itv-label" for="name">회사명</label> <input
										class="form-control itvCpname" type="text"
										placeholder="회사명을 입력해주세요." /> <input
										class="btn btn-primary block btn-sm" type="submit" value="검색" />
								</div>
							</div>
							<div>
								<label class="itv-label">선호직무</label>
								<div class="optionJob mt-2">
									<dl class="row_item">
										<dt class="category-list">
											<p>
												직무·직업<span class="toggle-button"
													onclick="toggleTagList(event)">+</span>
											</p>
										</dt>
										<dd class="tag-group">
											<input type="checkbox" id="tag1" value="데이터엔지니어"> <label
												for="tag1">데이터엔지니어</label> <input type="checkbox" id="tag2"
												value="프론트엔드"> <label for="tag2">프론트엔드</label>
										</dd>
									</dl>

									<dl class="row_item">
										<dt class="category-list">
											<p>
												전문분야<span class="toggle-button"
													onclick="toggleTagList(event)">+</span>
											</p>
										</dt>
										<dd class="tag-group">
											<input type="checkbox" id="tag3" value="자바"> <label
												for="tag3">자바</label> <input type="checkbox" id="tag4"
												value="AWS"> <label for="tag4">AWS</label> <input
												type="checkbox" id="tag5" value="Python"> <label
												for="tag5">Python</label>
										</dd>
									</dl>

									<dl class="row_item">
										<dt class="category-list">
											<p>
												기술스택<span class="toggle-button"
													onclick="toggleTagList(event)">+</span>
											</p>
										</dt>
										<dd class="tag-group">
											<input type="checkbox" id="tag6" value="React"> <label
												for="tag6">React</label> <input type="checkbox" id="tag7"
												value="웹개발"> <label for="tag7">웹개발</label> <input
												type="checkbox" id="tag8" value="ABAP"> <label
												for="tag8">ABAP</label>
										</dd>
									</dl>
								</div>
								<div class="tag-group">
									<span id="lctag"> </span> <span class="clear"
										onclick="clearLocations()"><i
										class="fa-solid fa-rotate-right"></i> 지역 초기화</span>
								</div>
							</div>
							<div class="space-btw mt-2">
								<div class="itvCpContent">
									<label class="itv-label">면접 당시 경력</label>
									<div class="form-group itvCpContent">
										<input type="radio" name="experience" id="new"> <label
											for="new">신입</label> <input type="radio" name="experience"
											id="career"> <label for="career">경력</label>
									</div>
								</div>
								<div class="itvCpContent">
									<label class="itv-label">면접 일자</label>
									<div class="form-group itvCpContent">
										<select>
											<option>연도 선택</option>
											<option>2021</option>
											<option>2022</option>
											<option>2023</option>
											<option>2024</option>
										</select> <select>

											<option>월 선택</option>
											<option value="1">1월</option>
											<option value="2">2월</option>
											<option value="3">3월</option>
											<option value="4">4월</option>
											<option value="5">5월</option>
											<option value="6">6월</option>
											<option value="7">7월</option>
											<option value="8">8월</option>
											<option value="9">9월</option>
											<option value="10">10월</option>
											<option value="11">11월</option>
											<option value="12">12월</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="border-b border-border py-10">
							<h5>면접 정보 입력</h5>
							<div class="itvCpContent">
								<label class="itv-label" for="name">전반적 평가</label>
								<div class="itv-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">긍정적</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">보통</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">부정적</label>
								</div>

							</div>
							<div class="itvCpContent">
								<label class="itv-label" for="name">난이도</label>
								<div class="itv-toolbar">
									<input type="radio" id="opinion1" name="opinion" value="1">
									<label for="opinion1">쉬움</label> <input type="radio"
										id="opinion2" name="opinion" value="2"> <label
										for="opinion2">보통</label> <input type="radio" id="opinion3"
										name="opinion" value="3"> <label for="opinion3">어려움</label>
								</div>

							</div>
							<div class="mt-6">
								<label class="itv-label" for="name">면접 및 전형 유형 필수 (다중 선택
									가능)</label>
								<div class="itvCpContent">
									<label> <input type="radio" name="option" checked>
										직무 및 인성면접
									</label> <label> <input type="radio" name="option">
										PT면접
									</label> <label> <input type="radio" name="option">
										토론면접
									</label> <label> <input type="radio" name="option" checked>
										실무 과제 및 시험
									</label> <label> <input type="radio" name="option"> 인적성
										검사
									</label> <label> <input type="radio" name="option"> 기타
									</label>
								</div>
							</div>
							<div class="mt-6">
								<label class="itv-label" for="name">면접 인원 필수 (다중 선택 가능)</label>
								<div class="itvCpContent">
									<label> <input type="radio" name="option" checked>
										1:1 면접
									</label> <label> <input type="radio" name="option"> 지원자
										1명, 면접관다수
									</label> <label> <input type="radio" name="option"> 그룹
										면접
									</label>
								</div>



							</div>
							<div class="mt-6">
								<label class="itv-label" for="name">전형 및 면접 진행방식</label>
								<div class="form-group itvCpContent">
									<input class="form-control h-1" type="text"
										placeholder="서류 합격 후 어떤 전형과 면접을 경험하셨나요?" />
								</div>


							</div>
						</div>


						<div class="border-b border-border py-10">

							<h5>합격 정보 입력</h5>
							<div class="mt-6">
								<label class="itv-label" for="name">면접 질문(최소 2개)</label>
								<div class="form-group mt-2">
									<form>
										<input class="form-control h-1" type="text"
											placeholder="ex) 회사를 선택한 이유" /> <input
											class="form-control h-1" type="text"
											placeholder="ex) 경력에 대한 상세한 질문" /> <input
											class="form-control h-1" type="text"
											placeholder="ex) 회사를 선택한 이유" />
									</form>
								</div>
							</div>
							<div class="form-group mt-6">
								<label class="itv-label" for="name">면접 TIP 및 특이 사항</label>
								<div class="form-group itvCpContent mt-2">
									<input class="form-control h-1" type="text"
										placeholder="분위기, 준비 TIP을 공유해주세요." />
								</div>
							</div>
							<div class="form-group mt-6">
								<label class="itv-label" for="name">합격 여부</label>
								<div class="form-group itvCpContent">
									<div class="itv-toolbar">
										<input type="radio" id="opinion1" name="opinion" value="1">
										<label for="opinion1">합격</label> <input type="radio"
											id="opinion2" name="opinion" value="2"> <label
											for="opinion2">불합격</label> <input type="radio" id="opinion3"
											name="opinion" value="3"> <label for="opinion3">대기중</label>
									</div>

								</div>
							</div>
						</div>
						<div class="text-center mt-4">
							<p>
								면접 후기에 대한 저작권은 IT's Good Job에 있습니다. <br>등록하신 콘텐츠는 임의로 삭제하실
								수 없습니다.
							</p>
							<input class="btn btn-primary mt-4" type="submit" value="등록" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
		
	</script>
</body>
</html>