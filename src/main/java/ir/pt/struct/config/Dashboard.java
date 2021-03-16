package ir.pt.struct.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Slf4j
@Data
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@PropertySource(value = "classpath:application.yml")
public class Dashboard {
    @Value("${clientId}")
    private String CLIENT_ID;
    @Value("${clientSecret}")
    private String CLIENT_SECRET;
    @Value("${scope}")
    private String SCOPE;
    @Value("${accessTokenUri}")
    private String TOKEN_URL;
    @Value("${username}")
    private String USERNAME;
    @Value("${password}")
    private String PASSWORD;
    @Value("${zuul.routes.myLogOut.url}")
    private String MY_AUTH_LOGOUT_URL;
    @Value("${zuul.routes.logout.url}")
    private String AUTH_LOGOUT_URL;
}