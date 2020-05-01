function initMap(latitude, longitude) {
	if (latitude == undefined || longitude == undefined){
		return;
	}
    var myLatLng = {lat: latitude, lng: longitude};
    if (document.getElementById('map')==null) return;
    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 14,
        mapTypeId: 'roadmap',
        clickableIcons: false,
        disableDoubleClickZoom: true,
        fullscreenControl: false,
        disableDefaultUI: true
    });
    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        draggable:true
    });
    
    google.maps.event.addListener(marker, 'dragend', function() {
	    $('#singleLat').val(marker.position.lat());
	    $('#singleLng').val(marker.position.lng());
	});
    
}
var path = [];
function drawPath(json){
	path.forEach(x=>x.setMap(null));
	let fromJson = JSON.parse(json);
	let line = fromJson.map(x=>{
		let o={};
		o.lat=x.latitude;
		o.lng=x.longitude;
		return o;
	});
	let element = new google.maps.Polyline({
	    path: line,
	    geodesic: true,
	    strokeColor: '#FF0000',
	    strokeOpacity: 1.0,
	    strokeWeight: 2
	  });
	element.setMap(map);
	path.push(element);
}
function updateCrawlNowLabel(status){
	if (status == 'on'){
		$('#lb-crawlnow').text("Đang chạy crawl now")
	}else{
		$('#lb-crawlnow').text("")
	}
}

function renderMessageCrawlNow(){
	PF('alert').renderMessage({"summary":"Thông báo",
        "detail":"Đang crawl ngay",
        "severity":"info"})
}