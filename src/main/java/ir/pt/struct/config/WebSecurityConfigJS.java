//package ir.pt.struct.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//
//@Configuration
//@EnableOAuth2Sso
//public class WebSecurityConfigJS extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                //.headers().cacheControl().disable().and()
//                .logout().logoutUrl("/logout")
//                .and().antMatcher("/**").authorizeRequests().antMatchers("/login", "/authorization/**",
//                "/bower_components/**", "/resources/**", "/logout").permitAll()
//                .anyRequest().authenticated()
//                .and().csrf().ignoringAntMatchers("/logout")
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//    }
//}
