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
						<h1>유저 관리</h1>
					</div>

					<div class="section-body">
						<!-- <h2 class="section-title">기업 목록</h2>
            <p class="section-lead">2024-04-26 기준</p> -->

						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h4>유저 목록</h4>
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
													<button id="btn-resignation-user"
														class="btn btn-icon icon-left btn-danger">
														<i class="far fa-edit"></i> 회원 탈퇴
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
													<th class="p-0 text-center">유저 아이디</th>
													<th class="p-0 text-center">이름</th>
													<th class="p-0 text-center">게시글 수</th>
													<th class="p-0 text-center">댓글 수</th>
													<th class="p-0 text-center">리뷰 수</th>
													<th class="p-0 text-center">가입일자</th>
													<th class="p-0 text-center">신고 횟수</th>
													<th class="p-0 text-center">상태</th>
												</tr>


												<c:forEach items="${userList}" var="userList">
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
														<td class="p-0 text-center">${userList.id}</td>
														<td class="p-0 text-center">${userList.name }</td>
														<td class="p-0 text-center">${userList.board}</td>
														<td class="p-0 text-center">${userList.comment }</td>
														<td class="p-0 text-center">${userList.review }</td>
														<td class="p-0 text-center">${userList.regdate }</td>
														<td class="p-0 text-center">${userList.report }</td>
														<td class="p-0 text-center">${userList.status }</td>
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
														href="/good/admin/listuser.do?page=${Math.max(pageUtil.startPage - 1, 1)}">
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
														href="/good/admin/listuser.do?page=${page}"> ${page} </a>
													</li>
												</c:forEach>

												<c:if test="${pageUtil.hasNextPage()}">
													<li class="page-item"><a class="page-link"
														href="/good/admin/listuser.do?page=${endPage + 1}"> <i
															class="fas fa-chevron-right"></i>
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

			<div class="modal fade" id="resignationModal" tabindex="-1"
				role="dialog" aria-labelledby="resignationModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="resignationModalLabel">회원 탈퇴</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form id="resignationForm" method="POST"
								action="/good/admin/userWithdrawal.do">
								<div class="form-group">
									<label for="user-ids">선택된 회원 아이디</label> <input type="text"
										class="form-control" id="user-ids" name="user-ids" readonly>
								</div>
								<div class="form-group">
									<label for="status">상태</label> <select class="form-control"
										id="status" name="status">
										<option value="0">정상</option>
										<option value="1">회원탈퇴</option>
									</select>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">취소</button>
							<button type="submit" form="resignationForm"
								class="btn btn-danger">회원 탈퇴</button>
						</div>
					</div>
				</div>
			</div>




			<%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>
		</div>
	</div>

	<script>
	$(document).ready(function() {
		  $('#btn-resignation-user').on('click', function() {
		    const checkedCheckboxes = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
		    const checkedCount = checkedCheckboxes.length;

		    if (checkedCount === 0) {
		      alert('회원을 선택해주세요.');
		      return;
		    }

		    $('#btn-resignation-user').fireModal({
		      body: $('#resignationModal'),
		      title: '회원 탈퇴',
		      footerClass: 'text-right',
		      buttons: [
		        {
		          text: '회원 탈퇴',
		          class: 'btn btn-danger',
		          submit: true,
		          handler: function(modal) {
		            modal.find('#resignationForm').submit();
		          }
		        }
		      ],
		      created: function(modal) {
		        const userIds = [];
		        checkedCheckboxes.each(function() {
		          const selectedRow = $(this).closest('tr');
		          const userId = selectedRow.find('td:nth-child(2)').text().trim();
		          userIds.push(userId);
		        });

		        modal.find('#user-ids').val(userIds.join(','));
		      }
		    });
		  });
		});
	
	</script>


</body>
</html>