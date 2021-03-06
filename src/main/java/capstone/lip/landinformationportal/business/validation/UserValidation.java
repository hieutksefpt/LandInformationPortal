package capstone.lip.landinformationportal.business.validation;

import org.apache.commons.lang3.StringUtils;

import capstone.lip.landinformationportal.common.constant.UserGenderConstant;
import capstone.lip.landinformationportal.common.constant.UserRoleConstant;
import capstone.lip.landinformationportal.common.constant.UserStatusConstant;
import capstone.lip.landinformationportal.common.entity.User;

public class UserValidation extends StringValidation{
	public String isValidUser(User user) {
		if (user.getRole() == null || user.getFullName() == null || user.getUsername() == null 
				|| user.getPassword() == null || user.getEmail() == null || user.getUserStatus() == null) {
			return "null data";
		}
		if (user.getRole()!= null && !user.getRole().equals(UserRoleConstant.ADMIN) && !user.getRole().equals(UserRoleConstant.USER) ) {
			return "role is invalid";
		}
		if (!user.getUserStatus().equals(UserStatusConstant.ACTIVE) && !user.getUserStatus().equals(UserStatusConstant.BAN)) {
			return "status is invalid";
		}
		if (user.getGender() != null && !user.getGender().equals(UserGenderConstant.MALE) && !user.getGender().equals(UserGenderConstant.FEMALE) && !user.getGender().isEmpty()){
			return "gender is invalid";
		}
		if (!isEmptyString(user.getFullName()).isEmpty()) {
			return "empty";
		}
		if (!isEmptyString(user.getUsername()).isEmpty()) {
			return "empty";
		}
                
		if (!isEmptyString(user.getPassword()).isEmpty()) {
			return "empty";
		}
		if (!isValidEmail(user.getEmail()).isEmpty()) {
			return "email invalid";
		}
		if (!containSpace(user.getUsername()).isEmpty() ) {
			return "username contain space";
		}
		if (!containUTF8(user.getUsername()).isEmpty()) {
			return "username invalid";
		}
		if (user.getUsername().matches(".*[^a-zA-Z0-9].*")) {
			return "username invalid";
		}
		if (!containUTF8(user.getPassword()).isEmpty()) {
			return "password has utf-8";
		}
		if (StringUtils.isNumeric(user.getFullName())) {
			return "fullname invalid";
		}
		if (!hasNumber(user.getFullName()).isEmpty()) {
			return "fullname invalid";
		}
		if (!isSpecialCharOnly(user.getFullName()).isEmpty()) {
			return "fullname invalid";
		}
		if (user.getPhone()!=null && !user.getPhone().trim().isEmpty() && !StringUtils.isNumeric(user.getPhone())) {
			return "phone is invalid";
		}
		return "";
	}
}
