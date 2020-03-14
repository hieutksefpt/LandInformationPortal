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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

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

}
