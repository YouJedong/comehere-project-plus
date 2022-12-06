// 썸머노트 준비
$(document).ready(function() {
   $('textarea').summernote({
      minHeight: 700,
      lang: "ko-KR"});
});
// 썸머노트 submit메서드
function goWrite(frm) {
   var title = frm.title.value;
   var content = frm.content.value;
   if (title.trim() == '') {
     swal('','제목을 입력해주세요','warning');
         return false;
   }
   if (content.trim() == '') {
     swal('','내용을 입력해주세요','warning').then(() =>{
         return false;
     });
   } else {
       swal({
           text : "게시글을 등록하시겠습니까?",
           icon : "info",
           buttons : ["아니요", "예"],
         }).then(function(isConfirm) {
           if (isConfirm) {
             frm.submit();
           } else{
             return false;
           }
       });
   }
}