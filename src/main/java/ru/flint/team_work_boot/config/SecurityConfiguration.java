package ru.flint.team_work_boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.flint.team_work_boot.config.security.AuthorizedUser;
import ru.flint.team_work_boot.dao.UserRepository;
import ru.flint.team_work_boot.entity.Role;
import ru.flint.team_work_boot.entity.User;

import java.util.Optional;


@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration {
    private final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            log.debug("Authenticating '{}'", email);
            Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
            return new AuthorizedUser(optionalUser.orElseThrow(
                    () -> new UsernameNotFoundException("User '" + email + "' not found")));
        };
    }

    //  https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter#configuring-websecurity
    //  https://stackoverflow.com/a/61147599/548473
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**");
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasAnyRole(Role.STUDENT.name(),Role.LECTURER.name())
                .requestMatchers(HttpMethod.GET,"/api/students/**").hasAnyRole(Role.STUDENT.name(),Role.ADMIN.name())
                .requestMatchers("/api/students/**").hasRole(Role.LECTURER.name())
                .requestMatchers("/api/**").hasRole(Role.ADMIN.name())
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();

        return http.build();
    }
}
