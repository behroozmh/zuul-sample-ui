package ir.pt.struct.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@PropertySource(value = "classpath:application.yml")
public class Dashboard {
    @Value("${zuul.routes.myLogOut.url}")
    private String MY_AUTH_LOGOUT_URL;

    public String getMY_AUTH_LOGOUT_URL() {
        return MY_AUTH_LOGOUT_URL;
    }

    public void setMY_AUTH_LOGOUT_URL(String MY_AUTH_LOGOUT_URL) {
        this.MY_AUTH_LOGOUT_URL = MY_AUTH_LOGOUT_URL;
    }
}
