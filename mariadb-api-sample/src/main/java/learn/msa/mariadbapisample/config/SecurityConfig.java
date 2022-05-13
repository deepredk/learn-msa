package learn.msa.mariadbapisample.config;

import learn.msa.mariadbapisample.jwt.JwtAccessDeniedHandler;
import learn.msa.mariadbapisample.jwt.JwtAuthenticationEntryPoint;
import learn.msa.mariadbapisample.jwt.TokenProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
            .csrf().disable()

            .exceptionHandling() // 예외처리
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            // enable h2-console
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            // 세션을 사용하지 않기 때문에 STATELESS로 설정
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests() // HttpServletRequest를 사용한 요청들에 대해 접근제한
            .antMatchers("/api/hello").permitAll() // 얘는 인증없어도 됨
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/signup").permitAll()

            .anyRequest().authenticated() // 나머지는 인증되어야 함

            .and()
            .apply(new JwtSecurityConfig(tokenProvider));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring() // 무시해
            .antMatchers("/h2-console/**", "/favicon.ico"); // 요것들
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
