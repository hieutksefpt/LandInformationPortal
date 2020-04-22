/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.bean;

import capstone.lip.landinformationportal.business.service.Interface.IFeedbackService;
import capstone.lip.landinformationportal.business.service.Interface.IUserService;
import capstone.lip.landinformationportal.common.constant.FeedbackStatusConstant;
import capstone.lip.landinformationportal.common.entity.Feedback;
import capstone.lip.landinformationportal.common.entity.User;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Admin
 */
@Named
@ViewScoped
public class FeedbackBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    private IUserService userService;
    
    @Autowired
    private IFeedbackService feedbackService;
    
    private User userSelected;
    private Long userIdSelected;
    private String fullname;

    private String feedbackTitle;
    private String feedbackContent;
    private String feedbackStatus;

    @PostConstruct
    public void init() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernameInToken = "";
        if (auth != null) {
            usernameInToken = (String) auth.getPrincipal();
        }
        userSelected = userService.findByUsername(usernameInToken);
        userIdSelected = userSelected.getUserId();
    }

    public void sendFeedback() throws IOException{
        if (!fullname.isEmpty() && fullname != null && feedbackTitle != null && !feedbackTitle.isEmpty() && !feedbackContent.isEmpty() && feedbackContent != null) {
            feedbackStatus = FeedbackStatusConstant.OPEN;
            Feedback newfb = new Feedback();
            newfb.setFeedbackTitle(feedbackTitle);
            newfb.setFeedbackContent(feedbackContent);
            newfb.setFeedbackStatus(feedbackStatus);
            newfb.setUser(userSelected);
            
            newfb = feedbackService.save(newfb);
            if (newfb == null) {
            	PrimeFaces.current().executeScript("showLogError()");
            	return;
            }
            
            PrimeFaces.current().executeScript("showLogSuccessSendFeedback()");
            
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/homepage.xhtml?");
        } else {
            PrimeFaces.current().executeScript("showLogErrorEmpty()");
        }
    }

    public Long getUserIdSelected() {
        return userIdSelected;
    }

    public void setUserIdSelected(Long userIdSelected) {
        this.userIdSelected = userIdSelected;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public User getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(User userSelected) {
        this.userSelected = userSelected;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }


    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

}
