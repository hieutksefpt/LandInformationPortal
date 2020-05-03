function openChangeUserRolePopup() {
    $('#changeUserRolePopup').modal('show');
}

function hideChangeUserRolePopup() {
    $('#changeUserRolePopup').modal('hide');
}

function checkclick(dlg) {
    if (PF('table-user').selection.length > 0) {
        PF(dlg).show()
    } else {
        PF('alert').renderMessage({"summary": "Lỗi",
            "detail": "Hãy chọn 1 bản ghi",
            "severity": "warn"})
    }
}