<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<footer class="footer bg-theme-light/50">
	<div class="container max-w-[1440px]">
		<div
			class="footer-copyright mx-auto border-t border-border pb-10 pt-7 text-center">
			<p>
				문의 사항은 <a href="#!">ITsgoodjob@goodjob.com</a>으로 연락주세요.<br><br>
				<span id="adminLink">©</span>IT's Good Job. All rights reserved. 
			</p>
		</div>
	</div>
</footer>

<script>
  const adminLink = document.getElementById('adminLink');

  adminLink.addEventListener('click', (event) => {
    if (event.ctrlKey && event.altKey && event.button === 0) {
      // Ctrl + Alt + 좌클릭 시 관리자 페이지로 이동
      location.href = '/good/admin/main.do';
      event.stopPropagation(); // 이벤트 전파 방지
    }
  });
</script>