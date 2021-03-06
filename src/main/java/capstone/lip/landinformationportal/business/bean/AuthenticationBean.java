package capstone.lip.landinformationportal.business.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import capstone.lip.landinformationportal.business.service.Interface.IUserService;
import capstone.lip.landinformationportal.common.constant.UserStatusConstant;
import capstone.lip.landinformationportal.common.entity.User;
import capstone.lip.landinformationportal.common.utils.EncryptedPassword;

@Named
@ViewScoped
public class AuthenticationBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String usernameSignup;
	private String passwordSignup;
	private String usernameSignin;
	private String passwordSignin;
	private String emailForget;
	private User currentUser;
	@Autowired
	private IUserService userService;
	
	@Value("${password.reset.length}")
	private int passwordLength;
	
	@PostConstruct
    public void init() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth!= null) {
			currentUser = new User().setFullName((String)auth.getPrincipal());
		}
	}
	
	public void signup() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
			ec.redirect(ec.getRequestContextPath() + "/signup.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		if (!validate()) {
//			return;
//		}
//		String encryptPass = EncryptedPassword.encrytePassword(passwordSignup);
//		userService.save(new User()
//				.setUsername(usernameSignup).
//				setPassword(encryptPass).setRole(UserRoleConstant.USER));
//		setMessage(FacesMessage.SEVERITY_INFO, "Tạo tài khoản thành công");
	}
	
	public void signin() {
		User user = userService.findByUsername(usernameSignin);
		if (user == null) {
			PrimeFaces.current().executeScript("setMessageError('Tài khoản không tồn tại')");
			return;
		}
		if (!EncryptedPassword.checkPassword(passwordSignin, user.getPassword())) {
			PrimeFaces.current().executeScript("setMessageError('Mật khẩu không chính xác')");
			return;
		}
		if (user.getUserStatus().equals(UserStatusConstant.BAN)) {
			PrimeFaces.current().executeScript("setMessageError('Tài khoản bị khóa, hãy liên hệ quản trị viên')");
			return;
		}
		if (user!= null && EncryptedPassword.checkPassword(passwordSignin, user.getPassword())) {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usernameSignin, passwordSignin);
			SecurityContextHolder.getContext().setAuthentication(token);
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        try {
				ec.redirect(ec.getRequestContextPath() + "/homepage.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void signout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
			ec.redirect(ec.getRequestContextPath() + "/homepage.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMessage(FacesMessage.Severity severityType, String message) {
		
		FacesMessage msg = new FacesMessage();
		if (severityType == FacesMessage.SEVERITY_ERROR) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lỗi", message);
		} else if (severityType == FacesMessage.SEVERITY_WARN) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Lưu ý", message);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", message);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void redirectAdminManage() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
			ec.redirect(ec.getRequestContextPath() + "/admin/listuser.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void redirectHomepage() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
			ec.redirect(ec.getRequestContextPath() + "/homepage.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void redirectListOwnReo() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
			ec.redirect(ec.getRequestContextPath() + "/user/listownrealestate.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void forgetPass() {
		User user = userService.findByUsername(usernameSignin);
		if (user == null) {
			PrimeFaces.current().executeScript("setMessageError('Tài khoản không tồn tại')");
			return;
		}
		if (user.getUserStatus().equals(UserStatusConstant.BAN)) {
			PrimeFaces.current().executeScript("setMessageError('Tài khoản bị khóa, hãy liên hệ quản trị viên')");
			return;
		}
		if (!user.getEmail().equals(emailForget)) {
			PrimeFaces.current().executeScript("setMessageError('Email không đúng')");
			return;
		}
		PrimeFaces.current().executeScript("setMessageForgetpass()");
		userService.resetPassword(user.getUserId(), passwordLength);
	}
	public String getUsernameSignup() {
		return usernameSignup;
	}

	public void setUsernameSignup(String usernameSignup) {
		this.usernameSignup = usernameSignup;
	}

	public String getPasswordSignup() {
		return passwordSignup;
	}

	public void setPasswordSignup(String passwordSignup) {
		this.passwordSignup = passwordSignup;
	}

	public String getUsernameSignin() {
		return usernameSignin;
	}

	public void setUsernameSignin(String usernameSignin) {
		this.usernameSignin = usernameSignin;
	}

	public String getPasswordSignin() {
		return passwordSignin;
	}

	public void setPasswordSignin(String passwordSignin) {
		this.passwordSignin = passwordSignin;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getEmailForget() {
		return emailForget;
	}

	public void setEmailForget(String emailForget) {
		this.emailForget = emailForget;
	}
	
}
