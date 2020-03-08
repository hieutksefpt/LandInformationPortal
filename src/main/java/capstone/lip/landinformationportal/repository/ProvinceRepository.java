package capstone.lip.landinformationportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.entity.Province;
import java.lang.Long;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long>{
}
