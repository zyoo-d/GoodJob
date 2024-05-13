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
						<h1>면접 후기 승인 관리</h1>
					</div>

					<div class="section-body">

						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h4>승인 목록</h4>
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
														class="btn btn-icon icon-left btn-primary"
														onclick="cancelApproval(this)">
														<i class="far fa-edit"></i> 승인 취소
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

													<th class="p-0 text-center">승인 일자</th>
												</tr>

												<c:choose>
													<c:when test="${not empty list}">
														<c:forEach items="${list}" var="approval"
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
																<td class="p-0 text-center">${approval.ITV_SEQ}</td>
																<td class="p-0 text-center">${approval.ID}</td>

																<td class="p-0 text-center">${approval.ITV_CPNAME}</td>

																<td class="p-0 text-center">${approval.ITV_S_DATE}</td>
															</tr>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<tr>
															<td colspan="8" class="p-0 text-center">면접 승인 데이터가
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
		  location.href = "/good/admin/listreportuser.do?page=${pageUtil.currentPage}&rp_seq=" + reportType;
	}
	
	
	
	let modalInputs;
	let selectedUser = {};
	
	
	function cancelApproval(btn) {
	    const row = $(btn).closest('tr');
	    const itvSeq = row.find('td:nth-child(2)').text().trim();

	    // 서버로 데이터 전송
	    $.ajax({
	        type: 'POST',
	        url: '/good/board/interview/InterviewApproval.do',
	        data: { itvSeq: itvSeq },
	        success: function(response) {
	            // 서버 응답 처리
	            console.log(itvSeq);
	            alert('승인 취소를 성공하였습니다.');
	            // 필요한 경우 추가 작업 수행
	        },
	        error: function(xhr, status, error) {
	        	 alert('승인 취소를 실패하였습니다.');
	        }
	    });
	}

	

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
		        console.log('일괄 처리 실행:', selectedUserIds, status, changeReason);

		        // 모달 닫기
		        modal.modal('hide');
		      }
		    }
		  ],
		  created: function(modal) {
		    bulkModalInputs = {
		      userCount: modal.find('#bulk-user-count'),
		      status: modal.find('#bulk-status'),
		      blockDate: modal.find('#bulk-block-date'),
		      releaseDate: modal.find('#bulk-release-date'),
		      changeReason: modal.find('#bulk-block-reason')
		    };

		    // 체크박스 선택된 유저 수 계산
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
		    modal.find('#bulk-user-ids').val(userIds.join(','));
		  }
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


	
	
	function formatDate(date) {
	    const isoString = date.toISOString();
	    const formattedDate = isoString.substring(0, 10);
	    console.log('Formatted date:', formattedDate);
	    return formattedDate;
	  }
	
  
  
  </script>






















</body>
</html>