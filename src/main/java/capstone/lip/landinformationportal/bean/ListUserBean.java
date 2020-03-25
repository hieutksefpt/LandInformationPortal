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
    private List<User> selectedUser;

    @PostConstruct
    public void init() {
        this.listUser = userService.findAll();
    }

    public void resetPassword() {
        for (int i = 0; i < selectedUser.size(); i++) {
            String newPassword = userService.resetPassword(selectedUser.get(i).getUserId(), 8);
            listUser.get(findUserIndexInList(selectedUser.get(i).getUserId())).setPassword(newPassword);
        }
    }

    public void changeUserRole(String role) {
        for (int i = 0; i < selectedUser.size(); i++) {
            selectedUser.get(i).setRole(role);
            userService.save(selectedUser.get(i));
            listUser.get(findUserIndexInList(selectedUser.get(i).getUserId())).setRole(role);
        }
    }

    public void banUser() {
        for (int i = 0; i < selectedUser.size(); i++) {
            selectedUser.get(i).setUserStatus("0");
            userService.save(selectedUser.get(i));
            listUser.get(findUserIndexInList(selectedUser.get(i).getUserId())).setUserStatus("0");
        }
    }

    public void unbanUser() {
        for (int i = 0; i < selectedUser.size(); i++) {
            selectedUser.get(i).setUserStatus("1");
            userService.save(selectedUser.get(i));
            listUser.get(findUserIndexInList(selectedUser.get(i).getUserId())).setUserStatus("1");
        }
    }

    public int findUserIndexInList(long userId) {
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getUserId() == userId) {
                return i;
            }
        }
        return -1;
    }

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public List<User> getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(List<User> selectedUser) {
        this.selectedUser = selectedUser;
    }

}
