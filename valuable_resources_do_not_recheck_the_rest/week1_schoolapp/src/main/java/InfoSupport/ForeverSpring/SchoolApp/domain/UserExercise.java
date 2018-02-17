package InfoSupport.ForeverSpring.SchoolApp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "userexercises")
@Getter
@Setter
@NoArgsConstructor
public class UserExercise {

  @Id
  @Column(name = "userexercise_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToOne
  @JoinColumn(name = "exercise_id")
  private Exercise exercise;

  @Column(name = "status")
  private String status;

  @Column(name = "comment")
  private String comment;

}
