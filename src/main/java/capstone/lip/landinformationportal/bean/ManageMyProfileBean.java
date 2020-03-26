/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.SimpleDateFormat;  
import java.util.Calendar;
import org.primefaces.component.password.Password;

/**
 *
 * @author Admin
 */

@Named
@ViewScoped
public class ManageMyProfileBean implements Serializable{
    
    private User userSelected;
    private String genderSelected;
    private String username;
    private String fullname;
    private String email;
    private String address;
    private String phone;
    private Date dateOfBirth;
    private Long userIdTemp;
    private String oldPass;
    private String newPass;
    private String confirmNewPass;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    
    @Autowired
    private IUserService userService;
    
    @PostConstruct
    public void init() {
        userIdTemp = Long.parseLong("2");   // fixed
//        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        long userId = Long.parseLong(params.get("userId"));
//        userSelected = userService.findById(userId);                          // get User from UserID
        userSelected = userService.findById(userIdTemp);
        username = userSelected.getUsername();
        fullname = userSelected.getFullName();
        email = userSelected.getEmail();
        address = userSelected.getAddress();
        phone = userSelected.getPhone();
        
        genderSelected = userSelected.getGender();
        try{                                                
            dateOfBirth = userSelected.getDateOfBirth();                        // cần xử lý lại ngày sinh sau
        }catch(Exception e){
            dateOfBirth = Calendar.getInstance().getTime();
        }
    }
    
    
    public void updateMyProfile(){
        userSelected.setFullName(fullname);
        userSelected.setEmail(email);
        userSelected.setAddress(address);
        userSelected.setDateOfBirth(dateOfBirth);
        userSelected.setPhone(phone);
        userSelected.setGender(genderSelected);
        // update to DB 
        userSelected = userService.save(userSelected);
        
    }
    
    public void changePassword(){
        if(oldPass.equals(userSelected.getPassword()) && newPass.equals(confirmNewPass)){
            userSelected.setPassword(confirmNewPass);
            userSelected = userService.save(userSelected);
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


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
