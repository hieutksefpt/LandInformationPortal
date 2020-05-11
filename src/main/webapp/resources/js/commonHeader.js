function setMessageError(error){
	$('#panel-error')[0].innerHTML = error;
	$("#panel-error").show();
	window.setTimeout(function() { $("#panel-error").hide(); }, 5000);
}

function setMessageForgetpass(){
	$("#panel-forgetpass").show();
	window.setTimeout(function() { $("#panel-forgetpass").hide(); }, 5000);
}
function setPopupForgetPass(){
	$('#div-password').hide();
	$('#div-email').show();
	$('#btn-group-forgetpass').show();
	$('#btn-group-signin').hide();
	$('#lbl-ResetTitle').show();
	$('#lbl-LoginTitle').hide();
}
function setPopupSignin(){
	$('#div-password').show();
	$('#div-email').hide();
	$('#btn-group-signin').show();
	$('#btn-group-forgetpass').hide();
	$('#lbl-ResetTitle').hide();
	$('#lbl-LoginTitle').show();
}
$(function() {
    $("#modalLogin input").keypress(function (e) {
        if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
            $('#btn-signin').click();
            return false;
        } else {
            return true;
        }
    });
});