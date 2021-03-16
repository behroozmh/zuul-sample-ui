package ir.pt.struct.config;

import ir.pt.struct.handler.CustomLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomLogoutHandler logoutHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl().disable()
                .frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers(new String[]{"/runtime-**", "/polyfills-**", "/main-**", "/scripts.**", "/styles.**"})
                .permitAll().anyRequest().authenticated()
                .and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                .and()
                .logout().addLogoutHandler(logoutHandler);
    }

}
