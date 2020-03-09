function openEditPopup(){
    $('#editPopup').modal('show');
}

function openDeletePopup(){
    $('#deletePopup').modal('show');
}

function openEditPopup2(){
    $('#editPopup2').modal('show');
}

function openDeletePopup2(){
    $('#deletePopup2').modal('show');
}

function openAddFeaturePopup(){
    if($('#view\\:landFeature').attr('aria-hidden')==="false"){
        $('#addLandFeaturePopup').modal('show');
    }else if($('#view\\:houseFeature').attr('aria-hidden')==="false"){
        $('#addHouseFeaturePopup').modal('show');
    }
}


