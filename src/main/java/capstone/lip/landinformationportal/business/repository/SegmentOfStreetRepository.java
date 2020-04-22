package capstone.lip.landinformationportal.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.common.entity.SegmentOfStreet;

@Repository
public interface SegmentOfStreetRepository extends JpaRepository<SegmentOfStreet, Long>{

}
