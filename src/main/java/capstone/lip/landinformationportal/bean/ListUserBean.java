package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.common.UserRoleConstant;
import capstone.lip.landinformationportal.dto.LazyListUser;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
    private LazyDataModel<User> lazyUser;
    private User currentUser;

    @PostConstruct
    public void init() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (auth != null) {
            username = (String) auth.getPrincipal();
        }
        currentUser = userService.findByUsername(username);
        this.listUser = userService.findAll();
        this.lazyUser = new LazyListUser(userService);
    }

    public void resetPassword() {
        for (int i = 0; i < selectedUser.size(); i++) {
            String newPassword = userService.resetPassword(selectedUser.get(i).getUserId(), 8);
        }
        this.lazyUser = new LazyListUser(userService);
    }
    
    public void changeUserRoleAdmin() {
        for (int i = 0; i < selectedUser.size(); i++) {
            selectedUser.get(i).setRole(UserRoleConstant.ADMIN);
            userService.save(selectedUser.get(i));
        }
        this.lazyUser = new LazyListUser(userService);
    }

    public void changeUserRoleUser() {
        for (int i = 0; i < selectedUser.size(); i++) {
            selectedUser.get(i).setRole(UserRoleConstant.USER);
            userService.save(selectedUser.get(i));
        }
        this.lazyUser = new LazyListUser(userService);
    }

    public void banUser() {
        for (int i = 0; i < selectedUser.size(); i++) {
            selectedUser.get(i).setUserStatus("0");
            userService.save(selectedUser.get(i));
        }
        this.lazyUser = new LazyListUser(userService);
    }

    public void unbanUser() {
        for (int i = 0; i < selectedUser.size(); i++) {
            selectedUser.get(i).setUserStatus("1");
            userService.save(selectedUser.get(i));
        }
        this.lazyUser = new LazyListUser(userService);
    }

    public int findUserIndexInList(long userId) {
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getUserId() == userId) {
                return i;
            }
        }
        return -1;
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        User user = (User) value;
        return user.getUserId().toString().toLowerCase().contains(filterText)
                || user.getUsername().toLowerCase().contains(filterText)
                || user.getFullName().toLowerCase().contains(filterText)
                || user.getEmail().toLowerCase().contains(filterText)
                || user.getPhone().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
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

    public LazyDataModel<User> getLazyUser() {
        return lazyUser;
    }

    public void setLazyUser(LazyDataModel<User> lazyUser) {
        this.lazyUser = lazyUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
}
