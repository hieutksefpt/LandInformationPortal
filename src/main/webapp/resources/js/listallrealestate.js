function openChangeRealEstateStatusPopup(){
    $('#changeRealEstatePopup').modal('show');
}

function hideChangeRealEstateStatusPopup(){
    $('#changeRealEstatePopup').modal('hide');
}

function openConfirmDeletePopup1() {
    $('#confirmDeletePopup').modal('show');
}
function hideConfirmDeletePopup1() {
    $('#confirmDeletePopup').modal('hide');
}

function checkclick(dlg) {
    if (PF('table-real-estate').selection.length > 0) {
        PF(dlg).show()
    } else {
        PF('alert').renderMessage({"summary": "Lỗi",
            "detail": "Hãy chọn 1 bản ghi",
            "severity": "warn"})
    }
}

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