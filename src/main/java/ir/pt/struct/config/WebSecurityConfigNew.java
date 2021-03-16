//package ir.pt.struct.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//
//@Configuration
//@EnableOAuth2Sso
//public class WebSecurityConfigNew extends WebSecurityConfigurerAdapter {
//
//    public WebSecurityConfigNew() {
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.headers().cacheControl().disable()
//                .frameOptions().disable()
//                .and()
//                .logout().logoutUrl("/logout")
//                .invalidateHttpSession(true).deleteCookies("JSESSIONID","SESSION")
//                .clearAuthentication(true).addLogoutHandler(new CookieClearingLogoutHandler("JSESSIONID","SESSION"))
//                .and()
//                .antMatcher("/**").authorizeRequests()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/runtime-**", "/polyfills-**", "/vendor-**", "/main-**", "/scripts.**", "/styles.**", "/styles-**")
//                .permitAll().anyRequest().authenticated()
//                .and()
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//    }
//}
