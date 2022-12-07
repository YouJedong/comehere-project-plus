document.getElementById('postButton').addEventListener('click', checkLoginAndWrite);

// 뒤로가기하면 자동으로 새로고침 (조회수 반영을 위해)
window.onpageshow = function(e) {
  if (e.persisted || (window.performance && window.performance.navigation.type === 2)) {
    window.location.reload();
  }
}

// 로그인 상태일 때 유효성 검사를 위한 번호 저장
let loginMemberNo = -1;
if($(".loginId").attr("id") != -1) {
 loginMemberNo = $(".loginId").attr("id");
};

// 비회원일때 로그인으로 유도
function checkLoginAndWrite() {
  if (loginMemberNo == undefined || loginMemberNo == -1) {
    swal({
      text :  "회원만 이용가능합니다.\n로그인하시겠습니까?",
      icon : "warning",
      buttons : ["아니요", "예"],
    }).then(function(isConfirm) {
      if (isConfirm) {
        location.href = '../auth/form';
      } else{
        return false;
      }
    });
  } else{
    location.href = $("#postButton").attr("value"); 
  }
}

// 페이지가 검색결과 list일 때 검색조건의 type을 selected하기 위한 메서드 
const searchOption = $("select").children();
for (let i = 0; i < searchOption.length; i++) {
  if (searchOption.eq(i).val() == "[[${search.type}]]") {
    $('#searchId').find("option").eq(i).attr("selected", true)
  }
} 

// url을 통해 게시판 종류를 찾아 타이틀 명 삽입
window.addEventListener('DOMContentLoaded', function() {
    var param = window.location.search.match(/no=([^&]*)/)[1];
    switch(param) {
      case '1' : document.getElementById( 'cateName' ).innerHTML = "자유게시판"; break;
      case '2' : document.getElementById( 'cateName' ).innerHTML = "식단게시판"; break;
      case '3' : document.getElementById( 'cateName' ).innerHTML = "챌린지게시판"; break;
      case '4' : document.getElementById( 'cateName' ).innerHTML = "질문게시판"; break;
      case '5' : document.getElementById( 'cateName' ).innerHTML = "핫딜게시판";
    }
});
  
// 현재 페이지 버튼 Active 시키기
window.addEventListener('DOMContentLoaded', function() {
  var param = 1; 
  if (window.location.search.match(/page=([^&]*)/) != null) {
    param = window.location.search.match(/page=([^&]*)/)[1];
  } 
  $('#pageButton' + param).addClass('active');
});