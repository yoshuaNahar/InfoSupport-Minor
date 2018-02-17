package InfoSupport.ForeverSpring.SchoolApp.repository;

import InfoSupport.ForeverSpring.SchoolApp.domain.UserExercise;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserExerciseRepository extends CrudRepository<UserExercise, Integer> {

  List<UserExercise> findByUserId(int userId);

  List<UserExercise> findByExerciseId(int exerciseId);
}
