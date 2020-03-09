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
    if ($('#view\\:landFeature').attr('aria-hidden') === "false") {
        $('#addLandFeaturePopup').modal('hide');
    } else if ($('#view\\:houseFeature').attr('aria-hidden') === "false") {
        $('#addHouseFeaturePopup').modal('hide');
    }
}

function reloadPage() {
    location.reload();
}