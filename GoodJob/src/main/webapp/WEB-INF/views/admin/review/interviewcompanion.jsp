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
						<h1>면접 후기 반려 관리</h1>
					</div>

					<div class="section-body">

						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h4>반려 목록</h4>
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
														<i class="far fa-edit"></i> 반려 취소
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
													<th class="p-0 text-center">글 번호</th>
													<th class="p-0 text-center">작성자</th>
													<th class="p-0 text-center">면접 본 기업명</th>
													<th class="p-0 text-center">반려 사유</th>
													<th class="p-0 text-center">승인 일자</th>
												</tr>

												<c:choose>
													<c:when test="${not empty list}">
														<c:forEach items="${list}" var="companionl"
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
																<td class="p-0 text-center">${companionl.ITV_SEQ}</td>
																<td class="p-0 text-center">${companionl.ID}</td>

																<td class="p-0 text-center">${companionl.ITV_CPNAME}</td>
																<td class="p-0 text-center">${companionl.CONTENT}</td>
																<td class="p-0 text-center">${companionl.ITV_C_DATE}</td>
															</tr>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<tr>
															<td colspan="8" class="p-0 text-center">면접 반려 데이터가
																없습니다.</td>
														</tr>
													</c:otherwise>
												</c:choose>
											</table>
										</div>
									</div>
									<!-- 페이징 기능 등 생략 -->
								</div>
							</div>
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

	<footer class="main-footer">
		<div class="footer-left">
			Copyright &copy; 2018
			<div class="bullet"></div>
			Design By <a href="https://nauval.in/">Muhamad Nauval Azhar</a>
		</div>
		<div class="footer-right"></div>
	</footer>

	<div id="modal-part" class="modal-part">
		<form method="POST" action="/good/admin/listreportuser.do">
			<div class="form-group">
				<label for="user-id">아이디</label> <input type="text"
					class="form-control" id="user-id" name="user-id">
			</div>
			<div class="form-group">
				<label for="status">상태</label> <select class="form-control"
					id="status">
					<option value="0">정상</option>
					<option value="3">3일 정지</option>
				</select>
			</div>
			<div class="form-group" id="reasonField">
				<label for="block-reason">사유</label> <input type="text"
					class="form-control" id="block-reason" name="block-reason">
				<select class="form-control" id="status"
					onchange="toggleReasonField(this.value)">
					<option value="0">욕설 및 오타</option>
					<option value="3">광고 글 의심</option>
					<option value="3">관련없는 내용 작성</option>
				</select>
			</div>
		</form>
	</div>

	<div id="modal-bulk-part" class="modal-part">
		<form method="POST" action="/good/admin/listreportuser.do">
			<input type="hidden" id="bulk-user-ids" name="user-id">
			<div class="form-group">
				<label for="bulk-user-count">선택된 유저 수</label> <input type="text"
					class="form-control" id="bulk-user-count" readonly>
			</div>
			<div class="form-group">
				<label for="bulk-status">상태</label> <select class="form-control"
					id="bulk-status">
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

	let modalInputs;
	let selectedUser = {};
	

	$('#btn-block-user').fireModal({
	    body: $('#modal-part'),
	    title: '면접 후기 반려 관리',
	    footerClass: 'text-right',
	    buttons: [
	        {
	            text: '저장',
	            class: 'btn btn-primary',
	            submit: true,
	            handler: function(modal) {
	                const status = modalInputs.status.val();
	                const reason = modalInputs.blockReason.val();
	                const itvSeq = selectedUser.id;

	                if (status === '0') {
	                    // 승인 처리 시 /good/board/interview/InterviewApproval.do로 데이터 전송
	                    $.ajax({
	                        type: 'POST',
	                        url: '/good/board/interview/InterviewApproval.do',
	                        data: { itvSeq: itvSeq },
	                        success: function(response) {
	                            if (response === 'success') {
	                                alert('해당 글이 승인처리 되었습니다.');
	                            } else {
	                                alert('승인 처리 중 오류가 발생했습니다.');
	                            }
	                        },
	                        error: function(xhr, status, error) {
	                            console.error('승인 처리 중 오류 발생:', error);
	                            alert('승인 처리 중 오류가 발생했습니다.');
	                        }
	                    });
	                } else {
	                    // 보류 처리 시 서버로 데이터 전송 또는 다른 작업 수행
	                    console.log('보류 처리:', itvSeq, reason);
	                }

	                // 모달 닫기
	                modal.modal('hide');
	            }
	        }
	    ],
	    created: function(modal) {
	        modalInputs = {
	            userId: modal.find('#user-id'),
	            status: modal.find('#status'),
	            blockReason: modal.find('#block-reason')
	        };
	    }
	});

	$(document).on('change', 'input[type="checkbox"][data-checkboxes="mygroup"]', function() {
	    if ($(this).is(':checked')) {
	        const row = $(this).closest('tr');
	        selectedUser = {
	            id: row.find('td:nth-child(2)').text().trim(),
	        };

	        modalInputs.userId.val(selectedUser.id);
	    } else {
	        selectedUser = {};
	        modalInputs.userId.val('');
	    }
	});

	let bulkModalInputs;



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

		$(document).on('change', 'input[type="checkbox"][data-checkboxes="mygroup"]', function() {
			  const checkedUsers = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
			  const userCount = checkedUsers.length;
			  bulkModalInputs.userCount.val(userCount + '명');

			  // 체크박스 선택된 유저 아이디 가져오기
			  const userIds = [];
			  checkedUsers.each(function() {
			    const selectedRow = $(this).closest('tr');
			    const userId = selectedRow.find('td:nth-child(2)').text().trim();
			    userIds.push(userId);
			  });

			  // 선택된 유저 아이디를 hidden 태그에 설정
			  $('#bulk-user-ids').val(userIds.join(','));
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
	        const blockDateInput = $('#block-date');
	        const releaseDateInput = $('#release-date');

	        if (selectedStatus === '0') {
	          blockDateInput.val('');
	          releaseDateInput.val('');
	        } else {
	          const blockDate = new Date();
	          console.log('Block date:', blockDate);
	          blockDateInput.val(formatDate(blockDate));

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
	          console.log('Release date:', releaseDate);
	          releaseDateInput.val(formatDate(releaseDate));
	        }
	      });
	    });
	
	
	function formatDate(date) {
	    const isoString = date.toISOString();
	    const formattedDate = isoString.substring(0, 10);
	    console.log('Formatted date:', formattedDate);
	    return formattedDate;
	  }
	
  
  
  </script>






















</body>
</html>