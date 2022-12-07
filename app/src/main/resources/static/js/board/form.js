// 사진첨부 버튼을 눌렀을 때 input 실행
$(function () {
    $('#btn-upload').click(function (e) {
      e.preventDefault();
      $('#fileTag').click();
    });
});

// 이미지 미리보기 
document.getElementById('fileTag').addEventListener('change', (e) => {
    readImage(e.target);
})
function readImage(input) {
    if(input.files && input.files[0]) {
      
        const reader = new FileReader();
        reader.onload = function(e){
            const previewImage = document.getElementById("previewImg");
            previewImage.src = e.target.result;
        }
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0]);
    }
}

// 등록 취소
function cancel() {
  swal({
        text : "게시글을 등록을 취소하시겠습니까?",
        icon : "info",
        buttons : ["아니요", "예"],
      }).then(function(isConfirm) {
        if (isConfirm) {
          location.href='list?no=' + cateNo;
        } else{
          return false;
        }
    });
}
