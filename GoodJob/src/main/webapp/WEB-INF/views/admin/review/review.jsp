<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                        <h1>리뷰 관리</h1>
                    </div>

                    <div class="section-body">
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="review-h4">승인 대기 목록</h4>
                                    </div>
                                    <div class="card-body p-0">
                                        <div class="table-responsive">
                                            <table class="table table-striped">
                                                <tr>
                                                    <th class="p-0 text-center">리뷰 번호</th>
                                                    <th class="p-0 text-center">회사명</th>
                                                    <th class="p-0 text-center">작성자</th>
                                                    <th class="p-0 text-center">작성일</th>
                                                    <th class="p-0 text-center">상태</th>
                                                </tr>
                                                <c:forEach items="${list}" var="dto">
                                                    <c:if test="${dto.cp_rv_confirm == 0}">
                                                        <tr>
                                                            <td class="p-0 text-center">${dto.cp_rv_seq}</td>
                                                            <td class="p-0 text-center">${dto.cp_name}</td>
                                                            <td class="p-0 text-center"><a href="/good/user/company/review/editreview.do?cp_rv_seq=${dto.cp_rv_seq}">${dto.id}</a></td>
                                                            <td class="p-0 text-center">${dto.cp_rv_regdate}</td>
                                                            <td>
                                                                <c:if test="${dto.cp_rv_confirm == 0}">대기</c:if>
                                                                <c:if test="${dto.cp_rv_confirm == 1}">승인</c:if>
                                                                <c:if test="${dto.cp_rv_confirm == 2}">거절</c:if>
<button type="button" class="btn btn-primary review-status" data-cp-rv-seq="${dto.cp_rv_seq}" data-target="#reviewModal">변경</button>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="card-footer text-center">
                                        <nav class="d-inline-block">
                                            <ul class="pagination mb-0">${pagebar}</ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="review-h4">승인 반려 목록</h4>
                                    </div>
                                    <div class="card-body p-0">
                                        <div class="table-responsive">
                                            <table class="table table-striped">
                                                <tr>
                                                    <th class="p-0 text-center">리뷰 번호</th>
                                                    <th class="p-0 text-center">회사명</th>
                                                    <th class="p-0 text-center">작성자</th>
                                                    <th class="p-0 text-center">작성일</th>
                                                    <th class="p-0 text-center">상태</th>
                                                </tr>
                                                <c:forEach items="${list}" var="dto">
                                                    <c:if test="${dto.cp_rv_confirm == 2}">
                                                        <tr>
                                                            <td class="p-0 text-center">${dto.cp_rv_seq}</td>
                                                            <td class="p-0 text-center">${dto.cp_name}</td>
                                                            <td class="p-0 text-center">${dto.id}</td>
                                                            <td class="p-0 text-center">${dto.cp_rv_regdate}</td>
                                                            <td class="p-0 text-center">거절</td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="card-footer text-center">
                                        <nav class="d-inline-block">
                                            <ul class="pagination mb-0">${pagebar}</ul>
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

    <!-- Modal -->
    <div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" aria-labelledby="reviewModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="reviewModalLabel">리뷰 상태 변경</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="reviewStatusForm" method="post" action="/good/admin/review/checkreview.do">

                        <input type="hidden" name="cp_rv_seq" id="cp_rv_seq">
                        <div class="form-group">
                            <label for="reviewStatus">상태:</label>
                            <select class="form-control" id="reviewStatus" name="action" required>
                                <option value="">선택해주세요</option>
                                <option value="approve">승인</option>
                                <option value="reject">거절</option>
                            </select>
                        </div>
                        <div class="form-group" id="rejectReasonGroup" style="display: none;">
                            <label for="rejectReason">거절 사유:</label>
                            <textarea class="form-control" id="rejectReason" name="rj_reason" rows="3" required></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                    <button type="submit" form="reviewStatusForm" class="btn btn-primary">변경</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Page Specific JS File -->
    <script src="/good/assets/js/page/components-table.js"></script>
    <script>
        $(document).ready(function() {
            $('table').on('click', '.review-status', function() {
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
        });
    </script>
</body>
</html>