/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global x, google */

var map;
var selectedMarkers = [];
var coordinateMarkers = [];
var shape;
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
        disableDoubleClickZoom: true // disable the default map zoom on double click
    });
    
    // Create new marker on single click event on the map
    google.maps.event.addListener(map, 'click', function (event) {
        var marker = new google.maps.Marker({
            position: event.latLng,
            map: map,
            title: event.latLng.lat() + ', ' + event.latLng.lng()
        });

        selectedMarkers.push(marker);
        coordinateMarkers = selectedMarkers.map(x => ({
            lat: parseFloat(x.getPosition().lat()),
            lng: parseFloat(x.getPosition().lng())
        }));
    });
}

function clearInput(){
    removeMarkers();
    shape.setMap(null);
}

function removeMarkers(){
    for (var i=0; i < selectedMarkers.length; i++){
        selectedMarkers[i].setMap(null);
    }
    selectedMarkers = [];
}

function drawPolygon(){
    var path = [];
    for (var i=0; i< selectedMarkers.length; i++){
        path.push({
           lat: parseFloat(selectedMarkers[i].getPosition().lat()),
           lng: parseFloat(selectedMarkers[i].getPosition().lng())
        });
    }
    shape = new google.maps.Polygon({
        paths: path,
        strokeColor: '#FF0000',
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: '#FF0000',
        fillOpacity: 0.35
    });
    shape.setMap(map);
    $('#form-submit\\:listCoordinate').val(JSON.stringify(coordinateMarkers));
}

function drawRoad() {
    var json = $('#geojson').val();
    dataLayer = map.data.addGeoJson(JSON.parse(json));
}