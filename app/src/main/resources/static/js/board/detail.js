const boardNo = document.getElementById( 'boardNo').value;
const memberNo = document.getElementById( 'memberNo' ).value;
const lastPageNo = document.getElementsByClassName('page-link').length;
let pageNo = lastPageNo; // 로딩할 때 마지막 댓글 페이지가 보이게 셋팅
document.getElementById('commentButton').addEventListener('click', insertAndReplay);

// 로그인 상태일 때 유효성 검사를 위한 번호 저장
let loginMemberNo = -1;
if($(".loginId").attr("id") != undefined) {
 loginMemberNo = $(".loginId").attr("id");
};

// 특정 댓글 페이지로 이동
function getId(pageId) {
  pageNo = pageId.replace('pageNo', '')
  commentList();
}

// 현재 페이지 버튼 active 시키기
window.addEventListener('DOMContentLoaded', function(){
    $('#pageButton' + pageNo).addClass('active');
});
document.querySelectorAll('.page-item').forEach((pageBtn) => {
  pageBtn.addEventListener('click', function(e) {
      $('.page-item').removeClass('active');
      $(e.currentTarget).addClass('active');
  });
});

// 로딩 시 댓글 목록 출력 
$(document).ready(function(){
  commentList(); //페이지 로딩시 댓글 목록 출력 
});
// 댓글 목록 
function commentList(){
    $.ajax({
      url : '/app/boardComment/list',
      type : 'get',
      data : {boardNo: boardNo,
              pageNo: pageNo
              },
      success : function(result){
        var a =''; 
        $.each(result, function(key, value){ 
          a += '<div class="commentArea comment-area">';
          a += '<div class="commentInfo' + value.no + ' comment-info">';
          if (loginMemberNo == value.writer.no) {
            a += '<a id="udBnt' +value.no+ '" class="btn btn-outline-primary btn-sm btn-jd" onclick="commentUpdate(' + value.no + ',\'' + value.content + '\');"> 수정 </a>';
            a += '<a id="dlBnt' +value.no+ '" class="btn btn-outline-primary btn-sm btn-jd" onclick="commentDelete('+value.no+');"> 삭제 </a> </div>';
          }
          if (loginMemberNo != -1 && loginMemberNo != value.writer.no) {
              a += '<a class="btn btn-outline-primary btn-sm btn-jd" onclick="commentComplain('+value.no+');"> 신고 </a></div>';
          }
          if (loginMemberNo == -1) {
              a += '<a class="btn btn-outline-primary btn-sm btn-jd" onclick="alertToLoginByTattle();"> 신고 </a></div>';
          }
          a += '<div class="commentContent'+value.no+' comment-cont">'+value.content;
          a += '<div class="comment-writer">' +value.writer.nickname + '</div>';
          a += '</div></div></div>';
        });
        $(".commentList").html(a);
      }
    });
}
// 댓글 등록
function insertAndReplay(){
  if (!checkContentEmpty()) {
    $.ajax({
      url : "/app/boardComment/add",
      data : {
        content : $("#commentCont").val(),
        boardNo : boardNo,
        memberNo : memberNo
      },
      type : "post",
      success : function(result){
        if(result != null){
          checkMaxCommentCount()
          commentList();
          $("#commentCont").val("");
        }
      }
    })
  }
}
//댓글 삭제 
function commentDelete(no){
    swal({
        text : "댓글을 삭제하시겠습니까?",
        icon : "warning",
        buttons : ["아니요", "예"],
      }).then(function(isConfirm) {
        if (isConfirm) {
            $.ajax({
                url : '/app/boardComment/delete/'+no,
                type : 'post',
                success : function(result){
                    if(result == 1) {
                      commentList(); //댓글 삭제후 목록 출력
                      swal({
                        text: '댓글이 삭제되었습니다.',
                        icon: 'success',
                        closeOnClickOutside: false
                      }).then(() => {
                        checkMinCommentCount() // 웹 상에서 댓글이 0개면 reload
                      });
                    } else {
                      swal('','댓글을 삭제할 수 없습니다!','error');
                    }
                }
            });
        } else{
          return false;
        }
    });
}
//댓글 수정 모드- 댓글 내용 출력을 input 폼으로 변경 
function commentUpdate(commentNo, content){
  $('#udBnt'+commentNo).css('display', 'none');
  $('#dlBnt'+commentNo).css('display', 'none');
    var a ='';
    a += '<label for="form"></label>';
    a += '<div class="input-group">';
    a += '<textarea onkeypress="block_enter(event)" cols="40" rows="3" class="form-control update-form" name="content_' + commentNo + '">' + content + '</textarea>';
    a += '<span class="input-group-btn"><button class="btn btn-outline-primary btn-sm btn-jd" type="button" onclick="commentUpdateProc(' + commentNo + ');">수정</button></span>';
    a += '<span class="input-group-btn"><button class="btn btn-outline-primary btn-sm btn-jd" type="button" onclick="commentList()">취소</button></span>';
    a += '</div>';
    
    $('.commentContent'+commentNo).html(a);
}
// 댓글 수정
function commentUpdateProc(commentNo){
    var updateContent = $('[name=content_'+commentNo+']').val();
    $.ajax({
        url : '/app/boardComment/update',
        type : 'post',
        data : { 
          'content' : updateContent, 
          'no' : commentNo},
        success : function(result){
            if(result == 1) commentList(); //댓글 수정후 목록 출력 
        }
    });
}

