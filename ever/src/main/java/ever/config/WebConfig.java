package ever.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:3001",
                        "http://localhost:8080",
                        "http://www.eccever.com",
                        "https://www.eccever.com",
                        "https://ever-ecc.vercel.app"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 모든 HTTP 메서드 허용
                .allowCredentials(true)  // 쿠키 및 HTTP 인증 허용
                .maxAge(3600)            // pre-flight 요청의 결과를 3600초 동안 캐시
                .exposedHeaders("JSESSIONID");  // 클라이언트에서 JSESSIONID 헤더에 접근 가능
    }

    /*
        서버에서 올바른 CORS 헤더를 제공해야 합니다.
        특히 Access-Control-Allow-Credentials 헤더를 true로 설정하고, Access-Control-Allow-Origin
        헤더에는 와일드카드(*) 대신 구체적인 도메인을 지정해야 합니다.
     */

}

