/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showLogPassError(){
    alert("Mật khẩu xác nhận không khớp ! ");
}


function successRegisterNotify(){
    alert("Đăng ký người dùng thành công");
}

function dulicateUsername(){
    alert("Tên đăng nhập đã tồn tại\nVui lòng sử dụng tên đăng nhập khác");
}

function dulicateEmail(){
    alert("Email này đã được đăng ký cho tài khoản khác\nVui lòng sử dụng Email khác");
}

function showErrorPhone(){
    alert("Số điện thoại này đã được đăng ký cho tài khoản khác\nVui lòng sử dụng số điện thoại khác");
}

function showErrorGeneral(){
    alert("Vui lòng hoàn thiện tất cả các trường!\nĐồng thời không sử dụng ký tự đặc biệt");
}


function showErrorLengthPass(){
    alert("Mật khẩu phải có độ dài trên 8 ký tự, không chứa khoảng trắng và ký tự đặc biệt !");
}