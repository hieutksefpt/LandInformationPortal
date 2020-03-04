

var map;
var selectedMarkers = [];
var coordinateMarkers = [];
var deleteOld = true;

function initMap() {
    //FPT 
    var latitude = 21.012633;
    var longitude = 105.527423;

    var myLatLng = {lat: latitude, lng: longitude};

    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 14,
        mapTypeId: 'roadmap',
        clickableIcons: false,
        disableDoubleClickZoom: true,
        fullscreenControl: false
    });
    map.addListener('bounds_changed', function() {
      searchBox.setBounds(map.getBounds());
    });

    var formControl = $('#formControl')[0];
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(formControl);
    
    var markers = [];
    var input = $('#searchbox-Address')[0];
    var searchBox = new google.maps.places.SearchBox(input);

    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });

    var markers = [];

    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        markers.forEach(function (marker) {
            marker.setMap(null);
        });
        markers = [];

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

    google.maps.event.addListener(map, 'click', function (event) {
    	let selectedType = $("#form\\:cbb-IpgType option:selected").val();
    	
    	if (deleteOld){
    		clearOldMarkers();
    	}
    	
    	var marker = new google.maps.Marker({
            position: event.latLng,
            map: map,
            title: event.latLng.lat() + ', ' + event.latLng.lng()
        });
    	selectedMarkers.push(marker);
    	setCoordinateForInput(marker)
    });
}

function setCoordinateForInput(marker){
	$('#form\\:txtinput-lngSingleCoordinate').val(marker.getPosition().lng());
	$('#form\\:txtinput-latSingleCoordinate').val(marker.getPosition().lat());
}

function clearOldMarkers(){
	for (let i=0; i<selectedMarkers.length; i++){
        selectedMarkers[i].setMap(null);
    }
    selectedMarkers = [];
}

function focusMap(latitude, longitude){
    map.setCenter(new google.maps.LatLng(latitude, longitude));
    map.setZoom(15);
}
function changeInfo(name, longitude, latitude){
	$('#form\\:txtinput-Name').val(name);
	$('#form\\:txtinput-lngSingleCoordinate').val(longitude);
	$('#form\\:txtinput-latSingleCoordinate').val(latitude);
}