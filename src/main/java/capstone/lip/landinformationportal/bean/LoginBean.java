package capstone.lip.landinformationportal.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.inject.Named;

@Named
public class LoginBean {
	
//	@Autowired
//    private AuthenticationManager authenticationManager;

	
	private String username;
	private String password;
	
	public void loginAction() {
		try {
			int i = 1;
			i++;
			System.out.print("Hello tuan oi");
//		    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( username, password ); 
//		    Authentication authentication = authenticationManager.authenticate(authenticationToken);
//		    Authentication authentication1 =
//		            SecurityContextHolder.getContext().getAuthentication();
//		    SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
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
