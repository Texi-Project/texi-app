package com.texi.app.configuration;

import com.texi.app.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**", "/login", "/auth").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/super/**").hasAuthority("SUPER")
                .antMatchers("/user/**").hasAuthority("USER")
                .anyRequest().authenticated() //all other urls can be access by any authenticated role
                .and()
                .formLogin() //enable form login instead of basic login
                    .loginPage("/auth")
                    .loginProcessingUrl("/user/login")
                    .failureUrl("/auth")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/user/dashboard")
                    .and()
                .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/auth")
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/denied")
                    .and()
//                .rememberMe()
//                    .rememberMeParameter("remember-me")
//                    .tokenRepository(tokenRepository())
//                    .and()
                .csrf()
                .disable()
        ;

//        http.rememberMe().rememberMeParameter("remember-me").key("uniqueAndSecret");
        http.headers().frameOptions().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/**",
                        "/swagger-ui.html",
                        "/webjars/**"
                );
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
        return jdbcTokenRepositoryImpl;
    }
}
