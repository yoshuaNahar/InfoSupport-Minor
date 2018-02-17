package InfoSupport.ForeverSpring.SchoolApp.domain;

import java.time.LocalDateTime;
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

@Entity
@Table(name = "exercises")
@Getter
@Setter
@NoArgsConstructor
public class Exercise {

  @Id
  @Column(name = "exercise_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "content")
  private String content;

  @Column(name = "teacher")
  private String teacher;

  @Column(name = "class")
  private String clazz;

  @Column(name = "start")
  private LocalDateTime start;

  @Column(name = "end")
  private LocalDateTime end;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "exercise")
  private List<UserExercise> userExercises = new ArrayList<>();

  public void assignUserExercise(UserExercise userExercise) {
    this.userExercises.add(userExercise);
  }

}
