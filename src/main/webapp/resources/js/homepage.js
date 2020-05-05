var path = [];
var listMarker = [];
var map;
function initMap() {
	let latitude = 21.012633;
    let longitude = 105.527423;
    let myLatLng = {lat: latitude, lng: longitude};
    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 14,
        mapTypeId: 'roadmap',
        clickableIcons: false,
        disableDoubleClickZoom: true,
        fullscreenControl: false,
        disableDefaultUI: true
    });
    
    let listRealEstate = $('#list-reo')[0];
    map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(listRealEstate);
    
    let formControl = $('#formControl')[0];
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(formControl);
    
    let legend = $('#legend')[0];
    map.controls[google.maps.ControlPosition.BOTTOM_LEFT].push(legend);
    
    let input = $('#searchbox-Address')[0];
	    searchBox = new google.maps.places.SearchBox(input);
	
	    map.addListener('bounds_changed', function () {
	        searchBox.setBounds(map.getBounds());
    });
    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }


        var bounds = new google.maps.LatLngBounds();
        places.forEach(function (place) {
            if (!place.geometry) {
                console.log("Returned place contains no geometry");
                return;
            }
            var icon = {
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25)
            };
            if (place.geometry.viewport) {
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });
    
    
}
function focusMap(latitude, longitude, zoom){
    map.setCenter(new google.maps.LatLng(latitude, longitude));
    map.setZoom(zoom);
}
function clearDataMap(){
	path.forEach(x=>x.setMap(null));
	path = [];
}
function getColor(price){
	if (price < 1000000000){
		return '#fe5c5c';
	}
	if (price < 5000000000){
		return '#fe3636';
	}
	if (price < 10000000000){
		return '#fe1a1a';
	}
	if (price < 20000000000){
		return '#dc0000';
	}
	return '#6b0000';
}
function replaceColor(price){
	let i = 1;
	if (typeof path !== 'undefined' && path.length > 0) {
		path.forEach(x=>x.setMap(null));
		let color = getColor(price);
		path[0].strokeColor = color;
		path[0].setMap(map);
	}
}
function drawPath(json){
	let line = json.map(x=>{
		let o={};
		o.lat=x.latitude;
		o.lng=x.longitude;
		o.price=x.price;
		return o;
	});
	
	
	defaultColor = '#FF0000';
	if (typeof line !== 'undefined' && line.length > 0){
		let price = line[0].price;
		i = 1;
		i = i+1;
	}
	
	let element = new google.maps.Polyline({
	    path: line,
	    geodesic: true,
	    strokeColor: '#FF0000',
	    strokeOpacity: 1.0,
	    strokeWeight: 10
	  });
	element.setMap(map);
	path.push(element);
}
function drawListMarker(list){
	
    listMarker.forEach(x=>{x.setMap(null)});
	listMarker = [];
    list.forEach(drawEachPoint)
	
	function drawEachPoint(item, index, arr){
    	let marker;
        
        
        if(item.source=='CONTRIBUTOR'){
            if (item.status=='VERIFIED'){
			marker = new google.maps.Marker({
	            position: {lat: item.latitude, lng: item.longitude},
	            map: map,
	            icon: contributeVerifiedIcon,
	            info: item
	        });
            }else if(item.status=='NOTVERIFY'){
                    marker = new google.maps.Marker({
                        position: {lat: item.latitude, lng: item.longitude},
                        map: map,
                        icon: contributeNotVerifiedIcon,
                        info: item
                    });
            }else{
                marker = new google.maps.Marker({
                        position: {lat: item.latitude, lng: item.longitude},
                        map: map,
                        icon: contributeConfusedIcon,
                        info: item
                    });
            }
        }else{
            if (item.status=='VERIFIED'){
			marker = new google.maps.Marker({
	            position: {lat: item.latitude, lng: item.longitude},
	            map: map,
	            icon: crawlVerifiedIcon,
	            info: item
	        });
            }else if(item.status=='NOTVERIFY'){
                    marker = new google.maps.Marker({
                        position: {lat: item.latitude, lng: item.longitude},
                        map: map,
                        icon: crawlNotVerifiedIcon,
                        info: item
                    });
            }else{
                marker = new google.maps.Marker({
                        position: {lat: item.latitude, lng: item.longitude},
                        map: map,
                        icon: crawlConfusedIcon,
                        info: item
                    });
            }
        }
        
    	
    	
    	google.maps.event.addListener(marker, 'click', function () {
    		if ($(PF('accord-panel').panels[0]).css('display') == "none")
    			PF('accord-panel').select(0);
    		$('#tb-reo').scrollTop($('#row-'+marker.info.id).offset().top - $($('.list-group-item')[0]).offset().top)
    		$('#row-'+marker.info.id).effect("highlight", {}, 3000);
        });
    	
    	listMarker.push(marker);
	}
//    var markerCluster = new MarkerClusterer(map, listMarker,
//            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
}
function displayReoList(isDisplay){
	if (isDisplay){
		$("#list-reo").css("display","block");
	}else{
		$("#list-reo").css("display","none");
	}
}