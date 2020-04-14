package capstone.lip.landinformationportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.entity.Report;

@Repository
public interface ReportRepository  extends JpaRepository<Report, Long>{
	Report findByIdUserIdAndIdRealEstateId(Long userId, Long realEstateId);
}
