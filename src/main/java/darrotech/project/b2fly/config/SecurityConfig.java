package darrotech.project.b2fly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/drag-and-drop", "/register").permitAll()  // Allow access to / and /index
                        .anyRequest().authenticated()  // Protect other endpoints
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")  // Custom login page
                        .permitAll()  // Allow access to the login page
                )
                .logout(LogoutConfigurer::permitAll);  // Allow access to logout
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCrypt password encoding
    }
}
