
function initMap() {
    var markers = JSON.parse($('#txtInput_coordinate_detailRealEstate').val());
    var latitude = markers.latitude;
    var longitude = markers.longitude;

    var myLatLng = {lat: latitude, lng: longitude};

    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 18,
        mapTypeId: 'roadmap',
        clickableIcons: false,
        disableDoubleClickZoom: true,
        fullscreenControl: false
    });

    var marker = new google.maps.Marker({position: myLatLng, map: map});
}
function openConfirmDeletePopup() {
    $('#confirmDeletePopup').modal('show');
}
function hideConfirmDeletePopup() {
    $('#confirmDeletePopup').modal('hide');
}

function checkclick(dlg) {
    PF(dlg).show();
}