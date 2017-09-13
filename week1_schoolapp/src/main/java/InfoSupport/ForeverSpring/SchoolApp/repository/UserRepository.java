package InfoSupport.ForeverSpring.SchoolApp.repository;

import InfoSupport.ForeverSpring.SchoolApp.domain.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Find user by email
     * Used for login.
     *
     * @param email
     * @return
     */
    public User findByEmail(String email);

}