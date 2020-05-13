/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.bean;

import capstone.lip.landinformationportal.business.service.Interface.IUserService;
import capstone.lip.landinformationportal.business.validation.UserValidation;
import capstone.lip.landinformationportal.common.constant.UserRoleConstant;
import capstone.lip.landinformationportal.common.entity.User;
import capstone.lip.landinformationportal.common.utils.EncryptedPassword;

import java.io.IOException;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */
@Named
@ViewScoped
public class SignUpBean implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
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
    public void init() {
        address = "";
        email = "";
        fullname = "";
        gender = "";
        password = "";
        phone = "";
        username = "";
        address = "";

    }

    public void registerUser() throws IOException {
        newUser = new User();
        newUser = setUserInformation();
        User duplicateUsername = userService.findByUsername(username);
        
        UserValidation validate = new UserValidation();
        String error = validate.isValidUser(newUser);
        if (error.equals("email invalid")) {
            PrimeFaces.current().executeScript("showErrorEmail()");
        } else if (!error.isEmpty()) {
            PrimeFaces.current().executeScript("showErrorGeneral()");
        } else if (duplicateUsername != null) {
            PrimeFaces.current().executeScript("dulicateUsername()");
        } else if (password.length() < 8) {
            PrimeFaces.current().executeScript("showErrorLengthPass()");
        } else if (!password.equals(confirmpassword)) {
            PrimeFaces.current().executeScript("showLogPassError()");
        } else if (confirmpassword.isEmpty() || confirmpassword == null) {
            PrimeFaces.current().executeScript("showLogPassError()");
        } else {
            newUser = userService.save(newUser);
            signUpSuccess();
        }

    }

    public User setUserInformation() {
        User user = new User();
        user.setAddress(address.trim());
        user.setEmail(email.trim());
        user.setFullName(fullname.trim());
        user.setGender(gender.trim());
        user.setPassword(EncryptedPassword.encrytePassword(password));
        user.setPhone(phone.trim());
        user.setUsername(username.trim());
        user.setUserStatus("ACTIVE");
        user.setRole(UserRoleConstant.USER);

        return user;
    }

    public void directToHomePage() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/homepage.xhtml?");
    }

    public void signUpSuccess() throws IOException {
        PrimeFaces.current().executeScript("successRegisterNotify()");
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/homepage.xhtml?");
    }

    public static boolean validateEmailRegex(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public void setMessage(FacesMessage.Severity severityType, String message) {

        FacesMessage msg = new FacesMessage();
        if (severityType == FacesMessage.SEVERITY_ERROR) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lỗi", message);
        } else if (severityType == FacesMessage.SEVERITY_WARN) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Lưu ý", message);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", message);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
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
