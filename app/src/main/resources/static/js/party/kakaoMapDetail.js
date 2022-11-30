let latResultVal = $("#latResult").val();
let lngResultVal = $("#lngResult").val();
let mapNameVal = $("#mapName").val();
let mapAdressVal = $("#mapAdress").val();
  
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(lngResultVal, latResultVal), // 지도의 중심좌표
        level: 6, // 지도의 확대 레벨
    }; 

// 지도를 생성한다 
var map = new kakao.maps.Map(mapContainer, mapOption); 
// 마우스 드래그와 모바일 터치를 이용한 지도 이동을 막는다
map.setDraggable(false);    
// 마우스 휠과 모바일 터치를 이용한 지도 확대, 축소를 막는다
map.setZoomable(false);   
// 지도에 확대 축소 컨트롤을 생성한다
var zoomControl = new kakao.maps.ZoomControl();
// 지도의 우측에 확대 축소 컨트롤을 추가한다
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
// 지도에 마커를 생성하고 표시한다


var marker = new kakao.maps.Marker({
    position: new kakao.maps.LatLng(lngResultVal, latResultVal), // 마커의 좌표
    map: map // 마커를 표시할 지도 객체
});
  

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});


function relayout() {    
    
    // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
    // 크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다 
    // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
    map.relayout();
}
  
// 좌표 -> 주소
var geocoder = new kakao.maps.services.Geocoder();
function searchDetailAddrFromCoords(coords, callback) {
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

function detailPick (marker, mapNameVal, mapAdressVal) {
    detailAddr = '<div>주소 : ' + mapAdressVal + '</div>';
    
    var content = '<div class="bAddr" style="padding-bottom:30px;">' +
                    '<span class="title" style="padding-bottom:5px; font-weight: bolder">' + mapNameVal + '</span>' + 
                    detailAddr + 
                '</div>';
    
    map.panTo(marker.getPosition());
    
    let mapResult = JSON.stringify(marker.getPosition());
    let [latResult, lngResult] = mapResult.split(',');
    let [a, latResult2] = latResult.split(':');
    let [b, lngResult2] = lngResult.split(':');
    let [lngResult3, c] = lngResult2.split('}');

    $('span[id=mapSelectName]').html(mapNameVal + ", ");
    $('span[id=mapSelectAddress]').html(mapAdressVal);
    
    // 인포윈도우에 클릭한 위치에 대한 상세 주소정보를 표시합니다
    infowindow.setContent(content);
    infowindow.open(map, marker);
    
}

$(document).ready(() => {
  searchDetailAddrFromCoords(marker.getPosition(), function(result, status) {
    if (status === kakao.maps.services.Status.OK) {
      detailPick(marker, mapNameVal, mapAdressVal);
    }   
  });
});

/*
kakao.maps.event.addListener(marker, 'click', function() {
  console.log(marker);
    searchDetailAddrFromCoords(marker.getPosition(), function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
          detailPick(marker, mapNameVal, mapAdressVal);
        }   
    });
})
*/
