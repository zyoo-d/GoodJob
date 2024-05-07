<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
    <div id="itvWriteContainer">
        <section class="page-hero pt-16 pb-6">
            <div class="container">
                <div class="card" id="itvWriteQnA">
                    <div class="card-content-wrapper">
                        <div class="card-content">
                            <div class="px-4 text-center">
                                <h1 class="mt-3 gradiTitle">
                                    <span>스터디 게시판</span>
                                </h1>
                            </div>

                            <div class="row mb-8">
                                <div class="form-group mt-8">
                                    <div class="qna-view-form qnaview recruit-present-info">
                                        <p class="company">${dto.cp_name}</p>
                                        <p>🗓️ 모집 마감일 : ~ ${dto.std_duedate}</p>
                                    </div>
                                    <div class="recruit-present-info mb-2">
                                        <h2>${dto.std_title}</h2>
                                        <c:if test="${dto.std_ing=='N'}">
                                            <a class="tag" href="#">모집중</a>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="textarea-group">
                                    <textarea cols="30" rows="10" readonly>${dto.std_content}</textarea>
                                </div>

                                <div class="writer-info">
                                    <p class="writer">${dto.nickname}</p>

                                    <p class="write-date">${dto.std_regdate}</p>
                                    <c:if test="${not empty id && (dto.id==id || lv == '2')}">
                                        <a href="/good/user/study/editstudy.do?std_seq=${dto.std_seq}">(수정</a>
                                        <input type="button" id="btnDelStudy" value="/ 삭제)">
                                    </c:if>
                                </div>
                            </div>
                            <div class="moving-btn">
                                <a
                                    href="/good/user/study/liststudy.do?column=${column}&word=${word}&page=${page}"
                                    class="btn btnList">목록으로</a>
                            </div>
                        </div>
                    </div>
                    <div class="comment-list">
                        <form>
                            <div class="add-comment">
                                <span><input type="text" placeholder="댓글을 입력하세요"
                                    name="viewStudyComment"></span>
                                <button type="button" class="btn" id="btnAddComment">
                                    <span class="material-symbols-outlined">done_outline</span>
                                </button>
                            </div>
                        </form>

                        <form class="comment-form" action="#" method="POST">
                            <table id="comment">
                                <tbody>
                                    <tr>
                                        <th>번호</th>
                                        <th>댓글</th>
                                        <th>댓글정보</th>
                                    </tr>
                                </tbody>
                            </table>

                            <!-- 댓글 페이징 -->
                            <div class="comment-paging">
                                <button type="button" class="btn more-comment">댓글 더보기</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <%@include file="/WEB-INF/views/inc/footer.jsp"%>
    <script>
        $('#btnDelStudy').click(function() {
            if (confirm("게시글을 삭제하시겠습니까?")) {
                location.href = "/good/user/study/delstudy.do?std_seq=${dto.std_seq}";
            }
        });

        $("#btnAddComment").click(function() {
            var commentContent = $("input[name='viewStudyComment']").val();
            var stdSeq = ${dto.std_seq};

            if (commentContent.trim() === "") {
                alert("댓글 내용을 입력해주세요.");
                return;     
            }

            $.ajax({
                url: "/good/user/comment/addcomment.do",
                type: "POST",
                data: {   
                    std_seq: stdSeq,
                    content: commentContent
                },
                dataType: 'json',
                success: function(response) {
                	 console.log(response);
                	 var newComment = response.dto; 
                
                     alert("댓글이 추가되었습니다.");
                    var row = "<tr>" +
                        "<td class='comment-num'>" + ($("#comment tbody tr").length + 1) + "</td>" +
                        "<td class='commentContent'><p>" + newComment.STD_CM_CONTENT + "</p></td>" +
                        "<td class='commentInfo'>" +
                        "<div><p>" + newComment.STD_CM_REGDATE + "</p>" +
                        "<div class='comment-edit'><p>" + newComment.NICKNAME + "</p>" +
                        "<div class='comment-icon'>" +
                        "<span class='material-symbols-outlined'>delete</span>" +
                        "<span class='material-symbols-outlined'>edit_note</span>" +
                        "</div></div></div></td></tr>";

                    // 새로운 댓글을 목록에 추가
                    $("#comment tbody").append(row);

                    // 사용자에게 댓글이 추가되었음을 알림
                    alert("댓글이 추가되었습니다.");

                    $("input[name='viewStudyComment']").val(""); // 댓글 입력 필드 초기화
                },
                error: function() {
                    alert("댓글 작성 중 오류가 발생했습니다.");
                }
            });
        });

     
       /*  $(document).ready(function() {
            $.ajax({
                url: "/good/user/comment/Listcomment.do",
                type: "POST",
                data: { std_seq: ${dto.std_seq} },
                success: function(response) {
                	console.log(response);
                    $("#comment tbody").empty(); // 기존 댓글 목록 비우기
                    $.each(response, function() {
                        var row = "<tr>" +
                            "<td class='comment-num'>" + ${response.dto.STD_CM_SEQ} + "</td>" +
                            "<td class='commentContent'><p>" + ${response.dto.STD_CM_CONTENT} + "</p></td>" +
                            "<td class='commentInfo'>" +
                            "<div><p>" + ${response.dto.STD_CM_REGDATE} + "</p>" +
                            "<div class='comment-edit'><p>" + ${response.dto.NICKNAME} + "</p>" +
                            "<div class='comment-icon'>" +
                            "<span class='material-symbols-outlined'>delete</span>" +
                            "<span class='material-symbols-outlined'>edit_note</span>" +
                            "</div></div></div></td></tr>";
                        $("#comment tbody").append(row);
                    });
                },
                error: function() {
                    alert("댓글 목록을 가져오는 중 오류가 발생했습니다.");
                }
            });
        });  */
    </script>
</body>
</html>