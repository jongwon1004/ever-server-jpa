package ever.repository.email;

import ever.entity.EmailAuth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@Rollback
class EmailAuthRepositoryImplTest {

    @Autowired
    EmailAuthRepository emailAuthRepository;

    @Test
    public void isEmailAuthenticated() {
        EmailAuth emailAuthenticated = emailAuthRepository.isEmailAuthenticated("whddnjs3340@naver.com");
        System.out.println("emailAuthenticated.getStatusType() = " + emailAuthenticated.getStatusType());
    }


}