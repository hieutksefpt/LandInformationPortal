/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var map;
var selectedMarkers = [];
var deleteOld = true;
var path = [];
var searchBox;
var saveRow = [];
var tempCheck = "";
var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];

//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches


function initMap() {
    //FPT 
    var latitude = 21.012633;
    var longitude = 105.527423;

    var myLatLng = {lat: latitude, lng: longitude};

    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 14,
        mapTypeId: 'roadmap',
        clickableIcons: false,
        disableDoubleClickZoom: true,
        fullscreenControl: false
    });


//    let marker = new google.maps.Marker({
//            position: event.latLng,
//            map: map,
//            title: event.latLng.lat() + ', ' + event.latLng.lng()
//        });


    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });

    var formControl = $('#formControl')[0];
    map.controls[google.maps.ControlPosition.BOTTOM_LEFT].push(formControl);

    var markers = [];
    var input = $('#searchbox-Address')[0];

    searchBox = new google.maps.places.SearchBox(input);

    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });

    var markers = [];

    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        markers.forEach(function (marker) {
            marker.setMap(null);
        });
        markers = [];

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

    google.maps.event.addListener(map, 'click', function (event) {
        let selectedType = $("#form\\:cbb-IpgType option:selected").val();

        let marker = new google.maps.Marker({
            position: event.latLng,
            map: map,
            title: event.latLng.lat() + ', ' + event.latLng.lng()
        });



        if (deleteOld) {
            clearOldMarkers();
            setCoordinateForInput(marker);
            selectedMarkers.push(marker);
        } else {
            selectedMarkers.push(marker);
            addDataToNewRow(marker);
        }

    });
}

function updateDeleteOld() {
    let selectedType = $("#form\\:cbb-IpgType option:selected").val();
    if (selectedType == "4") {
        deleteOld = false;
    } else {
        deleteOld = true;
    }
    countRow = 0;
}
function setCoordinateForInput(marker) {
    $('#msform\\:txtinput-lngSingleCoordinate').val(marker.getPosition().lng());
    $('#msform\\:txtinput-latSingleCoordinate').val(marker.getPosition().lat());
}

function clearOldMarkers() {
    for (let i = 0; i < selectedMarkers.length; i++) {
        selectedMarkers[i].setMap(null);
    }
    selectedMarkers = [];
}
let countRow = 0;

function renderTable() {
    saveRow.forEach(x => {
        $("#table-coordinate").append(x);
    });
}

function deleteRowFeature(element) {
    console.log($(element).closest("tr"));
    $(element).closest("tr").remove();
}
$("#table-coordinate").on("click", ".ibtnDel", function (event) {
    $(this).closest("tr").remove();
    countRow--;
});



function drawPath(jsonText) {
    let fromJson = JSON.parse(jsonText);
    let line = fromJson.map(x => {
        let o = {};
        o.lat = x.latitude;
        o.lng = x.longitude;
        return o;
    });
    let element = new google.maps.Polyline({
        path: line,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
    });
    element.setMap(map);
    path.push(element);
}

function focusMap(latitude, longitude, zoom) {
    map.setCenter(new google.maps.LatLng(latitude, longitude));
    map.setZoom(zoom);
}
function changeInfo(name, longitude, latitude) {
    $('#form\\:txtinput-Name').val(name);
    $('#form\\:txtinput-lngSingleCoordinate').val(longitude);
    $('#form\\:txtinput-latSingleCoordinate').val(latitude);
}
function clearDataMap() {
    path.forEach(x => x.setMap(null));
    selectedMarkers.forEach(x => x.setMap(null));
    path = [];
    selectedMarkers = [];
}
function clearAllInput() {

    $('#form\\:txtinput-Name').val("");
    $('#form\\:txtinput-lngSingleCoordinate').val("");
    $('#form\\:txtinput-latSingleCoordinate').val("");
}




// đoạn này bắt đầu test MultiForm
function validateMap() {

    tempCheck = $('#msform\\:checkLocation').val();

    if (tempCheck === "OK") {
        if (animating)
            return false;
        animating = true;
        next_button = $('.next')[0];
        current_fs = $(next_button).parent();
        next_fs = $(next_button).parent().next();

        //activate next step on progressbar using the index of next_fs
        $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

        //show the next fieldset
        next_fs.show();
        //hide the current fieldset with style
        current_fs.animate({opacity: 0}, {
            step: function (now, mx) {
                //as the opacity of current_fs reduces to 0 - stored in "now"
                //1. scale current_fs down to 80%
                scale = 1 - (1 - now) * 0.2;
                //2. bring next_fs from the right(50%)
                left = (now * 50) + "%";
                //3. increase opacity of next_fs to 1 as it moves in
                opacity = 1 - now;
                current_fs.css({
                    'transform': 'scale(' + scale + ')'
                });
                next_fs.css({'left': left, 'opacity': opacity});
            },
            duration: 800,
            complete: function () {
                current_fs.hide();
                animating = false;
            },
            //this comes from the custom easing plugin
            easing: 'easeInOutBack'
        });
    } else if (tempCheck === "Marker") {
        alert("Vui lòng thả điểm để định vị bất động sản trên bản đồ");
    } else if (tempCheck === "TP") {
        alert("Vui lòng cung cấp địa chỉ Tỉnh/Thành phố của bất động sản");
    } else if (tempCheck === "QH") {
        alert("Vui lòng cung cấp địa chỉ Quận/Huyện của bất động sản");
    } else if (tempCheck === "DP") {
        alert("Vui lòng cung cấp địa chỉ đường phố của bất động sản");
    } else if (tempCheck === "DD") {
        alert("Vui lòng cung cấp địa chỉ đoạn đường của bất động sản");
    }
}

