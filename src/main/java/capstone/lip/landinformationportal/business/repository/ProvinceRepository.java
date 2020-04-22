package capstone.lip.landinformationportal.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.common.entity.Province;

import java.lang.Long;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long>{
}
