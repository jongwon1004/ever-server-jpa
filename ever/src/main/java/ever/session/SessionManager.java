package ever.session;

import ever.constants.LoginUserConst;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

import static ever.constants.LoginUserConst.LOGIN_USER_NO;

@Component
public class SessionManager {

    public void createSession(Long userId) {
        HttpSession session = getCurrentSession();
        session.setAttribute(LOGIN_USER_NO, userId);
    }

    public Long getSessionUserId() {
        HttpSession session = getCurrentSession();
        return (Long) session.getAttribute(LOGIN_USER_NO);
    }

    private HttpSession getCurrentSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attr.getRequest().getSession(); // true -> 세션이 없으면 새로 생성
    }

}
