let tatlleNo = -1; /* 신고 사유 번호 초기화 */

function commentComplain(number) {
  objNo = number;
  console.log(number);
  $("#reportForm").attr("value", number);
  $("#reportForm").attr("name", "댓글");

  modal.style.display = 'flex';
  window.scrollTo(0,0);
  // x버튼 누르면 종료
  $(".close-area").click(function () {
      $("input:checkbox[name='reports']").each(function(){
          this.checked = false
          });
    modal.style.display = 'none';
    checkTag.setAttribute('checked', 'false');
  });
  // 신고 누르면 종료
  $(".send_bt3").click(function () {
      $("input:checkbox[name='reports']").each(function(){
          this.checked = false
          });
    modal.style.display = 'none';
  });
  // esc누르면 종료
  window.addEventListener('keyup', (e) => {
    if (modal.style.display === 'flex' && e.key === 'Escape') {
      modal.style.display = 'none';
      checkTag.setAttribute('checked', 'false');
    }
  });
}

const loremIpsum = document.getElementById("lorem-ipsum")
//fetch를 통해 받아온 데이터를 loremIpsum DOM에 넣어주기 때문에 모달창 내부에 내용이 채워져서 뜨는것. 
//fetch("https://baconipsum.com/api/?type=all-meat&paras=200&format=html")
      loremIpsum.innerHTML = ``
      /* .then(response => response.text())
      .then(result => loremIpsum.innerHTML = result) */
              
//디스플레이 non 버튼클릭하면 flex로 변환  
const modal = document.getElementById("modal")
const btnModal = document.querySelectorAll(".btn-modal")

$("#reportForm").attr("value", boardNo);
$("#reportForm").attr("name", "게시글");
btnModal.forEach((btn) => {
  btn.addEventListener('click', (e) => {
    objNo = boardNo;
    modal.style.display = 'flex';

    // x버튼 누르면 종료
    const closeFromExit = modal.querySelector('.close-area');
    closeFromExit.addEventListener('click', (e) => {
      $("input:checkbox[name='reports']").each(function(){
        this.checked = false
      });
      modal.style.display = 'none';
    });
      
    // 신고 누르면 종료
    const closeFromReport = modal.querySelector('.send_bt3');
    closeFromReport.addEventListener('click', (e) => {
      $("input:checkbox[name='reports']").each(function(){
        this.checked = false
      });
      modal.style.display = 'none';
    });
      
    // esc누르면 종료
    window.addEventListener('keyup', (e) => {
      if (modal.style.display === 'flex' && e.key === 'Escape') {
        modal.style.display = 'none';
      }
    });
  });
});
          
  
  function checkOnlyOne(element) {
      
      const checkboxes 
          = document.getElementsByName("reports");
      
      checkboxes.forEach((cb) => {
        cb.checked = false;
      })
      
      element.checked = true;
    }
  
  let check = 0;
  let checkTag = "";

      
  $(".form-check-input").change(function(e){
      if($(".form-check-input").is(":checked")){
          check = e.currentTarget.id;
          checkTag = e.currentTarget;
      }else{
      }
  });

$("#singo").click(function a () {
    $('input:checkbox[name=reports]').each(function (index) {
      if($(this).is(":checked")==true){
        console.log("신고사유번호 : " + $(this).attr("id"));
        tatlleNo = $(this).attr("id");
      }
    });
    if (tatlleNo == -1) {
      swal('신고 실패!', "신고사유를 선택해주세요!", 'warning');
      return false;
    }
    let value = $("#reportForm").val(); /* 신고할 게시글/댓글 번호 */
    let name = $("#reportForm").attr("name"); /* 신고할 대상이 '게시글'이라는 것을 알림 */
    console.log("게시글/댓글 번호 " + value + "게시글이니? 댓글이니? 표시 " + name);
    
    $.ajax({
      type: "POST",
      url: "/app/boardReport/add",
      data:{value: value, name: name, tatlleNo: tatlleNo, boardNo : boardNo},
      success: function (result) {
      document.getElementById("modal").style.display = 'none';
      checkTag.setAttribute('checked', 'false');
      tatlleNo = -1; /* 신고사유번호 다시 초기화 */
      swal('신고 성공', name + ' 신고를 완료했습니다!', 'success');
      },
    });
});
