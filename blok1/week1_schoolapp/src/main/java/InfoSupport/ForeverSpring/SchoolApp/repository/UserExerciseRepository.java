package InfoSupport.ForeverSpring.SchoolApp.repository;

import InfoSupport.ForeverSpring.SchoolApp.domain.UserExercise;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserExerciseRepository extends CrudRepository<UserExercise, Integer> {

    List<UserExercise> findByUserId(int userId);

    List<UserExercise> findByExerciseId(int exerciseId);
}
