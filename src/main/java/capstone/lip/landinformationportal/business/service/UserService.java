/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service;

import capstone.lip.landinformationportal.business.repository.UserRepository;
import capstone.lip.landinformationportal.business.service.Interface.IUserService;
import capstone.lip.landinformationportal.business.specification.SearchCriteria;
import capstone.lip.landinformationportal.business.specification.UserSpecifications;
import capstone.lip.landinformationportal.business.validation.UserValidation;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.User;
import capstone.lip.landinformationportal.common.utils.EmailSender;
import capstone.lip.landinformationportal.common.utils.EncryptedPassword;
import capstone.lip.landinformationportal.common.utils.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
            User test = this.findById(user.getUserId());
            if (test != null) {
                User userTemp = userRepository.findById(user.getUserId()).get();
                if (userTemp.getUsername().equals(user.getUsername())) {
                    UserValidation validate = new UserValidation();
                    String error = validate.isValidUser(user);
                    if (!error.isEmpty()) {
                        throw new Exception(error);
                    }
                    return userRepository.save(user);
                } else {
                    throw new Exception();
                }

            } else {
                UserValidation validate = new UserValidation();
                String error = validate.isValidUser(user);
                if (!error.isEmpty()) {
                    throw new Exception(error);
                }
                return userRepository.save(user);
            }
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findById(Long userId) {
        try {
            if(userId == null){
                return null;
            }else{
                return userRepository.findById(userId).get();
            }
            
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
            if (userId == null) {
                throw new Exception("Null user Id");
            }
            if (passwordLength < 8) {
                throw new Exception("Password length too short");
            }
            Optional<User> userTemp = userRepository.findById(userId);
            if (!userTemp.isPresent()) {
                throw new Exception("Not exist user");
            }
            User user = userTemp.get();
            String newPassword = PasswordGenerator.generate(passwordLength);
            String encryptedPassword = EncryptedPassword.encrytePassword(newPassword);
            user.setPassword(encryptedPassword);
            userRepository.save(user);
            emailSender.sendMailChangePassword(user.getEmail(), newPassword);
            return newPassword;
        } catch (Exception e) {
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
    public User findByEmail(String mail) {
        try {
            return userRepository.findByEmail(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User findByPhone(String phone) {
        try {
            return userRepository.findByPhone(phone);
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
            if (listUser == null || (listUser != null && listUser.isEmpty())) {
                throw new Exception("Out of range");
            }
            return pageUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Page<User> findAllByAttribute(Map<String, Object> listAttribute, Pageable page) {
        try {
            if (listAttribute == null) {
                throw new Exception();
            }
            List<UserSpecifications> listSpec = new ArrayList();
            if (listAttribute != null) {
                for (Map.Entry meta : listAttribute.entrySet()) {
                    String key = (String) meta.getKey();
                    String value = (String) meta.getValue();
                    listSpec.add(new UserSpecifications(new SearchCriteria(key, ":=", value)));
                }
            }
            if (!listSpec.isEmpty()) {
                return userRepository.findAll(createSpecification(listSpec), page);
            }
            return userRepository.findAll(page);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long countByAttribute(Map<String, Object> listAttribute) {
        try {
            if (listAttribute == null) {
                throw new Exception();
            }
            List<UserSpecifications> listSpec = new ArrayList();
            if (listAttribute != null) {
                for (Map.Entry meta : listAttribute.entrySet()) {
                    String key = (String) meta.getKey();
                    String value = (String) meta.getValue();
                    listSpec.add(new UserSpecifications(new SearchCriteria(key, ":=", value)));
                }
            }
            if (!listSpec.isEmpty()) {
                return userRepository.count(createSpecification(listSpec));
            }
            return userRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private Specification<User> createSpecification(List<UserSpecifications> listSpec) {
        if (listSpec == null) {
            return null;
        }
        Specification<User> spec = Specification.where(listSpec.get(0));
        for (int i = 1; i < listSpec.size(); i++) {
            spec = spec.and(listSpec.get(i));
        }
        return spec;
    }

}
