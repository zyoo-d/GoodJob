<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        #modal {
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: flex;
            justify-content: center;
            align-items: center;
            opacity: 0;
            pointer-events: none;
        }

        #modal.show {
            opacity: 1;
            pointer-events: auto;
        }

        #modal-content {
            position: relative;
            background-color: white;
            padding: 40px;
            border-radius: 4px;
            width: 400px;
            max-height: calc(100vh - 100px);
            overflow-y: auto;
        }

        #report-description {
            width: 100%;
            height: 120px;
            margin-bottom: 10px;
            outline: none;
            border: 1px solid #EAEAEA;
            resize: none;
            padding: 10px;
            box-sizing: border-box;
            font-size: 16px;
        }

        #report-header {
            margin-top: 0;
            padding-bottom: 10px;
            border-bottom: 1px solid black;
            margin-bottom: 20px;
        }

        #report-reasons label {
            display: block;
            margin-bottom: 15px;
            color: #646B7F;
        }
        
        #report-reasons input[type="radio"] {
        	margin-right: 10px;
        }
        
        

        #report-actions {
            text-align: center;
            margin-top: 20px;
        }

        #cancel-btn {
            position: absolute;
            top: 0px;
            right: 20px;
            font-size: 40px;
            background: none;
            border: none;
            cursor: pointer;
        }

        #report-btn {
            width: 60%;
            height: 50px;
            padding: 10px;
            background-color: #353535;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        
        
        
    </style>
</head>
<body>
    <div id="modal">
        <div id="modal-content">
            <button id="cancel-btn">&times;</button>
            <h2 id="report-header">신고하기</h2>
            <form id="report-form" action="/good/user/report/addreport.do" method="POST">
                <input type="hidden" name="seq" id="seq-input" value="">
                <input type="hidden" id="boardtype" name="boardtype" value="">
                <div id="report-reasons">
                    <label><input type="radio" name="reason" value="1">비방/욕설</label>
                    <label><input type="radio" name="reason" value="2">허위사실</label>
                    <label><input type="radio" name="reason" value="3">개인정보노출</label>
                    <label><input type="radio" name="reason" value="4">음란성</label>
                    <label><input type="radio" name="reason" value="5">게시글 도배</label>
                    <label><input type="radio" name="reason" value="6">부적절한 홍보</label>
                    <label><input type="radio" name="reason" value="7">기타</label>
                </div>
                <textarea id="report-description" name="description" placeholder="신고 사유를 입력해 주세요."></textarea>
                <div id="report-actions">
                    <button id="report-btn" type="submit" onclick="validateForm()">신고하기</button>
                </div>
            </form>
        </div>
    </div>

     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function hideModal() {
            var modal = document.getElementById("modal");
            modal.classList.remove("show");
        }

        $(document).ready(function() {
            $("#report-btn").click(function() {
                var boardtype = $(this).data("boardtype");
                var seq = $(this).data("seq");
                $("#boardtype").val(boardtype);
                $("#seq-input").val(seq);

                // 모달창 열릴 때마다 초기화
                $('input[name="reason"]').prop('checked', false);
                $("#report-description").val("");

                $("#modal").addClass("show");
            });

            $("#cancel-btn").click(function() {
                $("#modal").removeClass("show");
            });

            $(window).click(function(event) {
                if (event.target == $("#modal")[0]) {
                    $("#modal").removeClass("show");
                }
            });
        });

    </script>
</body>
</html>