package capstone.lip.landinformationportal.business.repository;

import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.common.entity.District;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{

}
