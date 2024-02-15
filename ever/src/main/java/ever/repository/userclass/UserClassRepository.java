package ever.repository.userclass;


import ever.entity.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserClassRepository extends JpaRepository<UserClass, Long> {

    Optional<UserClass> findUserClassByClassName(String className);

    Boolean existsByClassName(String className);


}
