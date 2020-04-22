/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service.Interface;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.User;

/**
 *
 * @author Admin
 */
public interface IUserService {

    List<User> findAll();

    User save(User user);

    User findById(Long userId);

    List<RealEstate> getListRealEstate(Long userId);

    String resetPassword(long userId, int passwordLength);

    User findByUsername(String username);

    long count();

    Page<User> findAll(Pageable page);
}
