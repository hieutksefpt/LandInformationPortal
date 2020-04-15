package capstone.lip.landinformationportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.entity.LandsDetail;

@Repository
public interface LandsDetailRepository extends JpaRepository<LandsDetail, Long> {

}
