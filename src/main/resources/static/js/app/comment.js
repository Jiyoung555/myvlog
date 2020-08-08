// 데이터 전송 객체 생성!
var comment = {

  // 이벤트 등록
  init: function() {
    var _this = this;
    // 생성 버튼 클릭 시!
    const createBtn = document.querySelector('#comment-create-btn');

    // 댓글 “수정 완료” 버튼들
    const updateBtns = document.querySelectorAll('.comment-update-btn');

    //수정-취소 버튼 서로 토글
    const editBtns = document.querySelectorAll('.comment-edit-btn');

    // 이벤트 감시 시, 수행할 메소드 연결!
    if(createBtn){
      createBtn.addEventListener('click', function(){
        _this.create();
      });
    }

    // 모든 수정 버튼별, 이벤트 등록
    if(updateBtns){
      updateBtns.forEach(function(item) {
        item.addEventListener('click', function() { // 클릭 이벤트 발생시,
          var form = this.closest('form'); // 클릭 이벤트가 발생한 버튼에 제일 가까운 폼을 찾고,
          _this.update(form); // 해당 폼으로, 업데이트 수행한다!
        });
      });
    }

    editBtns.forEach(function(item) { // item은 -> 여러개 editBtns 버튼들 중에 -> for each문으로 하나 꺼내온 거
      item.addEventListener('click', function() {
        if(item.innerHTML == '수정') {
          item.innerHTML = '취소';
        }else {
          item.innerHTML = '수정';
        }
      });
    });


  },

  // 댓글 등록
  create: function() {
    // 데이터
    ﻿// 댓글은 아직 작성되지 않았음. 따라서 댓글의 id값은 아직 없음
    var data = {
      author: document.querySelector('#comment-author').value,
      content: document.querySelector('#comment-content').value,
    };

    // url에서 article의 id를 추출
    // ﻿댓글 작성시, 그 댓글을 -> 게시글에 붙여야함 -> 따라서 게시글의 id가 필요함
    var split = location.pathname.split('/');
    var articleId = split[split.length - 1];

    // Ajax 통신
    // - https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch
    // - https://t.ly/Vrrz
    fetch('/api/comments/' + articleId, { // 요청을 보냄
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(function(response) { // 응답 처리
      if (response.ok) { // 성공
        alert('댓글이 등록되었습니다.');
        window.location.reload(`/articles/${articleId}#comment`);
      } else { // 실패
        alert('댓글 등록 실패..!');
      }
    });
  },

  // 댓글 수정
  update: function(form) {
    // 데이터
    var data = {
      id: form.querySelector('#comment-id').value,
      author: form.querySelector('#comment-author').value,
      content: form.querySelector('#comment-content').value,
    };
    // url에서 article의 id를 추출!
    var split = location.pathname.split('/');
    var articleId = split[split.length - 1];
    // 비동기 통신
    fetch('/api/comments/' + data.id, { // 요청을 보냄
      method: 'PUT',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(function(response) { // 응답 처리
      if (response.ok) { // 성공
        alert('댓글이 수정되었습니다.');
      } else { // 실패
        alert('댓글 수정 실패..!');
      }
      window.location.reload(true); // 페이지 리로드
    });
  }






};

// 객체 초기화!
comment.init();
