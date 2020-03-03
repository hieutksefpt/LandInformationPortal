package capstone.lip.landinformationportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.entity.FormedCoordinate;

@Repository
public interface FormedCoordinateRepository extends JpaRepository<FormedCoordinate, Long>{

}
