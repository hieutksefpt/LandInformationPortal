/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.common.entity.House;

/**
 *
 * @author Admin
 */
@Repository
public interface HouseRepository extends JpaRepository<House, Long>{
    
}
