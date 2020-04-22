package capstone.lip.landinformationportal.common.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorConfig implements ErrorController{

	private static final String PATH = "/error";
	
	@RequestMapping(value = PATH)
	public String error() {
		return "/homepage.xhtml";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
