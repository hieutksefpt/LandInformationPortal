package capstone.lip.landinformationportal.business.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.common.entity.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long>{
	
	@Query("Select distinct st from Street st join SegmentOfStreet sg on sg.street.streetId = st.streetId join District dt on dt.districtId = sg.district.districtId where dt.districtId = ?1")
	List<Street> findStreetByDistrictId(Long districtId);
	
}
