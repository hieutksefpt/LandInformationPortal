/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var map;
var selectedMarkers = [];
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
        disableDoubleClickZoom: true, // disable the default map zoom on double click
    });
    google.maps.event.addListener(map, 'click', function (event) {
        var marker = new google.maps.Marker({
            position: event.latLng,
            map: map,
            title: event.latLng.lat() + ', ' + event.latLng.lng()
        });

        if (selectedMarkers.length == 2){
        	alert("Clear input before adding new point");
        	return;
        }
        
        selectedMarkers.push(marker);
        coordinateMarkers = selectedMarkers.map(x => ({
            lat: parseFloat(x.getPosition().lat()),
            lng: parseFloat(x.getPosition().lng())
        }))
        
        $('#form-submit\\:firstLat').val(selectedMarkers[0].getPosition().lat());
        $('#form-submit\\:firstLng').val(selectedMarkers[0].getPosition().lng());
        if (selectedMarkers.length == 2){
        	$('#form-submit\\:secondLat').val(selectedMarkers[1].getPosition().lat());
            $('#form-submit\\:secondLng').val(selectedMarkers[1].getPosition().lng());
        }
    });
}
function focusMap(latitude, longitude){
	map.setCenter(new google.maps.LatLng(latitude, longitude));
}
var line;
function drawRoad(){
    let path = [];
    for (let i=0; i<selectedMarkers.length; i++){
        path.push({
           lat: parseFloat(selectedMarkers[i].getPosition().lat()),
           lng: parseFloat(selectedMarkers[i].getPosition().lng())
        })
    }
    line = new google.maps.Polyline({
        path: path,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
	  });
	
    line.setMap(map);
//    $('#form-submit\\:listCoordinate').val(JSON.stringify(coordinateMarkers));
}
function clearInput(){
    removeMarkers();
    line.setMap(null);
    $('#form-submit\\:firstLat').val();
    $('#form-submit\\:firstLng').val();
	$('#form-submit\\:secondLat').val();
    $('#form-submit\\:secondLng').val();
}
function removeMarkers(){
    for (let i=0; i<selectedMarkers.length; i++){
        selectedMarkers[i].setMap(null);
    }
    selectedMarkers = [];
}

function drawDataRoadByJSon() {
//    let json = $('#geojson').val();
//    if (dataLayer != null)
//    for (let i=0; i<dataLayer.length; i++){
//        map.data.remove(dataLayer[i]);
//    }
//    dataLayer = map.data.addGeoJson(JSON.parse(json));
//    map.data.addListener('click', function(event){
//        $('#form-submit\\:clickedLandId').val(event.feature.getProperty('id'));
//        $('#form-submit\\:nameLandInput').attr("placeholder", event.feature.getProperty('name'));
//        $('#form-submit\\:predictPriceInput').attr("placeholder", event.feature.getProperty('predictPrice'));
//        $('#form-submit\\:averagePriceInput').val(event.feature.getProperty('averagePrice'));
//        $('#form-submit\\:minPriceInput').val(event.feature.getProperty('minPrice'));
//        $('#form-submit\\:maxPriceInput').val(event.feature.getProperty('maxPrice'));
//    });
}