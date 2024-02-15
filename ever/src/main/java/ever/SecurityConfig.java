package ever;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll() // 모든 경로에 대해 접근 허용
                        .anyRequest().authenticated() // 나머지 요청들은 인증 필요
                )
                .csrf(csrf -> csrf
                        .disable() // CSRF 보호 비활성화
                );

        return http.build();
    }
}
