package capstone.lip.landinformationportal.config;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.context.annotation.Bean;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig() {
	    super(false);
	}

//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//	    return new ProviderManager(Arrays.asList((AuthenticationProvider) new AuthProvider()));
//	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// require all requests to be authenticated except for the resources
		http.authorizeRequests().antMatchers("/javax.faces.resource/**", "/helloworld.xhtml").permitAll()
				.antMatchers("/helloadmin.xhtml").authenticated().anyRequest().permitAll();
		// login
		http.formLogin().loginPage("/login.xhtml").permitAll().failureUrl("/login.xhtml?error=true");
//	     logout
		http.logout().logoutSuccessUrl("/login.xhtml");
		
		http.authorizeRequests().and().formLogin()
		.loginProcessingUrl("/j_spring_security_check") // Submit URL
        .loginPage("/login")//
        .defaultSuccessUrl("/userAccountInfo")//
        .failureUrl("/login?error=true")//
        .usernameParameter("username")//
        .passwordParameter("password")
        // Cấu hình cho Logout Page.
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
		
//	     not needed as JSF 2.2 is implicitly protected against CSRF
		http.csrf().disable();
		
		   // Cấu hình Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
	}
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
		InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
	    return memory;
    }
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////	    auth.inMemoryAuthentication().withUser("user")
////	        .password("{noop}1234").roles("USER").and()
////	        .withUser("admin").password("{noop}5678").roles("ADMIN");
////	    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}
}
