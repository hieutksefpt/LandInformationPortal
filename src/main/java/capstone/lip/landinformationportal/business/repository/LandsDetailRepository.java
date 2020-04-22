package capstone.lip.landinformationportal.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.common.entity.LandsDetail;

@Repository
public interface LandsDetailRepository extends JpaRepository<LandsDetail, Long> {
    LandsDetail findByIdLandIdAndIdLandsFeatureId(Long landId, Long landsFeatureId);
}
