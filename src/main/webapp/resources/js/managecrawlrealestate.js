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
    let input = $('#searchbox-Address')[0];
    map.controls[google.maps.ControlPosition.TOP_CENTER].push(input);
    searchBox = new google.maps.places.SearchBox(input);
	
    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });
    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }


        var bounds = new google.maps.LatLngBounds();
        places.forEach(function (place) {
            if (!place.geometry) {
                console.log("Returned place contains no geometry");
                return;
            }
            var icon = {
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25)
            };
            if (place.geometry.viewport) {
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
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