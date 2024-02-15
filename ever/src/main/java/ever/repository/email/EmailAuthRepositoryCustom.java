package ever.repository.email;

import ever.entity.EmailAuth;

public interface EmailAuthRepositoryCustom {

    String findCertNumberByEmail(String email);
}
