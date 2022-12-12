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

    @Value("${zuul.routes.logout.url}")
    private String LOGOUT_URL;

    public String getLOGOUT_URL() {
        return LOGOUT_URL;
    }

    public void setLOGOUT_URL(String LOGOUT_URL) {
        this.LOGOUT_URL = LOGOUT_URL;
    }
}
