var param = 1; 
var pageNum ="";
$('#pageButton' + param).addClass('active');

  document.querySelectorAll('.page-item').forEach((pageBtn) => {
  console.log("bbbbbd");
  pageBtn.addEventListener('click', function(e) {
  console.log("얌마????!");
      $('.page-item').removeClass('active');
      pageNum = $(e.currentTarget).find('button').val();
  });
});

let region_sido = "0";
let region_sigu = "0";
let sports = "0";
let partyDate = "0";
let partyTime = "0";
let searchText = "0";
let listStar = "0";
let listCreate = "0";
let listPartyDate = "0";
let page = "0";

let btnValue = "";

console.log("js준비");

$("#region_sigu").change(() => {
  loadList(getListConditions())
});

$("#sports_name").change(() => {
  loadList(getListConditions())
});

$("#partyDate").change(() => {
  loadList(getListConditions())
});

$("#partyTime").change(() => {
  loadList(getListConditions())
});

$("#searchButton").click(() => {
  loadList(getListConditions())
});

$("#list_reset").click(() => {
  $("#list_star").attr('value','0');
  $("#list_create").attr('value','0');
  $("#list_partyDate").attr('value','0');
  $("#list_star").css('background-color','transparent');
  $("#list_create").css('background-color','transparent');
  $("#list_partyDate").css('background-color','transparent');
  $("#list_star").hover(function() {$(this).css('background-color','#0d6efd'); $(this).css('color','#fff')}, function() {$(this).css('background-color','transparent'), $(this).css('color','#0d6efd');});
  $("#list_create").hover(function() {$(this).css('background-color','#0d6efd'); $(this).css('color','#fff')}, function() {$(this).css('background-color','transparent'), $(this).css('color','#0d6efd');});
  $("#list_partyDate").hover(function() {$(this).css('background-color','#0d6efd'); $(this).css('color','#fff')}, function() {$(this).css('background-color','transparent'), $(this).css('color','#0d6efd');});
  
  loadList(getListConditions())
});

$("#list_star").click(() => {
  console.log($("#list_star").attr('id'))
  changeBtnValue($("#list_star").attr('id'), $("#list_star").val())
  loadList(getListConditions())
});

$("#list_create").click(() => {
  changeBtnValue($("#list_create").attr('id'), $("#list_create").val())
  loadList(getListConditions())
});

$("#list_partyDate").click(() => {
  changeBtnValue($("#list_partyDate").attr('id'), $("#list_partyDate").val())
  loadList(getListConditions())
});

$(document).on("click", "#prevPage", () => {
  let pageValue = $("#prevPage").val();
  loadList(getListConditions(pageValue));
  loadPage(getListConditions(pageValue));
});

$(document).on("click", ".pageNum", (e) => {
  console.log($(e.target).val());
  console.log($(e.target).text());
  let pageValue = "";
  if ($(e.target).text() != "") {
    pageValue = $(e.target).text();
  }
  if ($(e.target).val() != "") {
    pageValue = $(e.target).val();
  }  loadList(getListConditions(pageValue));
  loadPage(getListConditions(pageValue));
});
$(document).on("click", "#nextPage", () => {
  let pageValue = $("#nextPage").val();
  loadList(getListConditions(pageValue));
  loadPage(getListConditions(pageValue));
});

function changeBtnValue(btnId, btnValue) {
  if (btnValue == 0) {
    $("#" + btnId).attr('value','1');
    $("#" + btnId).css('background-color','#CCCCCC');
    $("#" + btnId).hover(function() {$(this).css('background-color','#0d6efd'); $(this).css('color','#fff')}, function() {$(this).css('background-color','#CCCCCC');});
  }
  if (btnValue == 1) {
    $("#" + btnId).attr('value','0');
    $("#" + btnId).css('background-color','transparent');
    $("#" + btnId).hover(function() {$(this).css('background-color','#0d6efd'); $(this).css('color','#fff')}, function() {$(this).css('background-color','transparent'), $(this).css('color','#0d6efd');});
  }
}

function getListConditions(pageValue) {
  var params = {}
  if ($("#region_sido").val() != "0") {
    params.si = $("#region_sido").val();
  }
  if ($("#region_sigu").val() != "99") {
    params.gu = $("#region_sigu").val();
  }
  if ($("#sports_name").val() != "99") {
    params.sports = $("#sports_name").val();
  }
  if ($("#partyDate").val() != "") {
    params.partyDate = $("#partyDate").val();
  }
  if ($("#partyTime").val() != "") {
    params.partyTime = $("#partyTime").val();
  }
  if ($("#list_search").val() != "") {
    params.searchText = $("#list_search").val();
  }
  if ($("#list_star").val() != "") {
    params.listStar = $("#list_star").val();
  }
  if ($("#list_create").val() != "") {
    params.listCreate = $("#list_create").val();
  }
  if ($("#list_partyDate").val() != "") {
    params.listPartyDate = $("#list_partyDate").val();
 }
  if ($("#list_star").val() != "" && $("#list_create").val() != "") {
    params.listStar = $("#list_star").val();
    params.listCreate = $("#list_create").val();
  }
  if ($("#list_star").val() != "" && $("#list_partyDate").val() != "") {
    params.listStar = $("#list_star").val();
    params.listPartyDate = $("#list_partyDate").val();
  }
  if ($("#list_create").val() != "" && $("#list_partyDate").val() != "") {
    params.listCreate = $("#list_create").val();
    params.listPartyDate = $("#list_partyDate").val();
  }
  if ($("#list_star").val() != "" && $("#list_create").val() != "" && $("#list_partyDate").val() == "1") {
    params.listStar = $("#list_star").val();
    params.listCreate = $("#list_create").val();
    params.listPartyDate = $("#list_partyDate").val();
  }
  if (pageValue != "") {
    console.log(pageValue);
    params.page = pageValue;
  }
  
  return params;
}

function loadList(params) {
  
  console.log(params);
  
  $.ajax({
    type: "GET",
    url: "/app/party/list-ajax",
    data: params,
    success: function(result) {
        
      console.log(result);
      $('#partyList').html(result);

      }
  });

}

function loadPage(params) {
  
  $.ajax({
    type: "GET",
    url: "/app/party/list-ajax-page",
    data: params,
    success: function(result) {
      console.log(result);

      $('#partyPage').html(result);
              console.log("ddd");

      $('#pageButton' + pageNum).addClass('active');
    document.querySelectorAll('.page-item').forEach((pageBtn) => {
  console.log("bbbbbd");
  pageBtn.addEventListener('click', function(e) {
  console.log("얌마????!");
      $('.page-item').removeClass('active');
      pageNum = $(e.currentTarget).find('button').val();
  });

});
      }
  });
}

