package ever.controller.init;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init() {
        initMemberService.init();
    }

    @Service
    static class InitMemberService {

        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init() {
//            UserClass sk2a = new UserClass("SK2A");
//            UserClass sk1a = new UserClass("SK1A");
//            UserClass sk3a = new UserClass("SK3A");
//            UserClass sk2b = new UserClass("SK2B");
//
//            em.persist(sk2a);
//            em.persist(sk3a);
//            em.persist(sk1a);
//            em.persist(sk2b);
//
//            Member member1 = new Member("CHOIJONGWON1", "2220019", "whddnjs33401", "johnny1", "whddnjs33401@naver.com", "dnflwlq1408", sk2a);
//            Member member2 = new Member("CHOIJONGWON2", "2220020", "whddnjs33402", "johnny2", "whddnjs33402@naver.com", "dnflwlq1408", sk1a);
//            Member member3 = new Member("CHOIJONGWON3", "2220021", "whddnjs33403", "johnny3", "whddnjs33403@naver.com", "dnflwlq1408", sk3a);
//            Member member4 = new Member("CHOIJONGWON4", "2220022", "whddnjs33404", "johnny4", "whddnjs33404@naver.com", "dnflwlq1408", sk2b);
//
//            em.persist(member1);
//            em.persist(member2);
//            em.persist(member3);
//            em.persist(member4);
        }
    }

}
