window.onload = function() {
  let category1 = document.getElementsByClassName( 'boardCateNo1' ),
      category2 = document.getElementsByClassName( 'boardCateNo2' ),
      category3 = document.getElementsByClassName( 'boardCateNo3' ),
      category4 = document.getElementsByClassName( 'boardCateNo4' ),
      category5 = document.getElementsByClassName( 'boardCateNo5' );
  
  for (var i = 0; i < category1.length; i++) {
    category1[i].innerHTML = "자 유"
  }
  for (var i = 0; i < category2.length; i++) {
    category2[i].innerHTML = "식 단"
  }
  for (var i = 0; i < category3.length; i++) {
    category3[i].innerHTML = "챌린지"
  }
  for (var i = 0; i < category4.length; i++) {
    category4[i].innerHTML = "질 문"
  }
  for (var i = 0; i < category5.length; i++) {
    category5[i].innerHTML = "핫 딜"
  }
}