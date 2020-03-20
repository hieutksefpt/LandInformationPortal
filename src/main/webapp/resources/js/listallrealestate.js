        
function initMap() {

    var markers = JSON.parse($('#txtInput_multipleCoordinate_listAllRealEstate').val());
    var latitude = markers[0].latitude;
    var longitude = markers[0].longitude;
    var myLatLng = {lat: latitude, lng: longitude};
    
    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 14,
        mapTypeId: 'roadmap',
        clickableIcons: false,
        disableDoubleClickZoom: true,
        fullscreenControl: false
    });
    
    for (i = 0; i < markers.length; i++) {
        var latLng = {lat: markers[i].latitude, lng: markers[i].longitude};
        var marker = new google.maps.Marker({position: latLng, map: map});
    }
}

function openConfirmDeletePopup1() {
    $('#confirmDeletePopup').modal('show');
}
function hideConfirmDeletePopup1() {
    $('#confirmDeletePopup').modal('hide');
}