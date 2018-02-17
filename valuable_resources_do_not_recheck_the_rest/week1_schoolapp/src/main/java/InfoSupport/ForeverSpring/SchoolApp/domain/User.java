package InfoSupport.ForeverSpring.SchoolApp.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  private String role;

  @Column(name = "cellphone")
  private String cellphone;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<UserExercise> userExercises = new ArrayList<>();

  public User(int id, String email, String name, String role) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.role = role;
  }

  public User(String email, String name, String role) {
    this.email = email;
    this.name = name;
    this.role = role;
  }

  public User(User user) {
    this.id = user.id;
    this.email = user.email;
    this.password = user.password;
    this.name = user.name;
    this.role = user.role;
    this.cellphone = user.cellphone;
  }

  public void setPassword(String password) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    this.password = BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public void setPasswordWithoutHash(String password) {
    this.password = password;
  }

}
