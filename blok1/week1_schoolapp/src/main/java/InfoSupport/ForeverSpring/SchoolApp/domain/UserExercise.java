package InfoSupport.ForeverSpring.SchoolApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
