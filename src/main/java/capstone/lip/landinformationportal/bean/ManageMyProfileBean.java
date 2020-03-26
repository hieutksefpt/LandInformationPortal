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

/**
 *
 * @author Admin
 */

@Named
@ViewScoped
public class ManageMyProfileBean implements Serializable{
    
    private String genderSelected;
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private String address;
    private Long userIdTemp;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    
    @Autowired
    private IUserService userService;
    
    @PostConstruct
    public void init() {
        userIdTemp = Long.parseLong("2");   // fixed
//        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        long userId = Long.parseLong(params.get("userId"));
//        User user = userService.findById(userId);                          // get User from UserID
        User user = userService.findById(userIdTemp);
        username = user.getUsername();
        fullname = user.getFullName();
        email = user.getEmail();
        phone = user.getPhone();
        genderSelected = user.getGender();
        try{
            dateOfBirth = formatter.parse(user.getDateOfBirth());
        }catch(Exception e){
            System.out.println("Phong sai roi");
            dateOfBirth = Calendar.getInstance().getTime();
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

//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }

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
    
    
    
}
