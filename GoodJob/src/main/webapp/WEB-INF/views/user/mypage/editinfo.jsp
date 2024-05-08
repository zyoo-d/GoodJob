<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>개인정보</title>
    <%@include file="/WEB-INF/views/inc/adminasset.jsp"%>
    <%@include file="/WEB-INF/views/inc/mypageheader.jsp"%>
</head>
<body>
    <div class="main-content">
        <section class="section">
            <div class="section-header">
                <h1>개인정보</h1>
                <div class="section-header-breadcrumb">
                    <div class="breadcrumb-item active">
                        <a href="#">개인정보</a>
                    </div>
                    <div class="breadcrumb-item">회원정보수정</div>
                </div>
            </div>
            <div class="card card-primary" id="editInfo">
                <div class="card-header">
                    <h4>회원정보수정</h4>
                </div>
                <div class="card-body">
                    <form method="POST" action="/good/user/mypage/editinfo.do">
                        <div class="row">
                            <div class="form-group col-6">
                                <label for="id">ID</label> 
                                <input id="id" type="text" class="form-control" name="id" value="${id}" readonly>
                            </div>
                            <div class="form-group col-6">
                                <label for="name">이름</label> 
                                <input id="name" type="text" class="form-control" name="name" value="${name}" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-6">
                                <label for="password" class="d-block">비밀번호</label> 
                                <input id="password" type="password" class="form-control pwstrength" data-indicator="pwindicator" name="password" placeholder="8~16자 이내의 영어 대소문자, 숫자, (*), (-), (1)만 입력이 가능합니다." required>
                                <span id="pwError" style="color: tomato"></span>
                                <div id="pwindicator" class="pwindicator">
                                    <div class="bar"></div>
                                    <div class="label"></div>
                                </div>
                            </div>
                            <div class="form-group col-6">
                                <label for="pw_confirm" class="d-block">비밀번호 확인</label> 
                                <input id="pw_confirm" type="password" class="form-control" name="password-confirm" required>
                                <span id="pwCheckError" style="color: tomato"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label> 
                            <input id="email" type="email" class="form-control" name="email" placeholder="${email}" required>
                        </div>
                        <div class="form-group">
                            <label for="address">주소</label>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" id="address" name="address"  placeholder="${address}" required readonly>
                                <div class="input-group-append">
                                    <button class="btn btn-outline-primary" type="button" onclick="getAddress();">주소 찾기</button>
                                </div>
                            </div>
                        </div>  
                        <div class="form-group">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" name="agree" class="custom-control-input" id="agree"> 
                                <label class="custom-control-label" for="agree">회원정보 수정에 동의합니다.</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-primary btn-lg btn-block" id="btnEditInfo">정보 수정</button>
                        </div>
                        <input type="hidden" name="previousEmail" value="${email}"/>
                        <input type="hidden" name="previousAddress" value="${address}"/>
                    </form>
                </div>
            </div>
        </section>
    </div>
    <%@include file="/WEB-INF/views/inc/adminfooter.jsp"%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        $(document).ready(function() {
            function validatePassword() {
                var pwLimit = /^[a-zA-Z0-9!*-]{10,20}$/;
                if (!pwLimit.test($('#password').val())) {
                    $('#pwError').text("사용할 수 없는 비밀번호입니다.");
                    return false;
                } else {
                    $('#pwError').text("");
                    return true;
                }
            }

            function confirmPassword() {
                if ($('#password').val() !== $('#pw_confirm').val()) {
                    $('#pwCheckError').text("비밀번호가 일치하지 않습니다.");
                    return false;
                } else {
                    $('#pwCheckError').text("");
                    return true;
                }
            }

            $('#password, #pw_confirm').on('input', function() {
                validatePassword();
                confirmPassword();
            });

            $('#btnEditInfo').click(function() {
                if (!$('#agree').is(':checked')) {
                    alert('수정 사항을 확인하고 동의해 주세요.');
                    return;
                } else {
                    if (validatePassword() && confirmPassword()) {
                        $('form').submit(); // 모든 유효성 검사를 통과하면 form을 제출
                    }
                }
            });
        });

        function getAddress() {
            new daum.Postcode({
                oncomplete : function(data) {
                    var addr = '';
                    var extraAddr = '';

                    if (data.userSelectedType === 'R') {
                        addr = data.roadAddress;
                    } else {
                        addr = data.jibunAddress;
                    }

                    if (data.userSelectedType === 'R') {
                        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                            extraAddr += data.bname;
                        }
                        if (data.buildingName !== '' && data.apartment === 'Y') {
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        if (extraAddr !== '') {
                            extraAddr = ' (' + extraAddr + ')';
                        }
                    }

                    document.getElementById("address").value = addr;
                }
            }).open();
        }
    </script>
</body>
</html>
