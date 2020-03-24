var path = [];
function initMap() {
	let latitude = 21.012633;
    let longitude = 105.527423;
    let myLatLng = {lat: latitude, lng: longitude};
    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 14,
        mapTypeId: 'roadmap',
        clickableIcons: false,
        disableDoubleClickZoom: true,
        fullscreenControl: false,
        disableDefaultUI: true
    });
    
    let listRealEstate = $('#list-reo')[0];
    map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(listRealEstate);
    
    let formControl = $('#formControl')[0];
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(formControl);
    
    let input = $('#searchbox-Address')[0];
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
}
function focusMap(latitude, longitude){
    map.setCenter(new google.maps.LatLng(latitude, longitude));
    map.setZoom(15);
}
function clearDataMap(){
	path.forEach(x=>x.setMap(null));
	path = [];
}
function drawPath(json){
	let line = json.map(x=>{
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