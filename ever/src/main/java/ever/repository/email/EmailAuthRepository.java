package ever.repository.email;

import ever.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long>, EmailAuthRepositoryCustom {

    void deleteEmailAuthByEmail(String email);

}
