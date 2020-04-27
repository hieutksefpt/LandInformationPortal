function openEditPopup() {
    $('#editPopup').modal('show');
}

function openDeletePopup() {
    $('#deletePopup').modal('show');
}

function openEditPopup2() {
    $('#editPopup2').modal('show');
}

function openDeletePopup2() {
    $('#deletePopup2').modal('show');
}

function hideEditPopup() {
    $('#editPopup').modal('hide');
}

function hideDeletePopup() {
    $('#deletePopup').modal('hide');
}

function hideEditPopup2() {
    $('#editPopup2').modal('hide');
}

function hideDeletePopup2() {
    $('#deletePopup2').modal('hide');
}

function openAddFeaturePopup() {
    if ($('#view\\:landFeature').attr('aria-hidden') === "false") {
        $('#addLandFeaturePopup').modal('show');
    } else if ($('#view\\:houseFeature').attr('aria-hidden') === "false") {
        $('#addHouseFeaturePopup').modal('show');
    }
}

function hideAddFeaturePopup() {
    $('#addLandFeaturePopup').modal('hide');
    $('#addHouseFeaturePopup').modal('hide');
}

function reloadPage() {
    location.reload();
}

function showAlertLandsFeatureName() {
    alert("Tên thuộc tính đất không được để trống !");
}

function showAlertHousesFeatureName() {
    alert("Tên thuộc tính nhà không được để trống !");
}

function showAlertOnlyNumber(){
    alert("Khi chọn kiểu dữ liệu số bạn chỉ có thể nhập số !");
}