// 유효성 체크
function checkContentEmpty(){
  if (document.getElementById('commentCont').value == '') {
    swal('등록 실패!', "내용을 입력해주세요.", 'error');
    return true;
  } else {
    return false;
  }
}
function checkMaxCommentCount() {
  let count = document.getElementsByClassName('commentArea').length;
  if (count == 5) {
    window.location.reload();
  }
};
function checkMinCommentCount() {
  var count = document.getElementsByClassName('commentArea').length;
  if (count == 0) {
    window.location.reload();
  }
};

// 비회원 댓글 작성 시 로그인 유도
function alertToLogin(){
    swal({
        text :  "댓글을 작성하려면 로그인을 해야합니다.\n로그인하시겠습니까?",
        icon : "warning",
        buttons : ["아니요", "예"],
      }).then(function(isConfirm) {
        if (isConfirm) {
          location.href = '../auth/form';
        } else{
          return false;
        }
    });
}
// 비회원 신고 시 로그인 유도 
function alertToLoginByTattle(){
    swal({
        text :  "신고를 하려면 로그인을 해야합니다.\n로그인하시겠습니까?",
        icon : "warning",
        buttons : ["아니요", "예"],
      }).then(function(isConfirm) {
        if (isConfirm) {
          location.href = '../auth/form';
        } else{
          return false;
        }
    });
}

// 댓글 작성 시 엔터 막기 (오류)
function block_enter(e){
   if(e.keyCode == 13){
     window.event.preventDefault();
   }
}

// 스크랩 관련 함수
if ($('#scrap-button').length != 0) {
  document.getElementById('scrap-button').addEventListener('click', insertScrap);
}
// 스크랩 등록
function insertScrap() {
    swal({
        text : "스크랩 하시겠습니까?",
        icon : "info",
        buttons : ["아니요", "예"],
      }).then(function(isConfirm) {
        if (isConfirm) {
            $.ajax({
                url : "/app/scrap/add",
                data : {
                  boardNo : boardNo,
                  memberNo : memberNo
                },
                type : "post",
                success : function(result){
                  if(result == 1){
                    swal('','게시글이 스크랩되었습니다.','success');
                  } else if (result == 0) {
                    swal({
                          text : "이미 스크랩한 게시글입니다.\n스크랩을 취소하겠습니까?",
                          icon : "warning",
                          buttons : ["아니요", "예"],
                      }).then(function(isConfirm) {
                        if (isConfirm) {
                          deleteScrap();
                        } else {
                          return false;
                        }
                    });
                  }
                }
             })
        } else{
          return false;
        }
    });
}

// 스크랩 취소
function deleteScrap() {
    $.ajax({ 
        url : '/app/scrap/delete',
        type : 'post',
        data : { 
            boardNo : boardNo,
            memberNo : memberNo},
        success : function(result){
            if(result == 1) {
              swal('','스크랩이 취소되었습니다!','success');
            } else {
              alert("오류??");
            }
        }
    });
}

// 게시글 삭제 이벤트
if ($('#delete-button').length != 0) {
  document.getElementById('delete-button').addEventListener('click', deleteBoard);
}
function deleteBoard() {
    swal({
        text : "게시글을 삭제하시겠습니까?",
        icon : "warning",
        buttons : ["아니요", "예"],
      }).then(function(isConfirm) {
        if (isConfirm) {
          location.href = 'delete?no=' + boardNo;
        } else{
          return false;
        }
    });
}
