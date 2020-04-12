/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.repository.UserRepository;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import capstone.lip.landinformationportal.utils.EmailSender;
import capstone.lip.landinformationportal.utils.EncryptedPassword;
import capstone.lip.landinformationportal.utils.PasswordGenerator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender emailSender;
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long userID) {
        userRepository.deleteById(userID);
    }

    // search User theo username
    @Override
    public List<User> search(String userNamePart) {

        return userRepository.findByUsernameContaining(userNamePart);
    }

    // Hàm này get List Real Estate theo ID của User
    @Override
    public List<RealEstate> getListRealEstate(Long userId) {
        User user = userRepository.findById(userId).get();
        List<RealEstate> listRealEstate = user.getListRealEstate();
        return listRealEstate;
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).get();
    }
    
    @Value("${mail.username}")
	private String username;
    
    @Override
    public String resetPassword(long userId, int passwordLength) {
    	String usernameTemp = username;
        User user = userRepository.findById(userId).get();
        String newPassword = PasswordGenerator.generate(passwordLength);
        String encryptedPassword = EncryptedPassword.encrytePassword(newPassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        emailSender.sendMailChangePassword(user.getEmail(), newPassword);
        return newPassword;
    }

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByUsernamePassword(String username, String password) {
		return userRepository.findByUsernamePassword(username, password);
	}

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public Page<User> findAll(Pageable page) {
        return userRepository.findAll(page);
    }

}
