/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.validation;

/**
 *
 * @author Admin
 */
public class ProfileValidation {
    public String checkFillText(String fullNameText, String emailText, String addressText, String phoneText, String gender){
        StringValidation sv = new StringValidation();
        if(fullNameText == null || fullNameText.isEmpty()){
            return "empty";
        }
        else if(emailText == null || emailText.isEmpty()){
            return "empty";
        }
        else if(addressText == null || addressText.isEmpty()){
            return "empty";
        }
        else if(phoneText == null || phoneText.isEmpty()){
            return "empty";
        }
        else if(gender == null || gender.isEmpty()){
            return "empty";
        }
//        else if(!sv.isValidText(fullNameText).equals("")){
//            return "nameContainError";
//        }
        else if(!sv.isValidEmail(emailText).equals("")){
            return "emailError";
        }
        else {
            return "";
        }
    }
}
