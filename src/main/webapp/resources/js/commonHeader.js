function setMessageError(error){
	$('#panel-error')[0].innerHTML = error
	$("#panel-error").show();
	window.setTimeout(function() { $("#panel-error").hide(); }, 15000);
}