package capstone.lip.landinformationportal.repository;

import org.springframework.stereotype.Repository;
import capstone.lip.landinformationportal.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{

}
