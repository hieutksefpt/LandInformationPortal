/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var map;
var selectedMarkers = [];
var coordinateMarkers = [];
function initMap() {
    //FPT 
    var latitude = 21.012633;
    var longitude = 105.527423;

    var myLatLng = {lat: latitude, lng: longitude};

    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 14,
        disableDoubleClickZoom: true, // disable the default map zoom on double click
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
        }))

//        var content = document.getElementById('form:fname').value;
//        document.getElementById('form:fname').value = content + event.latLng.lat() + "," + event.latLng.lng() + "\n\n";
//        
//        listCoordinates.push([event.latLng.lat(),event.latLng.lng()]);
//        document.getElementById('form:json').value = JSON.stringify(listCoordinates);
    });
    var input = document.getElementById('input-gg-map');
    var searchBox = new google.maps.places.SearchBox(input);

    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });

    var markers = [];
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        // Clear out the old markers.
        markers.forEach(function (marker) {
            marker.setMap(null);
        });
        markers = [];

        // For each place, get the icon, name and location.
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

            // Create a marker for each place.
//            markers.push(new google.maps.Marker({
//                map: map,
//                icon: icon,
//                title: place.name,
//                position: place.geometry.location
//            }));

            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });
}
var dataLayer;
function drawDataRoadByJSon() {
    let json = $('#geojson').val();
    if (dataLayer != null)
    for (let i=0; i<dataLayer.length; i++){
        map.data.remove(dataLayer[i]);
    }
    dataLayer = map.data.addGeoJson(JSON.parse(json));
    map.data.addListener('click', function(event){
        $('#form-submit\\:clickedLandId').val(event.feature.getProperty('id'));
        $('#form-submit\\:nameLandInput').attr("placeholder", event.feature.getProperty('name'));
        $('#form-submit\\:predictPriceInput').attr("placeholder", event.feature.getProperty('predictPrice'));
        $('#form-submit\\:averagePriceInput').val(event.feature.getProperty('averagePrice'));
        $('#form-submit\\:minPriceInput').val(event.feature.getProperty('minPrice'));
        $('#form-submit\\:maxPriceInput').val(event.feature.getProperty('minPrice'));
    });
}
function clearInput(){
    removeMarkers();
    shape.setMap(null);
}
function removeMarkers(){
    for (let i=0; i<selectedMarkers.length; i++){
        selectedMarkers[i].setMap(null);
    }
    selectedMarkers = [];
}
let shape;

function draw(){
    let path = [];
    for (let i=0; i<selectedMarkers.length; i++){
        path.push({
           lat: parseFloat(selectedMarkers[i].getPosition().lat()),
           lng: parseFloat(selectedMarkers[i].getPosition().lng())
        })
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
function log(){
    console.log(coordinateMarkers);
}