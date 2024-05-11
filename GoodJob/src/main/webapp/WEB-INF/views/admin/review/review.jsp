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
						<h1>승인 대기 리뷰 관리</h1>
					</div>

					<div class="section-body">

						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h4 style="color: rgb(52, 57, 94);">승인 대기 리뷰 목록</h4>
										<div class="card-header-form">
											<form>
												<div class="input-group">
													<a href="#" class="btn btn-icon icon-left btn-primary" style="margin-left: 10px;" onclick="SelectReview()"><i class="far fa-edit"></i> 일괄 승인</a>
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
													<th class="p-0 text-center">리뷰번호</th>
													<th class="p-0 text-center">회사명</th>
													<th class="p-0 text-center">작성자</th>
													<th class="p-0 text-center">작성일</th>
													<th class="p-0 text-center">승인상태</th>
												</tr>

												<c:forEach items="${list}" var="dto">
													<c:if test="${dto.cp_rv_confirm == 0}">
														<tr>
															<td class="p-0 text-center">
																  <div class="custom-checkbox custom-control">
                    <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-${dto.cp_rv_seq}" name="selectedReviews" value="${dto.cp_rv_seq}">
                    <label for="checkbox-${dto.cp_rv_seq}" class="custom-control-label">&nbsp;</label>
                </div>
															</td>
															<td class="p-0 text-center">${dto.cp_rv_seq}</td>
															<td class="p-0 text-center">${dto.cp_name}</td>
															<td class="p-0 text-center"><a
																href="/good/user/company/review/editreview.do?cp_rv_seq=${dto.cp_rv_seq}">${dto.id}</a></td>
															<td class="p-0 text-center">${dto.cp_rv_regdate}</td>
															<td class="p-0 text-center status"><c:if
																	test="${dto.cp_rv_confirm == 0}">대기</c:if> <c:if
																	test="${dto.cp_rv_confirm == 1}">승인</c:if> <c:if
																	test="${dto.cp_rv_confirm == 2}">거절</c:if></td>
														</tr>
													</c:if>
												</c:forEach>
											</table>
										</div>
									</div>
									<div class="card-footer text-center">
										<nav class="d-inline-block">
											<ul class="pagination mb-0">
												<li class="page-item disabled"><a class="page-link"
													href="#" tabindex="-1"><i class="fas fa-chevron-left"></i></a>
												</li>
												<li class="page-item active"><a class="page-link"
													href="#">1 <span class="sr-only">(current)</span></a></li>
												<li class="page-item"><a class="page-link" href="#">2</a>
												</li>
												<li class="page-item"><a class="page-link" href="#">3</a></li>
												<li class="page-item"><a class="page-link" href="#"><i
														class="fas fa-chevron-right"></i></a></li>
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
	<!-- Modal -->
	<div class="modal fade" id="reviewModal" tabindex="-1" role="dialog"
		aria-labelledby="reviewModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="reviewModalLabel">리뷰 상태 변경</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="reviewStatusForm" method="post"
						action="/good/admin/review/checkreview.do">

						<input type="hidden" name="cp_rv_seq" id="cp_rv_seq">
						<div class="form-group">
							<label for="reviewStatus">상태:</label> <select
								class="form-control" id="reviewStatus" name="action" required>
								<option value="">선택해주세요</option>
								<option value="approve">승인</option>
								<option value="reject">거절</option>
							</select>
						</div>
						<div class="form-group" id="rejectReasonGroup"
							style="display: none;">
							<label for="rejectReason">거절 사유:</label>
							<textarea class="form-control" id="rejectReason" name="rj_reason"
								rows="3" required></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">취소</button>
					<button type="submit" form="reviewStatusForm"
						class="btn btn-primary">변경</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Page Specific JS File -->
	<script src="/good/assets/js/page/components-table.js"></script>
	<script src="/good/assets/js/page/boardreport.js"></script>
	<script>
        $(document).ready(function() {
            $('table').on('click', '.status', function() {
                var cpRvSeq = $(this).data('cp-rv-seq');
                $('#cp_rv_seq').val(cpRvSeq);
                $('#reviewModal').modal('show');
            });

            $('table').on('click', '.review-status', function() {
                var cpRvSeq = $(this).data('cp-rv-seq');
                $('#cp_rv_seq').val(cpRvSeq);
                $($(this).data('target')).modal('show');
            });     

            $('#reviewStatusForm').submit(function(e) {
                e.preventDefault();
                var form = $(this);
                var url = form.attr('action');
                var data = form.serialize();

                if ($('#reviewStatus').val() === 'reject' && $('#rejectReason').val().trim() === '') {
                    alert('거절 사유를 입력해주세요.');
                } else {
                    $.ajax({
                        type: 'POST',
                        url: url,
                        data: data,
                        success: function(response) {
                            if (response.success) {
                                $('#reviewModal').modal('hide');
                                location.reload();
                            } else {
                                alert('리뷰 상태 변경에 실패했습니다. 다시 시도해주세요.');
                            }
                        }
                    });
                }
            });
            $('#reviewStatus').change(function() {
                if ($(this).val() === 'reject') {
                    $('#rejectReasonGroup').show();
                } else {
                    $('#rejectReasonGroup').hide();
                }
            });
            function SelectReview() {
                var selectedReviews = [];
                $('input[type="checkbox"][name="selectedReviews"]:checked').each(function() {
                    selectedReviews.push($(this).val());
                });

                if (selectedReviews.length > 0) {
                    $.ajax({
                        type: 'POST',
                        url: '/good/admin/review/review.do',
                        data: { cp_rv_seqs: selectedReviews },
                        traditional: true,
                        success: function(response) {
                            if (response.success) {
                                alert('승인이 완료되었습니다.');
                                location.reload();
                            } else {
                                alert('일괄 승인에 실패했습니다. 다시 시도해주세요.');
                            }
                        },
                        error: function() {
                            alert('일괄 승인 요청 중 오류가 발생했습니다.');
                        }
                    });
                }
            }
            
        </script>
</body>
</html>