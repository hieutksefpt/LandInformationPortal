/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.common.UserRoleConstant;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import capstone.lip.landinformationportal.utils.EncryptedPassword;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */

@Named
@ViewScoped
public class SignUpBean implements Serializable{
    private User newUser;
    private String gender;
    private String username;
    private String fullname;
    private String email;
    private String address;
    private String phone;
    private Date dateOfBirth;
    private String role;
    private String password;
    private String confirmpassword;
    
    @Autowired
    private IUserService userService;
    
    
    @PostConstruct
    public void init(){
        address = "";
        email = "";
        fullname = "";
        gender = "";
        password = "";
        phone = "";
        username = "";
        address = "";
        
    }
    
    public void registerUser(){
        newUser = new User();
        newUser.setAddress(address);
        newUser.setEmail(email);
        newUser.setFullName(fullname);
        newUser.setGender(gender);
        newUser.setPassword(EncryptedPassword.encrytePassword(password));
        newUser.setPhone(phone);
        newUser.setUsername(username);
//        newUser.setDateOfBirth();
        newUser.setUserStatus("1");
        newUser.setRole(UserRoleConstant.USER);
        newUser = userService.save(newUser);
        
    }
    
    
    
    

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
    
    
}
