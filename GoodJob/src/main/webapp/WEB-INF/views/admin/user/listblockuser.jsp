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
						                            <th class="text-center">아이디</th>
						                            <th class="text-center">차단 일자</th>
						                            <th class="text-center">해제 일자</th>
 													<th class="text-center">사유</th>
 													<th class="text-center">상태</th>
												</tr>

												<c:choose>
													<c:when test="${not empty blockUserList}">
														<c:forEach items="${blockUserList}" var="blockUserList"
															varStatus="status">
															<tr>
																<td class="p-0 text-center">
																	<div class="custom-checkbox custom-control">
																		<input type="checkbox" data-checkboxes="mygroup"
																			class="custom-control-input"
																			id="checkbox-${status.index}"> <label
																			for="checkbox-${status.index}"
																			class="custom-control-label">&nbsp;</label>
																			<input type="hidden" name="ban_seq" value="${blockUserList.ban_seq}">
																	</div>
																</td>
																<td class="p-0 text-center">${blockUserList.id}</td>
																<td class="p-0 text-center">${blockUserList.ban_startdate}</td>
																<td class="p-0 text-center">${blockUserList.ban_enddate}</td>
																<td class="p-0 text-center">${blockUserList.ban_reason}</td>
																<td class="p-0 text-center">${blockUserList.remaining_time}</td>
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
														href="/good/admin/listblockuser.do?page=${Math.max(pageUtil.startPage - 1, 1)}">
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
														href="/good/admin/listblockuser.do?page=${page}">
															${page} </a>
													</li>
												</c:forEach>

												<c:if test="${pageUtil.hasNextPage()}">
													<li class="page-item"><a class="page-link"
														href="/good/admin/listblockuser.do?page=${endPage + 1}">
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