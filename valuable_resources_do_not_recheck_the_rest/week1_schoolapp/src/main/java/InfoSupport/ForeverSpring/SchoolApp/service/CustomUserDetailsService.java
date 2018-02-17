package InfoSupport.ForeverSpring.SchoolApp.service;

import InfoSupport.ForeverSpring.SchoolApp.domain.CustomUserDetails;
import InfoSupport.ForeverSpring.SchoolApp.domain.User;
import InfoSupport.ForeverSpring.SchoolApp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  /**
   * Constructor
   */
  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Created CustomUserDetails object for authentication
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);

    if (null == user) {
      throw new UsernameNotFoundException("No user present with username: " + username);
    } else {
      return new CustomUserDetails(user);
    }
  }

}
