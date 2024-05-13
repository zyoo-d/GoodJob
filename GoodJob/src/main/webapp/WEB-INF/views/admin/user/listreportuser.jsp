<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<h1>신고 회원 관리</h1>
					</div>

					<div class="section-body">

						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h4>신고 접수 목록</h4>
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
													<button id="btn-block-user"
														class="btn btn-icon icon-left btn-primary">
														<i class="far fa-edit"></i> 차단 관리
													</button>
													<button id="btn-block-alluser"
														class="btn btn-icon icon-left btn-primary">
														<i class="far fa-edit"></i> 일괄 처리
													</button>
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

													<th class="p-0 text-center report-type"><select id="reportType" class="p-0 text-center"
														name="reportType" onchange="sortReportType()">
															<option value="" ${empty param.rp_seq ? 'selected' : ''}>신고유형(전체)</option>
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

												<c:choose>
													<c:when test="${not empty reportList}">
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
																<c:if
																	test="${report.sub_type ne 'qna' && report.sub_type ne 'study'}">
																	<td class="p-0 text-center report_title"
																		onclick="location.href='/good/user/${report.sub_type}/${report.sub_type}view.do?cp_seq=${report.parent_seq}';">${report.title}</td>
																</c:if>
																<c:if test="${report.sub_type eq 'study'}">
																	<td class="p-0 text-center report_title"
																		onclick="location.href='/good/user/${report.sub_type}/view${report.sub_type}.do?std_seq=${report.parent_seq}';">${report.title}</td>
																</c:if>
																<c:if test="${report.sub_type eq 'qna'}">
																	<td class="p-0 text-center report_title"
																		onclick="location.href='/good/user/${report.sub_type}/view${report.sub_type}.do?${report.sub_type}_seq=${report.parent_seq}';">${report.title}</td>
																</c:if>
																<td class="p-0 text-center">${report.reporter_id}</td>
																<td class="p-0 text-center">${report.rp_seq}</td>
																<td class="p-0 text-center">${report.report_detail}</td>
																<td class="p-0 text-center">${report.report_regdate}</td>
															</tr>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<tr>
															<td colspan="8" class="p-0 text-center">회원 목록이 없습니다.</td>
														</tr>
													</c:otherwise>
												</c:choose>

											</table>
										</div>
									</div>




									<div class="card-footer text-center">
										<nav class="d-inline-block">
											<ul class="pagination mb-0">
												<c:if test="${pageUtil.hasPreviousPage()}">
													<li class="page-item"><a class="page-link"
														href="/good/admin/listreportuser.do?page=${Math.max(pageUtil.startPage - 1, 1)}&rp_seq=${param.rp_seq}">
															<i class="fas fa-chevron-left"></i>
													</a></li>
												</c:if>

												<c:set var="startPage"
													value="${pageUtil.currentPage - (pageUtil.currentPage - 1) % 5}" />
												<c:set var="endPage"
													value="${startPage + 4 < pageUtil.totalPages ? startPage + 4 : pageUtil.totalPages}" />

												<c:forEach begin="${startPage}" end="${endPage}" var="page">
													<li
														class="page-item ${page == pageUtil.currentPage ? 'active' : ''}">
														<a class="page-link"
														href="/good/admin/listreportuser.do?page=${page}&rp_seq=${param.rp_seq}">
															${page} </a>
													</li>
												</c:forEach>

												<c:if test="${pageUtil.hasNextPage()}">
													<li class="page-item"><a class="page-link"
														href="/good/admin/listreportuser.do?page=${endPage + 1}&rp_seq=${param.rp_seq}">
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
			<%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>
		</div>
	</div>

	<div id="modal-part" class="modal-part">
		<form method="POST" action="/good/admin/listreportuser.do">
			<input type="hidden" id="report_seq" name="report_seq" /> <input
				type="hidden" id="report_type" name="report_type" /> <input
				type="hidden" id="report_sub_type" name="report_sub_type" />
			<div class="form-group">
				<label for="user-id">아이디</label> <input type="text"
					class="form-control" id="user-id" name="user-id">
			</div>
			<div class="form-group">
				<label for="status">상태</label> <select class="form-control"
					id="status" name="status">
					<option value="0">정상</option>
					<option value="3">3일 정지</option>
					<option value="5">5일 정지</option>
					<option value="7">7일 정지</option>
					<option value="10">영구 정지</option>
				</select>
			</div>
			<div class="form-group">
				<label for="block-date">차단 일자</label> <input type="text"
					class="form-control" id="block-date" name="block-date">
			</div>
			<div class="form-group">
				<label for="release-date">해제 일자</label> <input type="text"
					class="form-control" id="release-date" name="release-date">
			</div>
			<div class="form-group">
				<label for="block-reason">사유</label> <input type="text"
					class="form-control" id="block-reason" name="block-reason">
			</div>
		</form>
	</div>

	<div id="modal-bulk-part" class="modal-part">
		<form method="POST" action="/good/admin/listreportuser.do">
			<input type="hidden" id="bulk-user-seqs" name="report_seq" /> <input
				type="hidden" id="bulk-user-ids" name="user-id"> <input
				type="hidden" id="bulk-report_types" name="report_type" /> <input
				type="hidden" id="bulk-report_sub_types" name="report_sub_type" />
			<div class="form-group">
				<label for="bulk-user-count">선택된 유저 수</label> <input type="text"
					class="form-control" id="bulk-user-count" readonly>
			</div>
			<div class="form-group">
				<label for="bulk-status">상태</label> <select class="form-control"
					id="bulk-status" name="status">
					<option value="0">정상</option>
					<option value="3">3일 정지</option>
					<option value="5">5일 정지</option>
					<option value="7">7일 정지</option>
					<option value="10">영구 정지</option>
				</select>
			</div>
			<div class="form-group">
				<label for="bulk-block-date">차단 일자</label> <input type="text"
					class="form-control" id="bulk-block-date" name="block-date">
			</div>
			<div class="form-group">
				<label for="bulk-release-date">해제 일자</label> <input type="text"
					class="form-control" id="bulk-release-date" name="release-date">
			</div>
			<div class="form-group">
				<label for="bulk-block-reason">사유</label> <input type="text"
					class="form-control" id="bulk-block-reason" name="block-reason">
			</div>
		</form>
	</div>


	<!-- Page Specific JS File -->
	<script src="/good/assets/js/page/components-table.js"></script>


	<script>
	function sortReportType() {
		  var reportType = document.getElementById("reportType").value;
		  location.href = "/good/admin/listreportuser.do?page=${pageUtil.currentPage}&rp_seq=" + reportType;
	}
	
	
	
	let modalInputs;
	let selectedUser = {};
	let bulkModalInputs;
	
	function formatDate(date) {
		  const isoString = date.toISOString();
		  const formattedDate = isoString.substring(0, 10);
		  console.log('Formatted date:', formattedDate);
		  return formattedDate;
		}
	
	function setBlockAndReleaseDates(status, blockDateInput, releaseDateInput) {
		  if (status === '0') {
		    blockDateInput.val('');
		    releaseDateInput.val('');
		  } else {
		    const blockDate = new Date();
		    blockDateInput.val(formatDate(blockDate));

		    const releaseDate = new Date(blockDate);
		    if (status === '3') {
		      releaseDate.setDate(releaseDate.getDate() + 3);
		    } else if (status === '5') {
		      releaseDate.setDate(releaseDate.getDate() + 5);
		    } else if (status === '7') {
		      releaseDate.setDate(releaseDate.getDate() + 7);
		    } else if (status === '10') {
		      releaseDate.setFullYear(releaseDate.getFullYear() + 1000);
		    }
		    releaseDateInput.val(formatDate(releaseDate));
		  }
		}
	
	
	

	$('#btn-block-user').on('click', function() {
		  const checkedCheckboxes = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
		  const checkedCount = checkedCheckboxes.length;

		  if (checkedCount === 0) {
		    alert('회원을 선택해주세요.');
		    return;
		  } else if (checkedCount > 1) {
		    alert('회원 한 명만 선택해주세요.');
		    return;
		  }

		  $('#btn-block-user').fireModal({
		    body: $('#modal-part'),
		    title: '차단 회원 관리',
		    footerClass: 'text-right',
		    buttons: [
		      {
		        text: '저장',
		        class: 'btn btn-primary',
		        submit: true,
		        handler: function(modal) {
		          const userId = modalInputs.userId.val();
		          const status = modalInputs.status.val();
		          const changeReason = $('#change-reason').val();

		          // 모달 닫기
		          modal.modal('hide');
		        }
		      }
		    ],
		    created: function(modal) {
		    	 const checkedCheckboxes = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
		    	    const checkedCount = checkedCheckboxes.length;

		    	    if (checkedCount === 0) {
		    	      alert('회원을 선택해주세요.');
		    	      return;
		    	    }
		    	
		    	
		    	    bulkModalInputs = {
		        userId: modal.find('#user-id'),
		        seq: modal.find('#report_seq'),
		        type: modal.find('#report_type'),
		        subType: modal.find('#report_sub_type'),
		        status: modal.find('#status'),
		        blockDate: modal.find('#block-date'),
		        releaseDate: modal.find('#release-date'),
		        blockReason: modal.find('#block-reason')
		      };

		      // 선택된 회원 정보 설정
		      const selectedRow = checkedCheckboxes.closest('tr');
		      const userId = selectedRow.find('td:nth-child(2)').text().trim();
		      const seq = selectedRow.find('input[name="report_seq"]').val();
		      const type = selectedRow.find('input[name="report_type"]').val();
		      const subType = selectedRow.find('td:nth-child(3)').text().trim();

		      bulkModalInputs.userId.val(userId);
		      bulkModalInputs.seq.val(seq);
		      bulkModalInputs.type.val(type);
		      bulkModalInputs.subType.val(subType);
		    }
		  });
		});

	$(document).on('change', 'input[type="checkbox"][data-checkboxes="mygroup"]', function() {
		  if ($(this).is(':checked')) {
		    const row = $(this).closest('tr');
		    selectedUser = {
		      id: row.find('td:nth-child(2)').text().trim(),
		      seq: row.find('input[name="report_seq"]').val(),
		      type: row.find('input[name="report_type"]').val(),
		      subType: row.find('td:nth-child(3)').text().trim(),
		    };

		    // modalInputs 객체가 초기화되었는지 확인 후 접근
		    if (bulkModalInputs && bulkModalInputs.userId) {
		    	bulkModalInputs.userId.val(selectedUser.id);
		    	bulkModalInputs.seq.val(selectedUser.seq);
		    	bulkModalInputs.type.val(selectedUser.type);
		    	bulkModalInputs.subType.val(selectedUser.subType);
		    }
		  } else {
		    selectedUser = {};

		    // modalInputs 객체가 초기화되었는지 확인 후 접근
		    if (bulkModalInputs && modalInputs.userId) {
		    	bulkModalInputs.userId.val('');
		    	bulkModalInputs.seq.val('');
		    	bulkModalInputs.type.val('');
		    	bulkModalInputs.subType.val('');
		    }
		  }
		});


	$('#btn-block-alluser').on('click', function() {
		  const checkedCheckboxes = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
		  const checkedCount = checkedCheckboxes.length;

		  if (checkedCount === 0) {
		    alert('회원을 선택해주세요.');
		    return;
		  }

		  $('#btn-block-alluser').fireModal({
		    body: $('#modal-bulk-part'),
		    title: '일괄 처리',
		    footerClass: 'text-right',
		    buttons: [
		      {
		        text: '일괄 처리',
		        class: 'btn btn-primary',
		        submit: true,
		        handler: function(modal) {
		          const selectedUserIds = $('#bulk-user-ids').val().split(',');
		          const status = bulkModalInputs.status.val();
		          const changeReason = bulkModalInputs.changeReason.val();

		          // 서버로 데이터 전송 또는 다른 작업 수행

		          // 모달 닫기
		          modal.modal('hide');
		        }
		      }
		    ],
		    created: function(modal) {
		      bulkModalInputs = {
		        userCount: modal.find('#bulk-user-count'),
		        status: modal.find('#bulk-status'),
		        seq: modal.find('#bulk-user-seqs'),
		        type: modal.find('#bulk-report_types'),
		        subType: modal.find('#bulk-report_sub_types'),
		        blockDate: modal.find('#bulk-block-date'),
		        releaseDate: modal.find('#bulk-release-date'),
		        changeReason: modal.find('#bulk-block-reason')
		      };

		      // 체크박스 선택된 유저 수 계산
		      bulkModalInputs.userCount.val(checkedCount + '명');

		      // 체크박스 선택된 유저 아이디 가져오기
		      const userIds = [];
		      const userSeqs = [];
		      const reportTypes = [];
		      const reportSubTypes = [];
		      checkedCheckboxes.each(function() {
		        const selectedRow = $(this).closest('tr');
		        const userId = selectedRow.find('td:nth-child(2)').text().trim();
		        const userSeq = selectedRow.find('input[name="report_seq"]').val();
		        const reportType = selectedRow.find('input[name="report_type"]').val();
		        const reportSubType = selectedRow.find('td:nth-child(3)').text().trim();
		        userIds.push(userId);
		        userSeqs.push(userSeq);
		        reportTypes.push(reportType);
		        reportSubTypes.push(reportSubType);
		      });

		      // 선택된 유저 아이디를 hidden 태그에 설정
		      modal.find('#bulk-user-ids').val(userIds.join(','));
		      modal.find('#bulk-user-seqs').val(userSeqs.join(','));
		      modal.find('#bulk-report_types').val(reportTypes.join(','));
		      modal.find('#bulk-report_sub_types').val(reportSubTypes.join(','));
		    }
		  });
		});
	
	

		$(document).on('change', '#bulk-status', function() {
		  const selectedStatus = $(this).val();

		  if (selectedStatus === '0') {
		    bulkModalInputs.blockDate.val('');
		    bulkModalInputs.releaseDate.val('');
		  } else {
		    const blockDate = new Date();
		    bulkModalInputs.blockDate.val(formatDate(blockDate));

		    const releaseDate = new Date(blockDate);
		    if (selectedStatus === '3') {
		      releaseDate.setDate(releaseDate.getDate() + 3);
		    } else if (selectedStatus === '5') {
		      releaseDate.setDate(releaseDate.getDate() + 5);
		    } else if (selectedStatus === '7') {
		      releaseDate.setDate(releaseDate.getDate() + 7);
		    } else if (selectedStatus === '10') {
		      releaseDate.setFullYear(releaseDate.getFullYear() + 1000);
		    }
		    bulkModalInputs.releaseDate.val(formatDate(releaseDate));
		  }
		});


	$(document).ready(function() {
	    // 모달 창이 열릴 때 초기 상태 설정
	    $('#btn-block-user').on('show.bs.modal', function() {
	      $('#status').val('0'); // 초기 상태를 '정상'으로 설정
	      $('#block-date').val('');
	      $('#release-date').val('');
	    });

	    
	    
	    $('#status').on('change', function() {
	        const selectedStatus = $(this).val();
	        setBlockAndReleaseDates(selectedStatus, $('#block-date'), $('#release-date'));
	      });
	    });

	
	
  
  
  </script>







</body>
</html>