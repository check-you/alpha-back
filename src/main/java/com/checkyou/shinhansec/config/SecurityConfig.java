package com.checkyou.shinhansec.config;

import com.checkyou.shinhansec.jwt.JwtAuthenticationFilter;
import com.checkyou.shinhansec.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //각 ROLE에 따라 접근 구분
                .antMatchers("/api/account/*").hasRole("USER")
                .antMatchers("/api/user/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Component
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public class CorsFilter extends OncePerRequestFilter {
//        @Override
//        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//            System.out.println("cors called");
//            response.setHeader("Access-Control-Allow-Origin", "");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            response.setHeader("Access-Control-Allow-Headers",
//                    "Origin, X-Requested-With, Content-Type, Accept, Authorization, x-xsrf-token");
//            response.setHeader("Access-Control-Expose-Headers", "Accesstoken");
//
//            // allow cros preflight
//            if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//                response.setStatus(HttpServletResponse.SC_OK);
//            } else {
//                filterChain.doFilter(request, response);
//            }
//        }
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
