package ir.pt.struct.config;

import ir.pt.struct.handler.CustomLogoutHandler;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;


@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private CustomLogoutHandler logoutHandler;

    public WebSecurityConfig(CustomLogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl().disable()
                .frameOptions().deny()
                .and()
                .authorizeRequests()
                .antMatchers(new String[]{"/runtime-**", "/polyfills-**", "/main-**", "/scripts.**", "/styles.**",
                        "/login", "/bower_components/**", "/resources/**", "/logout"})
                .permitAll().anyRequest().authenticated()
                .and()
                .logout().addLogoutHandler(logoutHandler)
                .and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                //.csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository());
    }
}
