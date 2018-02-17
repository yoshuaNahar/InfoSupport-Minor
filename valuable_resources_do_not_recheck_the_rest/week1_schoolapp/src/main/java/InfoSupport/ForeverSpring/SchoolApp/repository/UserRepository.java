package InfoSupport.ForeverSpring.SchoolApp.repository;

import InfoSupport.ForeverSpring.SchoolApp.domain.User;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

  /**
   * Find user by email Used for login.
   */
  public User findByEmail(String email);

}
