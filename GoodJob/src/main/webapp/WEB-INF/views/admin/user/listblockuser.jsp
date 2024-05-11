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
						<h1>차단 유저 관리</h1>
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
													<a href="#" class="btn btn-icon icon-left btn-primary"><i
														class="far fa-edit"></i> 수정</a>
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
																<td class="p-0 text-center">${report.title}</td>
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
														href="/good/admin/listreportuser.do?page=${pageUtil.startPage - 1}&rp_seq=${param.rp_seq}">
															<i class="fas fa-chevron-left"></i>
													</a></li>
												</c:if>

												<c:forEach begin="${pageUtil.startPage}"
													end="${pageUtil.startPage + 4 < pageUtil.totalPages ? pageUtil.startPage + 4 : pageUtil.totalPages}"
													var="page">
													<li
														class="page-item ${page == pageUtil.currentPage ? 'active' : ''}">
														<a class="page-link"
														href="/good/admin/listreportuser.do?page=${page}&rp_seq=${param.rp_seq}">
															${page} </a>
													</li>
												</c:forEach>

												<c:if test="${pageUtil.hasNextPage()}">
													<li class="page-item"><a class="page-link"
														href="/good/admin/listreportuser.do?page=${pageUtil.startPage + 5}&rp_seq=${param.rp_seq}">
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


	<!-- Page Specific JS File -->
	<script src="/good/assets/js/page/components-table.js"></script>


	<script>
	function sortReportType() {
		  var reportType = document.getElementById("reportType").value;
		  location.href = "/good/admin/userreport.do?page=${pageUtil.currentPage}&rp_seq=" + reportType;
	}
	
	
	
  
  let modalInputs;
  let selectedUser = {};

  $('#btn-block-user').fireModal({
    body: $('#modal-part'),
    title: '차단 회원 관리',
    footerClass: 'text-right',
    buttons: [
      {
        text: '저장',
        class: 'btn btn-primary',
        handler: function(modal) {
          console.log('저장 버튼 클릭됨');
        }
      }
    ],
    created: function(modal) {
      modalInputs = {
        userId: modal.find('#user-id'),
        blockDate: modal.find('#block-date'),
        releaseDate: modal.find('#release-date'),
        reason: modal.find('#block-reason'),
        status: modal.find('#status')
      };
    }
  });
  
  $(document).on('change', 'input[type="checkbox"][data-checkboxes="mygroup"]', function() {
	  if ($(this).is(':checked')) {
	    const row = $(this).closest('tr');
	    selectedUser = {
	      id: row.find('td:nth-child(2)').text(), // 아이디 열
	      blockDate: row.find('td:nth-child(3)').text(), // 차단일자 열
	      releaseDate: row.find('td:nth-child(4)').text(), // 해제일자 열
	      reason: row.find('td:nth-child(5)').text(), // 사유 열
	      status: row.find('td:nth-child(6)').text() // 상태 열
	    };

	    modalInputs.userId.val(selectedUser.id);
	    modalInputs.blockDate.val(selectedUser.blockDate);
	    modalInputs.releaseDate.val(selectedUser.releaseDate);
	    modalInputs.reason.val(selectedUser.reason);
	    modalInputs.status.val(selectedUser.status);
	  } else {
	    selectedUser = {};
	    modalInputs.userId.val('');
	    modalInputs.blockDate.val('');
	    modalInputs.releaseDate.val('');
	    modalInputs.reason.val('');
	    modalInputs.status.val('정상');
	  }

	  const checkedUsers = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
	  const userCount = checkedUsers.length;
	  bulkModalInputs.userCount.val(userCount + '명');
	});
  
  
  let bulkModalInputs;

  $('#btn-block-alluser').fireModal({
    body: $('#modal-bulk-part'),
    title: '일괄 처리',
    footerClass: 'text-right',
    buttons: [
      {
        text: '일괄 처리',
        class: 'btn btn-primary',
        handler: function(modal) {
          // 일괄 처리 로직 작성
          console.log('일괄 처리 버튼 클릭됨');
        }
      }
    ],
    created: function(modal) {
      bulkModalInputs = {
        userCount: modal.find('#bulk-user-count'),
        status: modal.find('#bulk-status'),
        changeReason: modal.find('#bulk-change-reason')
      };

      // 체크박스 선택된 유저 수 계산
      const checkedUsers = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
      const userCount = checkedUsers.length;
      bulkModalInputs.userCount.val(userCount + '명');
    }
  });
  
  $(document).on('change', 'input[type="checkbox"][data-checkboxes="mygroup"]', function() {
	  const checkedUsers = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
	  const userCount = checkedUsers.length;

	  if (userCount === 1) {
	    // 선택한 행의 정보 가져오기
	    const row = $(this).closest('tr');
	    const userId = row.find('td:nth-child(2)').text();
	    const blockDate = row.find('td:nth-child(3)').text();
	    const releaseDate = row.find('td:nth-child(4)').text();
	    const reason = row.find('td:nth-child(5)').text();
	    const status = row.find('td:nth-child(6)').text();

	    // 모달창 입력 필드에 값 설정
	    $('#user-id').val(userId);
	    $('#block-date').val(blockDate);
	    $('#release-date').val(releaseDate);
	    $('#block-reason').val(reason);
	    $('#status').val(status);

	    // modal-part 모달창 열기
	    $('#modal-part').modal('show');
	  } else if (userCount > 1) {
	    // 선택한 행의 아이디 목록 가져오기
	    const selectedIds = checkedUsers.map(function() {
	      return $(this).closest('tr').find('td:nth-child(2)').text();
	    }).get();

	    // 모달창 입력 필드에 값 설정
	    $('#user-ids').val(selectedIds.join(','));

	    // modal-bulk-part 모달창 열기
	    $('#modal-bulk-part').modal('show');
	  } else {
	    // 모달창 입력 필드 초기화
	    $('#user-id').val('');
	    $('#block-date').val('');
	    $('#release-date').val('');
	    $('#block-reason').val('');
	    $('#status').val('정상');
	    $('#user-ids').val('');
	  }
	});
  
  
  </script>






















</body>
</html>