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
    	try {
    		return userRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    @Override
    public User save(User user) {
    	try {
    		return userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    // Hàm này get List Real Estate theo ID của User
    @Override
    public List<RealEstate> getListRealEstate(Long userId) {
    	try {
	        User user = userRepository.findById(userId).get();
	        List<RealEstate> listRealEstate = user.getListRealEstate();
	        return listRealEstate;	
    	}catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    @Override
    public User findById(Long userId) {
    	try {
    		return userRepository.findById(userId).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }
    
    @Value("${mail.username}")
	private String username;
    
    @Override
    public String resetPassword(long userId, int passwordLength) {
    	try {
	        User user = userRepository.findById(userId).get();
	        String newPassword = PasswordGenerator.generate(passwordLength);
	        String encryptedPassword = EncryptedPassword.encrytePassword(newPassword);
	        user.setPassword(encryptedPassword);
	        userRepository.save(user);
	        emailSender.sendMailChangePassword(user.getEmail(), newPassword);
	        return newPassword;
        }catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

	@Override
	public User findByUsername(String username) {
		try {
			return userRepository.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

    @Override
    public long count() {
        try {
        	return userRepository.count();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }

    @Override
    public Page<User> findAll(Pageable page) {
    	try {
    		return userRepository.findAll(page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

}
