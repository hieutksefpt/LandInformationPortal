package capstone.lip.landinformationportal.common.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.business.repository.UserRepository;
import capstone.lip.landinformationportal.common.constant.UserRoleConstant;
import capstone.lip.landinformationportal.common.constant.UserStatusConstant;
import capstone.lip.landinformationportal.common.entity.User;
import capstone.lip.landinformationportal.common.utils.EncryptedPassword;

@Service
public class CustomAuthenticationManager implements AuthenticationManager, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal() + "";
	    String password = authentication.getCredentials() + "";

	    User user = userRepo.findByUsername(username);
	    if (user == null) {
	        throw new BadCredentialsException("1000");
	    }
	    if (!EncryptedPassword.checkPassword(password, user.getPassword())) {
	        throw new BadCredentialsException("1000");
	    }
	    if (user.getUserStatus().equals(UserStatusConstant.BAN)) {
	        throw new DisabledException("1001");
	    }
	    
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    if (user.getRole().equals(UserRoleConstant.ADMIN)) {
	    	authorities.add(new SimpleGrantedAuthority(UserRoleConstant.ROLE_ADMIN));
	    	authorities.add(new SimpleGrantedAuthority(UserRoleConstant.ROLE_USER));
	    }else if (user.getRole().equals(UserRoleConstant.USER)) {
	    	authorities.add(new SimpleGrantedAuthority(UserRoleConstant.ROLE_USER));
	    }
	    return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

}
