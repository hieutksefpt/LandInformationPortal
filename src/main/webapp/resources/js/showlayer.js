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
    map.data.addListener('click', function(event){
    	$('#inforPanel').css('display','block');
    	$('#priceInfor').val(event.feature.getProperty('price'));
    	$('#layerInfor').val(event.feature.getProperty('layerType'));
    	$('#roadSegmentNameInfor').val(event.feature.getProperty('roadSegmentName'));
    });
}

function focusMap(latitude, longitude){
	map.setCenter(new google.maps.LatLng(latitude, longitude));
	map.setZoom(15);
}
var dataGeoRoad, dataGeoLayer;
function drawRoad() {
    var json = $('#gjsonRoad').val();
    console.log(json);
    if (dataGeoRoad != null)
        for (let i=0; i<dataGeoRoad.length; i++){
            map.data.remove(dataGeoRoad[i]);
        }
    dataGeoRoad = map.data.addGeoJson(JSON.parse(json));
}
function drawLayer(){
	var json = $('#jsonLayer').val();
    console.log(json);
    if (dataGeoLayer != null)
        for (let i=0; i<dataGeoLayer.length; i++){
            map.data.remove(dataGeoLayer[i]);
        }
    dataGeoLayer = map.data.addGeoJson(JSON.parse(json));
    map.data.setStyle(function(feature) {
	    return /** @type {google.maps.Data.StyleOptions} */({
	      fillColor: feature.getProperty('fill'),
	      strokeWeight: 1
	    });
	  });
}
