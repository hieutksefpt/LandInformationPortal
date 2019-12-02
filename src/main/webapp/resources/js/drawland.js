/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var map;
var selectedMarkers = [];
console.log('ok');
function initMap(){
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

//        var content = document.getElementById('form:fname').value;
//        document.getElementById('form:fname').value = content + event.latLng.lat() + "," + event.latLng.lng() + "\n\n";
//        
//        listCoordinates.push([event.latLng.lat(),event.latLng.lng()]);
//        document.getElementById('form:json').value = JSON.stringify(listCoordinates);
    });
}

