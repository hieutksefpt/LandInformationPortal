package capstone.lip.landinformationportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.entity.SegmentOfStreet;

@Repository
public interface SegmentOfStreetRepository extends JpaRepository<SegmentOfStreet, Long>{

}
