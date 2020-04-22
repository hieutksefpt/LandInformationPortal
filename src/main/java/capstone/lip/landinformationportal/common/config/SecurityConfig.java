package capstone.lip.landinformationportal.common.config;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import capstone.lip.landinformationportal.common.constant.UserRoleConstant;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig() {
	    super(false);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/*").hasAuthority(UserRoleConstant.ROLE_ADMIN)
				.antMatchers("/user/*").hasAuthority(UserRoleConstant.ROLE_USER)
				.anyRequest().permitAll();
		http.csrf().disable();
	}
}
