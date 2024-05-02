"use strict";

$("#modal-1").fireModal({body: '차단회원 관리.'});
$("#modal-2").fireModal({body: 'Modal body text goes here.', center: true});

let modal_3_body = '<p>Object to create a button on the modal.</p><pre class="language-javascript"><code>';
modal_3_body += '[\n';
modal_3_body += ' {\n';
modal_3_body += "   text: 'Login',\n";
modal_3_body += "   submit: true,\n";
modal_3_body += "   class: 'btn btn-primary btn-shadow',\n";
modal_3_body += "   handler: function(modal) {\n";
modal_3_body += "     alert('Hello, you clicked me!');\n"
modal_3_body += "   }\n"
modal_3_body += ' }\n';
modal_3_body += ']';
modal_3_body += '</code></pre>';
$("#modal-3").fireModal({
  title: 'Modal with Buttons',
  body: modal_3_body,
  buttons: [
    {
      text: 'Click, me!',
      class: 'btn btn-primary btn-shadow',
      handler: function(modal) {
        alert('Hello, you clicked me!');
      }
    }
  ]
});

$("#modal-4").fireModal({
  footerClass: 'bg-whitesmoke',
  body: 'Add the <code>bg-whitesmoke</code> class to the <code>footerClass</code> option.',
  buttons: [
    {
      text: 'No Action!',
      class: 'btn btn-primary btn-shadow',
      handler: function(modal) {
      }
    }
  ]
});

$("#modal-5").fireModal({
  title: 'Login',
  body: $("#modal-login-part"),
  footerClass: 'bg-whitesmoke',
  autoFocus: false,
  onFormSubmit: function(modal, e, form) {
    // Form Data
    let form_data = $(e.target).serialize();
    console.log(form_data)

    // DO AJAX HERE
    let fake_ajax = setTimeout(function() {
      form.stopProgress();
      modal.find('.modal-body').prepend('<div class="alert alert-info">Please check your browser console</div>')

      clearInterval(fake_ajax);
    }, 1500);

    e.preventDefault();
  },
  shown: function(modal, form) {
    console.log(form)
  },
  buttons: [
    {
      text: 'Login',
      submit: true,
      class: 'btn btn-primary btn-shadow',
      handler: function(modal) {
      }
    }
  ]
});

$("#modal-6").fireModal({
  body: '<p>Now you can see something on the left side of the footer.</p>',
  created: function(modal) {
    modal.find('.modal-footer').prepend('<div class="mr-auto"><a href="#">I\'m a hyperlink!</a></div>');
  },
  buttons: [
    {
      text: 'No Action',
      submit: true,
      class: 'btn btn-primary btn-shadow',
      handler: function(modal) {
      }
    }
  ]
});

$('.oh-my-modal').fireModal({
  title: 'My Modal',
  body: 'This is cool plugin!'
});

let modalInputs;
let selectedUser = {};

$('#btn-block-user').fireModal({
  body: $('#modal-part'),
  title: '회원 정보 변경',
  footerClass: 'text-right',
  buttons: [
    {
      text: '저장',
      class: 'btn btn-primary',
      handler: function(modal) {
        console.log('저장 버튼 클릭됨');
      }
    }
  ],
  created: function(modal) {
    modalInputs = {
      userId: modal.find('#user-id'),
      blockDate: modal.find('#block-date'),
      releaseDate: modal.find('#release-date'),
      reason: modal.find('#block-reason'),
      status: modal.find('#status')
    };
  }
});


$(document).on('change', 'input[type="checkbox"][data-checkboxes="mygroup"]', function() {
  if ($(this).is(':checked')) {
    const row = $(this).closest('tr');
    selectedUser = {
      id: row.find('td:nth-child(2)').text(), // 아이디 열
      blockDate: row.find('td:nth-child(3)').text(), // 차단일자 열
      releaseDate: row.find('td:nth-child(4)').text(), // 해제일자 열
      reason: row.find('td:nth-child(5)').text(), // 사유 열
      status: row.find('td:nth-child(6)').text() // 상태 열
    };

    modalInputs.userId.val(selectedUser.id);
    modalInputs.blockDate.val(selectedUser.blockDate);
    modalInputs.releaseDate.val(selectedUser.releaseDate);
    modalInputs.reason.val(selectedUser.reason);
    modalInputs.status.val(selectedUser.status);
  } else {
    selectedUser = {};
    modalInputs.userId.val('');
    modalInputs.blockDate.val('');
    modalInputs.releaseDate.val('');
    modalInputs.reason.val('');
    modalInputs.status.val('정상');
  }

  const checkedUsers = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
  const userCount = checkedUsers.length;
  bulkModalInputs.userCount.val(userCount + '명');
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
      handler: function(modal) {
        // 일괄 처리 로직 작성
        console.log('일괄 처리 버튼 클릭됨');
      }
    }
  ],
  created: function(modal) {
    bulkModalInputs = {
      userCount: modal.find('#bulk-user-count'),
      status: modal.find('#bulk-status'),
      changeReason: modal.find('#bulk-change-reason')
    };

    // 체크박스 선택된 유저 수 계산
    const checkedUsers = $('input[type="checkbox"][data-checkboxes="mygroup"]:checked');
    const userCount = checkedUsers.length;
    bulkModalInputs.userCount.val(userCount + '명');
  }
});