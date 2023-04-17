package prj.margin.anywhere.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import prj.margin.anywhere.config.auth.CustomAuthenticationFailureHandler;
import prj.margin.anywhere.config.auth.CustomOAuth2UserService;
import prj.margin.anywhere.config.auth.SimplePasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomOAuth2UserService oAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                        .antMatchers("/login/**", "/oauth/**").permitAll()
                        .antMatchers("/images/**", "/css/**", "/js/**", "/modules/**").permitAll()
                        .anyRequest().authenticated())
                // .loginPage() - URL 을 별도로 설정하지 않으면 default 로그인 페이지가 제공됨.
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("loginId")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .failureHandler(customAuthenticationFailureHandler))
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login")
                        .userInfoEndpoint().userService(oAuth2UserService))
                .userDetailsService(userDetailsService)
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                        .expiredUrl("/login?expired"))
                .logout(logout -> logout
                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SimplePasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}
