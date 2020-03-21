
var map;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 14,
        mapTypeId: 'roadmap',
        clickableIcons: false,
        disableDoubleClickZoom: true,
        fullscreenControl: false
    });
}

function openConfirmDeletePopup1() {
    $('#confirmDeletePopup').modal('show');
}
function hideConfirmDeletePopup1() {
    $('#confirmDeletePopup').modal('hide');
}

var listMarkers = [];
function drawMarkers(markers) {
    clearOverlays();
    for (i = 0; i < markers.length; i++) {
        var latLng = {lat: markers[i].latitude, lng: markers[i].longitude};
        if (i == 0) {
            map.setCenter(latLng);
        }
        var marker = new google.maps.Marker({position: latLng, map: map});
        listMarkers.push(marker);
    }
}

function clearOverlays() {
    for (var i = 0; i < listMarkers.length; i++) {
        listMarkers[i].setMap(null);
    }
    listMarkers.length = 0;
}