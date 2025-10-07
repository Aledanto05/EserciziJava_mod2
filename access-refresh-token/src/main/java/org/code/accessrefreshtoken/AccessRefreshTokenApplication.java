package org.code.accessrefreshtoken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AccessRefreshTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessRefreshTokenApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final UserDetailsService userDetailsService;
        private final PasswordEncoder passwordEncoder;

        public WebSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
            this.userDetailsService = userDetailsService;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/api/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }
}
