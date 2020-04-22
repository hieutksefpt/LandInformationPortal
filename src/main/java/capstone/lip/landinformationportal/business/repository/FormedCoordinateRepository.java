package capstone.lip.landinformationportal.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.common.entity.FormedCoordinate;

@Repository
public interface FormedCoordinateRepository extends JpaRepository<FormedCoordinate, Long>{

}
