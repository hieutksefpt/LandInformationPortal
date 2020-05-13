/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.bean;

import capstone.lip.landinformationportal.business.service.Interface.IUserService;
import capstone.lip.landinformationportal.business.validation.StringValidation;
import capstone.lip.landinformationportal.business.validation.UserValidation;
import capstone.lip.landinformationportal.common.entity.User;
import capstone.lip.landinformationportal.common.utils.EncryptedPassword;

import java.io.IOException;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.context.ExternalContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Admin
 */
@Named
@ViewScoped
public class ManageMyProfileBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private User userSelected;
    private String genderSelected;
    private String username;
    private String fullname;
    private String email;
    private String address;
    private String phone;
    private Long userIdTemp;
    private String oldPass;
    private String newPass;
    private String confirmNewPass;

    @Autowired
    private IUserService userService;

    @PostConstruct
    public void init() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernameInToken = "";
        if (auth != null) {
            usernameInToken = (String) auth.getPrincipal();
        }

        userSelected = userService.findByUsername(usernameInToken);

        username = userSelected.getUsername();
        fullname = userSelected.getFullName();
        email = userSelected.getEmail();
        address = userSelected.getAddress();
        phone = userSelected.getPhone();

        genderSelected = userSelected.getGender();
    }

    public void updateMyProfile() {
        StringValidation sv = new StringValidation();
        userSelected.setFullName(fullname.trim());
        userSelected.setEmail(email.trim());
        userSelected.setAddress(address.trim());
        userSelected.setPhone(phone.trim());
        userSelected.setGender(genderSelected);
        UserValidation uv = new UserValidation();
        // update to DB 
        if(!checkLengthTextInput(userSelected.getFullName(),userSelected.getAddress(),userSelected.getEmail(),userSelected.getPhone()).equals("")){
            PrimeFaces.current().executeScript("showLengthInput()");
        }
        else if (uv.isValidUser(userSelected).equals("empty")) {
            PrimeFaces.current().executeScript("showEmptyError()");
        }  else if (uv.isValidUser(userSelected).equals("email invalid")) {
            PrimeFaces.current().executeScript("showEmailError()");
        } else if (uv.isValidUser(userSelected).equals("fullname invalid")) {
            PrimeFaces.current().executeScript("showNameContain()");
        } else {
            userSelected = userService.save(userSelected);
            PrimeFaces.current().executeScript("showNotifySuccess()");
        }

        

    }
    
    
    public String checkLengthTextInput(String text1,String text2,String text3,String text4){
        
        if(text1.length() > 100 || text2.length() > 100 || text3.length() > 100 || text4.length() > 100 ){
            return "not pass";
        }
        else
            return "";
    }

    public void changePassword() throws IOException {
        if (EncryptedPassword.checkPassword(oldPass, userSelected.getPassword()) && newPass.equals(confirmNewPass) && newPass.length() >= 8) {
            userSelected.setPassword(EncryptedPassword.encrytePassword(newPass));
            userSelected = userService.save(userSelected);

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/homepage.xhtml?");
        } else if (oldPass.isEmpty() || oldPass == null || newPass.isEmpty() || confirmNewPass.isEmpty()) {
            PrimeFaces.current().executeScript("showEmptyError()");
        } else if (newPass.length() < 8) {
            PrimeFaces.current().executeScript("showLogLengthPass()");
        } else if (!newPass.equals(confirmNewPass)) {
        	PrimeFaces.current().executeScript("showLogSamePass()");
        } else {
            PrimeFaces.current().executeScript("showLogErrorPass()");
        }
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGenderSelected() {
        return genderSelected;
    }

    public void setGenderSelected(String genderSelected) {
        this.genderSelected = genderSelected;
    }

    public User getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(User userSelected) {
        this.userSelected = userSelected;
    }

    public Long getUserIdTemp() {
        return userIdTemp;
    }

    public void setUserIdTemp(Long userIdTemp) {
        this.userIdTemp = userIdTemp;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmNewPass() {
        return confirmNewPass;
    }

    public void setConfirmNewPass(String confirmNewPass) {
        this.confirmNewPass = confirmNewPass;
    }

}
