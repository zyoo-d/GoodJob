/**
 *
 * You can write your JS code here, DO NOT touch the default style file
 * because it will make it harder for you to update.
 * 
 */

"use strict";


  // custom.js
$(document).ready(function() {
  // 현재 페이지의 URL 가져오기
  var currentPage = window.location.pathname;

  // 모든 메뉴 항목에서 active 클래스 제거
  $('.sidebar-menu li').removeClass('active');
  $('.sidebar-menu .dropdown-menu li').removeClass('active');

  // 메뉴 항목 활성화
  if (currentPage.includes('/user/')) {
    $('#menu-user').addClass('active');
    if (currentPage.includes('/usermanagement.jsp')) {
      $('#submenu-usermanagement').addClass('active');
    } else if (currentPage.includes('/userblock.jsp')) {
      $('#submenu-userblock').addClass('active');
    } else if (currentPage.includes('/userreport.jsp')) {
      $('#submenu-userreport').addClass('active');
    }
  } else if (currentPage.includes('/board/')) {
    $('#menu-board').addClass('active');
    if (currentPage.includes('/boardreport.jsp')) {
      $('#submenu-boardreport').addClass('active');
    }
  } else if (currentPage.includes('/company/')) {
    $('#menu-company').addClass('active');
    if (currentPage.includes('/addcompany.jsp')) {
      $('#submenu-addcompany').addClass('active');
    } else if (currentPage.includes('/company.jsp')) {
      $('#submenu-company').addClass('active');
    }
  } else if (currentPage.includes('/review/')) {
    $('#menu-review').addClass('active');
    if (currentPage.includes('/reviewmain.jsp')) {
      $('#submenu-reviewmain').addClass('active');
    } else if (currentPage.includes('/review.jsp')) {
      $('#submenu-review').addClass('active');
    }
  } else if (currentPage.includes('/survey/')) {
    $('#menu-survey').addClass('active');
    if (currentPage.includes('/addsurvey.jsp')) {
      $('#submenu-addsurvey').addClass('active');
    } else if (currentPage.includes('/survey.jsp')) {
      $('#submenu-survey').addClass('active');
    }
  }
});



// 로고 업로드 및 미리보기 기능
    const logoUpload = document.getElementById('logo-upload');
    const logoUploadLabel = document.querySelector('label[for="logo-upload"]');
    const logoUploadBtn = document.getElementById('logo-upload-btn');
    const logoPreviewContainer = document.getElementById('addcompany-logo');
    const logoPreview = document.getElementById('addcompany-logo-preview');
    const logoIcon = document.getElementById('addcompany-logo-icon');
  
    logoUpload.addEventListener('change', function(event) {
      const file = event.target.files[0];
      const reader = new FileReader();
  
      reader.onload = function(event) {
        logoPreview.src = event.target.result;
        logoPreview.style.display = 'block';
        logoIcon.style.display = 'none';
      };
  
      reader.onerror = function(event) {
        alert('이미지 파일을 읽는 도중 에러가 발생했습니다.');
      };
  
      if (file) {
        reader.readAsDataURL(file);
        logoUploadLabel.textContent = file.name;
      } else {
        logoPreview.src = '';
        logoPreview.style.display = 'none';
        logoIcon.style.display = 'block';
        logoUploadLabel.textContent = '파일 선택';
      }
    });
  
    logoUploadBtn.addEventListener('click', function() {
      logoUpload.click();
    });
  
    logoPreviewContainer.addEventListener('click', function() {
      logoUpload.click();
    });
    
    const adminLink = document.getElementById('adminLink');

  adminLink.addEventListener('click', () => {
    location.href = '/good/admin/main.do';
  });

  document.addEventListener('keydown', (event) => {
	    if (event.ctrlKey && event.shiftKey && event.code === 'KeyA') {
	      location.href = '/good/admin/main.do';
	    }
	  });