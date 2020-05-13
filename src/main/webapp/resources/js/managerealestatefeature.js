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
    showError("Tên thuộc tính đất không được để trống !");
}

function showAlertHousesFeatureName() {
    showError("Tên thuộc tính nhà không được để trống !");
}

function showAlertOnlyNumber() {
    showError("Khi chọn kiểu dữ liệu số bạn chỉ có thể nhập số !");
}

function showAlertDuplicateLandsFeature() {
    showError("Thuộc tính đất đã tồn tại !");
}

function showAlertDuplicateHousesFeature(){
    showError("Thuộc tính nhà đã tồn tại !");
}

function checkclick(dlg) {
    PF(dlg).show();
}