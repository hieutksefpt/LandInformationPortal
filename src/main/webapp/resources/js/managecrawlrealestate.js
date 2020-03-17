function initMap(latitude, longitude) {
	
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