function emptyDataAdd() {
    alert("Vui lòng nhập giá trị thuộc tính");
}

function loadLandUnit(landUnit) {
    document.getElementById("landUnit").textContent =  landUnit ;
}

function loadHouseUnit(houseUnit) {
    document.getElementById("houseUnit").textContent = houseUnit;
}

function landFeatureExisted() {
    alert("Thuộc tính này của Đất đã được thêm vào bảng.\nNếu muốn chỉnh sửa vui lòng xóa bỏ giá trị thuộc tính đã tồn tại !");
}

function houseFeatureExisted() {
    alert("Thuộc tính này của Nhà đã được thêm vào bảng.\nNếu muốn chỉnh sửa vui lòng xóa bỏ giá trị thuộc tính đã tồn tại !");
}

function showLogDataRange (){
    alert("Vui lòng điền giá trị thuộc tính phù hợp theo mẫu");
}

function showModalMandatory() {
    tempRealEstateName = $('#msform\\:realEstateName').val();
    tempRealEstateValue = $('#msform\\:realEstatePrice').val();
    tempRealEstateType = $('#msform\\:cbb-Type').val();


    tempLandFeature = $('#msform\\:cbb-landFeature').val();
    tempLandFeatureValue = $('#msform\\:txtInputLandFeatureNew').val();

    tempHouseFeature = $('#msform\\:cbb-houseFeature').val();
    tempHouseFeatureValue = $('#msform\\:txtInputHouseFeatureNew').val();


    tempNewLandName = $('#msform\\:txtInputLandName').val();
    tempNewLandValue = $('#msform\\:txtInputLandMoney').val();

    tempNewHouseName = $('#msform\\:txtInputHouseName').val();
    tempNewHouseValue = $('#msform\\:txtInputHouseMoney').val();
    tempLandFeatureList = $('#msform\\:cbb-landFeature').val();
    tempHouseFeatureList = $('#msform\\:cbb-houseFeature').val();


    if (tempRealEstateName === '') {
        alert("Tên của bất động sản không được để trống");
    } else if (tempRealEstateValue <= 0 || tempRealEstateValue === null) {
        alert("Giá trị của bất động sản phải lớn hơn 0 VND.\nĐồng thời phải lớn hơn hoặc bằng tổng giá trị của các bất động sản thành phần");
    } 
    
    else if (tempRealEstateType === 'Đất và Nhà') {                  // to validate when choosing combobox Type RE.
         if (tempNewLandValue < 0) {   
            alert("Giá trị của phần Đất phải lớn hơn 0 VND.");
        }else if (tempNewHouseValue < 0) {   
            alert("Giá trị của phần Nhà phải lớn hơn 0 VND.");
        }
    } else if (tempRealEstateType === 'Đất') {                             // If choose Land Feature --> have to contribute Land Value
        if (tempNewLandValue < 0) {
            alert("Giá trị của phần Đất phải lớn hơn 0 VND.");
        }
    } else if (tempRealEstateType === 'Nhà') {                             // If choose House Feature --> have to contribute House Value
        if (tempNewHouseValue < 0) {
            alert("Giá trị của phần Nhà phải lớn hơn 0 VND.");
        }
    }
}

function showLogEmptyLandHouse(){
    alert("Vui lòng cung cấp ít nhất 1 trong 3 trường Tên, giá trị, thuộc tính phần BĐS cấu thành, bao gồm cả Nhà và Đất");
}

function showLogPrice(){
    alert("Giá của Bất động sản không thể nhỏ hơn giá của BĐS cấu thành");
}




$(".previous").click(function () {
    if (animating)
        return false;
    animating = true;

    current_fs = $(this).parent();
    previous_fs = $(this).parent().prev();

    //de-activate current step on progressbar
    $("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

    //show the previous fieldset
    previous_fs.show();
    //hide the current fieldset with style
    current_fs.animate({opacity: 0}, {
        step: function (now, mx) {
            //as the opacity of current_fs reduces to 0 - stored in "now"
            //1. scale previous_fs from 80% to 100%
            scale = 0.8 + (1 - now) * 0.2;
            //2. take current_fs to the right(50%) - from 0%
            left = ((1 - now) * 50) + "%";
            //3. increase opacity of previous_fs to 1 as it moves in
            opacity = 1 - now;
            current_fs.css({'left': left});
            previous_fs.css({'transform': 'scale(' + scale + ')', 'opacity': opacity});
        },
        duration: 800,
        complete: function () {
            current_fs.hide();
            animating = false;
        },
        //this comes from the custom easing plugin
        easing: 'easeInOutBack'
    });
});

$(".submit").click(function () {
    return false;
}
)

