package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author AnhHao
 */

@Named
@ViewScoped
public class ListUserBean implements Serializable {
    
    @Autowired
    IUserService userService;
    
    private List<User> listUser;
    
    @PostConstruct
    public void init() {
        this.listUser = userService.findAll();
    }

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }
    
}
