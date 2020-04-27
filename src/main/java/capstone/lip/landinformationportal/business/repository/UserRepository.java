package capstone.lip.landinformationportal.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import capstone.lip.landinformationportal.common.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{
	
	List<User> findAll();
	
    List<User> findByUsernameContaining(String username);
        
    @Query("SELECT u FROM User u WHERE u.username = :name")
    User findByUsername(@Param("name") String username);
    
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
    
    @Query("SELECT u FROM User u WHERE u.phone = :phone")
    User findByPhone(@Param("phone") String phone);
    
    @Query("SELECT u FROM User u WHERE u.username = :name and u.password = :password")
    User findByUsernamePassword(@Param("name") String username, @Param("password") String password);
}
