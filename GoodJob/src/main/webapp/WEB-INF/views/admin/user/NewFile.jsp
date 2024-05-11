<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/WEB-INF/views/inc/adminasset.jsp"%>
</head>

<body>
	<div id="app">
		<div class="main-wrapper main-wrapper-1">
			<%@include file="/WEB-INF/views/inc/adminheader.jsp"%>

			<!-- Main Content -->
			<div class="main-content">
				<section class="section">
					<div class="section-header">
						<h1>신고 접수 관리</h1>
					</div>

					<div class="section-body">
						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h4>차단 유저 목록</h4>
										<div class="card-header-form">
											<form>
												<div class="input-group">
													<input type="text" class="form-control"
														placeholder="검색어를 입력하세요.">
													<div class="input-group-btn">
														<button class="btn btn-primary">
															<i class="fas fa-search"></i>
														</button>
													</div>
													<button id="btn-block-alluser"
														class="btn btn-icon icon-left btn-primary">
														<i class="far fa-edit"></i> 차단 관리
													</button>
													<a href="#" class="btn btn-icon icon-left btn-danger">
														<i class="fas fa-times"></i> 회원 탈퇴
													</a>
												</div>
											</form>
										</div>
									</div>
									<div class="card-body p-0">
										<div class="table-responsive">
											<table class="table table-striped">
												<tr>
													<th class="p-0 text-center">
														<div class="custom-checkbox custom-control">
															<input type="checkbox" data-checkboxes="mygroup"
																data-checkbox-role="dad" class="custom-control-input"
																id="checkbox-all"> <label for="checkbox-all"
																class="custom-control-label">&nbsp;</label>
														</div>
													</th>
													<th class="p-0 text-center">작성자</th>
													<th class="p-0 text-center">게시판 유형</th>
													<th class="p-0 text-center">제목</th>
													<th class="p-0 text-center">신고자</th>
													<th class="p-0 text-center"><select id="reportType"
														name="reportType" onchange="sortReportType()"
														style="padding: 4px 8px; border: 1px solid #ddd; border-radius: 4px; font-size: 14px; color: #333; background-color: #fff; width: 120px;">
															<option value="" ${empty param.rp_seq ? 'selected' : ''}>전체</option>
															<option value="1"
																${param.rp_seq == '1' ? 'selected' : ''}>비방/욕설</option>
															<option value="2"
																${param.rp_seq == '2' ? 'selected' : ''}>허위사실</option>
															<option value="3"
																${param.rp_seq == '3' ? 'selected' : ''}>개인정보노출</option>
															<option value="4"
																${param.rp_seq == '4' ? 'selected' : ''}>음란성</option>
															<option value="5"
																${param.rp_seq == '5' ? 'selected' : ''}>게시글 도배</option>
															<option value="6"
																${param.rp_seq == '6' ? 'selected' : ''}>부적절한
																홍보</option>
															<option value="7"
																${param.rp_seq == '7' ? 'selected' : ''}>기타</option>
													</select></th>
													<th class="p-0 text-center">신고 내용</th>
													<th class="p-0 text-center">신고 일자</th>
												</tr>

												<c:forEach items="${reportList}" var="report"
													varStatus="status">
													<tr>
														<td class="p-0 text-center">
															<div class="custom-checkbox custom-control">
																<input type="checkbox" data-checkboxes="mygroup"
																	class="custom-control-input"
																	id="checkbox-${status.index}"> <label
																	for="checkbox-${status.index}"
																	class="custom-control-label">&nbsp;</label>
															</div>
														</td>
														<td class="p-0 text-center">${report.writer_id}</td>
														<td class="p-0 text-center">${report.sub_type}</td>
														<td class="p-0 text-center">${report.title}</td>
														<td class="p-0 text-center">${report.reporter_id}</td>
														<td class="p-0 text-center">${report.rp_seq}</td>
														<td class="p-0 text-center">${report.report_detail}</td>
														<td class="p-0 text-center">${report.report_regdate}</td>
													</tr>
												</c:forEach>
											</table>
										</div>
									</div>

									<div class="card-footer text-center">
										<nav class="d-inline-block">
											<ul class="pagination mb-0">
												<c:if test="${pageUtil.hasPreviousPage()}">
													<li class="page-item"><a class="page-link"
														href="/good/admin/userreport.do?page=${pageUtil.startPage - 1}&rp_seq=${param.rp_seq}">
															<i class="fas fa-chevron-left"></i>
													</a></li>
												</c:if>

												<c:forEach begin="${pageUtil.startPage}"
													end="${pageUtil.startPage + 4 < pageUtil.totalPages ? pageUtil.startPage + 4 : pageUtil.totalPages}"
													var="page">
													<li
														class="page-item ${page == pageUtil.currentPage ? 'active' : ''}">
														<a class="page-link"
														href="/good/admin/userreport.do?page=${page}&rp_seq=${param.rp_seq}">${page}</a>
													</li>
												</c:forEach>

												<c:if test="${pageUtil.hasNextPage()}">
													<li class="page-item"><a class="page-link"
														href="/good/admin/userreport.do?page=${pageUtil.startPage + 5}&rp_seq=${param.rp_seq}">
															<i class="fas fa-chevron-right"></i>
													</a></li>
												</c:if>
											</ul>
										</nav>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<footer class="main-footer">
				<div class="footer-left">
					Copyright &copy; 2018
					<div class="bullet"></div>
					Design By <a href="https://nauval.in/">Muhamad Nauval Azhar</a>
				</div>
				<div class="footer-right"></div>
			</footer>
		</div>
	</div>

	<div id="modal-part" class="modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">회원 정보 변경</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="/good/admin/listreportuser.do" method="POST">
					<div class="modal-body">
						<div class="form-group">
							<label for="user-id">아이디</label> <input type="text"
								class="form-control" id="user-id" name="user-id" readonly>
						</div>
						<div class="form-group">
							<label for="block-date">차단 일자</label> <input type="text"
								class="form-control" id="block-date" name="block-date" readonly>
						</div>
						<div class="form-group">
							<label for="release-date">해제 일자</label> <input type="text"
								class="form-control" id="release-date" name="release-date">
						</div>
						<div class="form-group">
							<label for="block-reason">사유</label> <input type="text"
								class="form-control" id="block-reason" name="block-reason">
						</div>
						<div class="form-group">
							<label for="status">상태</label> <select class="form-control"
								id="status" name="status">
								<option value="3일정지">3일 정지</option>
								<option value="5일정지">5일 정지</option>
								<option value="7일정지">7일 정지</option>
								<option value="영구정지">영구 정지</option>
								<option value="정상">정상</option>
							</select>
						</div>
						<div class="form-group">
							<label for="change-reason">변경 사유</label> <input type="text"
								class="form-control" id="change-reason" name="change-reason">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">저장</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">닫기</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="modal-bulk-part" class="modal" tabindex="-1" role="dialog"
		data-backdrop="static">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">일괄 처리</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="/good/admin/listreportuser.do" method="POST">
					<div class="modal-body">
						<input type="hidden" id="user-ids" name="user-ids">
						<div class="form-group">
							<label for="bulk-user-count">선택된 유저 수</label> <input type="text"
								class="form-control" id="bulk-user-count" readonly>
						</div>
						<div class="form-group">
							<label for="bulk-status">상태</label> <select class="form-control"
								id="bulk-status" name="bulk-status">
								<option value="3일정지">3일 정지</option>
								<option value="5일정지">5일 정지</option>
								<option value="7일정지">7일 정지</option>
								<option value="영구정지">영구 정지</option>
								<option value="정상">정상</option>
							</select>
						</div>
						<div class="form-group">
							<label for="bulk-change-reason">변경 사유</label> <input type="text"
								class="form-control" id="bulk-change-reason"
								name="bulk-change-reason">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">일괄 처리</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">닫기</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Page Specific JS File -->
	<script src="/good/assets/js/page/components-table.js"></script>

	<script>
	function sortReportType() {
	  var reportType = document.getElementById("reportType").value;
	  location.href = "/good/admin/userreport.do?page=${pageUtil.currentPage}&rp_seq=" + reportType;
	}

	$(document).on('change', 'input[type="checkbox"][data-checkboxes="mygroup"]', function() {
	  const checkedUsers = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
	  const userCount = checkedUsers.length;
	  $('#bulk-user-count').val(userCount + '명');
	});

	$('#btn-block-alluser').click(function() {
	  const checkedUsers = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
	  const userCount = checkedUsers.length;

	  if (userCount === 1) {
	    // 선택한 행의 id 가져오기
	    const selectedId = checkedUsers.closest('tr').find('td:nth-child(2)').text();
	    
	    // 모달창에 id 값 전달
	    $('#user-id').val(selectedId);

	    // 현재 날짜 설정
	    var currentDate = new Date().toISOString().split('T')[0];
	    $('#block-date').val(currentDate);

	    // 개별 처리 모달창 열기
	    $('#modal-part').modal('show');
	  } else if (userCount > 1) {
	    // 선택한 행의 id 목록 가져오기
	    const selectedIds = checkedUsers.map(function() {
	      return $(this).closest('tr').find('td:nth-child(2)').text();
	    }).get();

	    // 모달창에 id 목록 전달
	    $('#user-ids').val(selectedIds.join(','));

	    // 일괄 처리 모달창 열기
	    $('#modal-bulk-part').modal('show');
	  } else {
	    alert('차단할 사용자를 선택해주세요.');
	  }
	});

	// 모달창 닫힐 때 홈페이지 동작 복원
	$('.modal').on('hidden.bs.modal', function () {
	  $('body').removeClass('modal-open');
	  $('.modal-backdrop').remove();
	});
	</script>
</body>
</html>