package capstone.lip.landinformationportal.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.config.CustomAuthenticationManager;

import javax.inject.Named;

@Named
public class LoginBean {
	
	@Autowired
    private CustomAuthenticationManager authenticationManager;
	
	private String username;
	private String password;
	
	public void loginAction() {
		try {
			int i= 1;
			i++;
//		    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( username, password ); 
//		    Authentication authentication = authenticationManager.authenticate(authenticationToken);
//		    SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			e.printStackTrace();
		    System.out.print("Login failed");
		    SecurityContextHolder.getContext().setAuthentication(null);
		}
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
