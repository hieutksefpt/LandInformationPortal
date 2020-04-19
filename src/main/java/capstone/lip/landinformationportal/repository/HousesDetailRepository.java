/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.repository;

import capstone.lip.landinformationportal.entity.HousesDetail;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Admin
 */
public interface HousesDetailRepository extends JpaRepository<HousesDetail, Long>{
	HousesDetail findByIdHouseIdAndIdHousesFeatureId(Long houseId, Long housesFeatureId);
}
