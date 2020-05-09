var listMarkers = [];
function initMap() {

    var markers = JSON.parse($('#txtInput_multipleCoordinate_listOwnRealEstate').val());
    if (markers.length == 0){
    	var myLatLng = {lat: 21.019692, lng: 105.841598};
    }else{
	    var latitude = markers[0].latitude;
	    var longitude = markers[0].longitude;
	    var myLatLng = {lat: latitude, lng: longitude};
    }
    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 14,
        mapTypeId: 'roadmap',
        clickableIcons: false,
        disableDoubleClickZoom: true,
        fullscreenControl: false
    });
    
    for (i = 0; i < markers.length; i++) {
        let latLng = {lat: markers[i].latitude, lng: markers[i].longitude};
        let marker = new google.maps.Marker({position: latLng, map: map, info: markers[i]});

        marker.addListener('click', function() {
        	$('#form-ViewList').scrollTop($('#row-'+marker.info.id).offset().top - $($('.list-group-item')[0]).offset().top)
        	$('#row-'+marker.info.id).effect("highlight", {}, 3000);
        });
        
//        google.maps.event.addListener(marker, 'click', function (e) {
////    		$('#row-'+marker.info.id).effect("highlight", {}, 3000);
//    		console.log(e)
//        });
        
        listMarkers.push(marker);
    }
    
    var markerCluster = new MarkerClusterer(map, listMarkers,
          {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});

}
