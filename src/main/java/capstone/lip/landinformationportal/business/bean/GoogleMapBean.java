package capstone.lip.landinformationportal.business.bean;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Value;

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
