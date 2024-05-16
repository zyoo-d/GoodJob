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
						<h1>승인 대기 면접 후기</h1>
					</div>

					<div class="section-body">

						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h4>대기 목록</h4>
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
														<i class="far fa-edit"></i> 게시글 관리
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

													<th class="p-0 text-center">작성 일자</th>
												</tr>

												<c:choose>
													<c:when test="${not empty list}">
														<c:forEach items="${list}" var="hold" varStatus="status">
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
																<td class="p-0 text-center">${hold.ITV_SEQ}</td>
																<td class="p-0 text-center">${hold.ID}</td>

																<td class="p-0 text-center">${hold.ITV_CPNAME}</td>

																<td class="p-0 text-center">${hold.ITV_REGDATE}</td>
															</tr>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<tr>
															<td colspan="8" class="p-0 text-center">면접 대기 데이터가
																없습니다.</td>
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
														href="/good/admin/interview/interviewhold.do.do?page=${pageUtil.startPage - 1}&rp_seq=${param.rp_seq}">
															<i class="fas fa-chevron-left"></i>
													</a></li>
												</c:if>

												<c:forEach begin="${pageUtil.startPage}"
													end="${pageUtil.startPage + 4 < pageUtil.totalPages ? pageUtil.startPage + 4 : pageUtil.totalPages}"
													var="page">
													<li
														class="page-item ${page == pageUtil.currentPage ? 'active' : ''}">
														<a class="page-link"
														href="/good/admin/interview/interviewhold.do.do?page=${page}&rp_seq=${param.rp_seq}">
															${page} </a>
													</li>
												</c:forEach>

												<c:if test="${pageUtil.hasNextPage()}">
													<li class="page-item"><a class="page-link"
														href="/good/admin/interview/interviewhold.do.do?page=${pageUtil.startPage + 5}&rp_seq=${param.rp_seq}">
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
		<div class="form-group">
			<label for="status">처리</label> <select class="form-control"
				id="status">
				<option value="0">승인</option>
				<option value="3">보류</option>
			</select>
		</div>
		<div class="form-group">
			<label for="status">사유</label> <select class="form-control" disabled
				id="chstatus">
				<option value="10"></option>
				<option value="0">오타 및 욕설</option>
				<option value="3">광고 글 의심</option>
				<option value="5">관련 없는 내용 작성</option>
			</select>
		</div>
	</div>


	<!-- Page Specific JS File -->

	<script src="/good/assets/js/page/components-table.js"></script>


	<script>

		let modalInputs;
		let selectedUser = {};

		function toggleReasonDropdown(status) {
		    const reasonDropdown = $('#chstatus');
		    if (status === '3') {
		        reasonDropdown.prop('disabled', false);
		    } else {
		        reasonDropdown.prop('disabled', true);
		        reasonDropdown.val(''); // 사유 초기화
		    }
		}

		$('#btn-block-user').fireModal({
		    body: $('#modal-part'),
		    title: '면접 후기 관리',
		    footerClass: 'text-right',
		    buttons: [
		        {
		            text: '저장',
		            class: 'btn btn-primary',
		            submit: true,
		            handler: function(modal) {
		                const status = modalInputs.status.val();
		                const reasonOption = $('#chstatus option:selected');
		                const reason = reasonOption.text();
		                const itvSeq = selectedUser.id;

		                // 여기에서 toggleReasonDropdown 함수 호출
		                toggleReasonDropdown(status);

		                if (status === '0') {
		                    // 승인 처리 시 /good/board/interview/InterviewApproval.do로 데이터 전송
		                    $.ajax({
		                        type: 'POST',
		                        url: '/good/board/interview/InterviewApproval.do',
		                        data: { itvSeq: itvSeq },
		                        success: function(response) {
		                            if (response === 'success') {
		                                $('tr td:contains("' + itvSeq + '")').closest('tr').remove();
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
		                } else if (status === '3') { // 보류 처리 시
		                    // AJAX 요청 보내기
		                    $.ajax({
		                        type: 'POST',
		                        url: '/good/board/interview/InterviewCompanion.do',
		                        data: {
		                            itvSeq: itvSeq,
		                            reason: reason
		                        },
		                        success: function(response) {
		                            $('tr td:contains("' + itvSeq + '")').closest('tr').remove();
		                            alert('해당 글이 보류처리 되었습니다.');
		                        },
		                        error: function(xhr, status, error) {
		                            alert('보류 처리 중 오류가 발생했습니다.');
		                        }
		                    });
		                } else {
		                    // 다른 상태 처리
		                    console.log('다른 상태 처리:', itvSeq, reason);
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

		        // 초기에 "처리" 드롭다운 값에 따라 "사유" 드롭다운 활성화 여부 설정
		        const initialStatus = modalInputs.status.val();
		        toggleReasonDropdown(initialStatus);

		        // "처리" 드롭다운 값 변경 시 "사유" 드롭다운 활성화 여부 변경
		        modalInputs.status.on('change', function() {
		            const selectedStatus = $(this).val();
		            toggleReasonDropdown(selectedStatus);
		        });
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
		            releaseDateInput.val(formatDate(releaseDate));
		        }
		    });
		});

		function formatDate(date) {
		    const isoString = date.toISOString();
		    const formattedDate = isoString.substring(0, 10);
		    return formattedDate;
		}
  
  
  </script>
</body>
</html>