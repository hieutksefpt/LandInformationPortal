package capstone.lip.landinformationportal.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptedPassword {
 
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    public static boolean checkPassword(String rawPassword, String passwordEncrypt) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	return encoder.matches(rawPassword, passwordEncrypt);
    }
    public static void main(String[] args) {
        String password = "admin";
        String encrytedPassword = encrytePassword(password);
 
        System.out.println("Encryted Password: " + encrytedPassword);
    }
     
}
