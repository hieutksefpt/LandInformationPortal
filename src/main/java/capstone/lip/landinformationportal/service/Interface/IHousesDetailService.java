/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.Interface;

import capstone.lip.landinformationportal.entity.HousesDetail;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHousesDetailService {

    List<HousesDetail> findAll();

    HousesDetail save(HousesDetail housesDetail);

    void delete(List<HousesDetail> listDistrict);

}
