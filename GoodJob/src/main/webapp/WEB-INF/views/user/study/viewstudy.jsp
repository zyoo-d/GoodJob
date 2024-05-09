<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
.font-sm {
	font-size: 0.8rem;
}
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
								<thead>
									<tr>
										<th>댓글</th>
										<th>정보</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${clist}" var="cdto" begin="0" end="9">
										<tr>
											<td class='commentContent'><p>${cdto.content}</p></td>
											<td class='commentInfo'><div>
													<p class="font-sm">${cdto.regdate}</p>
													<div class='comment-edit'>
														<p>${cdto.nickname}</p>
											 			<div class='comment-icon'>
															<c:if
																test="${not empty id && (cdto.id == id || lv == 2)}">
																<span class='material-symbols-outlined'
																	onclick="del(${cdto.cm_seq});">delete</span>
																<span class='material-symbols-outlined'
																	onclick="edit(${cdto.cm_seq});">edit_note</span>
															</c:if>
														</div>
													</div>
												</div></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<!-- 댓글 페이징 -->
							<div class="comment-paging">
								<button type="button" class="btn more-comment"
									id="btnMoreComment">댓글 더보기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
	
		var page = 1;
    	var size = 10;
    	let commentBegin = ${clist.size() + 1};
    	var stdSeq = ${dto.std_seq};
		console.log(commentBegin);
		console.log(stdSeq);
    	
		$('#btnDelStudy')
				.click(
						function() {
							if (confirm("게시글을 삭제하시겠습니까?")) {
								location.href = "/good/user/study/delstudy.do?std_seq=${dto.std_seq}";
							}
						});

		$("#btnAddComment")
		.click(
				function() {
					
					var commentContent = $(
							"input[name='viewStudyComment']").val();
					
				 	var userId = "${sessionScope.id}";
				    if (!userId) {
				        alert("로그인해주세요."); 
				        return;
				    } 
					
					if (commentContent.trim() === "") {
						alert("댓글 내용을 입력해주세요.");
						return;
					}

					$.ajax({
								url : "/good/board/study/stdaddcomment.do",
								type : "POST",
								data : {
									std_seq : stdSeq,
									content : commentContent
								},
								dataType : 'json',
								success : function(response) {
									console.log(response);
									var newComment = response.dto;
								
									var row = "<tr>"
											+ "<td class='commentContent'><p>"
											+ newComment.STD_CM_CONTENT
											+ "</p></td>"
											+ "<td class='commentInfo'>"
											+ "<div><p class='font-sm'>"
											+ newComment.STD_CM_REGDATE
											+ "</p>"
											+ "<div class='comment-edit'><p>"
											+ newComment.NICKNAME
											+ "</p>"
											+ "<div class='comment-icon'>"
											+ "<span class='material-symbols-outlined'>delete</span>"
											+ "<span class='material-symbols-outlined'>edit_note</span>"
											+ "</div></div></div></td></tr>";

									// 새로운 댓글을 목록에 추가
									$("#comment tbody").prepend(row);

									// 사용자에게 댓글이 추가되었음을 알림
									alert("댓글이 추가되었습니다.");

									$("input[name='viewStudyComment']")
											.val(""); // 댓글 입력 필드 초기화
								},
								error : function() {
									alert("댓글 작성 중 오류가 발생했습니다.");
								}
							});
				});
		
			
			<c:if test="${clist.size() == 0}">
				$('#btnMoreComment').hide();
			</c:if>
			
			$('#btnMoreComment').click(() => {
				
				//where rnum between 11 and 20;
				//where rnum between 21 and 30;
				$.ajax({
					type: 'GET',
					url: '/good/board/study/stdmorecomment.do',
					data: {
						commentBegin: commentBegin,
						bseq: stdSeq
					},
					dataType: 'json',
					success: function(response) {
						console.log(response);

						if (response.length > 0) {

							commentBegin += 10;
							
							response.forEach(obj => {

								 let temp = "<tr>" +
						            "<td class='commentContent'><p>" + obj.content + "</p></td>" +
						            "<td class='commentInfo'>" +
						            "<div><p class='font-sm'>" + obj.regdate + "</p>" +
						            "<div class='comment-edit'><p>" + obj.name + "</p>" +
						            "<div class='comment-icon'>" +
						            "<span class='material-symbols-outlined'>delete</span>" +
						            "<span class='material-symbols-outlined'>edit_note</span>" +
						            "</div></div></div></td></tr>";
								
								if ('${id}' != '' && (obj.id == '${id}' || ${lv == 2})) {
								
								temp += `		<div>
													<span class="material-symbols-outlined" onclick="del(\${obj.seq});">delete</span>
													<span class="material-symbols-outlined" onclick="edit(\${obj.seq});">edit_note</span>
												</div>`;
								}		
												
								temp += `	</div>
										</td>
									</tr>
								`;
								
								$('#comment tbody').append(temp);
								
							});
							
						} else {
							alert('더 이상 가져올 댓글이 없습니다.');
						}
						
					},
					error: function(a,b,c) {
						console.log(a,b,c);
					}
				});
				
			});
			
			function edit(seq) {
			    $('.commentEditRow').remove();
			    
			    let content = $(event.target).parents('tr').find('td.commentContent p').text();
			    
			    $(event.target).parents('tr').after(`
			        <tr class="commentEditRow">
			            <td><input type="text" name="content" class="full" required value="${content}" id="txtComment"></td>
			            <td class="commentEdit">
			                <span class="material-symbols-outlined" onclick="editComment(\${seq});">edit_square</span>
			                <span class="material-symbols-outlined" onclick="$(event.target).parents('tr').remove();">close</span>
			            </td>
			        </tr>
			    `);
			}

			function editComment(seq) {
				    let td = $(event.target).closest('tr').prev().find('td.commentContent p');
				    let tr = $(event.target).closest('tr');
				    let editedContent = tr.find('input[name=content]').val(); // Retrieve edited content from input field

			    
			    $.ajax({
			        type: 'POST',
			        url: '/good/board/comment/stdeditcomment.do',
			        data: {
			            seq: seq,
			            content: $(event.target).parents('tr').find('input[name=content]').val()
			        },
			        dataType: 'json',
			        success: function(result) {
			            if (result.result == '1') {
			            	td.text(editedContent); 
			                tr.remove();
			            } else {
			                alert('댓글 수정을 실패했습니다.');
			            }
			        },
			        error: function(a, b, c) {
			            console.log(a, b, c);
			        }
			    });
			}
			
			
			function del(seq) {
			    $('.commentEditRow').remove();

			    let tr = $(event.target).closest('tr');

			    if (confirm('삭제하겠습니까?')) {
			        $.ajax({
			            type: 'POST',
			            url: '/good/board/comment/stddelcomment.do', // 삭제 요청을 처리할 URL 수정
			            data: { seq: seq }, // 데이터를 객체 형태로 전달
			            dataType: 'json',
			            success: function(result) {
			                if (result.result == 1) {
			                    tr.remove();
			                } else {
			                    alert('댓글 삭제를 실패했습니다.');
			                }
			            },
			            error: function(a, b, c) {
			                console.log(a, b, c);
			            }
			        });
			    }
			}

		
	</script>
</body>
</html>