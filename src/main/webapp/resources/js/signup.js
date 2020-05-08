/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showLogPassError(){
    showError("Mật khẩu xác nhận không khớp ! ");
}


function successRegisterNotify(){
    showSuccess("Đăng ký người dùng thành công");
}

function dulicateUsername(){
    showError("Tên đăng nhập đã tồn tại\nVui lòng sử dụng tên đăng nhập khác");
}



function showErrorEmail(){
    showError("Vui lòng nhập đúng định dạng của Email");
}

function showErrorGeneral(){
    showError("Vui lòng hoàn thiện tất cả các trường!\nĐồng thời không sử dụng ký tự đặc biệt\nTên đăng nhập và tên người dùng không chứa khoảng trắng, số và ký tự đặc biệt");
}


function showErrorLengthPass(){
    showError("Mật khẩu phải có độ dài trên 8 ký tự, không chứa khoảng trắng và ký tự đặc biệt !");
}