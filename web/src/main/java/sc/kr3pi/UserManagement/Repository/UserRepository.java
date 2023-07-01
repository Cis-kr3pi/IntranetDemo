package sc.kr3pi.UserManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sc.kr3pi.UserManagement.model.Users;


@Transactional(readOnly = true)
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);



    @Transactional
    @Modifying
    @Query("UPDATE Users a " +
            "SET a.isEnable = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

  /*  @Query("SELECT u FROM User u WHERE u.email =?1")
    public Users findUser(String email);*/


}
