package capstone.lip.landinformationportal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import capstone.lip.landinformationportal.entity.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long>{

}
