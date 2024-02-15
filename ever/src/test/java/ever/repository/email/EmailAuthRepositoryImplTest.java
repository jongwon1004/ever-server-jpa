package ever.repository.email;

import ever.enums.StatusType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class EmailAuthRepositoryImplTest {

    @Autowired
    EmailAuthRepository emailAuthRepository;

    @Test
    public void isEmailAuthenticated() {
        StatusType status = emailAuthRepository.isEmailAuthenticated("whddnjs3340@naver.com");
        System.out.println("status = " + status);
    }


}