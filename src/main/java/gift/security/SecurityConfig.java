package gift.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Spring Security를 사용하여 애플리케이션의 보안 설정을 구성
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // csrf(Cross-site request forgery) 보호 비활성화
            .exceptionHandling(exceptionHandling -> exceptionHandling // 예외 처리 exception handling
                .authenticationEntryPoint((request, response, authException) -> { // 인증 과정에서 실패 시
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 상태 코드를 401로 설정한다.
                    response.setHeader("WWW-Authenticate", "Bearer realm=\"Secured\"");
                    response.getWriter().write("Unauthorized: No valid token provided");
                })
            )
            .authorizeHttpRequests(authz -> authz // 요청 규직 인증
                .requestMatchers("/product/**","/members/register", "/members/login", "/","/h2-console/**","/h2-console/login.do?**").permitAll() // register/login은 요청은 인증 없이 허용된다.
                .anyRequest().authenticated() // 위에서 명시적으로 허용된 경로를 제외한 모든 다른 요청은 인증된 사용자만 접근, 사용자는 다른 request를 위해 로그인 해야됨
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}