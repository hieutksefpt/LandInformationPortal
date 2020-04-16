function setMessageError(error){
	$('#panel-error')[0].innerHTML = error
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
}
function setPopupSignin(){
	$('#div-password').show();
	$('#div-email').hide();
	$('#btn-group-signin').show();
	$('#btn-group-forgetpass').hide();
}