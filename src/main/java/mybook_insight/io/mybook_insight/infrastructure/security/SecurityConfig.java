package mybook_insight.io.mybook_insight.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(
				auth -> auth
					.requestMatchers("/users/join", "/users/admin")
					.permitAll()
					.anyRequest()
					.authenticated()
			);
		return httpSecurity.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		// PasswordEncoder는 BCryptPasswordEncoder를 사용하여 비밀번호를 암호화합니다.
		return new BCryptPasswordEncoder();
	}
}
