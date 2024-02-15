package ever.repository.email;

import ever.entity.EmailAuth;

public interface EmailAuthRepositoryCustom {

    String findCertNumberByEmail(String email);

    EmailAuth isEmailAuthenticated(String email);

    void updateEmailAuthStatus(String email);
}
