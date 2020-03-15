/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.Interface;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.User;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IUserService {

    List<User> findAll();

    List<User> search(String userNamePart);

    User save(User user);

    void delete(Long userID);

    User findById(Long userId);

    List<RealEstate> getListRealEstate(Long userId);
}
