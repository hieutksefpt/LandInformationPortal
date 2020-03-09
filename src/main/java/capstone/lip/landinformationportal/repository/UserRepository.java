package capstone.lip.landinformationportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import capstone.lip.landinformationportal.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findAll();
	
<<<<<<< .mine
        List<User> findByuserNameContaining(String username);
=======
//        List<User> findByNameContaining(String username);
>>>>>>> .theirs
        
        User findByUserName(@Param("id") String username);
}
