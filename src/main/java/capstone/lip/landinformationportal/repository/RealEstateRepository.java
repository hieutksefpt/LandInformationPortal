/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.repository;

import capstone.lip.landinformationportal.entity.RealEstate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Admin
 */
public interface RealEstateRepository extends JpaRepository<RealEstate, Long>{
	RealEstate findByRealEstateLink(String link);
	List<RealEstate> findByRealEstateStatus(String status);
}
