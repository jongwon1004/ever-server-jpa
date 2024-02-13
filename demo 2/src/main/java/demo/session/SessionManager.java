package demo.session;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

@Component
public class SessionManager {

    public void createSession(String userId) {
        HttpSession session = getCurrentSession();
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid);
        session.setAttribute(userId, uuid);
    }

    public String getSession(Long userId) {
        HttpSession session = getCurrentSession();
        return (String) session.getAttribute(String.valueOf(userId));
    }

    private HttpSession getCurrentSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attr.getRequest().getSession(); // true -> 세션이 없으면 새로 생성
    }

}
