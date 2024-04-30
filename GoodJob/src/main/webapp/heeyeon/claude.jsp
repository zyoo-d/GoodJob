<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>직무/직업별 태그 선택</title>
<style>
.welfare-select-container {
  display: flex;
  flex-direction: column;
}

.welfare-select-row {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.welfare-select-col {
  flex: 1;
  margin-right: 20px;
  border: 1px solid #ccc;
  padding: 10px;
  max-height: 100px;
  overflow-y: auto;
}

.welfare-select-col:last-child {
  margin-right: 0;
}

.welfare-select-col h3 {
  margin-top: 0;
}

.chk_list {
  list-style-type: none;
  padding: 0;
}

.chk_item {
  margin-bottom: 5px;
}
</style>
</head>
<body>
	<div class="welfare-select-container">
  <div class="welfare-select-row">
    <div class="welfare-select-col">
      <h3>지원금/보험</h3>
      <ul class="chk_list">
        <!-- 체크박스 리스트 -->
      </ul>
    </div>
    <div class="welfare-select-col">
      <h3>급여제도</h3>
      <ul class="chk_list">
        <!-- 체크박스 리스트 -->
      </ul>
    </div>
    <div class="welfare-select-col">
      <h3>선물</h3>
      <ul class="chk_list">
        <!-- 체크박스 리스트 -->
      </ul>
    </div>
    <div class="welfare-select-col">
      <h3>교육/생활</h3>
      <ul class="chk_list">
        <li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp407"
														name="welfare_cd[]" value="corp407"
														data-description="건강검진" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp407">건강검진</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp408"
														name="welfare_cd[]" value="corp408"
														data-description="의료비지원(본인)" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp408">의료비지원(본인)</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp409"
														name="welfare_cd[]" value="corp409"
														data-description="금연수당" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp409">금연수당</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp411"
														name="welfare_cd[]" value="corp411"
														data-description="직원대출제도" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp411">직원대출제도</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp417"
														name="welfare_cd[]" value="corp417"
														data-description="각종 경조사 지원" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp417">각종 경조사
															지원</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp419"
														name="welfare_cd[]" value="corp419"
														data-description="단체 상해보험" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp419">단체
															상해보험</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp439"
														name="welfare_cd[]" value="corp439"
														data-description="의료비지원(가족)" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp439">의료비지원(가족)</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp440"
														name="welfare_cd[]" value="corp440"
														data-description="체력단련실운영" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp440">체력단련실운영</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp441"
														name="welfare_cd[]" value="corp441"
														data-description="헬스비 지급" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp441">헬스비 지급</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp442"
														name="welfare_cd[]" value="corp442"
														data-description="무료진료지정병원" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp442">무료진료지정병원</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp486"
														name="welfare_cd[]" value="corp486"
														data-description="본인학자금" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp486">본인학자금</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp487"
														name="welfare_cd[]" value="corp487"
														data-description="업무활동비 지급" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp487">업무활동비
															지급</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp488"
														name="welfare_cd[]" value="corp488"
														data-description="문화생활비" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp488">문화생활비</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp489"
														name="welfare_cd[]" value="corp489"
														data-description="통신비 지원" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp489">통신비 지원</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp490"
														name="welfare_cd[]" value="corp490"
														data-description="결혼준비 지원" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp490">결혼준비
															지원</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp491"
														name="welfare_cd[]" value="corp491"
														data-description="해외여행 지원" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp491">해외여행
															지원</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp492"
														name="welfare_cd[]" value="corp492"
														data-description="선택적복리후생" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp492">선택적복리후생</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp495"
														name="welfare_cd[]" value="corp495"
														data-description="복지카드/포인트" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp495">복지카드/포인트</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp496"
														name="welfare_cd[]" value="corp496"
														data-description="난임 치료 지원" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp496">난임 치료
															지원</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp497"
														name="welfare_cd[]" value="corp497"
														data-description="주요 제품 직원 할인"
														data-pass-onload-refresh="y"> <label class="lbl"
														for="welfare_cd_corp497">주요 제품 직원 할인</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp498"
														name="welfare_cd[]" value="corp498"
														data-description="자녀학자금" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp498">자녀학자금</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp534"
														name="welfare_cd[]" value="corp534"
														data-description="사내 결혼식장 제공" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp534">사내
															결혼식장 제공</label>
												</span></li>
												<li class="chk_item"><span class="inpChk small">
														<input type="checkbox" id="welfare_cd_corp537"
														name="welfare_cd[]" value="corp537"
														data-description="내일채움공제" data-pass-onload-refresh="y">
														<label class="lbl" for="welfare_cd_corp537">내일채움공제</label>
												</span></li>
      </ul>
    </div>
    <div class="welfare-select-col">
      <h3>근무 환경</h3>
      <ul class="chk_list">
        <!-- 체크박스 리스트 -->
      </ul>
    </div>
  </div>
  <div class="welfare-select-row">
    <div class="welfare-select-col">
      <h3>조직문화</h3>
      <ul class="chk_list">
        <!-- 체크박스 리스트 -->
      </ul>
    </div>
    <div class="welfare-select-col">
      <h3>출퇴근</h3>
      <ul class="chk_list">
        <!-- 체크박스 리스트 -->
      </ul>
    </div>
    <div class="welfare-select-col">
      <h3>리프레시</h3>
      <ul class="chk_list">
        <!-- 체크박스 리스트 -->
      </ul>
    </div>
  </div>
</div>
</body>
</html>