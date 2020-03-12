/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.Interface;

import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.RealEstate;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IRealEstateService {

    List<RealEstate> findAll();
    
    RealEstate save(RealEstate realEstate);

    void delete(List<RealEstate> listRealEstate);

    List<Land> getListLand(Long realEstateId);
    
    List<House> getListHouse(Long realEstateId);

    void delete(RealEstate realEstate);
}