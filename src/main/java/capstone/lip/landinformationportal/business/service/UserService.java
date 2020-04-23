/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service;

import capstone.lip.landinformationportal.business.repository.UserRepository;
import capstone.lip.landinformationportal.business.service.Interface.IUserService;
import capstone.lip.landinformationportal.business.validation.UserValidation;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.User;
import capstone.lip.landinformationportal.common.utils.EmailSender;
import capstone.lip.landinformationportal.common.utils.EncryptedPassword;
import capstone.lip.landinformationportal.common.utils.PasswordGenerator;

import java.util.List;
import java.util.Optional;

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
    		UserValidation validate = new UserValidation();
    		String error = validate.isValidUser(user);
    		if (!error.isEmpty()) {
    			throw new Exception(error);
    		}
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
    public String resetPassword(Long userId, int passwordLength) {
    	try {
    		if(userId == null) {
    			throw new Exception("Null user Id");
    		}
    		if(passwordLength < 8) {
    			throw new Exception("Password length too short");
    		}
	        Optional<User> userTemp = userRepository.findById(userId);
	        if(!userTemp.isPresent()) {
	        	throw new Exception("Not exist user");
	        }
	        User user = userTemp.get();
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
    		Page<User> pageUser = userRepository.findAll(page);
    		List<User> listUser = pageUser.getContent();
    		if(listUser == null || (listUser != null && listUser.isEmpty())) {
    			throw new Exception("Out of range");
    		}
    		return pageUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

}
