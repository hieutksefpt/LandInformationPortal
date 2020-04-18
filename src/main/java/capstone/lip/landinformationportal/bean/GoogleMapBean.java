package capstone.lip.landinformationportal.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import capstone.lip.landinformationportal.entity.User;

@Named
@ViewScoped
public class GoogleMapBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Value("${google.map.key}")
	private String googleMapKey;

	public String getGoogleMapKey() {
		return googleMapKey;
	}

	public void setGoogleMapKey(String googleMapKey) {
		this.googleMapKey = googleMapKey;
	}
	
	
}
