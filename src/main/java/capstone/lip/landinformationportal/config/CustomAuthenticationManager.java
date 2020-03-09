package capstone.lip.landinformationportal.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.repository.UserRepository;

@Service
public class CustomAuthenticationManager implements AuthenticationManager, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal() + "";
	    String password = authentication.getCredentials() + "";
	    
	    long test = userRepo.count();
	    
	    User user = (User) userRepo.findByUserNameContaining(username);
	    if (user == null) {
	        throw new BadCredentialsException("1000");
	    }
//	    if (!encoder.matches(password, user.getPassword())) {
//	        throw new BadCredentialsException("1000");
//	    }
//	    if (user.isDisabled()) {
//	        throw new DisabledException("1001");
//	    }
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	    return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

}
