function initMap(latitude, longitude) {
	if (latitude == undefined || longitude == undefined){
		return;
	}
    var myLatLng = {lat: latitude, lng: longitude};
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
        map: map
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